package braunimmobilien.bootstrap.webapp.pages.angebot;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.Page;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.collections.MicroMap;
import org.apache.wicket.util.string.interpolator.MapVariableInterpolator;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Kondition;
import braunimmobilien.model.Angstatus;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.KonditionManager;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.service.AngebotExistsException;

import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.markup.html.PackageResourceGuard;
import org.apache.wicket.markup.html.link.PopupSettings;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.wicket.request.Url;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import org.apache.wicket.request.cycle.RequestCycle;

import java.util.Map;
import java.util.Iterator;
import java.io.Serializable;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;

import braunimmobilien.sitemesh.itext.utils.*;

import java.io.File;

import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.resource.IResource.Attributes;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import braunimmobilien.bootstrap.webapp.pages.tree.MyBeginnersTreePage;
import org.apache.cocoon.pipeline.Pipeline;
import org.apache.cocoon.optional.pipeline.components.sax.jaxb.JAXBGenerator;
import org.apache.cocoon.optional.pipeline.components.sax.jaxb.GenericType;
import org.apache.cocoon.pipeline.NonCachingPipeline;
import org.apache.cocoon.sax.SAXPipelineComponent;
import org.apache.cocoon.sax.component.XMLGenerator;
import org.apache.cocoon.sax.component.SchemaProcessorTransformer;
import org.apache.cocoon.sax.component.XSLTTransformer;
import org.apache.cocoon.sax.component.XIncludeTransformer;
import org.apache.cocoon.sax.component.XMLSerializer;
import org.apache.fop.apps.MimeConstants;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.net.URL;

import org.apache.cocoon.optional.pipeline.components.sax.fop.FopSerializer;
public class AngebotPage extends BasePage {
    @SpringBean
    private static AngebotManager angebotManager;
    @SpringBean
    private AngstatusManager angstatusManager;
    @SpringBean
    private KonditionManager konditionManager;
    private final Page responsePage;
    private  static AngebotPage here=null;
   
    
    
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
    /**
     * Constructor user to create a new user
     *
     * @param responsePage page to navigate to after this page completes its work
     */
    public AngebotPage(Page responsePage) {
        this(responsePage, new Angebot());
        here=this;
    }
    
    
    public AngebotPage(PageParameters pars) {
    	super(pars);
    	here=this;
    	String responsehref=pars.get("responsehref").toString();
    	String angnr=pars.get("angnr").toString();
    	Angebot angebot=angebotManager.get(angnr);
    	responsePage=new MyBeginnersTreePage();
        // Create and add the form
        EditForm form = new EditForm("angebot-form", angebot,makeChoicesAngstatus,makeChoicesKondition) {
            protected void onSave(Angebot angebot) {
                onSaveAngebot(angebot);
            }

            protected void onCancel() {
                onCancelEditing();
            }

            protected void onDelete(Angebot angebot) {
                onDeleteAngebot(angebot);
            }
        };
        add(form);
    }
    
    /**
     * Constructor used to edit an user
     *
     * @param responsePage page to navigate to after this page completes its work
     * @param user     user to edit
     */
    public AngebotPage(final Page responsePage, Angebot angebot) {
    	super(new PageParameters());
        this.responsePage = responsePage;
        here=this;
        // Create and add the form
     
        EditForm form = new EditForm("angebot-form", angebot,makeChoicesAngstatus,makeChoicesKondition) {
            protected void onSave(Angebot angebot) {
                onSaveAngebot(angebot);
            }

            protected void onCancel() {
                onCancelEditing();
            }

            protected void onDelete(Angebot angebot) {
                onDeleteAngebot(angebot);
            }
        };
        add(form);
    }
    
    /**
     * Listener method for save action
     *
     * @param user user bean
     */
    protected void onSaveAngebot(Angebot angebot) {
        try {
        	
        	System.err.println(angebot.getId());
        	Angebot angebot1=angebotManager.get(angebot.getId());
           angebot1.setAngstatus(angstatusManager.get(angebot.getAngstatus().getId()));
        	angebotManager.saveAngebot(angebot1);
        } catch (AngebotExistsException uee) {
            uee.printStackTrace();
            // TODO: show error message on form
        }

        String message = MapVariableInterpolator.interpolate(getLocalizer().getString("angebot.saved", this),
                new MicroMap<String, String>("angnr", angebot.getId()));
        getSession().info(message);
        FeedbackPanel feedback = (FeedbackPanel) responsePage.get("feedback");
        feedback.setVisible(true);
        feedback.setEscapeModelStrings(true);
        setResponsePage(new AngebotList());
    
    }

    /**
     * Listener method for delete action
     *
     * @param user user bean
     */
    protected void onDeleteAngebot(Angebot angebot) {
        angebotManager.removeAngebot(angebot.getId().toString());

        String message = MapVariableInterpolator.interpolate(getLocalizer().getString("angebot.deleted", this),
                new MicroMap<String, String>("angnr", angebot.getId()));
        getSession().info(message);

        responsePage.get("feedback").setVisible(true);
      //  setRedirect(true);
        setResponsePage(responsePage);
    }

    /**
     * Lister method for cancel action
     */
    private void onCancelEditing() {
        setResponsePage(responsePage);
    }

    /**
     * Subclass of Form used to edit an user
     *
     * @author ivaynberg
     */
    private static abstract class EditForm extends Form {
    	 IModel<Angebot> iangebot = new AbstractReadOnlyModel<Angebot>()
    		        {
    		            @Override
    		            public Angebot getObject()
    		            {
    		             return (Angebot)EditForm.this.getDefaultModelObject();  
    		               
    		            }

    		        };
    	/**
         * Convenience method that adds and prepares a form component
         *
         * @param fc    form component
         * @param label IModel containing the string used in ${label} variable of
         *              validation messages
         */
        private void add(FormComponent fc, IModel<String> label) {
          
            super.add(fc);
           
            fc.setLabel(label);
            
            add(new ComponentFeedbackPanel(fc.getId() + "-feedback", fc));
        }

        /**
         * Constructor
         *
         * @param id   component id
         * @param user User object that will be used as a form bean
         */
        public EditForm(String id, Angebot angebot, IModel<List<? extends Angstatus>> makeChoicesAngstatus, IModel<List<? extends Kondition>> makeChoicesKondition) {
            /*
             * We wrap the user bean with a CompoundPropertyModel, this allows
             * us to easily connect form components to the bean properties
             * (component id is used as the property expression)
             */
            super(id, new CompoundPropertyModel<Angebot>(angebot));
          
            add(new DropDownChoice<Angstatus>("angstatus", makeChoicesAngstatus,new ChoiceRenderer<Angstatus>("statustext","id")),new ResourceModel("angebot.angstatus"));
            add(new RequiredTextField("id"), new ResourceModel("angebot.id"));
            add(new RequiredTextField("angkurzbeschreibung"), new ResourceModel("angebot.angkurzbeschreibung"));
              add(new RequiredTextField("anglagebeschreibung"), new ResourceModel("angebot.anglagebeschreibung"));
                add(new RequiredTextField("anganz"), new ResourceModel("angebot.anganz"));
                  add(new RequiredTextField("angaufdatum"), new ResourceModel("angebot.angaufdatum"));
                  add(new DropDownChoice<Kondition>("kondition", makeChoicesKondition,new ChoiceRenderer<Kondition>("kontext","id")),new ResourceModel("angebot.kondition"));
            add(new Button("save", new Model<String>("Save")) {
                public void onSubmit() {
                    onSave((Angebot) getForm().getModelObject());
                }
            });

            Button delete = new Button("delete", new Model<String>("Delete")) {
                public void onSubmit() {
                    onDelete((Angebot) getForm().getModelObject());
                }
            };

            if (angebot.getId() == null) {
                delete.setVisible(false);
                delete.setEnabled(false);
            }
            add(delete);

           
            add(new Button("cancel", new Model<String>("Cancel")) {
                public void onSubmit() {
                    onCancel();
                }
            }.setDefaultFormProcessing(false));

        	
            
Link pdfLink=new Link("printPdf",iangebot) {
    public void onClick() {
    Angebot angebot = (Angebot) getModelObject();
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
add(pdfLink);
 



 ResourceLink cocoonLink=null;
   
    
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	OutputStream outputStream;
    	File file=null;
    	PopupSettings popupSettings = new PopupSettings(PopupSettings.RESIZABLE |    PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
    	try{
		Pipeline<SAXPipelineComponent> pipeline = new NonCachingPipeline<SAXPipelineComponent>();
	
 

		  Angebot angebot1 = new Angebot();
		  angebot1.setId(angebot.getId());
		  angebot1.setAnganz(angebot.getAnganz());
		  angebot1.setAngaufdatum(angebot.getAngaufdatum());
		  angebot1.setAngkurzbeschreibung(angebot.getAngkurzbeschreibung());
		  angebot1.setAnglagebeschreibung(angebot.getAnglagebeschreibung());
		  angebot1.setAngstatus(angebot.getAngstatus());
		  angebot1.setKondition(angebot.getKondition());
		  GenericType<Angebot> customer1= GenericType.toGenericType(angebot1);
		// GenericType<Customer> customer1= GenericType.toGenericType(customer);
		  pipeline = new NonCachingPipeline<SAXPipelineComponent>();
			pipeline.addComponent(new JAXBGenerator(customer1));
			pipeline.addComponent(new XSLTTransformer(new URL(getRequestCycle().getUrlRenderer().renderFullUrl(
                    Url.parse(urlFor(new PackageResourceReference(AngebotPage.class,"/test5.xslt"),null).toString())))));
			pipeline.addComponent(new XSLTTransformer(new URL(getRequestCycle().getUrlRenderer().renderFullUrl(
                   Url.parse(urlFor(new PackageResourceReference(AngebotPage.class,"/page2fo.xsl"),null).toString())))));  
			pipeline.addComponent(new SchemaProcessorTransformer(new URL(getRequestCycle().getUrlRenderer().renderFullUrl(
                  Url.parse(urlFor(new PackageResourceReference(AngebotPage.class,"/fop.xsd"),null).toString())))));
			pipeline.addComponent(new FopSerializer());
		//	pipeline.addComponent(new XMLSerializer());
		
			pipeline.setup(baos);
			pipeline.execute();
	
   

			 ByteArrayResource pdfresource=	new ByteArrayResource(MimeConstants.MIME_PDF, baos.toByteArray(),"angebot.pdf"){
				@Override
				 protected void configureResponse(ResourceResponse res,Attributes attr){
					res.setContentDisposition(ContentDisposition.INLINE);
				}} ;
			cocoonLink=new ResourceLink("cocoonPdf",pdfresource);
			cocoonLink.setPopupSettings(popupSettings); 
    	}
		catch(Exception ex){
			System.err.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"+ex);
			ex.printStackTrace();
			}
		  	
add(cocoonLink);

        }

        /**
         * Callback for cancel button
         */
        protected abstract void onCancel();

        /**
         * Callback for delete button
         *
         * @param user user bean
         */
        protected abstract void onDelete(Angebot angebot);

        /**
         * Callback for save button
         *
         * @param user user bean
         */
        protected abstract void onSave(Angebot angebot);
   
    }
    
   public class  LoadableAngebotModel extends LoadableDetachableModel{
   private static final long serialVersionUID = 1L;
    private Serializable id;
    Angebot angebot;
   protected Object load(){
	   
	   if(id==null){
		   angebot=new Angebot();
		   return  angebot;}
	   else {
		    angebotManager.get(id.toString());
		   
		   return angebotManager.get(id.toString());}
	   }
   public LoadableAngebotModel(Serializable id){
	   super();
	   id=id;   
   }   
	public void detach(){
		angebotManager.save(angebot);
	}   
   }
    }
