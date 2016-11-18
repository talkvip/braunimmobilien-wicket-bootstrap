package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.model.CompoundPropertyModel;
import braunimmobilien.bootstrap.webapp.EntityModel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.model.StringResourceModel;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapAjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import braunimmobilien.model.Land;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Kunde;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.service.PersonManager;
import org.apache.wicket.markup.ComponentTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class StrassenSucheForm extends  Form{
	@SpringBean
    private  PersonManager personManager;
	@SpringBean
    private  EigentuemertypManager eigentuemertypManager;
	@SpringBean
    private  LandManager landManager;
	String selectedTextSearch;
	private String customCSS = null;
	 private boolean customEnabled = true;
	
	  static Logger logger = LoggerFactory.getLogger(StrassenSucheForm.class);
	
	private String selectedOrtstring="";
	 private String selectedStrassenstring="";
	 
	 /*
	  * 
	  *                                                  EIGENTUEMERTYP
	  * 
	  */
	 
		IModel<List<? extends Eigentuemertyp>>makeChoicesEigentuemertyp= new AbstractReadOnlyModel<List<? extends Eigentuemertyp>>()
        {
            @Override
            public List<Eigentuemertyp> getObject()
            { List<Eigentuemertyp> eigentuemertyplist=new  ArrayList<Eigentuemertyp>(); 
            	if(eigentuemertypManager!=null){
            	Iterator eigentuemertypiterator=eigentuemertypManager.getEigentuemertypes().iterator();
            while(eigentuemertypiterator.hasNext()){
            Eigentuemertyp eigentuemertyp=(Eigentuemertyp) eigentuemertypiterator.next();
            	eigentuemertyplist.add(eigentuemertyp);
            }
            	}
                return eigentuemertyplist;
            }

        }; 
      
        final DropDownChoice<Eigentuemertyp> eigentuemertyp = new DropDownChoice<Eigentuemertyp>("eigentuemertyp",
         		 makeChoicesEigentuemertyp,new ChoiceRenderer<Eigentuemertyp>("typenbeschreibung","id"));   


        final	 WebMarkupContainer eigentuemertypmarkup = new WebMarkupContainer("eigentuemertypmarkup");
	 
	 /*                                              
	
/*
 * 
 *                                                   TEXTSEARCH
 * 
 */
	 TextField<String> searchTextField= new TextField<String>("textsearch");
	 
	 final	 WebMarkupContainer textsearchmarkup = new WebMarkupContainer("textsearchmarkup");
	
/*
	 * 
	  *                                            LAND
	  * 
	  */
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
   
	 
	 
	 final DropDownChoice<Land> land = new DropDownChoice<Land>("land",
    		 makeChoicesLand,new ChoiceRenderer<Land>("landname","id"));	
	 
     final	 WebMarkupContainer landmarkup = new WebMarkupContainer("landmarkup");
	 
	 /*
	  * 
	  * 
	  *                                                        ORT
	  * 
	  * 
	  * 
	  */
	 
	 
	 
	  final AutoCompleteTextField<String> ortefield = new AutoCompleteTextField<String>("ortefield",
              new PropertyModel<String>(this,"selectedOrtstring"))
          {
              @Override
              protected Iterator<String> getChoices(String input)
              {Land land=((Search)StrassenSucheForm.this.getDefaultModelObject()).getLand();
            
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
                  
                  
                  private   IModel<List<? extends Orte>> makeChoicesOrt = new AbstractReadOnlyModel<List<? extends Orte>>()
      	        {
	        	 @Override
  	            public List<Orte> getObject()
  	            { List<Orte> ortelist=new  ArrayList<Orte>(); 
  	          try{
  	        	  Land land=((Search)StrassenSucheForm.this.getDefaultModelObject()).getLand();
  	            if (land!=null){
	                  
	                 	
	                 	 
	                 
	                 	 Iterator orteiterator=land.getOrte().iterator();
		    	            	while(orteiterator.hasNext()){
		    	            	Orte orte=(Orte)orteiterator.next();
		    	            
		    	            	
		    	            	

        	                 	 Iterator strasseniterator=orte.getStrassen().iterator();
        		    	            	while(strasseniterator.hasNext()){
        		    	            	Strassen strassen=(Strassen)strasseniterator.next();
        		    	            	
        		    	            	 Iterator objekteiterator=strassen.getObjekte().iterator();
   	    	    	            	
  	    	    	    	           
  	    	    	            		while(objekteiterator.hasNext()){
  	    	    	            			Objekte objekte=(Objekte)objekteiterator.next();
  	    	    	            			
  	    	    	            } 
        		    	            } 
		    	            	
		    	            	
		    	            	if(Strings.isEmpty(selectedOrtstring)){ortelist.add(orte);}
		    	            	else{
		    	            		if(orte.getOrtname().startsWith(selectedOrtstring, 0)) ortelist.add(orte);
		    	            		}
		    	            } 
  	            	
  	            }
  	          }catch(Exception e){System.err.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+e);}
  	                return ortelist;
  	            }


      	        };    
      	        
      	      final DropDownChoice<Orte> orte = new DropDownChoice<Orte>("orte",
 	          		 makeChoicesOrt,new ChoiceRenderer<Orte>("ortname","id"));   
					          
      	    final	 WebMarkupContainer ortemarkup = new WebMarkupContainer("ortemarkup");          
                         
  /*
   * 
   *         
   *                                                        STRASSE
   *         
   *         
   */
                  final AutoCompleteTextField<String> strassenfield = new AutoCompleteTextField<String>("strassenfield",
                          new PropertyModel<String>(this,"selectedStrassenstring"))
                      {
                          @Override
                          protected Iterator<String> getChoices(String input)
                          {Orte ort=((Search)StrassenSucheForm.this.getDefaultModelObject()).getOrte();
                        	  if (ort==null)
        	                  {
        	                      List<String> emptyList = Collections.emptyList();
        	                      return emptyList.iterator();
        	                  }
                             	 
                              else{
                             	 List<String> strassenlist=new  ArrayList<String>(); 
                             	 
                          
                             	 Iterator strasseniterator=ort.getStrassen().iterator();
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
                  
                              private   IModel<List<? extends Strassen>> makeChoicesStrassen = new AbstractReadOnlyModel<List<? extends Strassen>>()
                  	        {
          	        	 @Override
              	            public List<Strassen> getObject()
              	            { List<Strassen> strassenlist=new  ArrayList<Strassen>(); 
              	            Orte ort=((Search)StrassenSucheForm.this.getDefaultModelObject()).getOrte();
              	            if (ort!=null){
            	                  
            	                 	
            	                 	 
            	                 
            	                 	 Iterator strasseniterator=ort.getStrassen().iterator();
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
                  	        
                  	      final DropDownChoice<Strassen> strassen = new DropDownChoice<Strassen>("strasse",
     	    	          		 makeChoicesStrassen,new ChoiceRenderer<Strassen>("strname","id"));      
                  	        
                  	    final	 WebMarkupContainer strassenmarkup = new WebMarkupContainer("strassenmarkup");
                        	    
/*
 * 
 *                              OBJEKTE
 *                              
 *                              
 */
                   
                   
                   
                   
                   
                            	            	    
    	                        	    
    	                            	        private   	        IModel<List<? extends Objekte>> makeChoicesObjekte = new AbstractReadOnlyModel<List<? extends Objekte>>()
    	            	    	    	    	        {
    	            	    	    	    	            @Override
    	            	    	    	    	            public List<Objekte> getObject()
    	            	    	    	    	            { List<Objekte> objektelist=new  ArrayList<Objekte>(); 
    	            	    	    	    	            Strassen strasse=((Search)StrassenSucheForm.this.getDefaultModelObject()).getStrasse();
    	            	    	    	    	            if(strasse!=null){
    	            	    	    	    	            	
    	            	    	    	    	            	 Iterator objekteiterator=strasse.getObjekte().iterator();
    	            	    	    	    	            	
    	            	    	    	    	           
    	            	    	    	    	            		while(objekteiterator.hasNext()){
    	            	    	    	    	            			Objekte objekte=(Objekte)objekteiterator.next();
    	            	    	    	    	            
    	            	    	    	    	            	objektelist.add(objekte); 
    	            	    	    	    	            
    	            	    	    	    	            } 
    	            	    	    	    	          }
    	            	    	    	    	        	
    	            	    	    	    	          return objektelist;
    	            	    	    	    	            }
    	            	    	    	    	        };
    	            	    	    	    	        
    	            	    	    	    	
    	            	    	    	    	        IChoiceRenderer<Objekte> objektechoicerenderer=        		new IChoiceRenderer<Objekte>() {

    	    	                            	    	    public Object getDisplayValue(Objekte objekte)
    	    	                            	    	    {
    	    	                            	    	    	  return objekte.getObjhausnummer()+","+objekte.getObjektart().getObjartname()+" id: "+objekte.getId();
    	    	                            	    	     
    	    	                            	    	    	
    	    	                            	    	    }

    	    	                            	    	    public String getIdValue(Objekte object,int index)
    	    	                            	    	    {
    	    	                            	    	        return object.getId().toString();
    	    	                            	    	    }
    	    	                            	    }; 	 
    	    	                            	    
    	    	                            	    
    	    	                            	    final	 WebMarkupContainer objektemarkup = new WebMarkupContainer("objektemarkup");
    	            	    	    	    	        
/* 
 * 
 * 
 *                                                             PERSON
 * 
 * 
 */
    	            	    	    	    	        
    	            	    	    	    	        
    	            	    	    	    	        
    	            	    	    	    	        
    	            	    	    	    	        
    	            	    	    	    	        private   	        IModel<List<? extends Personen>> makeChoicesPersonen = new AbstractReadOnlyModel<List<? extends Personen>>()
    	    	            	    	    	    	        {
    	    	            	    	    	    	            @Override
    	    	            	    	    	    	            public List<Personen> getObject()
    	    	            	    	    	    	            { List<Personen> objektelist=new  ArrayList<Personen>(); 
    	    	            	    	    	    	           personManager.reindex();
    	    	            	    	    	    	            String searchtext=((Search)StrassenSucheForm.this.getDefaultModelObject()).getTextsearch();
    	    	            	    	    	    	            if(searchtext!=null&&searchtext.length()>0){
    	    	        	    	    	    	    	            Iterator personeniterator=personManager.search(searchtext).iterator();
    	    	        	    	    	    	    	            	while(personeniterator.hasNext()){
    	    	        	    	    	    	    	            	Personen personen=(Personen)personeniterator.next();
    	    	        	    	    	    	    	            	objektelist.add(personen);
    	    	        	    	    	    	    	          }
    	    	        	    	    	    	    	          java.util.Collections.sort(objektelist);
    	    	        	    	    	    	    	          return objektelist; 
    	    	            	    	    	    	            }
    	    	            	    	    	    	            Strassen strasse=((Search)StrassenSucheForm.this.getDefaultModelObject()).getStrasse();
    	    	            	    	    	    	            if(strasse!=null){
    	    	            	    	    	    	            	
    	    	            	    	    	    	            	 Iterator personeniterator=strasse.getPersonen().iterator();
    	    	            	    	    	    	            	
    	    	            	    	    	    	           
    	    	            	    	    	    	            		while(personeniterator.hasNext()){
    	    	            	    	    	    	            			Personen person=(Personen)personeniterator.next();
    	    	            	    	    	    	            
    	    	            	    	    	    	            	objektelist.add(person); 
    	    	            	    	    	    	            
    	    	            	    	    	    	            } 
    	    	            	    	    	    	          }
    	    	            	    	    	    	            java.util.Collections.sort(objektelist);
    	    	            	    	    	    	          return objektelist;
    	    	            	    	    	    	            }
    	    	            	    	    	    	        }; 
    	    	            	    	    	    	        
    	    	            	    	    	    	        
    	        	    	                            	    
    	   	    	                            	    	 IChoiceRenderer<Personen> personenchoicerenderer=        		new IChoiceRenderer<Personen>() {

    	   	    	                            	        	    public Object getDisplayValue(Personen personen)
    	   	    	                            	        	    {   if(personen.getEigtFirma()!=null){
    	   	    	                            	        	    	return personen.getEigtHausnummer()+","+personen.getEigtFirma()+","+personen.getEigtName()+" id : "+personen.getId();}
    	   	    	                            	        	    else{return personen.getEigtHausnummer()+","+personen.getEigtName()+" id : "+personen.getId();}
    	   	    	                            	        	    	
    	   	    	                            	        	    }

    	   	    	                            	        	    public String getIdValue(Personen object,int index)
    	   	    	                            	        	    {
    	   	    	                            	        	        return object.getId().toString();
    	   	    	                            	        	    }
    	   	    	                            	        }; 
    	   	    	                            	        
    	   	    	                            	       final DropDownChoice<Personen> personen = new DropDownChoice<Personen>("person",
    	   	    	                            	    		   makeChoicesPersonen,personenchoicerenderer); 
    	   	    	                            	 
    	    	            	    	    	    	        
    	   	    	                            	    final	 WebMarkupContainer personenmarkup = new WebMarkupContainer("personenmarkup");    	    	    	        
    	    	            	    	    	    	        
    	    	            	    	    	    	        
    	    	  /*
    	    	   * 
    	    	   *         											KUNDE
    	    	   *         
    	    	   */
    	    	            	    	    	    	    	IModel<List<? extends Kunde>> makeChoicesKunde = new AbstractReadOnlyModel<List<? extends Kunde>>()
    	    	            	    	    			        {
    	    	            	    	    			            @Override
    	    	            	    	    			            public List<Kunde> getObject()
    	    	            	    	    			            { List<Kunde> kundenlist=new  ArrayList<Kunde>(); 
    	    	            	    	    			            
    	    	            	    	    			            Personen person=((Search)StrassenSucheForm.this.getDefaultModelObject()).getPerson();
    	    	            	    	    	    	           
    	    	            	    	    			            if(person!=null){
    	    	            	    	    			              	
    	    	            	    	    	    	            	Iterator kundeniterator=person.getKunden().iterator();
    	    	            	    	    			            
    	    	            	    	    			            while(kundeniterator.hasNext()){
    	    	            	    	    			            	Kunde kunde=(Kunde)kundeniterator.next();
    	    	            	    	    			            	kundenlist.add(kunde);
    	    	            	    	    			            }
    	    	            	    	    			            }  
    	    	            	    	    			                return kundenlist;
    	    	            	    	    			            }

    	    	            	    	    			        };	
    	    	            	    	    	    	        
    	    	            	    	    			        IChoiceRenderer<Kunde> kundechoice=        		new IChoiceRenderer<Kunde>() {

    	    	    	                            	    	    public Object getDisplayValue(Kunde kunde)
    	    	    	                            	    	    {
    	    	    	                            	            return kunde.getId()+" "+kunde.getKundenart().getKundenart()+" "+kunde.getStatus().getStatustext();}
    	    	    	           
    	    	    	                            	    	    public String getIdValue(Kunde object,int index)
    	    	    	                            	    	    {
    	    	    	                            	    	        return object.getId().toString();
    	    	    	                            	    	    }
    	    	            	    	    			        };
    	    	    	                            	        final DropDownChoice<Kunde> kunden = new DropDownChoice<Kunde>("kunden", makeChoicesKunde,kundechoice);
    	    	    	                            	     
    	                            	             	    
    	    	                            	       		   final DropDownChoice<Objekte> objekte = new DropDownChoice<Objekte>("objekt",     
    	    	   	    	    	    	        		 makeChoicesObjekte,objektechoicerenderer);       	       
	      	      
/*
 * 
 * 																		ANGEBOTE
 * 
 */
    	    	                            	    		   
    	    	                            	    		    private	IModel<List<? extends Angebot>> makeChoicesAngebote = new AbstractReadOnlyModel<List<? extends Angebot>>()
    	    	                    	    	    	        {
    	    	                    	    	    	            @Override
    	    	                    	    	    	            public List<Angebot> getObject()
    	    	                    	    	    	            { List<Angebot> angebotelist=new  ArrayList<Angebot>(); 
    	    	                    	    	    	            
    	    	                    	    	    	            Objekte objekt=((Search)StrassenSucheForm.this.getDefaultModelObject()).getObjekt();
    	    	    	            	    	    	    	     
    	    	                    	    	    	            if(objekt!=null){
    	    	                    	    	    	            	Iterator angeboteiterator=objekt.getAngobjzuords().iterator();
    	    	                    	    	    	            while(angeboteiterator.hasNext()){
    	    	                    	    	    	            	Angobjzuord angobjzuord=(Angobjzuord)angeboteiterator.next();
    	    	                    	    	    	            	angebotelist.add(angobjzuord.getAngebot());
    	    	                    	    	    	            }
    	    	                    	    	    	            }
    	    	                    	    	    	                return angebotelist;
    	    	                    	    	    	            }

    	    	                    	    	    	        };
    	    	                    	    	    	        
    	    	                    	    	    	        IChoiceRenderer<Angebot> angebotechoicerenderer=        		new IChoiceRenderer<Angebot>() {

        	    	                            	        	    public Object getDisplayValue(Angebot angebot)
        	    	                            	        	    {  
        	    	                            	        	    	return angebot.getId();
        	    	                            	        	 
        	    	                            	        	    }

        	    	                            	        	    public String getIdValue(Angebot object,int index)
        	    	                            	        	    {
        	    	                            	        	        return object.getId();
        	    	                            	        	    }
        	    	                            	        };    
    	    	                    	    	    	        
    	    	                    	    	    	        final DropDownChoice<String> angebote = new DropDownChoice("angebote",
    	    	                    	   	    		     		 makeChoicesAngebote,angebotechoicerenderer);	  
    	    	                    	    	    	        
    	    	                    	    	    	        
    	    	                    	    	    	        
    	    	                    	    	    	        
    	    	                    	    	    	        
    	    	                            	    		   
    	    	                            	    		   
    	    	                            	    		   
    	    	                            	    		   
    	    	                            	    		   
    	    	                            	    		     
	

	    	        
	    	      
	    	           
	    	    final    BootstrapButton onNext=new BootstrapButton("nextButton",NextModel(),Buttons.Type.Default)
	    			{
	    				@Override
	    				public void onSubmit()
	    				{onNext();
	    				}
	    				 @Override
	    				    protected void onComponentTag(ComponentTag tag) {
	    				        super.onComponentTag(tag);
	    				        if (customCSS != null)
	    				            tag.put("class", customCSS);
	    				    }
	    			
	    				  @Override
	    				    public boolean isEnabled() {
	    			        return super.isEnabled() && customEnabled;
	    				    }
	    			
	    			};
	    			
	    		    final    BootstrapButton onTree=new BootstrapButton("treeButton",TreeModel(),Buttons.Type.Default)
	    			{
	    				@Override
	    				public void onSubmit()
	    				{onTree();
	    				}
	    				 @Override
	    				    protected void onComponentTag(ComponentTag tag) {
	    				        super.onComponentTag(tag);
	    				        if (customCSS != null)
	    				            tag.put("class", customCSS);
	    				    }
	    			
	    				  @Override
	    				    public boolean isEnabled() {
	    			        return super.isEnabled() && customEnabled;
	    				    }
	    			
	    			};        	    
	 
	 	   
	    			  
	    			public StrassenSucheForm(String id,final boolean withNext,final int whithObjekt,boolean witheigentuemertyp,IModel<Search> search)
     {
		
         super(id,new CompoundPropertyModel(search));
        
         textsearchmarkup.setOutputMarkupPlaceholderTag(true);
         textsearchmarkup.add(searchTextField);
         add(textsearchmarkup);
         searchTextField.add(new AjaxFormComponentUpdatingBehavior("onchange")
         {
             @Override
             protected void onUpdate(AjaxRequestTarget target)
             {	 personenmarkup.setVisible(true);
            	 landmarkup.setVisible(false);
            	 target.add(landmarkup);
                 target.add(personenmarkup);
             }
         });
         if(whithObjekt==1||whithObjekt==0) textsearchmarkup.setVisible(false);
         
         eigentuemertypmarkup.setOutputMarkupPlaceholderTag(true);
         eigentuemertypmarkup.add(eigentuemertyp);
 	 add(eigentuemertypmarkup);
 	eigentuemertypmarkup.setVisible(false);
 	 eigentuemertyp.add(new AjaxFormComponentUpdatingBehavior("onchange")
     {
         @Override
         protected void onUpdate(AjaxRequestTarget target)
         {
         target.add(eigentuemertypmarkup);
         }
     });
    
         if(witheigentuemertyp) eigentuemertypmarkup.setVisible(true);
     
        landmarkup.setVisible(true);
        landmarkup.setOutputMarkupPlaceholderTag(true);
        landmarkup.add(land);
        add(landmarkup);
       
        
        if( ((Search)StrassenSucheForm.this.getDefaultModelObject()).getLand()==null) {
        	ortemarkup.setVisible(false);
        logger.debug("land  abfrage vor ortemarkup "+((Search)StrassenSucheForm.this.getDefaultModelObject()).getLand()+" "+ortemarkup.isVisible());}
        else {
        	ortemarkup.setVisible(true);
        	 landmarkup.setVisible(false);
        }
      
        ortemarkup.setOutputMarkupPlaceholderTag(true);
        ortemarkup.add(orte);
        ortemarkup.add(ortefield);
        orte.setOutputMarkupPlaceholderTag(true);
     
        ortefield.add(new OnChangeAjaxBehavior() {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                    landmarkup.setVisible(false);
                    target.add(landmarkup);
                    target.add(orte);
            }
    });
        land.add(new AjaxFormComponentUpdatingBehavior("onchange")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
            logger.debug("onUpdate");	
            ortemarkup.setVisible(true);
            target.add(ortemarkup);
            logger.debug("ortemarkup");	
           textsearchmarkup.setVisible(false);
           target.add(textsearchmarkup);
            target.add(landmarkup);
            }
        });
       
   	   
       
        add(ortemarkup);
        
      
        if( ((Search)StrassenSucheForm.this.getDefaultModelObject()).getOrte()==null) strassenmarkup.setVisible(false);
        else {
        	strassenmarkup.setVisible(true);
        	 landmarkup.setVisible(false);
        	 ortemarkup.setVisible(false);
        }
        strassen.setOutputMarkupPlaceholderTag(true);
        strassenmarkup.setOutputMarkupPlaceholderTag(true);
        strassenmarkup.add(strassen);
        strassenmarkup.add(strassenfield);
       
        add(strassenmarkup);
        strassenfield.add(new OnChangeAjaxBehavior() {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                    ortemarkup.setVisible(false);
                    target.add(ortemarkup);
                    target.add(strassen);
            }
    });
        orte.add(new AjaxFormComponentUpdatingBehavior("onchange")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
            strassenmarkup.setVisible(true);
            target.add(strassenmarkup);
     
            }
        }); 
       
        objektemarkup.setOutputMarkupPlaceholderTag(true);
        objektemarkup.add(objekte);
        objektemarkup.add(angebote);
	 add(objektemarkup);
	 objektemarkup.setVisible(false);
        objekte.setOutputMarkupPlaceholderTag(true);
     
        personenmarkup.setOutputMarkupPlaceholderTag(true);
        personenmarkup.add(personen);
        personenmarkup.add(kunden);
        kunden.setOutputMarkupPlaceholderTag(true);
	 add(personenmarkup);
	 personenmarkup.setVisible(false);
	 add(personenmarkup);
	 personen.setOutputMarkupPlaceholderTag(true);
	
	 personen.add(new AjaxFormComponentUpdatingBehavior("onchange")
     {
         @Override
         protected void onUpdate(AjaxRequestTarget target)
         {
         logger.debug("onUpdate");	
         strassenmarkup.setVisible(false);
         target.add(strassenmarkup);
        target.add(personenmarkup);
         }
     });
	 kunden.add(new AjaxFormComponentUpdatingBehavior("onchange")
     {
         @Override
         protected void onUpdate(AjaxRequestTarget target)
         {
         logger.debug("onUpdate");	
        target.add(personenmarkup);
         }
     });
	    strassen.add(new AjaxFormComponentUpdatingBehavior("onchange")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
            	 if(withNext==false){  customCSS = "btn btn-info pull-left col-sm-4 disabled";
                 customEnabled = false;}
            	
            		 if(whithObjekt==1) {
            			 objektemarkup.setVisible(true);
            			 personenmarkup.setVisible(false);
            		 }
            	 
            		 if(whithObjekt==2){
            			 objektemarkup.setVisible(true);
            			 personenmarkup.setVisible(true);
            		 }
            		 if(whithObjekt==0){
            			 objektemarkup.setVisible(false);
            			 personenmarkup.setVisible(false);
            		 } 
            			if(whithObjekt==-1) {
            				personenmarkup.setVisible(true);
            			objektemarkup.setVisible(false);}
            		 
            target.add(objektemarkup);
            target.add(personenmarkup);
 //         target.add(onNext);
            }
        });
	
	    
	    add(new Button("backButton")
	  		{
	  			@Override

	  			public void onSubmit()
	  			{onBack();
	  			}
	  		}); 
	   
	   onNext.setOutputMarkupPlaceholderTag(true);
		add(onNext);
		 onTree.setOutputMarkupPlaceholderTag(true);
			add(onTree);
	    
	    add(new Button("cancelButton")
	  		{
	  			@Override

	  			public void onSubmit()
	  			{onCancel();
	  			}
	  		}.setDefaultFormProcessing(false) );  
	    logger.debug("ortemarkup am Ende  "+ortemarkup.isVisible());	     
	    
     }
	
	    			
	
	
	
	
	
	/*private class NextBootstrapAjaxButton extends BootstrapAjaxButton
	{
		
		public NextBootstrapAjaxButton(String id,IModel model){
			super(id,model,Buttons.Type.Default);
			this.setOutputMarkupPlaceholderTag(true);
			
		}
		
		 @Override
		    protected void onComponentTag(ComponentTag tag) {
		        super.onComponentTag(tag);
		        if (customCSS != null)
		            tag.put("class", customCSS);
		    }
	
		  @Override
		    public boolean isEnabled() {
	        return super.isEnabled() && customEnabled;
		    }
		  
	}*/
	
	
	
	 public abstract IModel<String> TreeModel();
	 public abstract IModel<String> NextModel();
	 public abstract IModel<String> CancelModel();
	 public abstract IModel<String> BackModel();
	 public abstract void onTree();
	 public abstract void onNext();
	 public abstract void onCancel();
	 public abstract void onBack();
	}
