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
 * Test passing parameters.
 */
public class ParameterPassing extends CocoonHtmlUnitTestCase {

    /**
     * Passing parameters from a matcher to the sitemap.
     */
    //@Test
    public void testPassingParamatersFromMatcherToSitemap() throws Exception {
        this.loadXmlPage("parameter-passing/working");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        this.assertXPath("/simple", "simple-text");
    }

    /**
     * Passing null from a matcher to the sitemap (-> doesn't match)
     */
    //@Test
    public void testPassingNullMatcherToSitemap() throws Exception {
        this.webClient.setThrowExceptionOnFailingStatusCode(false);
        this.loadResponse("parameter-passing/failing");
        assertTrue(this.response.getStatusCode() == 404);
    }
}
