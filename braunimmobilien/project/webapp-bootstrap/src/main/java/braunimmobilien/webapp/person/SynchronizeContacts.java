package braunimmobilien.webapp.person;
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



import com.google.gdata.data.Link;
import org.apache.wicket.Application;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.google.gdata.data.extensions.City;
import com.google.gdata.data.extensions.Country;
import com.google.gdata.data.extensions.Street;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.google.gdata.util.XmlBlob;
import com.google.gdata.util.ContentType;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Strassen;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
/**
 * Hello world!
 *
 */
public class SynchronizeContacts
{
	private static ContactsService contactsservice;
	 private static final String SCOPE[] = new String[]{"https://www.google.com/m8/feeds","https://www.googleapis.com/auth/calendar","https://mail.google.com/"};
		
		private static final String SCOPECALENDAR = "https://www.googleapis.com/auth/calendar";
		  // Check https://developers.google.com/gmail/api/auth/scopes for all available scopes
		  private static final String APP_NAME = "braunimmobiliencalendar";
		  // Email address of the user, or "me" can be used to represent the currently authorized user.
		  private static final String USER = "wichtigtuer.braun@gmail.com";
		  // Path to the client_secret.json file downloaded from the Developer Console
		  private static final String CLIENT_SECRET_PATH = "/home/braun/project/calendarclient/calendarclient_secret.json";

		  private static GoogleClientSecrets clientSecrets;
		  transient GoogleAuthorizationCodeFlow flow=null;
		  transient	    HttpTransport httpTransport = new NetHttpTransport();
		  transient	    JsonFactory jsonFactory = new JacksonFactory();
		private  String url;

public String getUrl() {
			return this.url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

public	SynchronizeContacts() throws AuthenticationException{
	 
	    

	try{    	  clientSecrets = GoogleClientSecrets.load(jsonFactory,   new FileReader(CLIENT_SECRET_PATH));
	    	 flow = new GoogleAuthorizationCodeFlow.Builder(
	    		        httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPE))
	    		        .setAccessType("online")
	    		        .setApprovalPrompt("auto").build();
	    	 this.url = flow.newAuthorizationUrl().setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI)
	    		        .build();
	    	  
	    	    if(url==null) throw new AuthenticationException("building url failed");}
	catch(Exception e){throw new AuthenticationException("buildingWicketApplication)Application.get()).getSynchronizeContacts() url failed "+e);}
	  
	 
}

public	void setContactsService(String accesskey) throws AuthenticationException{
	 try{ GoogleTokenResponse response = flow.newTokenRequest(accesskey)
	            .setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI).execute();
	        GoogleCredential credential = new GoogleCredential()
	            .setFromTokenResponse(response);
	        contactsservice =new ContactsService(APP_NAME);
	        contactsservice.setOAuth2Credentials(credential);
	       
	 }
 	catch(Exception e){
 		throw new AuthenticationException("setting ContactsService  failed "+e);
 	}	   
	
	
	
}

public	SynchronizeContacts(WicketApplication app) throws AuthenticationException{
	
	}
  

  
    private void authenticate() throws AuthenticationException{
    	
   
        
    }
    public void findAndDeleteContact(String name) throws ServiceException, IOException{
    ContactEntry contactEntry=findContact(name);
    	deleteContact(contactEntry);	
    }
    
    
    
    
    public static void deleteContact(String name)
    	    throws ServiceException, IOException {
   
    	
    	URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/wichtigtuerbraun@googlemail.com/full");
 
    	while(true) {	  ContactFeed resultFeed = contactsservice.getFeed(feedUrl, ContactFeed.class);
    	  ContactEntry foundentry=null;
    	  List<ContactEntry> liste =  new ArrayList();
    	  for (ContactEntry entry : resultFeed.getEntries()) {
    	System.err.println(entry.getName().getFullName().getValue());
if (entry.getName()!=null){  
	if (entry.getName().getFullName()!=null){
		if (entry.getName().getFullName().getValue()!=null){
		
 if (entry.getName().getFullName().getValue().equals(name)){
	 liste.add(entry); }
}
    	    }
    	  }
    	  }
    if(liste.size()==0) break;	  
   Iterator it=liste.iterator();
   while(it.hasNext()){
	  
	   ContactEntry contactentry=(ContactEntry)it.next();
	  
	   contactentry.delete();
	   
   }
    }	  
    	  
       	}
    
    
    public static void deleteContact(ContactEntry entry)
    	    throws ServiceException, IOException {
	   entry.delete();	  
       	}
    
    
    public String createAndInsertNewContact(Personen personen,TelefonListModel telefone, String notes,String group) throws ServiceException, IOException {
    	ContactEntry	contactEntry =createContact(personen,telefone,notes);
    	contactEntry.addGroupMembershipInfo(new GroupMembershipInfo(false,findContactGroup(group)));

			contactEntry =updateContactEntry(contactEntry);
    return	contactEntry.getId();
    }
    
    public static ContactEntry createContact(
    		Personen personen,TelefonListModel telefone, String notes)
    	    throws ServiceException, IOException {
    	  // Create the entry to insert
    
    	 ContactEntry contact = new ContactEntry();	
    	Name name = new Name();
    	
       	FullName fullname=new FullName();
       	if (personen.getEigtFirma()!=null&&personen.getEigtName()==null)
       	{fullname.setValue(personen.getEigtFirma());}
       	else{	fullname.setValue(personen.getEigtName());}
    	name.setFullName(fullname);
    	
    //	deleteContact(name.getFullName().getValue());
    
    	
    	contact.setName(name);
    	  contact.setContent(new PlainTextConstruct(notes));
if(personen.getEigtEmail()!=null && personen.getEigtEmail().length()>0){
 	  Email primaryMail = new Email();
    	  primaryMail.setAddress(personen.getEigtEmail());
    	  primaryMail.setRel("http://schemas.google.com/g/2005#home");
    	  primaryMail.setPrimary(true);
    	  contact.addEmailAddress(primaryMail);}

    	  /*   	  Email secondaryMail = new Email();
    	  secondaryMail.setAddress("liz@example.com");
    	  secondaryMail.setRel("http://schemas.google.com/g/2005#work");
    	  secondaryMail.setPrimary(false);
    	  contact.addEmailAddress(secondaryMail);

    	  ExtendedProperty favouriteFlower = new ExtendedProperty();
    	  favouriteFlower.setName("favourite flower");
    	  favouriteFlower.setValue("daisy");
    	  contact.addExtendedProperty(favouriteFlower);

    	  ExtendedProperty sportsProperty = new ExtendedProperty();
    	  sportsProperty.setName("sports");
    	  XmlBlob sportKinds = new XmlBlob();
    	  sportKinds.setBlob(new String("<dance><salsa/></dance>"));
    	  sportsProperty.setXmlBlob(sportKinds);
    	  contact.addExtendedProperty(sportsProperty);*/
    	  Strassen strassen=personen.getStrasse();
    	  Orte ort = personen.getStrasse().getOrt();
    	  Land land = personen.getStrasse().getOrt().getLand();
    	  City city=new City();
    	  city.setValue(ort.getOrtname());
    	  Street street=new Street();
    	  street.setValue(personen.getEigtHausnummer());
    	  Country country=new Country();
    	  country.setValue(land.getLandname());
    	  StructuredPostalAddress postaladdress = new StructuredPostalAddress();
    	  postaladdress.setStreet(street);
    	  postaladdress.setCity(city);
    	  postaladdress.setCountry(country); 
    	  PostCode postcode=new PostCode();
    	  postcode.setValue(strassen.getStrplz());
    	  postaladdress.setPostcode(postcode);
    	  if(personen.getEigtFirma()!=null)  postaladdress.setLabel("work");
    	  else postaladdress.setLabel("home");
    	  contact.addStructuredPostalAddress(postaladdress);
    	  Iterator it=telefone.getTelefonemodel().getTelefonList().iterator();
    	 
    	  while(it.hasNext()){
    		  TelefoneModel.Telefon telefon=(TelefoneModel.Telefon)it.next();
    		  PhoneNumber phonenumber= new PhoneNumber();  
    		  phonenumber.setPhoneNumber(telefon.getTelefon());
        	  phonenumber.setLabel(telefon.getArt()); 
        	  contact.addPhoneNumber(phonenumber);
    	  }
    	 
    	
    	 
    	
    	  URL postUrl = new URL("https://www.google.com/m8/feeds/contacts/wichtigtuerbraun@googlemail.com/full");
    	  return contactsservice.insert(postUrl, contact);
    	 
    	}
    
    
    public static void addContactPhoto(ContactEntry entry,  byte[] photoData)
    	      throws ServiceException, IOException {
    	
    	    Link photoLink = entry.getContactPhotoLink();
    	    URL photoUrl = new URL(photoLink.getHref());

    	    GDataRequest request = contactsservice.createRequest(GDataRequest.RequestType.UPDATE,
    	        photoUrl, new ContentType("image/jpeg"));

    	    OutputStream requestStream = request.getRequestStream();
    	    requestStream.write(photoData);

    	    request.execute();
    	  }
    
    public static ContactEntry findContact(String id)
    	    throws ServiceException, IOException {
    	return contactsservice.getEntry(new URL(id.replace("/base/","/thin/")), ContactEntry.class);
    	
    }
    
    
    
    public static void printAllContacts()
    	    throws ServiceException, IOException {
    	  // Request the feed
    	  URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/wichtigtuerbraun@googlemail.com/full");
    	  ContactFeed resultFeed = contactsservice.getFeed(feedUrl, ContactFeed.class);
    	  // Print the results
    	  System.out.println(resultFeed.getTitle().getPlainText());
    	  for (int i = 0; i < resultFeed.getEntries().size(); i++) {
    	    ContactEntry entry = resultFeed.getEntries().get(i);
    	    System.out.println("\t" + entry.getTitle().getPlainText());

    	    System.out.println("Email addresses:");
    	    for (Email email : entry.getEmailAddresses()) {
    	      System.out.print(" " + email.getAddress());
    	      if (email.getRel() != null) {
    	        System.out.print(" rel:" + email.getRel());
    	      }
    	      if (email.getLabel() != null) {
    	        System.out.print(" label:" + email.getLabel());
    	      }
    	      if (email.getPrimary()) {
    	        System.out.print(" (primary) ");
    	      }
    	      System.out.print("\n");
    	    }

    	    System.out.println("IM addresses:");
    	    for (Im im : entry.getImAddresses()) {
    	      System.out.print(" " + im.getAddress());
    	      if (im.getLabel() != null) {
    	        System.out.print(" label:" + im.getLabel());
    	      }
    	      if (im.getRel() != null) {
    	        System.out.print(" rel:" + im.getRel());
    	      }
    	      if (im.getProtocol() != null) {
    	        System.out.print(" protocol:" + im.getProtocol());
    	      }
    	      if (im.getPrimary()) {
    	        System.out.print(" (primary) ");
    	      }
    	      System.out.print("\n");
    	    }

    	    System.out.println("Groups:");
    	    for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
    	      String groupHref = group.getHref();
    	      System.out.println("  Id: " + groupHref);
    	    }

    	    System.out.println("Extended Properties:");
    	    for (ExtendedProperty property : entry.getExtendedProperties()) {
    	      if (property.getValue() != null) {
    	        System.out.println("  " + property.getName() + "(value) = " +
    	            property.getValue());
    	      } else if (property.getXmlBlob() != null) {
    	        System.out.println("  " + property.getName() + "(xmlBlob)= " +
    	            property.getXmlBlob().getBlob());
    	      }
    	    }
    	    
    	    Link photoLink = entry.getContactPhotoLink();
    	    
    	    System.out.println("Photo Link: " + photoLink.getHref());

    	    if (photoLink.getEtag() != null) {
    	      System.out.println("Contact Photo's ETag: " + photoLink.getEtag());
    	    }

    	    System.out.println("Contact's ETag: " + entry.getEtag());
    	  }
    	}
    private static byte[] loadFileFromPersistentStoreToByte(File file) throws Exception, FileNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyStream(new FileInputStream(file), byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        System.out.println(data.length);
        return data;
      }
    
    private static void loadFileFromPersistentStore(File file) throws Exception, FileNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyStream(new FileInputStream(file), byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        System.out.println(data.length);
      }
    
    private static void copyStream(InputStream src, OutputStream dest) throws Exception {
        byte[] buffer = new byte[16384];
        int len;
        while ((len = src.read(buffer)) > 0) {
          dest.write(buffer, 0, len);
        }
        src.close();
        dest.close();
      }
    private static void loadImageFromMemory(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", byteArrayOutputStream);
        byte[] imageData = byteArrayOutputStream.toByteArray();
        System.out.println(imageData.length);
      }
    
    public static void printAllGroups()
    	      throws ServiceException, IOException {
    	    // Request the feed
    	    URL feedUrl = new URL("https://www.google.com/m8/feeds/groups/wichtigtuerbraun@googlemail.com/full");
    	    ContactGroupFeed resultFeed = contactsservice.getFeed(feedUrl, ContactGroupFeed.class);
    	    // Print the results
    	    System.out.println(resultFeed.getTitle().getPlainText());

    	    for (int i = 0; i < resultFeed.getEntries().size(); i++) {
    	      ContactGroupEntry groupEntry = resultFeed.getEntries().get(i);
    	      System.out.println("Id: " + groupEntry.getId());
    	      System.out.println("Group Name: " + groupEntry.getTitle().getPlainText());
    	      System.out.println("Last Updated: " + groupEntry.getUpdated());
    	      System.out.println("Extended Properties:");
    	      for (ExtendedProperty property : groupEntry.getExtendedProperties()) {
    	        if (property.getValue() != null) {
    	          System.out.println("  " + property.getName() + "(value) = " +
    	              property.getValue());
    	         } else if (property.getXmlBlob() != null) {
    	          System.out.println("  " + property.getName() + "(xmlBlob) = " +
    	              property.getXmlBlob().getBlob());
    	        }
    	      }
    	      System.out.println("Self Link: " + groupEntry.getSelfLink().getHref());
    	      if (!groupEntry.hasSystemGroup()) {
    	        // System groups do not have an edit link
    	        System.out.println("Edit Link: " + groupEntry.getEditLink().getHref());
    	        System.out.println("ETag: " + groupEntry.getEtag());
    	      }
    	      if (groupEntry.hasSystemGroup()) {
    	        System.out.println("System Group Id: " +
    	            groupEntry.getSystemGroup().getId());
    	      }
    	   }
    	}
    public static String findContactGroup(String title)
  	      throws ServiceException, IOException {
  	    // Request the feed
  	    URL feedUrl = new URL("https://www.google.com/m8/feeds/groups/wichtigtuerbraun@googlemail.com/full");
  	    ContactGroupFeed resultFeed = contactsservice.getFeed(feedUrl, ContactGroupFeed.class);
  	    // Print the results
  	    System.out.println(resultFeed.getTitle().getPlainText());

  	    for (int i = 0; i < resultFeed.getEntries().size(); i++) {
  	      ContactGroupEntry groupEntry = resultFeed.getEntries().get(i);
  	      System.out.println("Id: " + groupEntry.getId());
  	      System.out.println("Group Name: " + groupEntry.getTitle().getPlainText());
  	      System.out.println("Last Updated: " + groupEntry.getUpdated());
  	      System.out.println("Extended Properties:");
  	      for (ExtendedProperty property : groupEntry.getExtendedProperties()) {
  	        if (property.getValue() != null) {
  	          System.out.println("  " + property.getName() + "(value) = " +
  	              property.getValue());
  	         } else if (property.getXmlBlob() != null) {
  	          System.out.println("  " + property.getName() + "(xmlBlob) = " +
  	              property.getXmlBlob().getBlob());
  	        }
  	      }
  	      System.out.println("Self Link: " + groupEntry.getSelfLink().getHref());
  	      if (!groupEntry.hasSystemGroup()) {
  	        // System groups do not have an edit link
  	        System.out.println("Edit Link: " + groupEntry.getEditLink().getHref());
  	        System.out.println("ETag: " + groupEntry.getEtag());
  	      }
  	      if (groupEntry.hasSystemGroup()) {
  	        System.out.println("System Group Id: " +
  	            groupEntry.getSystemGroup().getId());
  	      }
  	      if (groupEntry.getTitle().getPlainText().equals(title)) return groupEntry.getId();
  	   }
  	    return null;
  	}
    public static ContactEntry updateContactEntry(
    	    ContactEntry entryToUpdate)
    	    throws ServiceException, IOException {
    	  URL editUrl = new URL(entryToUpdate.getEditLink().getHref());
    	  return contactsservice.update(editUrl, entryToUpdate);
    	}
    
}
