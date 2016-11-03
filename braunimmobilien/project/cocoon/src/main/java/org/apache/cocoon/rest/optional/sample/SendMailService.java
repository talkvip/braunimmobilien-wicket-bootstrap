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
package org.apache.cocoon.rest.optional.sample;

import java.util.Date;

import org.apache.cocoon.rest.controller.annotation.RESTController;
import org.apache.cocoon.rest.controller.annotation.RequestParameter;
import org.apache.cocoon.rest.controller.method.Post;
import org.apache.cocoon.rest.controller.response.RestResponse;
import org.apache.cocoon.rest.controller.response.TextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StringUtils;

@RESTController
public class SendMailService implements Post {
    @RequestParameter
    private String to;
    @RequestParameter
    private String from;
    @RequestParameter
    private String subject;
    @RequestParameter
    private String text;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage templateMessage;
    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory
            .getLogger(SendMailService.class);

    @Override
    public RestResponse doPost() throws Exception {
        if (sendMail()) {
            return new TextResponse("{\"status\":" + true + " }", "application/json");
        }
        return new TextResponse("{\"status\":" + false + " }", "application/json");
    }

    private boolean sendMail() {
     
        if(!StringUtils.isEmpty(to)){
            this.templateMessage.setTo(to);
        }
        if(!StringUtils.isEmpty(from)){
            this.templateMessage.setFrom(from);
        }
        if(!StringUtils.isEmpty(subject)){
            this.templateMessage.setSubject(subject);
        }
        if(!StringUtils.isEmpty(text)){
            this.templateMessage.setText(text);
        }
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setSentDate(new Date());
        try {
            this.mailSender.send(msg);

            return true;
        } catch (MailException ex) {
            logger.error(String.format(
                    "Could not send Mail!\n%s", msg.toString()), ex);
            return false;
        }
    }

}
