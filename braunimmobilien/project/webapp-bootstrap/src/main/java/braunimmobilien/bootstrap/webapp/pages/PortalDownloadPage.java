package braunimmobilien.bootstrap.webapp.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.panel.Fragment;

import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Xtyp;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.service.TypeManager;
import braunimmobilien.service.XtypManager;
import braunimmobilien.util.ScoutUtil;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;

import org.apache.wicket.event.IEvent;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelectConfig;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.Stack;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.UpdatableProgressBar;
import braunimmobilien.bootstrap.webapp.components.base.StateSelect;
import braunimmobilien.bootstrap.webapp.Configuration;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapMultiSelect;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Map;

import com.google.common.collect.Lists;
import org.apache.wicket.markup.html.basic.Label;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.SplitButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.AjaxBootstrapTabbedPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.tabs.ClientSideBootstrapTabbedPanel;
import braunimmobilien.bootstrap.webapp.components.basecss.ButtonGroups;
import braunimmobilien.bootstrap.webapp.Configuration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.wicket.spring.injection.annot.SpringBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The {@code ComponentsPage}
 *
 * @author miha
 */


public class PortalDownloadPage extends BasePage {
	@SpringBean
	private braunimmobilien.bootstrap.webapp.Configuration configuration;
	@SpringBean
	private  TypeManager typeManager;
	@SpringBean
	private  EigentuemermusterManager eigentuemermusterManager;
	@SpringBean
		private  StrassenManager strassenManager;
	@SpringBean
	private  ObjektartManager objektartManager;
	@SpringBean
		private  ObjektManager objektManager;
	  @SpringBean
	private  ObjektsuchManager objektsuchManager;
	@SpringBean
		private PersonManager personManager;
	  @SpringBean
	private  ObjarttypManager objarttypManager;
	@SpringBean
    private XtypManager xtypManager;	
	@SpringBean
	private  OrteManager orteManager;
	@SpringBean
	protected ScoutManager scoutManager;
	static int time=0;
	private String type=null;
    public String getType() {
		return type;
	}
    private static Hashtable<String, Integer> ht = new Hashtable<String, Integer>(); 
    Label all=new Label("all", new Model<String>()
            {
                @Override
                public String getObject()
                {
                    return String.valueOf(ht.size());
                }
            });
    Label allnew=new Label("allnew", new Model<String>()
            {
                @Override
                public String getObject()
                {
                    return String.valueOf(newrecord.size());
                }
            });
    Label allerror=new Label("allerror", new Model<String>()
            {
                @Override
                public String getObject()
                {
                    return String.valueOf(interruptedrecord.size());
                }
            });

    Label alldone=new Label("alldone", new Model<String>()
            {
                @Override
                public String getObject()
                {
                    return String.valueOf(time);
                }
            });




	public void setType(String type) {
		this.type = type;
	}

	/**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
	
	
	
	List<String> interruptedrecord=new ArrayList<String>();	             
	List<String> newrecord=new ArrayList<String>();	
List<LinkedHashMap<String, String>> mietenhannover=new ArrayList<LinkedHashMap<String, String>>();
private static String objarttyp="1";
private static String ortid="1";

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

static boolean stop=true;
private static ScoutUtil scoutUtil=null;
static Logger logger = LoggerFactory.getLogger(PortalDownloadPage.class);

public PortalDownloadPage(PageParameters parameters) {
        super(parameters);
        stop=true;
        logger.debug("Protokoll Activities PortalDownloadPage");
        final   UpdatableProgressBar updatableBar = new UpdatableProgressBar("updatable", Model.of(0)) {
            @Override
            protected IModel<Integer> newValue() {
            	logger.debug("?????????????????????????vor stop ------------- ");
            	if (type==null) return Model.of(0);
            	logger.debug("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            	if (stop) {
            		 scoutUtil=new ScoutUtil(strassenManager,objektartManager,objektManager,scoutManager,objektsuchManager,orteManager,personManager);
            	        Iterator it=((List<LinkedHashMap<String, String>>)configuration.getProgress().get(type)).iterator();
            	        try{ while(it.hasNext()){
            	 		 LinkedHashMap<String, String> table=(LinkedHashMap<String, String>)it.next();
            	 		
            	 		  if(table.containsKey("ortid")) ortid=table.get("ortid");
            	 		 logger.debug("Configuration mietenhannover : "+table.get("address")+"  "+table.get("idpattern"));
            			         if(table.get("iterate").equals("no")){
            			            if (getScoutidListFromNet(table.get("address"),table.get("idpattern"),ht)) {
            			            	logger.debug("1 te Seite");
            			   	                Thread.sleep(250);
            			   	          
            			   	     }
            			         }
            			         if(table.get("iterate").equals("yes")){
            			        	 int faktor=Integer.parseInt(table.get("start"));
            			        	 Integer faktor1=new Integer(faktor);
            				            while (getScoutidListFromNet(table.get("address").replace("§i§",faktor1.toString()),table.get("idpattern"),ht)) {
            				            	logger.debug(faktor+ " te Seite");
            				   	                Thread.sleep(250);
            				   	           ++faktor;
            				   	        faktor1=new Integer(faktor);
            				   	     }
            				         }
            			         stop=false;
            	 	 }
            	        }catch(Exception e){System.err.println("1. "+e);
            	        
            	        }
            	        	
            		
            	}
            	if(time>=ht.size()) {
            		stop=true;
            		return Model.of(value());}
            	logger.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ ---------- "+ht.keySet().toArray()[time]+"  "+time+" "+ht.size());
            	org.jsoup.nodes.Document document=null;
    			try{
    				//Thread.sleep(250);
    		//		document = Jsoup.connect("http://www.immobilienscout24.de/expose/"+ht.keySet().toArray()[time]).get();
    			if(!scoutManager.exists(new Long((String)ht.keySet().toArray()[time]))){
    				logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!! nicht gefunden");
    				getDefinitiveData((String)ht.keySet().toArray()[time],eigentuemermusterManager,scoutManager,orteManager,objarttypManager,typeManager,personManager); 
    				newrecord.add((String)ht.keySet().toArray()[time]);}
    			Thread.sleep(250);;
    			
    			}catch(Exception e){
    				interruptedrecord.add((String)ht.keySet().toArray()[time]);
    				logger.debug("----------------- "+e);
    			}
            	int db=(int)((time*ProgressBar.MAX)/ht.size());
            	int newValue = (db) % ProgressBar.MAX;
            	
            	++time;
            	
                return Model.of(newValue);
            }
        };
        Stack labeledStack = new Stack(updatableBar.getStackId(), Model.of(0)) {
            @Override
            protected IModel<String> createLabelModel() {
                return new AbstractReadOnlyModel<String>() {
                    @Override
                    public String getObject() {
                        return String.format("From: %s", ht.size())+String.format(" checked: %s", time)+String.format(" new %s", newrecord.size())+String.format(" with error: %s", interruptedrecord.size());
                    }
                };
            }
        };
        labeledStack.labeled(true).type(ProgressBar.Type.SUCCESS);
        updatableBar.addStacks(labeledStack);
        add(updatableBar);
        updatableBar.setOutputMarkupId(true);
     //   updatableBar.updateInterval(Duration.seconds(1));
        
        
        Form form=new Form("intform");
        final BootstrapSelect<String> first =new BootstrapSelect<String>("default-live-search",new PropertyModel<String>(this,"type"),new ArrayList(configuration.getProgress().keySet()) ).with(of(false));
    
        add(form);
    	 form.add(first );
    
   	first.add(new AjaxFormComponentUpdatingBehavior("onchange")
	 {
	     @Override
	     protected void onUpdate(AjaxRequestTarget target)
	     {	logger.debug("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk "+type);
	   //  updatableBar.updateInterval(Duration.seconds(1));
	  //  target.add(updatableBar);
	     }
	 });	
     
   final	 AjaxLink ajaxlink=new AjaxLink<Void>("update-default") {

        private static final long serialVersionUID = -3698659776363173730L;

        @Override
        public void onClick(AjaxRequestTarget target) {
           
        	target.add(all);
        	target.add(allnew);
        	target.add(allerror);
        	target.add(alldone);
        }
    };
   
   add(ajaxlink);    
  add(all);      
     all.setOutputMarkupId(true); 
     add(allnew);      
     allnew.setOutputMarkupId(true); 
     add(allerror);      
     allerror.setOutputMarkupId(true); 
     add(alldone); 
     alldone.setOutputMarkupId(true);
       
        
        
        
      
      
    
    }

  
   
    
    
    

    /**
     * creates a new split button with some submenu links.
     *
     * @param markupId the markup id of that the split button has to use
     * @return new {@link SplitButton} instance
     */
  
      

    @Override
    protected boolean hasNavigation() {
        return true;
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
			 logger.debug("Seite holen");
			 if (check>0) return true;
			 return false;
		        }	
    
  
    
    public static String getTag(org.jsoup.nodes.Document document,String attribute)
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
    public static void getDefinitiveData(String scoutid,EigentuemermusterManager eigentuemermusterManager,ScoutManager scoutManager,OrteManager orteManager,ObjarttypManager objarttypManager,TypeManager typeManager,PersonManager personManager) throws java.io.IOException
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
  							logger.debug("PortalDownloadPage vor Objektbearbeitung "+scout.getId()+" "+scout.getWhere());
  							   Iterator it=eigentuemermusterManager.getEigentuemermusters().iterator();
  							 scout.setOrt(orteManager.get(new Long(ortid)));
  							 scoutManager.save(scout);
  							   try{	   scoutUtil.notexistObject(scout);}catch(braunimmobilien.util.ObjectNotIdentifiableException e){
  								   
  								   logger.error("ObjectNotIdentifiableException ",e);
  							   }
  							  
  							 logger.debug("PortalDownloadPage nach Objektbearbeitung "+scout.getId()+" "+scout.getObjekt());
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
  							scoutManager.save(scout);
  							existPerson(scout,personManager,scoutManager);
  							 logger.debug(" before store definitive Data "+scout.getId()+" "+scout.getType().getId());	
  						
  							
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
  
    private BootstrapSelectConfig of(boolean i18n) {
        BootstrapSelectConfig config = new BootstrapSelectConfig();
        if (i18n) {
           
            config
                    .withNoneSelectedText("My nothing selected")
                    .withNoResultText("My no results found")
                    .withCountSelectedText("My selected {0} from {1}")
                    .withMaxOptionsText("My limit ({n} {var} max)",
                            "My group limit({n} {var} max)",
                            "items", "item");
        }
        return config;
    }
    
    
}
