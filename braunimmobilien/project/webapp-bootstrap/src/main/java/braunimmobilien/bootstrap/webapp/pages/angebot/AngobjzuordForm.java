package braunimmobilien.bootstrap.webapp.pages.angebot;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import java.sql.Date;
import org.apache.wicket.util.convert.converter.SqlDateConverter;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.extensions.yui.calendar.TimeField;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.collections.MicroMap;
import org.apache.wicket.util.string.interpolator.MapVariableInterpolator;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.AngobjzuordExistsException;
import braunimmobilien.service.AngebotExistsException;
import org.apache.wicket.request.http.WebResponse;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import org.apache.wicket.request.cycle.RequestCycle;
import java.util.Map;
import java.util.Iterator;
import org.apache.wicket.markup.html.link.Link;
import java.io.File;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import java.util.Locale;
public class AngobjzuordForm extends BasePage {
	@SpringBean
	private AngebotManager angebotManager;
    @SpringBean
    private AngobjzuordManager angobjzuordManager;
    @SpringBean
    private ObjektartManager objektartManager;
    @SpringBean
    private ObjektsuchManager objektsuchManager;
    private final BasePage responsePage;
    
   
    
    
    IModel<List<? extends Objektart>> makeChoicesObjektart = new AbstractReadOnlyModel<List<? extends Objektart>>()
  	        {
  	            @Override
  	            public List<Objektart> getObject()
  	            { List<Objektart> objektartlist=new  ArrayList<Objektart>(); 
  	            	
  	            	Iterator objektartiterator=objektartManager.getObjektartes().iterator();
  	            while(objektartiterator.hasNext()){
  	            	Objektart objektart=(Objektart)objektartiterator.next();
  	            	objektartlist.add(objektart);
  	            }
  	               
  	                return objektartlist;
  	            }

  	        };
  	      IModel<List<? extends Objektsuch>> makeChoicesObjektsuch = new AbstractReadOnlyModel<List<? extends Objektsuch>>()
  	  	        {
  	  	            @Override
  	  	            public List<Objektsuch> getObject()
  	  	            { List<Objektsuch> objektsuchlist=new  ArrayList<Objektsuch>(); 
  	  	            	
  	  	            	Iterator objektsuchiterator=objektsuchManager.getObjektsuchen().iterator();
  	  	            while(objektsuchiterator.hasNext()){
  	  	            	Objektsuch objektsuch=(Objektsuch)objektsuchiterator.next();
  	  	            	objektsuchlist.add(objektsuch);
  	  	            }
  	  	               
  	  	                return objektsuchlist;
  	  	            }

  	  	        };
    /**
     * Constructor user to create a new user
     *
     * @param responsePage page to navigate to after this page completes its work
     */
    public AngobjzuordForm(BasePage responsePage) {
        this(responsePage, new Angobjzuord());
    }
    
    
   
    
    /**
     * Constructor used to edit an user
     *
     * @param responsePage page to navigate to after this page completes its work
     * @param user     user to edit
     */
    public AngobjzuordForm(final BasePage responsePage, Angobjzuord angobjzuord) {
        super(new PageParameters());
    	this.responsePage = responsePage;

        // Create and add the form
        EditForm form = new EditForm("angobjzuord-form", angobjzuord,makeChoicesObjektart,makeChoicesObjektsuch) {
            protected void onSave(Angobjzuord angobjzuord) {
                onSaveAngobjzuord(angobjzuord);
            }

            protected void onCancel() {
                onCancelEditing();
            }

            protected void onDelete(Angobjzuord angobjzuord) {
                onDeleteAngobjzuord(angobjzuord);
            }
        };
        add(form);
    }
    
    /**
     * Listener method for save action
     *
     * @param user user bean
     */
    protected void onSaveAngobjzuord(Angobjzuord angobjzuord) {
        try { System.err.println("xxxxxxxxxxxxxxxxxxxx "+angobjzuord.getAngebot());
        if(angobjzuord.getId()==null){angobjzuord.getAngebot().addAngobjzuord(angobjzuord);} 
      Angebot angebot=  angebotManager.get(angobjzuord.getAngebot().getId());
      Iterator it=angebot.getAngobjzuords().iterator();
   
      while(it.hasNext()){
    	  Angobjzuord angobjzuord1=(Angobjzuord)it.next();
    	if(angobjzuord1.getId().longValue()==angobjzuord1.getId().longValue()) {
    		angobjzuord1.setAktuell(angobjzuord.getAktuell());
    		angobjzuord1.getObjekte().setMerkmal(angobjzuord.getObjekte().getMerkmal());
    		angobjzuord1.getObjekte().setObjaufnahme(angobjzuord.getObjekte().getObjaufnahme());
    		angobjzuord1.getObjekte().setObjektart(angobjzuord.getObjekte().getObjektart());
    		angobjzuord1.getObjekte().setObjektsuch(angobjzuord.getObjekte().getObjektsuch());
    		angobjzuord1.getObjekte().setObjhausnummer(angobjzuord.getObjekte().getObjhausnummer());
    		angobjzuord1.getObjekte().setObjinfo(angobjzuord.getObjekte().getObjinfo());
    		angobjzuord1.getObjekte().setObjletztkontakt(angobjzuord.getObjekte().getObjletztkontakt());
    		angobjzuord1.getObjekte().setObjvorlage(angobjzuord.getObjekte().getObjvorlage());
    		
    		
    		
    		
    		
    	} 
    	  
      }
      
      angebotManager.saveAngebot(angebot);
      //  angebotManager.saveAngebot(angobjzuord.getAngebot());
        } catch (AngebotExistsException uee) {
            uee.printStackTrace();
            System.err.println(uee.toString());
            // TODO: show error message on form
        }

        String message = MapVariableInterpolator.interpolate(getLocalizer().getString("angobjzuord.saved", this),
        new MicroMap<String, String>("id", angobjzuord.getObjekte().getObjletztkontakt()+"  "+angobjzuord.getId().toString()));
        getSession().info(message);
        FeedbackPanel feedback = (FeedbackPanel) responsePage.get("feedback");
        feedback.setVisible(true);
        feedback.setEscapeModelStrings(true);
        setResponsePage(responsePage);
       // setRedirect(true);
      //  setResponsePage(responsePage);
    }

    /**
     * Listener method for delete action
     *
     * @param user user bean
     */
    protected void onDeleteAngobjzuord(Angobjzuord angobjzuord) {
        angobjzuordManager.removeAngobjzuord(angobjzuord.getId().toString());

        String message = MapVariableInterpolator.interpolate(getLocalizer().getString("angobjzuord.deleted", this),
                new MicroMap<String, String>("zuordnr", angobjzuord.getId().toString()));
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
    	 IModel<Angobjzuord> iangobjzuord = new AbstractReadOnlyModel<Angobjzuord>()
    		        {
    		            @Override
    		            public Angobjzuord getObject()
    		            {
    		             return (Angobjzuord)EditForm.this.getDefaultModelObject();  
    		               
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
            // Add the component to the form
            super.add(fc);
            // Set its label model
            fc.setLabel(label);
            // Add feedback panel that will be used to display component errors
            add(new ComponentFeedbackPanel(fc.getId() + "-feedback", fc));
        }

        /**
         * Constructor
         *
         * @param id   component id
         * @param user User object that will be used as a form bean
         */
        public EditForm(String id, Angobjzuord angobjzuord, IModel<List<? extends Objektart>> makeChoicesObjektart, IModel<List<? extends Objektsuch>> makeChoicesObjektsuch) {
            /*
             * We wrap the user bean with a CompoundPropertyModel, this allows
             * us to easily connect form components to the bean properties
             * (component id is used as the property expression)
             */
            super(id, new CompoundPropertyModel<Angobjzuord>(angobjzuord));
            add(new CheckBox("aktuell"),new ResourceModel("angobjzuord.aktuell"));
            super.add(new Label("id"));
           add(new RequiredTextField("objekte.merkmal"), new ResourceModel("angobjzuord.merkmal"));
              super.add(new Label("objekte.objaufnahme"));
                add(new RequiredTextField("objekte.objhausnummer"), new ResourceModel("angobjzuord.objhausnummer"));
            
                
                DateTextField objaufnahmeTextField = new DateTextField("objekte.objletztkontakt",   new StyleDateConverter("S-", true)){
                	@Override
        			public Locale getLocale()
         			{
         				return new Locale("en");
         			}};
         			 add(objaufnahmeTextField, new ResourceModel("angobjzuord.objaufnahme"));
         			DatePicker datePicker = new DatePicker()
         			{
         				@Override
         				protected String getAdditionalJavaScript()
         				{
         					return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
         				}
         			};
         			datePicker.setShowOnFieldClick(true);
         			datePicker.setAutoHide(true);
         			objaufnahmeTextField.add(datePicker);
         			 add(new DateTimeField("objekte.objvorlage"), new ResourceModel("angobjzuord.objvorlage"));
       //         add(new RequiredTextField("objekte.objaufnahme"), new ResourceModel("angobjzuord.objaufnahme"));
          //      add(new RequiredTextField("objekte.objletztkontakt"), new ResourceModel("angobjzuord.objletztkontakt"));
           //       add(new RequiredTextField("objekte.objvorlage"), new ResourceModel("angobjzuord.objvorlage"));
                  add(new DropDownChoice<Objektart>("objekte.objektart", makeChoicesObjektart,new ChoiceRenderer<Objektart>("objartname","id")),new ResourceModel("angobjzuord.objektart"));
                  add(new DropDownChoice<Objektsuch>("objekte.objektsuch", makeChoicesObjektsuch,new ChoiceRenderer<Objektsuch>("suchtext","id")),new ResourceModel("angobjzuord.objektsuch"));
                  add(new Button("save", new Model<String>("Save")) {
                public void onSubmit() {
                    onSave((Angobjzuord) getForm().getModelObject());
                }
            });

            Button delete = new Button("delete", new Model<String>("Delete")) {
                public void onSubmit() {
                    onDelete((Angobjzuord) getForm().getModelObject());
                }
            };

            if (angobjzuord.getId() == null) {
                delete.setVisible(false);
                delete.setEnabled(false);
            }
            add(delete);

            /*
             * Notice the setDefaultFormProcessing(false) call at the end. This
             * tells wicket that when this button is pressed it should not
             * perform any form processing (ie bind request values to the bean).
             */
            add(new Button("cancel", new Model<String>("Cancel")) {
                public void onSubmit() {
                    onCancel();
                }
            }.setDefaultFormProcessing(false));

        	
            

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
        protected abstract void onDelete(Angobjzuord angobjzuord);

        /**
         * Callback for save button
         *
         * @param user user bean
         */
        protected abstract void onSave(Angobjzuord angobjzuord);
    }
}
