package braunimmobilien.bootstrap.webapp.panels.pagination;

import java.util.Arrays;
import java.util.List;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.BootstrapPagingNavigator;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.PageableListView;

/**
 * A demo panel for {@link BootstrapPagingNavigator}
 */
public class PaginationPanel extends AbstractPaginationPanel {
private List<String> data = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6");
private int size=2;   
public PaginationPanel(String id) {
        super(id);

        setOutputMarkupId(true);
    }
    public PaginationPanel(String id,List<String> data,int size) {
        super(id);
        this.data=data;
        this.size=size;
        setOutputMarkupId(true);
    }
    @Override
    protected Component createPager(String id) {
        return new BootstrapPagingNavigator(id, pageable);
    }
    @Override
    protected List<String> createData() {
        return  Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6");
    }
    @Override
    protected int pageSize() {
        return 2;
    }
    
    
}
