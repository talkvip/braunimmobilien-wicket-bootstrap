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
package org.apache.cocoon.rest.optional;

import java.util.concurrent.Executor;

import javax.servlet.ServletContext;

import org.apache.cocoon.rest.controller.annotation.Inject;
import org.apache.cocoon.rest.controller.method.Get;
import org.apache.cocoon.rest.controller.method.Post;
import org.apache.cocoon.rest.controller.response.RestResponse;

public abstract class AbstractAsyncronService implements Get, Post,Runnable{
    public class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    };
    protected ThreadPerTaskExecutor bidExecutor;
    @Inject
    private ServletContext servletContext;
    private int state=0;
    private String id;
    
    public void setId(String id) {
        this.id = id;
    }
    public AbstractAsyncronService() {
        bidExecutor=new ThreadPerTaskExecutor();
        id=this.getClass().getCanonicalName();
    }
    protected void updateProgress(int i) {
        state=i;
        servletContext.setAttribute(id, state);
    }
    protected Object getProgress() {
        Object progress = servletContext.getAttribute(id);
        if(null==progress){
            progress = new String("-1");
        }
        return progress;
    }
    
    
    /* Here you would do something like
     * 
     *  super.bidExecutor.execute(this);
     *  where you implement the progress update code in run()
     * 
     * (non-Javadoc)
     * @see org.apache.cocoon.rest.controller.method.Post#doPost()
     */
    public abstract RestResponse doPost() throws Exception;
    
    /* Here you would do something like
     * 
     * Object progress = getProgress();
     * 
     * (non-Javadoc)
     * @see org.apache.cocoon.rest.controller.method.Get#doGet()
     */
    public abstract RestResponse doGet() throws Exception;
    
    /* Here you would do something like
     * 
     * updateProgress(1);
     * 
     * To update the progress
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public abstract void run();
}
