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
 * Test expression language usage
 */
public class ExpressionLanguageTest extends CocoonHtmlUnitTestCase {

    /**
     * JEXL test
     */
    @Test
    public void testJexl() throws Exception {
        this.loadXmlPage("expression-language/jexl?fileName=simple");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/simple", "simple-text");
    }

    /**
     * Map language test
     */
    @Test
    public void testMapNumbers() throws Exception {
        this.loadXmlPage("expression-language/map-numbers/simple");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/simple", "simple-text");
    }

    @Test
    public void testMapNamedParameters() throws Exception {
        this.loadXmlPage("expression-language/map-named-parameters/simple");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/simple", "simple-text");
    }

    /**
     * Nested matchers/maps
     */
    @Test
    public void testNextedMap() throws Exception {
        this.loadXmlPage("expression-language/nested/simple");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/simple", "simple-text");
    }
}
