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
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.Application;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.ComponentTag;
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

import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.GroupMembershipInfo;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant;

import braunimmobilien.service.EigentuemertypManager;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import braunimmobilien.model.BaseObject;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Eigtstatus;
import braunimmobilien.service.StrassenManager;
import org.apache.wicket.Application;
import braunimmobilien.webapp.person.SynchronizeContacts;
import braunimmobilien.webapp.person.TelefonListModel;
import braunimmobilien.webapp.person.TelefonPanel;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.OrteManager;
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
{String subject = "no subject";
String result = "no result";
private String specialusage="";
	
	@SpringBean
	ObjektManager objektManager;
	@SpringBean
	StrassenManager strassenManager;
	@SpringBean
	PersonManager personenManager;
	
	@SpringBean
    private  EigentuemertypManager eigentuemertypManager;
		@SpringBean
		EigtstatusManager eigtstatusManager;
	    static Logger logger = LoggerFactory.getLogger(PersonPanel.class);
		
	private final class PersonInput extends Form<Personen>
	{
	

		
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
    	        
    	        private TelefonListModel telefone = new TelefonListModel();
    	    
		/** test input string. */
		

		/**
		 * Construct.
		 * 
		 * @param id
		 *            The component id
		 */
		public PersonInput(String id,final Class responsepage,final PageParameters pageparameters,final IModel<Personen> objekte)
		{
			
			super(id, new CompoundPropertyModel(objekte));
			 
			 try{telefone = new TelefonListModel(((Personen)PersonInput.this.getDefaultModelObject()).getEigtTelefone());}
			    catch(Exception e){error("EigtTel nicht in Ordnung "+e);
			   
			       	    telefone=new TelefonListModel();
			       	    }
			
			TelefonPanel telefonPanel=new TelefonPanel("telefone",telefone);	
				
				telefonPanel.setOutputMarkupId(true);
			telefonPanel.setVisible(true);
			add(telefonPanel);
		add(new Label("id"));
		 add(new Label("strasse.id"));		
		 add(new TextField<String>("eigtHausnummer").setRequired(true));
		 add(new TextArea<String>("eigtInfo"));
		 add(new TextField<String>("eigtHomepage"));
		 add(new TextField<String>("eigtTelefone"));
		 add(new TextField<String>("eigtName").setRequired(true));
		 add(new TextField<String>("eigtAnschrift").setRequired(true));
		 add(new TextField<String>("eigtFirma"));
		 add(new TextField<String>("eigtTel"));
		 add(new TextField<String>("eigtBriefanrede").setRequired(true));
		 add(new TextField<String>("eigtEmail"));
		 add(new CheckBox("eigtAktuell").setRequired(true));
		 add(new DropDownChoice<Eigtstatus>("eigtstatus",makeChoicesEigtstatus,new ChoiceRenderer<Eigtstatus>("eigt_status_text","id")).setRequired(true));
	
		 final  DateTextField  eigtaufDatum= new DateTextField("eigtaufDatum",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 eigtaufDatum.setRequired(true);
		 add(eigtaufDatum);
		 final  DateTextField  eigtletztDatum= new DateTextField("eigtletztKontakt",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 add(eigtletztDatum);
		 final  DateTextField  eigtVorlage= new DateTextField("eigtVorlage",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 add(eigtVorlage);
		 add(new Button("locationButton")
			{
			 @Override
				public void onSubmit()
				{  
				activate(new IBreadCrumbPanelFactory()
				{
					@Override
					public BreadCrumbPanel create(String componentId,
						IBreadCrumbModel breadCrumbModel)
					{ 
				 try{
					
						   Personen person=PersonInput.this.getModelObject();
							 ((IndexBootstrap)PersonPanel.this.getPage()).checkAction(responsepage,pageparameters,person);
									((IndexBootstrap)PersonPanel.this.getPage()).setPerson(new EntityModel(person));
									((IndexBootstrap)PersonPanel.this.getPage()).setWithNext(false);
								 return new StrassenSuchePanel(componentId,responsepage,pageparameters, breadCrumbModel,0);
						 
							/*	 if(person.getId()!=null){  
								personenManager.save(person); 	
								
								
								 return new StrassenSuchePanel(componentId,responsepage,pageparameters, breadCrumbModel,0);
								 }*/
						
			
				 }
			
				 catch(Exception ex){
					 ex.printStackTrace();
			 System.err.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"+ex);
				System.exit(5);
				 pageparameters.add("error", ex);
				 }
				

				 return null;
					}
				});
				}
			});
		 add(new Button("nextButton")
			{
			 @Override
				public void onSubmit()
				{ 
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
					return null;}
					});
					
				
				}
			});
		 
		 add(new Button("backButton")
			{
			 @Override
				public void onSubmit()
				{ 
				 try{savePerson(pageparameters);
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
				    } 
	 catch(Exception ex){ 
	 pageparameters.add("error", ex);
	 }			
				 this.setResponsePage(responsepage, pageparameters);	
				}
			});
		 
		 add(new Button("treeButton")
			{
			 @Override
				public void onSubmit()
				{ 
				 try{savePerson(pageparameters);
					
				//		   Personen person=PersonInput.this.getModelObject();
				//			 ((IndexBootstrap)PersonPanel.this.getPage()).checkAction(responsepage,pageparameters,person);
						    } 
				 
			
				 catch(Exception ex){
					 ex.printStackTrace();
				System.exit(5);
				 pageparameters.add("error", ex);
				 }
			
				 this.setResponsePage(PersonTree.class, pageparameters);
					
				
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
		
		private void savePerson(PageParameters pars){
			 if(telefone.getTelefonemodel().getTelefonList().size()!=0){
					try{  ((Personen)this.getDefaultModelObject()).setEigtTelefone(telefone.getTelXml());
					
				  }
					catch(Exception e){error("Fehler in der Telefonliste :"+e);
					((Personen)this.getDefaultModelObject()).setEigtTelefone(null);
					
					}}
				
					 	ContactEntry contactEntry=null;
				SynchronizeContacts synchronizecontacts=null;
						try{
							synchronizecontacts= new SynchronizeContacts((WicketApplication)Application.get());
						
						if(((Personen)this.getDefaultModelObject()).getEigtTel()!=null&&((Personen)this.getDefaultModelObject()).getEigtTel().contains("www.google.com")){
						
							contactEntry=synchronizecontacts.findContact(
									((Personen)this.getDefaultModelObject()).getEigtTel());
									synchronizecontacts.deleteContact(contactEntry);
						}
						}
						catch(Exception e){error("Fehler bei find Contacts : "+e);
						((Personen)this.getDefaultModelObject()).setEigtTel("");
					
						}
					
							try{
					if(((Personen)this.getDefaultModelObject()).getEigtTelefone()!=null&&((Personen)this.getDefaultModelObject()).getEigtTelefone().length()>0){
						contactEntry =synchronizecontacts.createContact(((Personen)this.getDefaultModelObject()),telefone,"Testaufnahme");
						((Personen)this.getDefaultModelObject()).setEigtTel(contactEntry.getId());
						
					}
					System.err.println("Eigtstatus form1 "+((Personen)this.getDefaultModelObject()).getEigtstatus());
					Personen person=null;
					if (((Personen)this.getDefaultModelObject()).getId()!=null&&((Personen)this.getDefaultModelObject()).getId().longValue()>0){
					person=personenManager.get(((Personen)this.getDefaultModelObject()).getId());
					System.err.println("Eigtstatus alt "+person.getEigtstatus());
					person.setEigtstatus(eigtstatusManager.get(((Personen)this.getDefaultModelObject()).getEigtstatus().getId()));
					person.copyPersonen(((Personen)this.getDefaultModelObject()));
					System.err.println("Eigtstatus neu "+person.getEigtstatus());
					}
					else{
						person=(((Personen)this.getDefaultModelObject()));
						Strassen strasse=strassenManager.get(person.getStrasse().getId());
						person.setStrasse(strasse);
						strasse.addPerson(person);	
						person.setEigtstatus(eigtstatusManager.get(((Personen)this.getDefaultModelObject()).getEigtstatus().getId()));
					}
					
				
					System.err.println("Eigtstatus vor "+person.getEigtstatus());	
					Personen person1=personenManager.save(person);
					System.err.println("Eigtstatus danach "+person1.getEigtstatus());
					pars.remove("eigtid");
					pars.add("eigtid",person.getId().toString());
					if(contactEntry!=null){
						contactEntry.addGroupMembershipInfo(new GroupMembershipInfo(false,synchronizecontacts.findContactGroup("System Group: My Contacts")));
					
						contactEntry =synchronizecontacts.updateContactEntry(contactEntry);
					}
						}
					catch(Exception e){
						System.err.println("Fehler");
						e.printStackTrace();
						error("Fehler bei Synchronize Contacts : "+e);
					}
							if(pars.getPosition("telefon")>=0) pars.remove("telefon");
							else {pars.set("telefon", "on");};
			
			
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
	public PersonPanel(final String id, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);

		String result = "no result";
		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
		add(new Label("result", result));
	}
	
	
	public PersonPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel, IModel<Personen> person)
	{
		super(id, breadCrumbModel);
		
		String result=(new StringResourceModel("objekt.undefined",this,null)).getObject();
			result=pageparameters.get("eigtid").toString();
		
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
		
		
		add(new Label("result", result));
		add(new PersonInput("form",responsepage,pageparameters,person));
	}
	
	
	
	public PersonPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
		boolean found=false;
		 logger.debug("new PersonPanel responsepage "+responsepage.getSimpleName()+" PageParameters "+pageparameters);
		 if(pageparameters.getPosition("error")>0) {error(pageparameters.get("error").toString());
		 pageparameters.remove("error");}
		String result=(new StringResourceModel("objekt.undefined",this,null)).getObject();
		IModel personModel=null;
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
		 
		 if(found==false) System.exit(5);
			
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
		PersonInput form=new PersonInput("form",responsepage,pageparameters,personModel);
		 form.add(new Label("search", subject));
		add(new Label("result", result));
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
