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
 * Test the Cocoon-Wicket integration
 */
public class WicketTest extends CocoonHtmlUnitTestCase {

    /**
     * The the Wicket homepage.
     */
    @Test
    public void testHomepage()
            throws Exception {

        this.loadResponseWithRedirects("/wicket/hello-wicket");
        assertEquals(200, this.response.getStatusCode());
        assertEquals("text/html", this.response.getContentType());
    }

    /**
     * A Wicket redirect
     */
    @Test
    public void testRedirect()
            throws Exception {

        this.loadResponse("/wicket/");
        assertEquals(302, this.response.getStatusCode());
        assertTrue(this.response.getResponseHeaderValue("Location").
                endsWith("/wicket/hello-wicket"));
    }

    /**
     * A Wicket resource
     */
    @Test
    public void testWicketAjaxResource()
            throws Exception {

        this.loadResponse("/wicket/resources/org.apache.wicket.ajax."
                + "WicketAjaxReference/wicket-ajax.js");
        assertEquals(200, this.response.getStatusCode());
    }
}
