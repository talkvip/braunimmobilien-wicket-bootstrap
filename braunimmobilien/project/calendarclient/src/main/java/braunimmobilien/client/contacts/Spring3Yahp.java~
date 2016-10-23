package braunimmobilien.client.mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gdata.data.DateTime;

import java.util.Collections;

import braunimmobilien.service.KundeManager;
import braunimmobilien.model.Kunde;
import braunimmobilien.service.PersonManager;
import braunimmobilien.model.Personen;
import braunimmobilien.service.TypeManager;
import braunimmobilien.model.Type;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.model.Objarttyp;
import braunimmobilien.service.OrteManager;
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

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Thread;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.gdata.data.DateTime;

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

import com.google.api.services.gmail.model.ModifyMessageRequest;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;

public class Spring3Yahp {
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
	private static final Log LOG = LogFactory.getLog(Spring3Yahp.class);
	private static  String directory="/homeerweitert/java/immobilien/objekte/Hannover/Renditeobjekte/";
	private static String objarttyp="0";
	private static String ortid="1";
	private static int anzahl=2;
	static String adressen[]=new String[2];
	
	static ResultSet rs = null;
	static Statement stmt=null;
	static Connection con=null; 
	static BufferedWriter writer;
	
  // Check https://developers.google.com/gmail/api/auth/scopes for all available scopes
  private static final String SCOPE = "https://mail.google.com/";
// private static final String APP_NAME = "gmailexample";
 // private static final String USER = "braun18091949@gmail.com";
 // private static final String CLIENT_SECRET_PATH = "/home/java/wicket/gmailapi/gmailexample/src/client_secret.json";
  private static final String CLIENT_SECRET_PATH = "/home/java/wicket/appfuse/braunimmobilien-eclipse-maven/project/calendarclient/calendarclient_secret.json";

  private static final String USER = "wichtigtuer.braun@gmail.com";
  private static final String APP_NAME = "braunimmobiliencalendar";
  private static GoogleClientSecrets clientSecrets;
	/**
	 * @param args
	 */
	  
	
	public void yahp(String[] args) {
		
		if(args.length!=6){
			LOG.error("Die Anzahl der Parameter stimmt nicht");
	System.exit(1);
		}
	
		

		directory=args[0];
		
		objarttyp=args[1];
		ortid=args[2];
		adressen[0]=args[3];
		adressen[1]=args[4];
		anzahl=Integer.parseInt(args[5]);
	LOG.info("work directory : "+directory);
	LOG.info(" objarttyp "+objarttyp);
	LOG.info(" ortid "+ortid);
	LOG.info(" adressen "+adressen.toString());
	LOG.info(" anzahl "+anzahl);
	kundeManager.reindex();
	personManager.reindex();
	String emailt="\"ercan.murat@gmx.de\"";
	Iterator personeniterator=null;
		Iterator kundeniterator=kundeManager.search(emailt).iterator();
      	while(kundeniterator.hasNext()){
        	Kunde kunde=(Kunde)kundeniterator.next();
        	System.err.println("Kunde : "+kunde.getPerson().getEigtName());
      	}
      	Iterator personiterator=personManager.search(emailt).iterator();
      	while(personiterator.hasNext()){
        	Personen person=(Personen)personiterator.next();
        	System.err.println("Person : "+person.getEigtName());
      	}
//		System.exit(1);
	  HttpTransport httpTransport = new NetHttpTransport();
    JsonFactory jsonFactory = new JacksonFactory();

   
    try{
    clientSecrets = GoogleClientSecrets.load(jsonFactory,   new FileReader(CLIENT_SECRET_PATH));

    // Allow user to authorize via url.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPE))
        .setAccessType("online")
        .setApprovalPrompt("auto").build();

    String url = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
        .build();
    System.out.println("Please open the following URL in your browser then type"
                       + " the authorization code:\n" + url);

    // Read code entered by user.
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String code = br.readLine();

    // Generate Credential using retrieved code.
    GoogleTokenResponse response = flow.newTokenRequest(code)
        .setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).execute();
    GoogleCredential credential = new GoogleCredential()
        .setFromTokenResponse(response);
  
    
    
    
   
    // Create a new authorized Gmail API client
    Gmail service = new Gmail.Builder(httpTransport, jsonFactory, credential)
        .setApplicationName(APP_NAME).build();
   while(true){
    System.out.println("Please open tell which part to run:\n0 Exit\n1 Show Threads\n2 send message\n3 query messages\n4 show messages by label\n5 create label\n6 label message\n7 list labels\n8 list email address in Inbox\n");
    br = new BufferedReader(new InputStreamReader(System.in));
    String alternative = br.readLine();
    // Retrieve a page of Threads; max of 100 by default.
   
    if(alternative.equals("0")){
    System.exit(1);}
    
    
    if(alternative.equals("1")){
    ListThreadsResponse threadsResponse = service.users().threads().list(USER).execute();
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

    	
    
     
   

    if(alternative.equals("2")){	
    	  System.out.println("Please enter subject");
    	    br = new BufferedReader(new InputStreamReader(System.in));
    	    String subject = br.readLine();	
    	    System.out.println("Please enter mailtext");
    	    br = new BufferedReader(new InputStreamReader(System.in));
    	    String mailtext = br.readLine();	
	 try{ sendMessage(service,USER,createEmail(USER,USER,subject,mailtext));}
	    catch (Exception e){System.out.println("Message send not work "+e);}
    }
    
    if(alternative.equals("3")){
    	 System.out.println("Please enter query");
 	    br = new BufferedReader(new InputStreamReader(System.in));
 	    String query = br.readLine();	
	   List<Message> messages=listMessagesMatchingQuery(service, USER,
			      query);}
    
    if(alternative.equals("4")){
    	 System.out.println("Please enter label");
 	    br = new BufferedReader(new InputStreamReader(System.in));
 	    String label = br.readLine();	
	   List<String> labelIds=new ArrayList<String>(); 
	   labelIds.add(label); 
	   List<Message>  messages=listMessagesWithLabels(service, USER,labelIds
			      );	 
    }
  
    if(alternative.equals("5")){
   	 System.out.println("Please enter label");
	    br = new BufferedReader(new InputStreamReader(System.in));
	    String label = br.readLine();	
	    Label lab=createLabel(service,USER,label);
   }
    
    if(alternative.equals("6")){
    	 System.out.println("Please enter query");
  	    br = new BufferedReader(new InputStreamReader(System.in));
  	    String query = br.readLine();	
 	   List<Message> messages=listMessagesMatchingQuery(service, USER,
 			      query);
      	 System.out.println("Please enter label");
   	    br = new BufferedReader(new InputStreamReader(System.in));
   	    String label = br.readLine();
   	 List<String> labelRemoveIds=new ArrayList<String>(); 
   	 List<String> labelAddIds=new ArrayList<String>(); 
	   labelAddIds.add(label); 
   	 Iterator it=messages.iterator();
 	while(it.hasNext()){
 		
 		Message message=(Message)it.next();
 		 modifyMessage(service,USER,message.getId(),
 				 labelAddIds, labelRemoveIds); 
 		
 	}
   
      }
    if(alternative.equals("7")){
    	  listLabels(service, USER); 
    
      } 
    if(alternative.equals("8")){
    	
  	    String label = "INBOX";	
 	   List<String> labelIds=new ArrayList<String>(); 
 	   labelIds.add(label); 
 	   List<Message>  messages=listMessagesWithLabels(service, USER,labelIds);
    Iterator it=messages.iterator();
    while(it.hasNext()){
    	Message message=(Message) it.next();
    	
    	MimeMessage mime=  	getMimeMessage(service, USER, message.getId());
    	Address[] adressesr=mime.getAllRecipients();
    	Address[] adressesf=mime.getFrom();
    	Address addressr=adressesr[0];
    	String emailr="";
    	
    	
		if(addressr.toString().contains("<")){	
    	emailr="\""+addressr.toString().substring(addressr.toString().indexOf("<")+1, addressr.toString().length()-1)+"\"";}
    	else {
    	emailr="\""+addressr.toString()+"\"";}
    	
    	
    	Address addressf=adressesf[0];
    	
    	String emailf="";
    	if(addressf.toString().contains("<")){	
    	emailf="\""+addressf.toString().substring(addressf.toString().indexOf("<")+1, addressf.toString().length()-1)+"\"";}
    	else {
    	emailf="\""+addressf.toString()+"\"";
    	}
    	if (emailr.length()>2&&adressesr.length==1&&adressesf.length==1&&emailf.equals("\"anfrage@immobilien-hannover-braun.de\""))
    	{
        	personeniterator=personManager.search(emailr).iterator();
      	while(personeniterator.hasNext()){
        	Personen personen=(Personen)personeniterator.next();
     
        	   if(personen.getKunden().size()==1){
        		  kundeniterator=personen.getKunden().iterator();
        		  while(kundeniterator.hasNext()){
        			  
        		  Kunde kunde=(Kunde)kundeniterator.next();
        		System.err.println("Person To : "+personen.getEigtName());
        	}
        	}
      	}
    	}
    	
    	
    	
    	
    	
    	
    		
    		
    	if (emailf.length()>2&&adressesf.length==1&&adressesr.length==1&&emailr.equals("\"anfrage@immobilien-hannover-braun.de\""))
    	{
    		 personeniterator=personManager.search(emailf).iterator();
  	while(personeniterator.hasNext()){
    	Personen personen=(Personen)personeniterator.next();
   if(personen.getKunden().size()==1){
	  kundeniterator=personen.getKunden().iterator();
	  while(kundeniterator.hasNext()){
		  
	  Kunde kunde=(Kunde)kundeniterator.next();
    		System.err.println("Person From : "+personen.getEigtName()+" Kundennr. "+kunde.getId());
	  }
	  }
    	}
    	}
    }
    	  
    	
    
    
    
    } 
    
        
   } 
    
    
}
catch(Exception e){System.out.println("Exception "+e);
System.exit(1);}
	   
	   System.exit(1);
	}
	
	
	

	 public static MimeMessage createEmail(String to, String from, String subject,
		      String bodyText) throws MessagingException {
		    Properties props = new Properties();
		    Session session = Session.getDefaultInstance(props, null);

		    MimeMessage email = new MimeMessage(session);
		    InternetAddress tAddress = new InternetAddress(to);
		    InternetAddress fAddress = new InternetAddress(from);

		    email.setFrom(new InternetAddress(from));
		    email.addRecipient(javax.mail.Message.RecipientType.TO,
		                       new InternetAddress(to));
		    email.setSubject(subject);
		    email.setText(bodyText);
		    return email;
		  }
	  public static Message createMessageWithEmail(MimeMessage email)
		      throws MessagingException, IOException {
		    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		    email.writeTo(bytes);
		    String encodedEmail = Base64.encodeBase64URLSafeString(bytes.toByteArray());
		    Message message = new Message();
		    message.setRaw(encodedEmail);
		    return message;
		  }
	  
	  public static void sendMessage(Gmail service, String userId, MimeMessage email)
		      throws MessagingException, IOException {
		    Message message = createMessageWithEmail(email);
		    message = service.users().messages().send(userId, message).execute();

		    System.out.println("Message sent id: " + message.getId());
		    System.out.println(message.toPrettyString());
		  } 
	  
	  public static List<Message> listMessagesMatchingQuery(Gmail service, String userId,
		      String query) throws IOException,MessagingException {
		    ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();

		    List<Message> messages = new ArrayList<Message>();
		    while (response.getMessages() != null) {
		      messages.addAll(response.getMessages());
		      if (response.getNextPageToken() != null) {
		        String pageToken = response.getNextPageToken();
		        response = service.users().messages().list(userId).setQ(query)
		            .setPageToken(pageToken).execute();
		      } else {
		        break;
		      }
		    }

		    for (Message message : messages) {
		      System.out.println("Message for Query: "+query+" "+message.toPrettyString());
		    }

		    return messages;
		  }
	  public static List<Message> listMessagesWithLabels(Gmail service, String userId,
		      List<String> labelIds) throws IOException {
		    ListMessagesResponse response = service.users().messages().list(userId)
		        .setLabelIds(labelIds).execute();

		    List<Message> messages = new ArrayList<Message>();
		    while (response.getMessages() != null) {
		      messages.addAll(response.getMessages());
		      if (response.getNextPageToken() != null) {
		        String pageToken = response.getNextPageToken();
		        response = service.users().messages().list(userId).setLabelIds(labelIds)
		            .setPageToken(pageToken).execute();
		      } else {
		        break;
		      }
		    }

		    for (Message message : messages) {
		
		      System.out.println("Message for Labels "+labelIds+"  "+message.toPrettyString()+"  "+message.getRaw());
		    }

		    return messages;
		  }
	  
	  public static Label createLabel(Gmail service, String userId, String newLabelName)
		      throws IOException {
		    Label label = new Label().setName(newLabelName);
		    label.setLabelListVisibility("labelShow");
		    label.setMessageListVisibility("show");
		
		    label = service.users().labels().create(userId, label).execute();

		    System.out.println("Label id: " + label.getId());
		    System.out.println(label.toPrettyString());

		    return label;
		  }
	  public static void modifyMessage(Gmail service, String userId, String messageId,
		      List<String> labelsToAdd, List<String> labelsToRemove) throws IOException {
		    ModifyMessageRequest mods = new ModifyMessageRequest().setAddLabelIds(labelsToAdd)
		        .setRemoveLabelIds(labelsToRemove);
		    Message message = service.users().messages().modify(userId, messageId, mods).execute();

		    System.out.println("Message id: " + message.getId());
		    System.out.println(message.toPrettyString());
		  }
	  
	  
	  
	  public static Message getMessage(Gmail service, String userId, String messageId)
		      throws IOException {
		    Message message = service.users().messages().get(userId, messageId).execute();

		    System.out.println("Message snippet: " + message.getSnippet());

		    return message;
		  }
	  
	  public static MimeMessage getMimeMessage(Gmail service, String userId, String messageId)
		      throws IOException, MessagingException {
		    Message message = service.users().messages().get(userId, messageId).setFormat("raw").execute();

		    byte[] emailBytes = Base64.decodeBase64(message.getRaw());

		    Properties props = new Properties();
		    Session session = Session.getDefaultInstance(props, null);

		    MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
		  
		    return email;
		  }
	  public static void listLabels(Gmail service, String userId) throws IOException {
		    ListLabelsResponse response = service.users().labels().list(userId).execute();
		    List<Label> labels = response.getLabels();
		    for (Label label : labels) {
		      System.out.println(label.toPrettyString());
		    }
		  }
	  public static void deleteLabel(Gmail service, String userId, String labelId) throws IOException{
		    service.users().labels().delete(userId, labelId).execute();
		    System.out.println("Label with id: " + labelId + " deleted successfully.");
		  }
	  public static void getLabel(Gmail service, String userId, String labelId)
		      throws IOException {
		    Label label = service.users().labels().get(userId, labelId).execute();

		    System.out.println("Label " + label.getName() + " retrieved.");
		    System.out.println(label.toPrettyString());
		  }
	  public static void updateLabel(Gmail service, String userId, String labelId, String newLabelName,
		      boolean showInMessageList, boolean showInLabelList) throws IOException {
		    String messageListVisibility = showInMessageList ? "show" : "hide";
		    String labelListVisibility = showInLabelList ? "labelShow" : "labelHide";
		    Label newLabel = new Label().setName(newLabelName)
		        .setMessageListVisibility(messageListVisibility)
		        .setLabelListVisibility(labelListVisibility);
		    newLabel = service.users().labels().update(userId, labelId, newLabel).execute();

		    System.out.println("Label id: " + newLabel.getId());
		    System.out.println(newLabel.toPrettyString());
		  }
	  public static void patchLabel(Gmail service, String userId, String labelId, Label labelPatch)
		      throws IOException {
		    Label patchedLabel = service.users().labels().patch(userId, labelId, labelPatch).execute();

		    System.out.println("Label with ID " + labelId + " patched sucessfully.");
		    System.out.println(patchedLabel.toPrettyString());
		  }
}
