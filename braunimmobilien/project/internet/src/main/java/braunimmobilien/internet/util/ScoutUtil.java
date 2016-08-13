package braunimmobilien.internet.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Strassen;

public class ScoutUtil implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	
	  public ScoutUtil() {
		  
	  
	  }
	  public boolean notexistObject(Map<String,List<String>> result)throws ObjectNotIdentifiableException{   
		  if(!scout.getWhere().contains("Hannover")) throw new ObjectNotIdentifiableException("in Where no Hannover" + scout.getId());
		  System.err.println("result where field ---------------------------------"+result);
		  if(result.get("objekt")!=null&&result.get("objekt").size()==1){
			  String[] strings= result.get("objekt").get(0).split(" ");		
				Objekte objekt=objektManager.get(new Long(strings[0]));  
				 scout.setObjekt(objekt);
				   	objekt.addScout(scout);
				   scoutManager.save(scout);
				   return false;
		  }
		 return true;
		 
	
 }  
	
	  
	  
	  public boolean existPerson(Map<String,List<String>> telefone){
		  if (scout.getPhone()==null) return false;
		 
		 
		  
		  
		
		  
				if (telefone.containsKey("person") && telefone.get("person").size()==1){
					String[] strings= telefone.get("person").get(0).split(" ");	
					System.err.println("-------------Id------------------------"+strings[0]);
					Personen person=personManager.get(new Long(strings[0]));
					scout.setPerson(person);
					telefone.get("person");
					scoutManager.save(scout);
					return true;
				}
				String eigtmuster="";
				
				if (telefone.containsKey("telefon")){
					
						
					Iterator it=telefone.get("telefon").iterator();
					while(it.hasNext()){
						String telefon=(String)it.next();
						eigtmuster=eigtmuster+"\" \""+telefon;
					}	
				}
				
				if (telefone.containsKey("internet")){
			
						
					Iterator it=telefone.get("internet").iterator();
					while(it.hasNext()){
						String telefon=(String)it.next();
						eigtmuster=eigtmuster+"\" \""+telefon;
					}	
				}
				
				
				if(eigtmuster.length()>0){
					pageparameters.add("eigtmuster", "%"+eigtmuster.substring(3)+"%");	
				}
	
			if(telefone.get("orte")!=null){
				if (telefone.get("orte").size()==1){	
					String[] strings= telefone.get("orte").get(0).split(" ");	
					Orte orte=orteManager.get(new Long(strings[0]));
					pageparameters.add("landid", orte.getLand().getId());
					pageparameters.add("ortid", orte.getId());
				}
				}
			if(telefone.get("strasse")!=null){
				if (telefone.get("strasse").size()>0){	
					String[] strings= telefone.get("strasse").get(0).split(" ");	
					Strassen strasse=strassenManager.get(new Long(strings[0]));	
					pageparameters.add("landid", strasse.getOrt().getLand().getId());
					pageparameters.add("ortid", strasse.getOrt().getId());
					pageparameters.add("strid", strasse.getId());
					}
			}
				
			
		
				
		 return false;
		 } 
	  
	  public boolean analyzeTelefonfield(String phonestring,Map<String,List<String>> result){

		  Pattern p1 = Pattern.compile("([0-9 ]+)[A-Za-z]");
			
			if (phonestring==null) return false;
			List<String> snippets=new ArrayList();
			List<String> newsnippets=new ArrayList();
				String adresse="";
				String internet="";
				String find="";
				String telefon="";
				List<String> internets=new ArrayList();
				List<String> adressen=new ArrayList();
				Pattern p;
				String strasse="";
				String plz="";
				String ort="";
			
				snippets.add(phonestring);
				
			
				Iterator it=snippets.iterator();
				Pattern pstrasse = Pattern.compile("(.*)[\\D]([0-9 ]+)([A-Za-z]?)$");
				p = Pattern.compile("Telefon:([0-9 \\-\\/]+)([A-Za-z].*){0,1}$");
				while(it.hasNext()){	
			     String snippet=(String)it.next();
			     Matcher m = p.matcher(snippet);
			     int j=0;
				while (m.find()) {
					System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 1 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					find=m.group(1);
					System.err.println(" find "+find+" "+m.groupCount());
					if (result.containsKey("telefon")) result.get("telefon").add(find.substring(0,find.length()).replace(" ","").replace("/","").replace("-",""));
					else result.put("telefon",new ArrayList<String>(Arrays.asList(new String[]{find.substring(0,find.length()).replace(" ","").replace("/","").replace("-","")})));
					String[] split=snippet.split("Telefon:"+find.substring(0,find.length()));
					for(int i=0;i<split.length;++i){
						newsnippets.add(split[i]);
					}
					++j;
					}
				if(j==0) newsnippets.add(snippet);
				}
				System.err.println("newsnippets "+newsnippets.size());
				for(int k=0;k<newsnippets.size();++k){
					System.err.println(k+" "+newsnippets.toArray()[k]);
				}
				snippets=newsnippets;
				newsnippets=new ArrayList();
				it=snippets.iterator();
				p = Pattern.compile("Mobil:([0-9 \\-\\/]+)[A-Za-z]");
				while(it.hasNext()){	
				     String snippet=(String)it.next();
				     Matcher m = p.matcher(snippet);
				     int j=0;
					while (m.find()) {
						System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 2 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						find=m.group(1);
						System.err.println(" find "+find+" "+m.groupCount());
						if (result.containsKey("handy")) result.get("handy").add(find.substring(0,find.length()).replace(" ","").replace("-","").replace("/",""));
						else result.put("handy",new ArrayList<String>(Arrays.asList(new String[]{find.substring(0,find.length()).replace(" ","").replace("-","").replace("/","")})));
					
						String[] split=snippet.split("Mobil:"+find.substring(0,find.length()-1));
						for(int i=0;i<split.length;++i){
							newsnippets.add(split[i]);
						}
						++j;
						}
					if(j==0) newsnippets.add(snippet);
					}
				System.err.println("newsnippets "+newsnippets.size());
				for(int k=0;k<newsnippets.size();++k){
					System.err.println(k+" "+newsnippets.toArray()[k]);
				}
				
				snippets=newsnippets;
				newsnippets=new ArrayList();
				it=snippets.iterator();
				p = Pattern.compile("Mobil:([0-9 \\-\\/]+)$");
				while(it.hasNext()){	
				     String snippet=(String)it.next();
				     Matcher m = p.matcher(snippet);
				     int j=0;
					while (m.find()) {
						System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 2A XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						find=m.group(1);
						System.err.println(" find "+find+" "+m.groupCount());
						if (result.containsKey("handy")) result.get("handy").add(find.substring(0,find.length()).replace(" ","").replace("-","").replace("/",""));
						else result.put("handy",new ArrayList<String>(Arrays.asList(new String[]{find.substring(0,find.length()).replace(" ","").replace("-","").replace("/","")})));
						String[] split=snippet.split("Mobil:"+find.substring(0,find.length()));
						for(int i=0;i<split.length;++i){
							newsnippets.add(split[i]);
						}
						++j;
						}
					if(j==0) newsnippets.add(snippet);
					}
				System.err.println("newsnippets "+newsnippets.size());
				for(int k=0;k<newsnippets.size();++k){
					System.err.println(k+" "+newsnippets.toArray()[k]);
				}
				
				
				
				
				snippets=newsnippets;
				newsnippets=new ArrayList();
				it=snippets.iterator();
				p = Pattern.compile("Fax:([0-9 \\-\\/]+)[A-Za-z]");
				while(it.hasNext()){	
				     String snippet=(String)it.next();
				     Matcher m = p.matcher(snippet);
				     int j=0;
					while (m.find()) {
						System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 3 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						find=m.group(1);
						System.err.println(" find "+find+" "+m.groupCount());
					
						String[] split=snippet.split("Fax:"+find.substring(0,find.length()-1));
						for(int i=0;i<split.length;++i){
							newsnippets.add(split[i]);
						}
						++j;
						}
					if(j==0) newsnippets.add(snippet);
					}
				System.err.println("newsnippets "+newsnippets.size());
				for(int k=0;k<newsnippets.size();++k){
					System.err.println(k+" "+newsnippets.toArray()[k]);
				}
				snippets=newsnippets;	
					newsnippets=new ArrayList();
					it=snippets.iterator();
					p = Pattern.compile("^(.*) ([0-9]{5}) (.+) Impressum des Anbieters (.*) Firmenprofil/Homepage des Anbieters.*$");
					while(it.hasNext()){	
					     String snippet=(String)it.next();
					     Matcher m = p.matcher(snippet);
					     int j=0;
						while (m.find()) {
							System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 4 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
							strasse=m.group(1).trim();
							if (strasse.length()>0){
								if (result.containsKey("adresse")) result.get("adresse").add(strasse);
								else result.put("adresse",new ArrayList<String>(Arrays.asList(new String[]{strasse})));
							
								
							}
							plz=m.group(2);
							if (plz.length()>0){
								if (result.containsKey("plz")) result.get("plz").add(plz);
								else result.put("plz",new ArrayList<String>(Arrays.asList(new String[]{plz})));
							
								
							}
							ort=m.group(3).trim();
							if (ort.length()>0){
								if (result.containsKey("ort")) result.get("ort").add(ort);
								else result.put("ort",new ArrayList<String>(Arrays.asList(new String[]{ort})));
							
								
							}
							adresse=ort+" "+plz+" "+strasse;
							if (adresse.length()>0){}
							internet=m.group(4);
							if (internet.length()>0){
								if (result.containsKey("internet")) result.get("internet").add(internet);
								else result.put("internet",new ArrayList<String>(Arrays.asList(new String[]{internet})));
							
								
								
							}
							++j;
							}
						if(j==0) newsnippets.add(snippet);
						}
						snippets=newsnippets;
				
						System.err.println("newsnippets "+newsnippets.size());
				for(int k=0;k<newsnippets.size();++k){
					System.err.println(k+" "+newsnippets.toArray()[k]);
				}
				snippets=newsnippets;	
				newsnippets=new ArrayList();
				it=snippets.iterator();
				p = Pattern.compile("^(.*) ([0-9]{5}) (.+) Impressum des Anbieters (.*)$");
				while(it.hasNext()){	
				     String snippet=(String)it.next();
				     Matcher m = p.matcher(snippet);
				     int j=0;
					while (m.find()) {
						System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 5 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						strasse=m.group(1).trim();
						if (strasse.length()>0){
							if (result.containsKey("adresse")) result.get("adresse").add(strasse);
							else result.put("adresse",new ArrayList<String>(Arrays.asList(new String[]{strasse})));
						
							
						}
						plz=m.group(2);
						if (plz.length()>0){
							if (result.containsKey("plz")) result.get("plz").add(plz);
							else result.put("plz",new ArrayList<String>(Arrays.asList(new String[]{plz})));
						
							
						}
						ort=m.group(3).trim();
						if (ort.length()>0){
							if (result.containsKey("ort")) result.get("ort").add(ort);
							else result.put("ort",new ArrayList<String>(Arrays.asList(new String[]{ort})));
						
							
						}
						adresse=ort+" "+plz+" "+strasse;
						internet=m.group(4);
						if (internet.length()>0){
							if (result.containsKey("internet")) result.get("internet").add(internet);
							else result.put("internet",new ArrayList<String>(Arrays.asList(new String[]{internet})));
						
							
							
						}
						++j;
						}
					if(j==0) newsnippets.add(snippet);
					}
					snippets=newsnippets;
			
					System.err.println("newsnippets "+newsnippets.size());
			for(int k=0;k<newsnippets.size();++k){
				System.err.println(k+" "+newsnippets.toArray()[k]);
			}
				snippets=newsnippets;	
				newsnippets=new ArrayList();
				it=snippets.iterator();
				p = Pattern.compile("^(.*) ([0-9]{5}) (.+)$");
				while(it.hasNext()){	
				     String snippet=(String)it.next();
				     Matcher m = p.matcher(snippet);
				     int j=0;
					while (m.find()) {
						System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 6 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						strasse=m.group(1).trim();
						if (strasse.length()>0){
							if (result.containsKey("adresse")) result.get("adresse").add(strasse);
							else result.put("adresse",new ArrayList<String>(Arrays.asList(new String[]{strasse})));
						
							
						}
						plz=m.group(2);
						if (plz.length()>0){
							if (result.containsKey("plz")) result.get("plz").add(plz);
							else result.put("plz",new ArrayList<String>(Arrays.asList(new String[]{plz})));
						
							
						}
						ort=m.group(3).trim();
						if (ort.length()>0){
							if (result.containsKey("ort")) result.get("ort").add(ort);
							else result.put("ort",new ArrayList<String>(Arrays.asList(new String[]{ort})));
						
							
						}
						adresse=ort+" "+plz+" "+strasse;
						++j;
						}
					if(j==0) newsnippets.add(snippet);
					}
					snippets=newsnippets;
			
					System.err.println("newsnippets "+newsnippets.size());
			for(int k=0;k<newsnippets.size();++k){
				System.err.println(k+" "+newsnippets.toArray()[k]);
			}
				
	  }
	  
	  public boolean checkTelefonfield(Map<String,List<String>> result){

				System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX Suche XXXXXXXXXXXXXXXXXXXXXXXXXXXX Adresse : "+adresse);
				if (result.containsKey("handy")){
				Iterator	it=result.get("handy").iterator();
			String	telefon="";
					while(it.hasNext()){
						telefon=(String)it.next();
						System.err.println(" Handy X"+telefon+"X");
						List<Personen> personen=personManager.search("\""+telefon+"\"");
						Iterator itp=personen.iterator();
						while(itp.hasNext()){
						Personen
						person=(Personen)itp.next();
						if (result.containsKey("person")) {
							boolean found = false;
							Iterator itr = result.get("person").iterator();
							while(itr.hasNext()){
								String personstr=(String)itr.next();
								if(personstr.equals(person.getId()+" "+person.getEigtName())) found=true;
							}
							if(!found)  result.get("person").add(person.getId()+" "+person.getEigtName());
							
						}
						else result.put("person",new ArrayList<String>(Arrays.asList(new String[]{person.getId()+" "+person.getEigtName()})));
					}
			
					}
					}		
				
			if (result.containsKey("telefon")){
			it=result.get("telefon").iterator();
			
			while(it.hasNext()){
				telefon=(String)it.next();
				System.err.println(" Telefone X"+telefon+"X");
				List<Personen> personen=personManager.search("\""+telefon+"\"");
				Iterator itp=personen.iterator();
				while(itp.hasNext()){
				Personen
				person=(Personen)itp.next();
				if (result.containsKey("person")) {
					boolean found = false;
					Iterator itr = result.get("person").iterator();
					while(itr.hasNext()){
						String personstr=(String)itr.next();
						if(personstr.equals(person.getId()+" "+person.getEigtName())) found=true;
					}
					if(!found)  result.get("person").add(person.getId()+" "+person.getEigtName());
				}
				else result.put("person",new ArrayList<String>(Arrays.asList(new String[]{person.getId()+" "+person.getEigtName()})));
			}
	
			}
			}
			
			if (result.containsKey("ort")){
				it=result.get("ort").iterator();
				
				while(it.hasNext()){
					ort=(String)it.next();
					List<Orte> orte=orteManager.search("\""+ort+"\"");
					Iterator ito=orte.iterator();
					while(ito.hasNext()){
						Orte
					ortd=(Orte)ito.next();
					if (result.containsKey("orte")) result.get("orte").add(ortd.getId()+" "+ortd.getOrtname());
					else result.put("orte",new ArrayList<String>(Arrays.asList(new String[]{ortd.getId()+" "+ortd.getOrtname()})));
				}
		
				}
				}
			
			
			
			
			if (result.containsKey("adresse")&&result.containsKey("plz")){
				it=result.get("plz").iterator();
				 System.err.println("Stufe 1");
				while(it.hasNext()){
					plz=(String)it.next();
					 System.err.println("Stufe 2 X"+plz+"X");
					List<Strassen> strassen=strassenManager.search("\""+plz+"\"");
					Iterator itd=strassen.iterator();
					while(itd.hasNext()){
						System.err.println("Stufe 3");
					Strassen strase=(Strassen)itd.next();
					Iterator its=result.get("adresse").iterator();
					while(its.hasNext()){
						strasse=(String)its.next();
						 Matcher m1 = pstrasse.matcher(strasse);
						 System.err.println("X"+strasse+"X");
						 while(m1.find()){
							String strstring=m1.group(1);
							strstring= strstring.replace("Straße","Str");
							strstring= strstring.replace("straße","str").trim();
							strstring= strstring.replace("Strasse","Str");
							strstring= strstring.replace("strasse","str").trim();
							System.err.println("X"+strstring+"X");
							if(strstring.endsWith(".")) strstring=strstring.substring(0,strstring.length()-1);
							System.err.println(m1.group(2)+" YY "+strasse+" Strstring X"+strstring+"X");
							
								if (strase.getStrname().startsWith(strstring)) {
									if (result.containsKey("strasse")) result.get("strasse").add(strase.getId()+" "+strase.getStrplz()+" "+strase.getStrname());
									else result.put("strasse",new ArrayList<String>(Arrays.asList(new String[]{strase.getId()+" "+strase.getStrplz()+" "+strase.getStrname()})));
								}	
						 }
							}  
					}
				}
			}
			Iterator itg=result.keySet().iterator();
			while(itg.hasNext()){
				String key = (String) itg.next();
		
				 result.put(key, new ArrayList<String>( new HashSet<String>(result.get(key))));
				
			}
			
			
			return result;
}
	  
	  
	  public  boolean analyzeWherefield(String wherestring,Map<String,List<String>> result){
		 
		  System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX -1 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		  if (wherestring==null) return false;
		  System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 0 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		  Pattern p1 = Pattern.compile("^(.*) ([0-9]{5}) Hannover(.+)$");
		  Pattern pstrasse = Pattern.compile("(.*)[\\D]([0-9 ]+)([A-Za-z]?)$");
		  Matcher m = p1.matcher(wherestring);
		  
			while (m.find()) {
				System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 1 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				String adresse=m.group(1).trim().replace(",", "");
				String plz=m.group(2).trim();
				if (result.containsKey("adresse")) result.get("adresse").add(adresse);
				else result.put("adresse",new ArrayList<String>(Arrays.asList(new String[]{adresse})));
				if (result.containsKey("plz")) result.get("plz").add(plz);
				else result.put("plz",new ArrayList<String>(Arrays.asList(new String[]{plz})));
				}
			
				if(result.containsKey("adresse")){
					Iterator it=result.get("adresse").iterator();
					while(it.hasNext()){	
								     String adresse=(String)it.next();
								     m = pstrasse.matcher(adresse);
								     
								     while (m.find()) {
											System.err.println(" XXXXXXXXXXXXXXXXXXXXXXX 1 XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
											String strasse=m.group(1);
											String hauszahl=m.group(2).trim();
											String hausbuchst=m.group(3).trim().toLowerCase();
											if (result.containsKey("strasse")) result.get("strasse").add(strasse.trim());
											else result.put("strasse",new ArrayList<String>(Arrays.asList(new String[]{strasse.trim()})));
											if (result.containsKey("hausnummer")) result.get("hausnummer").add(hauszahl+hausbuchst);
											else result.put("hausnummer",new ArrayList<String>(Arrays.asList(new String[]{hauszahl+hausbuchst})));
											}
									
							}
					     }
								
	  }
	  
	  public  boolean checkWherefield(String wherestring,Map<String,List<String>> result){
			if (result.containsKey("strasse")&&result.containsKey("plz")){
					List<Strassen> strassen=strassenManager.search("\""+result.get("plz").get(0)+"\"");
					Iterator itd=strassen.iterator();
					while(itd.hasNext()){
						System.err.println("Stufe 3");
					Strassen strase=(Strassen)itd.next();
					Iterator it=result.get("strasse").iterator();
					while(it.hasNext()){
						 String strstring=(String)it.next();
				
							strstring= strstring.replace("Straße","Str");
							strstring= strstring.replace("straße","str").trim();
							strstring= strstring.replace("Strasse","Str");
							strstring= strstring.replace("strasse","str").trim();
							strstring= strstring.replace("str.","str").trim();
							strstring= strstring.replace("Str.","Str").trim();
							System.err.println("X"+strstring+"X");
							if(strstring.endsWith(".")) strstring=strstring.substring(0,strstring.length()-1);
								if (strase.getStrname().startsWith(strstring)) {
									if (result.containsKey("strassen")) result.get("strassen").add(strase.getId()+" "+strase.getStrplz()+" "+strase.getStrname());
									else result.put("strassen",new ArrayList<String>(Arrays.asList(new String[]{strase.getId()+" "+strase.getStrplz()+" "+strase.getStrname()})));
								}		}
						 }
							}  
					if(result.containsKey("strassen")&&result.containsKey("hausnummer")){
						String hausstr=result.get("hausnummer").get(0);
						String[] strings= result.get("strassen").get(0).split(" ");		
						Strassen strassen=strassenManager.get(new Long(strings[0]));
					
						Iterator its=strassen.getObjekte().iterator();
						boolean found = false;
						  while(its.hasNext()){
					        	Objekte objekte=(Objekte)its.next();
					        	System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!objekt "+objekte.getObjhausnummer());
					     if(objekte.getObjhausnummer()!=null){  
					    	 String hausnummer=objekte.getObjhausnummer().replaceFirst(strassen.getStrname(), "").trim();
					        System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!Hausnummer "+hausnummer);
					        if(hausnummer.replaceAll(" ", "").toLowerCase().equals(hausstr)){
					        	found=true;
					        	if((objekte.getObjektart().getDoppelbelegung()&&objekte.getObjektart().getId().longValue()==177)||(!objekte.getObjektart().getDoppelbelegung())){
					        	 System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!Hausnummer vorhanden");	
					        	 if (result.containsKey("objekt")) result.get("objekt").add(objekte.getId()+" "+objekte.getObjhausnummer());
									else result.put("objekt",new ArrayList<String>(Arrays.asList(new String[]{objekte.getId()+" "+objekte.getObjhausnummer()})));
					     
					        	}
					        	
					        }
					        }  
					        }
					       
					       if(!found) {	
					        	Objekte objekt = new Objekte();
					        	objekt.setStrasse(strassen);
					        	objekt.setObjhausnummer(strassen.getStrname()+" "+hausstr);
					       objekt.setObjektsuch(objektsuchManager.get(1L));
					       objekt.setObjektart(objektartManager.get(1L));
					     strassen.addObjekt(objekt);
					       objektManager.save(objekt);
					       if (result.containsKey("objekt")) result.get("objekt").add(objekt.getId()+" "+objekt.getObjhausnummer());
							else result.put("objekt",new ArrayList<String>(Arrays.asList(new String[]{objekt.getId()+" "+objekt.getObjhausnummer()})));
					       }
					        }	  
	return result;
	  }
						  
						  
	  
	  
	  
}
