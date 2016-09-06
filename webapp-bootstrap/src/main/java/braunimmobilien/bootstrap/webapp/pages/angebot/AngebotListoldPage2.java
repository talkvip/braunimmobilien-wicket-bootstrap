package braunimmobilien.bootstrap.webapp.pages.angebot;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.markup.html.form.CheckBox;


import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angstatus;
import braunimmobilien.model.Objektart;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.AngstatusManager;
import org.apache.wicket.model.PropertyModel;

import braunimmobilien.bootstrap.webapp.pages.provider.SortableAngebotDataProvider;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.bootstrap.webapp.panels.pagination.PaginationPanel;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

@MountPath(value = "/angebotes")
public class AngebotListoldPage2 extends BasePage {
    private static final long serialVersionUID = -5202104862675278153L;
    String blank ="                    ";
    @SpringBean
    private AngebotManager angebotManager;
    @SpringBean
    private AngstatusManager angstatusManager;
    IModel<List<String>> makeChoicesOffer = new AbstractReadOnlyModel<List<String>>()
  	        {
  	            @Override
  	            public List<String> getObject()
  	            { List<String> list=new  ArrayList<String>();
  	            list.add("RH");
  	            list.add("EI");
  	            list.add("VB");
  	            list.add("HS");
	            list.add("BP");
	            list.add("WO");
	            list.add("VH");
	            list.add("");
  	                return list;
  	            }
  	        };
  	       
  	      IModel<List<? extends Angstatus>> makeChoicesAngstatus = new AbstractReadOnlyModel<List<? extends Angstatus>>()
  	  	        {
  	  	            @Override
  	  	            public List<Angstatus> getObject()
  	  	            { List<Angstatus> angstatuslist=new  ArrayList<Angstatus>(); 
  	  	        Angstatus angstatusa=new Angstatus();
 	               angstatusa.setId(0L);;
 	               angstatusa.setStatustext("all Statuses");
 	              angstatuslist.add(angstatusa);
 	               Iterator angstatusiterator=angstatusManager.getAngstatuses().iterator();
  	  	            while(angstatusiterator.hasNext()){
  	  	            	Angstatus angstatus=(Angstatus)angstatusiterator.next();
  	  	            	angstatuslist.add(angstatus);
  	  	            }
  	  	             
  	  	           
  	  	                return angstatuslist;
  	  	            }

  	  	        };

    private Angstatus angstatus=null;
    private String verkaufsart="";
   private static  List<String> data;
    public AngebotListoldPage2(PageParameters parameters) {
    	 super(parameters);
    	  angstatus=new Angstatus();
              angstatus.setId(0L);;
              angstatus.setStatustext("all Statuses");
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
        feedbackPanel.setVisible(false); // other pages will set this to visible
        feedbackPanel.setEscapeModelStrings(false);
      final  SortableAngebotDataProvider dp = new SortableAngebotDataProvider(angstatus,verkaufsart);
        
        final DataView<Angebot> dataView = new DataView<Angebot>("angebote", dp) {
            protected void populateItem(final Item<Angebot> item) {
                Angebot angebot = item.getModelObject();

                Link<Angebot> link = new Link<Angebot>("edit-link", item.getModel()) {
                    public void onClick() {
                        onEditAngebot(getModelObject());
                    }
                };
                link.add(new Label("angebot.id", angebot.getId()));
                item.add(link);
                item.add(new Label("angebot.angstatus", angebot.getAngstatus().getStatustext()));
                item.add(new Label("angebot.angkurzbeschreibung", angebot.getAngkurzbeschreibung()));
                item.add(new Label("angebot.anglagebeschreibung", angebot.getAnglagebeschreibung()));
                item.add(new Label("angebot.anganz", angebot.getAnganz().toString()));
                item.add(new Label("angebot.angaufdatum", angebot.getAngaufdatum().toString()));
                item.add(new Label("angebot.kondition", angebot.getKondition().getKontext()));
                item.add(new AttributeModifier("class", true, new LoadableDetachableModel() {
                    protected Object load() {
                        return (item.getIndex() % 2 == 1) ? "even" : "odd";
                    }
                }));
            }
        };

        Form form = new Form("form");
        Button button = new Button("add-angebot") {
            public void onSubmit() {
                onEditAngebot(new Angebot());
            }
        };
        button.setDefaultFormProcessing(false);
        form.add(button);
        DropDownChoice<String> verkaufsartfield=new DropDownChoice<String>("verkaufsart", new PropertyModel<String>(this, "verkaufsart"),makeChoicesOffer);
        DropDownChoice<Angstatus> angstatusfield=new DropDownChoice<Angstatus>("angstatus",new PropertyModel<Angstatus>(this, "angstatus"), makeChoicesAngstatus,new ChoiceRenderer<Angstatus>("statustext","id"));
        final	 WebMarkupContainer dataviewmarkup = new WebMarkupContainer("dataviewmarkup");
        angstatusfield.add(new AjaxFormComponentUpdatingBehavior("onchange")
   	 {
   	     @Override
   	     protected void onUpdate(AjaxRequestTarget target)
   	     {	dp.setAngstatus(angstatus);
   	     target.add(dataviewmarkup);
   	     }
   	 });	
        verkaufsartfield.add(new AjaxFormComponentUpdatingBehavior("onchange")
      	 {
      	     @Override
      	     protected void onUpdate(AjaxRequestTarget target)
      	     {	dp.setVerkaufsart(verkaufsart);;
      	     target.add(dataviewmarkup);
      	     }
      	 });	 
        add(form);    
       form.add(verkaufsartfield);
       form.add(angstatusfield);
        add(dataviewmarkup);
        dataView.setItemsPerPage(10);
        dataviewmarkup.add(new OrderByBorder("orderById", "id", dp));
        dataviewmarkup.add(dataView);
        dataView.setOutputMarkupId(true);
        dataviewmarkup.add(new BootstrapPagingNavigator("navigator", dataView));
        dataviewmarkup.setOutputMarkupPlaceholderTag(true);
    }
    
  
    protected void onEditAngebot(Angebot angebot) {
    	PageParameters pageparameters=new PageParameters();
    	pageparameters.add("angnr",angebot.getId());
        setResponsePage(new AngebotTree(pageparameters));
    }
}
