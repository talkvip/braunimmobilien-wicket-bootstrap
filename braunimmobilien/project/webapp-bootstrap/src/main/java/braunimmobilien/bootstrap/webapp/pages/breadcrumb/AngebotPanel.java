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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.cocoon.optional.pipeline.components.sax.fop.FopSerializer;
import org.apache.cocoon.optional.pipeline.components.sax.jaxb.GenericType;
import org.apache.cocoon.optional.pipeline.components.sax.jaxb.JAXBGenerator;
import org.apache.cocoon.pipeline.NonCachingPipeline;
import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.sax.SAXPipelineComponent;
import org.apache.cocoon.sax.component.SchemaProcessorTransformer;
import org.apache.cocoon.sax.component.XSLTTransformer;
import org.apache.fop.apps.MimeConstants;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.AbstractResource.ResourceResponse;
import org.apache.wicket.request.resource.IResource.Attributes;
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
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.extensions.wizard.WizardModel.ICondition;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotPage;
import braunimmobilien.model.Angstatus;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Kondition;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.service.KonditionManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.sitemesh.itext.utils.AngebotPdfBuilder;
import braunimmobilien.sitemesh.itext.utils.PdfFactory;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.http.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class AngebotPanel extends BreadCrumbPanel
{
	static Logger logger = LoggerFactory.getLogger(AngebotPanel.class);
	@SpringBean
	AngebotManager angebotManager;
	@SpringBean
	AngstatusManager angstatusManager;
	@SpringBean
	KonditionManager konditionManager;
	
	String subject = "no subject";
	String result = "no result";
	private String specialusage="";
	private final class ObjektInput extends Form<Angebot>
	
	
	{

		
		
		Link pdfLink=new Link("printPdf") {
		    public void onClick() {
		    Angebot angebot = (Angebot) ObjektInput.this.getModelObject();
		    File file = PdfFactory.createFile(new AngebotPdfBuilder(angebot));
		    ResourceStreamRequestHandler target = new   ResourceStreamRequestHandler(
		    		new FileResourceStream(file));
		    target.setContentDisposition(ContentDisposition.INLINE);
		 
		 target.setFileName("objekt-" + angebot.getId() + ".pdf");
		 RequestCycle.get().scheduleRequestHandlerAfterCurrent(target);    
		((WebResponse)getRequestCycle().get().getResponse()).setContentType("application/pdf");
			getRequestCycle().scheduleRequestHandlerAfterCurrent(target);

		    }
		    };
		
		final Link callCocoonPdfExposee=	    new Link("callCocoonPdfExposee"){   public void onClick() {
   	  
		 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("http://"+((WebRequest) getRequest()). getClientUrl().getHost()+"/controllerbraunimmobilienget.pdf?id=exposee&reqparam=3&name="+ObjektInput.this.getModelObject().getId()));
    }
    };
    
    final Link callCocoonPdfAngebot=	    new Link("callCocoonPdfAngebot"){  
 	   public void onClick() {
 		  
 		  
 		  getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("http://"+((WebRequest) getRequest()). getClientUrl().getHost()+"/controllerbraunimmobilienget.pdf?id=angebotobjektgeber&reqparam=3&name="+ObjektInput.this.getModelObject().getId()));
	    }
    };
    
		
		
		
		
		 IModel<List<? extends Angstatus>> makeChoicesAngstatus = new AbstractReadOnlyModel<List<? extends Angstatus>>()
		  	        {
		  	            @Override
		  	            public List<Angstatus> getObject()
		  	            { List<Angstatus> angstatuslist=new  ArrayList<Angstatus>(); 
		  	            	
		  	            	Iterator angstatusiterator=angstatusManager.getAngstatuses().iterator();
		  	            while(angstatusiterator.hasNext()){
		  	            	Angstatus angstatus=(Angstatus)angstatusiterator.next();
		  	            	angstatuslist.add(angstatus);
		  	            }
		  	               
		  	                return angstatuslist;
		  	            }

		  	        };
		  	      IModel<List<? extends Kondition>> makeChoicesKondition = new AbstractReadOnlyModel<List<? extends Kondition>>()
		  	  	        {
		  	  	            @Override
		  	  	            public List<Kondition> getObject()
		  	  	            { List<Kondition> konditionlist=new  ArrayList<Kondition>(); 
		  	  	            	
		  	  	            	Iterator konditioniterator=konditionManager.getKonditionen().iterator();
		  	  	            while(konditioniterator.hasNext()){
		  	  	            	Kondition kondition=(Kondition)konditioniterator.next();
		  	  	            	konditionlist.add(kondition);
		  	  	            }
		  	  	               
		  	  	                return konditionlist;
		  	  	            }

		  	  	        };
		  
		  	  	    public ObjektInput(String id,final Class responsepage,final PageParameters pageparameters,final IModel<Angebot> angebot)
		  			{
			super(id, new CompoundPropertyModel(angebot));
	

           add(new DropDownChoice<Angstatus>("angstatus", makeChoicesAngstatus,new ChoiceRenderer<Angstatus>("statustext","id")));
           add(new Label("id"));
           add(new RequiredTextField("angkurzbeschreibung"));
             add(new RequiredTextField("anglagebeschreibung"));
               add(new RequiredTextField("anganz"));
               add(new DateTextField("angaufdatum",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString())));
                 add(new DropDownChoice<Kondition>("kondition", makeChoicesKondition,new ChoiceRenderer<Kondition>("kontext","id")));
                 add(pdfLink);	
                 ResourceLink cocoonLink=null;
                 
                 
             	ByteArrayOutputStream baos = new ByteArrayOutputStream();
             	OutputStream outputStream;
             	File file=null;
ByteArrayResource pdfresource=	new ByteArrayResource(MimeConstants.MIME_PDF, baos.toByteArray(),"angebot.pdf");
             	PopupSettings popupSettings = new PopupSettings(PopupSettings.RESIZABLE |    PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
             try{
        		Pipeline<SAXPipelineComponent> pipeline = new NonCachingPipeline<SAXPipelineComponent>();
         	
          
         	
         	if(angebot.getObject().getId()!=null){
         		 Angebot angebot1 = new Angebot();
         		  angebot1.setId(angebot.getObject().getId());
         		  angebot1.setAnganz(angebot.getObject().getAnganz());
         		  angebot1.setAngaufdatum(angebot.getObject().getAngaufdatum());
         		  angebot1.setAngkurzbeschreibung(angebot.getObject().getAngkurzbeschreibung());
         		  angebot1.setAnglagebeschreibung(angebot.getObject().getAnglagebeschreibung());
         		  angebot1.setAngstatus(angebot.getObject().getAngstatus());
         		  angebot1.setKondition(angebot.getObject().getKondition());
         		  GenericType<Angebot> customer1= GenericType.toGenericType(angebot1);
         		// GenericType<Customer> customer1= GenericType.toGenericType(customer);
         		  pipeline = new NonCachingPipeline<SAXPipelineComponent>();
         			pipeline.addComponent(new JAXBGenerator(customer1));
         			pipeline.addComponent(new XSLTTransformer(new URL(getRequestCycle().getUrlRenderer().renderFullUrl(
                             Url.parse(urlFor(new PackageResourceReference(AngebotPanel.class,"/test5.xslt"),null).toString())))));
         			pipeline.addComponent(new XSLTTransformer(new URL(getRequestCycle().getUrlRenderer().renderFullUrl(
                            Url.parse(urlFor(new PackageResourceReference(AngebotPanel.class,"/page2fo.xsl"),null).toString())))));  
         			pipeline.addComponent(new SchemaProcessorTransformer(new URL(getRequestCycle().getUrlRenderer().renderFullUrl(
                           Url.parse(urlFor(new PackageResourceReference(AngebotPanel.class,"/fop.xsd"),null).toString())))));
         			pipeline.addComponent(new FopSerializer());
         		//	pipeline.addComponent(new XMLSerializer());
         		
         			pipeline.setup(baos);
         			pipeline.execute();
         	
            

         			 pdfresource=	new ByteArrayResource(MimeConstants.MIME_PDF, baos.toByteArray(),"angebot.pdf"){
         				@Override
         				 protected void configureResponse(ResourceResponse res,Attributes attr){
         					res.setContentDisposition(ContentDisposition.INLINE);
         				}} ;}
         			
            	}
         		catch(Exception ex){
logger.error("Error CocoonPipeline "+ex,ex);
         			
         			}
         		  cocoonLink=new ResourceLink("cocoonPdf",pdfresource);
         			cocoonLink.setPopupSettings(popupSettings); 	
         add(cocoonLink); 
                 add(callCocoonPdfExposee);
add(callCocoonPdfAngebot);
             	 add(new Label("search", subject));
                 
                 add(new Button("back")
			{
			 @Override
				public void onSubmit()
				{ 
				 try{
					
						   Angebot angebot=ObjektInput.this.getModelObject();
					  angebot.setId(pageparameters.get("angnr").toString());
						  	angebotManager.save(angebot);
				 this.setResponsePage(responsepage, pageparameters);
				 } 
				 catch(Exception ex){
					 
								 pageparameters.add("error", ex);
				 error(ex.toString());
				 logger.error("FalschesBack "+ex,ex);
				 System.exit(5);

				 }	
				}
			});
                 add(new Button("next")
     			{
     			 @Override
     				public void onSubmit()
     				{ 
     				 try{
     						   Angebot angebot=ObjektInput.this.getModelObject();
     					  angebot.setId(pageparameters.get("angnr").toString());
     						  	angebotManager.save(angebot);
     					
     				 
     						  	activate(new IBreadCrumbPanelFactory()
     							{
     								@Override
     								public BreadCrumbPanel create(String componentId,
     									IBreadCrumbModel breadCrumbModel)
     								{ if(responsepage.getSimpleName().equals("AngebotTree")){
     									 PageParameters pars3=new PageParameters()
     											 .add("angnr","not null");
     										if	(MaklerFlowUtility.fits(pageparameters,pars3,true)) {	
     											pageparameters.add("objid", "null");
     											return	 new StrassenSuchePanel(componentId,responsepage,pageparameters,breadCrumbModel);	
     										}
     								}
     									return null;
     								}
     							
     							}); 
     				 
     				 
     				 } 
     				 
     			
     				 catch(Exception ex){
     					 error(ex.toString());
     				 pageparameters.add("error", ex);
     				 }
     				}
     			});
                    
                 add(new Button("cancel")
     			{
     				@Override
     				public void onSubmit()
     				{
     					
     					setResponsePage(AngebotBreadcrumbPage.class);
     				}
     			}.setDefaultFormProcessing(false));        
		}
	}
	
	public AngebotPanel(final String id, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);

		String result = "no result";
	//	add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
		add(new Label("result", result));
	}
	
	
	public AngebotPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel, IModel<Angebot> angebot)
	{
		super(id, breadCrumbModel);
		String result=(new StringResourceModel("objekt.undefined",this,null)).getObject();
		
		if(angebot.getObject().getId()!=null)
		{
			result=angebot.getObject().getId();}
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
	//	add(new BreadCrumbPanelLink("linkToFirst", this, AngebotPanel.class));
		add(new Label("result", result));
		add(new ObjektInput("form",responsepage,pageparameters,angebot));
	}
	
	
	public AngebotPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
//		String result=(new StringResourceModel("objekt.undefined",this,null)).getObject();
		IModel<Angebot> entity=null;
		 if(pageparameters.getPosition("error")>0) {error(pageparameters.get("error").toString());
		 pageparameters.remove("error");}
		 if(responsepage.getSimpleName().equals("AngebotTree")){
			 PageParameters pars1=new PageParameters()
				    	.add("angnr","not null");
					 if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							specialusage= (new StringResourceModel("exposeeid",this,null)).getObject();
						if(angebotManager.exists(pageparameters.get("angnr").toString())){		
							 subject=(new StringResourceModel("exposeeold",this,null)).getObject();
						 result = pageparameters.get("angnr").toString();
						 entity=new EntityModel<Angebot>(Angebot.class,pageparameters.get("angnr").toString());	}
					
					 else{
						 subject=(new StringResourceModel("exposeenew",this,null)).getObject();
						 result = pageparameters.get("angnr").toString();
						 Angebot angebot=new Angebot();
						 entity=new EntityModel<Angebot>(angebot);	
							
						 
					 }
					 }
		 }
		 if(responsepage.getSimpleName().equals("ObjektTree")){
			 PageParameters pars1=new PageParameters()
					 .add("objid","not null")
				    	.add("angnr","not null");
					 if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("exposeeid",this,null)).getObject();
						if(angebotManager.exists(pageparameters.get("angnr").toString())){		
							 subject=(new StringResourceModel("exposeeold",this,null)).getObject();
						 result = pageparameters.get("objid").toString()+"/"+pageparameters.get("angnr").toString();
						 entity=new EntityModel<Angebot>(Angebot.class,pageparameters.get("angnr").toString());	}
					
					 else{
						 subject=(new StringResourceModel("exposeenew",this,null)).getObject();
						 result = pageparameters.get("objid").toString()+"/"+pageparameters.get("angnr").toString();
						 Angebot angebot=new Angebot();
						 angebot.setId(result);
						 entity=new EntityModel<Angebot>(angebot);	
						 
					 }
					 }
		 }
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
		
		add(new Label("result", result));
		add(new ObjektInput("form",responsepage,pageparameters,entity));
	}
	
	
	
	
	@Override
	public IModel<String> getTitle()
	{
		return Model.of(specialusage);
	}
}
