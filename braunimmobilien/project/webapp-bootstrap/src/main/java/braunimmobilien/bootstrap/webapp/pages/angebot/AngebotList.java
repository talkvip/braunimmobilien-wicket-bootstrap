package braunimmobilien.bootstrap.webapp.pages.angebot;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import braunimmobilien.bootstrap.webapp.panels.pagination.PaginationPanel;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Angstatus;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.bootstrap.webapp.pages.provider.SortableAngebotDataProvider;
import braunimmobilien.bootstrap.webapp.pages.BasePage;

import org.apache.wicket.request.mapper.parameter.PageParameters;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */
public class AngebotList extends BasePage {
    private static final long serialVersionUID = -5202104862675278153L;
    @SpringBean
    private AngebotManager angebotManager;

    public AngebotList() {
    	super(new PageParameters());
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
        feedbackPanel.setVisible(false); // other pages will set this to visible
        feedbackPanel.setEscapeModelStrings(false);
        
        // Form and button for routing user to add a new user
        Form form = new Form("form");
        Button button = new Button("add-angebot") {
            public void onSubmit() {
                onEditAngebot(new Angebot());
            }
        };
        button.setDefaultFormProcessing(false);
        form.add(button);
        add(new PaginationPanel("pagingNavigator"));
        add(form);
        Angstatus angstatus=new Angstatus();
            angstatus.setId(0L);
            angstatus.setStatustext("all Statuses");
        SortableAngebotDataProvider dp = new SortableAngebotDataProvider(angstatus,"  ");

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

        dataView.setItemsPerPage(10);
       
        add(new OrderByBorder("orderById", "id", dp));
        add(dataView);
        add(new PagingNavigator("navigator", dataView));
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
        setResponsePage(new AngebotTree(pageparameters));
    }
}
