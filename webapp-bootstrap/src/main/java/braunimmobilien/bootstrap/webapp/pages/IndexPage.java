package braunimmobilien.bootstrap.webapp.pages;

import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import braunimmobilien.service.impl.EntityLoaderImpl;
public class IndexPage extends BasePage {
	
	
	public IndexPage() {
	super(new PageParameters());
        /*add(new Link("angstatusLink") {
            @Override
            public void onClick() {
                setResponsePage(new AngstatusList());
            }
        });*/
        add(new Link("angeboteLink") {
            @Override
            public void onClick() {
                setResponsePage(new AngebotBreadcrumbPage(new PageParameters()));
            }
        });
        
    }
}
