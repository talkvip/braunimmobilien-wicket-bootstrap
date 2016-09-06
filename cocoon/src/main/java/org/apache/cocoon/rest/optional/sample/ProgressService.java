/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cocoon.rest.optional.sample;

import org.apache.cocoon.rest.controller.annotation.RESTController;
import org.apache.cocoon.rest.controller.response.RestResponse;
import org.apache.cocoon.rest.controller.response.TextResponse;
import org.apache.cocoon.rest.optional.AbstractAsyncronService;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.beans.factory.annotation.Autowired;

import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.cocoon.Configuration;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Strassen;
import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.service.TypeManager;
import braunimmobilien.util.ScoutUtil;
import braunimmobilien.model.Type;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.model.Objarttyp;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.EigentuemermusterManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;
@RESTController
public class ProgressService extends AbstractAsyncronService {
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
	public ScoutManager getScoutManager() {
		return scoutManager;
	}

	public void setScoutManager(ScoutManager scoutManager) {
		this.scoutManager = scoutManager;
	}
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
    public RestResponse doPost() throws Exception {
        setId(this.getClass().getCanonicalName());
        super.bidExecutor.execute(this);
        return new TextResponse("{\"progress\":" + 1 + " }", "application/json");
    }

    @Override
    public RestResponse doGet() throws Exception {
        setId(this.getClass().getCanonicalName());
        Object progress = getProgress();
        return new TextResponse("{\"progress\":" + progress.toString() + " }",
                "application/json");
    }

    @Override
    public void run() {
    	 scoutUtil=new ScoutUtil(strassenManager,objektartManager,objektManager,scoutManager,objektsuchManager,orteManager,personManager);
   	 Hashtable<String, Integer> ht = new Hashtable<String, Integer>(); 
    	 List mietenhannover=(List)configuration.getProgress().get("mietenhannover");
    	 Iterator it=mietenhannover.iterator();
    	  try{ while(it.hasNext()){
    		 LinkedHashMap<String, String> table=(LinkedHashMap<String, String>)it.next();
    		
		          
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
	    	 this.updateProgress(0);
	    	 		int i=0;
	    	    	 Enumeration<String> keys = ht.keys(); 
	    		        while (keys.hasMoreElements()) { 
	    		        	++i;
	    		            String key = keys.nextElement(); 
	    		          Integer  s = ht.get(key); 
	    		          if (s==0) continue;
	    		         
	    		            if(!checkExist(key,scoutManager)){					 
	    		            	   System.out.println(key + " - " + s); 
	    		            	getDefinitiveData(key,eigentuemermusterManager,scoutManager,orteManager,objarttypManager,typeManager,personManager); 
	    					int db=(int)((i*100)/ht.size());
	    			         this.updateProgress(db);
	    			         ht.put(key, new Integer(0));}
	    			                Thread.sleep(250);  
	    		            }
       this.updateProgress(100);
	   }catch(java.io.IOException e){e.printStackTrace();
	   
	   
	   }
    	 
	   catch (InterruptedException e) {
          e.printStackTrace();}
  }
   
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
    
    
    public static void getDefinitiveData(String scoutid,EigentuemermusterManager eigentuemermusterManager,ScoutManager scoutManager,OrteManager orteManager,ObjarttypManager objarttypManager,TypeManager typeManager,PersonManager personManager)
	{Scout scout=new Scout();
		org.jsoup.nodes.Document document=null;
			try{ document = Jsoup.connect("http://www.immobilienscout24.de/expose/"+scoutid).get();
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
							 System.err.println(" before store definitive Data "+scout.getId()+" "+scout.getType().getId());	
						
							
			 } 
			  catch( Exception ex ) {
			        ex.printStackTrace();
			  System.exit(1);    
			  }	
			
	     
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
		//			person.addScout(scout);
				scout=	scoutManager.save(scout);
				System.err.println("-------------Scout------------------------"+scout.getPerson().getEigtName());
				return true;
				}
		 return false;
		 }    
   
    
}
