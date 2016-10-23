package braunimmobilien.server.beantojson;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.springframework.beans.factory.annotation.Autowired;


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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;



import java.util.Properties;


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
	private static String database="person";
	private static String id="1";
	
	
	
	
  
	/**
	 * @param args
	 */
	  
	
	public void yahp(String[] args) {
		
		if(args.length!=3){
			LOG.error("Die Anzahl der Parameter stimmt nicht");
	System.exit(1);
		}
	
		

		directory=args[0];
		
		database=args[1];
		id=args[2];
		
	LOG.info("work directory : "+directory);
	LOG.info(" database "+database);
	LOG.info(" id "+id);
	
	kundeManager.reindex();
	personManager.reindex();
	
   
    try{
  if(database.equals("person")) {
	 Personen person=personManager.get(new Long(id));
	  ObjectMapper mapper = new ObjectMapper(); 
	  mapper.writeValue(new File(directory+"/person.json"), person);

	//Object to JSON in String
	String jsonInString = mapper.writeValueAsString(person);
	  
  }
    
    
}
catch(Exception e){System.out.println("Exception "+e);
System.exit(1);}
	   
	   System.exit(1);
	}
	
	
	

	
}
