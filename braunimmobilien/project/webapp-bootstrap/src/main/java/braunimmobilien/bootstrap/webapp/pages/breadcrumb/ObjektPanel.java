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
import braunimmobilien.service.*;
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
public class ObjektPanel extends BreadCrumbPanel
{
	@SpringBean
	EigentuemertypManager eigentuemertypManager;
@SpringBean
	PersonManager personManager;
	@SpringBean
	StrassenManager strassenManager;
	@SpringBean
ScoutManager scoutManager;
@SpringBean
ObjektManager objektManager;
	@SpringBean
	AngebotManager angebotManager;
	@SpringBean
	AngobjzuordManager angobjzuordManager;
	@SpringBean
	ObjektsuchManager objektsuchManager;
@SpringBean
	ObjektartManager objektartManager;
@SpringBean
LandManager landManager;
private Class responsepage;
private PageParameters pageparameters;
private boolean withNext=true;	
private boolean withLocation=true;
static Logger logger = LoggerFactory.getLogger(ObjektPanel.class);
String subject = "no subject";
String result = "no result";
private String specialusage="";
		
	
		
		
	
	
	
	
	
	
		
	/**
	 * Construct.
	 * 
	 * @param id
	 * @param breadCrumbModel
	 * @param result
	 *            The 'result' to display as a label
	 */
	
	
	
	public ObjektPanel(final String id,final Class extresponsepage,final PageParameters extpageparameters, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
		pageparameters=extpageparameters;
		responsepage=extresponsepage;
		 if(pageparameters.getPosition("error")>0) {error(pageparameters.get("error").toString());
		 pageparameters.remove("error");}
	 if(pageparameters.getPosition("where")>0) {error(pageparameters.get("where").toString());
		 pageparameters.remove("where");}


String result=(new StringResourceModel("objekt.undefined",this,null)).getObject();
		IModel objModel=null;
		logger.debug("ObjektPanel init "+pageparameters);
		
		
		  if(responsepage.getSimpleName().equals("AngebotTree")){
		    	PageParameters pars1=new PageParameters()
		    	.add("objid","not null")
		    	.add("angnr","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject();
				 subject=(new StringResourceModel("objektold",this,null)).getObject();
				 result = pageparameters.get("angnr").toString()+"/"+pageparameters.get("objid").toString();
		    	objModel=new EntityModel<Objekte>(Objekte.class,new Long(pageparameters.get("objid").toString()));	
		//    	result=pageparameters.get("objid").toString();
		    }
		    
		    	pars1=new PageParameters()
		    			.add("objid","null")
			    	.add("strid","not null")
			    	.add("angnr","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject();
					 subject=(new StringResourceModel("objektnewinsert",this,null)).getObject();
					 result = pageparameters.get("angnr").toString()+"/null";
			    	Objekte objekt = new Objekte();
			    	objekt.setId(null);
			    	objekt.setStrasse(strassenManager.get(new Long(pageparameters.get("strid").toString())));
			    	objModel=new EntityModel<Objekte>(objekt);	
			    	pageparameters.remove("strid");
			    }
		    
		    }
		  if(responsepage.getSimpleName().equals("ObjektTree")){
		    	PageParameters pars1=new PageParameters()
		    	.add("objid","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		    	specialusage= (new StringResourceModel("objektid",this,null)).getObject();
				 subject=(new StringResourceModel("objektold",this,null)).getObject();
				 result = pageparameters.get("objid").toString();
		    	objModel=new EntityModel<Objekte>(Objekte.class,new Long(pageparameters.get("objid").toString()));	
		    }
		    
		    	pars1=new PageParameters()
		    			.add("objid","null")
			    	.add("strid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject();
					 subject=(new StringResourceModel("objektnewinsert",this,null)).getObject();
					 result="null";
			    	Objekte objekt = new Objekte();
			    	objekt.setId(null);
			    	objekt.setStrasse(strassenManager.get(new Long(pageparameters.get("strid").toString())));
			    	objModel=new EntityModel<Objekte>(objekt);	
			    	pageparameters.remove("strid");
			    }
		    
		    }
		  
		  if(responsepage.getSimpleName().equals("ScoutTree")){
		    	PageParameters pars1=new PageParameters()
		    			.add("objid","not null")
		    	.add("scoutid","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
		    	specialusage= (new StringResourceModel("scoutid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject();
				 subject=(new StringResourceModel("objektold",this,null)).getObject();
				 withNext=false;
				 withLocation=false;
				 result = pageparameters.get("objid").toString();
		    	objModel=new EntityModel<Objekte>(Objekte.class,new Long(pageparameters.get("objid").toString()));	
		    }
		    
		    	
				pars1=new PageParameters()
						.add("strid","not null")
		    			.add("objid","null")
				    	.add("scoutid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
			    	
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject();
					 subject=(new StringResourceModel("objektold",this,null)).getObject();
					result="null";
					 Objekte objekt = new Objekte();
				    	objekt.setId(null);
				    	 withNext=false;
						 withLocation=false;
						 Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
				    	objekt.setStrasse(strasse);
				    	//strasse.addObjekt(objekt);
				    	objekt.setObjhausnummer(objekt.getStrasse().getStrname());
				    	logger.debug("ObjektPanel scout "+objekt.getStrasse());
				    	objModel=new EntityModel<Objekte>(objekt);	
				    	pageparameters.remove("strid");		    }
		    
		    }
	
		  if(responsepage.getSimpleName().equals("PersonTree")){
		    	PageParameters pars1=new PageParameters()
		    			.add("eigtid","not null")		
		    	.add("objid","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		    	specialusage= (new StringResourceModel("eigtid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject();
				 subject=(new StringResourceModel("personold",this,null)).getObject();
				 result = pageparameters.get("eigtid").toString()+"/"+pageparameters.get("objid").toString();
		    	objModel=new EntityModel<Objekte>(Objekte.class,new Long(pageparameters.get("objid").toString()));	
		    }
		    
		    	pars1=new PageParameters()
		    	.add("objid","null")
		    			.add("eigtid","not null")
		    			.add("eigttypid","not null")
			    	.add("strid","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject();
					 subject=(new StringResourceModel("objektnewinsert",this,null)).getObject();
					 result="null";
			    	Objekte objekt = new Objekte();
			    	objekt.setId(null);
			    	Strassen strasse=strassenManager.get(new Long(pageparameters.get("strid").toString()));
			    	objekt.setObjhausnummer(strasse.getStrname());
			    	objekt.setStrasse(strasse);
			    	objModel=new EntityModel<Objekte>(objekt);	
			    	pageparameters.remove("strid");
			    }
		    
		    }
		 
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
		
		ObjektInput form=new ObjektInput("form",withNext,withLocation,objModel);
	
		add(new Label("result", result));
		 form.add(new Label("search", subject));
		 logger.debug("ObjektPanel before form ");
		add(form);
		logger.debug("ObjektPanel after form ");
	}
	
private  class ObjektInput extends  AbstractObjektInput{
		
		ObjektInput(String id,boolean withNext,boolean withLocation,IModel<Objekte> objektModel){
			super(id,withNext,withLocation,objektModel);
			
		}
		@Override
		public void onLocation(){
			activate(new IBreadCrumbPanelFactory()
			{
				@Override
				public BreadCrumbPanel create(String componentId,
					IBreadCrumbModel breadCrumbModel)
				{ 
			 try{
				 
				 Objekte objekt=(Objekte)ObjektInput.this.getModelObject();
				 if(objekt.getId()!=null){
				  objektManager.save(objekt);
				 if(responsepage.getSimpleName().equals("AngebotTree")){
				    	PageParameters pars1=new PageParameters()
				    	.add("objid","not null")
				    	.add("angnr","not null");
				    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				
					
							 return new StrassenSuchePanel(componentId,responsepage,pageparameters, breadCrumbModel);
				    }
				    }
				 }
			 }
		
			 catch(Exception ex){
				 ex.printStackTrace();
		 System.err.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"+ex);
			System.exit(5);
			 pageparameters.add("error", ex);
			 }
			

			 return null;
				}
			});	 } 
		
@Override
public void onBack(){
	 try{
			
		 Objekte objekt1=(Objekte)ObjektInput.this.getModelObject();
		 if(objekt1.getId()==null){ objekt1.getStrasse().addObjekt(objekt1);}
final	Objekte	  objekt=objektManager.save(objekt1);
		 if(responsepage.getSimpleName().equals("AngebotTree")){
		    	PageParameters pars1=new PageParameters()
		    	.add("objid","not null")
		    	.add("angnr","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		  Angebot angebot=angebotManager.get(pageparameters.get("angnr").toString());
	    	Angobjzuord angobjzuord=new Angobjzuord();
	    	angobjzuord.setObjekte(objekt);
	    	angobjzuord.setAngebot(angebot);
	    	angebot.addAngobjzuord(angobjzuord);
	    	objekt.addAngobjzuord(angobjzuord);
	    	angebotManager.save(angebot);
		 }
		    pars1=new PageParameters()
			    	.add("objid","null")
			    	.add("angnr","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			  Angebot angebot=angebotManager.get(pageparameters.get("angnr").toString());
		    	Angobjzuord angobjzuord=new Angobjzuord();
		    	angobjzuord.setObjekte(objekt);
		    	angobjzuord.setAngebot(angebot);
		    	angebot.addAngobjzuord(angobjzuord);
		    	objekt.addAngobjzuord(angobjzuord);
		    	angebotManager.save(angebot);
		    	pageparameters.add("objid", objekt.getId().toString()); 
			 }  
		 }
		 if(responsepage.getSimpleName().equals("ObjektTree")){
		    	PageParameters pars1=new PageParameters()
		    	.add("objid","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		 }
		    pars1=new PageParameters()
			    	.add("objid","null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	pageparameters.remove("objid");
		    	pageparameters.add("objid", objekt.getId().toString()); 
			 }  
		 }	
		
		 if(responsepage.getSimpleName().equals("PersonTree")){
		    	PageParameters pars1=new PageParameters()
			    	.add("objid","null")
			    	.add("eigtid","not null")
			    	.add("eigttypid","not null");
			    	
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    Personen person= personManager.get(new Long(pageparameters.get("eigtid").toString()));
			    Eigentuemertyp eigentuemertyp=eigentuemertypManager.get(new Long(pageparameters.get("eigttypid").toString()));
			    Objperszuord objperszuord=new Objperszuord();
			    objperszuord.setPersonen(person);
			    objperszuord.setObjekt(objekt);
			    objperszuord.setEigentuemertyp(eigentuemertyp);
			    person.addObjperszuord(objperszuord);
			    objekt.addObjperszuord(objperszuord);
			    	pageparameters.remove("objid");
			    	pageparameters.remove("eigttypid");
		    	objektManager.save(objekt);
		    	 setResponsePage(responsepage, pageparameters);
			 }  
		 }	
		
		
		 if(responsepage.getSimpleName().equals("ScoutTree")){
		    	PageParameters pars1=new PageParameters()

		    			.add("objid","not null")
		    	.add("scoutid","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
		    	activate(new IBreadCrumbPanelFactory()
				{
					@Override
					public BreadCrumbPanel create(String componentId,
						IBreadCrumbModel breadCrumbModel)
					{ 
					Scout scout =scoutManager.get(new Long(pageparameters.get("scoutid").toString()));
					scout.setObjekt(objekt);
					objekt.addScout(scout);
					objektManager.save(objekt);
					pageparameters.remove("where");
					 pageparameters.remove("objid"); 
						    	 return new ScoutPanel(componentId,responsepage,pageparameters, breadCrumbModel);
						    }
						    });	
		    	return;
			 } 
			 
			 	pars1=new PageParameters()

		    			.add("objid","null")
		    	.add("scoutid","not null");
			  
			   if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
		    	activate(new IBreadCrumbPanelFactory()
				{
					@Override
					public BreadCrumbPanel create(String componentId,
						IBreadCrumbModel breadCrumbModel)
					{
					Scout scout=scoutManager.get(new Long(pageparameters.get("scoutid").toString()));
				scout.setObjekt(objekt);
				objekt.addScout(scout);
				scout=scoutManager.save(scout);
					 pageparameters.remove("where");
					 pageparameters.remove("objid"); 
						    	 return new ScoutPanel(componentId,responsepage,pageparameters, breadCrumbModel);
						    }
						    });	
		    	return;
			 }  
		 }	
		 
			 } 
	 

	 catch(Exception ex){
	 pageparameters.add("error", ex);
	 }
	
	 this.setResponsePage(responsepage, pageparameters);
		

			 } 
@Override
public void onNext(){

	 Objekte objekt=(Objekte)ObjektInput.this.getModelObject();
	 if(objekt.getId()==null){ objekt.getStrasse().addObjekt(objekt);}
	  objektManager.save(objekt);
	 if(responsepage.getSimpleName().equals("AngebotTree")){
	    	PageParameters pars1=new PageParameters()
	    	.add("objid","not null")
	    	.add("angnr","not null");
	    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	  Angebot angebot=angebotManager.get(pageparameters.get("angnr").toString());
   	Angobjzuord angobjzuord=new Angobjzuord();
   	angobjzuord.setObjekte(objekt);
   	angobjzuord.setAngebot(angebot);
   	angebot.addAngobjzuord(angobjzuord);
   	objekt.addAngobjzuord(angobjzuord);
   	angebotManager.save(angebot);
	 }
	    pars1=new PageParameters()
		    	.add("objid","null")
		    	.add("angnr","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		  Angebot angebot=angebotManager.get(pageparameters.get("angnr").toString());
	    	Angobjzuord angobjzuord=new Angobjzuord();
	    	angobjzuord.setObjekte(objekt);
	    	angobjzuord.setAngebot(angebot);
	    	angebot.addAngobjzuord(angobjzuord);
	    	objekt.addAngobjzuord(angobjzuord);
	    	angebotManager.save(angebot);
	    	pageparameters.remove("objid");
	    	pageparameters.add("objid", objekt.getId().toString()); 
		 } 	    	    
	 }
	 if(responsepage.getSimpleName().equals("ObjektTree")){
	    	PageParameters pars1=new PageParameters()
	    	.add("objid","not null");
	    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	 }
	    pars1=new PageParameters()
		    	.add("objid","null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	    	pageparameters.remove("objid");
	    	pageparameters.add("objid", objekt.getId().toString()); 
		 } 	    	    
	 }
	activate(new IBreadCrumbPanelFactory()
{
	@Override
	public BreadCrumbPanel create(String componentId,
		IBreadCrumbModel breadCrumbModel)
	{ 
		 if(responsepage.getSimpleName().equals("AngebotTree")){
		    	PageParameters pars1=new PageParameters()
		    	.add("objid","not null")
		    	.add("angnr","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		    	pageparameters.add("eigtid", "null");
		    	 return new StrassenSuchePanel(componentId,responsepage,pageparameters, breadCrumbModel);
		    }
		    }	
		 if(responsepage.getSimpleName().equals("ObjektTree")){
		    	PageParameters pars1=new PageParameters()
		    	.add("objid","not null");
		    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		    	pageparameters.add("eigtid", "null");
		    	 return new StrassenSuchePanel(componentId,responsepage,pageparameters, breadCrumbModel);
		    }
		    }	
return null;
	}
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
		return Model.of((new StringResourceModel("goon",ObjektPanel.this,null)).getObject());
	}
	@Override
	public IModel<String> LocationModel()
	{
		return Model.of((new StringResourceModel("gotree",ObjektPanel.this,null)).getObject());
	}
	@Override
	public IModel<String> BackModel()
	{
		return Model.of((new StringResourceModel("goback",ObjektPanel.this,null)).getObject());
	}
	
	@Override
	public IModel<String> CancelModel()
	{
		return Model.of((new StringResourceModel("cancel",ObjektPanel.this,null)).getObject());
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
