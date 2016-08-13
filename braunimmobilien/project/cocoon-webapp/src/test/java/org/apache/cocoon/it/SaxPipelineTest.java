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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.apache.commons.httpclient.methods.HeadMethod;
import org.junit.Test;

/**
 * Test SAX Pipelines
 */
public class SaxPipelineTest extends CocoonHtmlUnitTestCase {

    /**
     * A simple pipeline that produces an HTML document.
     */
    @Test
    public void testSimplePipeline() throws Exception {
        this.loadResponse("/sax-pipeline/simple");
        assertEquals(200, this.response.getStatusCode());
        assertEquals("text/xml", this.response.getContentType());
        assertEquals(-1, this.response.getContentAsString().indexOf("-//W3C//DTD XHTML 1.0 Strict//EN"));
    }

    @Test
    public void testSimplePipelineTxt() throws Exception {
        this.loadResponse("/sax-pipeline/simple-txt");
        assertEquals(200, this.response.getStatusCode());
        assertTrue(this.response.getContentType().startsWith("text/plain"));
    }

    @Test
    public void testHead() throws Exception {
        HeadMethod headMethod = this.loadHeadResponse("/sax-pipeline/simple", 200);
        assertEquals("text/xml", headMethod.getResponseHeader("Content-type").getValue());
        String content = headMethod.getResponseBodyAsString();
        assertNull(content);
    }

    /**
     * A simple pipeline that produces an XHTML 1.0 document. This implicitly tests if the configuration of serializers
     * works properly.
     */
    @Test
    public void testSimplePipelineXhtml() throws Exception {
        this.loadResponse("/sax-pipeline/simple-xhtml");
        assertEquals(200, this.response.getStatusCode());
        assertEquals("text/html", this.response.getContentType());
        assertTrue(this.response.getContentAsString().indexOf("-//W3C//DTD XHTML 1.0 Strict//EN") > 0);
    }

    /**
     * A parameter is passed to an XSLT transformer.
     */
    @Test
    public void testSimplePipelineParameterPassingToTransformer() throws Exception {
        this.loadXmlPage("/sax-pipeline/simple-xml");
        assertEquals(200, this.response.getStatusCode());
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "3-false");
    }

    /**
     * A status code is set explicitly at a serializer.
     */
    @Test
    public void testSettingStatusCode() throws Exception {
        this.loadResponse("/sax-pipeline/unauthorized");
        assertEquals(401, this.response.getStatusCode());
        String lastModified = this.response.getResponseHeaderValue("Last-Modified");
        assertNull(lastModified);
    }

    @Test
    public void testSettingStatusCodeHead() throws Exception {
        this.loadHeadResponse("/sax-pipeline/unauthorized", 401);
    }
}
