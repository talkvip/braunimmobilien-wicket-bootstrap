package braunimmobilien.bootstrap.webapp.panels.pagination;



import braunimmobilien.model.Angebot;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.AttributeModifier;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotTree;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.request.mapper.parameter.PageParameters;
/**
 * A base panel for demos of paging navigators
 */
public abstract class AbstractGeneralAngebotBreadcrumbPaginationPanel extends Panel {

    protected final PageableListView<Angebot> pageable;

    public AbstractGeneralAngebotBreadcrumbPaginationPanel(String id){
        super(id);

        List<Angebot> data = createData();

        pageable = new PageableListView<Angebot>("pageable", data, pageSize()) {
            @Override
            protected void populateItem(final ListItem<Angebot> item) {
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
        add(pageable);

        add(createPager("pager"));
    }

    protected abstract List<Angebot> createData();
   

    protected abstract int pageSize();
   
    protected abstract Component createPager(String id);
    
    protected void onEditAngebot(Angebot angebot) {
    	PageParameters pageparameters=new PageParameters();
    	pageparameters.add("angnr",angebot.getId());
        setResponsePage(new AngebotTree(pageparameters));
    }

}


