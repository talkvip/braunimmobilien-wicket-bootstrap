package braunimmobilien.client.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.springframework.beans.factory.annotation.Autowired;



import com.google.gdata.data.DateTime;

import java.util.Collections;



import java.io.*;
import java.util.Hashtable;
import java.net.MalformedURLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.util.TimeZone;
import java.sql.*;

import com.google.gdata.client.Service.GDataRequest;
import com.google.gdata.client.http.HttpGDataRequest;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.client.Query;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.ContactGroupEntry;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.FullName;
import com.google.gdata.data.extensions.StructuredPostalAddress;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.data.extensions.PostCode;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.Link;
import com.google.gdata.data.extensions.City;
import com.google.gdata.data.extensions.Country;
import com.google.gdata.data.extensions.Street;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.XmlBlob;
import com.google.gdata.util.ContentType;
import com.google.api.services.calendar.model.Events;
import java.io.IOException;
import java.net.URL;
import com.google.api.services.calendar.model.Event;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import com.google.gdata.data.DateTime;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import javax.mail.Session;

import java.util.Properties;

import javax.mail.MessagingException;



public class Spring3Yahp {
	
	private static final Log LOG = LogFactory.getLog(Spring3Yahp.class);
	
	
	
  private static final String SCOPE[] = new String[]{"https://www.google.com/m8/feeds","https://www.googleapis.com/auth/calendar","https://mail.google.com/"};

  private static final String CLIENT_SECRET_PATH = "/home/java/wicket/appfuse/braunimmobilien-eclipse-maven/project/calendarclient/calendarclient_secret.json";
  private static final String USER = "wichtigtuer.braun@gmail.com";
  private static final String APP_NAME = "braunimmobiliencalendar";
  private static GoogleClientSecrets clientSecrets;
	/**
	 * @param args
	 */
	  
	
	public void yahp(String[] args) {
		
		
		  HttpTransport httpTransport = new NetHttpTransport();
		    JsonFactory jsonFactory = new JacksonFactory();
		    ContactsService contactservice=null;
		    Calendar calendarservice=null;
		    Gmail gmailservice=null;
		    BufferedReader br=null;
		    try{
		
		    	  clientSecrets = GoogleClientSecrets.load(jsonFactory,   new FileReader(CLIENT_SECRET_PATH));
		    	 GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		    		        httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPE))
		    		        .setAccessType("online")
		    		        .setApprovalPrompt("auto").build();
		    	 String url = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
		    		        .build();
		    	    String url1 = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
		    	            .build();
		    	        System.out.println("Please open the following URL in your browser then type"
		    	                           + " the authorization code:\n" + url1);
		    	        
		    	   
		    	        // Read code entered by user.
		    	        br = new BufferedReader(new InputStreamReader(System.in));
		    	        String code = br.readLine();

		    	        // Generate Credential using retrieved code.
		    	        GoogleTokenResponse response = flow.newTokenRequest(code)
		    	            .setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).execute();
		    	        
		    	        GoogleCredential credential = new GoogleCredential()
		    	            .setFromTokenResponse(response);
    
    
    
   
    // Create a new authorized Gmail API client
    contactservice = new ContactsService(APP_NAME);
    contactservice.setOAuth2Credentials(credential);
    
    calendarservice = new Calendar.Builder(httpTransport, jsonFactory, credential)
    .setApplicationName(APP_NAME).build(); 
    gmailservice = new Gmail.Builder(httpTransport, jsonFactory, credential)
    .setApplicationName(APP_NAME).build();
    
 //   		Builder(httpTransport, jsonFactory, credential)
  //      .setApplicationName(APP_NAME).build();
   while(true){
    System.out.println("Please open tell which part to run:\n0 Exit\n1 Show All Contacts\n2 Show all Contacts\n3 Show all Mails\n4 show messages by label\n5 create label\n6 label message\n7 list labels\n8 list email address in Inbox\n");
    br = new BufferedReader(new InputStreamReader(System.in));
    String alternative = br.readLine();
    // Retrieve a page of Threads; max of 100 by default.
   
    if(alternative.equals("0")){
    System.exit(1);}
    
    
   
   
   if(alternative.equals("1")){
	   URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/wichtigtuerbraun@googlemail.com/full");
	   
   	while(true) {	  ContactFeed resultFeed = contactservice.getFeed(feedUrl, ContactFeed.class);
   	  ContactEntry foundentry=null;
   	  List<ContactEntry> liste =  new ArrayList();
   	  for (ContactEntry entry : resultFeed.getEntries()) {
   	System.err.println(entry.getName().getFullName().getValue());}
	    	  }  
   
   }
   
   if(alternative.equals("2")){
	  
   		String pageToken = null;
   	 
   do{	
	   Events events = calendarservice.events().list("primary").setPageToken(pageToken).execute();
	   List<Event> items = events.getItems();
  	for (Event event : items) {
  		System.err.println(event.getSummary());
  	}
  	pageToken = events.getNextPageToken(); }
   	while (pageToken != null);
   }
   
   
   
   
   if(alternative.equals("3")){
	   ListThreadsResponse threadsResponse = gmailservice.users().threads().list(USER).execute();
	    List<Thread> threads = threadsResponse.getThreads();

	    // Print ID of each Thread.
	    for (Thread thread : threads) {
	    	if(thread.getMessages()!=null){
	    	Iterator it=thread.getMessages().iterator();
	    	while(it.hasNext()){
	    		
	    		Message message=(Message)it.next();
	    		 System.out.println("Messages by Thread"+message.toPrettyString());
	    		
	    	}
	    	}
	    	else{ System.out.println("No Messages by Thread");}
	    	 System.out.println("Thread ID: " + thread.getId()+"  "+thread.toPrettyString());

	    	
	}
   }
   
   }    
		    }    
    

catch(Exception e){System.out.println("Exception "+e);
System.exit(1);}
	   
	   System.exit(1);
	}
	
	
	

}
