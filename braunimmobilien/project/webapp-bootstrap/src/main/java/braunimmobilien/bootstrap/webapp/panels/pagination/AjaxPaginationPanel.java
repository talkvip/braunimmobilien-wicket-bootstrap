package braunimmobilien.bootstrap.webapp.panels.pagination;

import java.util.Arrays;
import java.util.List;

import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;

import org.apache.wicket.Component;

/**
 * A demo panel for BootstrapAjaxPagingNavigator
 */
public class AjaxPaginationPanel extends AbstractPaginationPanel {

    public AjaxPaginationPanel(String id) {
        super(id);

        setOutputMarkupId(true);
    }

    @Override
    protected Component createPager(String id) {
        return new BootstrapAjaxPagingNavigator(id, pageable);
    }
    @Override
    protected List<String> createData() {
        return Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6");
    }
    @Override
    protected int pageSize() {
        return 2;
    }
}
