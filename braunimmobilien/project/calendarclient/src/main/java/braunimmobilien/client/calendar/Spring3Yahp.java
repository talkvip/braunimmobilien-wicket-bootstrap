package braunimmobilien.client.calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.springframework.beans.factory.annotation.Autowired;









import java.util.Collections;

import braunimmobilien.service.ScoutManager;
import braunimmobilien.model.Scout;
import braunimmobilien.service.KundeManager;
import braunimmobilien.model.Kunde;
import braunimmobilien.service.PersonManager;
import braunimmobilien.model.Personen;
import braunimmobilien.service.TypeManager;
import braunimmobilien.model.Type;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.model.Objarttyp;
import braunimmobilien.service.OrteManager;
//import braunimmobilien.webapp.links.scout.ScoutForm;
import braunimmobilien.model.Orte;

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




import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Lists;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;






import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
//import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
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



//import com.google.gdata.client.calendar.CalendarService;
//import com.google.gdata.data.calendar.CalendarEventEntry;






import java.io.BufferedReader;
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
	
	 @Autowired
		private  ScoutManager scoutManager;
	 public ScoutManager getScoutManager() {
		return scoutManager;
	}

	public void setScoutManager(ScoutManager scoutManager) {
		this.scoutManager = scoutManager;
	}

	
	 @Autowired
		private  TypeManager typeManager;
	 public TypeManager getTypeManager() {
		return typeManager;
	}

	public void setTypeManager(TypeManager typeManager) {
		this.typeManager = typeManager;
	}

	public ObjarttypManager getObjarttypManager() {
		return objarttypManager;
	}

	public void setObjarttypManager(ObjarttypManager objarttypManager) {
		this.objarttypManager = objarttypManager;
	}

	public OrteManager getOrteManager() {
		return orteManager;
	}

	public void setOrteManager(OrteManager orteManager) {
		this.orteManager = orteManager;
	}

	public PersonManager getPersonManager() {
		return personManager;
	}

	public void setPersonManager(
			PersonManager personManager) {
		this.personManager = personManager;
	}
	@Autowired
		private  ObjarttypManager objarttypManager;
	 @Autowired
		private  OrteManager orteManager;
	 @Autowired
	private  KundeManager kundeManager;
	public KundeManager getKundeManager() {
		return kundeManager;
	}

	public void setKundeManager(KundeManager kundeManager) {
		this.kundeManager = kundeManager;
	}
	 @Autowired
		private  PersonManager personManager;
		

	public PersonManager getEinetuemermusterManager() {
		return personManager;
	}

	public void setEinetuemermusterManager(
			PersonManager einetuemermusterManager) {
		this.personManager = einetuemermusterManager;
	}
	
	 /**
	   * Be sure to specify the name of your application. If the application name is {@code null} or
	   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
	   */
	  private static final String APPLICATION_NAME = "braunimmobiliencalendar";

	  /** Directory to store user credentials. */
	  private static final java.io.File DATA_STORE_DIR =
	      new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");

	  /**
	   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
	   * globally shared instance across your application.
	   */
	  private static FileDataStoreFactory dataStoreFactory;
	  
	  /** Global instance of the HTTP transport. */
	  private static HttpTransport httpTransport;

	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	  private static com.google.api.services.calendar.Calendar service;

	  static final java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();
	
	
	private static final Log LOG = LogFactory.getLog(Spring3Yahp.class);
	private static  String function="0";
	private static String app_name="braunimmobiliencalendar";
	private static String user = "wichtigtuer.braun@gmail.com";
	private static String client_secret_path = "/home/java/wicket/appfuse/braunimmobilien-eclipse-maven/project/calendarclient/calendarclient_secret.json";
	private static  String year="2015";
	private static String month="2";
	private static String day="17";
	private static String hour="0";
	private static String minutes="30";
	private static String times="10";
	private static  String year1="2015";
	private static String month1="2";
	private static String day1="17";
	private static String merkmal="Scoutannonce";
	static BufferedWriter writer;
	 private static final String SCOPECALENDAR = "https://www.googleapis.com/auth/calendar";
  // Check https://developers.google.com/gmail/api/auth/scopes for all available scopes
  private static final String APP_NAME = "braunimmobiliencalendar";
  // Email address of the user, or "me" can be used to represent the currently authorized user.
  private static final String USER = "wichtigtuer.braun@gmail.com";
  // Path to the client_secret.json file downloaded from the Developer Console
  private static final String CLIENT_SECRET_PATH = "/home/java/wicket/appfuse/braunimmobilien-eclipse-maven/project/calendarclient/calendarclient_secret.json";

  private static GoogleClientSecrets clientSecrets;
	/**
	 * @param args
	 */
	  
	
	public void yahp(String[] args) {
		if(args.length<4){
			LOG.error("Die Anzahl der Parameter stimmt nicht");
	System.exit(1);
		}
	
		

		function=args[0];
		app_name=args[1];
		user = args[2];
		client_secret_path = args[3];
	
		
	LOG.info("Funktion : "+function);
	LOG.info(" Appname "+app_name);
	LOG.info(" user "+user);
	LOG.info(" Client Secret Path "+client_secret_path);
	
 	    
	
	   
	try {	
		  // initialize the transport
	      httpTransport = GoogleNetHttpTransport.newTrustedTransport();

	      // initialize the data store factory
	      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

	      // authorization
	      Credential credential = authorize();

	      // set up global Calendar instance
	      service = new com.google.api.services.calendar.Calendar.Builder(
	          httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	      while(true){
	        	String alternative="";
	           System.out.println("Please open tell which part to run:\n0 Exit\n1 Transfer Scout Ids into Events given date,hour and number per date and duration time in minutes\n2 Show all Events given date period\n3 show all calendars");
	         BufferedReader   br = new BufferedReader(new InputStreamReader(System.in));
	            alternative = br.readLine();
	            // Retrieve a page of Threads; max of 100 by default.
	            if(alternative.equals("0"))
    	            System.exit(1); 
	            if(alternative.equals("1")){ 
	            	year=args[4];
	            	month=args[5];
	            	day=args[6];
	            	hour=args[7];
	            	
	            	
	            	
	            	minutes=args[8];
	            	times=args[9];
	            	merkmal=args[10];
	            	LOG.info("Ausgangsdatum : "+day+" "+month+" "+year);
	            	LOG.info("Verteilung : "+hour+" "+minutes+" "+times);
	            	LOG.info("Suchkriterium : "+merkmal);
	            	
	            	
	            	place();}
	            
	            if(alternative.equals("2")) {
	            	year=args[4];
	        		month=args[5];
	        		day=args[6];
	        		year1=args[7];
	        		month1=args[8];
	        		day1=args[9];
	        		merkmal=args[10];
	        		LOG.info("Ausgangsdatum : "+day+" "+month+" "+year);
	        		LOG.info("Enddatum : "+day1+" "+month1+" "+year1);
	        		LOG.info("Suchkriterium : "+merkmal);
	            	
	            	
	            	show();}
	            
	            if(alternative.equals("3")) showCalendars();
	      
    }
	}
	catch (IOException e) {
	      LOG.error(e.getMessage());
	    } catch (Throwable t) {
	      LOG.error(t.getStackTrace());
	    }
	    System.exit(1);
	  
    	    
	}
    private void place() throws IOException{	            
							
        	        		java.util.Calendar calstart = java.util.Calendar.getInstance();
    	    	            	calstart.set(java.util.Calendar.YEAR, Integer.parseInt(year));
    	    	            	calstart.set(java.util.Calendar.MONTH,Integer.parseInt(month)-1); 
    	    	            	calstart.set(java.util.Calendar.DAY_OF_MONTH,Integer.parseInt(day));
    	    	            	calstart.set(java.util.Calendar.HOUR,Integer.parseInt(hour) -12);
    	    	            	 calstart.set(java.util.Calendar.MINUTE,0);
    	    	            	
        	        		
        	        	//	System.exit(1);
        	        		
        	        		
    	        List<Scout> scouts=scoutManager.getScoutes();
    	 	     Iterator it=scouts.iterator();
    	 	     int j=0;
    	 	    
    	        while(it.hasNext()){
    	 	    	 Scout scout=(Scout)it.next();   
    	 	     if(!scout.isYesScout()){
    	 	     if(j==Integer.parseInt(times)) break;
    	 	    String contacttext= " Wegen Scoutannonce "+ scout.getId()+" kontaktieren ";
    		      String infotext= " Scoutannonce ";
    		      infotext=infotext+ "Where : "+scout.getWhere();
    			      infotext=infotext+ " Who : "+scout.getWho()+" kontaktieren ";
    			      infotext=infotext+ " Spezial : "+scout.getPhone()+" kontaktieren ";
    			      LOG.info(addEvent(service.calendars().get("primary").execute(),calstart.getTime(),contacttext,infotext)+" added to primary");
    			      scout.setYesScout(new Boolean(true));
    	    	 	     scoutManager.save(scout);
    	    	 	    LOG.info(scout.getId());
    	    	 	   j=j+1;
    	    	 	   calstart.add(java.util.Calendar.MINUTE,Integer.parseInt(minutes));
    	 	     }
    	        }
    }
    	            	
    private void show() throws IOException{	    
    	
	   
	 java.util.Calendar calendar = new GregorianCalendar();
	     calendar.set(java.util.Calendar.YEAR, Integer.parseInt(year));
	calendar.set(java.util.Calendar.MONTH, Integer.parseInt(month)-1); // 11 = december
	calendar.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(day)); // christmas eve
	calendar.set(java.util.Calendar.HOUR_OF_DAY, -12);
	  java.util.Calendar calstart = java.util.Calendar.getInstance();
		
  	calstart.set(java.util.Calendar.YEAR, Integer.parseInt(year1));
  	calstart.set(java.util.Calendar.MONTH,Integer.parseInt(month1)-1); 
  	calstart.set(java.util.Calendar.DAY_OF_MONTH,Integer.parseInt(day1));
  	calstart.set(java.util.Calendar.HOUR, -12);
  	showEvents("primary",calendar.getTime() ,calstart.getTime());
	
	
	
	   
}      
    	            
    /*	        private void show() throws IOException{	    
    	            	
    	    
    	            	 	String pageToken = null;
    	            	int i=0;
    	            
    	            do {++i;
    	            	int j=0;
    	            	int k=0;
    	            	 java.util.Calendar calendar = new GregorianCalendar();
    	            	     calendar.set(java.util.Calendar.YEAR, Integer.parseInt(year));
    	            	calendar.set(java.util.Calendar.MONTH, Integer.parseInt(month)-1); // 11 = december
    	            	calendar.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(day)); // christmas eve
    	            	calendar.set(java.util.Calendar.HOUR_OF_DAY, -12);
    	            	
    	            	
    	            	
    	            	   com.google.api.client.util.DateTime minTime = new  com.google.api.client.util.DateTime(calendar.getTime(), TimeZone
    	    	    		          .getDefault());
    	            	   calendar.add(java.util.Calendar.HOUR_OF_DAY, 24);
    	            	   com.google.api.client.util.DateTime maxTime = new  com.google.api.client.util.DateTime(calendar.getTime(), TimeZone
 	    	    		          .getDefault());
    	            	   
    	          
    	            	   Events events = service.events().list("primary").setTimeMax(maxTime).setTimeMin(minTime).setPageToken(pageToken).execute();
    	            	   j=0;
    	            	List<Event> items = events.getItems();
    	            	  	for (Event event : items) {
    	            	  		 ++j;
    	            	    	if(event.getStart()!=null&&event.getEnd()!=null){
    	            	    com.google.api.client.util.DateTime start=event.getStart().getDateTime();
    	            	    com.google.api.client.util.DateTime end=event.getEnd().getDateTime();
    	            	    		if(start!=null&&end!=null){
    	            	    Date startdate=new Date(start.getValue());
    	            	    java.util.Calendar calsearch = java.util.Calendar.getInstance();
    	            	
    	            	    calsearch.setTime(startdate);
    	            	    LOG.info("vor "+calsearch.get(java.util.Calendar.YEAR)+"  "+calsearch.get(java.util.Calendar.MONTH)+"  "+calsearch.get(java.util.Calendar.DAY_OF_MONTH));
        					if(calsearch.get(java.util.Calendar.YEAR)==Integer.parseInt(year)&&calsearch.get(java.util.Calendar.MONTH)==Integer.parseInt(month)-1&&calsearch.get(java.util.Calendar.DAY_OF_MONTH)==Integer.parseInt(day)){
        						LOG.info("zwischen "+calsearch.get(java.util.Calendar.YEAR)+"  "+calsearch.get(java.util.Calendar.MONTH)+"  "+calsearch.get(java.util.Calendar.DAY_OF_MONTH));
        						if(event.getSummary().contains(merkmal)){
    	            	
        	LOG.info("nach "+calsearch.get(java.util.Calendar.YEAR)+"  "+calsearch.get(java.util.Calendar.MONTH)+"  "+calsearch.get(java.util.Calendar.DAY_OF_MONTH));
    	            				  java.util.Calendar calstart = java.util.Calendar.getInstance();
    	            		
    	    	            	calstart.set(java.util.Calendar.YEAR, Integer.parseInt(year1));
    	    	            	calstart.set(java.util.Calendar.MONTH,Integer.parseInt(month1)-1); 
    	    	            	calstart.set(java.util.Calendar.DAY_OF_MONTH,Integer.parseInt(day1));
    	    	            	calstart.set(java.util.Calendar.HOUR, -12);
    	    	            	calstart.set(java.util.Calendar.MINUTE, k*30);
    	    	            	   com.google.api.client.util.DateTime newstartdate = new  com.google.api.client.util.DateTime(calstart.getTime(), TimeZone
    	    	    	    		          .getDefault());
    	    	            	   EventDateTime eventStartDateTime=new EventDateTime();
    	     	    		      eventStartDateTime.setDateTime(newstartdate);
    	    	            	   event.setStart(eventStartDateTime);
    	    	            	   calstart.add(java.util.Calendar.MINUTE, 30);
    	    	            	  
    	    	            	  
    	    	            	
    	    	    	            	 com.google.api.client.util.DateTime newenddate = new  com.google.api.client.util.DateTime(calstart.getTime(), TimeZone
    	    	    	    	    		          .getDefault());
    	    	    	            	   EventDateTime eventEndDateTime=new EventDateTime();
    	    	     	    		      eventEndDateTime.setDateTime(newenddate);
    	    	    	            	   event.setEnd(eventEndDateTime);
    	    	    	            	  

    	            			Event updatedEvent = service.events().update("primary", event.getId(), event).execute();
    	            			LOG.info(updatedEvent.getUpdated());
    	            			++k;
    	            		
    	    	            		}}}}}
    	            	  LOG.info("Anzahl  Events :"+j+" found : "+k);
    	            	  pageToken = events.getNextPageToken();
    	            	 
    	            	 }while (pageToken != null);
LOG.info("Anzahl  Tokens :"+i);
    	            	}*/
    	            	
	 private static Credential authorize() throws Exception {
		    // load client secrets
		    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
		        new InputStreamReader(Spring3Yahp.class.getResourceAsStream("/calendarclient_secret.json")));
		    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
		        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
		      System.out.println(
		          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar "
		          + "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
		      System.exit(1);
		    }
		    // set up authorization code flow
		    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		        httpTransport, JSON_FACTORY, clientSecrets,
		        Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
		        .build();
		    // authorize
		    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		  }
	 private static void showCalendars() throws IOException {
		    View.header("Show Calendars");
		    CalendarList feed = service.calendarList().list().execute();
		    
		    View.display(feed);
		  }
	 private static void addCalendarsUsingBatch() throws IOException {
		    View.header("Add Calendars using Batch");
		    BatchRequest batch = service.batch();

		    // Create the callback.
		    JsonBatchCallback<Calendar> callback = new JsonBatchCallback<Calendar>() {

		      @Override
		      public void onSuccess(Calendar calendar, HttpHeaders responseHeaders) {
		        View.display(calendar);
		        addedCalendarsUsingBatch.add(calendar);
		      }

		      @Override
		      public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
		        System.out.println("Error Message: " + e.getMessage());
		      }
		    };

		    // Create 2 Calendar Entries to insert.
		    Calendar entry1 = new Calendar().setSummary("Calendar for Testing 1");
		    service.calendars().insert(entry1).queue(batch, callback);

		    Calendar entry2 = new Calendar().setSummary("Calendar for Testing 2");
		    service.calendars().insert(entry2).queue(batch, callback);

		    batch.execute();
		  }
	 private static Calendar addCalendar() throws IOException {
		    View.header("Add Calendar");
		    Calendar entry = new Calendar();
		    entry.setSummary("Calendar for Testing 3");
		    Calendar result = service.calendars().insert(entry).execute();
		    View.display(result);
		    return result;
		  }

		  private static Calendar updateCalendar(Calendar calendar) throws IOException {
		    View.header("Update Calendar");
		    Calendar entry = new Calendar();
		    entry.setSummary("Updated Calendar for Testing");
		    Calendar result = service.calendars().patch(calendar.getId(), entry).execute();
		    View.display(result);
		    return result;
		  }


		  private static void addEvent(Calendar calendar) throws IOException {
		    View.header("Add Event");
		    Event event = newEvent();
		    Event result = service.events().insert(calendar.getId(), event).execute();
		    View.display(result);
		  }
		
		  private static String addEvent(Calendar calendar,Date date,String summary,String info) throws IOException {
			    View.header("Add Event");
			    Event event = newEvent();
			    Event result = service.events().insert(calendar.getId(), event).execute();
			    View.display(result);
			    return event.getId();
			  }

		  private static Event newEvent() {
		    Event event = new Event();
		    event.setSummary("New Event");
		    Date startDate = new Date();
		    Date endDate = new Date(startDate.getTime() + 3600000);
		    DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
		    event.setStart(new EventDateTime().setDateTime(start));
		    DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
		    event.setEnd(new EventDateTime().setDateTime(end));
		    return event;
		  }
		  private static Event newEvent(Date date,String summary,String info) {
			    Event event = new Event();
			    event.setSummary(summary);
			    event.setDescription(info);
			    Date startDate = new Date();
			    Date endDate = new Date(startDate.getTime() + 3600000);
			    DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
			    event.setStart(new EventDateTime().setDateTime(start));
			    DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
			    event.setEnd(new EventDateTime().setDateTime(end));
			    return event;
			  }
		  private static void showEvents(Calendar calendar) throws IOException {
		    View.header("Show Events");
		    Events feed = service.events().list(calendar.getId()).execute();
		    View.display(feed);
		  }

		  
		  private static void showEvents(String calendarID,Date datemin,Date datemax) throws IOException {
			    View.header("Show Events");
			    String pageToken = null;
			    do {  Events feed = service.events().list(calendarID).setTimeMax(new  com.google.api.client.util.DateTime(datemax, TimeZone
	    		          .getDefault())).setTimeMin(new  com.google.api.client.util.DateTime(datemin, TimeZone
	    		          .getDefault())).setPageToken(pageToken).execute();
			 
			    View.display(feed);
			    pageToken = feed.getNextPageToken();
           	 
     	 }while (pageToken != null);
			  }
		  
		  private static void deleteCalendarsUsingBatch() throws IOException {
		    View.header("Delete Calendars Using Batch");
		    BatchRequest batch = service.batch();
		    for (Calendar calendar : addedCalendarsUsingBatch) {
		      service.calendars().delete(calendar.getId()).queue(batch, new JsonBatchCallback<Void>() {

		        @Override
		        public void onSuccess(Void content, HttpHeaders responseHeaders) {
		          System.out.println("Delete is successful!");
		        }

		        @Override
		        public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) {
		          System.out.println("Error Message: " + e.getMessage());
		        }
		      });
		    }

		    batch.execute();
		  }

		  private static void deleteCalendar(Calendar calendar) throws IOException {
		    View.header("Delete Calendar");
		    service.calendars().delete(calendar.getId()).execute();
		  }
	 
	 
	 
	
	}
 
    	               
 
	  
    
 
	
	
	



