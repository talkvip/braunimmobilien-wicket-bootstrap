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
import org.custommonkey.xmlunit.Diff;
import org.junit.Test;

/**
 * Test accessing the object model.
 */
public class ObjectModelTest extends CocoonHtmlUnitTestCase {

    /**
     * Accessing all request parameters from within a generator.
     */
    @Test
    public void testTemporaryRedirect() throws Exception {
        this.loadResponse("object-model/request-parameters?a=1&b=2&c=3");
        assertEquals(200, this.response.getStatusCode());
        String content = this.response.getContentAsString();
        String expectedContent = "<?xml version=\"1.0\"?><request-parameters><a>1</a><c>3</c><b>2</b></request-parameters>";
        assertTrue(new Diff(expectedContent, content).similar());
    }

}
