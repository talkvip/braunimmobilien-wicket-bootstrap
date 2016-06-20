package braunimmobilien.bootstrap.webapp.pages.auth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;

import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.model.Nutzer;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Xtyp;
import braunimmobilien.service.NutzerManager;
import braunimmobilien.util.ScoutUtil;
import braunimmobilien.bootstrap.webapp.MaklerSession;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

import java.util.Date;

import org.apache.wicket.event.IEvent;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelectConfig;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.Stack;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.UpdatableProgressBar;
import braunimmobilien.bootstrap.webapp.components.base.StateSelect;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.wicketstuff.annotation.mount.MountPath;

import braunimmobilien.model.Angebot;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapMultiSelect;

import org.apache.wicket.markup.html.form.Button;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Map;
import java.util.TimeZone;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;

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

import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxBootstrapTabbedPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.ClientSideBootstrapTabbedPanel;
import braunimmobilien.bootstrap.webapp.components.basecss.ButtonGroups;
import braunimmobilien.bootstrap.webapp.pages.WithoutBasePage;
import braunimmobilien.service.AngebotManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.wicket.spring.injection.annot.SpringBean;
/**
 * The {@code ComponentsPage}
 *
 * @author miha
 */

//@MountPath(value = "/signinpage")
public class SignInPage extends WithoutBasePage {
	@SpringBean
    private NutzerManager nutzerManager;
	

    private static final long serialVersionUID = -3698659776363173730L;

  
public SignInPage(PageParameters parameters) {
        super(parameters);
        final FeedbackPanel feedback = new FeedbackPanel("feedback");
		add(feedback);
       add(new SignInForm("intform"));
    }


		
		private static class SignInForm extends BootstrapForm{
			private String maklerPassword;
			private String maklerUsername;
			@SpringBean
		    private NutzerManager nutzerManager;	
			public SignInForm(String id){
				super(id);
				setModel(new CompoundPropertyModel(this));
				add(new TextField("maklerUsername"));
				add(new PasswordTextField("maklerPassword"));
			/*	 BootstrapButton signin = new BootstrapButton("signin", Buttons.Type.Default){
					 @Override
					 public void onSubmit() {
			            	
							
						if(signIn(maklerUsername,maklerPassword)){
							
							continueToOriginalDestination();
						}
						else{
							error("Unknown username/password");
							setResponsePage(getApplication().getHomePage());
						}
					}
					};
					 add(signin);  */	
			}
		@Override
			protected final void onSubmit(){
				
			if(signIn(maklerUsername,maklerPassword)){
				continueToOriginalDestination();
			}
			else{
				error("Unknown username/password");
				setResponsePage(getApplication().getHomePage());
			}
			}
			
			private boolean signIn(String username,String password){
				System.err.println("-----------------------------------------"+password+" "+username);
				if (username!=null&&password!=null)
				{
					Iterator it = nutzerManager.getNutzer().iterator();
					while (it.hasNext()){
						Nutzer nutzer = (Nutzer) it.next();
					System.err.println("-----------------------------------------"+nutzer);
						if(nutzer!=null){
						if(nutzer.getUsername().equals(username)&&nutzer.getPassword().equals(password)){
						MaklerSession.get().setNutzer(nutzer);
						return true;
					}
				}

			}
			}	
			
				return false;	
				
			}
			}
		
}
