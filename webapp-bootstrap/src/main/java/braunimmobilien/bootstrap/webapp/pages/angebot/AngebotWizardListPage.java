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
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import braunimmobilien.bootstrap.webapp.panels.pagination.InfiniteAngebotWizardPaginationPanel;
import braunimmobilien.bootstrap.webapp.panels.pagination.InfinitePaginationPanel;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angstatus;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.bootstrap.webapp.pages.provider.SortableAngebotDataProvider;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.bootstrap.webapp.panels.pagination.PaginationPanel;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */


public class AngebotWizardListPage extends BasePage {
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
	private static final long serialVersionUID = -5202104862675278153L;
    String blank ="                    ";
    @SpringBean
    private AngebotManager angebotManager;
   private static  List<String> data;
   private static  List<Angebot> data1;
    public AngebotWizardListPage(PageParameters parameters) {
    	 super(parameters);
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
        feedbackPanel.setVisible(false); // other pages will set this to visible
        feedbackPanel.setEscapeModelStrings(false);
        
        angstatus = new Angstatus();
        angstatus=new Angstatus();
        angstatus.setId(0L);;
        angstatus.setStatustext("all Statuses");
        Form form = new Form("form");
        Button button = new Button("add-angebot") {
            public void onSubmit() {
                onEditAngebot(new Angebot());
            }
        };
        data1 =new ArrayList<Angebot>();
   
        Iterator it =  angebotManager.getAngebote().iterator();
        while(it.hasNext()){
        	Angebot angebot = (Angebot) it.next();
       
       data1.add(angebot);
        }
        button.setDefaultFormProcessing(false);
        form.add(button);
       
        add(form);
   
        
        final 	InfiniteAngebotWizardPaginationPanel infiniteAngebotPaginationPanel	=	new InfiniteAngebotWizardPaginationPanel("infinites"){
      	  
      	  @Override
            protected int pageSize() {
                return data1.size();
            }
        	  @Override
        	    protected List<Angebot> createData() {
        	        return  data1;
        	    }  
      	  
        };
        infiniteAngebotPaginationPanel.setOutputMarkupId(true);
      infiniteAngebotPaginationPanel.setOutputMarkupPlaceholderTag(true);
     add(infiniteAngebotPaginationPanel);
     DropDownChoice<String> verkaufsartfield=new DropDownChoice<String>("verkaufsart", new PropertyModel<String>(this, "verkaufsart"),makeChoicesOffer);
     DropDownChoice<Angstatus> angstatusfield=new DropDownChoice<Angstatus>("angstatus",new PropertyModel<Angstatus>(this, "angstatus"), makeChoicesAngstatus,new ChoiceRenderer<Angstatus>("statustext","id"));
    
     angstatusfield.add(new AjaxFormComponentUpdatingBehavior("onchange")
	 {
	     @Override
	     protected void onUpdate(AjaxRequestTarget target)
	     {	 
	    	 data1.clear();
	    	 Iterator it=angebotManager.getAngebote().iterator();
	         while(it.hasNext()){
	         	Angebot angebot=(Angebot)it.next();
	         	if (angstatus.getId().longValue()!=0&&angebot.getAngstatus().getId().longValue()==angstatus.getId().longValue()&&angebot.getId().startsWith(verkaufsart)){
	         	data1.add(angebot);		
	         	}
	         	if (angstatus.getId().longValue()==0&&angebot.getId().startsWith(verkaufsart)){
	             	data1.add(angebot);		
	             	}
	         }
	     target.add(infiniteAngebotPaginationPanel);
	     }
	 });	
     verkaufsartfield.add(new AjaxFormComponentUpdatingBehavior("onchange")
   	 {
   	     @Override
   	     protected void onUpdate(AjaxRequestTarget target)
   	     {	
   	    	 data1.clear();
	    	 Iterator it=angebotManager.getAngebote().iterator();
	         while(it.hasNext()){
	         	Angebot angebot=(Angebot)it.next();
	         	if (angstatus.getId().longValue()!=0&&angebot.getAngstatus().getId().longValue()==angstatus.getId().longValue()&&angebot.getId().startsWith(verkaufsart)){
	         	data1.add(angebot);		
	         	}
	         	if (angstatus.getId().longValue()==0&&angebot.getId().startsWith(verkaufsart)){
	             	data1.add(angebot);		
	             	}
	         }
   	     target.add(infiniteAngebotPaginationPanel);
   	     }
   	 });	 
     add(form);    
    form.add(verkaufsartfield);
    form.add(angstatusfield);
    

    
    }
    
    /**
     * Listener for the edit action
     * 
     * @param user
     *            user to be edited
     */
    protected void onEditAngebot(Angebot angebot) {
    	PageParameters pageparameters=new PageParameters();
    	pageparameters.add("angnr",angebot.getId());
        setResponsePage(new AngebotWizardTree(pageparameters));
    }
}
