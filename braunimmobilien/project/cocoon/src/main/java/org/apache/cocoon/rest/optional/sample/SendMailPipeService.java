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
import org.apache.commons.io.IOUtils;
import java.util.Locale;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.springframework.core.io.InputStreamResource;
import braunimmobilien.cocoon.Configuration;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Angstatus;

import java.io.InputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;

import javax.mail.internet.MimeMessage;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.rest.controller.annotation.RESTController;
import org.apache.cocoon.rest.controller.annotation.RequestParameter;
import org.apache.cocoon.rest.controller.method.Post;
import org.apache.cocoon.rest.controller.response.RestResponse;
import org.apache.cocoon.rest.controller.response.TextResponse;
import org.apache.cocoon.rest.controller.response.URLResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.core.io.InputStreamSource;
import org.apache.commons.io.FileUtils;

import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.AngstatusManager;

import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.optional.pipeline.components.sax.jaxb.JAXBGenerator;
import org.apache.cocoon.optional.pipeline.components.sax.jaxb.GenericType;
import org.apache.cocoon.pipeline.NonCachingPipeline;
import org.apache.cocoon.sax.SAXPipelineComponent;
import org.apache.cocoon.sax.component.XMLGenerator;
import org.apache.cocoon.stringtemplate.StringTemplateGenerator;
import org.apache.cocoon.sax.component.SchemaProcessorTransformer;
import org.apache.cocoon.sax.component.TextSerializer;
import org.apache.cocoon.sax.component.XSLTTransformer;
import org.apache.cocoon.sax.component.XIncludeTransformer;
import org.apache.cocoon.sax.component.XMLSerializer;
import org.springframework.core.io.ByteArrayResource;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.apache.velocity.tools.generic.DateTool;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.net.URL;

import org.apache.cocoon.optional.pipeline.components.sax.fop.FopSerializer;
@RESTController
public class SendMailPipeService implements Post {
	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private NachweiseManager nachweiseManager;
	@Autowired
	private Configuration configurationCocoon;
	@Autowired
	private AngebotManager angebotManager;
	@RequestParameter
    private String name;
	@RequestParameter
    private String nachweisnr;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage templateMessage;
    @Autowired
    @Qualifier("emailPlainPipe")
    private Pipeline<?> mailPipe;
    @Autowired
    @Qualifier("emailPlainPipe")
    private Pipeline<?> mailTextPipe;
    @Autowired
    @Qualifier("emailPlainPipe")
    private Pipeline<?> mailTextPipe1;
    @Autowired
    @Qualifier("emailPlainPipe")
    private Pipeline<?> mailAttachmentPipe;
    @Autowired
    @Qualifier("emailPlainPipe")
    private Pipeline<?> mailexpAttachmentPipe;
    @Autowired
    @Qualifier("emailPlainPipe")
    private Pipeline<?> mailexpAttachmentPipe1;
    @Autowired
    @Qualifier("emailPlainPipe")
    private Pipeline<?> mailexpAttachmentPipe2;
    private String xml="<root> Hello <name/> how do you like the pipeline example</root>";
    private String xsl="<xsl:stylesheet xmlns=\"http://www.w3.org/1999/xhtml\" "
            + "version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">"
            + "<xsl:param name=\"name\" select=\"'cocoon'\"/>"
            + "<xsl:template match=\"/\"><text><xsl:apply-templates/></text></xsl:template>"
            + "<xsl:template match=\"name\"><xsl:value-of select=\"$name\"/></xsl:template>"
            + "</xsl:stylesheet>";
    private String identity="<?xml version=\"1.0\" ?>"
    		+"<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">"
    		+"<xsl:template match=\"/ | @* | node()\">"
    		+"<xsl:copy>"             
    		+"<xsl:apply-templates select=\"@* | node()\" />"
    		+"</xsl:copy>"
    		+"</xsl:template>"
    		+"</xsl:stylesheet>";
    private String email="h.h.braun@ich-ueberall.de";
    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory
            .getLogger(SendMailPipeService.class);

    @Override
    public RestResponse doPost() throws Exception {
        try {
            boolean success = sendMail();
            if (success) {
                return new TextResponse("{\"status\":" + true + " }", "application/json");
            }
        } catch (Exception e) {
            logger.error("could not send mail ",e);
        } 
        return new TextResponse("{\"status\":" + false + " }", "application/json");
    }

    private boolean sendMail() throws Exception {
        logger.debug("request received "+this.name+"  "+this.nachweisnr);
    	String text;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStreamSource inputStream;
       
        Long nachweisid=new Long(Long.parseLong(this.nachweisnr));
	    Nachweise nachweis=nachweiseManager.get(nachweisid);
	    logger.debug("access nachweis successful "+this.name+"  "+this.nachweisnr+"  "+nachweis.getId());
	    if(nachweis.getAngebot()!=null){
	    Iterator it=nachweis.getAngebot().getAngobjzuords().iterator();
	  while (it.hasNext()){
	   Angobjzuord angobjzuord=(Angobjzuord)it.next();
	   logger.debug("nachweis angobjzuord "+this.name+"  "+this.nachweisnr+"  "+angobjzuord.getId());
	  angobjzuord.getObjekte().setAngobjzuords(null);
	   angobjzuord.getObjekte().setNachweise(null);
	   angobjzuord.getObjekte().setObjperszuords(null);
	   angobjzuord.getObjekte().setScouts(null);
	   angobjzuord.getObjekte().getStrasse().setObjekte(null);
	   angobjzuord.getObjekte().getStrasse().setPersonen(null);
	   angobjzuord.getObjekte().getStrasse().getOrt().setStrassen(null);
	   angobjzuord.getObjekte().getStrasse().getOrt().getLand().setOrte(null);
	  
	  }  
	  nachweis.getAngebot().setNachweise(null);
	   nachweis.getAngebot().setNachweise1(null);
	   nachweis.getAngebot().setNachweise2(null);}
	    
	  if(nachweis.getAngebot1()!=null){
		  nachweis.getAngebot1().setNachweise(null);
	   nachweis.getAngebot1().setNachweise1(null);
	   nachweis.getAngebot1().setNachweise2(null);}
	 //   nachweis.setKunde(null);
	   logger.debug("Angebot fertig");
	  
	   if(nachweis.getObjekt()!=null){
			  
			  nachweis.getObjekt().setAngobjzuords(null);
			  nachweis.getObjekt().setNachweise(null);
			  nachweis.getObjekt().setObjperszuords(null);
			  nachweis.getObjekt().setScouts(null);
			  nachweis.getObjekt().getStrasse().setObjekte(null);
			  nachweis.getObjekt().getStrasse().setPersonen(null);
			  nachweis.getObjekt().getStrasse().getOrt().setStrassen(null);
			  nachweis.getObjekt().getStrasse().getOrt().getLand().setOrte(null);
			  
			  }
		  logger.debug("Objekt fertig");
		  
	   
	   
	   
	   if(nachweis.getKunde()!=null){  
		   nachweis.getKunde().setNachweise(null);
		   nachweis.getKunde().getPerson().setObjperszuords(null);
	    nachweis.getKunde().getPerson().setScouts(null);
	    nachweis.getKunde().getPerson().setNachweise(null);
	    nachweis.getKunde().getPerson().setMitarbeiter(null);
	    nachweis.getKunde().getPerson().setKunden(null);
	      nachweis.getKunde().getPerson().setEigentuemermuster(null);
	    nachweis.getKunde().getPerson().getStrasse().getOrt().setStrassen(null);;
	    nachweis.getKunde().getPerson().getStrasse().getOrt().getLand().setOrte(null);;
	    nachweis.getKunde().getPerson().getStrasse().setObjekte(null);
	   nachweis.getKunde().getPerson().getStrasse().setPersonen(null);}
	   logger.debug("Kunde fertig");
	   if(nachweis.getPerson()!=null){  
		 
		   /* 	   nachweis.getPerson().setObjperszuords(null);
	   nachweis.getPerson().setScouts(null);
	    nachweis.getPerson().setNachweise(null);
	    nachweis.getPerson().setMitarbeiter(null);
	    nachweis.getPerson().setKunden(null);
	      nachweis.getPerson().setEigentuemermuster(null);
	    nachweis.getPerson().getStrasse().getOrt().setStrassen(null);;
	    nachweis.getPerson().getStrasse().getOrt().getLand().setOrte(null);;
	    nachweis.getKunde().getPerson().getStrasse().setObjekte(null);
	   nachweis.getPerson().getStrasse().setPersonen(null);*/
	   }
	   logger.debug("person fertig");
		
	   //   nachweis.getKunde().setPerson(null);
	    // ;
	   if(nachweis.getMitarbeiter()!=null){    
	    nachweis.getMitarbeiter().getPerson().setObjperszuords(null);
	    nachweis.getMitarbeiter().getPerson().setScouts(null);
	    nachweis.getMitarbeiter().getPerson().setNachweise(null);
	    nachweis.getMitarbeiter().getPerson().setMitarbeiter(null);
	    nachweis.getMitarbeiter().getPerson().setKunden(null);
	    nachweis.getMitarbeiter().getPerson().setEigentuemermuster(null);
	    nachweis.getMitarbeiter().getPerson().getStrasse().getOrt().setStrassen(null);;
	    nachweis.getMitarbeiter().getPerson().getStrasse().getOrt().getLand().setOrte(null);;
	    nachweis.getMitarbeiter().getPerson().getStrasse().setObjekte(null);
	    nachweis.getMitarbeiter().getPerson().getStrasse().setPersonen(null);
	   }
	    logger.debug("mitarbeiter fertig");
		
	    
	    
//	    nachweis.setObjekt(null);
//	    nachweis.setPerson(null);
	   
	    if(nachweis.getKunde()!=null){email=nachweis.getKunde().getPerson().getEigtEmail();}
	    if(nachweis.getPerson()!=null){email=nachweis.getPerson().getEigtEmail();}
	    logger.debug("nachweis email set");
		
        
            if (name.equals("int")){
            	
        //mit Velocity und einer internen Pipeline
            	   logger.debug("int chosen "+this.name+"  "+this.nachweisnr);;
           	MimeMessage message = mailSender.createMimeMessage();
            	SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
            	 MimeMessageHelper helper = new MimeMessageHelper(message, true);
            	  helper.setTo(email);
                  helper.setFrom(configurationCocoon.getEmail());
                 helper.setCc(configurationCocoon.getEmail());
                 if (nachweis.getXtyp().getBetreff().equals("unterlagen1")) helper.setSubject(nachweis.getUnterlagen1());
                 else  helper.setSubject(nachweis.getXtyp().getBetreff());
                 message.setSentDate(new Date());
                 logger.debug("mail prepared "+this.name+"  "+this.nachweisnr);
               try{ 
            	
            	   Map<String, Object> parameters = new HashMap<String, Object>();
           	  parameters.put("configuration", configurationCocoon);
            	   parameters.put("resolver","velocity");
            	   parameters.put("inputFile", "nachweis.xml");
            	  // parameters.put("xslFile", "identity.xsl");
                  parameters.put("xslFile", "fopvorlage.xsl");
            	  parameters.put("xsl1File", "fopvariables.xsl");
            //	   parameters.put("xsl1File", "identity.xsl");
            	//   parameters.put("outputType", "xml");
            	   parameters.put("outputType", "pdf");
                  parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                  parameters.put("nachweis", nachweis);
                  parameters.put("locale", Locale.GERMAN);
                  parameters.put("date",new DateTool());
                mailAttachmentPipe.setup(baos, parameters);
                mailAttachmentPipe.execute();
                inputStream=new ByteArrayResource(baos.toByteArray());
               helper.addAttachment(nachweis.getXtyp().getXtypkuerzel()+".pdf", inputStream);
            //    helper.addAttachment(nachweis.getXtyp().getXtypkuerzel()+".xml", inputStream);
               }catch(Exception ex){  logger.error("xbrief pdf attachement error "+this.name+"  "+this.nachweisnr+" "+ex);
               return false;
               }
                logger.debug("xbrief pdf attachement "+this.name+"  "+this.nachweisnr);;
               try{
            	   Map<String, Object> parameters = new HashMap<String, Object>();
                   parameters.put("configuration", configurationCocoon);
                   parameters.put("resolver","velocity");
                   parameters.put("inputFile", "xbriefe/xsl/"+nachweis.getXtyp().getXvor().toLowerCase()+".xsl");
                   parameters.put("xslFile", "include.xsl");
                   parameters.put("xsl1File", "identity.xsl");
                   parameters.put("outputType", "xml");
                   if(!StringUtils.isEmpty(name)){
                       parameters.put("name", name);}
                   parameters.put("nachweis", nachweis);
                   ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
                 
                   mailTextPipe1.setup(baos3, parameters);
                   mailTextPipe1.execute();
                   String xslString=new String(baos3.toByteArray());	
                 
            	   
            	   
            	   parameters = new HashMap<String, Object>();
                parameters.put("configuration", configurationCocoon);
                parameters.put("resolver","velocity");
                parameters.put("inputFile", "nachweis.xml");
                parameters.put("xslString", xslString);
                parameters.put("xsl1File", "identity.xsl");
                parameters.put("outputType", "text");
                if(!StringUtils.isEmpty(name)){
                    parameters.put("name", name);}
                parameters.put("nachweis", nachweis);
                ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                mailTextPipe.setup(baos1, parameters);
                mailTextPipe.execute();
                  helper.setText(new String(baos1.toByteArray()));
                  logger.debug("xbrief mailtext "+this.name+"  "+this.nachweisnr);;
             if(nachweis.getXtyp().getXtypmitexposee()){
                
                  if(nachweis.getAngebot()!=null){
                	 InputStreamSource inputStreamexp=null;
                	 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                	  parameters = new HashMap<String, Object>();
                	  parameters.put("resolver","velocity");
                	  parameters.put("inputFile","exposee/"+nachweis.getAngebot().getId()+"/exposee/"+nachweis.getAngebot().getId()+ ".fo");
                      parameters.put("xslFile", "identity.xsl");
                      parameters.put("xsl1File", "identity.xsl");
                      parameters.put("outputType", "pdf");
                      parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                      parameters.put("nachweis", nachweis);
                    mailexpAttachmentPipe.setup(baos2, parameters);
                    mailexpAttachmentPipe.execute();
                    inputStreamexp=new ByteArrayResource(baos2.toByteArray());	
                    helper.addAttachment(nachweis.getAngebot().getId()+ ".pdf", inputStreamexp);
                    logger.debug("first exposee added "+this.name+"  "+this.nachweisnr);;
              }
                     if(nachweis.getAngebot1()!=null){
                	 InputStreamSource inputStreamexp=null;
               	 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
               	  parameters = new HashMap<String, Object>();
               	  parameters.put("resolver","velocity");
               	  parameters.put("inputFile","exposee/"+nachweis.getAngebot1().getId()+"/exposee/"+nachweis.getAngebot1().getId()+ ".fo");
                     parameters.put("xslFile", "identity.xsl");
                     parameters.put("xsl1File", "identity.xsl");
                     parameters.put("outputType", "pdf");
                     parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                     parameters.put("nachweis", nachweis);
                   mailexpAttachmentPipe1.setup(baos2, parameters);
                   mailexpAttachmentPipe1.execute();
                   inputStreamexp=new ByteArrayResource(baos2.toByteArray());	
                   helper.addAttachment(nachweis.getAngebot1().getId()+ ".pdf", inputStreamexp);
               }
                    if(nachweis.getAngebot2()!=null){
               	 InputStreamSource inputStreamexp=null;
              	 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
              	  parameters = new HashMap<String, Object>();
              	  parameters.put("resolver","velocity");
              	  parameters.put("inputFile","exposee/"+nachweis.getAngebot2().getId()+"/exposee/"+nachweis.getAngebot2().getId()+ ".fo");
                    parameters.put("xslFile", "identity.xsl");
                    parameters.put("xsl1File", "identity.xsl");
                    parameters.put("outputType", "pdf");
                    parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                    parameters.put("nachweis", nachweis);
                  mailexpAttachmentPipe2.setup(baos2, parameters);
                  mailexpAttachmentPipe2.execute();
                  inputStreamexp=new ByteArrayResource(baos2.toByteArray());	
                  helper.addAttachment(nachweis.getAngebot2().getId()+ ".pdf", inputStreamexp);
              }
             }
                if(nachweis.getAnlage1()!=null){
                	if (nachweis.getAnlage1().endsWith(".pdf")){
                		
                		File file =new File(configurationCocoon.getDocdir()+"/velocity/exposee/"+nachweis.getAnlage1());
                		 helper.addAttachment(nachweis.getAnlage1().replaceAll("/", "_"), file);
                	}
                	if (nachweis.getAnlage1().endsWith(".fo")){	
                	
                		 InputStreamSource inputStreamexp=null;
                    	 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    	  parameters = new HashMap<String, Object>();
                    	  parameters.put("resolver","velocity");
                    	  parameters.put("inputFile","exposee/"+nachweis.getAnlage1());
                          parameters.put("xslFile", "identity.xsl");
                          parameters.put("xsl1File", "identity.xsl");
                          parameters.put("outputType", "pdf");
                          parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                          parameters.put("nachweis", nachweis);
                        mailexpAttachmentPipe.setup(baos2, parameters);
                        mailexpAttachmentPipe.execute();
                        inputStreamexp=new ByteArrayResource(baos2.toByteArray());	
                        helper.addAttachment(nachweis.getAnlage1().replaceAll("/", "_").replaceAll(".fo", ".pdf"), inputStreamexp);
                 } 
                }
                if(nachweis.getAnlage2()!=null){
                	if (nachweis.getAnlage2().endsWith(".pdf")){
                		
                		File file =new File(configurationCocoon.getDocdir()+"/velocity/exposee/"+nachweis.getAnlage2());
                		 helper.addAttachment(nachweis.getAnlage2().replaceAll("/", "_"), file);
                	}
                	if (nachweis.getAnlage2().endsWith(".fo")){	
                	
                		 InputStreamSource inputStreamexp=null;
                    	 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    	  parameters = new HashMap<String, Object>();
                    	  parameters.put("resolver","velocity");
                    	  parameters.put("inputFile","exposee/"+nachweis.getAnlage2());
                          parameters.put("xslFile", "identity.xsl");
                          parameters.put("xsl1File", "identity.xsl");
                          parameters.put("outputType", "pdf");
                          parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                          parameters.put("nachweis", nachweis);
                        mailexpAttachmentPipe.setup(baos2, parameters);
                        mailexpAttachmentPipe.execute();
                        inputStreamexp=new ByteArrayResource(baos2.toByteArray());	
                        helper.addAttachment(nachweis.getAnlage2().replaceAll("/", "_").replaceAll(".fo", ".pdf"), inputStreamexp);
                 } 
                }
                
                if(nachweis.getAnlage3()!=null){
                	if (nachweis.getAnlage3().endsWith(".pdf")){
                		
                		File file =new File(configurationCocoon.getDocdir()+"/velocity/exposee/"+nachweis.getAnlage3());
                		 helper.addAttachment(nachweis.getAnlage3().replaceAll("/", "_"), file);
                	}
                	if (nachweis.getAnlage3().endsWith(".fo")){	
                	
                		 InputStreamSource inputStreamexp=null;
                    	 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    	  parameters = new HashMap<String, Object>();
                    	  parameters.put("resolver","velocity");
                    	  parameters.put("inputFile","exposee/"+nachweis.getAnlage3());
                          parameters.put("xslFile", "identity.xsl");
                          parameters.put("xsl1File", "identity.xsl");
                          parameters.put("outputType", "pdf");
                          parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                          parameters.put("nachweis", nachweis);
                        mailexpAttachmentPipe.setup(baos2, parameters);
                        mailexpAttachmentPipe.execute();
                        inputStreamexp=new ByteArrayResource(baos2.toByteArray());	
                        helper.addAttachment(nachweis.getAnlage3().replaceAll("/", "_").replaceAll(".fo", ".pdf"), inputStreamexp);
                 } 
                }
              
                if(nachweis.getAnlage4()!=null){
                	if (nachweis.getAnlage4().endsWith(".pdf")){
                		
                		File file =new File(configurationCocoon.getDocdir()+"/velocity/exposee/"+nachweis.getAnlage4());
                		 helper.addAttachment(nachweis.getAnlage4().replaceAll("/", "_"), file);
                	}
                	if (nachweis.getAnlage4().endsWith(".fo")){	
                	
                		 InputStreamSource inputStreamexp=null;
                    	 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    	  parameters = new HashMap<String, Object>();
                    	  parameters.put("resolver","velocity");
                    	  parameters.put("inputFile","exposee/"+nachweis.getAnlage2());
                          parameters.put("xslFile", "identity.xsl");
                          parameters.put("xsl1File", "identity.xsl");
                          parameters.put("outputType", "pdf");
                          parameters.put("userConfigPath",configurationCocoon.getUserConfigPath());
                          parameters.put("nachweis", nachweis);
                        mailexpAttachmentPipe.setup(baos2, parameters);
                        mailexpAttachmentPipe.execute();
                        inputStreamexp=new ByteArrayResource(baos2.toByteArray());	
                        helper.addAttachment(nachweis.getAnlage4().replaceAll("/", "_").replaceAll(".fo", ".pdf"), inputStreamexp);
                 } 
                }
                     
                     
                    
                     
               /* Iterator     it=nachweis.getAngebot().getAngobjzuords().iterator();
           		  while (it.hasNext()){
           		   Angobjzuord angobjzuord=(Angobjzuord)it.next();
           		
           		      
           		  
           		  }*/
              
                mailSender.send(message);
                    return true;
                } catch (Exception ex) {
                	ex.printStackTrace();
                    logger.error(String.format(
                            "Could not send Mail!\n%s", msg.toString()), ex);
                    ex.printStackTrace();
                    return false;
                }    	
            }
            
            
            
            
            
            if (name.equals("email")){
            	//mit
            	MimeMessage message = mailSender.createMimeMessage();
            	SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
               try{ 
            	   Map<String, Object> parameters = new HashMap<String, Object>();
                  parameters.put("inputFile", "nachweis.xml");
                  parameters.put("xslFile", "fopvorlage.xsl");
                  parameters.put("xsl1File", "fopvariables.xsl");
                  parameters.put("outputType", "pdf");
                  if(!StringUtils.isEmpty(name)){
                      parameters.put("name", name);}
                  parameters.put("nachweis", nachweis);
                mailAttachmentPipe.setup(baos, parameters);
                System.err.println("Before First pipeline executed");
                try{mailAttachmentPipe.execute();}catch(Exception ex){
                	System.err.println("vvvvvvvvvvvvvvvvvv "+ex);
                	ex.printStackTrace();
                }
                inputStream=new ByteArrayResource(baos.toByteArray());
                System.err.println("First pipeline executed");
                parameters = new HashMap<String, Object>();
                parameters.put("inputFile", "nachweis.xml");
                parameters.put("xslFile", "nachweis.xsl");
                parameters.put("xsl1File", "identity.xsl");
                parameters.put("outputType", "text");
                if(!StringUtils.isEmpty(name)){
                    parameters.put("name", name);}
                parameters.put("nachweis", nachweis);
                ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
              mailTextPipe.setup(baos1, parameters);
              // execute the pipe and create the txt
              mailTextPipe.execute();
              // set text response
              URL oracle = new URL("http://localhost:8888/fop/test1.pdf");
              	        BufferedReader in = new BufferedReader(
            	        new InputStreamReader(oracle.openStream()));
              	        String wholedoc="";
            	        String inputLine;
            	        while ((inputLine = in.readLine()) != null)
            	            wholedoc=wholedoc+inputLine;
            	        in.close();  
                text = new String(baos1.toByteArray());
                System.err.println(text);
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo("h.h.braun@ich-ueberall.de");
                helper.setFrom("braunimmobilien@online.de");
               helper.setText(text); 
               helper.addAttachment("page.pdf", new ByteArrayResource( IOUtils.toByteArray(new URL("http://localhost:8888/fop/test1.pdf"))  ));
                message.setSentDate(new Date());
                System.err.println("Before Mail send");
                mailSender.send(message);
             System.err.println("Mail send");
                    return true;
                } catch (Exception ex) {
                	 System.err.println("Mail send error");
                    logger.error(String.format(
                            "Could not send Mail!\n%s", msg.toString()), ex);
                    ex.printStackTrace();
                    return false;
                }    	
            }
            
            
            
            
            
            
            
            
            if (name.equals("link")){
            	//keine Attachments dafür links und HTML Text
            	MimeMessage message = mailSender.createMimeMessage();
            	SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
               try{  
            	   Map<String, Object> parameters = new HashMap<String, Object>();
                  parameters.put("inputFile", "nachweis.xml");
                  parameters.put("xslFile", "fopvorlage.xsl");
                  parameters.put("xsl1File", "fopvariables.xsl");
                  parameters.put("outputType", "pdf");
                  if(!StringUtils.isEmpty(name)){
                      parameters.put("name", name);}
                  parameters.put("nachweis", nachweis);
            	  // prepare the pipe
                mailAttachmentPipe.setup(baos, parameters);
                // execute the pipe and create the txt
                System.err.println("Before First pipeline executed");
                try{mailAttachmentPipe.execute();}catch(Exception ex){
                	System.err.println("vvvvvvvvvvvvvvvvvv "+ex);
                	ex.printStackTrace();
                }
                // set text response
                inputStream=new ByteArrayResource(baos.toByteArray());
                System.err.println("First pipeline executed");
                parameters = new HashMap<String, Object>();
                parameters.put("inputFile", "nachweis.xml");
                parameters.put("xslFile", "nachweis.xsl");
                parameters.put("xsl1File", "identity.xsl");
                parameters.put("outputType", "text");
                if(!StringUtils.isEmpty(name)){
                    parameters.put("name", name);}
                parameters.put("nachweis", nachweis);
                ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
          	  // prepare the pipe
              mailTextPipe.setup(baos1, parameters);
              // execute the pipe and create the txt
              mailTextPipe.execute();
              // set text response
              URL oracle = new URL("http://localhost:8888/fop/test1.pdf");
              	        BufferedReader in = new BufferedReader(
            	        new InputStreamReader(oracle.openStream()));
              	        String wholedoc="";
            	        String inputLine;
            	        while ((inputLine = in.readLine()) != null)
            	            wholedoc=wholedoc+inputLine;
            	        in.close();
                text = new String(baos1.toByteArray());
                System.err.println(text);
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo("h.h.braun@ich-ueberall.de");
                helper.setFrom("braunimmobilien@online.de");
               helper.setText(text);
               
               helper.addAttachment("page.pdf", new ByteArrayResource(IOUtils.toByteArray(wholedoc)));
                message.setSentDate(new Date());
                System.err.println("Before Mail send");
                mailSender.send(message);
             System.err.println("Mail send");
                    return true;
                } catch (Exception ex) {
                	 System.err.println("Mail send error");
                    logger.error(String.format(
                            "Could not send Mail!\n%s", msg.toString()), ex);
                    ex.printStackTrace();
                    return false;
                }    	
            }
            
            
            
            
            if (name.equals("map")){
            	//Attachements aus dem Internet
            	
            	MimeMessage message = mailSender.createMimeMessage();
               try{  
            	  
              Connection.Response response1= Jsoup.connect("http://localhost:8888/controllerbraunimmobilien/angebot/RH100?reqparam=3")
            		  .ignoreContentType(true)
                      .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
                      .referrer("http://www.google.com")   
                      .timeout(12000) 
                      .execute();
              Connection.Response response= Jsoup.connect("http://localhost:8888/controllerbraunimmobilien/angebot/RH100?reqparam=4")
            		  .ignoreContentType(true)
                      .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
                      .referrer("http://www.google.com")   
                      .timeout(12000) 
                      .execute();
                text = new String(response.bodyAsBytes());
                System.err.println(text);
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo("h.h.braun@ich-ueberall.de");
                helper.setFrom("braunimmobilien@online.de");
               helper.setText(text);
               
               helper.addAttachment("page.pdf", new ByteArrayResource(response1.bodyAsBytes() ));
                message.setSentDate(new Date());
                System.err.println("Before Mail send");
                mailSender.send(message);
             System.err.println("Mail send");
                    return true;
                } catch (Exception ex) {
                	 System.err.println("Mail send error");
                    logger.error(String.format(
                            "Could not send Mail!\n%s", message.toString()), ex);
                    ex.printStackTrace();
                    return false;
                }    	
            }
                 
            if (name.equals("servlet")){
            	//Attachements aus dem Internet
            	
            	MimeMessage message = mailSender.createMimeMessage();
               try{  
            	   Map<String, Object> data = new HashMap<String, Object>();
            	   ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            	   URLResponse response1= new URLResponse("servlet:/controller/screen", data);
            	   response1.execute(baos1);
            	   URLResponse response= new URLResponse("servlet:/controller/screen", data);
            	   response.execute(baos);
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setTo("h.h.braun@ich-ueberall.de");
                helper.setFrom("braunimmobilien@online.de");
               helper.setText(new String(baos1.toByteArray()));
               
               helper.addAttachment("page.pdf", new ByteArrayResource(baos.toByteArray() ));
                message.setSentDate(new Date());
                System.err.println("Before Mail send");
                mailSender.send(message);
             System.err.println("Mail send");
                    return true;
                } catch (Exception ex) {
                	 System.err.println("Mail send error");
                    logger.error(String.format(
                            "Could not send Mail!\n%s", message.toString()), ex);
                    ex.printStackTrace();
                    return false;
                }    	
            } 
            
            if (name.equals("jaxb")){
            	//Generierung mit JAXB
            	
            	 MimeMessage message = mailSender.createMimeMessage();
            	try{
            	     Pipeline<SAXPipelineComponent> pipeline = new NonCachingPipeline<SAXPipelineComponent>();
            	  GenericType<Nachweise> nachweisgen= GenericType.toGenericType(nachweis);
          		  pipeline = new NonCachingPipeline<SAXPipelineComponent>();
          			pipeline.addComponent(new JAXBGenerator(nachweisgen));
          			pipeline.addComponent(new XMLSerializer());
          			pipeline.setup(baos);
          				pipeline.execute();
          				inputStream=new ByteArrayResource(baos.toByteArray());
          				MimeMessageHelper helper = new MimeMessageHelper(message, true);
                  helper.setTo("h.h.braun@ich-ueberall.de");
                  helper.setFrom("braunimmobilien@online.de");   
                 helper.setText("JAXB Pipeline!");
                  helper.addAttachment("JAXBNachweis.xml", inputStream);
                  message.setSentDate(new Date());
                  mailSender.send(message);
                 logger.debug(String.format(
                         "Could send Mail!\n%s", message.toString()));
                 return true;
             } catch (Exception ex) {
                 logger.error(String.format(
                         "Could not send Mail!\n%s", message.toString()), ex);
                 ex.printStackTrace();
                 return false;
             } 	
            }
            
            
            
            
            
            
            if (name.equals("string")){
            	//Generierung mit dem StringTemplateGenerator
            	
           	 MimeMessage message = mailSender.createMimeMessage();
           	try{
           		
           	 Map<String, Object> parameters = new HashMap<String, Object>();
             parameters.put("nachweis", nachweis);
           
           	     Pipeline<SAXPipelineComponent> pipeline = new NonCachingPipeline<SAXPipelineComponent>();
           	  GenericType<Nachweise> nachweisgen= GenericType.toGenericType(nachweis);
         		  pipeline = new NonCachingPipeline<SAXPipelineComponent>();
         		 File file =new File(configurationCocoon.getDocdir()+"/ST/nachweis.xml");
         	//	 XMLGenerator template= new XMLGenerator(file.toURL());
         		
         	  StringTemplateGenerator template=new StringTemplateGenerator(file);
         		 template.setConfiguration(parameters);
         		  pipeline.addComponent(template);
         		 pipeline.addComponent(new XIncludeTransformer());
         		 
         		 File file1 =new File(configurationCocoon.getDocdir()+"/velocity/xbriefe/xsl/"+nachweis.getXtyp().getXtypkuerzel().toLowerCase()+".xsl");
                 Source xslSource = new StreamSource(file1);
                 XSLTTransformer transformer = new XSLTTransformer(
                         xslSource, new Date().getTime());
                 transformer.setParameters(parameters);
                 pipeline.addComponent(transformer);
         		 
         			pipeline.addComponent(TextSerializer.createPlainSerializer());
         			pipeline.setup(baos);
         				pipeline.execute();
         				MimeMessageHelper helper = new MimeMessageHelper(message, true);
                 helper.setTo("h.h.braun@ich-ueberall.de");
                 helper.setFrom("braunimmobilien@online.de");   
                helper.setText(new String(baos.toByteArray()));
               //  helper.addAttachment("JAXBNachweis.xml", inputStream);
                 message.setSentDate(new Date());
                 mailSender.send(message);
                logger.debug(String.format(
                        "Could send Mail!\n%s", message.toString()));
                return true;
            } catch (Exception ex) {
                logger.error(String.format(
                        "Could not send Mail!\n%s", message.toString()), ex);
                ex.printStackTrace();
                return false;
            } 	
           }
            
            
            
            return false;       
    }
}
