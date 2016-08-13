package org.apache.cocoon.sample.controller;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.cocoon.configuration.Settings;
import org.apache.cocoon.rest.controller.annotation.RESTController;
import org.apache.cocoon.rest.controller.annotation.RequestParameter;
import org.apache.cocoon.rest.controller.annotation.SitemapParameter;
import org.apache.cocoon.rest.controller.method.Get;
import org.apache.cocoon.rest.controller.response.RestResponse;
import org.apache.cocoon.rest.controller.response.URLResponse;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Locale.GERMANY;

import java.util.Locale;

import static java.text.DateFormat.FULL;

import java.text.DateFormat;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.cocoon.Configuration;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Angstatus;
import braunimmobilien.model.Personen;

import java.util.ArrayList;
import java.util.List;
@RESTController
public class BraunimmobilienFlexibleRESTController implements Get {
  @Autowired
    private AngebotManager angebotManager;
    @Autowired
    private NachweiseManager nachweiseManager;
    @Autowired
	private Configuration configuration;
      @Autowired
    private KundeManager kundenManager;
      @Autowired
      private PersonManager personManager;
  @RequestParameter
    private String id;
  private static final Logger logger = LoggerFactory.getLogger(BraunimmobilienFlexibleRESTController.class);

    @RequestParameter
    private String name;

    @RequestParameter
    private String reqparam;

    @Autowired
    private Settings settings;

    @Override
    public RestResponse doGet() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
      logger.debug("whichid "+id+" name "+name);
      
      
          if(id.equals("kunde")){
      
       Kunde kunde=null;
        data.put("id", this.id);
        data.put("name", this.name);
        data.put("reqparam", this.reqparam);
        logger.debug("before database Kundentable access"); 
      try{  kunde=kundenManager.get(new Long(this.name));
    logger.debug("database Kundentable access successful 1 "); 
   Iterator it= kunde.getNachweise().iterator();
    while(it.hasNext()){
    Nachweise nachweis=(Nachweise)it.next();
  if(nachweis.getAngebot()!=null){ 
   nachweis.getAngebot().setAngobjzuords(null);
   nachweis.getAngebot().setNachweise(null);
   nachweis.getAngebot().setNachweise1(null);
   nachweis.getAngebot().setNachweise2(null);
	  nachweis.getAngebot().setNachweise(null);
	   nachweis.getAngebot().setNachweise1(null);
	   nachweis.getAngebot().setNachweise2(null);}
	    
	  if(nachweis.getAngebot1()!=null){
		  nachweis.getAngebot1().setAngobjzuords(null);
		   nachweis.getAngebot1().setNachweise(null);
		   nachweis.getAngebot1().setNachweise1(null);
		   nachweis.getAngebot1().setNachweise2(null);
			  nachweis.getAngebot1().setNachweise(null);
			   nachweis.getAngebot1().setNachweise1(null);
			   nachweis.getAngebot1().setNachweise2(null);}
	
   nachweis.setAngebot1(null);
   nachweis.setAngebot2(null);
   nachweis.setPerson(null);
   nachweis.setObjekt(null);
   nachweis.setKunde(null);
   nachweis.setMitarbeiter(null);}
  //  kunde.setNachweise(null);
    kunde.getPerson().setEigentuemermuster(null);
    kunde.getPerson().setKunden(null);
    kunde.getPerson().setMitarbeiter(null);
    kunde.getPerson().setObjperszuords(null);
    kunde.getPerson().setScouts(null);
    kunde.getPerson().getStrasse().setPersonen(null);
    kunde.getPerson().getStrasse().setObjekte(null);
    kunde.getPerson().getStrasse().getOrt().setStrassen(null);
//    kunde.setPerson(null);
      }
    catch(Exception ex){
     logger.error("database Kundentable access fault "+ex); 
 
    }
      logger.debug("database Kundentable access successful "+kunde.getId()); 
       data.put("kunde", kunde);
         Locale locale = GERMANY;
		    int style = FULL;
		    DateFormat fmt = null;
		        fmt = DateFormat.getDateInstance(style, locale);
         data.put("now",fmt.format(new Date()) );
      data.put("testProperty", this.settings.getProperty("testProperty"));
 try{  if(this.reqparam.equals("1"))
   return new URLResponse("servlet:/controllerbraun/xml/"+id, data);
  if(this.reqparam.equals("2"))
   return new URLResponse("servlet:/controllerbraun/html/"+id, data);
   if(this.reqparam.equals("3"))
   return new URLResponse("servlet:/controllerbraun/pdf/"+id, data);
   if(this.reqparam.equals("4"))
	   return new URLResponse("servlet:/controllerbraun/txt/"+id, data);
return new URLResponse("servlet:/controllerbraun/error", data);
 }
 catch(Exception ex){
 logger.error("controller fault "+ex);
 }

 }      
      

          if(id.equals("angebotletzt")){
                Locale locale = GERMANY;
       		    int style = FULL;
       		    DateFormat fmt = null;
       		        fmt = DateFormat.getDateInstance(style, locale);
                data.put("now",fmt.format(new Date()) );
             data.put("testProperty", this.settings.getProperty("testProperty"));
        try{  if(this.reqparam.equals("1"))
          return new URLResponse("servlet:/controllerbraun/xml/"+id, data);
         if(this.reqparam.equals("2"))
          return new URLResponse("servlet:/controllerbraun/html/"+id, data);
          if(this.reqparam.equals("3"))
          return new URLResponse("servlet:/controllerbraun/pdf/"+id, data);
          if(this.reqparam.equals("4"))
       	   return new URLResponse("servlet:/controllerbraun/txt/"+id, data);
       return new URLResponse("servlet:/controllerbraun/error", data);
        }
        catch(Exception ex){
        logger.error("controller fault "+ex);
        }

        }      
          
          
      
      
   if(id.equals("exposee")){
      
        data.put("id", this.id);
        data.put("name", this.name);
        data.put("reqparam", this.reqparam);
        
         Locale locale = GERMANY;
		    int style = FULL;
		    DateFormat fmt = null;
		        fmt = DateFormat.getDateInstance(style, locale);
         data.put("now",fmt.format(new Date()) );
      data.put("testProperty", this.settings.getProperty("testProperty"));
 try{  if(this.reqparam.equals("1"))
   return new URLResponse("servlet:/controllerexposee/xml/"+name, data);
  if(this.reqparam.equals("2"))
   return new URLResponse("servlet:/controllerexposee/html/"+name, data);
   if(this.reqparam.equals("3"))
   return new URLResponse("servlet:/controllerexposee/pdf/"+name+".pdf", data);
   if(this.reqparam.equals("4"))
	   return new URLResponse("servlet:/controllerexposee/txt/"+name, data);
return new URLResponse("servlet:/controllerbraun/error", data);
 }
 catch(Exception ex){
 logger.error("controller fault "+ex);
 }

 }           
      
   if(id.equals("xfreeangebot")){
	   List<Angebot> angebotlist=new  ArrayList<Angebot>();   
       data.put("id", this.id);
       data.put("name", this.name);
       data.put("reqparam", this.reqparam);
       System.err.println("ssssssssssssss "+name);
       List<Angebot> angebote=angebotManager.search(name);
       System.err.println("ssssssssssssss "+angebote.size());
       Iterator angeboteiterator=angebote.iterator();
   	while(angeboteiterator.hasNext()){
   	Angebot angebot=(Angebot)angeboteiterator.next();
   if(angebot.getId().startsWith("RH")){
    			   if(angebot.getAngobjzuords()!=null){ 
    			   Iterator angobjzuordsiterator=angebot.getAngobjzuords().iterator();
    			   while(angobjzuordsiterator.hasNext()){
    	    	Angobjzuord angobjzuord=(Angobjzuord)angobjzuordsiterator.next();
    			   angobjzuord.setAngebot(null);
    			   angobjzuord.getObjekte().setAngobjzuords(null);
    			   angobjzuord.getObjekte().setNachweise(null);
    			   angobjzuord.getObjekte().setObjperszuords(null);
    			   angobjzuord.getObjekte().setScouts(null);
    			   angobjzuord.getObjekte().getStrasse().setObjekte(null);
    			   angobjzuord.getObjekte().getStrasse().setPersonen(null);
    			   angobjzuord.getObjekte().getStrasse().getOrt().setStrassen(null);
    			   angobjzuord.getObjekte().getStrasse().getOrt().getLand().setOrte(null);  
    		   } 
    			   }
    			   
    			   int i=0;
    			   Iterator it= angebot.getNachweise().iterator();
    			    while(it.hasNext()){
    			    	if(i>50) break;
    			    	++i;
    			    Nachweise nachweis=(Nachweise)it.next();
    			    nachweis.setAngebot(null);
    			   nachweis.setAngebot1(null);
    			   nachweis.setAngebot2(null);
    			   nachweis.setPerson(null);
    			   nachweis.setObjekt(null);
    			   if(nachweis.getKunde()!=null){
    				   if(nachweis.getKunde().getPerson()!=null){
    				   nachweis.getKunde().getPerson().setEigentuemermuster(null);
    				   nachweis.getKunde().getPerson().setKunden(null);
    				  if( nachweis.getKunde().getPerson().getEigtFirma()!=null)  nachweis.getKunde().getPerson().setEigtFirma(nachweis.getKunde().getPerson().getEigtFirma().replaceAll("&", "&amp;"));
    				  if( nachweis.getKunde().getPerson().getEigtName()!=null) nachweis.getKunde().getPerson().setEigtName( nachweis.getKunde().getPerson().getEigtName().replaceAll("&", "&amp;"));
    				   nachweis.getKunde().getPerson().setMitarbeiter(null);
    				   nachweis.getKunde().getPerson().setObjperszuords(null);
    				   nachweis.getKunde().getPerson().setScouts(null);
    				   nachweis.getKunde().getPerson().getStrasse().setPersonen(null);
    				   nachweis.getKunde().getPerson().getStrasse().setObjekte(null);
    				   nachweis.getKunde().getPerson().getStrasse().getOrt().setStrassen(null);   
    				   nachweis.getKunde().setNachweise(null);
    				   nachweis.getKunde().getPerson().setNachweise(null);
    				//   nachweis.getKunde().setPerson(null);
    			   }}
    			   //nachweis.setKunde(null);
    			   nachweis.setMitarbeiter(null);}
    			   
    			   
    			 
    			  
   angebotlist.add(angebot);}
   	}
        Locale locale = GERMANY;
		    int style = FULL;
		    DateFormat fmt = null;
		        fmt = DateFormat.getDateInstance(style, locale);
        data.put("now",fmt.format(new Date()) );
     data.put("testProperty", this.settings.getProperty("testProperty"));
data.put("angebotlist", angebotlist);
     try{  if(this.reqparam.equals("1"))
  return new URLResponse("servlet:/controllerlisteangebot/xml/"+id, data);
 if(this.reqparam.equals("2"))
  return new URLResponse("servlet:/controllerlisteangebot/html/"+id, data);
  if(this.reqparam.equals("3"))
  return new URLResponse("servlet:/controllerlisteangebot/pdf/"+id, data);
  if(this.reqparam.equals("4"))
	   return new URLResponse("servlet:/controllerlisteangebot/txt/"+name, data);
return new URLResponse("servlet:/controllerbraun/error", data);
}
catch(Exception ex){
logger.error("controller fault "+ex);
}

}   
   
   
   if(id.equals("xfree")){
	   List<Personen> personenlist=new  ArrayList<Personen>();   
       data.put("id", this.id);
       data.put("name", this.name);
       data.put("reqparam", this.reqparam);
       System.err.println("ssssssssssssss "+name);
       List<Personen> personen=personManager.search(name);
       System.err.println("ssssssssssssss "+personen.size());
       Iterator personeniterator=personen.iterator();
   	while(personeniterator.hasNext()){
   	Personen person=(Personen)personeniterator.next();
   	if(person.getEigtFirma()!=null)   	person.setEigtFirma(person.getEigtFirma().replaceAll("&", "&amp;"));
   	if(person.getEigtInfo()!=null) 	person.setEigtInfo(person.getEigtInfo().replaceAll("&", "&amp;"));
   	if(person.getEigtName()!=null)   	person.setEigtName(person.getEigtName().replaceAll("&", "&amp;"));
    person.setEigentuemermuster(null);
    Iterator kundeniterator=person.getKunden().iterator();
    while(kundeniterator.hasNext()){
    	Kunde kunde=(Kunde)kundeniterator.next();
    	   Iterator nachweiseiterator=kunde.getNachweise().iterator();
    	   while(nachweiseiterator.hasNext()){
    		   Nachweise nachweis=(Nachweise)nachweiseiterator.next();
    		   if(nachweis.getAngebot()!=null){ 
    			   if(nachweis.getAngebot().getAngobjzuords()!=null){ 
    			   Iterator angobjzuordsiterator=nachweis.getAngebot().getAngobjzuords().iterator();
    			   while(angobjzuordsiterator.hasNext()){
    	    		   Angobjzuord angobjzuord=(Angobjzuord)angobjzuordsiterator.next();
    			   angobjzuord.setAngebot(null);
    			   angobjzuord.getObjekte().setAngobjzuords(null);
    			   angobjzuord.getObjekte().setNachweise(null);
    			   angobjzuord.getObjekte().setObjperszuords(null);
    			   angobjzuord.getObjekte().setScouts(null);
    			   angobjzuord.getObjekte().getStrasse().setObjekte(null);
    			   angobjzuord.getObjekte().getStrasse().setPersonen(null);
    			   angobjzuord.getObjekte().getStrasse().getOrt().setStrassen(null);
    			   angobjzuord.getObjekte().getStrasse().getOrt().getLand().setOrte(null);  
    		   } 
    			   }
    			//   nachweis.getAngebot().setAngobjzuords(null);
    			   nachweis.getAngebot().setNachweise(null);
    			   nachweis.getAngebot().setNachweise1(null);
    			   nachweis.getAngebot().setNachweise2(null);
    				  nachweis.getAngebot().setNachweise(null);
    				   nachweis.getAngebot().setNachweise1(null);
    				   nachweis.getAngebot().setNachweise2(null);}
    				    
    			
    	   nachweis.setAngebot1(null);
    	   nachweis.setAngebot2(null);
    	   nachweis.setKunde(null);
    	   nachweis.setMitarbeiter(null);
    	   nachweis.setPerson(null);   
    	   }
    	   kunde.setPerson(null);
    }
    person.setMitarbeiter(null);
    person.setObjperszuords(null);
    person.setScouts(null);
    person.getStrasse().setPersonen(null);
    person.getStrasse().setObjekte(null);
    person.getStrasse().getOrt().setStrassen(null);
   	personenlist.add(person);
 }
 java.util.Collections.sort(personenlist);
 
        Locale locale = GERMANY;
		    int style = FULL;
		    DateFormat fmt = null;
		        fmt = DateFormat.getDateInstance(style, locale);
        data.put("now",fmt.format(new Date()) );
     data.put("testProperty", this.settings.getProperty("testProperty"));
data.put("personenlist", personenlist);
     try{  if(this.reqparam.equals("1"))
  return new URLResponse("servlet:/controllerliste/xml/"+id, data);
 if(this.reqparam.equals("2"))
  return new URLResponse("servlet:/controllerliste/html/"+id, data);
  if(this.reqparam.equals("3"))
  return new URLResponse("servlet:/controllerliste/pdf/"+id, data);
  if(this.reqparam.equals("4"))
	   return new URLResponse("servlet:/controllerliste/txt/"+name, data);
return new URLResponse("servlet:/controllerbraun/error", data);
}
catch(Exception ex){
logger.error("controller fault "+ex);
}

}                  
   
   
   
      
   if(id.equals("xbrief")){
      
        data.put("id", this.id);
        data.put("name", this.name);
        data.put("reqparam", this.reqparam);
        data.put("configuration", this.configuration);
         Locale locale = GERMANY;
		    int style = FULL;
		    DateFormat fmt = null;
		        fmt = DateFormat.getDateInstance(style, locale);
         data.put("now",fmt.format(new Date()) );
      data.put("testProperty", this.settings.getProperty("testProperty"));
      
      Long nachweisid=new Long(Long.parseLong(this.name));
	    Nachweise nachweis=nachweiseManager.get(nachweisid);
	    logger.debug("access nachweis successful "+this.name+"  "+this.name+"  "+nachweis.getId());
	    if(nachweis.getAngebot()!=null){
	    Iterator it=nachweis.getAngebot().getAngobjzuords().iterator();
	  while (it.hasNext()){
	   Angobjzuord angobjzuord=(Angobjzuord)it.next();
	   logger.debug("nachweis angobjzuord "+this.name+"  "+this.name+"  "+angobjzuord.getId());
	  angobjzuord.getObjekte().setAngobjzuords(null);
	   angobjzuord.getObjekte().setNachweise(null);
	   angobjzuord.getObjekte().setObjperszuords(null);
	   angobjzuord.getObjekte().setScouts(null);
	   angobjzuord.getObjekte().getStrasse().setObjekte(null);
	   angobjzuord.getObjekte().getStrasse().setPersonen(null);
	   angobjzuord.getObjekte().getStrasse().getOrt().setStrassen(null);
	   angobjzuord.getObjekte().getStrasse().getOrt().getLand().setOrte(null);
	  
	  }  
	  nachweis.getAngebot().setNachweise(null);
	   nachweis.getAngebot().setNachweise1(null);
	   nachweis.getAngebot().setNachweise2(null);}
	    
	  if(nachweis.getAngebot1()!=null){
		  nachweis.getAngebot1().setNachweise(null);
	   nachweis.getAngebot1().setNachweise1(null);
	   nachweis.getAngebot1().setNachweise2(null);}
	 //   nachweis.setKunde(null);
	   logger.debug("Angebot fertig");
	  
	   if(nachweis.getObjekt()!=null){
			  
			  nachweis.getObjekt().setAngobjzuords(null);
			  nachweis.getObjekt().setNachweise(null);
			  nachweis.getObjekt().setObjperszuords(null);
			  nachweis.getObjekt().setScouts(null);
			  nachweis.getObjekt().getStrasse().setObjekte(null);
			  nachweis.getObjekt().getStrasse().setPersonen(null);
			  nachweis.getObjekt().getStrasse().getOrt().setStrassen(null);
			  nachweis.getObjekt().getStrasse().getOrt().getLand().setOrte(null);
			  
			  }
		  logger.debug("Objekt fertig");
		  
	   
	   
	   
	   if(nachweis.getKunde()!=null){  
		   nachweis.getKunde().setNachweise(null);
		   nachweis.getKunde().getPerson().setObjperszuords(null);
	    nachweis.getKunde().getPerson().setScouts(null);
	    nachweis.getKunde().getPerson().setNachweise(null);
	    nachweis.getKunde().getPerson().setMitarbeiter(null);
	    nachweis.getKunde().getPerson().setKunden(null);
	      nachweis.getKunde().getPerson().setEigentuemermuster(null);
	    nachweis.getKunde().getPerson().getStrasse().getOrt().setStrassen(null);;
	    nachweis.getKunde().getPerson().getStrasse().getOrt().getLand().setOrte(null);;
	    nachweis.getKunde().getPerson().getStrasse().setObjekte(null);
	   nachweis.getKunde().getPerson().getStrasse().setPersonen(null);}
	   logger.debug("Kunde fertig");
	   if(nachweis.getPerson()!=null){  
		 
		   /* 	   nachweis.getPerson().setObjperszuords(null);
	   nachweis.getPerson().setScouts(null);
	    nachweis.getPerson().setNachweise(null);
	    nachweis.getPerson().setMitarbeiter(null);
	    nachweis.getPerson().setKunden(null);
	      nachweis.getPerson().setEigentuemermuster(null);
	    nachweis.getPerson().getStrasse().getOrt().setStrassen(null);;
	    nachweis.getPerson().getStrasse().getOrt().getLand().setOrte(null);;
	    nachweis.getKunde().getPerson().getStrasse().setObjekte(null);
	   nachweis.getPerson().getStrasse().setPersonen(null);*/
	   }
	   logger.debug("person fertig");
		
	   //   nachweis.getKunde().setPerson(null);
	    // ;
	   if(nachweis.getMitarbeiter()!=null){    
	    nachweis.getMitarbeiter().getPerson().setObjperszuords(null);
	    nachweis.getMitarbeiter().getPerson().setScouts(null);
	    nachweis.getMitarbeiter().getPerson().setNachweise(null);
	    nachweis.getMitarbeiter().getPerson().setMitarbeiter(null);
	    nachweis.getMitarbeiter().getPerson().setKunden(null);
	    nachweis.getMitarbeiter().getPerson().setEigentuemermuster(null);
	    nachweis.getMitarbeiter().getPerson().getStrasse().getOrt().setStrassen(null);;
	    nachweis.getMitarbeiter().getPerson().getStrasse().getOrt().getLand().setOrte(null);;
	    nachweis.getMitarbeiter().getPerson().getStrasse().setObjekte(null);
	    nachweis.getMitarbeiter().getPerson().getStrasse().setPersonen(null);
	   } 
       logger.debug("nachweis fertig");
      data.put("nachweis",nachweis);
    
      
 try{  if(this.reqparam.equals("1"))
   return new URLResponse("servlet:/controllerxbrief/xml/nachweis", data);
  if(this.reqparam.equals("2"))
   return new URLResponse("servlet:/controllerxbrief/html/"+name, data);
   if(this.reqparam.equals("3"))
   return new URLResponse("servlet:/controllerxbrief/pdf/nachweis", data);
   if(this.reqparam.equals("4"))
	   return new URLResponse("servlet:/controllerxbrief/txt/"+name, data);
return new URLResponse("servlet:/controllerbraun/error", data);
 }
 catch(Exception ex){
 logger.error("controller fault "+ex);
 }

 }               
      
      
      
      
      
      
      
      
      
      
      
      
      if(id.equals("angebotobjektgeber")||id.equals("angebotich")){
      
       Angebot angebot=null;
        data.put("id", this.id);
        data.put("name", this.name);
        data.put("reqparam", this.reqparam);
      try{  angebot=angebotManager.get(this.name);
    logger.debug("database access successful 1 "); 
        Iterator it= angebot.getAngobjzuords().iterator();
        while(it.hasNext()){
        Angobjzuord angobjzuord=(Angobjzuord)it.next();
       
        
        angobjzuord.getObjekte().setObjperszuords(null);
        angobjzuord.getObjekte().setAngobjzuords(null);
         angobjzuord.getObjekte().setNachweise(null);
          angobjzuord.getObjekte().setScouts(null);
          
        
       
        angobjzuord.getObjekte().getStrasse().getOrt().setStrassen(null);
          angobjzuord.getObjekte().getStrasse().setObjekte(null);
          angobjzuord.getObjekte().getStrasse().setPersonen(null);
     angobjzuord.getObjekte().getStrasse().getOrt().getLand().setOrte(null);
 //      angobjzuord.getObjekte().getStrasse().setOrt(null); löst Problem nicht.
 //angobjzuord.getObjekte().setStasse(null); löst Problem.
        }
    //    angebot.setAngobjzuords(null);
        it= angebot.getNachweise().iterator();
        while(it.hasNext()){
        Nachweise nachweis=(Nachweise)it.next();
       nachweis.setAngebot(null);
       nachweis.setAngebot1(null);
       nachweis.setAngebot2(null);
       nachweis.setPerson(null);
       nachweis.setObjekt(null);
       nachweis.getKunde().setNachweise(null);
       System.err.println( nachweis.getKunde().getPerson().getEigtName());
       nachweis.getKunde().getPerson().setEigentuemermuster(null);
       nachweis.getKunde().getPerson().setKunden(null);
       nachweis.getKunde().getPerson().setMitarbeiter(null);
       nachweis.getKunde().getPerson().setObjperszuords(null);
       nachweis.getKunde().getPerson().setScouts(null);
       nachweis.getKunde().getPerson().getStrasse().setPersonen(null);
       nachweis.getKunde().getPerson().getStrasse().setObjekte(null);
       nachweis.getKunde().getPerson().getStrasse().getOrt().setStrassen(null);
       nachweis.setMitarbeiter(null);
//   nachweis.setKunde(null);
        }   
    //     angebot.setNachweise(null);
         angebot.setNachweise1(null);
         angebot.setNachweise2(null);
         }
    catch(Exception ex){
     logger.error("database access fault "+ex); 
  //   return new URLResponse("servlet:/controllerbraun/error", data);
    }
   //   logger.debug("database access successful "+angebot); 
       data.put("angebot", angebot);
         Locale locale = GERMANY;
		    int style = FULL;
		    DateFormat fmt = null;
		        fmt = DateFormat.getDateInstance(style, locale);
         data.put("now",fmt.format(new Date()) );
      data.put("testProperty", this.settings.getProperty("testProperty"));
 try{  if(this.reqparam.equals("1"))
   return new URLResponse("servlet:/controllerbraun/xml/"+id, data);
  if(this.reqparam.equals("2"))
   return new URLResponse("servlet:/controllerbraun/html/"+id, data);
   if(this.reqparam.equals("3"))
   return new URLResponse("servlet:/controllerbraun/pdf/"+id, data);
   if(this.reqparam.equals("4"))
	   return new URLResponse("servlet:/controllerbraun/txt/"+id, data);
return new URLResponse("servlet:/controllerbraun/error", data);
 }
 catch(Exception ex){
 logger.error("controller fault "+ex);
 }

 }      
return null;}
}
