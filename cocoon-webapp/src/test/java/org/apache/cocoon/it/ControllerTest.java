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
import org.apache.commons.httpclient.methods.HeadMethod;
import org.junit.Test;

/**
 * Test a SpringRESTController and StringTemplate.
 */
public class ControllerTest extends CocoonHtmlUnitTestCase {

    /**
     * Test a pipeline that calls a SpringRESTController which delegates to a pipeline that uses a controller
     * context-aware StringTemplate generator.
     */
    @Test
     public void testControllerInvocation() throws Exception {
        this.loadXmlPage("/controller/abc/foo?reqparam=1");
        int statusCode = this.response.getStatusCode();
        assertTrue(statusCode == 202);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p[1]", "name=foo");
        this.assertXPath("/html/body/p[2]", "id=abc");
        this.assertXPath("/html/body/p[3]", "reqparam=1");
        this.assertXPath("/html/body/p[4]", "testProperty=test");
    }

    /**
     * Use the HEAD method to access a pipeline that calls a SpringRESTController.
     */
    @Test
    public void testControllerInvocationHead() throws Exception {
        HeadMethod headMethod = this.loadHeadResponse("/controller/abc/foo?reqparam=1", 202);
        assertEquals("text/xml", headMethod.getResponseHeader("Content-type").getValue());
        String content = headMethod.getResponseBodyAsString();
        assertNull(content);
    }

    /**
     * Test the conditional GET support for controllers.
     */
    @Test
    public void testControllerConditionalGetInvocation() throws Exception {
        this.loadXmlPage("/controller/conditional-get/abc/foo?reqparam=1");
        int statusCode = this.response.getStatusCode();
        assertTrue(statusCode == 202);
        assertEquals("text/xml", this.response.getContentType());
        String eTag = this.response.getResponseHeaderValue("ETag");
        assertNotNull("ETag has to be set at this point!", eTag);
        this.assertXPath("/html/body/p[1]", "name=foo");
        this.assertXPath("/html/body/p[2]", "id=abc");
        this.assertXPath("/html/body/p[3]", "reqparam=1");
        this.assertXPath("/html/body/p[4]", "testProperty=test");

        this.webClient.addRequestHeader("If-None-Match", eTag);
        this.loadResponse("/controller/conditional-get/abc/foo?reqparam=1");
        assertEquals(304, this.response.getStatusCode());

        this.webClient.addRequestHeader("If-None-Match", eTag);
        this.loadResponse("/controller/conditional-get/abc/foo?reqparam=2");
        assertEquals(202, this.response.getStatusCode());
    }
}
