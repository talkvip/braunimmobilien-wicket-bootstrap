/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.it;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test readers
 */
public class ReaderTest extends CocoonHtmlUnitTestCase {

    /**
     * Call a pipeline that explicitly sets the mime-type of the resource.
     */
    @Test
    public void testReadingResourceWithExplicitMimeType() throws Exception {
        this.loadResponse("read/javascript-resource-explicit");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/javascript", this.response.getContentType());
    }

    /**
     * Call a pipeline that automatically sets the mime-type of the resource.
     */
    @Test
    public void testReadingResourceWithImplicitMimeType() throws Exception {
        this.loadResponse("read/javascript-resource-implicit.js");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("application/x-javascript", this.response.getContentType());
    }

    /**
     * Call a pipeline that uses an unknown file extensions.
     */
    @Test
    public void testReadingResourceWithUnknownMimeType() throws Exception {
        this.loadResponse("read/javascript-resource-implicit.abc");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("", this.response.getContentType());
    }

    /**
     * A resource reader supports conditional gets.
     */
    @Test
    public void testConditionalGet() throws Exception {
        this.loadResponse("read/javascript-resource-implicit.js");
        String lastModified = this.response.getResponseHeaderValue("Last-Modified");
        String eTag = this.response.getResponseHeaderValue("ETag");
        assertNotNull("Last-Modified has to be set at this point!", lastModified);
        assertNotNull("ETag has to be set at this point!", eTag);
        assertFalse("Last-Modified has to be set at this point!", "".equals(lastModified));
        assertFalse("ETag has to be set at this point!", "".equals(eTag));

        this.webClient.addRequestHeader("If-Modified-Since", lastModified);
        this.loadResponse("read/javascript-resource-implicit.js");
        assertEquals(304, this.response.getStatusCode());

        this.webClient.addRequestHeader("If-None-Match", eTag);
        this.loadResponse("read/javascript-resource-implicit.js");
        assertEquals(304, this.response.getStatusCode());
    }
}
