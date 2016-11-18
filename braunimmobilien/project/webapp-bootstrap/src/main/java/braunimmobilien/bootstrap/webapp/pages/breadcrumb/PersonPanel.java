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
package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.io.FileReader;
import java.util.Arrays;
import org.apache.cxf.common.util.StringUtils;
import braunimmobilien.bootstrap.webapp.pages.einstellungen.EinstellungenPanel;
import braunimmobilien.bootstrap.webapp.pages.objekt.ObjektTree;
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.Application;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelectConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.ajax.AjaxRequestTarget;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.GroupMembershipInfo;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant;
import com.google.api.services.gmail.Gmail;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.common.collect.Lists;
import com.google.gdata.client.contacts.ContactsService;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationMessage;

import braunimmobilien.service.EigentuemertypManager;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapCheckbox;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import braunimmobilien.model.*;

import braunimmobilien.service.StrassenManager;
import org.apache.wicket.Application;

import braunimmobilien.webapp.person.*;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class PersonPanel extends BreadCrumbPanel
{
	private String accesskey;
	private Class responsepage;
	private PageParameters pageparameters;
	private boolean withNext=true;	
	private boolean withLocation=true;
	String subject = "no subject";
String result = "no result";
private String specialusage="";
	/* private static final String SCOPE[] = new String[]{"https://www.google.com/m8/feeds","https://www.googleapis.com/auth/calendar","https://mail.google.com/"};
	
	private static final String SCOPECALENDAR = "https://www.googleapis.com/auth/calendar";
	  // Check https://developers.google.com/gmail/api/auth/scopes for all available scopes
	  private static final String APP_NAME = "braunimmobiliencalendar";
	  // Email address of the user, or "me" can be used to represent the currently authorized user.
	  private static final String USER = "wichtigtuer.braun@gmail.com";
	  // Path to the client_secret.json file downloaded from the Developer Console
	  private static final String CLIENT_SECRET_PATH = "/home/braun/project/calendarclient/calendarclient_secret.json";

	  private static GoogleClientSecrets clientSecrets;*/
	  @SpringBean
		ScoutManager scoutManager;
	@SpringBean
	ObjektManager objektManager;
	@SpringBean
	StrassenManager strassenManager;
	@SpringBean
	PersonManager personenManager;
//		SynchronizeContacts 	synchronizecontacts=null;
	@SpringBean
    private  EigentuemertypManager eigentuemertypManager;
	//	@SpringBean
	//	EigtstatusManager eigtstatusManager;
	    static Logger logger = LoggerFactory.getLogger(PersonPanel.class);
		
	
		
		 
		 
		
	
		
		
	
	
	
	
	
	
		
	/**
	 * Construct.
	 * 
	 * @param id
	 * @param breadCrumbModel
	 * @param result
	 *            The 'result' to display as a label
	 */
	
	
	
	public PersonPanel(final String id,final Class extresponsepage,final PageParameters extpageparameters, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
		pageparameters=extpageparameters;
		responsepage=extresponsepage;
		 if(pageparameters.getPosition("who")>0) {info(pageparameters.get("who").toString());
		 pageparameters.remove("who");}
		 if(pageparameters.getPosition("phone")>0) {error(pageparameters.get("phone").toString());
		 pageparameters.remove("phone");}
		

		 
			
		boolean found=false;
		 logger.debug("new PersonPanel responsepage "+responsepage.getSimpleName()+" PageParameters "+pageparameters);
		 if(pageparameters.getPosition("error")>0) {error(pageparameters.get("error").toString());
		 pageparameters.remove("error");}
		String result=(new StringResourceModel("objekt.undefined",this,null)).getObject();
		IModel personModel=null;
	    logger.debug("scout person insert 0");
		 if(responsepage.getSimpleName().equals("AngebotTree")&&found==false){
		    	PageParameters pars1=new PageParameters()
		    			.add("objid","not null")
		    	.add("eigtid","not null")
			    	.add("angnr","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject();		
						 subject=(new StringResourceModel("eigtold",this,null)).getObject();
						 found=true;
			    	result=pageparameters.get("angnr").toString()+"/"+pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString();
						personModel=new EntityModel<Personen>(Personen.class,new Long(pageparameters.get("eigtid").toString()));	
			    }
				pars1=new PageParameters()
		    			.add("objid","not null")
		    			.add("eigtid","null")
		    	.add("eigttypid","not null")
		    	.add("strid","not null")
			    	.add("angnr","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	
			    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject();		
					 subject=(new StringResourceModel("eigtnew",this,null)).getObject();
		
		    	result=pageparameters.get("angnr").toString()+"/"+pageparameters.get("objid").toString()+"/null";
		    	Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
				Personen person1 =new Personen();
				 person1.setId(null);
		        person1.setStrasse(strasse);
	            person1.setEigtHausnummer(strasse.getStrname());
	            personModel=new EntityModel<Personen>(person1);
	            pageparameters.remove("strid");
			    	
	            found=true;	
			    
			    }
		
		
			
		 }
		    logger.debug("scout person insert 1");
		 if(responsepage.getSimpleName().equals("ObjektTree")&&found==false){
		    	PageParameters pars1=new PageParameters()
		    			.add("objid","not null")
		    	.add("eigtid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject();		
						 subject=(new StringResourceModel("eigtold",this,null)).getObject();
						 found=true;
			    	result=pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString();
						personModel=new EntityModel<Personen>(Personen.class,new Long(pageparameters.get("eigtid").toString()));	
			    }
				pars1=new PageParameters()
		    			.add("objid","not null")
		    			.add("eigtid","null")
		    	.add("eigttypid","not null")
		    	.add("strid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject();		
					 subject=(new StringResourceModel("eigtnew",this,null)).getObject();
		
		    	result=pageparameters.get("objid").toString()+"/null";
			
		    	Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
				Personen person1 =new Personen();
				 person1.setId(null);
		        person1.setStrasse(strasse);
	            person1.setEigtHausnummer(strasse.getStrname());
	            personModel=new EntityModel<Personen>(person1);
	            pageparameters.remove("strid");	
	            found=true;
			    
			    }
		
		
			
		 }
		    logger.debug("scout person insert 3");
		 
		 if(responsepage.getSimpleName().equals("ScoutTree")&&found==false){
		    	PageParameters pars1=new PageParameters()
		    			.add("scoutid","not null")
		    			.add("eigtid","not null");
		   
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)&&found==false) {
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject();		
					 subject=(new StringResourceModel("eigtnew",this,null)).getObject();
		
		    	result=pageparameters.get("eigtid").toString()+"/null";
		    	personModel=new EntityModel<Personen>(Personen.class,new Long(pageparameters.get("eigtid").toString()));
		    	 withNext=false;
				 withLocation=false;
	            found=true;
			    
			    }
			    pars1=new PageParameters()
			    		.add("strid","not null")
		    			.add("scoutid","not null")
		    			.add("eigtid","null");
			    logger.debug("scout person insert 5");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)&&found==false) {
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject();		
					 subject=(new StringResourceModel("eigtnew",this,null)).getObject();
		
		    	result="/null";
		    	Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
				Personen person1 =new Personen();
				 person1.setId(null);
		        person1.setStrasse(strasse);
		      //  strasse.addPerson(person1);
	            person1.setEigtHausnummer(strasse.getStrname());
	            personModel=new EntityModel<Personen>(person1);
	            pageparameters.remove("strid");	
	            found=true;
	            withNext=false;
				 withLocation=false;
	          logger.debug("scout person insert 6");
			    
			    }
		 }
		 
		 
		 
		 if(responsepage.getSimpleName().equals("PersonTree")&&found==false){
		    	PageParameters pars1=new PageParameters()
		    	.add("eigtid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	specialusage= (new StringResourceModel("eigtid",this,null)).getObject();		
						 subject=(new StringResourceModel("eigtold",this,null)).getObject();
			found=true;
			    	result=pageparameters.get("eigtid").toString();
						personModel=new EntityModel<Personen>(Personen.class,new Long(pageparameters.get("eigtid").toString()));	
			    }
			  pars1.add("strid", "not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
			    	Personen person=personenManager.get(new Long(pageparameters.get("eigtid").toString()));
			    	person.getStrasse().getPersonen().remove(person); 
			    	person.setStrasse(strasse);
			    	strasse.addPerson(person);
			    	personenManager.save(person);
		            pageparameters.remove("strid");
			    	
			    	
			    	specialusage= (new StringResourceModel("eigtid",this,null)).getObject();		
						 subject=(new StringResourceModel("eigtold",this,null)).getObject();
			found=true;
			    	result=pageparameters.get("eigtid").toString();
						personModel=new EntityModel<Personen>(Personen.class,new Long(pageparameters.get("eigtid").toString()));	
			    }
				
				pars1=new PageParameters()
		    			.add("objid","not null")
		    			.add("eigtid","null")
		    	.add("eigttypid","not null")
		    	.add("strid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject();		
					 subject=(new StringResourceModel("eigtnew",this,null)).getObject();
		
		    	result=pageparameters.get("objid").toString()+"/null";
			
		    	Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
				Personen person1 =new Personen();
				 person1.setId(null);
		        person1.setStrasse(strasse);
	            person1.setEigtHausnummer(strasse.getStrname());
	            personModel=new EntityModel<Personen>(person1);
	            pageparameters.remove("strid");	
	            found=true;	
			    
			    }
		
		pars1=new PageParameters()
		    			.add("eigtid","null")
		    	.add("strid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)&&found==false) {
			    	specialusage= (new StringResourceModel("eigtid",this,null)).getObject();		
					 subject=(new StringResourceModel("eigtnew",this,null)).getObject();
		
		    	result=pageparameters.get("objid").toString()+"/null";
			
		    	Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
				Personen person1 =new Personen();
				 person1.setId(null);
		        person1.setStrasse(strasse);
	            person1.setEigtHausnummer(strasse.getStrname());
	            System.err.println("NNNNNNNNNNNNNNNNNNNNN"+person1);
	            personModel=new EntityModel<Personen>(person1);
	            pageparameters.remove("strid");	
	            found=true;	
			    
			    }
			
		 }
		 logger.debug("after decision tree "+found);
		 if(found==false) System.exit(5);
			
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
		 logger.debug("call PersonInput "+personModel);
		PersonInput form=new PersonInput("form",withNext,withLocation,personModel);
		 form.add(new Label("search", subject));
		add(new Label("result", result));
		add(form);
		logger.debug("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"+pageparameters);
		
	}
	
private  class PersonInput extends  AbstractPersonInput{
		
		PersonInput(String id,boolean withNext,boolean withLocation,IModel<Personen> personModel){
			super(id,withNext,withLocation,personModel);
			
		}
		@Override
		public void onLocation(){
			logger.debug("locationButton PageParameters : "+pageparameters+"responsepage : "+responsepage.getSimpleName());
			activate(new IBreadCrumbPanelFactory()
			{
				@Override
				public BreadCrumbPanel create(String componentId,
					IBreadCrumbModel breadCrumbModel)
				{ 
			 try{
				
					   Personen person=(Personen)PersonInput.this.getModelObject();
						 
							personenManager.save(person); 	
							
							
							 return new StrassenSuchePanel(componentId,responsepage,pageparameters, breadCrumbModel);
							 
			 }
		
			 catch(Exception ex){
			 pageparameters.add("error", ex);

			 return new PersonPanel(componentId,responsepage,pageparameters, breadCrumbModel);
			
			 }
				}
			});
					 } 
		
@Override
public void onBack(){
	try{
		
    	if((accesskey!=null)&&(accesskey.length()>0)){
    	synchronizecontacts.setContactsService(accesskey); 
    	
    		        info(new NotificationMessage(Model.of("Google Services ContactsService set "), Model.of("info message title")));
    		      }
				 savePerson(pageparameters);
				 logger.debug("now Parameter"+pageparameters);
				 if(responsepage.getSimpleName().equals("PersonTree")){
				 
				 PageParameters pars1=new PageParameters()
								    	.add("telefon","not null")
								    	.add("eigtid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
									//		pageparameters.remove("telefon");
													}
				 } 
				 
				 logger.debug("now Parameter 1");
				 
			 if(responsepage.getSimpleName().equals("AngebotTree")){
				 
				 PageParameters pars1=new PageParameters()
								    	.add("angnr","not null")
								    	.add("telefon","not null")
								    	.add("eigtid","not null")
								    	.add("eigttypid","not null")
								    	.add("objid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
											pageparameters.remove("telefon");
											 Personen person1=personenManager.get(new Long(pageparameters.get("eigtid").toString())); 
									    	 Objekte objekt=objektManager.get(new Long(pageparameters.get("objid").toString()));
									    	 Eigentuemertyp eigentuemertyp1=eigentuemertypManager.get(new Long(pageparameters.get("eigttypid").toString())); 
									    	Objperszuord objperszuord=new Objperszuord();
									    	objperszuord.setObjekt(objekt);
									    	objperszuord.setPersonen(person1);
									    	objperszuord.setEigentuemertyp(eigentuemertyp1);
									    	person1.addObjperszuord(objperszuord);
									    	objekt.addObjperszuord(objperszuord);   	
									    	objektManager.save(objekt);	
										}
				 }
			 logger.debug("now Parameter 2");
if(responsepage.getSimpleName().equals("ObjektTree")){
				 
				 PageParameters pars1=new PageParameters()
								    	.add("telefon","not null")
								    	.add("eigtid","not null")
								    	.add("eigttypid","not null")
								    	.add("objid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
											pageparameters.remove("telefon");
											 Personen person1=personenManager.get(new Long(pageparameters.get("eigtid").toString())); 
									    	 Objekte objekt=objektManager.get(new Long(pageparameters.get("objid").toString()));
									    	 Eigentuemertyp eigentuemertyp1=eigentuemertypManager.get(new Long(pageparameters.get("eigttypid").toString())); 
									    	Objperszuord objperszuord=new Objperszuord();
									    	objperszuord.setObjekt(objekt);
									    	objperszuord.setPersonen(person1);
									    	objperszuord.setEigentuemertyp(eigentuemertyp1);
									    	person1.addObjperszuord(objperszuord);
									    	objekt.addObjperszuord(objperszuord);   	
									    	objektManager.save(objekt);	
										}
				 }

logger.debug("now Parameter 3");
if(responsepage.getSimpleName().equals("ScoutTree")){
 logger.debug("now Parameter 4");
 PageParameters pars1=new PageParameters()
				    	.add("eigtid","not null")
				    	.add("scoutid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
							 activate(new IBreadCrumbPanelFactory()
								{
									@Override
									public BreadCrumbPanel create(String componentId,
										IBreadCrumbModel breadCrumbModel)
									{ 
										logger.debug("scout back deal");
										pageparameters.remove("who");
										Scout scout = scoutManager.get(new Long(pageparameters.get("scoutid").toString()));
										if(scout.getPerson()==null){
										Personen person= personenManager.get(new Long(pageparameters.get("eigtid").toString()));
										person.addScout(scout);
										scout.setPerson(person);
										personenManager.save(person);}
														pageparameters.remove("eigtid");
														 return new ScoutPanel(componentId,responsepage,pageparameters, breadCrumbModel);	
									}
								});
							 return;
						}
						
						 pars1=new PageParameters()
								 .add("telefon","not null")
								 .add("who","not null")
							    	.add("eigtid","null")
							    	.add("scoutid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
										 activate(new IBreadCrumbPanelFactory()
											{
												@Override
												public BreadCrumbPanel create(String componentId,
													IBreadCrumbModel breadCrumbModel)
												{ 
													pageparameters.remove("telefon");
													pageparameters.remove("who");
													Scout scout = scoutManager.get(new Long(pageparameters.get("scoutid").toString()));
													Personen person= personenManager.get(new Long(pageparameters.get("eigtid").toString()));
													person.addScout(scout);
													scout.setPerson(person);
													personenManager.save(person);
																	pageparameters.remove("eigtid");
																	 return new ScoutPanel(componentId,responsepage,pageparameters, breadCrumbModel);	
												}
											});
										 return;
									}
						
						
 }
			    } 
 catch(Exception ex){ 
 pageparameters.add("error", ex);
 this.setResponsePage(responsepage, pageparameters);
 }			
			 pageparameters.add("error", "no next defined");
			 this.setResponsePage(responsepage, pageparameters);	

			 } 
@Override
public void onNext(){
	 logger.debug("next Button PageParameters "+pageparameters);
	 try{savePerson(pageparameters);
		
				    } 
	 	 catch(Exception ex){
	 pageparameters.add("error", ex);
	 logger.error("next Button error save "+ex+" PageParameters "+pageparameters);
	 this.setResponsePage(PersonTree.class, pageparameters);
	 return;
	 }
	
	 activate(new IBreadCrumbPanelFactory()
		{
			@Override
			public BreadCrumbPanel create(String componentId,
				IBreadCrumbModel breadCrumbModel)
			{ 
				
				 if(responsepage.getSimpleName().equals("AngebotTree")){
				 PageParameters pars1=new PageParameters()
					    	.add("angnr","not null")
					    	.add("telefon","not null")
					    	.add("eigtid","not null")
					    	.add("objid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
								pageparameters.remove("telefon");
								pageparameters.add("kundennr", "null");
								 return new KundePanel(componentId,responsepage,pageparameters, breadCrumbModel);	
							}
							 pars1=new PageParameters()
								    	.add("angnr","not null")
								    	.add("telefon","not null")
								    	.add("eigtid","not null")
								    	.add("eigttypid","not null")
								    	.add("objid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
											pageparameters.remove("telefon");
											 Personen person1=personenManager.get(new Long(pageparameters.get("eigtid").toString())); 
									    	 Objekte objekt=objektManager.get(new Long(pageparameters.get("objid").toString()));
									    	 Eigentuemertyp eigentuemertyp1=eigentuemertypManager.get(new Long(pageparameters.get("eigttypid").toString())); 
									    	Objperszuord objperszuord=new Objperszuord();
									    	objperszuord.setObjekt(objekt);
									    	objperszuord.setPersonen(person1);
									    	objperszuord.setEigentuemertyp(eigentuemertyp1);
									    	person1.addObjperszuord(objperszuord);
									    	objekt.addObjperszuord(objperszuord);   	
									    	objektManager.save(objekt);
									    	pageparameters.remove("eigttypid");	
									    	pageparameters.add("kundennr", "null");
											 return new KundePanel(componentId,responsepage,pageparameters, breadCrumbModel);	
										}
										 pars1=new PageParameters()
											    	.add("angnr","not null")
											    	.add("telefon","not null")
											    	.add("eigtid","null")
											    	.add("eigttypid","not null")
											    	.add("objid","not null");
													if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
														pageparameters.remove("telefon");
														 Personen person1=personenManager.get(new Long(pageparameters.get("eigtid").toString())); 
												    	 Objekte objekt=objektManager.get(new Long(pageparameters.get("objid").toString()));
												    	 Eigentuemertyp eigentuemertyp1=eigentuemertypManager.get(new Long(pageparameters.get("eigttypid").toString())); 
												    	Objperszuord objperszuord=new Objperszuord();
												    	objperszuord.setObjekt(objekt);
												    	objperszuord.setPersonen(person1);
												    	objperszuord.setEigentuemertyp(eigentuemertyp1);
												    	person1.addObjperszuord(objperszuord);
												    	objekt.addObjperszuord(objperszuord);   	
												    	objektManager.save(objekt);
												    	pageparameters.remove("eigttypid");	
												    	pageparameters.add("kundennr", "null");
														 return new KundePanel(componentId,responsepage,pageparameters, breadCrumbModel);	
													}
				 
				 }
				 if(responsepage.getSimpleName().equals("ObjektTree")){
					 PageParameters pars1=new PageParameters()
						    	.add("telefon","not null")
						    	.add("eigtid","not null")
						    	.add("objid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
									pageparameters.remove("telefon");
									pageparameters.add("kundennr", "null");
									 return new KundePanel(componentId,responsepage,pageparameters, breadCrumbModel);	
								}
								 pars1=new PageParameters()
									    	.add("telefon","not null")
									    	.add("eigtid","not null")
									    	.add("eigttypid","not null")
									    	.add("objid","not null");
											if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
												pageparameters.remove("telefon");
												 Personen person1=personenManager.get(new Long(pageparameters.get("eigtid").toString())); 
										    	 Objekte objekt=objektManager.get(new Long(pageparameters.get("objid").toString()));
										    	 Eigentuemertyp eigentuemertyp1=eigentuemertypManager.get(new Long(pageparameters.get("eigttypid").toString())); 
										    	Objperszuord objperszuord=new Objperszuord();
										    	objperszuord.setObjekt(objekt);
										    	objperszuord.setPersonen(person1);
										    	objperszuord.setEigentuemertyp(eigentuemertyp1);
										    	person1.addObjperszuord(objperszuord);
										    	objekt.addObjperszuord(objperszuord);   	
										    	objektManager.save(objekt);
										    	pageparameters.remove("eigttypid");	
										    	pageparameters.add("kundennr", "null");
												 return new KundePanel(componentId,responsepage,pageparameters, breadCrumbModel);	
											}
					 }
				 if(responsepage.getSimpleName().equals("PersonTree")){
					 PageParameters pars1=new PageParameters()
						    	.add("telefon","not null")
						    	.add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
									pageparameters.remove("telefon");
									pageparameters.add("kundennr", "null");
									 return new KundePanel(componentId,responsepage,pageparameters, breadCrumbModel);	
								}
					 }
				 
				 if(responsepage.getSimpleName().equals("ScoutTree")){
					 PageParameters pars1=new PageParameters()
							 .add("telefon","not null")
						    	.add("scouid","not null")
						    	.add("eigtid","null");
								if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
									pageparameters.remove("telefon");
									pageparameters.add("kundennr", "null");
									 return new KundePanel(componentId,responsepage,pageparameters, breadCrumbModel);	
								}
					 }	 
				 
				 
				 
				 
		return null;}
		});
		
	
	}
			 
@Override
public void onCancel(){
	setResponsePage(responsepage, pageparameters);
			 }



/*private BootstrapSelectConfig of(boolean i18n) {
    BootstrapSelectConfig config = new BootstrapSelectConfig();
    if (i18n) {
       
        config
                .withNoneSelectedText("My nothing selected")
                .withNoResultText("My no results found")
                .withCountSelectedText("My selected {0} from {1}")
                .withMaxOptionsText("My limit ({n} {var} max)",
                        "My group limit({n} {var} max)",
                        "items", "item");
    }
    return config;
}*/

	
	@Override
	public IModel<String> NextModel()
	{
		return Model.of((new StringResourceModel("goon",PersonPanel.this,null)).getObject());
	}
	@Override
	public IModel<String> LocationModel()
	{
		return Model.of((new StringResourceModel("gotree",PersonPanel.this,null)).getObject());
	}
	@Override
	public IModel<String> BackModel()
	{
		return Model.of((new StringResourceModel("goback",PersonPanel.this,null)).getObject());
	}
	
	@Override
	public IModel<String> CancelModel()
	{
		return Model.of((new StringResourceModel("cancel",PersonPanel.this,null)).getObject());
	}
	
}
	

	/**
	 * @see org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant#getTitle()
	 */
	@Override
	public IModel<String> getTitle()
	{
		return Model.of(specialusage);
	}
	
}
