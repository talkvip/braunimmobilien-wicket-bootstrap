/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cocoon.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class JSONTest extends CocoonHtmlUnitTestCase {

    @Test
    public void testSerializeAsJSON() throws Exception {
        this.loadResponse("json/xml2json");
        assertEquals(200, this.response.getStatusCode());
        assertTrue(this.response.getContentType().startsWith("application/json"));

        final String json = IOUtils.toString(this.response.getContentAsStream());
        assertFalse(json.isEmpty());
    }

    @Test
    public void testGenerateFromJSON() throws Exception {
        this.loadResponse("json/json2xml");
        assertEquals(200, this.response.getStatusCode());
        assertEquals("text/xml", this.response.getContentType());

        final String xml = IOUtils.toString(this.response.getContentAsStream());
        assertFalse(xml.isEmpty());
    }
}
