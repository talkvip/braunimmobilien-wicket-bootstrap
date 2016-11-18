package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Eigtstatus;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.webapp.person.SynchronizeContacts;
import braunimmobilien.webapp.person.TelefonListModel;
import braunimmobilien.webapp.person.TelefonPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationMessage;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import braunimmobilien.webapp.person.*;

public abstract class AbstractPersonInput  extends  Form{
	
	 private static final String SCOPE[] = new String[]{"https://www.google.com/m8/feeds","https://www.googleapis.com/auth/calendar","https://mail.google.com/"};
		
		private static final String SCOPECALENDAR = "https://www.googleapis.com/auth/calendar";
		  // Check https://developers.google.com/gmail/api/auth/scopes for all available scopes
		  private static final String APP_NAME = "braunimmobiliencalendar";
		  // Email address of the user, or "me" can be used to represent the currently authorized user.
		  private static final String USER = "wichtigtuer.braun@gmail.com";
		  // Path to the client_secret.json file downloaded from the Developer Console
		  private static final String CLIENT_SECRET_PATH = "/home/braun/project/calendarclient/calendarclient_secret.json";

		  private static GoogleClientSecrets clientSecrets;
		  private String customCSS = null;
			 private boolean customEnabled = true;
		  
	/*	  @SpringBean
			ScoutManager scoutManager;
		@SpringBean
		ObjektManager objektManager;
		@SpringBean
		StrassenManager strassenManager;*/
		@SpringBean
		PersonManager personenManager;
			SynchronizeContacts 	synchronizecontacts=null;
		@SpringBean
	    private  EigentuemertypManager eigentuemertypManager;
			@SpringBean
			EigtstatusManager eigtstatusManager;

	 final Logger logger = LoggerFactory.getLogger(this.getClass());
	 transient GoogleAuthorizationCodeFlow flow=null;
	  transient	    HttpTransport httpTransport = new NetHttpTransport();
	  transient	    JsonFactory jsonFactory = new JacksonFactory();
	  private String accesskey;
	/* Button finish = new Button("finish", new Model<String>("Finish")) {
       public void onSubmit() {
       
       	}        	
       }
   };*/
	  
	  final    BootstrapButton finish=new BootstrapButton("finish",NextModel(),Buttons.Type.Default)
		{
			@Override
			public void onSubmit()
			{	info(new NotificationMessage(Model.of("Saved model " + getDefaultModelObject()), Model.of("error message title")));
	       	
	       	if (accesskey!=null&&!StringUtils.isEmpty(accesskey))
	       	{
	       	try{ 
	       	
	       	synchronizecontacts.setContactsService(accesskey); 
	       	
	       		        info(new NotificationMessage(Model.of("Google Services ContactsService set "), Model.of("info message title")));
	       		         }    
	       		   catch(Exception e){error("Google Service Error "+e);
	       	}  
			}
			}
			 @Override
			    protected void onComponentTag(ComponentTag tag) {
			        super.onComponentTag(tag);
			        if (customCSS != null)
			            tag.put("class", customCSS);
			    }
		
			  @Override
			    public boolean isEnabled() {
		        return super.isEnabled() && customEnabled;
			    }
		
		};
	  
	  
	  
   final    BootstrapButton onNext=new BootstrapButton("nextButton",NextModel(),Buttons.Type.Default)
	{
		@Override
		public void onSubmit()
		{onNext();
		}
		 @Override
		    protected void onComponentTag(ComponentTag tag) {
		        super.onComponentTag(tag);
		        if (customCSS != null)
		            tag.put("class", customCSS);
		    }
	
		  @Override
		    public boolean isEnabled() {
	        return super.isEnabled() && customEnabled;
		    }
	
	};
	
   final    BootstrapButton onLocation=new BootstrapButton("locationButton",LocationModel(),Buttons.Type.Default)
	{
		@Override
		public void onSubmit()
		{onLocation();
		}
		 @Override
		    protected void onComponentTag(ComponentTag tag) {
		        super.onComponentTag(tag);
		        if (customCSS != null)
		            tag.put("class", customCSS);
		    }
	
		  @Override
		    public boolean isEnabled() {
	        return super.isEnabled() && customEnabled;
		    }
	
	};        	    

	
	final Link callGoogleMail=	    new Link("callGoogleMail"){   
			public void onClick() {
				 String url1="http://localhost:8080";
			
			try{	  
			
	
       		            synchronizecontacts=new SynchronizeContacts();
				 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler(synchronizecontacts.getUrl()));}
				 catch(Exception e){error("call google error "+e);}
			}
			};
		private Boolean contactsNowProperty=new Boolean(true);
		
		final CheckBox contactsNow=new CheckBox("contactsNow",new PropertyModel<Boolean>(this,"contactsNowProperty"));
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
   	      	PopupSettings googlePopupSettings = new PopupSettings(PopupSettings.RESIZABLE |
		        PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);  
   	        private TelefonListModel telefone = new TelefonListModel();
   	    
	TextField accessKey=new TextField("accesskey",new PropertyModel<String>(this, "accesskey"));
		/**
		 * Construct.
		 * 
		 * @param id
		 *            The component id
		 */
		public AbstractPersonInput(String id,final boolean withNext,final boolean withLocation,final IModel<Personen> objekte)
		{
			
			super(id, new CompoundPropertyModel(objekte));
			 
			logger.debug("vor telefone set");
			 try{
			 if((((Personen) AbstractPersonInput.this.getDefaultModelObject()).getEigtTelefone()!=null)&&(((Personen)AbstractPersonInput.this.getDefaultModelObject()).getEigtTelefone().length()>0)){
			 telefone = new TelefonListModel(((Personen)AbstractPersonInput.this.getDefaultModelObject()).getEigtTelefone());}
			else{telefone=new TelefonListModel();}
			
			 logger.debug("telefone set"+telefone+" "+((Personen)AbstractPersonInput.this.getDefaultModelObject()).getEigtTelefone());
			 }catch(Exception e){
			    error("EigtTel nicht in Ordnung "+e);
			   logger.debug("error telefone set"+e);
			       	    telefone=new TelefonListModel();
			       	    }
			
			TelefonPanel telefonPanel=new TelefonPanel("telefone",telefone);	
			//	telefonPanel.setOutputMarkupId(true);
			telefonPanel.setVisible(true);
			add(telefonPanel);
		add(accessKey);
		   contactsNow.add(new AjaxFormComponentUpdatingBehavior("onchange")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {	
            if(!contactsNowProperty){
            callGoogleMail.setVisible(false);
		finish.setVisible(false);
            accessKey.setVisible(false);
            target.add(callGoogleMail);
             target.add(finish);
             target.add(accessKey);
            }
              else{
            callGoogleMail.setVisible(true);
		finish.setVisible(true);
            accessKey.setVisible(true);
            target.add(callGoogleMail);
             target.add(finish);
             target.add(accessKey);
            }
            }
        });
      
         callGoogleMail.setOutputMarkupPlaceholderTag(true);
        accessKey.setOutputMarkupPlaceholderTag(true);
		add(callGoogleMail);
		callGoogleMail.setPopupSettings(googlePopupSettings);
		add(finish);
		add(new Label("id"));
		 add(new Label("strasse.id"));	
		 add(contactsNow);		
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
		//  add(new BootstrapCheckbox("eigtAktuell", null, Model.of("Checkbox demo")).setRequired(true));
		 add(new CheckBox("eigtAktuell").setRequired(true));
	//	   add(new BootstrapSelect<Eigtstatus>("eigtstatus",makeChoicesEigtstatus,new ChoiceRenderer<Eigtstatus>("eigt_status_text","id")));
   
	 add(new DropDownChoice<Eigtstatus>("eigtstatus",makeChoicesEigtstatus,new ChoiceRenderer<Eigtstatus>("eigt_status_text","id")).setRequired(true));
	
		 final  DateTextField  eigtaufDatum= new DateTextField("eigtaufDatum",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 eigtaufDatum.setRequired(true);
		 add(eigtaufDatum);
		 final  DateTextField  eigtletztDatum= new DateTextField("eigtletztKontakt",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 add(eigtletztDatum);
		 final  DateTextField  eigtVorlage= new DateTextField("eigtVorlage",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
		 add(eigtVorlage);
		 
		
	
		 add(new Button("backButton")
	  		{
	  			@Override

	  			public void onSubmit()
	  			{onBack();
	  			}
	  		}); 
	   
	   onNext.setOutputMarkupPlaceholderTag(true);
		add(onNext);
		 onLocation.setOutputMarkupPlaceholderTag(true);
			add(onLocation);
	    
	    add(new Button("cancelButton")
	  		{
	  			@Override

	  			public void onSubmit()
	  			{onCancel();
	  			}
	  		}.setDefaultFormProcessing(false) ); 	    
		}
		 public abstract IModel<String> LocationModel();
		 public abstract IModel<String> NextModel();
		 public abstract IModel<String> CancelModel();
		 public abstract IModel<String> BackModel();
		 public abstract void onLocation();
		 public abstract void onNext();
		 public abstract void onCancel();
		 public abstract void onBack();	
			
	protected	 void savePerson(PageParameters pars){
				Personen person=(Personen)AbstractPersonInput.this.getDefaultModelObject();
				logger.debug("inspect person after submit "+person);
//				person.setEigtAktuell(true);
				Personen	person1=null;
				try{
				
				logger.debug("inspect person after submit "+telefone.getTelefonemodel().getTelefonList().size()+"  "+telefone.getTelXml()+"  "+pars);

								if(telefone.getTelefonemodel().getTelefonList().size()>0) person.setEigtTelefone(telefone.getTelXml());
							logger.debug("inspect set new EigtTelefone");
						  
									
							
								if((person.getEigtTel()!=null)&&((person.getEigtTel().contains("www.google.com"))&&(synchronizecontacts!=null))){
								logger.debug("inspect synchronize find Contact");
							
											synchronizecontacts.findAndDeleteContact(((Personen)this.getDefaultModelObject()).getEigtTel());
								}
								
							if((person.getEigtTelefone()!=null)&&(person.getEigtTelefone().length()>0)&&(synchronizecontacts!=null)){
								logger.debug("inspect synchronize create new Contact");
							
							person.setEigtTel(synchronizecontacts.createAndInsertNewContact(person,telefone,"Testaufnahme","System Group: My Contacts"));
									}
						
							
							if (person.getId()!=null&&person.getId().longValue()>0){
						logger.debug("inspect old person");
				
					
							}
							else{
								
							//	Strassen strasse=strassenManager.get(person.getStrasse().getId());
							//	person.setStrasse(strasse);
								person.getStrasse().addPerson(person);	
					//			person.setEigtstatus(eigtstatusManager.get(((Personen)this.getDefaultModelObject()).getEigtstatus().getId()));
							}
							
						logger.debug("inspect save person");
							logger.debug("inspect0 finished save Person "+pars+"  "+person);
							person=personenManager.save(person);
						logger.debug("inspect1 finished save Person "+pars+"  "+person);
							pars.remove("eigtid");
							pars.add("eigtid",person.getId().toString());
					
								}
							catch(Exception e){
								
								logger.debug("inspect error set group membership");
								
								error("Fehler bei Synchronize Contacts : "+e);
							}
									if(pars.getPosition("telefon")>=0) pars.remove("telefon");
									else {pars.set("telefon", "on");};
					logger.debug("inspect finished save Person "+pars+"  "+person);
				}	
	
	
}
