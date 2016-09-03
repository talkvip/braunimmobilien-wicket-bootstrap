package org.apache.cocoon.sample.controller;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.TypeManager;
import braunimmobilien.util.ScoutUtil;
import braunimmobilien.cocoon.Configuration;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Angstatus;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;

import java.util.ArrayList;
import java.util.List;
@RESTController
public class BraunimmobilienScoutController implements Get {
	private static ScoutUtil scoutUtil=null;
	@Autowired
	private  EigentuemermusterManager eigentuemermusterManager;
	  @Autowired
		private Configuration configuration;
	  @Autowired
		private  StrassenManager strassenManager;
	@Autowired
	private  ObjektartManager objektartManager;
	  @Autowired
		private  ObjektManager objektManager;
	@Autowired
	private  ObjektsuchManager objektsuchManager;
	  @Autowired
		private PersonManager personManager;
	@Autowired
	private  ObjarttypManager objarttypManager;
 @Autowired
	private  OrteManager orteManager;
	
	@Autowired
		private  TypeManager typeManager;
	@Autowired
		private  ScoutManager scoutManager;
	private static String objarttyp="1";
	private static String ortid="1";	
	
	@Autowired
    private AngebotManager angebotManager;
    @Autowired
    private NachweiseManager nachweiseManager;
      @Autowired
    private KundeManager kundenManager;
  @RequestParameter
    private String id;
  private static final Logger logger = LoggerFactory.getLogger(BraunimmobilienScoutController.class);

    @RequestParameter
    private String reqparam;

    @Autowired
    private Settings settings;
    private static Hashtable<String,Hashtable<String,String>> searchTable=new Hashtable<String,Hashtable<String,String>>(){{
		  put("where", new Hashtable<String,String>(){{
			  
			  put("data-qa","is24-expose-address");  
			  
	  }});
put("what", new Hashtable<String,String>(){{
			  
			  put("id","expose-title");  
			  
	  }});
put("what1", new Hashtable<String,String>(){{
			  
			  put("class","is24-ex-details");  
			  
	  }});
put("what2", new Hashtable<String,String>(){{
	 
	  put("class","is24qa-objektbeschreibung is24-pre");  
	  
}});
put("what3", new Hashtable<String,String>(){{
	
	  put("class","is24qa-ausstattung is24-pre");  
	  
}});
put("what4", new Hashtable<String,String>(){{
	  
	  put("class","is24qa-lage is24-pre");  
	  
}});
put("who", new Hashtable<String,String>(){{
	  
	  put("class","is24-ex-realtor-s padding grid");  
	  
}});
/*put("who", new Hashtable<String,String>(){{

put("class","is24-realtor grid-item one-third");  

}});*/
put("who1", new Hashtable<String,String>(){{

put("id","is24-expose-realtor-box");  

}});
/* put("who1", new Hashtable<String,String>(){{

put("class","is24-address padding-vertical-s");  

}});*/
put("phone", new Hashtable<String,String>(){{

put("class","is24-phone-number hide");  

}});
}}; 
    @Override
    public RestResponse doGet() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
      logger.debug("whichid "+id+" reqparam "+reqparam);
      int i=0;
      int notworkedout=0;
          if(id.equals("mietehannover")){
        	  
        	  scoutUtil=new ScoutUtil(strassenManager,objektartManager,objektManager,scoutManager,objektsuchManager,orteManager,personManager);
        	   	 Hashtable<String, Integer> ht = new Hashtable<String, Integer>(); 
        	    	 List mietenhannover=(List)configuration.getProgress().get("mietenhannover");
        	    	 Iterator it=mietenhannover.iterator();
        	    	  try{ while(it.hasNext()){
        	    		 LinkedHashMap<String, String> table=(LinkedHashMap<String, String>)it.next();
        	    		
        	    		  if(table.containsKey("ortid")) ortid=table.get("ortid");
        			            System.err.println("Configuration mietenhannover : "+table.get("address")+"  "+table.get("idpattern"));
        			         if(table.get("iterate").equals("no")){
        			            if (getScoutidListFromNet(table.get("address"),table.get("idpattern"),ht)) {
        			   	    		 System.err.println("1 te Seite");
        			   	                Thread.sleep(250);
        			   	          
        			   	     }
        			         }
        			         if(table.get("iterate").equals("yes")){
        			        	 int faktor=Integer.parseInt(table.get("start"));
        			        	 Integer faktor1=new Integer(faktor);
        				            while (getScoutidListFromNet(table.get("address").replace("§i§",faktor1.toString()),table.get("idpattern"),ht)) {
        				   	    	 System.err.println(faktor+ " te Seite");
        				   	                Thread.sleep(250);
        				   	           ++faktor;
        				   	        faktor1=new Integer(faktor);
        				   	     }
        				         }
        	    	 }
        	    	
        		    	  System.out.println("Anzahl - " + ht.size()); 
        		    	  data.put("scoutids", ht);
        		    	  data.put("size",  ht.size());
        		    	 		
        		    	    	 Enumeration<String> keys = ht.keys(); 
        		    		        while (keys.hasMoreElements()) { 
        		    		        	
        		    		            String key = keys.nextElement(); 
        		    		          Integer  s = ht.get(key); 
        		    		          if (s==0) continue;
        		    		         
        		    		            if(!checkExist(key,scoutManager)){	
        		    		            	++i;
        		    		            	   System.out.println(key + " - " + s); 
        		    		            	if(getDefinitiveData(key,eigentuemermusterManager,scoutManager,orteManager,objarttypManager,typeManager,personManager)) ++notworkedout; 
        		    					
        		    			        
        		    			         ht.put(key, new Integer(0));}
        		    			                Thread.sleep(1000);  
        		    		            }
        	    
        		   }
        	    	 
        		   catch (Exception e) {
        			   data.put("exception",e);} 
        	  
        	  
        	  
        	  
          }
          data.put("notworkedout", notworkedout);
          data.put("id", this.id);
          data.put("new", i);
          data.put("reqparam", this.reqparam);
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
   return null; }
    
    public static boolean  getScoutidListFromNet (String href,String xpath,Hashtable<String, Integer> ht)throws java.io.IOException{
    	int check=0;
		org.jsoup.nodes.Document document=null;
		document = Jsoup.connect(href).get();
			Elements elements=document.getElementsByTag("a");
			 for (Element a : elements) {			 
				if( a.hasAttr("href")&&a.attr("href").matches(xpath)){
					 System.err.println("Check "+ht.get(a.attr("href").substring(8)));
			if(ht.get(a.attr("href").substring(8))!=null) ht.put(a.attr("href").substring(8),ht.get(a.attr("href").substring(8))+1);
			else 	{ht.put(a.attr("href").substring(8),new Integer(1));
			check=check+1;
			}
				 }
				 }
			 System.err.println("Seite holen");
			 if (check>0) return true;
			 return false;
		        }	
    
    public static boolean checkExist(String scoutid,ScoutManager scoutManager)
	{
		   try{
			   System.err.println(" checkExist "+scoutManager.exists(new Long(scoutid)));
		return scoutManager.exists(new Long(scoutid));   
		   }
		catch(Exception ex){	
			System.out.println("error in check  "+ex);}
		return false;
	}
    
    
    public static boolean getDefinitiveData(String scoutid,EigentuemermusterManager eigentuemermusterManager,ScoutManager scoutManager,OrteManager orteManager,ObjarttypManager objarttypManager,TypeManager typeManager,PersonManager personManager) throws java.io.IOException
	{Scout scout=new Scout();
		org.jsoup.nodes.Document document=null;
			document = Jsoup.connect("http://www.immobilienscout24.de/expose/"+scoutid).get();
			scout.setId(new Long(scoutid));
			Date date=new Date();
						scout.setWhere(getPath(document,"where").replaceAll("'", ""));
						scout.setWhat(date.toString()+" "+getPath(document,"what").replaceAll("'", "")+getPath(document,"what1").replaceAll("'", "")+getPath(document,"what2").replaceAll("'", "")+getPath(document,"what3").replaceAll("'", "")+getPath(document,"what4").replaceAll("'", ""));
						 scout.setWho(getPath(document,"who").replaceAll("'", "")+" "+getPath(document,"who1").replaceAll("'", ""));
						 scout.setPhone(getPath(document,"phone").replaceAll("'", ""));
							 scout.setType(typeManager.get(1L));
							 System.err.println(" definitive Data "+scout.getId()+" "+scout.getWhere());
							   Iterator it=eigentuemermusterManager.getEigentuemermusters().iterator();
						try{	   scoutUtil.notexistObject(scout);}catch(braunimmobilien.util.ObjectNotIdentifiableException e){}
							   scout.setOrt(orteManager.get(new Long(ortid)));
							   while(it.hasNext()){
							   Eigentuemermuster eigentuemermuster=(Eigentuemermuster)it.next();
						 String pattern=eigentuemermuster.getEigentuemermuster();
						String suchstring1=scout.getWho().toLowerCase();
						String pattern1=pattern.toLowerCase();
						
						if(suchstring1.contains( pattern1.substring(1,pattern1.length()-1))){
							scout.setPerson(eigentuemermuster.getPerson());
							scout.setType(eigentuemermuster.getType());
							if (scout.getType().getId().longValue()==1&&scout.getObjekt()!=null) scout.setType(typeManager.get(3L));
							break;
							}
						   }   
							scout.setObjarttyp(objarttypManager.get(new Long(objarttyp)));	
							scout=	scoutManager.save(scout);
							existPerson(scout,personManager,scoutManager);
							if(scout.getType().getId().longValue()==1) return true;
							return false;
	}
    public static String getPath(org.jsoup.nodes.Document document,String attribute)
	{  
		if (searchTable.get(attribute)!=null){
		if (searchTable.get(attribute).keySet().size()==1){
		Iterator it=searchTable.get(attribute).keySet().iterator();
		while (it.hasNext()){
			String key=(String) it.next();
			
			 Elements divs = document.getElementsByAttributeValue(key, searchTable.get(attribute).get(key));
			
			    String back="";
			 for (Element div : divs) {
				 back = back+div.text();
				 }
			return back;}}
		}
			return "";
			
	
	      
	}   
    public static boolean existPerson(Scout scout,PersonManager personManager,ScoutManager scoutManager){
		  if (scout.getPhone()==null) return false;
		  if (scout.getPerson()!=null) return false;
		  Map<String,List<String>> telefone=scoutUtil.analyzeTelefonfield(scout);
				if (telefone.containsKey("person") && telefone.get("person").size()==1){
					String[] strings= telefone.get("person").get(0).split(" ");	
					System.err.println("-------------Id------------------------"+strings[0]);
					Personen person=personManager.get(new Long(strings[0]));
					scout=scoutManager.get(scout.getId());
					scout.setPerson(person);
					scout.setPhone("neu hinzugefügt");
					person.addScout(scout);
				scout=	scoutManager.save(scout);
				System.err.println("-------------Scout------------------------"+scout.getPerson().getEigtName());
				return true;
				}
		 return false;
		 }       
    
    
    
 }      
      

