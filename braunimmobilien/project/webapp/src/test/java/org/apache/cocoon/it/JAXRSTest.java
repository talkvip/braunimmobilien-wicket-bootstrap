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
 * Test the usage of JAX-RS services.
 */
public class JAXRSTest extends CocoonHtmlUnitTestCase {

    @Test
     public void test401StatusCode() throws Exception {
        this.loadResponse("/jax-rs/sample/sax-pipeline/unauthorized");
        int statusCode = this.response.getStatusCode();
        assertTrue(statusCode == 401);

        String lastModified = this.response.getResponseHeaderValue("Last-Modified");
        assertNull(lastModified);

        String cocoonVersion = this.response.getResponseHeaderValue("X-Cocoon-Version");
        assertNotNull(cocoonVersion);
        assertFalse(cocoonVersion.equals(""));
    }

    @Test
    public void test404() throws Exception {
        this.loadResponse("/jax-rs/sample/not-exisiting");
        int statusCode = this.response.getStatusCode();
        assertTrue(statusCode == 404);
    }

    @Test
    public void testPassingParameters() throws Exception {
        this.loadXmlPage("/jax-rs/sample/parameter-passing/1?req-param=1");
        int statusCode = this.response.getStatusCode();
        assertTrue(statusCode == 202);
        assertEquals("text/xml", this.response.getContentType());

        this.assertXPath("/html/body/p[1]", "name=Donald Duck");
        this.assertXPath("/html/body/p[2]", "id=1");
        this.assertXPath("/html/body/p[3]", "reqparam=1");
        this.assertXPath("/html/body/p[4]", "testProperty=test");
    }

    @Test
    public void testAnotherResource() throws Exception {
        this.loadResponse("jax-rs/sample2/read/javascript-resource-implicit.js");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals(this.response.getContentType(), "application/x-javascript");
    }

    @Test
    public void testConditionalGet() throws Exception {
        this.loadResponse("jax-rs/sample2/read/javascript-resource-implicit.js");
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
