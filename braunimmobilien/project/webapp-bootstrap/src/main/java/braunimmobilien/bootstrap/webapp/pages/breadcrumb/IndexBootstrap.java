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
import org.apache.wicket.Page;

import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.bootstrap.webapp.pages.BraunHomePage;
import braunimmobilien.bootstrap.webapp.pages.objekt.ObjektTree;
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import org.apache.wicket.spring.injection.annot.SpringBean;

import braunimmobilien.service.LandManager;

import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.ajax.AjaxRequestTarget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;

import braunimmobilien.bootstrap.webapp.breadcrumb.BreadCrumbBar;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.EigentuemertypManager;

import org.apache.wicket.spring.injection.annot.SpringBean;

import braunimmobilien.model.BaseObject;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Land;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.bootstrap.webapp.EntityModel;

import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
/**
 * Index page for the bread crumb example.
 * 
 * @author Eelco Hillenius
 */
public class IndexBootstrap extends BasePage
{private final FeedbackPanel feedback=new FeedbackPanel("feedback");;
@SpringBean
private AngebotManager angebotManager;
@SpringBean
private NachweiseManager nachweiseManager;
@SpringBean
private ScoutManager scoutManager;
@SpringBean
private AngobjzuordManager angobjzuordManager;
@SpringBean
private KundeManager kundeManager;
@SpringBean
private StrassenManager strassenManager;
@SpringBean
private  PersonManager personManager;
@SpringBean
private  ObjektManager objektManager;
@SpringBean
private  EigentuemertypManager eigentuemertypManager;
private IModel<Angebot> angebot=new EntityModel<Angebot>(null);
private boolean withObjekt;






public boolean isWithObjekt() {
	return withObjekt;
}





public void setWithObjekt(boolean withObjekt) {
	this.withObjekt = withObjekt;
}
private boolean withNext=true;
public boolean isWithNext() {
	return withNext;
}





public void setWithNext(boolean withNext) {
	this.withNext = withNext;
}
private IModel<Personen> person=new EntityModel<Personen>(null);
public IModel<Personen> getPerson() {
	return person;
}





public void setPerson(IModel<Personen> person) {
	this.person = person;
}








private IModel<Objekte> objekt=new EntityModel<Objekte>(null);
	public IModel<Objekte> getObjekt() {
	return objekt;
}





public void setObjekt(IModel<Objekte> objekt) {
	this.objekt = objekt;
}





	public IModel<Angebot> getAngebot() {
	return angebot;
}





public void setAngebot(IModel<Angebot> angebot) {
	this.angebot = angebot;
}

public IndexBootstrap(){
	super();
	BreadCrumbBar breadCrumbBar = new BreadCrumbBar("breadCrumbBar");
	add(breadCrumbBar);
	 add(feedback);
	 BreadCrumbPanel firstPanel=null;
				firstPanel = new StrassenSuchePanel("panel",braunimmobilien.bootstrap.webapp.pages.BraunHomePage.class, new PageParameters(),breadCrumbBar,2);
								add(firstPanel);
			breadCrumbBar.setActive(firstPanel);
			}			

public IndexBootstrap(PageParameters pageparameters){
	super(pageparameters);
	
	BreadCrumbBar breadCrumbBar = new BreadCrumbBar("breadCrumbBar");
	add(breadCrumbBar);
	 add(feedback);
	 BreadCrumbPanel firstPanel=null;
		 PageParameters pars1=new PageParameters()
	    	.add("objid","null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				
				firstPanel = new StrassenSuchePanel("panel",ObjektTree.class, pageparameters,breadCrumbBar);
				
			}
			 pars1=new PageParameters()
		    	.add("eigtid","null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					
					firstPanel = new StrassenSuchePanel("panel",PersonTree.class, pageparameters,breadCrumbBar);
					
				}
			add(firstPanel);
			breadCrumbBar.setActive(firstPanel);
	 }

public IndexBootstrap(Class responsepage,PageParameters pageparameters){
	super(pageparameters);
	
	BreadCrumbBar breadCrumbBar = new BreadCrumbBar("breadCrumbBar");
	add(breadCrumbBar);
	 add(feedback);
	 BreadCrumbPanel firstPanel=null;
	 if(responsepage.getSimpleName().equals("KundeSuch")){
		 PageParameters pars1=new PageParameters()
				 .add("kundennr","not null")
		 		.add("usage","not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				
				if (pageparameters.get("usage").toString().equals("nachweis"))		firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
				if (pageparameters.get("usage").toString().equals("edit"))		firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
				
			}
		 
		 
	 }
	 
	 if(responsepage.getSimpleName().equals("PersonTree")){
		
		 PageParameters pars2=new PageParameters()
					.add("telefon","not null")
					.add("kundennr","not null")
					.add("eigtid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
						firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
					}
					  pars2=new PageParameters()
								.add("kundennr","null")
								.add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
									firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
					 pars2=new PageParameters()
							.add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
									firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
			PageParameters pars3=new PageParameters()
					.add("telefon","not null")
					.add("eigtid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars3,true)) {
						firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
					}	
				
					 PageParameters pars4=new PageParameters()
							 .add("telefon","not null")
							 .add("objid","not null")
					 .add("eigtid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
							firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
						}	
						 pars4=new PageParameters()
								 .add("objid","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
								firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
						PageParameters pars5=new PageParameters()
								 .add("nachweisid","not null")
								 .add("telefon","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars5,true)) {
								firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
							PageParameters pars6=new PageParameters()
									.add("telefon","not null")
									 .add("objid","not null")
									 .add("nachweisid","not null")
							 .add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
									firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
								PageParameters pars7=new PageParameters()
										.add("telefon","not null")
										 .add("objid","not null")
										 .add("scoutid","not null")
								 .add("eigtid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
										firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
									}	
									PageParameters pars8=new PageParameters()
											.add("telefon","not null")
											 .add("scoutid","not null")
									 .add("eigtid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
											firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);	
										}
										pars8=new PageParameters()
									.add("kundennr","not null")
										 .add("eigtid","not null");
											if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
												firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
											}	
	 }
	 
	 
	 
	 if(responsepage.getSimpleName().equals("KundeTree")){
		 PageParameters pars1=new PageParameters()
				 .add("kundennr","not null");
		 		
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
			}
		 
			 PageParameters pars2=new PageParameters()
					 .add("kundennr","not null")
			 .add("nachweisid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
					firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
				}
				 PageParameters pars3=new PageParameters()
						 .add("kundennr","not null")
				 .add("eigtid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars3,true)) {
						firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
					}	
				
					 PageParameters pars4=new PageParameters()
							 .add("kundennr","not null")
							 .add("objid","not null")
					 .add("eigtid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
							firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
						}	
						PageParameters pars5=new PageParameters()
								 .add("kundennr","not null")
								 .add("nachweisid","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars5,true)) {
								firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
							PageParameters pars6=new PageParameters()
									 .add("kundennr","not null")
									 .add("objid","not null")
									 .add("nachweisid","not null")
							 .add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
									firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
								PageParameters pars7=new PageParameters()
										 .add("kundennr","not null")
										 .add("objid","not null")
										 .add("scoutid","not null")
								 .add("eigtid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
										firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
									}	
									PageParameters pars8=new PageParameters()
											 .add("kundennr","not null")
											 .add("scoutid","not null")
									 .add("eigtid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
											firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);	
										}	
	 }
	 
	 if(responsepage.getSimpleName().equals("AngebotTree")){
		 PageParameters pars1=new PageParameters()
				 .add("angnr","null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				
	firstPanel = new AngebotPanel("panel",responsepage, pageparameters,breadCrumbBar);
				
			}
			pars1=new PageParameters()
					 .add("angnr","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					
		firstPanel = new AngebotPanel("panel",responsepage, pageparameters,breadCrumbBar);
					
				} 
		 pars1=new PageParameters()
				 .add("angnr","not null")
		 		.add("nachweisid","not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				
	firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
				
			}
			 pars1=new PageParameters()
					 .add("kundennr","not null")
					 .add("angnr","not null")
			 		.add("nachweisid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					
		firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
					
				}
			 pars1=new PageParameters()
					 .add("objid","null")
					 .add("angnr","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
		firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar);
					
				}
			 pars1=new PageParameters()
					 .add("angnr","not null")
			 		.add("objid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					
		firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);
					
				}
				 pars1=new PageParameters()
						 .add("scoutid","not null")
						 .add("angnr","not null")
				 		.add("objid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						
			firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);
						
					}
				 pars1=new PageParameters()
						 .add("eigtid","null")
						 .add("objid","not null")
						 .add("angnr","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					/*	PageParameters parsi=new PageParameters()
								.add("objid",pageparameters.get("objid").toString())	
								.add("angnr",pageparameters.get("angnr").toString());*/	
			firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar);
						
					}
					 pars1=new PageParameters()
							 .add("eigtid","not null")
							 .add("objid","not null")
							 .add("angnr","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							/*PageParameters parsi=new PageParameters()
									.add("objid",pageparameters.get("objid").toString())	
									.add("angnr",pageparameters.get("angnr").toString());*/	
				firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);
							
						}
						 pars1=new PageParameters()
								 .add("angnr","not null")
								 .add("eigtid","not null")
								 .add("kundennr","null")
						 		.add("objid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
								
					firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
								
							}	
						
						
				 pars1=new PageParameters()
						 .add("angnr","not null")
						 .add("eigtid","not null")
						 .add("kundennr","not null")
				 		.add("objid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						
			firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
						
					}
					pars1=new PageParameters()
							 .add("angnr","not null")
							 .add("eigtid","not null")
							 .add("nachweisid","null")
							 .add("kundennr","not null")
					 		.add("objid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							
				firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
							
						}
					
					
					
					pars1=new PageParameters()
							 .add("angnr","not null")
							 .add("eigtid","not null")
							 .add("nachweisid","not null")
							 .add("kundennr","not null")
					 		.add("objid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							
				firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
							
						}
			}
	 if(responsepage.getSimpleName().equals("ObjektTree")){
		 PageParameters pars1=new PageParameters()
				 .add("objid","not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				
	firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);
				
			}
			
			pars1.add("nachweisid","null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					
		firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
					
				}
			
			
			
			PageParameters pars2=new PageParameters()
					 .add("objid","not null")
			 .add("nachweisid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
					
		firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
					
				}
				PageParameters pars3=new PageParameters()
						 .add("objid","not null")
				.add("nachweisid","not null")
				.add("kundnnr","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars3,true)) {
						
			firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
						
					}
					PageParameters pars4=new PageParameters()
							 .add("objid","not null")
					.add("scoutid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
							
				firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);
							
						}
					
						PageParameters pars5=new PageParameters()
								 .add("objid","not null")
								 .add("eigtid","not null")
						.add("scoutid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars5,true)) {
								
					firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);
								
							}
							PageParameters pars6=new PageParameters()
									 .add("objid","not null")
									 .add("eigtid","not null")
							.add("kundennr","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
									
						firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
									
								}
								PageParameters pars7=new PageParameters()
										 .add("objid","not null")
										 .add("eigtid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars7,true)) {
										
							firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);
										
									}
									PageParameters pars8=new PageParameters()
											 .add("objid","not null")
											 .add("eigtid","null");
										if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
											
								firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar);
											
										}
										PageParameters pars9=new PageParameters()
												 .add("objid","not null")
												 .add("angnr","not null");
											if	(MaklerFlowUtility.fits(pageparameters,pars9,true)) {
												
									firstPanel = new AngebotPanel("panel",responsepage, pageparameters,breadCrumbBar);
												
											}
											PageParameters pars12=new PageParameters()
													 .add("objid","not null")
													 .add("angnr","null");
												if	(MaklerFlowUtility.fits(pageparameters,pars12,true)) {
													
										firstPanel = new AngebotPanel("panel",responsepage, pageparameters,breadCrumbBar);
													
												}
											PageParameters pars10=new PageParameters()
													 .add("objid","not null")
													 .add("nachweisid","not null")
													 .add("eigtid","not null");
												if	(MaklerFlowUtility.fits(pageparameters,pars10,true)) {
													
										firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);
													
												}
												PageParameters pars11=new PageParameters()
														 .add("objid","not null")
														 .add("nachweisid","null");
													
													if	(MaklerFlowUtility.fits(pageparameters,pars11,true)) {
														
											firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
														
													}
												pars11=new PageParameters()
															 .add("objid","null")
															 .add("strid","not null");
														
														if	(MaklerFlowUtility.fits(pageparameters,pars11,true)) {
															
												firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);
															
														}
			}
		
	 
	 
			add(firstPanel);
			breadCrumbBar.setActive(firstPanel);
	 }


public IndexBootstrap(Class responsepage,PageParameters pageparameters,int whichVariant){
	
	super(pageparameters);
	
	BreadCrumbBar breadCrumbBar = new BreadCrumbBar("breadCrumbBar");
	add(breadCrumbBar);
	 add(feedback);
	 BreadCrumbPanel firstPanel=null;
	
	 if(responsepage.getSimpleName().equals("KundeSuch")){
		 PageParameters pars1=new PageParameters()
	    	.add("kundennr","not null");
		 
		
			if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
				
				firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
			}
	 }
	 
	 
	 
	 if(responsepage.getSimpleName().equals("ScoutTree")){
		 PageParameters pars1=new PageParameters();
		 pars1=new PageParameters()
			    	.add("scoutid","not null")
		 			.add("objid", "not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
				if (firstPanel==null)	firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);
			}
			
			pars1=new PageParameters()
			    	.add("scoutid","not null")
		 			.add("where", "not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,false)&&whichVariant==1) {
				
				if (firstPanel==null)	firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar,1);;
			}
			pars1=new PageParameters()
			    	.add("scoutid","not null")
			    	.add("eigtid", "not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
						
				if (firstPanel==null)	firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);
					}
			pars1=new PageParameters()
			    	.add("scoutid","not null")
			    	.add("who", "not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,false)&&whichVariant==2) {
				if (firstPanel==null)	firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar,-1);
					}
			 pars1=new PageParameters()
	    	.add("scoutid","not null");
		 
		
			if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
				
			if (firstPanel==null)	firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);
			}
		
	
		
	 }
	 
	
	 if(responsepage.getSimpleName().equals("AngebotTree")){
		 PageParameters pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar,1);
			}
			
			 pars1=new PageParameters()
		    	.add("angnr","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						firstPanel=new AngebotPanel("panel", responsepage,pageparameters,breadCrumbBar,new EntityModel<Angebot>(angebotManager.get(pageparameters.get("angnr").toString())));		
				}
			 pars1=new PageParameters()
		    	.add("angnr","not null")
		    	.add("objid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						firstPanel=new ObjektPanel("panel", responsepage,pageparameters,breadCrumbBar,new EntityModel<Objekte>(objektManager.get(new Long(pageparameters.get("objid").toString()))));		
				}
				 pars1=new PageParameters()
			    	.add("angnr","not null")
			    	.add("objid","not null")
				 .add("eigtid","null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
						firstPanel=new StrassenSuchePanel("panel", responsepage,pageparameters,breadCrumbBar,-1);		
					}
					 pars1=new PageParameters()
				    	.add("angnr","not null")
				    	.add("objid","not null")
					 .add("eigtid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
								firstPanel=new PersonPanel("panel", responsepage,pageparameters,breadCrumbBar);	
						}
						 pars1=new PageParameters()
					    	.add("angnr","not null")
					    	.add("objid","not null")
					    	.add("kundennr","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
									firstPanel=new KundePanel("panel", responsepage,pageparameters,breadCrumbBar);	
							}
							 pars1=new PageParameters()
						    	.add("angnr","not null")
						    	.add("scoutid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
										firstPanel=new ScoutPanel("panel", responsepage,pageparameters,breadCrumbBar);	
								}
								 pars1=new PageParameters()
							    	.add("angnr","not null")
							    	.add("nachweisid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {	
											firstPanel=new NachweisPanel("panel", responsepage,pageparameters,breadCrumbBar);	
									}
									 pars1=new PageParameters()
								    	.add("angnr","not null")
								    	.add("kundennr","not null")
								    	.add("nachweisid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
												firstPanel=new KundePanel("panel", responsepage,pageparameters,breadCrumbBar);	
										}				
			}		
	 add(firstPanel);
		breadCrumbBar.setActive(firstPanel);
}




	public IndexBootstrap(Class responsepage,PageParameters pageparameters,IModel<?> model,boolean withNext){
		
		super(pageparameters);
//		model.detach();
		
		BreadCrumbBar breadCrumbBar = new BreadCrumbBar("breadCrumbBar");
		add(breadCrumbBar);
		 add(feedback);
		 BreadCrumbPanel firstPanel=null;
	
		 if(responsepage.getSimpleName().equals("ScoutTree")){
			 PageParameters pars1=new PageParameters()
		    	.add("scoutid","not null");
		
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					
					firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);
				}
		 }
		 
		
		 if(responsepage.getSimpleName().equals("AngebotTree")){
			 PageParameters pars1=new PageParameters()
		    	.add("angnr","not null")
		    	.add("objid","null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar,1);
				}
				
				 pars1=new PageParameters()
			    	.add("angnr","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							firstPanel=new AngebotPanel("panel", responsepage,pageparameters,breadCrumbBar,new EntityModel<Angebot>(angebotManager.get(pageparameters.get("angnr").toString())));		
					}
				 pars1=new PageParameters()
			    	.add("angnr","not null")
			    	.add("objid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							firstPanel=new ObjektPanel("panel", responsepage,pageparameters,breadCrumbBar,new EntityModel<Objekte>(Objekte.class,new Long(pageparameters.get("objid").toString())));		
					}
					 pars1=new PageParameters()
				    	.add("angnr","not null")
				    	.add("objid","not null")
					 .add("eigtid","null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
							firstPanel=new StrassenSuchePanel("panel", responsepage,pageparameters,breadCrumbBar,-1);		
						}
						 pars1=new PageParameters()
					    	.add("angnr","not null")
					    	.add("objid","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
									firstPanel=new PersonPanel("panel", responsepage,pageparameters,breadCrumbBar);	
							}
							 pars1=new PageParameters()
						    	.add("angnr","not null")
						    	.add("objid","not null")
						    	.add("kundennr","not null")
							 .add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
										firstPanel=new KundePanel("panel", responsepage,pageparameters,breadCrumbBar);	
								}
								 pars1=new PageParameters()
							    	.add("angnr","not null")
							    	.add("scoutid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
											firstPanel=new ScoutPanel("panel", responsepage,pageparameters,breadCrumbBar);	
									}
									 pars1=new PageParameters()
								    	.add("angnr","not null")
								    	.add("nachweisid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {	
												firstPanel=new NachweisPanel("panel", responsepage,pageparameters,breadCrumbBar);	
										}
										 pars1=new PageParameters()
									    	.add("angnr","not null")
									    	.add("kundennr","not null")
									    	.add("nachweisid","not null");
											if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {		
													firstPanel=new KundePanel("panel", responsepage,pageparameters,breadCrumbBar);	
											}
									add(firstPanel);
				breadCrumbBar.setActive(firstPanel);
				}			
	}
	
	

	@Override
	 public void onDetach()
	    {
	    	super.onDetach();
	    	angebot.detach();
	    	objekt.detach();
	    	person.detach();
	    } 
	
	
	
    public void makeFlowActionObjekt(final Class responsepage,PageParameters pars,BaseObject baseobject){
    	if(responsepage.getSimpleName().equals("AngebotTree")){
    		Objekte objekt1=(Objekte)baseobject;
    	PageParameters pars1=new PageParameters()
    	.add("angnr","not null")
    	.add("objid","null");
    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
    	System.err.println("Found");
    	
    	Angebot angebot1=angebotManager.get(pars.get("angnr").toString());
					//	pars.set("objid",objekt1.getId());
						objekt1.getStrasse().addObjekt(objekt1);
						 Angobjzuord angobjzuord = new Angobjzuord(); 
							angebot1.addAngobjzuord(angobjzuord);
							 angobjzuord.setAngebot(angebot1);
							  angobjzuord.setObjekte(objekt1);
							  objekt1.addAngobjzuord(angobjzuord);
							angobjzuordManager.save(angobjzuord);	
							 pars.remove("objid","0"); 
							 pars.add("objid", objekt1.getId().toString());
							return;
					}
	pars1=new PageParameters()
	.add("angnr","not null")
	.add("objid","not null");
if	(MaklerFlowUtility.fits(pars,pars1,true)) {	
	objektManager.save(objekt1);
	return;
				}
    	
    }
    	if(responsepage.getSimpleName().equals("BraunHomePage")){
    		Objekte objekt1=(Objekte)baseobject;
    	PageParameters pars1=new PageParameters()
    	.add("objid","null");
    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
    	objektManager.save(objekt1);	
							return;
					}
    }	  	
    }	
    
    
    public void makeFlowActionPerson(final Class responsepage,PageParameters pars,BaseObject baseobject){
    	if(responsepage.getSimpleName().equals("AngebotTree")){
    		PageParameters pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","not null")
	    	.add("eigttypid","not null")
			.add("eigtid","null");
			if	(MaklerFlowUtility.fits(pars,pars1,true)) {
				
				Objperszuord objperszuord = new Objperszuord();
				 objperszuord.setId(null);
			
					   objperszuord.setPersonen((Personen)baseobject); 
					  objperszuord.setEigentuemertyp(eigentuemertypManager.get(new Long(pars.get("eigttypid").toString())));
					  objperszuord.setObjekt(objektManager.get(new Long(pars.get("objid").toString())));
			if(objperszuord.getPersonen().getId()==null)	 objperszuord.getPersonen().getStrasse().addPerson(objperszuord.getPersonen());
				 objperszuord.getPersonen().addObjperszuord(objperszuord);
				 objperszuord.getObjekt().addObjperszuord(objperszuord); 
						 personManager.save(objperszuord.getPersonen()); 
						 pars.remove("eigtid","0"); 
						 pars.add("eigtid", objperszuord.getPersonen().getId().toString());
						 return;
				 }
			pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","not null")
			.add("eigtid","not null");
			if	(MaklerFlowUtility.fits(pars,pars1,true)) {
						 personManager.save((Personen)baseobject); 
				 }
    	}
    	if(responsepage.getSimpleName().equals("BraunHomePage")){
    	Personen person=(Personen)baseobject;
    	
    	PageParameters pars1=new PageParameters()
    			.add("strid","not null")
    	.add("eigtid","null");
  
    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
//	 System.err.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+pars);
//	    	System.exit(5);
	    	person.getStrasse().addPerson(person);
    	personManager.save(person);	
							return;
					}
pars1=new PageParameters()
	.add("eigtid","not null");

if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	personManager.save(person);	
						return;
				}
    }	  	
    }
    
 
	public void checkAction(final Class responsepage,final PageParameters pageparameters,BaseObject object){
		MaklerFlowUtility flow=new MaklerFlowUtility();
		flow.search(this,responsepage,pageparameters,object);		
	}
	
	public BreadCrumbPanel checkActionBreadcrumb(String componentId,
			IBreadCrumbModel breadCrumbModel, final Class responsepage,final PageParameters pageparameters,BaseObject object){
		MaklerFlowUtility flow=new MaklerFlowUtility();
		return flow.searchBreadcrumb(this,componentId,breadCrumbModel,responsepage,pageparameters,object);		
	}
	
	
	public BreadCrumbPanel makeFlowActionBreadcrumbObjekt(String componentId,IBreadCrumbModel breadCrumbModel,final Class responsepage,final PageParameters pars, BaseObject baseObject){
		
		if(responsepage.getSimpleName().equals("AngebotTree")){
	    	PageParameters pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","null");
	    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	    	IModel<Objekte> objekt=new EntityModel<Objekte>((Objekte)baseObject);
	    	objekt.detach();
           return new ObjektPanel(componentId, responsepage,pars,breadCrumbModel, objekt);	
	    }
	    	}
		if(responsepage.getSimpleName().equals("BraunHomePage")){
	    	PageParameters pars1=new PageParameters()
	    	.add("objid","null");
	    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	    	IModel<Objekte> objekt=new EntityModel<Objekte>((Objekte)baseObject);
	    	objekt.detach();
           return new ObjektPanel(componentId, responsepage,pars,breadCrumbModel, objekt);	
	    }
	    	}
		
		
		return null;
	}
	
	
	
	public BreadCrumbPanel makeFlowActionBreadcrumbPerson(String componentId,IBreadCrumbModel breadCrumbModel,final Class responsepage,final PageParameters pars, BaseObject baseObject){
		
		if(responsepage.getSimpleName().equals("AngebotTree")){
	    	PageParameters pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","not null")
	    	.add("eigttypid","not null")
	    	.add("eigtid","null");
	    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	    	IModel<Personen> person=new EntityModel<Personen>((Personen)baseObject);
	    //	System.err.println((Personen)baseObject);
	    //	System.exit(5);
	    	person.detach();
           return new PersonPanel(componentId, responsepage,pars,breadCrumbModel,person);	
	    }
	    	}
		if(responsepage.getSimpleName().equals("BraunHomePage")){
	    	PageParameters pars1=new PageParameters()
	    	.add("eigtid","null");
	    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	    	IModel<Personen> person=new EntityModel<Personen>((Personen)baseObject);
	    	person.detach();
	    	return new PersonPanel(componentId, responsepage,pars,breadCrumbModel,person);	
	    }
	    	}
		return null;
	}
	
	
	public BreadCrumbPanel makeFlowActionBreadcrumbStrasse(String componentId,IBreadCrumbModel breadCrumbModel,final Class responsepage,final PageParameters pars, BaseObject baseObject){
	
		if(responsepage.getSimpleName().equals("AngebotTree")){
	    	PageParameters pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","null");
	    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	    	System.err.println("Found"); 	
	    	Objekte	objekt1=new Objekte();
			 objekt1.setId(null);
			 Strassen strasse=(Strassen)baseObject;
				objekt1.setStrasse(strasse);
           objekt1.setObjhausnummer(strasse.getStrname());
           return new ObjektPanel(componentId, responsepage,pars,breadCrumbModel, new EntityModel<Objekte>(objekt1));	
	    }
	  
	    	pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","not null");
	    	 if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	 	    	System.err.println("Found"); 	
	 	    	Objekte objekt1=getObjekt().getObject();
	 	   	objekt1.getStrasse().getObjekte().remove(objekt1);
	 		 Strassen strasse=(Strassen)baseObject;
	 	   	strasse.addObjekt(objekt1);
	 	   	objekt1.setStrasse(strasse);
	 	   	objekt1.setObjhausnummer(strasse.getStrname());
	 	   	strassenManager.save(strasse);
	 	   	return new ObjektPanel(componentId, responsepage,pars,breadCrumbModel, new EntityModel<Objekte>(objekt1));
	 	   		    }
	
		    	 pars1=new PageParameters()
			    	.add("angnr","not null")
			    	.add("eigtid","null")
			    	.add("eigttypid","not null")
			    	.add("objid","not null");
			    	 if	(MaklerFlowUtility.fits(pars,pars1,true)) {	
			    		 Strassen strasse=(Strassen)baseObject;
			    		 pars.add("strid",strasse.getId().toString());
			            return new PersonPanel(componentId, responsepage,pars,breadCrumbModel);
			 	    }
			    	
			    	 pars1=new PageParameters()
				    	.add("angnr","not null")
				    	.add("eigtid","not null")
				    	.add("eigttypid","not null")
				    	.add("objid","not null");
				    	 if	(MaklerFlowUtility.fits(pars,pars1,true)) {	
				    		 Personen person=getPerson().getObject();
				 	 	   	person.getStrasse().getPersonen().remove(person);
				 	 		 Strassen strasse=(Strassen)baseObject;
				 	 	   	strasse.addPerson(person);
				 	 	   	person.setStrasse(strasse);
				 	 	   	person.setEigtHausnummer(strasse.getStrname());
				 	 	   	strassenManager.save(strasse);
				            return new PersonPanel(componentId, responsepage,pars,breadCrumbModel);
				 	    }
			    	 
			    	 
		    	 pars1=new PageParameters()
			    	.add("angnr","not null")
			    	.add("eigtid","not null")
			    	.add("objid","not null");
			    	 if	(MaklerFlowUtility.fits(pars,pars1,true)) {
			    		
			    		 Personen person1=personManager.get(new Long(pars.get("eigtid").toString()));
							person1.getStrasse().getPersonen().remove(person1);
							 Strassen strasse=(Strassen)baseObject;
							strasse.addPerson(person1);
							person1.setStrasse(strasse);
							person1.setEigtHausnummer(strasse.getStrname());
							strassenManager.save(strasse);
							return new PersonPanel(componentId, responsepage,pars,breadCrumbModel);
				   }
			    	 

	    	}
		
		if(responsepage.getSimpleName().equals("BraunHomePage")){
	    	PageParameters pars1=new PageParameters()
	    	.add("objid","null");
	    	
	    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
	    	System.err.println("Found"); 	
	    	Objekte	objekt1=new Objekte();
			 objekt1.setId(null);
			 Strassen strasse=(Strassen)baseObject;
				objekt1.setStrasse(strasse);
           objekt1.setObjhausnummer(strasse.getStrname());
           return new ObjektPanel(componentId, responsepage,pars,breadCrumbModel, new EntityModel<Objekte>(objekt1));	
	    }
	    
		pars1=new PageParameters()
    	.add("eigtid","null")
		.add("strid","not null");
	//	System.err.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"+pars);
    //	System.exit(5);
    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
    	System.err.println("Found"); 	
    	Personen	person=new Personen();
		 person.setId(null);
		 Strassen strasse=(Strassen)baseObject;
			person.setStrasse(strasse);
       person.setEigtHausnummer(strasse.getStrname());
       return new PersonPanel(componentId, responsepage,pars,breadCrumbModel, new EntityModel<Personen>(person));	
    }
    
	    
	    	}
		
		
	return null;	
	}
	
	
	

	}	    	        
	

