package braunimmobilien.bootstrap.webapp.pages;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
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

import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Land;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.service.LandManager;
public class WicketTesterPage extends WebPage{
	@SpringBean
    private  LandManager landManager;
	IModel<Land> selectedLand=new EntityModel<Land>(Land.class,null);
	IModel<Orte> selectedOrt=new EntityModel<Orte>(Orte.class,null);;
  	IModel<List<? extends Land>>makeChoicesLand= new AbstractReadOnlyModel<List<? extends Land>>()
    {
        @Override
        public List<Land> getObject()
        { List<Land> landlist=new  ArrayList<Land>(); 
        	Iterator landiterator=landManager.getLandes().iterator();
        while(landiterator.hasNext()){
        	Land land=(Land) landiterator.next();
        	landlist.add(land);
        }
        	
            return landlist;
        }

    }; 
	
    private   IModel<List<? extends Orte>> makeChoicesOrt = new AbstractReadOnlyModel<List<? extends Orte>>()
    {
 @Override
    public List<Orte> getObject()
    { List<Orte> ortelist=new  ArrayList<Orte>(); 
    if (selectedLand.getObject()!=null){
       	 Iterator orteiterator=selectedLand.getObject().getOrte().iterator();
	            	while(orteiterator.hasNext()){
	            	Orte orte=(Orte)orteiterator.next();
	            	ortelist.add(orte);
	            		}
	            } 
        return ortelist;
    }
 };             	    
	final DropDownChoice<Land> land = new DropDownChoice<Land>("land",new PropertyModel<Land>(this,"selectedLand.entity"),
    		 makeChoicesLand,new ChoiceRenderer<Land>("landname","id"));	  

	
   	        final DropDownChoice<Orte> orte = new DropDownChoice<Orte>("orte",new PropertyModel<Orte>(this,"selectedOrt.entity"),
   	            
   	          		 makeChoicesOrt,new ChoiceRenderer<Orte>("ortname","id"));   
	
	public WicketTesterPage()
	{
		Form form= new Form("form");
		add(form);
		form.add(land);
		form.add(orte);
		orte.setOutputMarkupPlaceholderTag(true);
		orte.setVisible(false);
		land.setOutputMarkupPlaceholderTag(true);
		land.add(new AjaxFormComponentUpdatingBehavior("onchange")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
            orte.setVisible(true);
            target.add(orte);
            }
        });
		  form.add(new Button("nextButton")
	  		{
	  			@Override

	  			public void onSubmit(){
	  			System.err.println(selectedLand.getObject());
	  			}
	  		}); 
	}
}
