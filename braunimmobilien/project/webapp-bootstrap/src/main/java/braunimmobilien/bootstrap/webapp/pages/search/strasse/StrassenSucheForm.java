package braunimmobilien.bootstrap.webapp.pages.search.strasse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

import braunimmobilien.model.Land;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.service.LandManager;



public abstract class StrassenSucheForm extends  Form{
	@SpringBean
    private  LandManager landManager;
	 protected Land selectedLand;
	
	protected Orte selectedOrt;

	  protected Strassen selectedStrasse;
	  protected Objekte selectedObjekt;
	 private String selectedOrtstring="";
	 private String selectedStrassenstring="";
	  final AutoCompleteTextField<String> ortefield = new AutoCompleteTextField<String>("ortefield",
              new PropertyModel<String>(this,"selectedOrtstring"))
          {
              @Override
              protected Iterator<String> getChoices(String input)
              {
            
             	 if (StrassenSucheForm.this.selectedLand==null)
                  {
                      List<String> emptyList = Collections.emptyList();
                      return emptyList.iterator();
                  }
                  else{
                 	 List<String> ortlist=new  ArrayList<String>(); 
                 	 
                 
                 	 Iterator orteiterator=selectedLand.getOrte().iterator();
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
                         
           
                  final AutoCompleteTextField<String> strassenfield = new AutoCompleteTextField<String>("strassenfield",
                          new PropertyModel<String>(this,"selectedStrassenstring"))
                      {
                          @Override
                          protected Iterator<String> getChoices(String input)
                          {
                        	  if (StrassenSucheForm.this.selectedOrt==null)
        	                  {
        	                      List<String> emptyList = Collections.emptyList();
        	                      return emptyList.iterator();
        	                  }
                             	 
                              else{
                             	 List<String> strassenlist=new  ArrayList<String>(); 
                             	 
                          
                             	 Iterator strasseniterator=StrassenSucheForm.this.selectedOrt.getStrassen().iterator();
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
	                            	          
	                            	            if (selectedLand!=null){
	    	                  	                  
	    	                  	                 	
	    	                  	                 	 
	    	                  	                 
	    	                  	                 	 Iterator orteiterator=selectedLand.getOrte().iterator();
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
	                            	            
	                            	                return ortelist;
	                            	            }


    	                            	        };             	    
    	                        	    
    	                            	        private   	        IModel<List<? extends Objekte>> makeChoicesObjekte = new AbstractReadOnlyModel<List<? extends Objekte>>()
    	            	    	    	    	        {
    	            	    	    	    	            @Override
    	            	    	    	    	            public List<Objekte> getObject()
    	            	    	    	    	            { List<Objekte> objektelist=new  ArrayList<Objekte>(); 
    	            	    	    	    	      
    	            	    	    	    	            if(selectedStrasse!=null){
    	            	    	    	    	            	 Iterator objekteiterator=selectedStrasse.getObjekte().iterator();
    	            	    	    	    	            	
    	            	    	    	    	           
    	            	    	    	    	            		while(objekteiterator.hasNext()){
    	            	    	    	    	            			Objekte objekte=(Objekte)objekteiterator.next();
    	            	    	    	    	            
    	            	    	    	    	            	objektelist.add(objekte); 
    	            	    	    	    	            
    	            	    	    	    	            } 
    	            	    	    	    	          }
    	            	    	    	    	        	
    	            	    	    	    	          return objektelist;
    	            	    	    	    	            }
    	            	    	    	    	        };
    	            	    	    	    	        
    	            	    	         	       
			
    	                            	        private   IModel<List<? extends Strassen>> makeChoicesStrassen = new AbstractReadOnlyModel<List<? extends Strassen>>()
    	    	                            	        {
    	                            	        	 @Override
    		                            	            public List<Strassen> getObject()
    		                            	            { List<Strassen> strassenlist=new  ArrayList<Strassen>(); 
    		                            	          
    		                            	            if (selectedOrt!=null){
    		    	                  	                  
    		    	                  	                 	
    		    	                  	                 	 
    		    	                  	                 
    		    	                  	                 	 Iterator strasseniterator=selectedOrt.getStrassen().iterator();
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
    	    	                        
	       	       
    	    	                            	        final DropDownChoice<Objekte> objekte = new DropDownChoice<Objekte>("objekte",new PropertyModel<Objekte>(this,"selectedObjekt"),
    	    	      	    	    	    	              
    	    	   	    	    	    	        		 makeChoicesObjekte,new ChoiceRenderer<Objekte>("objhausnummer","id"));       	       
	      	      
	 	final DropDownChoice<Land> land = new DropDownChoice<Land>("land",new PropertyModel<Land>(this,"selectedLand"),
	     		 makeChoicesLand,new ChoiceRenderer<Land>("landname","id"));	  
	
	 	
	    	        final DropDownChoice<Orte> orte = new DropDownChoice<Orte>("orte",new PropertyModel<Orte>(this,"selectedOrt"),
	    	            
	    	          		 makeChoicesOrt,new ChoiceRenderer<Orte>("ortname","id"));   
	   					          
	    	        final DropDownChoice<Strassen> strassen = new DropDownChoice<Strassen>("strassen",new PropertyModel<Strassen>(this,"selectedStrasse"),
		    	            
	    	          		 makeChoicesStrassen,new ChoiceRenderer<Strassen>("strname","id"));   
            	     	    
                	    
	
	public StrassenSucheForm(String id)
     {
		
         super(id);
       
        if (landManager==null) System.err.println("es geht CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        final	 WebMarkupContainer landmarkup = new WebMarkupContainer("landmarkup");
        landmarkup.setVisible(true);
        landmarkup.setOutputMarkupPlaceholderTag(true);
        landmarkup.add(land);
        add(landmarkup);
        final	 WebMarkupContainer ortemarkup = new WebMarkupContainer("ortemarkup");
        if(selectedLand==null) ortemarkup.setVisible(false);
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
            ortemarkup.setVisible(true);
            target.add(ortemarkup);
      
            }
        });
       
   	   
       
        add(ortemarkup);
        
        final	 WebMarkupContainer strassenmarkup = new WebMarkupContainer("strassenmarkup");
        if(selectedOrt==null) strassenmarkup.setVisible(false);
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
	add(objekte);
	 objekte.setOutputMarkupPlaceholderTag(true);
	 objekte.setVisible(false);
	 
	    strassen.add(new AjaxFormComponentUpdatingBehavior("onchange")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
            objekte.setVisible(true);
            target.add(objekte);
      
            }
        });
	    
	    
	    add(new Button("backButton")
	  		{
	  			@Override

	  			public void onSubmit()
	  			{onBack();
	  			}
	  		}); 
	    
	    add(new Button("nextButton")
		{
			@Override

			public void onSubmit()
			{onNext();
			}
		}); 
	    
	    add(new Button("cancelButton")
	  		{
	  			@Override

	  			public void onSubmit()
	  			{onCancel();
	  			}
	  		});  
	    
	    
     }
	  public void setSelectedLand(Land selectedLand) {
			this.selectedLand = selectedLand;
		}



		public void setSelectedOrt(Orte selectedOrt) {
			this.selectedOrt = selectedOrt;
		}



		public void setSelectedStrasse(Strassen selectedStrasse) {
			this.selectedStrasse = selectedStrasse;
		}

	 public abstract void onNext();
	 public abstract void onCancel();
	 public abstract void onBack();
	}
