/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package braunimmobilien.bootstrap.webapp.pages.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.wizard.StaticContentStep;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardModel;
import org.apache.wicket.extensions.wizard.WizardModel.ICondition;
import org.apache.wicket.extensions.wizard.WizardStep;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.ValidationError;

import java.util.Comparator;
import java.net.URL;
import java.util.Locale;

import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.http.WebRequest;

import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.MitarbeiterManager;
import braunimmobilien.service.XtypManager;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.KonditionManager;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.TypeManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.service.KundenartManager;
import braunimmobilien.model.Kundenart;
import braunimmobilien.model.Land;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Emailbrief;
import braunimmobilien.model.Angstatus;
import braunimmobilien.model.Kondition;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.model.Type;
import braunimmobilien.model.Objarttyp;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Xtyp;
import braunimmobilien.model.Mitarbeiter;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Eigtstatus;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Personen;
import braunimmobilien.util.ScoutUtil;

import java.util.Date;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.gdata.util.ServiceException;

import java.io.IOException;

import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.link.ExternalLink;

import java.util.Map;

import org.apache.wicket.extensions.markup.html.tree.Tree;

import java.util.TimeZone;

import com.google.api.services.calendar.model.EventDateTime;

import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.extensions.yui.calendar.DateTimeField;

import javax.swing.tree.TreeNode;

import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
/**
 * This wizard shows some basic form use. It uses custom panels for the form elements, and a single
 * domain object ({@link Land}) as it's subject. Also, the land roles step}is an optional step, that
 * will only be executed when assignRoles is true (and that value is edited in the land details
 * step).
 * 
 * @author Eelco Hillenius
 */
public class NewLandWizard extends Wizard
{@SpringBean
	ObjektsuchManager objektsuchManager;
@SpringBean
EigtstatusManager eigtstatusManager;
@SpringBean
ObjarttypManager objarttypManager;
@SpringBean
KundeManager kundenManager;
@SpringBean
KundenartManager kundenartManager;
@SpringBean
TypeManager typeManager;
@SpringBean
ScoutManager scoutManager;
@SpringBean
EigentuemermusterManager eigentuemermusterManager;
@SpringBean
NachweiseManager nachweiseManager;
@SpringBean
MitarbeiterManager mitarbeiterManager;
@SpringBean
XtypManager xtypManager;
@SpringBean
AngstatusManager angstatusManager;
@SpringBean
KonditionManager konditionManager;
@SpringBean
	ObjektartManager objektartManager;
@SpringBean
StrassenManager strassenManager;
@SpringBean
LandManager landManager;
@SpringBean
OrteManager orteManager;
@SpringBean
AngobjzuordManager angobjzuordManager;
@SpringBean
ObjektManager objektManager;
@SpringBean
PersonManager personManager;
@SpringBean
EigentuemertypManager eigentuemertypManager;
@SpringBean
AngebotManager angebotManager;
final WizardModel model = new WizardModel();
	/**
	 * The confirmation step.
	 */
// The base URL for a user's calendar metafeed (needs a username appended).
  private static final String METAFEED_URL_BASE = 
      "https://www.google.com/calendar/feeds/";

  // The string to add to the user's metafeedUrl to access the event feed for
  // their primary calendar.
  private static final String EVENT_FEED_URL_SUFFIX = "/private/full";

  // The URL for the metafeed of the specified user.
  // (e.g. http://www.google.com/feeds/calendar/jdoe@gmail.com)
  private static URL metafeedUrl = null;

  // The URL for the event feed of the specified user's primary calendar.
  // (e.g. http://www.googe.com/feeds/calendar/jdoe@gmail.com/private/full)
  private static URL eventFeedUrl = null;
private final class PersonStep extends WizardStep implements ICondition
{

	 final TextArea<String> eigtanschrift = new TextArea<String>("eigtAnschrift");	
		
			
		
	
		
		
		
			
		
		
		IModel<List<? extends Eigtstatus>> makeChoicesEigtStatus = new AbstractReadOnlyModel<List<? extends Eigtstatus>>()
		        {
		            @Override
		            public List<Eigtstatus> getObject()
		            { List<Eigtstatus> eigtstatuslist=new  ArrayList<Eigtstatus>(); 
		            	
		            	Iterator eigtstatusiterator=eigtstatusManager.getEigtstatuses().iterator();
		            while(eigtstatusiterator.hasNext()){
		            	Eigtstatus eigtstatus=(Eigtstatus)eigtstatusiterator.next();
		            	
		            	eigtstatuslist.add(eigtstatus);
		            }
		               
		                return eigtstatuslist;
		            }

		        };
		          
		          
		       
		       	
		      
		      
	
	public PersonStep()
	
	{  setTitleModel(new ResourceModel("person.title"));
		setSummaryModel(new StringResourceModel("person.summary", this, new Model<Strassen>(
			strassen)));	
		Label eigtid=new Label("person.id");
		
	add(eigtid);
	add(new CheckBox("newLocation"));
TextField<String> eigtHausnummer= new TextField<String>("person.eigtHausnummer");
add(eigtHausnummer);
TextField<String> eigtHomepage= new TextField<String>("person.eigtHomepage");
add(eigtHomepage);
final DropDownChoice<Eigtstatus> eigtstatus = new DropDownChoice<Eigtstatus>("person.eigtstatus", makeChoicesEigtStatus, new ChoiceRenderer<Eigtstatus>("eigt_status_text","id"));
add(eigtstatus);

final TextField<String> eigtname = new TextField<String>("person.eigtName");		
add(eigtname);
eigtname.setRequired(true);
final TextArea<String> eigtanschrift = new TextArea<String>("person.eigtAnschrift");	
add(eigtanschrift);
eigtanschrift.setRequired(true);
final TextArea<String> eigtemail = new TextArea<String>("person.eigtEmail");		
add(eigtemail);

final TextField<String> eigtbriefanrede = new TextField<String>("person.eigtBriefanrede");		
add(eigtbriefanrede);
eigtbriefanrede.setRequired(true);
final CheckBox eigtaktuell=new CheckBox("person.eigtAktuell");
add(eigtaktuell);

final TextArea<String> eigttel = new TextArea<String>("person.eigtTel");		
add(eigttel);
final TextField<String> eigtfirma = new TextField<String>("person.eigtFirma");	
add(eigtfirma);

final TextArea<String> eigtinfo = new TextArea<String>("person.eigtInfo");		
add(eigtinfo);
final TextArea<String> eigttelefone = new TextArea<String>("person.eigtTelefone");		
add(eigttelefone);
eigttelefone.setVisible(false);
DateTextField eigtaufdatum = new DateTextField("person.eigtaufDatum", new StyleDateConverter("L-", true))
{
  @Override
  public Locale getLocale()
  {
      return getSession().getLocale();
  }
};
add(eigtaufdatum); 
eigtaufdatum.setRequired(true);
DatePicker datePickereigtaufdatum = new DatePicker();
datePickereigtaufdatum.setShowOnFieldClick(true);
eigtaufdatum.add(datePickereigtaufdatum);
DateTextField eigtletztkontakt = new DateTextField("person.eigtletztKontakt", new StyleDateConverter("L-", true))
{
@Override
public Locale getLocale()
{
   return getSession().getLocale();
}
};
add(eigtletztkontakt); 
DatePicker datePickereigtletztkontakt = new DatePicker();
datePickereigtletztkontakt.setShowOnFieldClick(true);
eigtletztkontakt.add(datePickereigtletztkontakt);
eigtletztkontakt.setRequired(true);



		
	}
	@Override
	public boolean evaluate()
	{
		return newperson;
	}
}


	private final class ObjektStep extends WizardStep implements ICondition
	{
   
		
		IModel<List<? extends Objektsuch>>makeChoicesObjektsuch= new AbstractReadOnlyModel<List<? extends Objektsuch>>()
    	        {
    	            @Override
    	            public List<Objektsuch> getObject()
    	            { List<Objektsuch> landlist=new  ArrayList<Objektsuch>(); 
    	            	if(objektsuchManager!=null){
    	            	Iterator landiterator=objektsuchManager.getObjektsuchen().iterator();
    	            while(landiterator.hasNext()){
    	            	Objektsuch land=(Objektsuch) landiterator.next();
    	            	landlist.add(land);
    	            }
    	            	}
    	                return landlist;
    	            }

    	        }; 
    	    	IModel<List<? extends Objektart>>makeChoicesObjektart= new AbstractReadOnlyModel<List<? extends Objektart>>()
            	        {
            	            @Override
            	            public List<Objektart> getObject()
            	            { List<Objektart> landlist=new  ArrayList<Objektart>(); 
            	            	if(objektartManager!=null){
            	            	Iterator landiterator=objektartManager.getObjektartes().iterator();
            	            while(landiterator.hasNext()){
            	            	Objektart land=(Objektart) landiterator.next();
            	            	landlist.add(land);
            	            }
            	            	}
            	                return landlist;
            	            }

            	        }; 
		/**
		 * Construct.
		 */
		public ObjektStep()
		
		{  setTitleModel(new ResourceModel("objekt.title"));
			setSummaryModel(new StringResourceModel("objekt.summary", this, new Model<Strassen>(
				strassen)));
			
			add(new CheckBox("newLocation"));
			add(new TextField<String>("objekt.objhausnummer"));
		 add(new TextField<String>("objekt.objinfo"));
		 add(new TextField<Double>("objekt.merkmal"));
		 add(new DropDownChoice<Objektsuch>("objekt.objektsuch",makeChoicesObjektsuch,new ChoiceRenderer<Objektsuch>("suchtext","id")));
		 add(new DropDownChoice<Objektart>("objekt.objektart",makeChoicesObjektart,new ChoiceRenderer<Objektart>("objartname","id")));
		 add(new RequiredTextField("objekt.objaufnahme"));
		 add(new TextField("objekt.objletztkontakt"));
		 add(new TextField("objekt.objvorlage"));
		
		}
		@Override
		public boolean evaluate()
		{
			return newobjekt;
		}
	}
	private final class SucheStep extends WizardStep implements ICondition
	{
		
		
		 private String selectedOrtstring="";
		 private String selectedStrassenstring="";
		  final AutoCompleteTextField<String> ortefield = new AutoCompleteTextField<String>("ortefield",
	              new PropertyModel<String>(this,"selectedOrtstring"))
	          {
	              @Override
	              protected Iterator<String> getChoices(String input)
	              {
	            
	             	 if (land==null)
	                  {
	                      List<String> emptyList = Collections.emptyList();
	                      return emptyList.iterator();
	                  }
	                  else{
	                 	 List<String> ortlist=new  ArrayList<String>(); 
	                 	 
	                 
	                 	 Iterator orteiterator=land.getOrte().iterator();
		    	            	while(orteiterator.hasNext()){
		    	            	Orte orte=(Orte)orteiterator.next();
		    	            	if(Strings.isEmpty(input)){ortlist.add(orte.getOrtname());}
		    	            	else{
		    	            		if(orte.getOrtname().startsWith(input, 0)) ortlist.add(orte.getOrtname());
		    	            		}
		    	            } 	 
	                 	return ortlist.iterator(); 
	                  }
	              }
	                  };
	                  
	                  IModel<List<? extends Eigentuemertyp>> makeChoicesEigentuemertyp = new AbstractReadOnlyModel<List<? extends Eigentuemertyp>>()
	          		        {
	          		            @Override
	          		            public List<Eigentuemertyp> getObject()
	          		            { List<Eigentuemertyp> eigentuemertyplist=new  ArrayList<Eigentuemertyp>(); 
	          		            	
	          		            	Iterator eigentuemertypiterator=eigentuemertypManager.getEigentuemertypes().iterator();
	          		            while(eigentuemertypiterator.hasNext()){
	          		            	Eigentuemertyp eigentuemertyp=(Eigentuemertyp)eigentuemertypiterator.next();
	          		            	
	          		            	eigentuemertyplist.add(eigentuemertyp);
	          		            }
	          		               
	          		                return eigentuemertyplist;
	          		            }

	          		        };     
	           
	                  final AutoCompleteTextField<String> strassenfield = new AutoCompleteTextField<String>("strassenfield",
	                          new PropertyModel<String>(this,"selectedStrassenstring"))
	                      {
	                          @Override
	                          protected Iterator<String> getChoices(String input)
	                          {
	                        	  if (ort==null)
	        	                  {
	        	                      List<String> emptyList = Collections.emptyList();
	        	                      return emptyList.iterator();
	        	                  }
	                             	 
	                              else{
	                             	 List<String> strassenlist=new  ArrayList<String>(); 
	                             	 
	                          
	                             	 Iterator strasseniterator=strassenManager.getStrassenes(ort.getId().toString()).iterator();
	         	    	            	while(strasseniterator.hasNext()){
	         	    	            	Strassen strassen=(Strassen)strasseniterator.next();
	         	    	            	if(Strings.isEmpty(input)){strassenlist.add(strassen.getStrname()+","+strassen.getStrplz());}
	         	    	            	else{
	         	    	            		if(strassen.getStrname().startsWith(input, 0)) strassenlist.add(strassen.getStrname()+","+strassen.getStrplz());
	         	    	            		}
	         	    	            } 	 
	                             	return strassenlist.iterator(); 
	                              }
	                          }
	                              };      
	                  
	                        	    
	                        	    
	                 
	                   
	                   
	               	IModel<List<? extends Land>>makeChoicesLand= new AbstractReadOnlyModel<List<? extends Land>>()
	            	        {
	            	            @Override
	            	            public List<Land> getObject()
	            	            { List<Land> landlist=new  ArrayList<Land>(); 
	            	            	if(landManager!=null){
	            	            	Iterator landiterator=landManager.getLandes().iterator();
	            	            while(landiterator.hasNext()){
	            	            	Land land=(Land) landiterator.next();
	            	            	landlist.add(land);
	            	            }
	            	            	}
	            	                return landlist;
	            	            }

	            	        }; 
	                   
	                   
	                   
	                   
	                   
	                   
	                            	        private   IModel<List<? extends Orte>> makeChoicesOrt = new AbstractReadOnlyModel<List<? extends Orte>>()
	    	                            	        {
	                            	        	 @Override
		                            	            public List<Orte> getObject()
		                            	            { List<Orte> ortelist=new  ArrayList<Orte>(); 
		                            	          
		                            	            if (land!=null){
		    	                  	                  
		    	                  	                 	
		    	                  	                 	 
		    	                  	                 
		    	                  	                 	 Iterator orteiterator=land.getOrte().iterator();
		    	                  		    	            	while(orteiterator.hasNext()){
		    	                  		    	            	Orte orte=(Orte)orteiterator.next();
		    	                  		    	            
		    	                  		    	            	
		    	                  		    	            	

	    		    	                  	                /* 	 Iterator strasseniterator=orte.getStrassen().iterator();
	    		    	                  		    	            	while(strasseniterator.hasNext()){
	    		    	                  		    	            	Strassen strassen=(Strassen)strasseniterator.next();
	    		    	                  		    	            	
	    		    	                  		    	            	 Iterator objekteiterator=strassen.getObjekte().iterator();
	 	    	            	    	    	    	            	
	    		    	            	    	    	    	           
		    	            	    	    	    	            		while(objekteiterator.hasNext()){
		    	            	    	    	    	            			Objekte objekte=(Objekte)objekteiterator.next();
		    	            	    	    	    	            			
		    	            	    	    	    	            } 
	    		    	                  		    	            } */
		    	                  		    	            	
		    	                  		    	            	
		    	                  		    	            	if(Strings.isEmpty(selectedOrtstring)){ortelist.add(orte);}
		    	                  		    	            	else{
		    	                  		    	            		if(orte.getOrtname().startsWith(selectedOrtstring, 0)) ortelist.add(orte);
		    	                  		    	            		}
		    	                  		    	            } 
		                            	            	
		                            	            }
		                            	            
		                            	                return ortelist;
		                            	            }


	    	                            	        };             	    
	    	                        	    
	    	                            	        private   	        IModel<List<? extends Objekte>> makeChoicesObjekte = new AbstractReadOnlyModel<List<? extends Objekte>>()
	    	            	    	    	    	        {
	    	            	    	    	    	            @Override
	    	            	    	    	    	            public List<Objekte> getObject()
	    	            	    	    	    	            { List<Objekte> objektelist=new  ArrayList<Objekte>(); 
	    	            	    	    	    	      
	    	            	    	    	    	            if(strassen!=null){
	    	            	    	    	    	            	 Iterator objekteiterator=strassen.getObjekte().iterator();
	    	            	    	    	    	            	
	    	            	    	    	    	           
	    	            	    	    	    	            		while(objekteiterator.hasNext()){
	    	            	    	    	    	            			Objekte objekte=(Objekte)objekteiterator.next();
	    	            	    	    	    	            
	    	            	    	    	    	            	objektelist.add(objekte); 
	    	            	    	    	    	            
	    	            	    	    	    	            } 
	    	            	    	    	    	          }
	    	            	    	    	    	        	
	    	            	    	    	    	          return objektelist;
	    	            	    	    	    	            };
	    	            	    	    	    	        };
	    	            	    	    	    	        
	    	            	    	    	    	        private   	        IModel<List<? extends Personen>> makeChoicesPersonen = new AbstractReadOnlyModel<List<? extends Personen>>()
	    	    	            	    	    	    	        {
	    	    	            	    	    	    	            @Override
	    	    	            	    	    	    	            public List<Personen> getObject()
	    	    	            	    	    	    	            { List<Personen> personenlist=new  ArrayList<Personen>(); 
	    	    	            	    	    	    	      
	    	    	            	    	    	    	            if(strassen!=null){
	    	    	            	    	    	    	            	 Iterator personeniterator=strassen.getPersonen().iterator();
	    	    	            	    	    	    	            	
	    	    	            	    	    	    	           
	    	    	            	    	    	    	            		while(personeniterator.hasNext()){
	    	    	            	    	    	    	            			Personen person=(Personen)personeniterator.next();
	    	    	            	    	    	    	            
	    	    	            	    	    	    	            	personenlist.add(person); 
	    	    	            	    	    	    	            
	    	    	            	    	    	    	            } 
	    	    	            	    	    	    	          }
	    	    	            	    	    	    	        	
	    	    	            	    	    	    	          return personenlist;
	    	    	            	    	    	    	            }
	    	    	            	    	    	    	        };
				
	    	                            	        private   IModel<List<? extends Strassen>> makeChoicesStrassen = new AbstractReadOnlyModel<List<? extends Strassen>>()
	    	    	                            	        {
	    	                            	        	 @Override
	    		                            	            public List<Strassen> getObject()
	    		                            	            { List<Strassen> strassenlist=new  ArrayList<Strassen>(); 
	    		                            	          
	    		                            	            if (ort!=null){
	    		    	                  	                  
	    		    	                  	                 	
	    		    	                  	                 	 
	    		    	                  	                 
	    		    	                  	                 	 Iterator strasseniterator=strassenManager.getStrassenes(ort.getId().toString()).iterator();
	    		    	                  		    	            	while(strasseniterator.hasNext()){
	    		    	                  		    	            	Strassen strassen=(Strassen)strasseniterator.next();
	    		    	                  		    	            	if(Strings.isEmpty(selectedStrassenstring)){strassenlist.add(strassen);}
	    		    	                  		    	            	else{
	    		    	                  		    	            		if(strassen.getStrname().startsWith(selectedStrassenstring, 0)) strassenlist.add(strassen);
	    		    	                  		    	            		}
	    		    	                  		    	            } 
	    		                            	            	
	    		                            	            }
	    		                            	            
	    		                            	                return strassenlist;
	    		                            	            }


	    	    	                            	        }; 
	    	    	                            	        
	    	    	                            	        final DropDownChoice<Eigentuemertyp> eigentuemertypen=new DropDownChoice<Eigentuemertyp>("eigentuemertyp",
		    	    	      	    	    	    	              
		    	    	   	    	    	    	        		 makeChoicesEigentuemertyp,new ChoiceRenderer<Eigentuemertyp>("typenbeschreibung","id"));       	       
		       	         
	    	    	                            	        
	    	    	                            	        final DropDownChoice<Personen> personen=new DropDownChoice<Personen>("person",
		    	    	      	    	    	    	              
		    	    	   	    	    	    	        		 makeChoicesPersonen,new ChoiceRenderer<Personen>("eigtHausnummer","id"));       	       
		       	       
	    	    	                            	        final DropDownChoice<Objekte> objekte = new DropDownChoice<Objekte>("objekt",
	    	    	      	    	    	    	              
	    	    	   	    	    	    	        		 makeChoicesObjekte,new ChoiceRenderer<Objekte>("objhausnummer","id"));       	       
		      	      
		 	final DropDownChoice<Land> dropland = new DropDownChoice<Land>("land",makeChoicesLand,new ChoiceRenderer<Land>("landname","id"));	  
		
		 	
		    	        final DropDownChoice<Orte> orte = new DropDownChoice<Orte>("ort",makeChoicesOrt,new ChoiceRenderer<Orte>("ortname","id"));   
		   					          
		    	        final DropDownChoice<Strassen> dropstrassen = new DropDownChoice<Strassen>("strassen",makeChoicesStrassen,new ChoiceRenderer<Strassen>("strname","id"));   
	            	     	   
		    	        final	 WebMarkupContainer eigentuemertypmarkup = new WebMarkupContainer("eigentuemertypmarkup");
		    	        final	 WebMarkupContainer landmarkup = new WebMarkupContainer("landmarkup");
		
		/**
		 * Construct.
		 */
		public SucheStep()
		{
			setTitleModel(new ResourceModel("suche.title"));
			setSummaryModel(new StringResourceModel("suche.summary", this, new Model<Land>(
				land)));
			  landmarkup.setVisible(true);
			 eigentuemertypmarkup.setVisible(false);
				if(responsepage.getSimpleName().equals("AngebotWizardTree")){
					if(pageparameters.getPosition("angnr")>-1&&pageparameters.getPosition("objid")<0&&pageparameters.getPosition("eigtid")<0){
						 eigentuemertypmarkup.setVisible(false);
						 landmarkup.setVisible(true);}
					if(pageparameters.getPosition("angnr")>-1&&pageparameters.getPosition("objid")>-1&&pageparameters.getPosition("eigtid")>-1&&pageparameters.get("eigtid").toString().equals("0")){
						 eigentuemertypmarkup.setVisible(true);
						 landmarkup.setVisible(false);}
				}
			 
			
			 eigentuemertypmarkup.setOutputMarkupPlaceholderTag(true);
			 eigentuemertypmarkup.add(eigentuemertypen);
		        add(eigentuemertypmarkup);
		       
		      
		        landmarkup.setOutputMarkupPlaceholderTag(true);
		        landmarkup.add(dropland);
		        add(landmarkup);
		        final	 WebMarkupContainer ortemarkup = new WebMarkupContainer("ortemarkup");
		        if(land==null) ortemarkup.setVisible(false);
		        else {
		        	ortemarkup.setVisible(true);
		        	 landmarkup.setVisible(false);
		        }
		        ortemarkup.setOutputMarkupPlaceholderTag(true);
		        ortemarkup.add(orte);
		        ortemarkup.add(ortefield);
		    //    orte.setOutputMarkupPlaceholderTag(true);
		     
		        ortefield.add(new OnChangeAjaxBehavior() {

		            @Override
		            protected void onUpdate(AjaxRequestTarget target) {
		                    landmarkup.setVisible(false);
		                    target.add(landmarkup);
		                    target.add(orte);
		            }
		    });
		        eigentuemertypen.add(new AjaxFormComponentUpdatingBehavior("onchange")
		        {
		            @Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {
System.err.println("=======================================================================Änderung erfolgt");
		            
					
					
		            landmarkup.setVisible(true);
		            target.add(landmarkup);
		      
		            }
		        });
		        
		        
		        
		        
		       dropland.add(new AjaxFormComponentUpdatingBehavior("onchange")
		        {
		            @Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {
System.err.println("=======================================================================Änderung erfolgt");
		            
					
					setNewland(false);
		            ortemarkup.setVisible(true);
		            target.add(ortemarkup);
		      
		            }
		        });
		       
		/*        dropland.add(new OnChangeAjaxBehavior()
		        {
		            @Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {
System.err.println("=======================================================================Änderung erfolgt");
		            	setNewland(false);
		            ortemarkup.setVisible(true);
		            target.add(ortemarkup);
		      
		            }
		        });*/
		       
		        add(ortemarkup);
		        
		        final	 WebMarkupContainer strassenmarkup = new WebMarkupContainer("strassenmarkup");
		        if(ort==null) strassenmarkup.setVisible(false);
		        else {
		        	strassenmarkup.setVisible(true);
		        	 landmarkup.setVisible(false);
		        	 ortemarkup.setVisible(false);
		        }
		        dropstrassen.setOutputMarkupPlaceholderTag(true);
		        strassenmarkup.setOutputMarkupPlaceholderTag(true);
		        strassenmarkup.add(dropstrassen);
		        strassenmarkup.add(strassenfield);
		       
		        add(strassenmarkup);
		        strassenfield.add(new OnChangeAjaxBehavior() {

		            @Override
		            protected void onUpdate(AjaxRequestTarget target) {
		                    ortemarkup.setVisible(false);
		                    target.add(ortemarkup);
		                    target.add(dropstrassen);
		            }
		    });
		        orte.add(new AjaxFormComponentUpdatingBehavior("onchange")
		        {
		            @Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {setNewort(false);
		            strassenmarkup.setVisible(true);
		            target.add(strassenmarkup);
		     
		            }
		        });
		        final	 WebMarkupContainer objektmarkup = new WebMarkupContainer("objektmarkup");
		      
		        objektmarkup.add(objekte);
		        objektmarkup.setOutputMarkupPlaceholderTag(true);
				 objekte.setOutputMarkupPlaceholderTag(true);
				 add(objektmarkup);
		//		 objektmarkup.add(objekte);
		        final	 WebMarkupContainer personmarkup = new WebMarkupContainer("personmarkup");
		        personmarkup.add(personen);
		        personmarkup.setOutputMarkupPlaceholderTag(true);
				 personen.setOutputMarkupPlaceholderTag(true);
				 add(personmarkup);
				// personmarkup.add(personen);
		        
		        
		        
		        
		        if(strassen==null) {
		        	
		        	objektmarkup.setVisible(false);
		        	personmarkup.setVisible(false);
		        }
		        else {
		        	personmarkup.setVisible(true);
		        	objektmarkup.setVisible(true);
		        	 landmarkup.setVisible(false);
		        	 ortemarkup.setVisible(false);
		        	 strassenmarkup.setVisible(false);
		        }
		        
		    //    personmarkup.add(objekte);
		        
		      
			// personmarkup.setOutputMarkupPlaceholderTag(true);
		//	 person.setOutputMarkupPlaceholderTag(true);
			
			    dropstrassen.add(new AjaxFormComponentUpdatingBehavior("onchange")
		        {
		            @Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {setNewstrasse(false);
		          
		            if(pageparameters.getPosition("eigtid")>-1&&pageparameters.getPosition("objid")>-1&&pageparameters.get("eigtid").toString().equals("0")&&!pageparameters.get("objid").toString().equals("0")){
		            	personmarkup.setVisible(true);
		            	
		            }
		            else{
		            	
		            	objektmarkup.setVisible(true);}
		            target.add(objektmarkup);
		           target.add(personmarkup);
		            }
		        });
			 /*   objekte.add(new AjaxFormComponentUpdatingBehavior("onchange")
		        {
		            @Override
		            protected void onUpdate(AjaxRequestTarget target)
		            {
		            	target.add()
		            	//setNewobjekt(false);
		            }
		        });*/
			/*
			 * add(new RequiredTextField<String>("land.lastName")); add(new
			 * TextField<String>("land.department"));
			 */
			// add(new CheckBox("assignRoles"));

		}
		@Override
		public boolean evaluate()
		{
			return isNewLocation();
		}
	}

	/**
	 * The land details step.
	 */
	private final class OrtStep extends WizardStep implements ICondition
	{
		/**
		 * Construct.
		 */
		public OrtStep()
		{
			setTitleModel(new ResourceModel("ort.title"));
			setSummaryModel(new StringResourceModel("ort.summary", this, new Model<Land>(
				land)));
			add(new RequiredTextField<String>("ort.ortname"));
			add(new RequiredTextField<String>("ort.ortplz"));
			/*
			 * add(new RequiredTextField<String>("land.lastName")); add(new
			 * TextField<String>("land.department"));
			 */
			// add(new CheckBox("assignRoles"));

		}
		@Override
		public boolean evaluate()
		{
			return newort;
		}
		

	}

	/**
	 * The land name step.
	 */
	private final class LandStep extends WizardStep implements ICondition
	{
		/**
		 * Construct.
		 */
		public LandStep()
		{
			super(new ResourceModel("land.title"), new ResourceModel("land.summary"));
	
			add(new RequiredTextField<String>("land.landname"));
			add(new RequiredTextField<String>("land.kennzeichen"));
/*
 * FormComponent<String> email = new
 * RequiredTextField<String>("land.email").add(EmailAddressValidator.getInstance()); add(email);
 * 
 * TextField<String> emailRepeat = new TextField<String>("emailRepeat", new Model<String>());
 * add(emailRepeat);
 * 
 * add(new EqualInputValidator(email, emailRepeat));
 */
		}
		@Override
		public boolean evaluate()
		{
			return newland;
		}
	}

	private final class AngebotStep extends WizardStep implements ICondition
	{
		 IModel<List<? extends Angstatus>> makeChoicesAngstatus = new AbstractReadOnlyModel<List<? extends Angstatus>>()
		  	        {
		  	            @Override
		  	            public List<Angstatus> getObject()
		  	            { List<Angstatus> angstatuslist=new  ArrayList<Angstatus>(); 
		  	            	
		  	            	Iterator angstatusiterator=angstatusManager.getAngstatuses().iterator();
		  	            while(angstatusiterator.hasNext()){
		  	            	Angstatus angstatus=(Angstatus)angstatusiterator.next();
		  	            	angstatuslist.add(angstatus);
		  	            }
		  	               
		  	                return angstatuslist;
		  	            }

		  	        };
		  	      IModel<List<? extends Kondition>> makeChoicesKondition = new AbstractReadOnlyModel<List<? extends Kondition>>()
		  	  	        {
		  	  	            @Override
		  	  	            public List<Kondition> getObject()
		  	  	            { List<Kondition> konditionlist=new  ArrayList<Kondition>(); 
		  	  	            	
		  	  	            	Iterator konditioniterator=konditionManager.getKonditionen().iterator();
		  	  	            while(konditioniterator.hasNext()){
		  	  	            	Kondition kondition=(Kondition)konditioniterator.next();
		  	  	            	konditionlist.add(kondition);
		  	  	            }
		  	  	               
		  	  	                return konditionlist;
		  	  	            }

		  	  	        };
		  
		public  AngebotStep()
		{
			super(new ResourceModel("land.title"), new ResourceModel("land.summary"));
	

            add(new DropDownChoice<Angstatus>("angebot.angstatus", makeChoicesAngstatus,new ChoiceRenderer<Angstatus>("statustext","id")));
            add(new RequiredTextField("angebot.id"));
            add(new RequiredTextField("angebot.angkurzbeschreibung"));
              add(new RequiredTextField("angebot.anglagebeschreibung"));
                add(new RequiredTextField("angebot.anganz"));
                  add(new RequiredTextField("angebot.angaufdatum"));
                  add(new DropDownChoice<Kondition>("angebot.kondition", makeChoicesKondition,new ChoiceRenderer<Kondition>("kontext","id")));
/*
 * FormComponent<String> email = new
 * RequiredTextField<String>("land.email").add(EmailAddressValidator.getInstance()); add(email);
 * 
 * TextField<String> emailRepeat = new TextField<String>("emailRepeat", new Model<String>());
 * add(emailRepeat);
 * 
 * add(new EqualInputValidator(email, emailRepeat));
 */
		}
		@Override
		public boolean evaluate()
		{
			return newAngebot;
		}
	}
	
	
	private final class ScoutStep extends WizardStep implements ICondition
	{
		public String chosen="";
		
		   private List<String> keys=Arrays.asList(new String[]{"strassen","objekt","telefon","handy","ort","strasse","plz","internet","person","orte","adresse","hausnummer"}); 
		
		
		final Link callStichwortGoogleCalendar=	    new Link("callStichwortGoogleCalendar"){   public void onClick() {
		  	  
		   	 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("https://www.google.com/calendar/render?cid=wichtigtuer.braun%40googlemail.com"));
		   }
		   };
		   final Link callStichwortGoogleContacts=	    new Link("callStichwortGoogleContacts"){   public void onClick() {
			  	  
			   	 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/ca/&ss=1&scc=1&ltmpl=googlemail&hl=de"));
			   }
			   };

private Xtyp xtyp;

public Xtyp getXtyp() {
	return xtyp;
}

public void setXtyp(Xtyp xtyp) {
	this.xtyp = xtyp;
}
private Boolean whereincludedProperty=new Boolean(true);
public Boolean getWhereincludedProperty() {
	return whereincludedProperty;
}

public void setWhereincludedProperty(Boolean whereincludedProperty) {
	this.whereincludedProperty = whereincludedProperty;
}
private Boolean whoincludedProperty=new Boolean(true);
public Boolean getWhoincludedProperty() {
	return whoincludedProperty;
}

public void setWhoincludedProperty(Boolean whoincludedProperty) {
	this.whoincludedProperty = whoincludedProperty;
}
private Boolean telefonincludedProperty=new Boolean(false);



public Boolean getTelefonincludedProperty() {
	return telefonincludedProperty;
}

public void setTelefonincludedProperty(Boolean telefonincludedProperty) {
	this.telefonincludedProperty = telefonincludedProperty;
}
final CheckBox whereincluded=new CheckBox("whereincluded",new PropertyModel<Boolean>(this,"whereincludedProperty"));

final CheckBox whoincluded=new CheckBox("whoincluded",new PropertyModel<Boolean>(this,"whoincludedProperty"));

final CheckBox telefonincluded=new CheckBox("telefonincluded",new PropertyModel<Boolean>(this,"telefonincludedProperty"));


Eigentuemermuster selectedMuster;
	    public Eigentuemermuster getSelectedMuster() {
return selectedMuster;
}
	  
public void setSelectedMuster(Eigentuemermuster selectedMuster) {
this.selectedMuster = selectedMuster;
}

	
			
         
			   private	IModel<List<? extends Xtyp>> makeChoicesXtyp = new AbstractReadOnlyModel<List<? extends Xtyp>>()
	 	 	  	        {
	 	 	  	            @Override
	 	 	  	            public List<Xtyp> getObject()
	 	 	  	            { List<Xtyp> xtyplist=new  ArrayList<Xtyp>(); 
	 	 	  	            	
	 	 	  	            	Iterator xtypiterator=xtypManager.getXtyps().iterator();
	 	 	  	            while(xtypiterator.hasNext()){
	 	 	  	            	Xtyp xtyp=(Xtyp)xtypiterator.next();
	 	 	  	            	
	 	 	  	            	xtyplist.add(xtyp);
	 	 	  	            }
	 	 	  	               
	 	 	  	                return xtyplist;
	 	 	  	            }

	 	 	  	        };
         
        
         
	 	 	  	    final DropDownChoice<Xtyp> xtypselect = new DropDownChoice<Xtyp>("xtyp",new PropertyModel<Xtyp>(this,"xtyp"),
				     		 makeChoicesXtyp,new ChoiceRenderer<Xtyp>("xtypkuerzel","id"));	  	
         
         
         
     	IModel<List<? extends Eigentuemermuster>> makeChoicesEigentuemermuster = new AbstractReadOnlyModel<List<? extends Eigentuemermuster>>()
		        {
		            @Override
		            public List<Eigentuemermuster> getObject()
		            { List<Eigentuemermuster> eigentuemermusterlist=new  ArrayList<Eigentuemermuster>(); 
		           
   	            	Iterator eigentuemermusteriterator=eigentuemermusterManager.getEigentuemermusters().iterator();
		            
		            while(eigentuemermusteriterator.hasNext()){
		            	Eigentuemermuster eigentuemermuster=(Eigentuemermuster)eigentuemermusteriterator.next();
		            	eigentuemermusterlist.add(eigentuemermuster);
		            }
		           
		                return eigentuemermusterlist;
		            }

		        };		
		        final PageParameters pars=new PageParameters(){{
		      		set("context","parameter");
		        }};

		    	IModel<List<? extends Type>> makeChoicesType = new AbstractReadOnlyModel<List<? extends Type>>()
				        {
				            @Override
				            public List<Type> getObject()
				            {Iterator it=  typeManager.getTypees().iterator();
				            	List<Type> scoutlist=new ArrayList<Type>();
				            while(it.hasNext()){
				            	Type index=(Type)it.next();
				            	scoutlist.add(index);
				            	
				            }
				                return scoutlist;
				            }

				        };
				        IModel<List<? extends Objarttyp>> makeChoicesObjarttyp = new AbstractReadOnlyModel<List<? extends Objarttyp>>()
						        {
						            @Override
						            public List<Objarttyp> getObject()
						            {Iterator it=  objarttypManager.getObjarttypes().iterator();
						            	List<Objarttyp> scoutlist=new ArrayList<Objarttyp>();
						            while(it.hasNext()){
						            	Objarttyp index=(Objarttyp)it.next();
						            	scoutlist.add(index);
						            	
						            }
						                return scoutlist;
						            }

						        };	
				    
				  	IChoiceRenderer<Eigentuemertyp> eigentuemertypchoicerenderer=  new IChoiceRenderer<Eigentuemertyp>() {

					    public Object getDisplayValue(Eigentuemertyp object)
					    {
					    	
					        return object.getTypenbeschreibung();
					    }

					    public String getIdValue(Eigentuemertyp object,int index)
					    {
					        return object.toString();
					    }

					  
					    };
				  	
				  	
final DropDownChoice<Eigentuemermuster> eigentuemermuster = new DropDownChoice("eigentuemermuster",
	  new PropertyModel<Eigentuemermuster>(this, "selectedMuster"),	 makeChoicesEigentuemermuster,new ChoiceRenderer("eigentuemermuster"));	    
   	        
final DropDownChoice<Type> type = new DropDownChoice("scout.type",makeChoicesType);	 
private final ScoutUtil scoutUtil;


 	private        IModel<List<? extends Eigentuemertyp>> makeChoicesEigentuemertyp = new AbstractReadOnlyModel<List<? extends Eigentuemertyp>>()
	        {
	            @Override
	            public List<Eigentuemertyp> getObject()
	            { List<Eigentuemertyp> eigentuemertyplist=new  ArrayList<Eigentuemertyp>(); 
	         
	            Iterator eigentuemertypiterator=eigentuemertypManager.getEigentuemertypes().iterator();
	            	while(eigentuemertypiterator.hasNext()){
	            	Eigentuemertyp eigentuemertyp=(Eigentuemertyp)eigentuemertypiterator.next();
	            	eigentuemertyplist.add(eigentuemertyp);}
	          return eigentuemertyplist;
	            }
	        };
	      
	        final DropDownChoice<Objarttyp> objarttyp = new DropDownChoice("scout.objarttyp",makeChoicesObjarttyp);	    
	       
	        
	        final DropDownChoice<Eigentuemertyp> eigentuemertyp = new DropDownChoice<Eigentuemertyp>("eigentuemertyp",
  		     		new PropertyModel<Eigentuemertyp>(this,"zuordnungsartid"), makeChoicesEigentuemertyp,eigentuemertypchoicerenderer);	  
  	
	  		
   	  PopupSettings scoutPopupSettings = new PopupSettings(PopupSettings.RESIZABLE |
                 PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);	
   	  
   	  Date telefondat=new Date();
   	 Eigentuemertyp zuordnungsartid; 
			        	public Eigentuemertyp getZuordnungsartid() {
			return zuordnungsartid;
		}

		public void setZuordnungsartid(Eigentuemertyp zuordnungsartid) {
			this.zuordnungsartid = zuordnungsartid;
		}

						public Date getTelefondat() {
			return telefondat;
		}

		public void setTelefondat(Date telefondat) {
			this.telefondat = telefondat;
		}

		public ScoutStep()
		{
			super(new ResourceModel("land.title"), new ResourceModel("land.summary"));
	
			scoutUtil=new ScoutUtil(strassenManager,objektartManager,objektManager,scoutManager,objektsuchManager,orteManager,personManager);
			final ExternalLink scoutlink=new ExternalLink("scoutlink","http://www.immobilienscout24.de/expose/"+scout.getId().toString());
			Label linksid=new Label("scout.id");
		
 add(linksid);
 
final CheckBox yesScout=new CheckBox("scout.yesScout");
add(yesScout);






add(telefonincluded);
add(xtypselect);
add(whoincluded);
add(whereincluded);

add(eigentuemermuster);


add(eigentuemertyp);

/*final Label result;
add(result = new Label("result", new PropertyModel<String>(this, "result")));
result.setOutputMarkupId(true);*/

/*
 * First modal window
 */

List<Object> l1 = new ArrayList<Object>();
Map<String,List<String>> structure=scoutUtil.analyzeTelefonfield(scout);
Iterator it=structure.keySet().iterator();
while(it.hasNext()){
	String key=(String)it.next();	
	 List<Object> l2 = new ArrayList<Object>();
	 l2.add(key);
	 Iterator its=structure.get(key).iterator();
	 while(its.hasNext()){
			String found=(String)its.next();
			
			l2.add(found);}

l1.add(l2);}
TreeModel treeModel = convertToTreeModel(l1);

final Tree tree = new Tree("tree", treeModel)
{
	@Override
	protected String renderNode(TreeNode node)
	{
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)node;
		Object userObject = treeNode.getUserObject();
	  return ((String)userObject);
	}
	 @Override
	 protected void onNodeLinkClicked(AjaxRequestTarget target, TreeNode node) { 
		if (
			!keys.contains((String)((DefaultMutableTreeNode) node).getUserObject()))	
		{System.err.println((String)((DefaultMutableTreeNode) node).getUserObject());
	chosen=(String)((DefaultMutableTreeNode) node).getUserObject();
		
		
		}	 }
};

add(tree);



		List<Object> lo1 = new ArrayList<Object>();
		Map<String,List<String>> structureo=scoutUtil.analyzeWherefield(scout);
		it=structureo.keySet().iterator();
		while(it.hasNext()){
			String key=(String)it.next();	
			 List<Object> lo2 = new ArrayList<Object>();
			 lo2.add(key);
			 Iterator its=structureo.get(key).iterator();
			 while(its.hasNext()){
					String found=(String)its.next();
					
					lo2.add(found);}

		lo1.add(lo2);}
		TreeModel treeoModel = convertToTreeModel(lo1);

		final Tree treeo = new Tree("treeo", treeoModel)
		{
			@Override
			protected String renderNode(TreeNode node)
			{
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)node;
				Object userObject = treeNode.getUserObject();
			  return ((String)userObject);
			}
			 @Override
			 protected void onNodeLinkClicked(AjaxRequestTarget target, TreeNode node) { 
				if (
					!keys.contains((String)((DefaultMutableTreeNode) node).getUserObject()))	
				{System.err.println((String)((DefaultMutableTreeNode) node).getUserObject());
			chosen=(String)((DefaultMutableTreeNode) node).getUserObject();
				//	RedirectRequestHandler telefonbuchhandler=new RedirectRequestHandler("http://www4.dastelefonbuch.de/?kw="+((String)((DefaultMutableTreeNode) node).getUserObject()) +"&ort=&pu=1&s=a10000&cmd=search&ort_ok=0&vert_ok=0&ciid=&rgid=&kgs=&district=&ciquarter=&pcZVO=&cx=&cy=&lat=&lon=&radius=&sp=0&aktion=23&ckrid=");
				//getRequestCycle().scheduleRequestHandlerAfterCurrent(telefonbuchhandler);
				
				
				}	 }
		};
		// disable ajax links in this example
		//tree.setLinkType(LinkType.REGULAR);
		add(treeo);

		






 TextArea<String> where = new TextArea<String>("scout.where");		
 add(where);
TextArea<String> what = new TextArea<String>("scout.what");		
 add(what);
TextArea<String> who = new TextArea<String>("scout.who");		
 add(who);
TextArea<String> phone = new TextArea<String>("scout.phone");		

DateTimeField telefondate = new DateTimeField("telefondate",new PropertyModel<Date>(this,"telefondat"))
{
   @Override
   public Locale getLocale()
   {
       return Locale.GERMAN;
   }
};
add(telefondate); 





 add(phone);
//final CheckBox bearbeitet=new CheckBox("bearbeitet");
 IChoiceRenderer<Type> typechoice=        		new IChoiceRenderer<Type>() {

	    public Object getDisplayValue(Type object)
	    {Type type= object;
   return type.getType();
	     
	    	
	    }

	    public String getIdValue(Type object,int index)
	    {Type type= object;
	        return type.getId().toString();
	    }
}; 
type.setChoiceRenderer(typechoice);
add(type);
IChoiceRenderer<Objarttyp> objarttypchoice=  new IChoiceRenderer<Objarttyp>() {

    public Object getDisplayValue(Objarttyp object)
    {Objarttyp objarttyp= object;
return objarttyp.getTypentext();
     
    	
    }

    public String getIdValue(Objarttyp object,int index)
    {Objarttyp objarttyp= object;
        return objarttyp.getId().toString();
    }
}; 
objarttyp.setChoiceRenderer(objarttypchoice);
add(objarttyp);

		xtypselect.add(new AjaxFormComponentUpdatingBehavior("onchange")
		 {
		     @Override
		     protected void onUpdate(AjaxRequestTarget target)
		     {	
		   
		    
		     }
		 });	
		
		eigentuemermuster.add(new AjaxFormComponentUpdatingBehavior("onchange")
		 {
		     @Override
		     protected void onUpdate(AjaxRequestTarget target)
		     {
		    	
	  
		    
		     }
		 });	
     }
				           
				        	
				       
				            public String getResult()
				            {
				                return result;
				            }

				            /**
				             * @param result
				             *            the result to set
				             */
				            public void setResult(String result)
				            {
				                this.result = result;
				            }

				            private String result;	
				            
	
				            private TreeModel convertToTreeModel(List<Object> list)
				    		{
				    			TreeModel model = null;
				    			
				    			DefaultMutableTreeNode rootNode= new DefaultMutableTreeNode("Found");
				    			add(rootNode, list);
				    			model = new DefaultTreeModel(rootNode);
				    			return model;
				    		}
				    	 @SuppressWarnings("unchecked")
				    		private void add(DefaultMutableTreeNode parent, List<Object> sub)
				    		{
				    			for (Object obj : sub)
				    			{
				    				if (obj instanceof List)
				    				{
				    					DefaultMutableTreeNode child=new DefaultMutableTreeNode("<no>");
				    						Iterator itt=((List) obj).iterator();
				    						Object object=null;
				    						while(itt.hasNext()){
				    							object=itt.next();
				    							if(keys.contains(object)) break;
				    							}
				    				child = new DefaultMutableTreeNode(object);
				    				((List)obj).remove(object);
				    					
				    					
				    					parent.add(child);
				    					add(child, (List<Object>) obj);
				    					
				    			
				    				}
				    				else
				    				{
				    					DefaultMutableTreeNode child = new DefaultMutableTreeNode(obj);
				    					parent.add(child);
				    				}
				    			}
		}
		@Override
		public boolean evaluate()
		{
			return newScout;
		}
	}
	
	
	private final class NachweisStep extends WizardStep implements ICondition
	{
		
		public class angebotComparator implements Comparator<Angebot> {
		    public int compare(Angebot angebot1, Angebot angebot2) {
		    	if(angebot1.getId().substring(0,1).equals(angebot2.getId().substring(0,1))) {
		    		Long long1=new Long(angebot1.getId().substring(2));
		    		Long long2=new Long(angebot2.getId().substring(2));
		    		return long1.compareTo(long2);
		    	}
		        return angebot1.getId().substring(0,1).compareTo(angebot2.getId().substring(0,1));
		     
		}
		}
		
		IChoiceRenderer<Emailbrief> emailbriefchoicerenderer=        		new IChoiceRenderer<Emailbrief>() {

	    	  public Object getDisplayValue(Emailbrief emailbrief)
	    	    {return emailbrief.getId()+" "+emailbrief.getSubject();
	    	    }

	    	    public String getIdValue(Emailbrief object,int index)
	    	    {return object.getId();
	    	    }
	}; 	 	
		
		
	     
	      
	      final Model model = new Model("Response String"); 
	     
	      final Label label = new Label("response", model);
		
		
		
		
		 private final Link callPdfAnhangEmailNachweise=	new Link("callPdfAnhangEmailNachweise") {
			    public void onClick() {
			    	
			    	
			    	// getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("http://"+((WebRequest) getRequest()). getClientUrl().getHost()+":8088/cocoon-kbr/anzeigen/pdfanhangemail/"+((Nachweise)NachweiseForm.this.getDefaultModelObject()).getId().toString()+"/nachweise.flow"));
			    }
			    };
			public Link getCallPdfAnhangEmailNachweise() {
			return callPdfAnhangEmailNachweise;
		}
		public Link getCallCocoonPdfNachweise() {
			return callCocoonPdfNachweise;
		}
			private    final Link callCocoonPdfNachweise=	    new Link("callCocoonPdfNachweise"){   public void onClick() {
				
				 
	  		 
			     	 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("http://"+((WebRequest) getRequest()). getClientUrl().getHost()+":8088/controllerbraunimmobilienget?id=xbrief&reqparam=3&name="+nachweis.getId().toString()));
				    }
				    };
			    private	IModel<List<? extends Angebot>> makeChoicesAngebote = new AbstractReadOnlyModel<List<? extends Angebot>>()
	 	        {
	 	            @Override
	 	            public List<Angebot> getObject()
	 	            { List<Angebot> angebotelist=new  ArrayList<Angebot>(); 
	 	            	Iterator angeboteiterator=angebotManager.getAngebote().iterator();
	 	            while(angeboteiterator.hasNext()){
	 	            	Angebot angebot=(Angebot)angeboteiterator.next();
	 	            	if(angebot.getAngstatus().getId().longValue()==1)
	 	            	angebotelist.add(angebot);
	 	            }
	 	            
	 	            
	 	           Collections.sort(angebotelist, new angebotComparator());
	 	                return angebotelist;
	 	            }

	 	        };
	 	           IChoiceRenderer<Angebot> angebotchoicerenderer=        		new IChoiceRenderer<Angebot>() {

	 	        	  public Object getDisplayValue(Angebot angebot)
	 		    	    {
	 		    	        return angebot.getId();
	 		    	    }

	 		    	    public String getIdValue(Angebot angebot,int index)
	 		    	    {
	 		    	        return angebot.getId();
	 		    	    }
	 	    }; 	              
	 	            
	 	            
	 	            
	 	      private	IModel<List<? extends Mitarbeiter>> makeChoicesMitarbeiter = new AbstractReadOnlyModel<List<? extends Mitarbeiter>>()
	 	  	        {
	 	  	            @Override
	 	  	            public List<Mitarbeiter> getObject()
	 	  	            { List<Mitarbeiter> mitarbeiterlist=new  ArrayList<Mitarbeiter>(); 
	 	  	            	Iterator mitarbeiteriterator=mitarbeiterManager.getMitarbeiteres().iterator();
	 	  	            while(mitarbeiteriterator.hasNext()){
	 	  	            	Mitarbeiter mitarbeiter=(Mitarbeiter)mitarbeiteriterator.next();
	 	  	            	mitarbeiterlist.add(mitarbeiter);
	 	  	            	
	 	  	            }
	 	  	               
	 	  	                return mitarbeiterlist;
	 	  	            }

	 	  	        };
	 	      
	 	  	        IChoiceRenderer<Objekte> objektechoicerenderer=        		new IChoiceRenderer<Objekte>() {

		        	  public Object getDisplayValue(Objekte objekt)
			    	    {
			    	        return objekt.getObjhausnummer();
			    	    }

			    	    public String getIdValue(Objekte object,int index)
			    	    {
			    	        return object.getId().toString();
			    	    }
		    }; 	  

		    IChoiceRenderer<Mitarbeiter> mitarbeiterchoicerenderer=        		new IChoiceRenderer<Mitarbeiter>() {


	    	    public Object getDisplayValue(Mitarbeiter object)
	    	    {
	        	
	    	        return object.getPerson().getEigtName();
	    	    }

	    	    public String getIdValue(Mitarbeiter object,int index)
	    	    {
	    	        return object.getId().toString();
	    	    }

	    	  
	    	    };
		    
	    	    IChoiceRenderer<Xtyp> xtypchoicerenderer=        		new IChoiceRenderer<Xtyp>() {

	    	    public Object getDisplayValue(Xtyp object)
	    	    {    	    	
	    	        return object.getXtypkuerzel();
	    	    }

	    	    public String getIdValue(Xtyp object,int index)
	    	    {
	    	        return object.getId().toString();
	    	    }

	    	  
	    	    };

		    
		    
		    
		    
	 	  	     private	IModel<List<? extends Objekte>> makeChoicesObjekt = new AbstractReadOnlyModel<List<? extends Objekte>>()
	 	 	  	        {
	 	 	  	            @Override
	 	 	  	            public List<Objekte> getObject()
	 	 	  	            { List<Objekte> objektlist=new  ArrayList<Objekte>(); 
	 	 	  	            	if ((nachweis.getPerson()!=null)&&(nachweis.getPerson().getId().longValue()>0)){
	 	 	  	            		Iterator objekteiterator=nachweis.getPerson().getObjperszuords().iterator();
	 	 	  	            while(objekteiterator.hasNext()){
	 	 	  	            	Objperszuord persobjzuord=(Objperszuord)objekteiterator.next();
	 	 	  	            	objektlist.add(persobjzuord.getObjekt());
	 	 	  	            }
	 	 	  	            	}
	 	 	  	                return objektlist;
	 	 	  	            	
	 	 	  	            }

	 	 	  	        };
	 	  	     private	IModel<List<? extends Xtyp>> makeChoicesXtyp = new AbstractReadOnlyModel<List<? extends Xtyp>>()
	 	 	  	        {
	 	 	  	            @Override
	 	 	  	            public List<Xtyp> getObject()
	 	 	  	            { List<Xtyp> xtyplist=new  ArrayList<Xtyp>(); 
	 	 	  	            	Iterator xtypiterator=xtypManager.getXtyps().iterator();
	 	 	  	            while(xtypiterator.hasNext()){
	 	 	  	            	Xtyp xtyp=(Xtyp)xtypiterator.next();
	 	 	  	            	xtyplist.add(xtyp);
	 	 	  	            }
	 	 	  	               
	 	 	  	                return xtyplist;
	 	 	  	            }

	 	 	  	        };
	 	 	  	        
	 	 	  	        
	 	
	 	 	  	        
	 	 	  	        
	 	 	  	 
	 	
	 	 	  	   
	 	 	  	  
					final DropDownChoice<Angebot> angebote = new DropDownChoice<Angebot>("nachweis.angebot",
	 	 		     		 makeChoicesAngebote,angebotchoicerenderer);	  
	 	 	  	 final DropDownChoice<Angebot> angebote1 = new DropDownChoice<Angebot>("nachweis.angebot1",
		 		     		 makeChoicesAngebote,angebotchoicerenderer);	
	 	 	   final DropDownChoice<Angebot> angebote2 = new DropDownChoice<Angebot>("nachweis.angebot2",
			     		 makeChoicesAngebote,angebotchoicerenderer);	
	 	
	 	   final DropDownChoice<Mitarbeiter> nachmitarbnr = new DropDownChoice<Mitarbeiter>("nachweis.mitarbeiter",
		     		 makeChoicesMitarbeiter,mitarbeiterchoicerenderer);	 
	 	   final DropDownChoice<Xtyp> nachxnr = new DropDownChoice<Xtyp>("nachweis.xtyp",
		     		 makeChoicesXtyp,xtypchoicerenderer);	 
	 	/* 	 final DropDownChoice<Xtyp> nachxnr = new DropDownChoice<Xtyp>("xtyp.id",
	 		 makeChoicesXtyp);*/	  		
	 	  final DropDownChoice<Objekte> nachobjnr = new DropDownChoice<Objekte>("nachweis.objekt",
		     		 makeChoicesObjekt,objektechoicerenderer);	  	
	 	     
		
		
		
		
		public NachweisStep()
		{
			super(new ResourceModel("land.title"), new ResourceModel("land.summary"));

					
					 PopupSettings googlePopupSettings = new PopupSettings(PopupSettings.RESIZABLE |
			                 PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
		Label id=new Label("nachweis.id");
		add(id);
		nachmitarbnr.setRequired(true);
		nachxnr.setRequired(true);

		add(angebote);

		add(angebote1);

		add(angebote2);

		add(nachmitarbnr);

		add(nachxnr);
		DateLabel nachdatum=new DateLabel("nachweis.nachdatum", new StyleDateConverter("L-", true)){
		    @Override
		    public Locale getLocale()
		    {
		        return getSession().getLocale();
		    }
		};
		add(nachdatum); 
		Label nachkundnr=new Label("nachweis.kunde.id");
		 add(nachkundnr);
		 Label nacheigtnr=new Label("nachweis.person.id");
		 add(nacheigtnr);
		 TextField<Double> nachgedruckt= new TextField<Double>("nachweis.nachgedruckt");
		 add(nachgedruckt);
		TextField<String> anlage1= new TextField<String>("nachweis.anlage1");
		 add(anlage1);
		 TextField<String> anlage2= new TextField<String>("nachweis.anlage2");
		 add(anlage2);
		 TextField<String> anlage3= new TextField<String>("nachweis.anlage3");
		 add(anlage3);
		 TextField<String> anlage4= new TextField<String>("nachweis.anlage4");
		 add(anlage4);
		 TextField<String> unterlagen1= new TextField<String>("nachweis.unterlagen1");
		 add(unterlagen1);
		 TextArea<String> unterlagen2 = new TextArea<String>("nachweis.unterlagen2");		
		 add(unterlagen2);
		 TextField<String> unterlagen3= new TextField<String>("nachweis.unterlagen3");
		 add(unterlagen3);
		 TextField<String> unterlagen4= new TextField<String>("nachweis.unterlagen4");
		 add(unterlagen4);
		 TextField<Double> steuerfeld= new TextField<Double>("nachweis.steuerfeld");
		 add(steuerfeld);
		 add(nachobjnr);
		 TextField<String> nachdoku= new TextField<String>("nachweis.nachdoku");
		 add(nachdoku);
		 label.setOutputMarkupId(true);
/*		 AjaxFallbackLink restLink=new AjaxFallbackLink("rest") {
		     public void onClick(AjaxRequestTarget target) {
		         if (target != null) {
		        	  String wholeline = "";
		        	 try{HttpClient client = new DefaultHttpClient();
			    	  HttpPost post = new HttpPost("http://localhost:8088/rest/SendMailPipeService");
			    	  List nameValuePairs = new ArrayList(1);
			    	  nameValuePairs.add(new BasicNameValuePair("name", "int")); //you can as many name value pair as you want in the list.
			    	  nameValuePairs.add(new BasicNameValuePair("nachweisnr", ((Nachweise)NachweiseForm.this.getDefaultModelObject()).getId().toString()));
			    	  post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			    	  HttpResponse response = client.execute(post);
			    	  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			    	  String line="";
			    	  while ((line = rd.readLine()) != null) {
			    	   System.out.println(line);
			    	   wholeline=wholeline+line;
			    	  }}
			    	catch(Exception ex){System.out.println(ex);}

			    	
		        	 
		        	 model.setObject(wholeline); 
		              label.setDefaultModel(model); 
		           
		             target.add(label);
		         }
		     }
		 };
		 
		 add(restLink);
		 restLink.setVisible(false);*/
		// add(label);

		final CheckBox email=new CheckBox("nachweis.email");
		add(email);
	/*	final Button saveNachweiseButton=new Button("saveNachweiseButton")
		{
			@Override
			public void onSubmit()
			{PageParameters pars = new PageParameters();
			








			
			if(NachweiseForm.this.getModelObject().getKunde()!=null) {
				if(NachweiseForm.this.getModelObject().getId()==null){
				Kunde kunde = kundeManager.get(NachweiseForm.this.getModelObject().getKunde().getId());
				NachweiseForm.this.getModelObject().setKunde(kunde);
				kunde.addNachweis(NachweiseForm.this.getModelObject());}
				System.err.println("Kunde");
			}

			
				if(NachweiseForm.this.getModelObject().getAngebot()!=null) {
					
					System.err.println("Angebot nach submit "+((Nachweise)NachweiseForm.this.getDefaultModelObject()).getAngebot());
					if(NachweiseForm.this.getModelObject().getId()!=null) {
						
					Angebot angebot=angebotManager.get(NachweiseForm.this.getModelObject().getAngebot().getId());
					NachweiseForm.this.getModelObject().setAngebot(angebot);
					
					angebot.addNachweis(NachweiseForm.this.getModelObject());}
					System.err.println("Angebot");
				}
		if(NachweiseForm.this.getModelObject().getAngebot1()!=null) {
					
					System.err.println("Angebot1 nach submit "+NachweiseForm.this.getModelObject().getAngebot1());
					if(NachweiseForm.this.getModelObject().getId()!=null) {
						
					Angebot angebot=angebotManager.get(NachweiseForm.this.getModelObject().getAngebot1().getId());
					NachweiseForm.this.getModelObject().setAngebot1(angebot);
					
					angebot.addNachweis1(NachweiseForm.this.getModelObject());}
					System.err.println("Angebot1");
				}
		if(NachweiseForm.this.getModelObject().getAngebot2()!=null) {
			
			System.err.println("Angebot2 nach submit "+NachweiseForm.this.getModelObject().getAngebot2());
			if(NachweiseForm.this.getModelObject().getId()!=null) {
				
			Angebot angebot=angebotManager.get(NachweiseForm.this.getModelObject().getAngebot2().getId());
			NachweiseForm.this.getModelObject().setAngebot2(angebot);
			
			angebot.addNachweis2(NachweiseForm.this.getModelObject());}
			System.err.println("Angebot2");
		}
			

				
			
				
				if(((Nachweise)NachweiseForm.this.getDefaultModelObject()).getPerson()!=null) {
					
					if(NachweiseForm.this.getModelObject().getId()==null){
						Personen  person=personManager.get(((Nachweise)NachweiseForm.this.getModelObject()).getPerson().getId());
					System.err.println("Person gefunden "+person);
					NachweiseForm.this.getModelObject().setPerson(person);
					person.addNachweis(NachweiseForm.this.getModelObject());
					personManager.save(person);
					
					System.err.println("Person "+NachweiseForm.this.getModelObject().getId());
					
					}
					
				}
				
				if(NachweiseForm.this.getModelObject().getObjekt()!=null) {
					if(NachweiseForm.this.getModelObject().getId()==null){
					Objekte objekt=objektManager.get(((Nachweise)NachweiseForm.this.getModelObject()).getObjekt().getId());
					NachweiseForm.this.getModelObject().setObjekt(objekt);
					objekt.addNachweise(NachweiseForm.this.getModelObject());}
					else{
						Objekte objekt=objektManager.get(((Nachweise)NachweiseForm.this.getModelObject()).getObjekt().getId());
						NachweiseForm.this.getModelObject().setObjekt(objekt);
						objekt.addNachweise(NachweiseForm.this.getModelObject());	
					objektManager.save(objekt);
						
					}
					System.err.println("Objekt");
				}
				
				
				
			
			
			
				try{
			nachweiseManager.saveNachweise(NachweiseForm.this.getModelObject());	
				
				}catch(NachweiseExistsException ex){setResponsePage(MaklerSearchPage.class,pars);}
			
		pars.add("nachweisnr",NachweiseForm.this.getModelObject().getId().toString() );
		System.err.println("nächste Seite");
		setResponsePage(NachweisPage.class,pars);
			}

			};  
			add(saveNachweiseButton);
			saveNachweiseButton.setOutputMarkupId(true);*/

		  
		   
		/*	    callCocoonPdfNachweise.setOutputMarkupId(true);
		     add(callCocoonPdfNachweise);
		     callCocoonPdfNachweise.setVisible(false);
		     callCocoonPdfNachweise.setPopupSettings(googlePopupSettings);
			
				 email.add(new AjaxFormComponentUpdatingBehavior("onchange")
			        {
			            @Override
			            protected void onUpdate(AjaxRequestTarget target)
			            {
			       
			           callCocoonPdfNachweise.setVisible(false);
			        
			           target.add(callCocoonPdfNachweise);

			           
			            }
			        });
			   if(modele.getObject().getId()!=null)  {
				  if (modele.getObject().getEmail().booleanValue()==true){
					  callCocoonPdfNachweise.setVisible(false); 
					  restLink.setVisible(true);
				  }
				  else{
					  callCocoonPdfNachweise.setVisible(true); 
				  restLink.setVisible(false);}  
			   } */
						        
		}
		@Override
		public boolean evaluate()
		{
			return newNachweis;
		}
	}
	
	
	private final class KundeStep extends WizardStep implements ICondition
	{
		 final TextArea<String> eigtanschrift = new TextArea<String>("eigtAnschrift");	
			
			
		 IModel<List<? extends Kundenart>> makeChoicesKundenart = new AbstractReadOnlyModel<List<? extends Kundenart>>()
			        {
			            @Override
			            public List<Kundenart> getObject()
			            { List<Kundenart> kundenartlist=new  ArrayList<Kundenart>(); 
			            	
			            	Iterator kundenartiterator=kundenartManager.getKundenartes().iterator();
			            while(kundenartiterator.hasNext()){
			            	Kundenart kundenart=(Kundenart)kundenartiterator.next();
			            	kundenartlist.add(kundenart);
			            }
			               
			                return kundenartlist;
			            }

			        };
			
			
			        final DropDownChoice<Kundenart> kundenart = new DropDownChoice<Kundenart>("kunde.kundenart", makeChoicesKundenart,new ChoiceRenderer<Kundenart>("kundenart","id"));
			     
			
			
			
			
			IModel<List<? extends Eigtstatus>> makeChoicesEigtStatus = new AbstractReadOnlyModel<List<? extends Eigtstatus>>()
			        {
			            @Override
			            public List<Eigtstatus> getObject()
			            { List<Eigtstatus> eigtstatuslist=new  ArrayList<Eigtstatus>(); 
			            	
			            	Iterator eigtstatusiterator=eigtstatusManager.getEigtstatuses().iterator();
			            while(eigtstatusiterator.hasNext()){
			            	Eigtstatus eigtstatus=(Eigtstatus)eigtstatusiterator.next();
			            	
			            	eigtstatuslist.add(eigtstatus);
			            }
			               
			                return eigtstatuslist;
			            }

			        };
			          
			          
			       
			       	
			      
			      
		
		public KundeStep()
		
		{  setTitleModel(new ResourceModel("person.title"));
			setSummaryModel(new StringResourceModel("person.summary", this, new Model<Strassen>(
				strassen)));	
			Label kundennr=new Label("kunde.id");
			
			add(kundennr);
			   add(kundenart);
			   DateTextField kunddatum = new DateTextField("kunde.datum", new StyleDateConverter("L-", true));
			add(kunddatum);
			Label eigtid=new Label("kunde.person.id");
			
		add(eigtid);
		add(new CheckBox("newLocation"));
	TextField<String> eigtHausnummer= new TextField<String>("kunde.person.eigtHausnummer");
	add(eigtHausnummer);
	TextField<String> eigtHomepage= new TextField<String>("kunde.person.eigtHomepage");
	add(eigtHomepage);
	final DropDownChoice<Eigtstatus> eigtstatus = new DropDownChoice<Eigtstatus>("kunde.person.eigtstatus", makeChoicesEigtStatus, new ChoiceRenderer<Eigtstatus>("eigt_status_text","id"));
	add(eigtstatus);

	final TextField<String> eigtname = new TextField<String>("kunde.person.eigtName");		
	add(eigtname);
	eigtname.setRequired(true);
	final TextArea<String> eigtanschrift = new TextArea<String>("kunde.person.eigtAnschrift");	
	add(eigtanschrift);
	eigtanschrift.setRequired(true);
	final TextArea<String> eigtemail = new TextArea<String>("kunde.person.eigtEmail");		
	add(eigtemail);

	final TextField<String> eigtbriefanrede = new TextField<String>("kunde.person.eigtBriefanrede");		
	add(eigtbriefanrede);
	eigtbriefanrede.setRequired(true);
	final CheckBox eigtaktuell=new CheckBox("kunde.person.eigtAktuell");
	add(eigtaktuell);

	final TextArea<String> eigttel = new TextArea<String>("kunde.person.eigtTel");		
	add(eigttel);
	final TextField<String> eigtfirma = new TextField<String>("kunde.person.eigtFirma");	
	add(eigtfirma);

	final TextArea<String> eigtinfo = new TextArea<String>("kunde.person.eigtInfo");		
	add(eigtinfo);
	final TextArea<String> eigttelefone = new TextArea<String>("kunde.person.eigtTelefone");		
	add(eigttelefone);
	eigttelefone.setVisible(false);
	DateTextField eigtaufdatum = new DateTextField("kunde.person.eigtaufDatum", new StyleDateConverter("L-", true))
	{
	  @Override
	  public Locale getLocale()
	  {
	      return getSession().getLocale();
	  }
	};
	add(eigtaufdatum); 
	eigtaufdatum.setRequired(true);
	DatePicker datePickereigtaufdatum = new DatePicker();
	datePickereigtaufdatum.setShowOnFieldClick(true);
	eigtaufdatum.add(datePickereigtaufdatum);
	DateTextField eigtletztkontakt = new DateTextField("kunde.person.eigtletztKontakt", new StyleDateConverter("L-", true))
	{
	@Override
	public Locale getLocale()
	{
	   return getSession().getLocale();
	}
	};
	add(eigtletztkontakt); 
	DatePicker datePickereigtletztkontakt = new DatePicker();
	datePickereigtletztkontakt.setShowOnFieldClick(true);
	eigtletztkontakt.add(datePickereigtletztkontakt);
	eigtletztkontakt.setRequired(true);



			
		}
		@Override
		public boolean evaluate()
		{
			return newKunde;
		}
	}
	
	
	/**
	 * The land details step.
	 */
	private final class StrassenStep extends WizardStep implements ICondition
	{
		/**
		 * Construct.
		 */
		public StrassenStep()
		{
			super(new ResourceModel("strassen.title"), null);
			setSummaryModel(new StringResourceModel("strassen.summary", this,
				new Model<Orte>(ort)));
			
			if(pageparameters.getPosition("objid")>-1&&pageparameters.getPosition("eigtid")>-1&&pageparameters.get("eigtid").toString().equals("0")){
				setNewobjekt(false);
				setNewperson(true);
				}
			if(pageparameters.getPosition("objid")>-1&&pageparameters.getPosition("eigtid")<0&&pageparameters.get("objid").toString().equals("0")){
				
				setNewobjekt(true);
				setNewperson(false);
				
			}
			final TextField<String> strnameField = new TextField<String>("strassen.strname");
			add(strnameField);
			final TextField<String> strhausbereichField = new TextField<String>("strassen.strhausbereich");
			add(strhausbereichField);
			final TextField<String> strplzField = new TextField<String>("strassen.strplz");
			add(strplzField);
			final CheckBox merkmalField = new CheckBox("strassen.merkmal");
			add(merkmalField);
			final TextField<String> planquadratField = new TextField<String>("strassen.planquadrat");
			add(planquadratField);
			add(new AbstractFormValidator()
			{
				@Override
				public FormComponent[] getDependentFormComponents()
				{
					// name and roles don't have anything to validate,
					// so might as well just skip them here
					return null;
				}

				@Override
				public void validate(Form<?> form)
				{
					
				}
			});
			
		}

		/**
		 * @see org.apache.wicket.extensions.wizard.WizardModel.ICondition#evaluate()
		 */
		@Override
		public boolean evaluate()
		{
			return newstrasse;
		}
	}

	

	/** Whether the assign roles step should be executed. */
	private boolean newstrasse = true;
	private boolean newort = true;
	private boolean newland = true;
	private boolean newLocation = false;
	private boolean newKunde = false;
	private boolean newScout = false;
	private boolean newNachweis = false;
	private boolean newobjekt = true;
	private boolean newperson = true;
	private boolean newAngebot = false;
	
	private Land land=null;
	private Orte ort=null;
	private Eigentuemertyp eigentuemertyp=null;
	private Personen person=null;
	private Scout scout=null;
	private Nachweise nachweis=null;
	private Angebot angebot=null;
	private Kunde kunde=null;
	private Strassen strassen=null;
	private Objekte objekt=null;
	
	private Class responsepage=null;
	private PageParameters pageparameters=null;
	/**
	 * Construct.
	 * 
	 * @param id
	 *            The component id
	 */
	public NewLandWizard(String id,Class responsepage,PageParameters pageparameters)
	{
		super(id);
this.responsepage=responsepage;
this.pageparameters=pageparameters;
		// create a blank land
		
		setDefaultModel(new CompoundPropertyModel<NewLandWizard>(this));
		if(responsepage.getSimpleName().equals("BraunHomePage")){
		
			PageParameters pars1=new PageParameters();
	    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	    	model.add(new SucheStep());	
	    	model.add(new LandStep());
	   	model.add(new OrtStep());
	   	model.add(new StrassenStep());
	   	model.add(new ObjektStep());
	    	setNewLocation(true);
			}
		}
		if(responsepage.getSimpleName().equals("AngebotWizardTree")){
			PageParameters pars1=new PageParameters()
	    	.add("kundennr","not null")
	    	.add("angnr","not null")
	    		.add("eigtid","not null")
	    		.add("angnr","not null")
	    	.add("objid","not null");
	    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						kunde=kundenManager.get(new Long(pageparameters.get("kundennr").toString()));
				setNewKunde(true);
				model.add(new KundeStep());	
			}
	   pars1=new PageParameters()
    	.add("scoutid","not null")
    		.add("angnr","not null")
    	.add("objid","not null");
    if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
    	scout=scoutManager.get(new Long(pageparameters.get("scoutid").toString()));
		setNewScout(true);
		model.add(new ScoutStep());
		}
		
    pars1=new PageParameters()
	.add("nachweisid","not null")
		.add("angnr","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	nachweis=nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));
	setNewNachweis(true);
	model.add(new NachweisStep());
	}	
pars1=new PageParameters()
		.add("angnr","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	angebot=angebotManager.get(pageparameters.get("angnr").toString());
	setNewAngebot(true);
	model.add(new AngebotStep());
	}		
pars1=new PageParameters()
	.add("angnr","not null")
.add("objid","null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
model.add(new SucheStep());
model.add(new LandStep());
model.add(new OrtStep());
model.add(new StrassenStep());
model.add(new ObjektStep());
}
pars1=new PageParameters()
.add("angnr","not null")
.add("eigtid","null")
.add("objid","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
model.add(new SucheStep());
model.add(new LandStep());
model.add(new OrtStep());
model.add(new StrassenStep());
model.add(new PersonStep());
}
pars1=new PageParameters()
.add("angnr","not null")
.add("objid","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
objekt=objektManager.get(new Long(pageparameters.get("objid").toString()));
model.add(new ObjektStep());	
model.add(new SucheStep());
model.add(new LandStep());
model.add(new OrtStep());
model.add(new StrassenStep());
model.add(new ObjektStep());
setNewland(false);
setNewort(false);
setNewstrasse(false);
}	
		
pars1=new PageParameters()
.add("angnr","not null")
.add("eigtid","not null")
.add("objid","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
person=personManager.get(new Long(pageparameters.get("eigtid").toString()));
model.add(new PersonStep());	
model.add(new SucheStep());
model.add(new LandStep());
model.add(new OrtStep());
model.add(new StrassenStep());
model.add(new PersonStep());
setNewland(false);
setNewort(false);
setNewstrasse(false);
}
		}
	
		model.setLastVisible(true);
		init(model);
		
		
	}
	public NewLandWizard(String id)
	{
		super(id);
this.responsepage=Index.class;
this.pageparameters=new PageParameters();
		
		setDefaultModel(new CompoundPropertyModel<NewLandWizard>(this));
		WizardModel model = new WizardModel();
		if(responsepage.getSimpleName().equals("AngebotWizardTree")){
		
			
			if(pageparameters.getPosition("angnr")>-1&&pageparameters.getPosition("objid")>-1&&pageparameters.get("objid").toString().equals("0")&&pageparameters.getPosition("eigtid")<0){
				
		model.add(new SucheStep());
		model.add(new LandStep());
		model.add(new OrtStep());
		model.add(new StrassenStep());
		model.add(new ObjektStep());}
			if(pageparameters.getPosition("angnr")>-1&&pageparameters.getPosition("objid")>-1&&pageparameters.getPosition("eigtid")>-1&&pageparameters.get("eigtid").toString().equals("0")){
				
				model.add(new SucheStep());
				model.add(new LandStep());
				model.add(new OrtStep());
				model.add(new StrassenStep());
				model.add(new PersonStep());}	
			
				if(pageparameters.getPosition("angnr")>-1&&pageparameters.getPosition("objid")>-1&&!pageparameters.get("objid").toString().equals("0")&&pageparameters.getPosition("eigtid")<0){
					model.add(new ObjektStep());		
			model.add(new SucheStep());
			model.add(new LandStep());
			model.add(new OrtStep());
			model.add(new StrassenStep());
		}
				if(pageparameters.getPosition("angnr")>-1&&pageparameters.getPosition("objid")>-1&&pageparameters.getPosition("eigtid")>-1&&!pageparameters.get("eigtid").toString().equals("0")){
					model.add(new PersonStep());
					model.add(new SucheStep());
					model.add(new LandStep());
					model.add(new OrtStep());
					model.add(new StrassenStep());
					setNewland(false);}	
				
					//	
		// initialize the wizard with the wizard model we just built
		model.setLastVisible(true);
		init(model);
		}
	}
	public Objekte getObjekt() {
		return objekt;
	}
	public void setObjekt(Objekte objekt) {
		this.objekt = objekt;
	}
	public Eigentuemertyp getEigentuemertyp() {
		return eigentuemertyp;
	}
	public void setEigentuemertyp(Eigentuemertyp eigentuemertyp) {
		this.eigentuemertyp = eigentuemertyp;
	}
	public boolean isNewLocation() {
		return newLocation;
	}
	public void setNewLocation(boolean newLocation) {
		this.newLocation = newLocation;
	}
	public Land getLand()
	{
		return land;
	}
	public Orte getOrt()
	{
		return ort;
	}
	public Strassen getStrassen()
	{
		return strassen;
	}
	public Personen getPerson() {
		return person;
	}
	public void setPerson(Personen person) {
		this.person = person;
	}
	public boolean isNewland()
	{
		return newland;
	}
	
	public boolean isNewort()
	{
		return newort;
	}
	public boolean isNewperson()
	{
		return newperson;
	}
	
	public boolean isNewobjekt()
	{
		return newobjekt;
	}
	public boolean isNewstrasse()
	{
		return newstrasse;
	}

	/**
	 * @see org.apache.wicket.extensions.wizard.Wizard#onCancel()
	 */
	@Override
	public void onCancel()
	{
		setResponsePage(responsepage,pageparameters);
	}

	/**
	 * @see org.apache.wicket.extensions.wizard.Wizard#onFinish()
	 */
	@Override
	public void onFinish()
	{
		
			try{
			if(responsepage.getSimpleName().equals("AngebotWizardTree")){
				PageParameters	pars1=new PageParameters()
				.add("angnr","not null");
		if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			angebotManager.save(angebot);
			}		
		pars1=new PageParameters()
		.add("objid","null")
		.add("angnr","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			
				if(objekt!=null){
					if(objekt.getId()!=null&&objekt.getId()!=0){
						
						
					}
					else{
						if(land.getId()==null||land.getId()==0){
							landManager.save(land);
						}
						else land=landManager.get(land.getId());
						if(ort.getId()==null||ort.getId()==0){
							land.addOrt(ort);
							ort.setLand(land);
							orteManager.save(ort);
						}
						else ort=orteManager.get(ort.getId());
						if(strassen.getId()==null||strassen.getId()==0){
							ort.addStrassen(strassen);
							strassen.setOrt(ort);
							strassenManager.save(strassen);
						}
						else strassen=strassenManager.get(strassen.getId());
						objekt.setStrasse(strassen);
						strassen.addObjekt(objekt);
						}
					objektManager.save(objekt);
						Angobjzuord angobjzuord=new Angobjzuord();
						Angebot angebot=angebotManager.get(pageparameters.get("angnr").toString());
						angobjzuord.setAngebot(angebot);
						angobjzuord.setObjekte(objekt);
						angebot.addAngobjzuord(angobjzuord);
						objekt.addAngobjzuord(angobjzuord);
						angobjzuordManager.save(angobjzuord);
					
				}
}

pars1=new PageParameters()
.add("eigtid","null")
.add("objid","not null")
.add("angnr","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	
				if(person!=null&&eigentuemertyp!=null){
if(person.getId()!=null&&person.getId()!=0){
						
						
					}
					else{
						if(land.getId()==null||land.getId()==0){
							landManager.save(land);
						}
						else land=landManager.get(land.getId());
						if(ort.getId()==null||ort.getId()==0){
							land.addOrt(ort);
							ort.setLand(land);
							orteManager.save(ort);
						}
						else ort=orteManager.get(ort.getId());
						if(strassen.getId()==null||strassen.getId()==0){
							ort.addStrassen(strassen);
							strassen.setOrt(ort);
							strassenManager.save(strassen);
						}
						else strassen=strassenManager.get(strassen.getId());
						person.setStrasse(strassen);
						strassen.addPerson(person);
						}
					personManager.save(person);
					Objekte objekt1=objektManager.get(new Long(pageparameters.get("objid").toString()));
					  
							 Eigentuemertyp eigt=eigentuemertypManager.get(eigentuemertyp.getId());
							 
						 Objperszuord objperszuord=new Objperszuord();
						 objperszuord.setObjekt(objekt1);
								 objperszuord.setPersonen(person);
								 person.addObjperszuord(objperszuord);
								 objekt1.addObjperszuord(objperszuord);
								 objperszuord.setEigentuemertyp(eigt);
							personManager.save(person); 
			
				}
}

pars1=new PageParameters()
.add("eigtid","not null")
.add("objid","not null")
.add("angnr","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				if(person!=null){
					
												personManager.save(person); 
								
									}
}
pars1=new PageParameters()
.add("objid","not null")
.add("angnr","not null");
if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
	
				
					objektManager.save(objekt);
				}	
			}
			
			}
			catch(Exception ex){
				ex.printStackTrace();
System.exit(5);
}
			
		
		setResponsePage(responsepage,pageparameters);
	}

	/**
	 * Sets assignRoles.
	 * 
	 * @param assignRoles
	 *            assignRoles
	 */
	public boolean isNewKunde() {
		return newKunde;
	}
	public void setNewKunde(boolean newKunde) {
		this.newKunde = newKunde;
	}
	
	public boolean isNewAngebot() {
		return newAngebot;
	}
	public void setNewAngebot(boolean newAngebot) {
		this.newAngebot = newAngebot;
	}
	public boolean isNewScout() {
		return newScout;
	}
	public void setNewScout(boolean newScout) {
		this.newScout = newScout;
	}
	public boolean isNewNachweis() {
		return newNachweis;
	}
	public void setNewNachweis(boolean newNachweis) {
		this.newNachweis = newNachweis;
	}
	public void setNewort(boolean newort)
	{
		this.newort = newort;
	}

	
	public void setNewland(boolean newland)
	{
		this.newland = newland;
	}
	
	public void setNewstrasse(boolean newstrasse)
	{
		this.newstrasse = newstrasse;
	}
	public void setNewobjekt(boolean newobjekt)
	{
		this.newobjekt = newobjekt;
	}
	
	public void setNewperson(boolean newperson)
	{
		this.newperson = newperson;
	}
	/**
	 * Sets land.
	 * 
	 * @param land
	 *            land
	 */
	public void setLand(Land land)
	{
		this.land = land;
	}
	public void setOrt(Orte ort)
	{
		this.ort = ort;
	}
	public void setStrassen(Strassen strassen)
	{
		this.strassen = strassen;
	}
	@Override
	public Component newButtonBar(final String id)
	{
		return new WizardButtonBar(id, this);
	}
    private static Event createSingleEvent(Calendar service,
		      String eventTitle, String eventContent,java.util.Date date) throws ServiceException,
		      IOException {
      	Event myEvent = new Event();
	        myEvent.setSummary(eventTitle);
	        myEvent.setDescription(eventContent);  
	        java.util.Calendar cal = java.util.Calendar.getInstance();
	        cal.setTime(date);
	        
	    	   com.google.api.client.util.DateTime newstartdate = new  com.google.api.client.util.DateTime(cal.getTime(), TimeZone
   		          .getDefault());
   	   EventDateTime eventStartDateTime=new EventDateTime();
	      eventStartDateTime.setDateTime(newstartdate);
   	   myEvent.setStart(eventStartDateTime);
   	   cal.add(java.util.Calendar.MINUTE,Integer.parseInt("30"));	    	    		     
		     com.google.api.client.util.DateTime endTime=new com.google.api.client.util.DateTime(cal.getTime(), TimeZone/**
* Construct.
*/
 		          .getDefault()); 
		      EventDateTime eventEndDateTime=new EventDateTime();
		        eventEndDateTime.setDateTime(endTime);
		      myEvent.setEnd(eventEndDateTime);
		      Event createdEvent = service.events().insert("primary", myEvent).execute();
      	
      	return createdEvent;
      	
      	
		   
		  }
}
