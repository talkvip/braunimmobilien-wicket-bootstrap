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

import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.model.StringResourceModel;

import braunimmobilien.model.Personen;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.BaseObject;
import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class StrassenPanel extends BreadCrumbPanel
{
	String subject = "no subject";
	String result = "no result";
	private String specialusage="";
	
	@SpringBean
	PersonManager personManager;
@SpringBean
ObjektManager objektManager;
@SpringBean
StrassenManager strassenManager;
	@SpringBean
	private  OrteManager orteManager;
	  static Logger logger = LoggerFactory.getLogger(StrassenPanel.class);
	private final class StrasseInput extends Form<Strassen>
	{
		/** test input string. */
		

		/**
		 * Construct.
		 * 
		 * @param id
		 *            The component id
		 */
		public StrasseInput(String id,final Class responsepage,final PageParameters pageparameters,final IModel<Strassen> strasse)
		{ super(id, new CompoundPropertyModel<Strassen>(strasse));
		 logger.debug("new StrassenPanel responsepage "+responsepage.getSimpleName()+" PageParameters "+pageparameters);
		 add(new TextField<String>("strname"));
		 add(new TextField<String>("strplz"));
		 add(new TextField<String>("strhausbereich"));
		 add(new TextField<String>("planquadrat"));
		 add(new CheckBox("merkmal"));
		 add(new Button("nextButton")
			{
			 @Override
				public void onSubmit()
				{logger.debug("nextButton StrassenPanel responsepage "+responsepage.getSimpleName()+" PageParameters "+pageparameters);
					activate(new IBreadCrumbPanelFactory()
					{
						@Override
						public BreadCrumbPanel create(String componentId,
							IBreadCrumbModel breadCrumbModel)
						{
							Strassen strasse=StrasseInput.this.getModelObject();
						try{
						Orte ort=orteManager.get(strasse.getOrt().getId());
						ort.addStrassen(strasse);
						strasse.setOrt(ort);
						strassenManager.save(strasse);}
						catch(Exception ex){

					    	pageparameters.add("error","Exception in StrassenPanel nextButton "+ex);
					    	  return new StrassenPanel(componentId, responsepage,pageparameters,breadCrumbModel,new EntityModel<Strassen>(strasse));
					
							
							
						}
						if(responsepage.getSimpleName().equals("AngebotTree")){
					    	PageParameters pars1=new PageParameters()
					    	.add("angnr","not null")
					    	.add("objid", "null");
					    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					    	pageparameters.add("strid", strasse.getId().toString());
					    	
					    	  return new ObjektPanel(componentId, responsepage,pageparameters,breadCrumbModel);
					    }
					    
					    
					    pars1=new PageParameters()
						    	.add("angnr","not null")
					   .add("eigtid", "null")

					   .add("objid", "not null")
					   .add("eigttypid", "not null");
					    
					    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					    	pageparameters.add("strid", strasse.getId().toString());
					    	
					    	  return new PersonPanel(componentId, responsepage,pageparameters,breadCrumbModel);
					    }
					            pars1=new PageParameters()
						    	.add("angnr","not null")
						    	.add("objid","not null");
						    	
						    	
						    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						    	pageparameters.add("strid", strasse.getId().toString());
						    	
						    	  return new ObjektPanel(componentId, responsepage,pageparameters,breadCrumbModel);
						    }
					    
					    
					    
						}
						if(responsepage.getSimpleName().equals("ObjektTree")){
							PageParameters	pars1=new PageParameters()
					    	.add("objid","null");
					    	
					    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
					    	pageparameters.add("strid", strasse.getId().toString());
					    	
					    	  return new ObjektPanel(componentId, responsepage,pageparameters,breadCrumbModel);
					    }
					    pars1=new PageParameters()
						    	.add("objid","not null");
						    	
						    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
						    	Objekte objekt=objektManager.get(new Long(pageparameters.get("objid").toString()));
						    	objekt.setStrasse(strasse);
						    	strasse.addObjekt(objekt);
						    	objekt.setObjhausnummer(strasse.getStrname());
						    	objektManager.save(objekt);
						    
						    	
						    	  return new PersonPanel(componentId, responsepage,pageparameters,breadCrumbModel);
						    }
					    
					    
						}
					    if(responsepage.getSimpleName().equals("PersonTree")){
					    	PageParameters pars1=new PageParameters()
					    	.add("eigtid","not null");
					    	
					    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
					    	pageparameters.add("strid", strasse.getId().toString());
					    	
					    	  return new PersonPanel(componentId, responsepage,pageparameters,breadCrumbModel);
					    }
					    
						pars1=new PageParameters()
						    	.add("eigtid","not null");
						    	
						    if	(MaklerFlowUtility.fits(pageparameters,pars1,false)) {
						    	Personen person=personManager.get(new Long(pageparameters.get("eigtid").toString()));
						    	person.setStrasse(strasse);
						    	strasse.addPerson(person);
						    	person.setEigtHausnummer(strasse.getStrname());
						    	personManager.save(person);
						    
						    	
						    	  return new PersonPanel(componentId, responsepage,pageparameters,breadCrumbModel);
						    }
					    
					    
					    
					    }
					    
	
					    	pageparameters.add("error","no action defined in StrassenPanel nextButton");
					    	  return new StrassenPanel(componentId, responsepage,pageparameters,breadCrumbModel,new EntityModel<Strassen>(strasse));
					    }				
					});
			 
			 
			 
				
				}
			});

			
		}
		

	}
	/**
	 * Construct.
	 * 
	 * @param id
	 * @param breadCrumbModel
	 */
	public StrassenPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel,final IModel<Strassen> strasse)
	{
		super(id, breadCrumbModel);
		 if(pageparameters.getPosition("error")>0) {error(pageparameters.get("error").toString());
		 pageparameters.remove("error");}
		 if(responsepage.getSimpleName().equals("AngebotTree")){
		    	PageParameters pars1=new PageParameters()
		    			.add("objid","null")
			    	.add("angnr","not null");
			    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("land",this,null)).getObject()+"/"+(new StringResourceModel("ort",this,null)).getObject()+"/"+(new StringResourceModel("strasse",this,null)).getObject();		
						 subject=(new StringResourceModel("strassenew",this,null)).getObject();
			
			    	result=pageparameters.get("angnr").toString()+"/"+strasse.getObject().getOrt().getLand().getLandname()+"/"+strasse.getObject().getOrt().getOrtname()+"/null";
						
			    }
		 }
		Link pdfLink=new Link("cancelButton") {
		    public void onClick() {
		   this.setResponsePage(responsepage,pageparameters);

		    }
		    };
		add(pdfLink);
		add(new Label("result", result));
		StrasseInput form=new StrasseInput("form",responsepage,pageparameters,strasse);
		form.add(new Label("search", subject));
		add(form);
	}
public StrassenPanel(final String id, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
		add(new StrasseInput("form",null,null,new EntityModel<Strassen>(new Strassen())));
	}
	/**
	 * @see org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant#getTitle()
	 */
	@Override
	public IModel<String> getTitle()
	{
		return Model.of((new StringResourceModel("street.searchshort",this,null)).getObject());
	}
	
	public BreadCrumbPanel checkActionBreadcrumb(String componentId,
			IBreadCrumbModel breadCrumbModel, final Class responsepage,final PageParameters pageparameters,BaseObject object){
		MaklerFlowUtility flow=new MaklerFlowUtility();
		return flow.searchBreadcrumb(this,componentId,breadCrumbModel,responsepage,pageparameters,object);		
	}
	public BreadCrumbPanel makeFlowActionBreadcrumbStrasse(String componentId,IBreadCrumbModel breadCrumbModel,final Class responsepage,final PageParameters pars, BaseObject baseObject){
		
		if(responsepage.getSimpleName().equals("AngebotTree")){
	    	PageParameters pars1=new PageParameters()
	    	.add("angnr","not null")
	    	.add("objid","null");
	    if	(MaklerFlowUtility.fits(pars,pars1,true)) {
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
	 	    	Objekte objekt1=objektManager.get(new Long(pars.get("objid").toString()));
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
				    		 Personen person=personManager.get(new Long(pars.get("eigtid").toString()));
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
