package braunimmobilien.client.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gdata.data.DateTime;

import java.util.Collections;
import braunimmobilien.webapp.person.*;
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

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Thread;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.gdata.data.DateTime;
import com.google.gdata.client.contacts.ContactsService;
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
	private static  String eigtid="1";
	private static String objarttyp="0";
	private static String ortid="1";
	private static int anzahl=2;
	static String adressen[]=new String[2];
	
	static ResultSet rs = null;
	static Statement stmt=null;
	static Connection con=null; 
	static BufferedWriter writer;
	
 	/**
	 * @param args
	 */
	  
	
	public void yahp(String[] args) {
		
		if(args.length!=6){
			LOG.error("Die Anzahl der Parameter stimmt nicht");
	System.exit(1);
		}
	
		

		eigtid=args[0];
		
		objarttyp=args[1];
		ortid=args[2];
		adressen[0]=args[3];
		adressen[1]=args[4];
		anzahl=Integer.parseInt(args[5]);
	LOG.info("eigtid : "+eigtid);
	LOG.info(" objarttyp "+objarttyp);
	LOG.info(" ortid "+ortid);
	LOG.info(" adressen "+adressen.toString());
	LOG.info(" anzahl "+anzahl);

	
	
	
	try{
	
	
/*	kundeManager.reindex();
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
      	}*/

    	SynchronizeContacts	synchronizecontacts=new SynchronizeContacts();	
      
    	System.out.println("Please open the following URL in your browser then type"
                + " the authorization code:\n" + synchronizecontacts.getUrl());

// Read code entered by user.
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String code = br.readLine();

synchronizecontacts.setContactsService(code);	      



   
   while(true){
    System.out.println("Please open tell which part to run:\n0 Exit\n1 Show Threads\n2 send message\n3 query messages\n4 show messages by label\n5 create label\n6 label message\n7 list labels\n8 list email address in Inbox\n");
    br = new BufferedReader(new InputStreamReader(System.in));
    String alternative = br.readLine();
    // Retrieve a page of Threads; max of 100 by default.
   
    if(alternative.equals("0")){
    System.exit(1);}
    if(alternative.equals("1")){
    //	Personen person=personManager.get(new Long(eigtid));
   //synchronizecontacts.findAndDeleteContact(person.getEigtTel());
    	synchronizecontacts.printAllGroups();
    	}
    if(alternative.equals("2")){
   /* 	Personen person=personManager.get(new Long(eigtid));
    	TelefonListModel telefone=null;
    	 if((person.getEigtTelefone()!=null)&&(person.getEigtTelefone().length()>0)){
			 telefone = new TelefonListModel(person.getEigtTelefone());}
			else{telefone=new TelefonListModel();}
			
    	person.setEigtTel(synchronizecontacts.createAndInsertNewContact(person,telefone,"Testaufnahme","System Group: My Contacts"));
		*/
    	}
    
  
    	 
    	
    
    
 
    
        
   } 
    
    
}
catch(Exception e){
	e.printStackTrace();
	System.out.println("Exception ");
LOG.error(e);
System.exit(1);}
	   
	   System.exit(1);
	}
	
	
	

}
