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
 * Test the serializers components.
 */
public class SerializersTest extends CocoonHtmlUnitTestCase {

    @Test
    public void testEXML() throws Exception {
        this.loadResponse("/serializers/exml");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/xml", this.response.getContentType());
        assertEquals("utf-8", this.response.getContentCharSet());
        assertTrue(this.response.getContentAsString().indexOf("-//W3C//DTD XHTML 1.0 Strict//EN") == -1);
    }

    @Test
    public void testEXHTML() throws Exception {
        this.loadResponse("/serializers/exhtml");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/html", this.response.getContentType());
        assertEquals("utf-8", this.response.getContentCharSet());
        assertTrue(this.response.getContentAsString().indexOf("-//W3C//DTD XHTML 1.0 Strict//EN") > 0);
    }

    @Test
    public void testEHTML() throws Exception {
        this.loadResponse("/serializers/ehtml");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("text/html", this.response.getContentType());
        assertEquals("iso-8859-1", this.response.getContentCharSet());
        assertTrue(this.response.getContentAsString().indexOf("-//W3C//DTD HTML 4.01 Transitional//EN") > 0);
    }
}
