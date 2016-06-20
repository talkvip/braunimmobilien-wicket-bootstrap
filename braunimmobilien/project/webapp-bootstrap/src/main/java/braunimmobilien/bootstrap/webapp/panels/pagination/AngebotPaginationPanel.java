package braunimmobilien.bootstrap.webapp.panels.pagination;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.apache.wicket.markup.html.panel.Panel;

import braunimmobilien.model.Angebot;
import braunimmobilien.service.AngebotManager;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * A demo panel for {@link BootstrapPagingNavigator}
 */
public class AngebotPaginationPanel extends Panel  {
private List<Angebot> data =new ArrayList<Angebot>();
private int size=2;  
private final PageableListView<Angebot> pageable;
@SpringBean
private AngebotManager angebotManager;
public AngebotPaginationPanel(String id) {
        super(id);
        List<Angebot> data = createData();
        pageable = new PageableListView<Angebot>("pageable", data, pageSize()) {
            @Override
            protected void populateItem(ListItem<Angebot> item) {
            }
        };
        add(pageable);

        add(createPager("pager"));
        setOutputMarkupId(true);
    }
    public AngebotPaginationPanel(String id,List<Angebot> data,int size) {
        super(id);
        this.data=data;
        this.size=size;
        pageable = new PageableListView<Angebot>("pageable", data, size) {
            @Override
            protected void populateItem(ListItem<Angebot> item) {
            	 item.add(new Label("item", item.getModelObject()));
            }
        };
        add(pageable);

        add(createPager("pager"));
        setOutputMarkupId(true);
    }
 
    private Component createPager(String id) {
        return new BootstrapPagingNavigator(id, pageable);
    }
  
  
    private List<Angebot> createData() {
    	 data =new ArrayList<Angebot>();
    	Iterator it =  angebotManager.getAngebote().iterator();
        while(it.hasNext()){
        	Angebot angebot = (Angebot) it.next();
        	data.add(angebot);
        }
        return data;
    }
  
    protected int pageSize() {
        return 2;
    }
    
    
}
