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
import org.junit.Ignore;
import org.junit.Test;

/**
 * Testing advanced matching scenarios.
 */
public class MatcherTest extends CocoonHtmlUnitTestCase {

    @Test
    public void testStartsWithWhen() throws Exception {
        this.loadXmlPage("advanced-matching?myparam=55");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "5");
    }

    @Test
    public void testEqualsWhen() throws Exception {
        this.loadXmlPage("advanced-matching?myparam=11");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "11");
    }

    @Test
    public void testRegexpWhen() throws Exception {
        this.loadXmlPage("advanced-matching?myparam=22");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "2");
    }

    @Test
    public void testOtherwise() throws Exception {
        this.loadXmlPage("advanced-matching?myparam=32");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "otherwise");

        this.loadXmlPage("advanced-matching");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "otherwise");
    }

    @Test
    public void testMatchUncompletePipeline() throws Exception {
        this.loadXmlPage("advanced-matching-2?myparam=1");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "1");

        this.loadXmlPage("advanced-matching-2?myparam=2");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "otherwise");
    }

    @Test
    public void testDeeplyNestedMatcherSelector1() throws Exception {
        this.loadXmlPage("advanced-matching-4?param_a=1&param_b=1&param_c=1&param_d=1&param_e=1");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p[text() = '1']", "1");
        this.assertXPath("/html/body/p[text() = '2']", "2");
        this.assertXPath("/html/body/p[text() = '3']", "3");
        this.assertXPath("/html/body/p[text() = '4']", "4");
        this.assertXPath("/html/body/p[text() = '5']", "");
        this.assertXPath("/html/body/p[text() = '6']", "6");
        this.assertXPath("/html/body/p[text() = '7']", "7");
        this.assertXPath("/html/body/p[text() = '8']", "");
    }

    @Test
    public void testDeeplyNestedMatcherSelector2() throws Exception {
        this.loadXmlPage("advanced-matching-4?param_a=1&param_b=1&param_c=1&param_d=2&param_e=1");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p[text() = '1']", "1");
        this.assertXPath("/html/body/p[text() = '2']", "2");
        this.assertXPath("/html/body/p[text() = '3']", "3");
        this.assertXPath("/html/body/p[text() = '4']", "");
        this.assertXPath("/html/body/p[text() = '5']", "");
        this.assertXPath("/html/body/p[text() = '6']", "6");
        this.assertXPath("/html/body/p[text() = '7']", "7");
        this.assertXPath("/html/body/p[text() = '8']", "");
    }

    @Test
    public void testDeeplyNestedMatcherSelector3() throws Exception {
        this.loadXmlPage("advanced-matching-4?param_a=1&param_b=2&param_e=1&param_g=2");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p[text() = '1']", "1");
        this.assertXPath("/html/body/p[text() = '2']", "");
        this.assertXPath("/html/body/p[text() = '3']", "");
        this.assertXPath("/html/body/p[text() = '4']", "");
        this.assertXPath("/html/body/p[text() = '5']", "5");
        this.assertXPath("/html/body/p[text() = '6']", "6");
        this.assertXPath("/html/body/p[text() = '7']", "7");
        this.assertXPath("/html/body/p[text() = '8']", "8");
    }

    @Test
    public void testDeeplyNestedMatcherSelector4() throws Exception {
        this.loadXmlPage("advanced-matching-4?param_g=1");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p[text() = '1']", "1");
        this.assertXPath("/html/body/p[text() = '2']", "");
        this.assertXPath("/html/body/p[text() = '3']", "");
        this.assertXPath("/html/body/p[text() = '4']", "");
        this.assertXPath("/html/body/p[text() = '5']", "");
        this.assertXPath("/html/body/p[text() = '6']", "");
        this.assertXPath("/html/body/p[text() = '7']", "7");
        this.assertXPath("/html/body/p[text() = '8']", "");
    }

    @Test
    public void testNamedMatcher() throws Exception {
        this.loadXmlPage("named-matcher/1?param_a=1&param_b=1");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "1");
    }

    @Test
    @Ignore
    public void testDeepRelativeMatcher() throws Exception {
        this.loadXmlPage("deep-relative-matcher/1?param_a=1&param_b=1");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/html/body/p", "1");
    }
}
