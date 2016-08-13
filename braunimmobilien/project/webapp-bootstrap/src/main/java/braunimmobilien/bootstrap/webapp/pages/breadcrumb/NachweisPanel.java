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
package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanelLink;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant;

import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import braunimmobilien.model.Emailbrief;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Mitarbeiter;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Land;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Xtyp;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.XtypManager;
import braunimmobilien.service.MitarbeiterManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test bread crumb enabled panel.
 * 
 * @author Eelco Hillenius
 */
public class NachweisPanel extends BreadCrumbPanel
{
static Logger logger = LoggerFactory.getLogger(NachweisPanel.class);
	String subject = "no subject";
	String result = "no result";
	private String specialusage="";
	@SpringBean
	NachweiseManager nachweiseManager;
	@SpringBean
	StrassenManager strassenManager;
@SpringBean
OrteManager orteManager;
@SpringBean
XtypManager xtypManager;
@SpringBean
MitarbeiterManager mitarbeiterManager;
@SpringBean
PersonManager personManager;
@SpringBean
ObjektManager objektManager;
	@SpringBean
	AngebotManager angebotManager;
	@SpringBean
	AngobjzuordManager angobjzuordManager;
	@SpringBean
	ObjektsuchManager objektsuchManager;
@SpringBean
	ObjektartManager objektartManager;

@SpringBean
KundeManager kundeManager;
	private final class NachweisInput extends Form<Nachweise>
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
	
	
	
	
	 public Label getLabel() {
		return label;
	}


	private final Link callPdfAnhangEmailNachweise=	new Link("callPdfAnhangEmailNachweise") {
		    public void onClick() {
		    	
		    	
		    	// getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("http://"+((WebRequest) getRequest()). getClientUrl().getHost()+":8088/cocoon-kbr/anzeigen/pdfanhangemail/"+((Nachweise)NachweiseForm.this.getDefaultModelObject()).getId().toString()+"/nachweise.flow"));
		    }
		    };
		public Link getCallPdfAnhangEmailNachweise() {
		return callPdfAnhangEmailNachweise;
	}
	
		
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
	    IChoiceRenderer<Personen> personenchoicerenderer=        		new IChoiceRenderer<Personen>() {

      	  public Object getDisplayValue(Personen person)
	    	    {
	    	        return person.getEigtName();
	    	    }

	    	    public String getIdValue(Personen object,int index)
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
 	 	  	        
 	 	  	        
 	
 	 	  	        
 	 	  	        
 	 	  	 
 	
 	 	  	   
 	 	  	  
 	 	  	    final BootstrapSelect<Angebot> angebote = new BootstrapSelect<Angebot>("angebot",
 	 		     		 makeChoicesAngebote,angebotchoicerenderer);	  
 	 	  	final BootstrapSelect<Angebot> angebote1 = new BootstrapSelect<Angebot>("angebot1",
	 		     		 makeChoicesAngebote,angebotchoicerenderer);	
 	 	   final BootstrapSelect<Angebot> angebote2 = new BootstrapSelect<Angebot>("angebot2",
		     		 makeChoicesAngebote,angebotchoicerenderer);	
 	
 	   final BootstrapSelect<Mitarbeiter> nachmitarbnr = new BootstrapSelect<Mitarbeiter>("mitarbeiter",
	     		 makeChoicesMitarbeiter,mitarbeiterchoicerenderer);	 
 	  final BootstrapSelect<Xtyp> nachxnr = new BootstrapSelect<Xtyp>("xtyp",
	     		 makeChoicesXtyp,xtypchoicerenderer);	 
 	/* 	 final DropDownChoice<Xtyp> nachxnr = new DropDownChoice<Xtyp>("xtyp.id",
 		 makeChoicesXtyp);*/	  		
 	
 	 private    final Link callCocoonPdfNachweise=	    new Link("callCocoonPdfNachweise"){   public void onClick() {
			
		 
  		 
     	 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("http://"+((WebRequest) getRequest()). getClientUrl().getHost()+":8088/controllerbraunimmobilienget?id=xbrief&reqparam=3&name="+((Nachweise)NachweisInput.this.getDefaultModelObject()).getId().toString()));
	    }
	    };
	
	    public Link getCallCocoonPdfNachweise() {
		return callCocoonPdfNachweise;
	}


		PopupSettings googlePopupSettings = new PopupSettings(PopupSettings.RESIZABLE |
                PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
		
	final	 AjaxFallbackLink restLink=new AjaxFallbackLink("rest") {
		     public void onClick(AjaxRequestTarget target) {
		         if (target != null) {
		        	  String wholeline = "";
		        	 try{HttpClient client = new DefaultHttpClient();
			    	  HttpPost post = new HttpPost("http://localhost:8088/rest/SendMailPipeService");
			    	  List nameValuePairs = new ArrayList(1);
			    	  nameValuePairs.add(new BasicNameValuePair("name", "int")); //you can as many name value pair as you want in the list.
			    	  nameValuePairs.add(new BasicNameValuePair("nachweisnr", ((Nachweise)NachweisInput.this.getDefaultModelObject()).getId().toString()));
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
		public AjaxFallbackLink getRestLink() {
		return restLink;
	}
		public NachweisInput(String id,final Class responsepage,final PageParameters pageparameters,final IModel<Nachweise> nachweise)
		{
			
			super(id, new CompoundPropertyModel(nachweise));
		 add(new Label("id"));
		 nachmitarbnr.setRequired(true);
			nachxnr.setRequired(true);
			 callCocoonPdfNachweise.setOutputMarkupId(true);
		     add(callCocoonPdfNachweise);
		     callCocoonPdfNachweise.setVisible(false);
		     callCocoonPdfNachweise.setPopupSettings(googlePopupSettings);
		    
			add(angebote);

			add(angebote1);

			add(angebote2);

			add(nachmitarbnr);

			add(nachxnr);
			DateLabel nachdatum=new DateLabel("nachdatum", new StyleDateConverter("L-", true)){
			    @Override
			    public Locale getLocale()
			    {
			        return getSession().getLocale();
			    }
			};
			add(nachdatum); 
			Label nachkundnr=new Label("kunde.id");
			 add(nachkundnr);
			 TextField<Double> nachgedruckt= new TextField<Double>("nachgedruckt");
			 add(nachgedruckt);
			TextField<String> anlage1= new TextField<String>("anlage1");
			 add(anlage1);
			 TextField<String> anlage2= new TextField<String>("anlage2");
			 add(anlage2);
			 TextField<String> anlage3= new TextField<String>("anlage3");
			 add(anlage3);
			 TextField<String> anlage4= new TextField<String>("anlage4");
			 add(anlage4);
			 TextField<String> unterlagen1= new TextField<String>("unterlagen1");
			 add(unterlagen1);
			 TextArea<String> unterlagen2 = new TextArea<String>("unterlagen2");		
			 add(unterlagen2);
			 TextField<String> unterlagen3= new TextField<String>("unterlagen3");
			 add(unterlagen3);
			 TextField<String> unterlagen4= new TextField<String>("unterlagen4");
			 add(unterlagen4);
			 TextField<Double> steuerfeld= new TextField<Double>("steuerfeld");
			 add(steuerfeld);
			
			 add(restLink);
			 restLink.setVisible(false);

			 label.setOutputMarkupId(true);
			 restLink.setOutputMarkupId(true);
			 label.setVisible(false);

			 add(label);
			 final	IModel<List<? extends Objekte>> makeChoicesObjekt = new AbstractReadOnlyModel<List<? extends Objekte>>()
	 	 	  	        {
	 	 	  	            @Override
	 	 	  	            public List<Objekte> getObject()
	 	 	  	            { List<Objekte> objektlist=new  ArrayList<Objekte>(); 
	 	 	  	        Iterator objekteiterator=null;
	 	 	  	        Personen person=nachweise.getObject().getPerson();
	 	 	  	            	if ((person!=null)&&(person.getId().longValue()>0)){
	 	 	  	            	objekteiterator=person.getObjperszuords().iterator();
	 	 	  	            	while(objekteiterator.hasNext()){
	 	 	  	            	Objperszuord persobjzuord=(Objperszuord)objekteiterator.next();
	 	 	  	            	objektlist.add(persobjzuord.getObjekt());
	 	 	  	            }
	 	 	  	            }
	 	 	  	            	Objekte objekt=nachweise.getObject().getObjekt();
	 	 	  	            if ((objekt!=null)&&(objekt.getId().longValue()>0)){
	 	 	  	           
	 	 	  	            objektlist.add(objekt);}
	 	 	  	                return objektlist;
	 	 	  	            	
	 	 	  	            }

	 	 	  	        };
	 	 	  	    final BootstrapSelect<Objekte> nachobjnr = new BootstrapSelect<Objekte>("objekt",
	 	 		     		 makeChoicesObjekt,objektechoicerenderer);	  	
	 	 	 	     
			 add(nachobjnr);
			 final	IModel<List<? extends Personen>> makeChoicesPerson = new AbstractReadOnlyModel<List<? extends Personen>>()
	  	        {
	  	            @Override
	  	            public List<Personen> getObject()
	  	            { List<Personen> personenlist=new  ArrayList<Personen>(); 
	  	        Iterator personeniterator=null;
	  	        Personen person=nachweise.getObject().getPerson();
	  	            	if ((person!=null)&&(person.getId().longValue()>0)){
	  	            	  personenlist.add(person);
	  	            	
	  	            }
	  	            	Objekte objekt=nachweise.getObject().getObjekt();
	  	            if ((objekt!=null)&&(objekt.getId().longValue()>0)){
	  	            	personeniterator=objekt.getObjperszuords().iterator();
	  	            	while(personeniterator.hasNext()){
	  	            	Objperszuord persobjzuord=(Objperszuord)personeniterator.next();
	  	            	personenlist.add(persobjzuord.getPersonen());
	  	            }
	  	          }
	  	                return personenlist;
	  	            	
	  	            }

	  	        };
	  	    final BootstrapSelect<Personen> nacheigtnr = new BootstrapSelect<Personen>("person",
		     		 makeChoicesPerson,personenchoicerenderer);	  	
	 	     
	 add(nachobjnr);
	 add(nacheigtnr);
			 TextField<String> nachdoku= new TextField<String>("nachdoku");
			 add(nachdoku);
			 label.setOutputMarkupId(true);
			final CheckBox email=new CheckBox("email");
			add(email);
			 add(new Button("backButton")
			{
			 @Override
				public void onSubmit()
				{  
		
				 try{
					
						   Nachweise nachweise=NachweisInput.this.getModelObject();
						 
								 if(nachweise.getId()==null){ 
				if(nachweise.getAngebot()!=null) {
			nachweise.getAngebot().addNachweis(nachweise);
		}
				if(nachweise.getAngebot1()!=null) {
					nachweise.getAngebot1().addNachweis(nachweise);
				}
				if(nachweise.getAngebot2()!=null) {
					nachweise.getAngebot2().addNachweis(nachweise);
				}
			if(nachweise.getKunde()!=null) {
			nachweise.getKunde().addNachweis(nachweise);
		}	
			if(nachweise.getPerson()!=null) {
				nachweise.getPerson().addNachweis(nachweise);
			}	
			if(nachweise.getObjekt()!=null) {
				nachweise.getObjekt().addNachweise(nachweise);
			}	
			nachweiseManager.save(nachweise);
		pageparameters.remove("nachweisid");
							 pageparameters.add("nachweisid", nachweise.getId().toString());
								 }
								 else{ 
												nachweiseManager.save(nachweise);
								 }
								 
				 
			
			
				 }
					catch(Exception ex){

	 pageparameters.add("error", ex);
	
	 }
				 setResponsePage(responsepage, pageparameters);	
				}
			});
			 
			 
		 add(new Button("nextButton")
			{
			 @Override
				public void onSubmit()
				{ 
				 
				 activate(new IBreadCrumbPanelFactory()
					{
						@Override
						public BreadCrumbPanel create(String componentId,
							IBreadCrumbModel breadCrumbModel)
						{ String error="";
						Nachweise nachweise =NachweisInput.this.getModelObject();
					 try{
						
						
							 
						 if(nachweise.getId()==null){ 
								if(nachweise.getAngebot()!=null) {
							nachweise.getAngebot().addNachweis(nachweise);
						}
								if(nachweise.getAngebot1()!=null) {
									nachweise.getAngebot1().addNachweis(nachweise);
								}
								if(nachweise.getAngebot2()!=null) {
									nachweise.getAngebot2().addNachweis(nachweise);
								}
							if(nachweise.getKunde()!=null) {
							nachweise.getKunde().addNachweis(nachweise);
						}	
							if(nachweise.getPerson()!=null) {
								nachweise.getPerson().addNachweis(nachweise);
							}	
							if(nachweise.getObjekt()!=null) {
								nachweise.getObjekt().addNachweise(nachweise);
								nachweiseManager.save(nachweise);
								pageparameters.remove("nachweisid");
								 pageparameters.add("nachweisid", nachweise.getId().toString());
							}				
										
									 }
									 else{ 
													nachweiseManager.save(nachweise);
									 }
									 
					 }
									catch(Exception ex){
logger.error("NachweisPanel "+ex,ex);
					 pageparameters.add("error", ex);
					 return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
					 }
					 return new NachweisPanel(componentId,responsepage,pageparameters, breadCrumbModel);
					}
				});
				 
				 
				}
			});
		 add(new Button("cancelButton")
			{
				@Override
				public void onSubmit()
				{
					
					setResponsePage(responsepage, pageparameters);
				}
			}.setDefaultFormProcessing(false));   
		 email.add(new AjaxFormComponentUpdatingBehavior("onchange")
	        {
	            @Override
	            protected void onUpdate(AjaxRequestTarget target)
	            {
	       
	            	 
	            	 callCocoonPdfNachweise.setVisible(false); 
            		  restLink.setVisible(false);
            		  label.setVisible(false);
	        
	           target.add(callCocoonPdfNachweise);
	           target.add(restLink);
	           target.add(label);
	            }
	        });
	 

		}
	
	}
	
	
	
	
	
		
	/**
	 * Construct.
	 * 
	 * @param id
	 * @param breadCrumbModel
	 * @param result
	 *            The 'result' to display as a label
	 */
	public NachweisPanel(final String id, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);

		String result = "no result";
		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
		add(new Label("result", result));
	}
	
	
	
	
	
	public NachweisPanel(final String id, final IBreadCrumbModel breadCrumbModel, String result,boolean withNext)
	{
		super(id, breadCrumbModel);

		if (result == null || "".equals(result.trim())) 
		{
			result = "(hey, you didn't even provide some input!)";
		}
		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
		add(new Label("result", result));
	
	}
	public NachweisPanel(final String id, final Class responsepage,final PageParameters pageparameters,final IBreadCrumbModel breadCrumbModel, Strassen strasse,boolean withNext)
	{
		super(id, breadCrumbModel);
		
		
		String result="";
		if (strasse == null )
		{
			result = "(hey, you didn't even provide some input!)";
		}
		else{
result="";
		}
		add(new Label("result", result));
		add(new BreadCrumbPanelLink("linkToFirst", this, StrassenSuchePanel.class));
	}
	public NachweisPanel(final String id,final Class responsepage,final PageParameters pageparameters, final IBreadCrumbModel breadCrumbModel)
	{
		super(id, breadCrumbModel);
		String result=(new StringResourceModel("objekt.undefined",this,null)).getObject();
		
			result="null";
		if(breadCrumbModel.allBreadCrumbParticipants().size()>0)
		{breadCrumbModel.setActive(breadCrumbModel.allBreadCrumbParticipants().get(0));
		IBreadCrumbParticipant removed = breadCrumbModel.allBreadCrumbParticipants().remove(0);
		}
		 if(pageparameters.getPosition("error")>0) {error(pageparameters.get("error").toString());
		 pageparameters.remove("error");}
		NachweisInput nachweiseform=null;
	logger.error("NachweisPanel PageParameters "+pageparameters);
		 Nachweise nachweis=null;
		 if(responsepage.getSimpleName().equals("KundeSuch")){
		 PageParameters pars1=new PageParameters()
				 .add("kundennr","not null")
		 		.add("usage","not null");
			if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				result=pageparameters.get("kundennr").toString()+"/null";
				  nachweis = new Nachweise();
				  Kunde kunde=kundeManager.get(new Long(pageparameters.get("kundennr").toString()));
					nachweis.setKunde(kunde);
					 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(nachweis));
			}
			 pars1=new PageParameters()
					 .add("kundennr","not null")
					 .add("nachweisid","not null")
			 		.add("usage","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					result=pageparameters.get("kundennr").toString()+"/"+pageparameters.get("nachweisid").toString();
					  nachweis = nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));	
					  nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
				}
		 }
		 if(responsepage.getSimpleName().equals("KundeTree")){
			
			 PageParameters pars1=new PageParameters()
					 .add("kundennr","not null")
			 		.add("nachweisid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
				nachweis = nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));	
					result=pageparameters.get("kundennr").toString()+"/"+pageparameters.get("nachweisid").toString();
					 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
				}
				 PageParameters pars2=new PageParameters()
					 .add("kundennr","not null")
					  .add("eigtid","not null")
			 		.add("nachweisid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars2,true)) {
				nachweis = nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));	
					result=pageparameters.get("kundennr").toString()+"/"+pageparameters.get("nachweisid").toString();
					 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
				}
			 }
		 if(responsepage.getSimpleName().equals("AngebotTree")){
			 PageParameters pars1=new PageParameters()
					 .add("angnr","not null")
			 		.add("nachweisid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
			    	specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("nachweisid",this,null)).getObject();
					 subject=(new StringResourceModel("nachweisold",this,null)).getObject();
		
					result=pageparameters.get("angnr").toString()+"/"+pageparameters.get("nachweisid").toString();
					 nachweis = nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));	
					 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
					    }
					    pars1=new PageParameters()
					    .add("angnr","not null")
					 .add("eigtid","not null")
					 .add("nachweisid","null")
					 .add("kundennr","not null")
			 		.add("objid","not null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject()+(new StringResourceModel("eigtid",this,null)).getObject()+(new StringResourceModel("kundenid",this,null)).getObject()+(new StringResourceModel("nachweisid",this,null)).getObject();
					 subject=(new StringResourceModel("nachweisnew",this,null)).getObject();
		
					result=pageparameters.get("angnr").toString()+"/"+pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/"+pageparameters.get("kundennr").toString()+"/null";
			
					 nachweis = new Nachweise();
					 pageparameters.remove("nachweisid");
					 nachweis.setKunde(kundeManager.get(new Long(pageparameters.get("kundennr").toString())));	
					 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(nachweis));
					    }
				   pars1=new PageParameters()
						    .add("angnr","not null")
						 .add("eigtid","not null")
						 .add("nachweisid","not null")
						 .add("kundennr","not null")
				 		.add("objid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						specialusage= (new StringResourceModel("exposeeid",this,null)).getObject()+"/"+(new StringResourceModel("objektid",this,null)).getObject()+(new StringResourceModel("eigtid",this,null)).getObject()+(new StringResourceModel("kundenid",this,null)).getObject()+(new StringResourceModel("nachweisid",this,null)).getObject();
						 subject=(new StringResourceModel("nachweisold",this,null)).getObject();
			
						result=pageparameters.get("angnr").toString()+"/"+pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/"+pageparameters.get("kundennr").toString()+"/"+pageparameters.get("nachweisid").toString();
				
						 nachweis = nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));	
						 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
						    }
			 }
			if(responsepage.getSimpleName().equals("ObjektTree")){
			 PageParameters pars1=new PageParameters()
					 .add("objid","not null")
			 		.add("nachweisid","null");
				if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
					specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("nachweisid",this,null)).getObject();
					 subject=(new StringResourceModel("nachweisnew",this,null)).getObject();
		
					result=pageparameters.get("objid").toString()+"/"+pageparameters.get("nachweisid").toString();
					 nachweis = new Nachweise();	
					 nachweis.setObjekt(objektManager.get(new Long(pageparameters.get("objid").toString())));
					 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(nachweis));
					    }
					pars1=new PageParameters()
						 .add("objid","not null")
				 		.add("nachweisid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						specialusage= (new StringResourceModel("objektid",this,null)).getObject()+"/"+(new StringResourceModel("nachweisid",this,null)).getObject();
						 subject=(new StringResourceModel("nachweisold",this,null)).getObject();
			
						result=pageparameters.get("objid").toString()+"/"+pageparameters.get("nachweisid").toString();
						nachweis = nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));	
						 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
				    }
						    pars1=new PageParameters()
						 .add("eigtid","not null")
						 .add("nachweisid","null")
						 .add("kundennr","not null")
				 		.add("objid","not null");
					if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
						specialusage= (new StringResourceModel("objektid",this,null)).getObject()+(new StringResourceModel("eigtid",this,null)).getObject()+(new StringResourceModel("kundenid",this,null)).getObject()+(new StringResourceModel("nachweisid",this,null)).getObject();
						 subject=(new StringResourceModel("nachweisnew",this,null)).getObject();
			
						result=pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/"+pageparameters.get("kundennr").toString()+"/null";
				
						 nachweis = new Nachweise();
						 pageparameters.remove("nachweisid");
						 nachweis.setKunde(kundeManager.get(new Long(pageparameters.get("kundennr").toString())));	
						 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(nachweis));
						    }
					   pars1=new PageParameters()
							 .add("eigtid","not null")
							 .add("nachweisid","not null")
							 .add("kundennr","not null")
					 		.add("objid","not null");
						if	(MaklerFlowUtility.fits(pageparameters,pars1,true)) {
							specialusage= (new StringResourceModel("objektid",this,null)).getObject()+(new StringResourceModel("eigtid",this,null)).getObject()+(new StringResourceModel("kundenid",this,null)).getObject()+(new StringResourceModel("nachweisid",this,null)).getObject();
							 subject=(new StringResourceModel("nachweisold",this,null)).getObject();
				
							result=pageparameters.get("objid").toString()+"/"+pageparameters.get("eigtid").toString()+"/"+pageparameters.get("kundennr").toString()+"/"+pageparameters.get("nachweisid").toString();
					
							 nachweis = nachweiseManager.get(new Long(pageparameters.get("nachweisid").toString()));	
							 nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
							    }
				
				
				
			 } 
			 nachweiseform.add(new Label("search", subject));
		//	IModel<Nachweise> model=new EntityModel<Nachweise>(nachweis);
		add(new Label("result", result));
	//	NachweisInput nachweiseform=new NachweisInput("form",responsepage,pageparameters,new EntityModel<Nachweise>(Nachweise.class,new Long(pageparameters.get("nachweisid").toString())));
		add(nachweiseform);
		 if(nachweis.getEmail().booleanValue()==false) nachweiseform.getCallCocoonPdfNachweise().setVisible(true);
		 if((nachweis.getEmail().booleanValue()==true)) nachweiseform.getRestLink().setVisible(true);
		 if(nachweis.getEmail().booleanValue()==true) nachweiseform.getLabel().setVisible(true);
	
	}
	
	

	
	/**
	 * @see org.apache.wicket.extensions.breadcrumb.IBreadCrumbParticipant#getTitle()
	 */
	@Override
	public IModel<String> getTitle(){
	
		return Model.of(specialusage);
	}
}
