package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.extensions.breadcrumb.panel.IBreadCrumbPanelFactory;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import braunimmobilien.bootstrap.webapp.MaklerFlowUtility;
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import braunimmobilien.model.*;
import braunimmobilien.service.*;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationMessage;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import braunimmobilien.webapp.person.*;

public abstract class AbstractObjektInput  extends  Form{
	@SpringBean
	ObjektsuchManager objektsuchManager;
@SpringBean
	ObjektartManager objektartManager;
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
	        
	        final    BootstrapButton onNext=new BootstrapButton("nextButton",NextModel(),Buttons.Type.Default)
	    	{
	    		@Override
	    		public void onSubmit()
	    		{onNext();
	    		}
	    		 @Override
	    		    protected void onComponentTag(ComponentTag tag) {
	    		        super.onComponentTag(tag);
	    		        if (nextcustomCSS != null)
	    		            tag.put("class", nextcustomCSS);
	    		    }
	    	
	    		  @Override
	    		    public boolean isEnabled() {
	    	        return super.isEnabled() && nextcustomEnabled;
	    		    }
	    	
	    	};
	    	
	       final    BootstrapButton onLocation=new BootstrapButton("locationButton",LocationModel(),Buttons.Type.Default)
	    	{
	    		@Override
	    		public void onSubmit()
	    		{onLocation();
	    		}
	    		 @Override
	    		    protected void onComponentTag(ComponentTag tag) {
	    		        super.onComponentTag(tag);
	    		        if (locationcustomCSS != null)
	    		            tag.put("class", locationcustomCSS);
	    		    }
	    	
	    		  @Override
	    		    public boolean isEnabled() {
	    	        return super.isEnabled() && locationcustomEnabled;
	    		    }
	    	
	    	};   
	    	  private String nextcustomCSS = null;
				 private boolean nextcustomEnabled = true;
				 private String locationcustomCSS = null;
				 private boolean locationcustomEnabled = true;
/** test input string. */


/**
* Construct.
* 
* @param id
*            The component id
*/
public AbstractObjektInput(String id,final boolean withNext,final boolean withLocation,final IModel<Objekte> objekte)
{

super(id, new CompoundPropertyModel(objekte));
if(withNext==false){  nextcustomCSS = "btn btn-info pull-left col-sm-4 disabled";
nextcustomEnabled = false;}
if(withLocation==false){  locationcustomCSS = "btn btn-info pull-left col-sm-4 disabled";
locationcustomEnabled = false;}
add(new Label("id"));
add(new Label("strasse.id"));		
add(new TextField<String>("objhausnummer").setRequired(true));
add(new TextArea<String>("objinfo"));
add(new TextField<Double>("merkmal"));
add(new DropDownChoice<Objektsuch>("objektsuch",makeChoicesObjektsuch,new ChoiceRenderer<Objektsuch>("suchtext","id")).setRequired(true));
add(new DropDownChoice<Objektart>("objektart",makeChoicesObjektart,new ChoiceRenderer<Objektart>("objartname","id")).setRequired(true));
final  DateTextField  objaufnahme= new DateTextField("objaufnahme",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
objaufnahme.setRequired(true);
add(objaufnahme);
final  DateTextField  objletztkontakt= new DateTextField("objletztkontakt",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
add(objletztkontakt);
final  DateTextField  objvorlage= new DateTextField("objvorlage",new DateTextFieldConfig().showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage(getSession().getLocale().toString()));
add(objvorlage); 
	
add(new Button("backButton")
	{
		@Override

		public void onSubmit()
		{onBack();
		}
	}); 

onNext.setOutputMarkupPlaceholderTag(true);
add(onNext);
onLocation.setOutputMarkupPlaceholderTag(true);
add(onLocation);

add(new Button("cancelButton")
	{
		@Override

		public void onSubmit()
		{onCancel();
		}
	}.setDefaultFormProcessing(false) ); 	    


}
		 public abstract IModel<String> LocationModel();
		 public abstract IModel<String> NextModel();
		 public abstract IModel<String> CancelModel();
		 public abstract IModel<String> BackModel();
		 public abstract void onLocation();
		 public abstract void onNext();
		 public abstract void onCancel();
		 public abstract void onBack();	
			
	
	
}
