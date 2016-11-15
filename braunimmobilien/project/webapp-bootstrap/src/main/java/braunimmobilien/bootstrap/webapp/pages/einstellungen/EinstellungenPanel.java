package braunimmobilien.bootstrap.webapp.pages.einstellungen;

import java.io.FileInputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.bootstrap.webapp.Configuration;
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

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import braunimmobilien.model.Angebot;
import braunimmobilien.service.AngebotManager;

import org.apache.cxf.common.util.StringUtils;
import org.apache.wicket.Application;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.MaskConverter;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.basic.Label;

import java.util.Arrays;

import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;
@SuppressWarnings("serial")
public class EinstellungenPanel extends Panel {
	 private static final String SCOPE[] = new String[]{"https://www.google.com/m8/feeds","https://www.googleapis.com/auth/calendar","https://mail.google.com/"};
	 @SpringBean
		Configuration configuration;
	private static final String SCOPECALENDAR = "https://www.googleapis.com/auth/calendar";
	  // Check https://developers.google.com/gmail/api/auth/scopes for all available scopes
	  private static final String APP_NAME = "braunimmobiliencalendar";
	  // Email address of the user, or "me" can be used to represent the currently authorized user.
	  private static final String USER = "wichtigtuer.braun@gmail.com";
	  // Path to the client_secret.json file downloaded from the Developer Console
	  private final String CLIENT_SECRET_PATH = configuration.getGoogleAuthenticationJson();

	  private static GoogleClientSecrets clientSecrets;
	  transient GoogleAuthorizationCodeFlow flow=null;
	  transient	    HttpTransport httpTransport = new NetHttpTransport();
	  transient	    JsonFactory jsonFactory = new JacksonFactory();
	  static Logger logger = LoggerFactory.getLogger(EinstellungenPanel.class);
	  
	  private class EinstellungenForm extends BootstrapForm
	{private String accesskey;
	
	
	
	 
	
	
	
	 public String getAccesskey() {
		return accesskey;
	}
	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	PopupSettings googlePopupSettings = new PopupSettings(PopupSettings.RESIZABLE |
		        PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
	   
	 final Link callGoogleMail=	    new Link("callGoogleMail"){   
			public void onClick() {
				 
				    String url1="http://localhost:8080";
				    try{

				    	  clientSecrets = GoogleClientSecrets.load(jsonFactory,   new FileReader(CLIENT_SECRET_PATH));
				    	 flow = new GoogleAuthorizationCodeFlow.Builder(
				    		        httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPE))
				    		        .setAccessType("online")
				    		        .setApprovalPrompt("auto").build();
				    	 String url = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
				    		        .build();
				    	    url1 = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
				    	            .build();
				    }
				    catch(Exception e){System.out.println("Exception "+e);
				    	        e.printStackTrace();	System.exit(1);}
				 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler(url1));
			}
			};
	
	
	
	
    public EinstellungenForm(String name)
	{
		super(name);
		// Dropdown for selecting locale
		add(new LocaleDropDownChoice("localeSelect"));
		logger.debug("CLIENT_SECRET_PATH "+CLIENT_SECRET_PATH);;
		add(new TextField("accesskey",new PropertyModel<String>(this, "accesskey")));
		
		add(callGoogleMail);
		callGoogleMail.setPopupSettings(googlePopupSettings);
		// Link to return to default locale
		add(new Link<Void>("defaultLocaleLink")
		{
			@Override
			public void onClick()
			{
				WebRequest request = (WebRequest)getRequest();
				setLocale(request.getLocale());
			}
		});

	
	/**
	 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
	 */
    Button finish = new Button("finish", new Model<String>("Finish")) {
        public void onSubmit() {
        	info(new NotificationMessage(Model.of("Saved model " + getDefaultModelObject()), Model.of("error message title")));
        	
        	if (accesskey!=null&&!StringUtils.isEmpty(accesskey))
        	{
        		 try{
        			 System.err.println(accesskey);
        			 GoogleTokenResponse response = flow.newTokenRequest(accesskey)
        		            .setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).execute();
        		        GoogleCredential credential = new GoogleCredential()
        		            .setFromTokenResponse(response);
        		        ContactsService contactsservice =new ContactsService(APP_NAME);
        		        contactsservice.setOAuth2Credentials(credential);
        		        WicketApplication app = (WicketApplication)Application.get();
        		        app.setContactsService( contactsservice);
        		        Calendar calendarservice=new Calendar.Builder(httpTransport, jsonFactory, credential).setApplicationName(APP_NAME).build();
        		        app.setCalendarService(calendarservice);
        		        Gmail gmailservice=new Gmail.Builder(httpTransport, jsonFactory, credential).setApplicationName(APP_NAME).build(); 
        		        app.setGmailService(gmailservice);
        		       
        		        info(new NotificationMessage(Model.of("Google Services  set "+calendarservice), Model.of("info message title")));
        	        	
        		 }
        	catch(Exception e){error("Google Service Error "+e);
        	e.printStackTrace();
        	}	       
        		        
        	}        	
        }
    };
    add(finish);}
	@Override
	public void onSubmit()
	{
		// Form validation successful. Display message showing edited model.
		info("Saved model " + getDefaultModelObject());
	}



private final class LocaleChoiceRenderer extends ChoiceRenderer<Locale>
{
	/**
	 * Constructor.
	 */
	public LocaleChoiceRenderer()
	{
	}

	/**
	 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getDisplayValue(Object)
	 */
	@Override
	public Object getDisplayValue(Locale locale)
	{
		return locale.getDisplayName(getLocale());
	}
}
public void setLocale(Locale locale)
{
	if (locale != null)
	{
		getSession().setLocale(locale);
	}
}
/**
 * Dropdown with Locales.
 */

private final class LocaleDropDownChoice extends BootstrapSelect<Locale>
{
	/**
	 * Construct.
	 * 
	 * @param id
	 *            component id
	 */
	public LocaleDropDownChoice(String id)
	{
		super(id, WicketApplication.LOCALES, new LocaleChoiceRenderer());

		// set the model that gets the current locale, and that is used for
		// updating the current locale to property 'locale' of FormInput
		setModel(new PropertyModel<Locale>(EinstellungenForm.this, "locale"));
	}

	/**
	 * @see org.apache.wicket.markup.html.form.DropDownChoice#onSelectionChanged(java.lang.Object)
	 */
	@Override
	public void onSelectionChanged(Locale newSelection)
	{
		// note that we don't have to do anything here, as our property
		// model allready calls FormInput.setLocale when the model is
		// updated

		// force re-render by setting the page to render to the bookmarkable
		// instance, so that the page will be rendered from scratch,
		// re-evaluating the input patterns etc
//		setResponsePage(EinstellungenPanel.class);
	}

	/**
	 * @see org.apache.wicket.markup.html.form.DropDownChoice#wantOnSelectionChangedNotifications()
	 */
	@Override
	protected boolean wantOnSelectionChangedNotifications()
	{
		// we want roundtrips when a the user selects another item
		return true;
	}
}

	}


/**
 * Sets locale for the user's session (getLocale() is inherited from Component)
 * 
 * @param locale
 *            The new locale
 */


public EinstellungenPanel(String name) {
	super(name);
		// Construct form and feedback panel and hook them up
		final NotificationPanel feedback = new NotificationPanel("feedback");
		add(feedback);
		add(new EinstellungenForm("EinstellungenForm"));
	}

private static class URLConverter implements IConverter<URL>
{
	public static final URLConverter INSTANCE = new URLConverter();

	public URL convertToObject(String value, Locale locale)
	{
		try
		{
			return new URL(value);
		}
		catch (MalformedURLException e)
		{
			throw new ConversionException("'" + value + "' is not a valid URL");
		}
	}

	public String convertToString(URL value, Locale locale)
	{
		return value != null ? value.toString() : null;
	}
}
}