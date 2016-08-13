package braunimmobilien.bootstrap.webapp.pages.parameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import org.apache.wicket.markup.html.link.PopupSettings;
import braunimmobilien.bootstrap.webapp.EntityModel;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.AttributeModifier;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import braunimmobilien.bootstrap.webapp.MaklerFlow;
import braunimmobilien.bootstrap.webapp.pages.tree.MyFoo;
import braunimmobilien.bootstrap.webapp.pages.provider.MyFooProvider;
import braunimmobilien.bootstrap.webapp.pages.wizard.NewLandWizard;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.Page;
import java.io.StringWriter;
import java.io.PrintWriter;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.http.WebRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.wicket.markup.html.link.PopupSettings;
import java.io.File;
import java.util.List;
import java.util.Hashtable;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import java.util.Collections;
import org.apache.wicket.markup.html.form.Button;
import java.util.Iterator;
import java.util.Collections;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import java.util.ArrayList;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.WebMarkupContainer;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Scout;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.model.Xtyp;
import braunimmobilien.service.XtypManager;
import braunimmobilien.model.Objektart;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.model.Angstatus;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.model.Eigtstatus;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.model.Kondition;
import braunimmobilien.service.KonditionManager;
import braunimmobilien.model.Kundenart;
import braunimmobilien.service.KundenartManager;
import braunimmobilien.model.Mitarbeiter;
import braunimmobilien.service.MitarbeiterManager;
import braunimmobilien.model.Objarttyp;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.model.Status;
import braunimmobilien.service.StatusManager;
import braunimmobilien.model.Telefonart;
import braunimmobilien.service.TelefonartManager;
import braunimmobilien.model.Type;
import braunimmobilien.service.TypeManager;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.ListChoice;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */

public class Parameters extends BasePage {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private ObjektartManager objektartManager;
	@SpringBean
    private EigentuemermusterManager eigentuemermusterManager;
	@SpringBean
    private XtypManager xtypManager;
	@SpringBean
    private AngstatusManager angstatusManager;
	@SpringBean
    private EigentuemertypManager eigentuemertypManager;
	@SpringBean
    private EigtstatusManager eigtstatusManager;
	@SpringBean
    private KonditionManager konditionManager;
	@SpringBean
    private MitarbeiterManager mitarbeiterManager;
	@SpringBean
    private ObjarttypManager objarttypManager;
	@SpringBean
    private ObjektsuchManager objektsuchManager;
	@SpringBean
    private StatusManager statusManager;
	@SpringBean
    private TelefonartManager telefonartManager;
	@SpringBean
    private TypeManager typeManager;  
	@SpringBean
    private KundenartManager kundenartManager; 

	final	ParameterModel parametermodel=new ParameterModel();
	final PageParameters pageparameters = new PageParameters();
	
	IModel<List<? extends Angstatus>> makeChoicesAngstatus = new AbstractReadOnlyModel<List<? extends Angstatus>>()
	        {
	            @Override
	            public List<Angstatus> getObject()
	            { List<Angstatus> angstatuslist=new  ArrayList<Angstatus>(); 
	           
	            	Iterator angstatusiterator=angstatusManager.getAll().iterator();
	            
	            while(angstatusiterator.hasNext()){
	            	Angstatus angstatus=(Angstatus)angstatusiterator.next();
	            	angstatuslist.add(angstatus);
	            }
	            
	                return angstatuslist;
	            }

	        };	       
	        
	        IModel<List<? extends Eigentuemertyp>> makeChoicesEigentuemertyp = new AbstractReadOnlyModel<List<? extends Eigentuemertyp>>()
	    	        {
	    	            @Override
	    	            public List<Eigentuemertyp> getObject()
	    	            { List<Eigentuemertyp> eigentuemertyplist=new  ArrayList<Eigentuemertyp>(); 
	    	           
	    	            	Iterator eigentuemertypiterator=eigentuemertypManager.getAll().iterator();
	    	            
	    	            while(eigentuemertypiterator.hasNext()){
	    	            	Eigentuemertyp eigentuemertyp=(Eigentuemertyp)eigentuemertypiterator.next();
	    	            	eigentuemertyplist.add(eigentuemertyp);
	    	            }
	    	            
	    	                return eigentuemertyplist;
	    	            }

	    	        };	   
	    	        
	    	        IModel<List<? extends Eigtstatus>> makeChoicesEigtstatus = new AbstractReadOnlyModel<List<? extends Eigtstatus>>()
	    	    	        {
	    	    	            @Override
	    	    	            public List<Eigtstatus> getObject()
	    	    	            { List<Eigtstatus> eigtstatuslist=new  ArrayList<Eigtstatus>(); 
	    	    	           
	    	    	            	Iterator eigtstatusiterator=eigtstatusManager.getAll().iterator();
	    	    	            
	    	    	            while(eigtstatusiterator.hasNext()){
	    	    	            	Eigtstatus eigtstatus=(Eigtstatus)eigtstatusiterator.next();
	    	    	            	eigtstatuslist.add(eigtstatus);
	    	    	            }
	    	    	            
	    	    	                return eigtstatuslist;
	    	    	            }

	    	    	        };	       
	    	    	        
	    	    	        IModel<List<? extends Kondition>> makeChoicesKondition = new AbstractReadOnlyModel<List<? extends Kondition>>()
	    	    	    	        {
	    	    	    	            @Override
	    	    	    	            public List<Kondition> getObject()
	    	    	    	            { List<Kondition> konditionlist=new  ArrayList<Kondition>(); 
	    	    	    	           
	    	    	    	            	Iterator konditioniterator=konditionManager.getAll().iterator();
	    	    	    	            
	    	    	    	            while(konditioniterator.hasNext()){
	    	    	    	            	Kondition kondition=(Kondition)konditioniterator.next();
	    	    	    	            	konditionlist.add(kondition);
	    	    	    	            }
	    	    	    	            
	    	    	    	                return konditionlist;
	    	    	    	            }

	    	    	    	        };	       
	    	        
	    	        
	
	    	    	    	        IModel<List<? extends Kundenart>> makeChoicesKundenart = new AbstractReadOnlyModel<List<? extends Kundenart>>()
	    	    	    	    	        {
	    	    	    	    	            @Override
	    	    	    	    	            public List<Kundenart> getObject()
	    	    	    	    	            { List<Kundenart> kundenartlist=new  ArrayList<Kundenart>(); 
	    	    	    	    	           
	    	    	    	    	            	Iterator kundenartiterator=kundenartManager.getAll().iterator();
	    	    	    	    	            
	    	    	    	    	            while(kundenartiterator.hasNext()){
	    	    	    	    	            	Kundenart kundenart=(Kundenart)kundenartiterator.next();
	    	    	    	    	            	kundenartlist.add(kundenart);
	    	    	    	    	            }
	    	    	    	    	            
	    	    	    	    	                return kundenartlist;
	    	    	    	    	            }

	    	    	    	    	        };	                             	    
	
	
	
	IModel<List<? extends Mitarbeiter>> makeChoicesMitarbeiter = new AbstractReadOnlyModel<List<? extends Mitarbeiter>>()
	        {
	            @Override
	            public List<Mitarbeiter> getObject()
	            { List<Mitarbeiter> mitarbeiterlist=new  ArrayList<Mitarbeiter>(); 
	           
	            	Iterator mitarbeiteriterator=mitarbeiterManager.getAll().iterator();
	            
	            while(mitarbeiteriterator.hasNext()){
	            	Mitarbeiter mitarbeiter=(Mitarbeiter)mitarbeiteriterator.next();
	            	mitarbeiterlist.add(mitarbeiter);
	            }
	            
	                return mitarbeiterlist;
	            }

	        };	              
                       
                        	    
	        IModel<List<? extends Objarttyp>> makeChoicesObjarttyp = new AbstractReadOnlyModel<List<? extends Objarttyp>>()
	    	        {
	    	            @Override
	    	            public List<Objarttyp> getObject()
	    	            { List<Objarttyp> objarttyplist=new  ArrayList<Objarttyp>(); 
	    	           
	    	            	Iterator objarttypiterator=objarttypManager.getAll().iterator();
	    	            
	    	            while(objarttypiterator.hasNext()){
	    	            	Objarttyp objarttyp=(Objarttyp)objarttypiterator.next();
	    	            	objarttyplist.add(objarttyp);
	    	            }
	    	            
	    	                return objarttyplist;
	    	            }

	    	        };	       
	    	        
	    	        IModel<List<? extends Objektsuch>> makeChoicesObjektsuch = new AbstractReadOnlyModel<List<? extends Objektsuch>>()
	    	    	        {
	    	    	            @Override
	    	    	            public List<Objektsuch> getObject()
	    	    	            { List<Objektsuch> objektsuchlist=new  ArrayList<Objektsuch>(); 
	    	    	           
	    	    	            	Iterator objektsuchiterator=objektsuchManager.getAll().iterator();
	    	    	            
	    	    	            while(objektsuchiterator.hasNext()){
	    	    	            	Objektsuch objektsuch=(Objektsuch)objektsuchiterator.next();
	    	    	            	objektsuchlist.add(objektsuch);
	    	    	            }
	    	    	            
	    	    	                return objektsuchlist;
	    	    	            }

	    	    	        };	   
	    	    	        
	    	    	        IModel<List<? extends Status>> makeChoicesStatus = new AbstractReadOnlyModel<List<? extends Status>>()
	    	    	    	        {
	    	    	    	            @Override
	    	    	    	            public List<Status> getObject()
	    	    	    	            { List<Status> statuslist=new  ArrayList<Status>(); 
	    	    	    	           
	    	    	    	            	Iterator statusiterator=statusManager.getAll().iterator();
	    	    	    	            
	    	    	    	            while(statusiterator.hasNext()){
	    	    	    	            	Status status=(Status)statusiterator.next();
	    	    	    	            	statuslist.add(status);
	    	    	    	            }
	    	    	    	            
	    	    	    	                return statuslist;
	    	    	    	            }

	    	    	    	        };	       
	    	    	    	        
	    	    	    	        IModel<List<? extends Telefonart>> makeChoicesTelefonart = new AbstractReadOnlyModel<List<? extends Telefonart>>()
	    	    	    	    	        {
	    	    	    	    	            @Override
	    	    	    	    	            public List<Telefonart> getObject()
	    	    	    	    	            { List<Telefonart> telefonartlist=new  ArrayList<Telefonart>(); 
	    	    	    	    	           
	    	    	    	    	            	Iterator telefonartiterator=telefonartManager.getAll().iterator();
	    	    	    	    	            
	    	    	    	    	            while(telefonartiterator.hasNext()){
	    	    	    	    	            	Telefonart telefonart=(Telefonart)telefonartiterator.next();
	    	    	    	    	            	telefonartlist.add(telefonart);
	    	    	    	    	            }
	    	    	    	    	            
	    	    	    	    	                return telefonartlist;
	    	    	    	    	            }

	    	    	    	    	        };	       
	    	    	        
	    	    	        
	    	    	    	    	        IModel<List<? extends Type>> makeChoicesType = new AbstractReadOnlyModel<List<? extends Type>>()
	    	    	    	    	    	        {
	    	    	    	    	    	            @Override
	    	    	    	    	    	            public List<Type> getObject()
	    	    	    	    	    	            { List<Type> typelist=new  ArrayList<Type>(); 
	    	    	    	    	    	           
	    	    	    	    	    	            	Iterator typeiterator=typeManager.getAll().iterator();
	    	    	    	    	    	            
	    	    	    	    	    	            while(typeiterator.hasNext()){
	    	    	    	    	    	            	Type type=(Type)typeiterator.next();
	    	    	    	    	    	            	typelist.add(type);
	    	    	    	    	    	            }
	    	    	    	    	    	            
	    	    	    	    	    	                return typelist;
	    	    	    	    	    	            }

	    	    	    	    	    	        };	     
	    	    	    	    	    	        
	    	    	    	    	    	
	    	
	    	    	    	    	    	        IModel<List<? extends Objektart>> makeChoicesObjektart = new AbstractReadOnlyModel<List<? extends Objektart>>()
	    	    	    	    	    	    	        {
	    	    	    	    	    	    	            @Override
	    	    	    	    	    	    	            public List<Objektart> getObject()
	    	    	    	    	    	    	            { List<Objektart> objektartlist=new  ArrayList<Objektart>(); 
	    	    	    	    	    	    	           
	    	    	    	    	    	    	            	Iterator objektartiterator=objektartManager.getAll().iterator();
	    	    	    	    	    	    	            
	    	    	    	    	    	    	            while(objektartiterator.hasNext()){
	    	    	    	    	    	    	            	Objektart objektart=(Objektart)objektartiterator.next();
	    	    	    	    	    	    	            	objektartlist.add(objektart);
	    	    	    	    	    	    	            }
	    	    	    	    	    	    	            
	    	    	    	    	    	    	                return objektartlist;
	    	    	    	    	    	    	            }

	    	    	    	    	    	    	        };	                 
	    	
	    	
	    	
	    	IModel<List<? extends Xtyp>> makeChoicesXtyp = new AbstractReadOnlyModel<List<? extends Xtyp>>()
	    	        {
	    	            @Override
	    	            public List<Xtyp> getObject()
	    	            { List<Xtyp> xtyplist=new  ArrayList<Xtyp>(); 
	    	           
	    	            	Iterator xtypiterator=xtypManager.getAll().iterator();
	    	            
	    	            while(xtypiterator.hasNext()){
	    	            	Xtyp xtyp=(Xtyp)xtypiterator.next();
	    	            	xtyplist.add(xtyp);
	    	            }
	    	            
	    	                return xtyplist;
	    	            }

	    	        };	                             	    
                        	    
                   
			
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
			
			        IChoiceRenderer musterchoice=        		new IChoiceRenderer() {
			        	
			    	    public Object getDisplayValue(Object object)
			    	    {Eigentuemermuster muster= (Eigentuemermuster)object;
			          try{  return muster.getId()+" "+muster.getEigentuemermuster();}
			    	    catch(Exception ex){ 
			    	      return "Fehler";}
			    	    	
			    	    }

			    	    public String getIdValue(Object object,int index)
			    	    {Eigentuemermuster muster= (Eigentuemermuster)object;
			    	        return muster.getId().toString();
			    	    }
			}; 
			  IChoiceRenderer xtypchoice=        		new IChoiceRenderer() {

		  	    public Object getDisplayValue(Object object){
		  	    Xtyp xtyp= (Xtyp)object;
		        try{  return xtyp.getId()+" "+xtyp.getXtypkuerzel();}
		  	    catch(Exception ex){ 
		  	      return "Fehler";}
		  	    	
		  	    }

		  	    public String getIdValue(Object object,int index)
		  	    {Xtyp xtyp=(Xtyp)object;
		  	        return xtyp.getId().toString();
		  	    }
		}; 



		IChoiceRenderer objektartchoice=        		new IChoiceRenderer() {

			    public Object getDisplayValue(Object object){
			    Objektart objektart= (Objektart)object;
		    try{  return objektart.getId()+" "+objektart.getObjartname();}
			    catch(Exception ex){ 
			      return "Fehler";}
			    	
			    }

			    public String getIdValue(Object object,int index)
			    {Objektart objektart=(Objektart)object;
			        return objektart.getId().toString();
			    }
		}; 

		IChoiceRenderer angstatuschoice=        		new IChoiceRenderer() {

			    public Object getDisplayValue(Object object){
			    Angstatus angstatus= (Angstatus)object;
		    try{  return angstatus.getId()+" "+angstatus.getStatustext();}
			    catch(Exception ex){ 
			      return "Fehler";}
			    	
			    }

			    public String getIdValue(Object object,int index)
			    {Angstatus angstatus=(Angstatus)object;
			        return angstatus.getId().toString();
			    }
		}; 

		IChoiceRenderer eigentuemertypchoice=        		new IChoiceRenderer() {

		    public Object getDisplayValue(Object object){
		    Eigentuemertyp eigentuemertyp= (Eigentuemertyp)object;
		try{  return eigentuemertyp.getId()+" "+eigentuemertyp.getTypenbeschreibung();}
		    catch(Exception ex){ 
		      return "Fehler";}
		    	
		    }

		    public String getIdValue(Object object,int index)
		    {Eigentuemertyp eigentuemertyp=(Eigentuemertyp)object;
		        return eigentuemertyp.getId().toString();
		    }
		}; 


		IChoiceRenderer eigtstatuschoice=        		new IChoiceRenderer() {

			    public Object getDisplayValue(Object object){
			    Eigtstatus eigtstatus= (Eigtstatus)object;
		    try{  return eigtstatus.getId()+" "+eigtstatus.getEigt_status_text();}
			    catch(Exception ex){ 
			      return "Fehler";}
			    	
			    }

			    public String getIdValue(Object object,int index)
			    {Eigtstatus eigtstatus=(Eigtstatus)object;
			        return eigtstatus.getId().toString();
			    }
		}; 

		IChoiceRenderer konditionchoice=        		new IChoiceRenderer() {

		    public Object getDisplayValue(Object object){
		    Kondition kondition= (Kondition)object;
		try{  return kondition.getId()+" "+kondition.getKontext();}
		    catch(Exception ex){ 
		      return "Fehler";}
		    	
		    }

		    public String getIdValue(Object object,int index)
		    {Kondition kondition=(Kondition)object;
		        return kondition.getId().toString();
		    }
		}; 

		IChoiceRenderer mitarbeiterchoice=        		new IChoiceRenderer() {

		    public Object getDisplayValue(Object object){
		    Mitarbeiter mitarbeiter= (Mitarbeiter)object;
		try{  return mitarbeiter.getId()+" "+mitarbeiter.getPerson().getEigtName();}
		    catch(Exception ex){ 
		      return "Fehler";}
		    	
		    }

		    public String getIdValue(Object object,int index)
		    {Mitarbeiter mitarbeiter=(Mitarbeiter)object;
		        return mitarbeiter.getId().toString();
		    }
		}; 

		IChoiceRenderer objarttypchoice=        		new IChoiceRenderer() {

		public Object getDisplayValue(Object object){
		Objarttyp objarttyp= (Objarttyp)object;
		try{  return objarttyp.getId()+" "+objarttyp.getTypentext();}
		catch(Exception ex){ 
		  return "Fehler";}
			
		}

		public String getIdValue(Object object,int index)
		{Objarttyp objarttyp=(Objarttyp)object;
		    return objarttyp.getId().toString();
		}
		}; 


		IChoiceRenderer objektsuchchoice=        		new IChoiceRenderer() {

			    public Object getDisplayValue(Object object){
			    Objektsuch objektsuch= (Objektsuch)object;
		    try{  return objektsuch.getId()+" "+objektsuch.getSuchtext();}
			    catch(Exception ex){ 
			      return "Fehler";}
			    	
			    }

			    public String getIdValue(Object object,int index)
			    {Objektsuch objektsuch=(Objektsuch)object;
			        return objektsuch.getId().toString();
			    }
		}; 

		IChoiceRenderer statuschoice=        		new IChoiceRenderer() {

		    public Object getDisplayValue(Object object){
		    Status status= (Status)object;
		try{  return status.getId()+" "+status.getStatustext();}
		    catch(Exception ex){ 
		      return "Fehler";}
		    	
		    }

		    public String getIdValue(Object object,int index)
		    {Status status=(Status)object;
		        return status.getId().toString();
		    }
		}; 

		IChoiceRenderer telefonartchoice=        		new IChoiceRenderer() {

		    public Object getDisplayValue(Object object){
		    Telefonart telefonart= (Telefonart)object;
		try{  return telefonart.getId()+" "+telefonart.getEn();}
		    catch(Exception ex){ 
		      return "Fehler";}
		    	
		    }

		    public String getIdValue(Object object,int index)
		    {Telefonart telefonart=(Telefonart)object;
		        return telefonart.getId().toString();
		    }
		}; 

		IChoiceRenderer typechoice=        		new IChoiceRenderer() {

		public Object getDisplayValue(Object object){
		Type type= (Type)object;
		try{  return type.getId()+" "+type.getType();}
		catch(Exception ex){ 
		  return "Fehler";}
			
		}

		public String getIdValue(Object object,int index)
		{Type type=(Type)object;
		    return type.getId().toString();
		}
		}; 


		IChoiceRenderer kundenartchoice=        		new IChoiceRenderer() {

		    public Object getDisplayValue(Object object){
		    Kundenart kundenart= (Kundenart)object;
		try{  return kundenart.getId()+" "+kundenart.getKundenart();}
		    catch(Exception ex){ 
		      return "Fehler";}
		    	
		    }

		    public String getIdValue(Object object,int index)
		    {Kundenart kundenart=(Kundenart)object;
		        return kundenart.getId().toString();
		    }
		}; 
	     
		 final Button editEigentuemermuster=new Button("editEigentuemermuster")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getEigentuemermuster()!=null&&parametermodel.getEigentuemermuster().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);
					
					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertEigentuemermuster=new Button("insertEigentuemermuster")
			{
				@Override
				public void onSubmit()
				{
					
				
				}
			};       
			
			
			final Button editXtyp=new Button("editXtyp")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getXtyp()!=null&&parametermodel.getXtyp().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);
					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertXtyp=new Button("insertXtyp")
			{
				@Override
				public void onSubmit()
				{
					
						
				}
			};    	
			
			
			
		
			
		
		
			
			
			
		
		
			final Button editAngstatus=new Button("editAngstatus")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getAngstatus()!=null&&parametermodel.getAngstatus().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);	
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertAngstatus=new Button("insertAngstatus")
			{
				@Override
				public void onSubmit()
				{	
					
				}
			};    	
			
			final Button editEigentuemertyp=new Button("editEigentuemertyp")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getEigentuemertyp()!=null&&parametermodel.getEigentuemertyp().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertEigentuemertyp=new Button("insertEigentuemertyp")
			{
				@Override
				public void onSubmit()
				{
					
				}
			};    	
			
			
			final Button editEigtstatus=new Button("editEigtstatus")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getEigtstatus()!=null&&parametermodel.getEigtstatus().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertEigtstatus=new Button("insertEigtstatus")
			{
				@Override
				public void onSubmit()
				{
					
				}
			};    	
			
			
			final Button editKundenart=new Button("editKundenart")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getKundenart()!=null&&parametermodel.getKundenart().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertKundenart=new Button("insertKundenart")
			{
				@Override
				public void onSubmit()
				{	
					
				}
			};    	
			
			
			final Button editObjektart=new Button("editObjektart")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getObjektart()!=null&&parametermodel.getObjektart().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertObjektart=new Button("insertObjektart")
			{
				@Override
				public void onSubmit()
				{	
					
				}
			};    	
			
			final Button editObjektsuch=new Button("editObjektsuch")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getObjektsuch()!=null&&parametermodel.getObjektsuch().getId().longValue()>0)	{
					parametermodel.makePageParameters(pageparameters);
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertObjektsuch=new Button("insertObjektsuch")
			{
				@Override
				public void onSubmit()
				{
					
				}
			};    	
			
			
			final Button editStatus=new Button("editStatus")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getStatus()!=null&&parametermodel.getStatus().getId().longValue()>0)	{
				
					parametermodel.makePageParameters(pageparameters);
					
					
					

					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertStatus=new Button("insertStatus")
			{
				@Override
				public void onSubmit()
				{
				
					
					
					
					
					

		
				}
			};    	
			
			final Button editTelefonart=new Button("editTelefonart")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getTelefonart()!=null&&parametermodel.getTelefonart().getId().longValue()>0)	{
				
					parametermodel.makePageParameters(pageparameters);
					
					
					

					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertTelefonart=new Button("insertTelefonart")
			{
				@Override
				public void onSubmit()
				{
				
					
					
					
					
					

		
				}
			};    	
			
			
			final Button editKondition=new Button("editKondition")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getKondition()!=null&&parametermodel.getKondition().getId().length()>0)	{
				
					parametermodel.makePageParameters(pageparameters);
					
					
					

					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertKondition=new Button("insertKondition")
			{
				@Override
				public void onSubmit()
				{
				
					
					
					
					
					

		
				}
			};    	
			
			final Button editMitarbeiter=new Button("editMitarbeiter")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getMitarbeiter()!=null&&parametermodel.getMitarbeiter().getId().longValue()>0)	{
				
					parametermodel.makePageParameters(pageparameters);
					
					
					

					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertMitarbeiter=new Button("insertMitarbeiter")
			{
				@Override
				public void onSubmit()
				{
				
					
					
					
					
					

		
				}
			};    	
			
			
			final Button editObjarttyp=new Button("editObjarttyp")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getObjarttyp()!=null&&parametermodel.getObjarttyp().getId().longValue()>0)	{
				
					parametermodel.makePageParameters(pageparameters);
					
					
					

					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertObjarttyp=new Button("insertObjarttyp")
			{
				@Override
				public void onSubmit()
				{
				
					
					
					
					
					

		
				}
			};    	
			
			
			final Button editType=new Button("editType")
			{
				@Override
				public void onSubmit()
				{
				if(parametermodel.getType()!=null&&parametermodel.getType().getId().longValue()>0)	{
				
					parametermodel.makePageParameters(pageparameters);
					
					
					

					
					
				}
				else{this.error(getString("java.lang.StringError"));}	
				}
			};      
			
		 final Button insertType=new Button("insertType")
			{
				@Override
				public void onSubmit()
				{
				
					
					
					
					
					

		
				}
			};    	
			
		
		
  final DropDownChoice<Eigentuemermuster> eigentuemermuster = new DropDownChoice("eigentuemermuster",
	    		     		 makeChoicesEigentuemermuster);	    
	    	        
  final DropDownChoice<Xtyp> xtyp = new DropDownChoice("xtyp",
  		 makeChoicesXtyp);	        
	    	      
  final DropDownChoice<Objektart> objektart = new DropDownChoice("objektart",
	  		 makeChoicesObjektart);	        
  
  final DropDownChoice<Angstatus> angstatus = new DropDownChoice("angstatus",
	  		 makeChoicesAngstatus);	        
		    	      
	  final DropDownChoice<Eigentuemertyp> eigentuemertyp = new DropDownChoice("eigentuemertyp",
		  		 makeChoicesEigentuemertyp);	        
  
	  final DropDownChoice<Eigtstatus> eigtstatus = new DropDownChoice("eigtstatus",
		  		 makeChoicesEigtstatus);	        
			    	      
		  final DropDownChoice<Kondition> kondition = new DropDownChoice("kondition",
			  		 makeChoicesKondition);	        
		  
		  final DropDownChoice<Kundenart> kundenart = new DropDownChoice("kundenart",
			  		 makeChoicesKundenart);	        
				    	      
			  final DropDownChoice<Mitarbeiter> mitarbeiter = new DropDownChoice("mitarbeiter",
				  		 makeChoicesMitarbeiter);	        
			  
			  final DropDownChoice<Objarttyp> objarttyp = new DropDownChoice("objarttyp",
				  		 makeChoicesObjarttyp);	        
					    	      
				  final DropDownChoice<Objektsuch> objektsuch = new DropDownChoice("objektsuch",
					  		 makeChoicesObjektsuch);	        
				  
				  final DropDownChoice<Status> status = new DropDownChoice("status",
					  		 makeChoicesStatus);	        
						    	      
					  final DropDownChoice<Telefonart> telefonart = new DropDownChoice("telefonart",
						  		 makeChoicesTelefonart);	        
					  final DropDownChoice<Type> type = new DropDownChoice("type",
						  		 makeChoicesType);	        
							    	      

public Parameters()
	
	{ super();
	  BootstrapForm  bootstrapForm = new BootstrapForm("form");
	  add(bootstrapForm);
	  final NotificationPanel feedback = new NotificationPanel("feedback");
	  	add(feedback);
		eigentuemermuster.setChoiceRenderer(musterchoice);
		bootstrapForm.add(eigentuemermuster);
       
        xtyp.setChoiceRenderer(xtypchoice);
        bootstrapForm.add(xtyp);
        
        objektart.setChoiceRenderer(objektartchoice);
        bootstrapForm.add(objektart);
        
        angstatus.setChoiceRenderer(angstatuschoice);
        bootstrapForm.add(angstatus);
        
        eigentuemertyp.setChoiceRenderer(eigentuemertypchoice);
        bootstrapForm.add(eigentuemertyp);
        
        eigtstatus.setChoiceRenderer(eigtstatuschoice);
        bootstrapForm.add(eigtstatus);
        
        kondition.setChoiceRenderer(konditionchoice);
        bootstrapForm.add(kondition);
        
        mitarbeiter.setChoiceRenderer(mitarbeiterchoice);
        bootstrapForm.add(mitarbeiter);
        
        objarttyp.setChoiceRenderer(objarttypchoice);
        bootstrapForm.add(objarttyp);
        
        objektsuch.setChoiceRenderer(objektsuchchoice);
        bootstrapForm.add(objektsuch);
        
        status.setChoiceRenderer(statuschoice);
        bootstrapForm.add(status);
        
        telefonart.setChoiceRenderer(telefonartchoice);
        bootstrapForm.add(telefonart);
        
        type.setChoiceRenderer(typechoice);
        bootstrapForm.add(type);
        
        kundenart.setChoiceRenderer(kundenartchoice);
        bootstrapForm.add(kundenart); 
	  
	  
          bootstrapForm.add(editEigentuemermuster);
	      bootstrapForm.add(insertEigentuemermuster);
	      bootstrapForm.add(editXtyp);
	      bootstrapForm.add(insertXtyp);
	      bootstrapForm.add(editObjektart);
	      bootstrapForm.add(insertObjektart);
	      bootstrapForm.add(editAngstatus);
	      bootstrapForm.add(insertAngstatus);
	      bootstrapForm.add(editEigentuemertyp);
	      bootstrapForm.add(insertEigentuemertyp);
	      bootstrapForm.add(editEigtstatus);
	      bootstrapForm.add(insertEigtstatus);
	      bootstrapForm.add(editKundenart);
	      bootstrapForm.add(insertKundenart);
	      bootstrapForm.add(editObjektsuch);
	      bootstrapForm.add(insertObjektsuch);
	      bootstrapForm.add(editStatus);
	      bootstrapForm.add(insertStatus);
	      bootstrapForm.add(editEigtstatus);
	      bootstrapForm.add(insertTelefonart);
	      bootstrapForm.add(editTelefonart);
	      bootstrapForm.add(insertKondition);
	      bootstrapForm.add(editKondition);
	      bootstrapForm.add(insertMitarbeiter);
	      bootstrapForm.add(editMitarbeiter);
	      bootstrapForm.add(insertObjarttyp);
	      bootstrapForm.add(editObjarttyp);
	      bootstrapForm.add(insertType);
	      bootstrapForm.add(editType);
	  
	  
}



}
