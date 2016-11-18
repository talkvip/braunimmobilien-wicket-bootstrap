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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Index page for the bread crumb example.
 * 
 * @author Eelco Hillenius
 */
public class IndexBootstrap extends BasePage
{
	
	private final FeedbackPanel feedback=new FeedbackPanel("feedback");
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
static Logger logger = LoggerFactory.getLogger(IndexBootstrap.class);





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
				if (firstPanel==null) {
					error("no panel defined");
					firstPanel=new ErrorPanel("panel",BraunHomePage.class, pageparameters,breadCrumbBar);}
			add(firstPanel);
			breadCrumbBar.setActive(firstPanel);
	 }

public IndexBootstrap(Class responsepage,PageParameters pageparameters){
	super(pageparameters);
	
	BreadCrumbBar breadCrumbBar = new BreadCrumbBar("breadCrumbBar");
	add(breadCrumbBar);
	 add(feedback);
	 BreadCrumbPanel firstPanel=null;
	
	 logger.debug("IndexBootstrap init "+responsepage.getSimpleName()+ "  " +pageparameters);
	 
	 if((responsepage.getSimpleName().equals("PersonTree"))&&(firstPanel==null)){
		
		 PageParameters pars2=new PageParameters()
					.add("telefon","not null")
					.add("kundennr","not null")
					.add("eigtid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
			if(firstPanel==null)			firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
					}
					  pars2=new PageParameters()
								.add("kundennr","null")
								.add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
									if(firstPanel==null)						firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
					 pars2=new PageParameters()
							.add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
									if(firstPanel==null)									firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
			PageParameters pars3=new PageParameters()
					.add("telefon","not null")
					.add("eigtid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars3,true)) {
						if(firstPanel==null)			firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
					}	
					pars3=new PageParameters()
							.add("telefon","not null")
							.add("eigtid","null");
							if	(MaklerFlowUtility.fits(pageparameters,pars3,true)) {
								if(firstPanel==null)			firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
					 PageParameters pars4=new PageParameters()
							 .add("telefon","not null")
							 .add("objid","not null")
					 .add("eigtid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
							if(firstPanel==null)		firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
						}	
						 pars4=new PageParameters()
								 .add("objid","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
								if(firstPanel==null)		firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
							 pars4=new PageParameters()
									 .add("nachweisid","not null")
							 .add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
									if(firstPanel==null)		firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
						PageParameters pars5=new PageParameters()
								 .add("nachweisid","not null")
								 .add("telefon","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars5,true)) {
								if(firstPanel==null)		firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
							PageParameters pars6=new PageParameters()
									.add("telefon","not null")
									 .add("objid","not null")
									 .add("nachweisid","not null")
							 .add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
									if(firstPanel==null)			firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
								PageParameters pars7=new PageParameters()
										.add("telefon","not null")
										 .add("objid","not null")
										 .add("scoutid","not null")
								 .add("eigtid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
										if(firstPanel==null)			firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
									}	
									PageParameters pars8=new PageParameters()
											.add("telefon","not null")
											 .add("scoutid","not null")
									 .add("eigtid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
											if(firstPanel==null)			firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);	
										}
										pars8=new PageParameters()
									.add("kundennr","not null")
										 .add("eigtid","not null");
											if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
												if(firstPanel==null)				firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
											}	
											pars8=new PageParameters()
													.add("objid","null")
														 .add("eigtid","not null");
															if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
																if(firstPanel==null)				firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar);	
															}	
	 }
	
	 if((responsepage.getSimpleName().equals("ScoutTree"))&&(firstPanel==null)){
			
		 PageParameters 	pars2=new PageParameters()
					.add("kundennr","not null")
			    	.add("scoutid","not null")
			    	.add("eigtid", "not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars2,false)) {
						
				if (firstPanel==null)	firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);
					}
		 
		 
		pars2=new PageParameters()
					.add("scoutid","not null")
					.add("objid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars2,false)) {
						if(firstPanel==null)			firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
					}
					
					pars2=new PageParameters()
							.add("scoutid","not null")
							.add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars2,false)) {
								if(firstPanel==null)								firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
					
							pars2=new PageParameters()
							    	.add("scoutid","not null")
							    	.add("eigtid", "null");
							if	(MaklerFlowUtility.fits(pageparameters,pars2,false)) {
										
								if (firstPanel==null)	firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar);
									}
							
							pars2=new PageParameters()
							    	.add("scoutid","not null")
							    	.add("objid", "null");
							if	(MaklerFlowUtility.fits(pageparameters,pars2,false)) {
										
								if (firstPanel==null)	firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar);
									}
						
							
							pars2=new PageParameters()
								.add("scoutid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars2,false)) {		
									if(firstPanel==null)				firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}		
					
					
								  
										

														
												
																
											
											
											
	 }
	
	 
	 if((responsepage.getSimpleName().equals("KundeTree"))&&(firstPanel==null)){
		 PageParameters pars1=new PageParameters()
				 .add("kundennr","not null");
		 		
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				if(firstPanel==null)				firstPanel = new KundePanel("panel",responsepage, pageparameters,breadCrumbBar);	
			}
		 
			 pars1.add("nachweisid","null");
			 		
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					if(firstPanel==null)			firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
				}
			
			PageParameters pars2=new PageParameters()
					 .add("kundennr","not null")
			 .add("nachweisid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
					if(firstPanel==null)			firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
				}
				
				
				
				 PageParameters pars3=new PageParameters()
						 .add("kundennr","not null")
				 .add("eigtid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars3,true)) {
						if(firstPanel==null)		firstPanel = new PersonPanel("panel",responsepage, pageparameters,breadCrumbBar);	
					}	
				
					 PageParameters pars4=new PageParameters()
							 .add("kundennr","not null")
							 .add("objid","not null")
					 .add("eigtid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars4,true)) {
							if(firstPanel==null)			firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
						}	
						PageParameters pars5=new PageParameters()
								 .add("kundennr","not null")
								 .add("nachweisid","not null")
						 .add("eigtid","not null");
							if	(MaklerFlowUtility.fits(pageparameters,pars5,true)) {
								if(firstPanel==null)		firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);	
							}	
							PageParameters pars6=new PageParameters()
									 .add("kundennr","not null")
									 .add("objid","not null")
									 .add("nachweisid","not null")
							 .add("eigtid","not null");
								if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
									if(firstPanel==null)		firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
								}	
								PageParameters pars7=new PageParameters()
										 .add("kundennr","not null")
										 .add("objid","not null")
										 .add("scoutid","not null")
								 .add("eigtid","not null");
									if	(MaklerFlowUtility.fits(pageparameters,pars6,true)) {
										if(firstPanel==null)		firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);	
									}	
									PageParameters pars8=new PageParameters()
											 .add("kundennr","not null")
											 .add("scoutid","not null")
									 .add("eigtid","not null");
										if	(MaklerFlowUtility.fits(pageparameters,pars8,true)) {
											if(firstPanel==null)		firstPanel = new ScoutPanel("panel",responsepage, pageparameters,breadCrumbBar);	
										}	
	 }
	 
	 if((responsepage.getSimpleName().equals("AngebotTree"))&&(firstPanel==null)){
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
						firstPanel = new StrassenSuchePanel("panel",responsepage, pageparameters,breadCrumbBar);
						
					}
					 pars1=new PageParameters()
							 .add("eigtid","not null")
							 .add("objid","not null")
							 .add("angnr","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
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
	 if((responsepage.getSimpleName().equals("ObjektTree"))&&(firstPanel==null)){
		 PageParameters pars1=new PageParameters()
				 .add("objid","not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				
	firstPanel = new ObjektPanel("panel",responsepage, pageparameters,breadCrumbBar);
				
			}
			
			
			
			
			pars1.add("nachweisid","null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					
		firstPanel = new NachweisPanel("panel",responsepage, pageparameters,breadCrumbBar);
					
				}
			
			
				pars1=new PageParameters()
						.add("angnr","null")
						 .add("objid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						
			firstPanel = new AngebotPanel("panel",responsepage, pageparameters,breadCrumbBar);
						
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
		
	 if (firstPanel==null) {
			error("no panel defined");
			firstPanel=new ErrorPanel("panel",BraunHomePage.class, pageparameters,breadCrumbBar);}

			add(firstPanel);
			breadCrumbBar.setActive(firstPanel);
	 }



	

	
	
	

	}	    	        
	

