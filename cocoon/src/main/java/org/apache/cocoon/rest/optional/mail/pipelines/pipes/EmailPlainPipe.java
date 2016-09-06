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
package org.apache.cocoon.rest.optional.mail.pipelines.pipes;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.cocoon.rest.optional.sample.SendMailPipeService;
import org.apache.cocoon.sax.component.XIncludeTransformer;
import org.apache.velocity.app.VelocityEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.cocoon.pipeline.NonCachingPipeline;
import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.sax.SAXPipelineComponent;
import org.apache.cocoon.sax.component.TextSerializer;
import org.apache.cocoon.sax.component.XMLSerializer;
import org.apache.cocoon.sax.component.XMLGenerator;
import org.apache.cocoon.sax.component.XSLTTransformer;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.apache.cocoon.sax.component.SchemaProcessorTransformer;
import org.apache.cocoon.optional.pipeline.components.sax.fop.FopSerializer;
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmailPlainPipe extends NonCachingPipeline<SAXPipelineComponent>
        implements Pipeline<SAXPipelineComponent> {
	@Autowired
	private VelocityEngine velocityEngine;
	 private static final Logger logger = LoggerFactory
	            .getLogger(EmailPlainPipe.class);
    @Override
    public void setup(OutputStream outputStream, Map<String, Object> parameters) {
    	 logger.debug(" Pipeline Beginn "+parameters);
    	 String xsl1File="";
    	if(parameters.containsKey("resolver")){
    		if(((String)parameters.get("resolver")).equals("velocity")){
    	String xmlFile="";
    	try{xmlFile=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, (String)parameters.get("inputFile"), "UTF-8", parameters);}
    	catch(Exception ex){xmlFile="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><document>"+ex+"</document>";}
    	logger.debug(" Pipeline Velocity "+xmlFile);
        XMLGenerator generator = new XMLGenerator(xmlFile.getBytes());
        this.addComponent(generator);
        this.addComponent(new XIncludeTransformer());
        String xslFile="";
        if(parameters.containsKey("xslString")){
       xslFile=(String)parameters.get("xslString");
        	
        	
        }
        else{ try{xslFile=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, (String)parameters.get("xslFile"), "UTF-8", parameters);}
    	catch(Exception ex){xslFile="";}}
        logger.debug(" Pipeline XInclude "+xslFile);
        Source xslSource = new StreamSource(new ByteArrayInputStream(xslFile.getBytes()));
        XSLTTransformer transformer = new XSLTTransformer(
                xslSource, new Date().getTime());
        transformer.setParameters(parameters);
        this.addComponent(transformer);
        logger.debug(" Pipeline XSLFile ");
        
        try{xsl1File=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, (String)parameters.get("xsl1File"), "UTF-8", parameters);}
    	catch(Exception ex){xsl1File="";}
        //System.err.println(xsl1File);
        Source xsl1Source = new StreamSource(new ByteArrayInputStream(xsl1File.getBytes()));
        XSLTTransformer transformer1 = new XSLTTransformer(
                xsl1Source, new Date().getTime());
        transformer1.setParameters(parameters);
        this.addComponent(transformer1);}
    		  logger.debug(" Pipeline XSLFile1 "+xsl1File);
        if(((String)parameters.get("outputType")).equals("text")){
        this.addComponent(TextSerializer.createPlainSerializer());}
        if(((String)parameters.get("outputType")).equals("xml")){
        	this.addComponent(XMLSerializer.createXMLSerializer());}
        if(((String)parameters.get("outputType")).equals("pdf")){
        //	Source fopSource = new StreamSource(new File("/home/java/wicket/appfuse/braunimmobilien-eclipse-maven/project/cocoon/src/main/resources/COB-INF/read/fop.xsd"));		
        //		this.addComponent(new SchemaProcessorTransformer(fopSource,new Date().getTime()));
        	FopSerializer fop=new FopSerializer();
        	fop.setConfiguration(parameters);
        	this.addComponent(fop);
        	  logger.debug(" Pipeline FopSerializer ");
        	}
        if(((String)parameters.get("resolver")).equals("ST")){
        
        }
    	}
       
        super.setup(outputStream, parameters);
    }
}
