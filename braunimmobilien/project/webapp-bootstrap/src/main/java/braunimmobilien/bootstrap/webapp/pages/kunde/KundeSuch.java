package braunimmobilien.bootstrap.webapp.pages.kunde;

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
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotTree;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.EigentuemertypManager;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch;
import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */

public class KundeSuch extends BasePage {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private KundeManager kundeManager;
	@SpringBean
    private PersonManager personManager;	
	 String selectedSearch;
	Kunde selectedKunde;
	 final	 WebMarkupContainer kundemarkupold = new WebMarkupContainer("kundemarkupold");
	 static Logger logger = LoggerFactory.getLogger(KundeSuch.class);
	 final TextField<String> searchField= new TextField<String>("searchField", new PropertyModel<String>(this, "selectedSearch"));
	   final   Button editKundeButton=new Button("editKundeButton")
				{
					@Override
					public void onSubmit()
					{PageParameters pars = new PageParameters();
					  logger.debug("searchstring "+getSelectedSearch());
					    logger.debug("kunde "+getSelectedKunde());
					pars.add("kundennr", getSelectedKunde().getId().toString());
		//			pars.add("usage","edit");
						setResponsePage(new IndexBootstrap(KundeTree.class,pars));
					}
				};

	 			final InsertNachweisWithKundenNr insertNachweisWithKundenNrButton=new InsertNachweisWithKundenNr("insertNachweisWithKundenNrButton");
	 			final ShowKundenTree showKundenTreeButtonButton=new ShowKundenTree("showKundenTreeButtonButton");
	 			
	final Label kundendaten =new Label("kunde",new PropertyModel(this, "selectedSearch"));
	 public String getSelectedSearch() {
			return selectedSearch;
		}
		public void setSelectedSearch(String selectedSearch) {
			this.selectedSearch = selectedSearch;
		}
	
	public Kunde getSelectedKunde() {
			return selectedKunde;
		}
		public void setSelectedKunde(Kunde selectedKunde) {
			this.selectedKunde = selectedKunde;
		}
	




public KundeSuch()
	
	{ super();
	  BootstrapForm  bootstrapForm = new BootstrapForm("form");
	  add(bootstrapForm);
	  final NotificationPanel feedback = new NotificationPanel("feedback");
		add(feedback);
	 
	   PopupSettings googlePopupSettings = new PopupSettings(PopupSettings.RESIZABLE |
               PopupSettings.SCROLLBARS).setHeight(500).setWidth(700);
     
      searchField.setOutputMarkupId(true);
      bootstrapForm.add(searchField);
      searchField.add(new AjaxFormComponentUpdatingBehavior("onchange")
      {
          @Override
          protected void onUpdate(AjaxRequestTarget target)
          {
          try{	
        	  
        	  logger.debug("searchfield searchstring "+getSelectedSearch());
        	 
         setSelectedKunde(kundeManager.get(new Long(getSelectedSearch())));
 setSelectedSearch(getSelectedKunde().toString());
         kundemarkupold.setVisible(true);
         logger.debug("searchfield kunde "+getSelectedKunde());
         
         target.add(kundemarkupold);
          }catch(Exception e){
        	
        		  setSelectedSearch("Kunde : "+getSelectedSearch()+" nicht vorhanden!");
        		  logger.error("Error "+e+" "+getSelectedSearch());
          kundemarkupold.setVisible(false);
          target.add(kundemarkupold);
          }
          logger.debug("searchfield searchstring "+getSelectedSearch());
     	 
          target.add(kundendaten);
      
          }
      });
      bootstrapForm.add(kundendaten);
      kundendaten.setOutputMarkupId(true);
    
     

      kundemarkupold.setVisible(false);
      kundemarkupold.setOutputMarkupPlaceholderTag(true);                                 
  //    InsertNachweisWithKundenNr insertnachweiswithkundennr=new InsertNachweisWithKundenNr("insertNachweisWithKundenNrButton");
      kundemarkupold.add(editKundeButton);
      kundemarkupold.add(insertNachweisWithKundenNrButton);
      kundemarkupold.add(showKundenTreeButtonButton);
      final Link callCocoonPdfKunde=	    new Link("callCocoonPdfKunde"){   public void onClick() {
        	 getRequestCycle().scheduleRequestHandlerAfterCurrent(new RedirectRequestHandler("http://"+((WebRequest) getRequest()). getClientUrl().getHost()+":8088/controllerbraunimmobilienget?id=kunde&name="+KundeSuch.this.selectedSearch+"&reqparam=3"));
        }
        };
        callCocoonPdfKunde.setPopupSettings(googlePopupSettings);
      kundemarkupold.add(callCocoonPdfKunde);
      bootstrapForm.add(kundemarkupold);
    
     
	       
	  
	  
	  
	  
	  
	  
}
private class ShowKundenTree extends Button{
	ShowKundenTree(String id){
		super(id);
	}
	@Override
		public void onSubmit()
		{PageParameters pars = new PageParameters();
	pars.add("kundennr",(KundeSuch.this.selectedSearch));
	setResponsePage(new KundeTree(pars));
		}
		}


private class InsertNachweisWithKundenNr extends Button{
	InsertNachweisWithKundenNr(String id){
		super(id);
	}
	@Override
		public void onSubmit()
		{PageParameters pars = new PageParameters();
	pars.add("kundennr",(KundeSuch.this.selectedSearch));
	pars.add("nachweisid","null");
			setResponsePage(new IndexBootstrap(KundeSuch.class,pars));
		}
}
}
