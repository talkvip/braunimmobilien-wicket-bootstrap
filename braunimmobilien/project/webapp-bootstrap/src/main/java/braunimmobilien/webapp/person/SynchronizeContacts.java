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
import com.google.gdata.data.Link;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Strassen;
import braunimmobilien.bootstrap.webapp.WicketApplication;

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
	private static ContactsService myService;

	
	SynchronizeContacts() throws AuthenticationException{
	myService = new ContactsService("braunimmobiliencontactservice");	
	authenticate();
}

public	SynchronizeContacts(WicketApplication app) throws AuthenticationException{
		myService = app.getContactsService();
	}
  
 
  
    private void authenticate() throws AuthenticationException{
    	
        myService.setUserCredentials("wichtigtuer.braun@googlemail.com", "ArtImSapFenzel1911");
            System.out.println( "Authenticated" );
        
    }
    
    public static void deleteContact(String name)
    	    throws ServiceException, IOException {
   
    	
    	URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/wichtigtuerbraun@googlemail.com/full");
 
    	while(true) {	  ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
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
    	  return myService.insert(postUrl, contact);
    	 
    	}
    
    
    public static void addContactPhoto(ContactEntry entry,  byte[] photoData)
    	      throws ServiceException, IOException {
    	
    	    Link photoLink = entry.getContactPhotoLink();
    	    URL photoUrl = new URL(photoLink.getHref());

    	    GDataRequest request = myService.  createRequest(GDataRequest.RequestType.UPDATE,
    	        photoUrl, new ContentType("image/jpeg"));

    	    OutputStream requestStream = request.getRequestStream();
    	    requestStream.write(photoData);

    	    request.execute();
    	  }
    
    public static ContactEntry findContact(String id)
    	    throws ServiceException, IOException {
    	return myService.getEntry(new URL(id.replace("/base/","/thin/")), ContactEntry.class);
    	
    }
    
    
    
    public static void printAllContacts()
    	    throws ServiceException, IOException {
    	  // Request the feed
    	  URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/wichtigtuerbraun@googlemail.com/full");
    	  ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
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
    	    ContactGroupFeed resultFeed = myService.getFeed(feedUrl, ContactGroupFeed.class);
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
  	    ContactGroupFeed resultFeed = myService.getFeed(feedUrl, ContactGroupFeed.class);
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
    	  return myService.update(editUrl, entryToUpdate);
    	}
    
}
