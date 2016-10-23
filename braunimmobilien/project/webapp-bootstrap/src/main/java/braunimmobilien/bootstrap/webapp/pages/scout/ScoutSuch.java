package braunimmobilien.bootstrap.webapp.pages.scout;

import java.util.ArrayList;

import org.apache.wicket.model.IModel;

import java.util.Date;
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

import braunimmobilien.util.ObjectNotIdentifiableException;

import org.apache.wicket.markup.html.form.DropDownChoice;

import braunimmobilien.model.Objarttyp;
import braunimmobilien.model.Type;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Scout;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.TypeManager;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.util.ScoutUtil;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelectConfig;
import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotTree;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.StrassenSucheForm;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */

public class ScoutSuch extends BasePage {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private ScoutManager scoutManager;
	@SpringBean
    private TypeManager typeManager;
    @SpringBean
    private ObjarttypManager objarttypManager;
    @SpringBean
    private StrassenManager strassenManager;
	@SpringBean
    private ObjektartManager objektartManager;
    @SpringBean
    private ObjektManager objektManager;
    @SpringBean
    private ObjektsuchManager objektsuchManager;
	@SpringBean
    private OrteManager orteManager;
    @SpringBean
    private PersonManager personManager;
   
    static Logger logger = LoggerFactory.getLogger(ScoutSuch.class);
    
    private final ScoutUtil scoutUtil;
	private int zaehler=0;
	Scout scoutfield;
	public Scout getScoutfield() {
		return scoutfield;
	}


	public void setScoutfield(Scout scoutfield) {
		this.scoutfield = scoutfield;
	}
	Type typefield;
	public Type getTypefield() {
		return typefield;
	}


	public void setTypefield(Type typefield) {
		this.typefield = typefield;
	}


	public Objarttyp getObjarttypfield() {
		return objarttypfield;
	}


	public void setObjarttypfield(Objarttyp objarttypfield) {
		this.objarttypfield = objarttypfield;
	}
	Objarttyp objarttypfield;
	 String selectedSearch;
                      	   public String getSelectedSearch() {
		return selectedSearch;
	}


	public void setSelectedSearch(String selectedSearch) {
		this.selectedSearch = selectedSearch;
	}


						public int getZaehler() {
		return zaehler;
	}


	public void setZaehler(int zaehler) {
		this.zaehler = zaehler;
	}
final	TextField<String> searchField= new TextField<String>("searchField", new PropertyModel<String>(this, "selectedSearch"));
	
	
	final   Button addObjectToScoutButton=new Button("addObjectToScoutButton")
		{
			@Override
			public void onSubmit()
			{PageParameters pars = new PageParameters();
			List<Scout> scoutlist=getScoutlist(); 
       	Iterator itt=	scoutlist.iterator();
          	while(itt.hasNext()){
          		Scout scout1=(Scout)itt.next();
          		if (scout1.getObjekt()==null){
          		try{
          			if(scoutUtil.notexistObject(scout1)) continue;
          		if (scout1.getPerson()!=null&&scout1.getPerson().getEigentuemermuster().size()!=0){
	scout1.setType(typeManager.get(Long.parseLong("3")));
	scoutManager.save(scout1);
          		}
	
}

          		catch(ObjectNotIdentifiableException e){	
          			
          			if (scout1.getPerson()!=null&&scout1.getPerson().getEigentuemermuster().size()!=0){
          				scout1.setType(typeManager.get(Long.parseLong("3")));
          				scoutManager.save(scout1);
          			}
          			
       		}
          		}
          	}
      
          setResponsePage(ScoutSuch.class);
              
		}
		};  
	

		final   Button editScoutButton=new Button("editScoutButton")
       				{
       					@Override
       					public void onSubmit()
       					{
       						PageParameters pars = new PageParameters();
       				if	(scoutfield!=null) 					pars.add("scoutid", scoutfield.getId().toString());
       				if (typefield  !=null)					pars.add("typeid", typefield.getId().toString());
       			if( objarttypfield!=null)         			pars.add("objarttypid", objarttypfield.getId().toString());
				if( selectedSearch!=null)					pars.add("searchtext", selectedSearch);
	//			 setResponsePage(new IndexBootstrap(ScoutTree.class,pars,false));
				 setResponsePage(new ScoutTree(pars));
       					}
       				};  
       				
       			 final   Button eraseScoutButton=new Button("eraseScoutButton")
    				{
    					@Override
    					public void onSubmit()
    					{PageParameters pars = new PageParameters();
    						if	(scoutfield!=null)	{
    							pars.add("scoutid", scoutfield.getId().toString());
    						makeAction(this.getId(),pars);
    					//	makeAction("ScoutSucheForm","default","eraseScoutButton",pars);
    						
    						
    						//		 ReturnClass(this.getId(),pars,scoutconfiguration);
    						
    							}
    			
    					}
    				};  

IModel<List<? extends Scout>> makeChoicesScout = new AbstractReadOnlyModel<List<? extends Scout>>()
    {
        @Override
        public List<Scout> getObject()
        { 
            return getScoutlist();
        }

    };		

	IModel<List<? extends Type>> makeChoicesType = new AbstractReadOnlyModel<List<? extends Type>>()
	        {
	            @Override
	            public List<Type> getObject()
	            { List<Type> typelist=new  ArrayList<Type>(); 
	           
	            	Iterator it=typeManager.getTypees().iterator();
	            while(it.hasNext()){
	            	Type type=(Type)it.next();
	            	typelist.add(type);
	            }
	                return typelist;
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

final   	Label number = new Label("number",new PropertyModel(this,"zaehler"));
final BootstrapSelect<Objarttyp> objarttyp =new BootstrapSelect<Objarttyp>("objarttyp",new PropertyModel<Objarttyp>(this,"objarttypfield"),makeChoicesObjarttyp).with(of(false).withLiveSearch(true));
final BootstrapSelect<Type> type =new BootstrapSelect<Type>("type",new PropertyModel<Type>(this,"typefield"),makeChoicesType).with(of(false).withLiveSearch(true));
//   final DropDownChoice<Scout> scout = new DropDownChoice<Scout>("scout",new PropertyModel<Scout>(this,"scoutfield"), makeChoicesScout);
 //   final DropDownChoice<Type> type = new DropDownChoice<Type>("type",new PropertyModel<Type>(this,"typefield"), makeChoicesType);
   final BootstrapSelect<Scout> scout =new BootstrapSelect<Scout>("scout",new PropertyModel<Scout>(this,"scoutfield"),makeChoicesScout).with(of(false).withLiveSearch(true));			
//    final DropDownChoice<Objarttyp> objarttyp = new DropDownChoice("objarttyp",new PropertyModel<Objarttyp>(this,"objarttypfield"),makeChoicesObjarttyp);	    
    

//final PageParameters pageparameters;	        


public ScoutSuch()
	
	{ super();
	 scoutUtil=new ScoutUtil(strassenManager,objektartManager,objektManager,scoutManager,objektsuchManager,orteManager,personManager);
	  BootstrapForm  bootstrapForm = new BootstrapForm("form");
	  logger.debug("start ");
	  add(bootstrapForm);
	  final NotificationPanel feedback = new NotificationPanel("feedback");
		add(feedback);	
		searchField.setOutputMarkupId(true);
		bootstrapForm.add(searchField);
        searchField.add(new AjaxFormComponentUpdatingBehavior("onchange")
         {
             @Override
             protected void onUpdate(AjaxRequestTarget target)
             { logger.debug("ajax");
                 target.add(scout);
                 target.add(number);
             }
         });
        bootstrapForm.add(addObjectToScoutButton);
        bootstrapForm.add(eraseScoutButton);
        bootstrapForm.add(editScoutButton);
        bootstrapForm.add(scout);
          scout.setOutputMarkupPlaceholderTag(true);
          bootstrapForm.add(type);
          number.setOutputMarkupPlaceholderTag(true);
          bootstrapForm.add(number);
          type.setOutputMarkupPlaceholderTag(true);
          type.add(new AjaxFormComponentUpdatingBehavior("onchange")
          {
              @Override
              protected void onUpdate(AjaxRequestTarget target)
              {
          
              scout.setVisible(true);
              target.add(scout);
              target.add(number);
              
             
              }
          });
  	       IChoiceRenderer scoutchoice=        		new IChoiceRenderer() {

  	    	    public Object getDisplayValue(Object object){
  			Scout scout= (Scout)object;
  	           return scout.getId();
  	    	    	
  	    	    }

  	    	    public String getIdValue(Object object,int index)
  	    	    {Scout scout= (Scout)object;
  	    	        return scout.getId().toString();
  	    	    }
  	}; 
  	
  	  IChoiceRenderer typechoice=        		new IChoiceRenderer() {

    	    public Object getDisplayValue(Object object){
  		Type type= (Type)object;
           return type.getType();
    	    	
    	    }

    	    public String getIdValue(Object object,int index)
    	    {Type type= (Type)object;
    	        return type.getId().toString();
    	    }
  }; 
  	
  	scout.setChoiceRenderer(scoutchoice);
  	type.setChoiceRenderer(typechoice);    
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
  	bootstrapForm.add(objarttyp);   
  	 objarttyp.add(new AjaxFormComponentUpdatingBehavior("onchange")
       {
           @Override
           protected void onUpdate(AjaxRequestTarget target)
           {
       
           scout.setVisible(true);
           target.add(scout);
           target.add(number);
           
          
           }
       });
  //	scoutManager.reindex();
}

private List<Scout> getScoutlist()
{ List<Scout> scoutlist=new  ArrayList<Scout>(); 
logger.debug("scoutlist "+getTypefield()+"  "+getObjarttypfield()+"  "+getSelectedSearch());
 Type type=new Type();
 type.setId(new Long(0));
 Objarttyp objarttyp=new Objarttyp();
 objarttyp.setId(new Long(0));
 if(getTypefield()!=null)type=getTypefield();
 if(getObjarttypfield()!=null)objarttyp=getObjarttypfield();
 Iterator scoutiterator;
 if(getSelectedSearch()!=null) scoutiterator=scoutManager.search(getSelectedSearch()).iterator();
 else scoutiterator=scoutManager.getScoutes().iterator();
zaehler=0;
logger.debug("scoutiterator ");
while(scoutiterator.hasNext()){
	logger.debug("scoutiterator has member");
	Scout scout=(Scout)scoutiterator.next();

	if (scout.getType()==null) continue;
//	if (scout.getWho()==null) continue;
	if(type.getId().intValue()==0 && objarttyp.getId().longValue()==0){
    scoutlist.add(scout);
    	++zaehler;
	continue;}
	
	
if (scout.getType().getId().intValue()==type.getId().intValue()&&objarttyp.getId().longValue()==0){
	logger.debug("scoutiterator added");
scoutlist.add(scout);
	++zaehler;      		
	continue;
	}
		if (scout.getType().getId().intValue()==0&&scout.getObjarttyp().getId().longValue()==objarttyp.getId().longValue()){
         	scoutlist.add(scout);
         	++zaehler;
         	continue;
         	}	
		System.err.println(" 00000000000000000000000000000000000000000000000000 "+scout.getObjarttyp().getId().longValue());
		if (scout.getType().getId().intValue()==type.getId().intValue()&&scout.getObjarttyp().getId().longValue()==objarttyp.getId().longValue()){
			scoutlist.add(scout);
         	++zaehler;
         	continue;
         	}	
}
System.err.println("äääääääääääääää Anzahl Einträge Type "+type+" "+objarttyp+"  "+zaehler);
    return scoutlist;
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
