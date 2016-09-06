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
 * Test profiling.
 */
public class ProfilingTest extends CocoonHtmlUnitTestCase {

    @Test
    public void testCachingPipeline() throws Exception {
        this.loadResponse("caching-pipeline/on");
        this.loadResponse("caching-pipeline/on");
        String profilingId = this.response.getResponseHeaderValue("X-Cocoon-Profiling-ID");
        assertNotNull("Profiling ID not found", profilingId);

        this.loadResponse("cocoon-profiling/" + profilingId);
        assertEquals(200, this.response.getStatusCode());
        assertTrue(this.response.getContentAsString().length() > 100);
        assertTrue(this.response.getContentAsString().length() > 100);
        assertEquals("text/xml", this.response.getContentType());

        this.loadResponse("cocoon-profiling/" + profilingId + ".png");
        assertEquals(200, this.response.getStatusCode());
        assertTrue(this.response.getContentAsString().length() > 100);
        assertEquals("image/png", this.response.getContentType());
    }
}
