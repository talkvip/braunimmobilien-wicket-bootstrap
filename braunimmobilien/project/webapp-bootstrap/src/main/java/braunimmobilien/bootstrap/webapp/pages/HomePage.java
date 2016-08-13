package braunimmobilien.bootstrap.webapp.pages;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import braunimmobilien.bootstrap.webapp.components.site.BootstrapFooter;
import braunimmobilien.bootstrap.webapp.components.site.Footer;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;

/**
 * The {@code HomePage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/bootstrap")
public class HomePage extends BasePage {

    /**
     * Construct.
     *
     * @param parameters the current page parameters.
     */
    public HomePage(PageParameters parameters) {
        super(parameters);
        add(new Footer("footer"));
     
        String version = getProperties().getProperty("bootstrap.fix.version");
        Label downloadButton = new Label("download-link", Model.of("Download <small>(" + version + ")</small>"));
        downloadButton.setEscapeModelStrings(false);
        downloadButton.add(new AttributeModifier("href", Model.of(getProperties().getProperty("bootstrap.downloadUrl"))));

        add(downloadButton);
    }
}
