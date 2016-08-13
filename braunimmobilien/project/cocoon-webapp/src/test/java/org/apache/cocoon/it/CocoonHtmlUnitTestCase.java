/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.it;

import static org.junit.Assert.*;

import com.gargoylesoftware.htmlunit.SubmitMethod;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import java.net.URL;

import org.apache.cocoon.tools.it.HtmlUnitTestCase;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.HeadMethod;

public abstract class CocoonHtmlUnitTestCase extends HtmlUnitTestCase {

    private static final String BASEURL = "http://localhost:8890";

    @Override
    protected URL setupBaseUrl()
            throws Exception {

        String baseUrl = System.getProperty("htmlunit.base-url");
        if (baseUrl == null) {
            baseUrl = BASEURL;
        }
        return new URL(baseUrl);
    }

    protected HeadMethod loadHeadResponse(final String pageURL,
            final int expectedStatusCode)
            throws Exception {

        final URL url = new URL(this.setupBaseUrl(), pageURL);
        final HeadMethod method = new HeadMethod(url.toExternalForm());
        final HttpClient client = new HttpClient();
        final int statusCode = client.executeMethod(method);
        assertEquals(expectedStatusCode, statusCode);

        return method;
    }

    /**
     * Sends HTTP GET request and loads response object. Handle redirects.
     */
    protected void loadResponseWithRedirects(final String pageURL)
            throws Exception {

        final URL url = new URL(this.setupBaseUrl(), pageURL);
        this.webClient.setRedirectEnabled(true);
        this.response = this.webClient.loadWebResponse(
                new WebRequestSettings(url, SubmitMethod.GET));
        this.webClient.setRedirectEnabled(false);
    }
}
