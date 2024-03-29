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
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * Test FOP serialization
 */
public class FOPTest extends CocoonHtmlUnitTestCase {

    /**
     * Create a PDF document
     */
    @Test
    public void testSerializeAsPDF() throws Exception {
        this.loadResponse("fop/test.pdf");
        assertTrue(this.response.getStatusCode() == 200);
        assertEquals("application/pdf", this.response.getContentType());

        String pdfContent = IOUtils.toString(this.response.getContentAsStream());
        assertTrue(pdfContent.startsWith("%PDF-1.4"));
    }
}
