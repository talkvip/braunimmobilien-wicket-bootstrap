/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cocoon.sitemap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class MockHttpServletResponse implements HttpServletResponse {

    private String redirect;

    private int statusCode;

    public MockHttpServletResponse() {
        super();
    }

    public void addCookie(Cookie cookie) {
    	// ignore
    }

    public void addDateHeader(String name, long date) {
    	// ignore
    }

    public void addHeader(String name, String value) {
    	// ignore
    }

    public void addIntHeader(String name, int value) {
    	// ignore
    }

    public boolean containsHeader(String name) {
        return false;
    }

    public String encodeRedirectUrl(String url) {
        return url;
    }

    public String encodeRedirectURL(String url) {
        return url;
    }

    public String encodeUrl(String url) {
        return null;
    }

    public String encodeURL(String url) {
        return null;
    }

    public void flushBuffer() throws IOException {
    	// ignore
    }

    public int getBufferSize() {
        return 0;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public Locale getLocale() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return null;
    }

    public boolean hasRedirected() {
        return this.redirect != null;
    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {
    	// ignore
    }

    public void resetBuffer() {
    	// ignore
    }

    public void sendError(int sc) throws IOException {
    	// ignore
    }

    public void sendError(int sc, String msg) throws IOException {
    	// ignore
    }

    public void sendRedirect(String location) throws IOException {
        this.redirect = location;
    }

    public void setBufferSize(int size) {
    	// ignore
    }

    public void setCharacterEncoding(String charset) {
    	// ignore
    }

    public void setContentLength(int len) {
    	// ignore
    }

    public void setContentType(String type) {
    	// ignore
    }

    public void setDateHeader(String name, long date) {
    	// ignore
    }

    public void setHeader(String name, String value) {
    	// ignore
    }

    public void setIntHeader(String name, int value) {
    	// ignore
    }

    public void setLocale(Locale loc) {
    	// ignore
    }

    public void setStatus(int sc) {
        this.statusCode = sc;
    }

    public void setStatus(int sc, String sm) {
    	// ignore
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
