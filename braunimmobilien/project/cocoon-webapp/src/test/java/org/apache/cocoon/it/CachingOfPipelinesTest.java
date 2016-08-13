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
import org.apache.cocoon.sample.generation.CachingTimestampGenerator;
import org.apache.cocoon.sample.generation.TimestampGenerator;
import org.junit.Test;

/**
 * Test pipeline caching. The tests use the {@link TimestampGenerator} which always takes more 0.5 seconds to produce a
 * result.
 */
public class CachingOfPipelinesTest extends CocoonHtmlUnitTestCase {

    /**
     * Test if different matches in the same &lt;map:pipeline&gt;, produce different results.
     */
    @Test
    public void testCachingOfDifferentPipelines() throws Exception {
        this.loadResponse("sax-pipeline/simple-1");
        String content1 = this.response.getContentAsString();
        assertNotNull(content1);

        this.loadResponse("sax-pipeline/simple-2");
        String content2 = this.response.getContentAsString();
        assertNotNull(content2);

        assertFalse(content1.equals(content2));
    }

    /**
     * This caching pipeline uses the {@link CachingTimestampGenerator} that produces results valid for 1.5 seconds.
     * This means that the second request has to return the same result but the third a different one because the cache
     * key produced by the first result has become invalid in the meantime.
     */
    @Test
    public void testCachingPipeline() throws Exception {
        this.loadResponse("caching-pipeline/on");
        String content1 = this.response.getContentAsString();

        this.loadResponse("caching-pipeline/on");
        String content2 = this.response.getContentAsString();
        assertEquals("The response has to be always the same.", content1, content2);

        Thread.sleep(1000);
        this.loadResponse("caching-pipeline/on");
        String content3 = this.response.getContentAsString();
        assertFalse("The response has to change because the caching period has expired.", content3.equals(content2));
    }

    /**
     * This async-caching pipeline returns the same result for 2 seconds (see the 'expires' parameter in the sitemap).
     * After 2.1 seconds it still produces the same result but because the cache key of this pipeline has changed, a
     * separate thread is started that refreshs the cache.
     * 
     * After waiting for another second, a fresh result is returned.
     */
    @Test
    public void testExpiresAsyncCaching() throws Exception {
        this.loadResponse("/expires/async-caching-pipeline/on");
        String content1 = this.response.getContentAsString();

        Thread.sleep(2100);
        this.loadResponse("/expires/async-caching-pipeline/on");
        String content2 = this.response.getContentAsString();
        assertEquals("The response has to be the same as before.", content1, content2);

        Thread.sleep(1000);
        this.loadResponse("/expires/async-caching-pipeline/on");
        String content3 = this.response.getContentAsString();
        assertFalse("The response mustn't be the same as before.", content3.equals(content2));
    }

    /**
     * This caching pipeline returns the same result for 2 seconds (see the 'expires' parameter in the sitemap). After
     * 2.1 it produces a different result because the first result generated has become invalid in the meantime and the
     * refresh is being performed in the same thread.
     * 
     * @see {@link #testExpiresAsyncCaching()}
     */
    @Test
    public void testExpiresCachingPipeline() throws Exception {
        Thread.sleep(2000);
        this.loadResponse("/expires/caching-pipeline/on");
        assertEquals(200, this.response.getStatusCode());
        String content1 = this.response.getContentAsString();

        Thread.sleep(2100);
        this.loadResponse("/expires/caching-pipeline/on");
        assertEquals(200, this.response.getStatusCode());
        String content2 = this.response.getContentAsString();
        assertFalse("The response has to be the same as before.", content1.equals(content2));
    }

    @Test
    public void testInvalidExpiresCachingPipeline() throws Exception {
        this.loadResponse("/expires/caching-pipeline/invalid");
        assertEquals(500, this.response.getStatusCode());
    }

    /**
     * A non-caching pipeline mustn't produce the same result twice.
     */
    @Test
    public void testNonCachingPipeline() throws Exception {
        this.loadResponse("caching-pipeline/off");
        String content1 = this.response.getContentAsString();
        assertNotNull(content1);

        this.loadResponse("caching-pipeline/off");
        String content2 = this.response.getContentAsString();
        assertNotNull(content2);
        assertFalse("The response has to change with every request.", content1.equals(content2));
    }
}
