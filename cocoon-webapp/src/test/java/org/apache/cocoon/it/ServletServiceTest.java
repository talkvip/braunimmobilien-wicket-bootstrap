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
 * Test the usage of servlet services in Cocoon sitemaps.
 */
public class ServletServiceTest extends CocoonHtmlUnitTestCase {

    @Test
    public void testSimpleServletServiceCallingAPipeline() throws Exception {
        this.loadXmlPage("ssf/local");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p[1]", "3-true");
    }

    @Test
    public void testSimpleServletServiceCallingAResource() throws Exception {
        this.loadResponse("ssf/read-resource");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/javascript", this.response.getContentType());
    }

    @Test
    public void testServiceConsumer() throws Exception {
        this.loadPostResponse("ssf/service-consumer", "<a>1</a>");
        assertTrue(this.response.getStatusCode() == 200);
        String contentAsString = this.response.getContentAsString();
        assertNotNull(contentAsString);
        assertTrue("The service has to return the passed request body as content.", contentAsString
                .contains("<a>1</a>"));
    }

    @Test
    public void testCallingATransformerServletService() throws Exception {
        this.loadXmlPage("ssf/calling-a-transformer-servlet-service");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p[1]", "transformer-pipeline");
    }

    @Test
    public void testCallingASerializerServletService() throws Exception {
        this.loadXmlPage("ssf/calling-a-serializer-servlet-service");
        assertTrue(this.response.getStatusCode() == 210);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "serializer-pipeline");
    }
}
