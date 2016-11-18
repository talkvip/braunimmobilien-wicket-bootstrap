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
import braunimmobilien.model.Eigentuemertyp;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant;
import braunimmobilien.service.AngebotExistsException;
import braunimmobilien.service.EigentuemertypManager;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import braunimmobilien.model.BaseObject;
import braunimmobilien.model.Kundenart;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Status;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Eigtstatus;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.KundenartManager;
import braunimmobilien.service.StatusManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
import braunimmobilien.bootstrap.webapp.pages.BraunHomePage;
import braunimmobilien.bootstrap.webapp.pages.ConfigurationNotDefinedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class KundePanel extends BreadCrumbPanel
{
static Logger logger = LoggerFactory.getLogger(KundePanel.class);

@SpringBean
	KundeManager kundeManager;
@SpringBean
StatusManager statusManager;
	@SpringBean
	PersonManager personManager;
	@SpringBean
	KundenartManager kundenartManager;
	@SpringBean
	private  EigentuemertypManager eigentuemertypManager;
	@SpringBean
	EigtstatusManager eigtstatusManager;
	String subject = "no subject";
	String result = "no result";
	private String specialusage="";
	private final class KundeInput extends Form<Kunde>
	{
		IModel<List<? extends Status>> makeChoicesStatus = new AbstractReadOnlyModel<List<? extends Status>>()
        {
            @Override
            public List<Status> getObject()
            { List<Status> statuslist=new  ArrayList<Status>(); 
            	
            	Iterator statusiterator=statusManager.getStatuses().iterator();
            while(statusiterator.hasNext()){
            	Status status=(Status)statusiterator.next();
            	statuslist.add(status);
            }
               
                return statuslist;
            }

        };
    
		
		 IModel<List<? extends Kundenart>> makeChoicesKundenart = new AbstractReadOnlyModel<List<? extends Kundenart>>()
			        {
			            @Override
			            public List<Kundenart> getObject()
			            { List<Kundenart> kundenartlist=new  ArrayList<Kundenart>(); 
			            	
			            	Iterator kundenartiterator=kundenartManager.getKundenartes().iterator();
			            while(kundenartiterator.hasNext()){
			            	Kundenart kundenart=(Kundenart)kundenartiterator.next();
			            	kundenartlist.add(kundenart);
			            }
			               
			                return kundenartlist;
			            }

			        };
			
			
			        final DropDownChoice<Kundenart> kundenart = new DropDownChoice<Kundenart>("kundenart", makeChoicesKundenart,new ChoiceRenderer<Kundenart>("kundenart","id"));
			        final DropDownChoice<Status> status = new DropDownChoice<Status>("status", makeChoicesStatus,new ChoiceRenderer<Status>("statustext","id"));
			       
		
		IModel<List<? extends Eigtstatus>>makeChoicesEigtstatus= new AbstractReadOnlyModel<List<? extends Eigtstatus>>()
    	        {
    	            @Override
    	            public List<Eigtstatus> getObject()
    	            { List<Eigtstatus> eigtstatuslist=new  ArrayList<Eigtstatus>(); 
    	            	if(eigtstatusManager!=null){
    	            	Iterator eigtstatusiterator=eigtstatusManager.getEigtstatuses().iterator();
    	            while(eigtstatusiterator.hasNext()){
    	            	Eigtstatus eigtstatus=(Eigtstatus) eigtstatusiterator.next();
    	            	eigtstatuslist.add(eigtstatus);
    	            }
    	            	}
    	                return eigtstatuslist;
    	            }

    	        }; 
    	    
		/** test input string. */
		

		/**
		 * Construct.
		 * 
		 * @param id
		 *            The component id
		 */
		public KundeInput(String id,final Class responsepage,final PageParameters pageparameters,final IModel<Kunde> kunde)
		{
			
			super(id, new CompoundPropertyModel(kunde));
Label kundennr=new Label("id");
			add(status);
			add(kundennr);
			   add(kundenart);
			   final  DateTextField  datum= new DateTextField("datum",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
				 datum.setRequired(true);
		add(datum);
		 add(new Label("person.id"));
		 add(new Label("person.strasse.id"));		
		 add(new TextField<String>("person.eigtHausnummer").setRequired(true));
		 add(new TextArea<String>("person.eigtInfo"));
		 add(new TextField<String>("person.eigtHomepage"));
		 add(new TextField<String>("person.eigtTelefone"));
		 add(new TextField<String>("person.eigtName").setRequired(true));
		 add(new TextField<String>("person.eigtAnschrift").setRequired(true));
		 add(new TextField<String>("person.eigtFirma"));
		 add(new TextField<String>("person.eigtTel"));
		 add(new TextField<String>("person.eigtBriefanrede").setRequired(true));
		 add(new TextField<String>("person.eigtEmail"));
		 add(new CheckBox("person.eigtAktuell").setRequired(true));
		 add(new DropDownChoice<Eigtstatus>("person.eigtstatus",makeChoicesEigtstatus,new ChoiceRenderer<Eigtstatus>("eigt_status_text","id")).setRequired(true));
	
		 final  DateTextField  eigtaufDatum= new DateTextField("person.eigtaufDatum",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 eigtaufDatum.setRequired(true);
		 add(eigtaufDatum);
		 final  DateTextField  eigtletztDatum= new DateTextField("person.eigtletztKontakt",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 add(eigtletztDatum);
		 final  DateTextField  eigtVorlage= new DateTextField("person.eigtVorlage",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 add(eigtVorlage);
		 add(new Button("backButton")
			{
			 @Override
				public void onSubmit()
				{  
					 try{ 
						 
					  Kunde kunde=KundeInput.this.getModelObject();
					  logger.debug("backButton kundenne "+kunde.getId());
					  if(kunde.getId()==null ) kunde.getPerson().addKunde(kunde);	
					 kundeManager.save(kunde); 
					 logger.debug("backButton kundennr "+kunde.getId());
					 if(pageparameters.get("kundennr").toString().equals("null")){
					 pageparameters.remove("kundennr");
					  pageparameters.add("kundennr",kunde.getId().toString());
					 }
			
				    	 } catch(Exception ex){
						
								 pageparameters.add("error", ex);
								 }
					    	 setResponsePage(responsepage, pageparameters);	 	
				}
			});
		 add(new Button("nextButton")
			{
			 @Override
				public void onSubmit()
				{
				 
				 logger.debug("messageid: kunde1 next responsepage : "+responsepage.getSimpleName()+" PageParameters : "+pageparameters);
				 try{ 
			Kunde	  kunden=KundeInput.this.getModelObject();
				  if (kunden.getId()==null) kunden.getPerson().addKunde(kunden);
								 kundeManager.save(kunden); 	
				if (pageparameters.get("kundennr").toString().equals("null")) {
				pageparameters.remove("kundennr");
				pageparameters.add("kundennr",kunden.getId().toString());}
				 
				 logger.debug("messageid: kunde2 next responsepage : "+responsepage.getSimpleName()+" PageParameters : "+pageparameters);

					 activate(new IBreadCrumbPanelFactory()
						{
							@Override
							public BreadCrumbPanel create(String componentId,
								IBreadCrumbModel breadCrumbModel)
							{ 	 
					
								
								if(responsepage.getSimpleName().equals("PersonTree")){
								    	PageParameters pars1=new PageParameters()
								    			.add("eigtid","not null")	
								    		.add("kundennr","not null");
								    		
									  
											
												   if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {	
												 pageparameters.add("nachweisid","null");	
														return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
														 	
									    }
									 
								 	}
				if(responsepage.getSimpleName().equals("AngebotTree")){
				    	PageParameters pars1=new PageParameters()
				    			.add("objid","not null")	
				    		.add("kundennr","null")
				    		.add("eigtid","not null")
					    	.add("angnr","not null");
					  
							
								   if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {	
								 pageparameters.add("nachweisid","null");	
										return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
										 	
					    }
					   pars1=new PageParameters()
				    			.add("objid","not null")	
				    		.add("kundennr","not null")
				    		.add("eigtid","not null")
					    	.add("angnr","not null");
					    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {			
								 pageparameters.add("nachweisid","null");
										return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
										 	
					    }
				 	}
				 	if(responsepage.getSimpleName().equals("ObjektTree")){
				    	PageParameters pars1=new PageParameters()
				    			.add("objid","not null")	
				    		.add("kundennr","null")
				    		.add("eigtid","not null");
					  
							
								   if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {	
								 pageparameters.add("nachweisid","null");	
										return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
										 	
					    }
					   pars1=new PageParameters()
				    			.add("objid","not null")	
				    		.add("kundennr","not null")
				    		.add("eigtid","not null");
					    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {			
								
								 pageparameters.add("nachweisid","null");
										return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
										 	
					    }
				 	}
				if(responsepage.getSimpleName().equals("KundeTree")){
				    	PageParameters pars1=new PageParameters()
				    		.add("kundennr","null");
					  
							
								   if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {	
								 pageparameters.add("nachweisid","null");	
										return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
										 	
					    }
					   pars1=new PageParameters()
				    		.add("kundennr","not null");
					    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {			
								 pageparameters.add("nachweisid","null");
										return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
										 	
					    }
				 	}
			    	return null;
			    	}});
				
				  } 	
				 catch(Exception ex){
					pageparameters.add("error", ex);
				logger.error("KundePanel "+ex,ex);
				setResponsePage(responsepage, pageparameters);
				 }	
				}
			});
	     add(new Button("cancelButton")
			{
				@Override
				public void onSubmit()
				{
					
					setResponsePage(responsepage, pageparameters);
				}
			}.setDefaultFormProcessing(false));        
		
		}
	}
	
	
	
	
	
	
	
		
	/**
	 * Construct.
	 * 
	 * @param id
	 * @param breadCrumbModel
	 * @param result
	 *            The 'result' to display as a label
	 */
/*	public KundePanel(final String id, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);

		String result = "no result";
		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
		add(new Label("result", result));
	}*/
	
	
	
	
	
	
	public KundePanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
		String result="";
		IModel<Kunde> kundei=null;
		 if(pageparameters.getPosition("error")>0) {error(pageparameters.get("error").toString());
		 pageparameters.remove("error");
		 }
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
		 if(responsepage.getSimpleName().equals("AngebotTree")){
		    	PageParameters pars1=new PageParameters()
		    		.add("objid","not null")
		    		.add("eigtid","not null")
		    		.add("kundennr","null")
			    	.add("angnr","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	Kunde kunde = new Kunde();
			    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject()+"/"+(new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundenew",this,null)).getObject();
		
		    	result=pageparameters.get("angnr").toString()+"/"+pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/null";
			
			    
			    	kunde.setId(null);
			    	kunde.setPerson(personManager.get(new Long(pageparameters.get("eigtid").toString())));
			    	kundei=new EntityModel<Kunde>(kunde);
			    }
			    pars1=new PageParameters()
			    		.add("objid","not null")
			    		.add("eigtid","not null")
			    		.add("kundennr","not null")
				    	.add("angnr","not null");
				    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundeold",this,null)).getObject();
		
		    	result=pageparameters.get("angnr").toString()+"/"+pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/"+pageparameters.get("kundennr").toString();
			
				    	 kundei=new EntityModel<Kunde>(Kunde.class,new Long(pageparameters.get("kundennr").toString()));
				    }
		    }
		     if(responsepage.getSimpleName().equals("ObjektTree")){
		    	PageParameters pars1=new PageParameters()
		    		.add("objid","not null")
		    		.add("eigtid","not null")
		    		.add("kundennr","null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	Kunde kunde = new Kunde();
			    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject()+"/"+(new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundenew",this,null)).getObject();
		
		    	result=pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/null";
			
			    
			    	kunde.setId(null);
			    	kunde.setPerson(personManager.get(new Long(pageparameters.get("eigtid").toString())));
			    	kundei=new EntityModel<Kunde>(kunde);
			    }
			    pars1=new PageParameters()
			    		.add("objid","not null")
			    		.add("eigtid","not null")
			    		.add("kundennr","not null");
				    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				    	specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("eigtid",this,null)).getObject()+"/"+(new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundeold",this,null)).getObject();
		
		    	result=pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/"+pageparameters.get("kundennr").toString();
			
				    	 kundei=new EntityModel<Kunde>(Kunde.class,new Long(pageparameters.get("kundennr").toString()));
				    }
		    }
		    
		       if(responsepage.getSimpleName().equals("PersonTree")){
		    	PageParameters pars1=new PageParameters()
		    		.add("eigtid","not null")
		    		.add("kundennr","null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	Kunde kunde = new Kunde();
			    	specialusage= (new StringResourceModel("eigtid",this,null)).getObject()+"/"+(new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundenew",this,null)).getObject();
		
		    	result=pageparameters.get("eigtid").toString()+"/null";
			
			    
			    	kunde.setId(null);
			    	kunde.setPerson(personManager.get(new Long(pageparameters.get("eigtid").toString())));
			    	kundei=new EntityModel<Kunde>(kunde);
			    }
			    pars1=new PageParameters()
			    		.add("eigtid","not null")
			    		.add("kundennr","not null");
				    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				    	specialusage= (new StringResourceModel("eigtid",this,null)).getObject()+"/"+(new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundeold",this,null)).getObject();
		
		    	result=pageparameters.get("eigtid").toString()+"/"+pageparameters.get("kundennr").toString();
			
				    	 kundei=new EntityModel<Kunde>(Kunde.class,new Long(pageparameters.get("kundennr").toString()));
				    }
		    }
		    
		    
		 if(responsepage.getSimpleName().equals("KundeTree")){
		    	PageParameters pars1=new PageParameters()
			    		.add("kundennr","not null");
				    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				    	specialusage= (new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundeold",this,null)).getObject();
		
		    	result=pageparameters.get("kundennr").toString();
			
				    	 kundei=new EntityModel<Kunde>(Kunde.class,new Long(pageparameters.get("kundennr").toString()));
				    }
		    }
		 if(responsepage.getSimpleName().equals("ScoutTree")){
		    	PageParameters pars1=new PageParameters()
		    			.add("eigtid","not null")
			    		.add("scoutid","not null")
			    		.add("kundennr","not null");
				    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				    	specialusage= (new StringResourceModel("kundenid",this,null)).getObject();		
					 subject=(new StringResourceModel("kundeold",this,null)).getObject();
		
		    	result=pageparameters.get("kundennr").toString();
			
				    	 kundei=new EntityModel<Kunde>(Kunde.class,new Long(pageparameters.get("kundennr").toString()));
				    }
		    }
	
		
		 KundeInput form=new KundeInput("form",responsepage,pageparameters,kundei);
		add(new Label("result", result));
		 form.add(new Label("search", subject));
		add(form);
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
