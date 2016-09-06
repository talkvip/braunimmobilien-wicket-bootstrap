package braunimmobilien.bootstrap.webapp.pages;

import com.newrelic.api.agent.NewRelic;

import org.apache.wicket.Application;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapExternalLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.IeEdgeMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.*;
import de.agilecoders.wicket.core.markup.html.references.BootlintHeaderItem;
import de.agilecoders.wicket.core.markup.html.references.RespondJavaScriptReference;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.filter.FilteredHeaderItem;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.request.resource.PackageResourceReference;

import braunimmobilien.bootstrap.webapp.*;
import braunimmobilien.bootstrap.webapp.pages.einstellungen.EinstellungenPage;
import braunimmobilien.bootstrap.webapp.components.site.*;
import braunimmobilien.bootstrap.webapp.assets.base.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.wicket.request.resource.CssResourceReference;
/**
 * Base wicket-bootstrap {@link org.apache.wicket.Page}
 *
 * @author miha
 */
public abstract class BasePage extends GenericWebPage<Void> {
	
    /**
     * Construct.
     *
     * @param parameters current page parameters
     */
    public BasePage(final PageParameters parameters) {
       super(parameters);
       build();
    }
 
    private void build(){
        add(new HtmlTag("html"));

        add(new OptimizedMobileViewportMetaTag("viewport"));
        add(new IeEdgeMetaTag("ie-edge"));
        add(new MetaTag("description", Model.of("description"), Model.of("Apache Wicket & Bootstrap Demo")));
        add(new MetaTag("author", Model.of("author"), Model.of("Michael Haitz <michael.haitz@agile-coders.de>")));

        add(newNavbar("navbar"));
        add(newNavigation("navigation"));
       
       add(new BootstrapFooter("bootstrap-footer"));
      //  add(new Footer("footer"));

       add(new HeaderResponseContainer("footer-container", "footer-container"));
       add(new Code("code-internal"));
        // add new relic RUM scripts.
        add(new Label("newrelic", Model.of(NewRelic.getBrowserTimingHeader()))
                .setEscapeModelStrings(false)
                .setRenderBodyOnly(true)
                .add(new AttributeModifier("id", "newrelic-rum-header")));
        add(new Label("newrelic-footer", Model.of(NewRelic.getBrowserTimingFooter()))
                .setEscapeModelStrings(false)
                .setRenderBodyOnly(true)
                .add(new AttributeModifier("id", "newrelic-rum-footer")));
    }
    public BasePage() {
        super();
       build();
    }
       /*  add(new HtmlTag("html"));

         add(new OptimizedMobileViewportMetaTag("viewport"));
         add(new IeEdgeMetaTag("ie-edge"));
         add(new MetaTag("description", Model.of("description"), Model.of("Apache Wicket & Bootstrap Demo")));
         add(new MetaTag("author", Model.of("author"), Model.of("Michael Haitz <michael.haitz@agile-coders.de>")));

         add(newNavbar("navbar"));
         add(newNavigation("navigation"));
        
        add(new BootstrapFooter("bootstrap-footer"));
       //  add(new Footer("footer"));

        add(new HeaderResponseContainer("footer-container", "footer-container"));
        add(new Code("code-internal"));
         // add new relic RUM scripts.
         add(new Label("newrelic", Model.of(NewRelic.getBrowserTimingHeader()))
                 .setEscapeModelStrings(false)
                 .setRenderBodyOnly(true)
                 .add(new AttributeModifier("id", "newrelic-rum-header")));
         add(new Label("newrelic-footer", Model.of(NewRelic.getBrowserTimingFooter()))
                 .setEscapeModelStrings(false)
                 .setRenderBodyOnly(true)
                 .add(new AttributeModifier("id", "newrelic-rum-footer")));
     }*/
    /**
     * @return application properties
     */
    public Properties getProperties() {
        return WicketApplication.get().getProperties();
    }

    /**
     * creates a new {@link Navbar} instance
     *
     * @param markupId The components markup id.
     * @return a new {@link Navbar} instance
     */
    protected Navbar newNavbar(String markupId) {
        Navbar navbar = new Navbar(markupId);

        navbar.setPosition(Navbar.Position.TOP);
        navbar.setInverted(true);

        // show brand name
        navbar.setBrandName(Model.of("Braun Immobilien"));

        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
                        //    new NavbarButton<Void>(BaseCssPage.class, Model.of("Base CSS")),
                    //    new NavbarButton<Void>(ComponentsPage.class, Model.of("Components")),
                     //   new NavbarButton<Void>(PortalDownloadPage.class, Model.of("Portal Download")),
                   //     new NavbarButton<Void>(FormPage.class, Model.of("Braunimmobilienform")),
                  //      new NavbarExternalLink(Model.of("https://github.com/l0rdn1kk0n/wicket-bootstrap"))
                   //             .setLabel(Model.of("Github"))
                   //             .setTarget(BootstrapExternalLink.Target.blank)
                   //             .setIconType(GlyphIconType.export),
                       newAddonsDropDownButton(),
                       newNewBraunimmobilienDropDownButton()
                        )
        );
      
        NavbarButton button=new NavbarButton<Void>(HomePage.class, Model.of("Wicket & Bootstrap"));
        DropDownButton dropdown = new NavbarDropDownButton(Model.of("Themes")) {
            @Override
            public boolean isActive(Component item) {
                return false;
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
                subMenu.add(new MenuHeader(Model.of("all available themes:")));
                subMenu.add(new MenuDivider());

                final IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
                final List<ITheme> themes = settings.getThemeProvider().available();

                for (final ITheme theme : themes) {
                    PageParameters params = new PageParameters();
                    params.set("theme", theme.name());

                    subMenu.add(new MenuBookmarkablePageLink<Void>(getPageClass(), params, Model.of(theme.name())));
                }

                return subMenu;
            }
        }.setIconType(GlyphIconType.book);
       
        navbar.addComponents(new ImmutableNavbarComponent(dropdown, Navbar.ComponentPosition.RIGHT));
        navbar.addComponents(new ImmutableNavbarComponent(button, Navbar.ComponentPosition.RIGHT));
        return navbar;
    }

    /**
     * @return new dropdown button for all addons
     */
    private Component newAddonsDropDownButton() {
        return new NavbarDropDownButton(Model.of("Cloud Applications")) {
            /** serialVersionUID. */
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
                subMenu.add(new MenuBookmarkablePageLink<Void>(EinstellungenPage.class, Model.of("Einstellungen")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(PortalDownloadPage.class, Model.of("Portal Download Applications")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(CalendarPage.class, Model.of("Google Calendar Application")).setIconType(GlyphIconType.calendar));
                   subMenu.add(new MenuBookmarkablePageLink<Void>(DatePickerPage.class, Model.of("DatePicker")).setIconType(GlyphIconType.time));
                              subMenu.add(new MenuBookmarkablePageLink<Void>(DatetimePickerPage.class, Model.of("DateTimePicker")).setIconType(GlyphIconType.time));
                              /*     subMenu.add(new MenuBookmarkablePageLink<Void>(SelectPage.class, Model.of("SelectPicker")).setIconType(GlyphIconType.search));
                subMenu.add(new MenuBookmarkablePageLink<Void>(IssuesPage.class, Model.of("Github Issues")).setIconType(GlyphIconType.book));
                subMenu.add(new MenuBookmarkablePageLink<Void>(ExtensionsPage.class, Model.of("Extensions")).setIconType(GlyphIconType.alignjustify));
                subMenu.add(new MenuBookmarkablePageLink<Void>(ExtensionsBootstrapFileInputPage.class, Model.of("Extensions - Bootstrap FileInput")).setIconType(GlyphIconType.alignjustify));
                subMenu.add(new MenuBookmarkablePageLink<Void>(FontAwesomePage.class, Model.of("Font Awesome")).setIconType(GlyphIconType.font));
                subMenu.add(new MenuBookmarkablePageLink<Void>(XEditablePage.class, Model.of("X-Editable")).setIconType(GlyphIconType.pencil));
                subMenu.add(new MenuBookmarkablePageLink<Void>(TooltipValidationPage.class, Model.of("Validation")).setIconType(GlyphIconType.okcircle));*/
                return subMenu;
            }
        }.setIconType(GlyphIconType.thlarge);
    }
    private Component newNewBraunimmobilienDropDownButton() {
        return new NavbarDropDownButton(Model.of("Boostrap Braunimmobilien")) {
            /** serialVersionUID. */
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
                PageParameters StrassensuchObjektBreadcrumbPageParameters = new PageParameters();
                StrassensuchObjektBreadcrumbPageParameters.add("objid", "null");
                PageParameters StrassensuchPersonBreadcrumbPageParameters = new PageParameters();
                StrassensuchPersonBreadcrumbPageParameters.add("eigtid", "null");
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage.class, Model.of("AngebotsBreadcrunbliste")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.angebot.AngebotWizardListPage.class, Model.of("AngebotsWizardliste")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap.class, StrassensuchObjektBreadcrumbPageParameters,Model.of("StrassensuchObjektBreadcrumb")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap.class, StrassensuchPersonBreadcrumbPageParameters,Model.of("StrassensuchPersonBreadcrumb")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage.class, Model.of("StrassensuchWizard")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.kunde.KundeSuch.class, Model.of("KundeSuch")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch.class, Model.of("ScoutSuch")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.parameters.Parameters.class, Model.of("Parameters")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.subjects.SubjectsPage.class, Model.of("Subjects")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.repair.Repair.class, Model.of("Repair")).setIconType(GlyphIconType.downloadalt));
                  subMenu.add(new MenuBookmarkablePageLink<Void>(braunimmobilien.bootstrap.webapp.pages.WicketTesterPage.class, Model.of("Test")).setIconType(GlyphIconType.downloadalt));
                         //     subMenu.add(new MenuBookmarkablePageLink<Void>(DatetimePickerPage.class, Model.of("DateTimePicker")).setIconType(GlyphIconType.time));
                              /*     subMenu.add(new MenuBookmarkablePageLink<Void>(SelectPage.class, Model.of("SelectPicker")).setIconType(GlyphIconType.search));
                subMenu.add(new MenuBookmarkablePageLink<Void>(IssuesPage.class, Model.of("Github Issues")).setIconType(GlyphIconType.book));
                subMenu.add(new MenuBookmarkablePageLink<Void>(ExtensionsPage.class, Model.of("Extensions")).setIconType(GlyphIconType.alignjustify));
                subMenu.add(new MenuBookmarkablePageLink<Void>(ExtensionsBootstrapFileInputPage.class, Model.of("Extensions - Bootstrap FileInput")).setIconType(GlyphIconType.alignjustify));
                subMenu.add(new MenuBookmarkablePageLink<Void>(FontAwesomePage.class, Model.of("Font Awesome")).setIconType(GlyphIconType.font));
                subMenu.add(new MenuBookmarkablePageLink<Void>(XEditablePage.class, Model.of("X-Editable")).setIconType(GlyphIconType.pencil));
                subMenu.add(new MenuBookmarkablePageLink<Void>(TooltipValidationPage.class, Model.of("Validation")).setIconType(GlyphIconType.okcircle));*/
                return subMenu;
            }
        }.setIconType(GlyphIconType.thlarge);
    }
    /**
     * sets the theme for the current user.
     *
     * @param pageParameters current page parameters
     */
    private void configureTheme(PageParameters pageParameters) {
        StringValue theme = pageParameters.get("theme");

        if (!theme.isEmpty()) {
            IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
            settings.getActiveThemeProvider().setActiveTheme(theme.toString(""));
        }
    }

    protected ITheme activeTheme() {
        IBootstrapSettings settings = Bootstrap.getSettings(getApplication());

        return settings.getActiveThemeProvider().getActiveTheme();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        configureTheme(getPageParameters());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

//        response.render(CssHeaderItem.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
        response.render(new FilteredHeaderItem(JavaScriptHeaderItem.forReference(ApplicationJavaScript.INSTANCE), "footer-container"));
        response.render(RespondJavaScriptReference.headerItem());
        response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(BasePage.class,"bootstrap-treeview.js")));
        response.render(CssHeaderItem.forReference(new CssResourceReference(BasePage.class, "bootstrap-treeview.css")));
        response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(BasePage.class,"treeview.js")));
        if ("google".equalsIgnoreCase(activeTheme().name())) {
            response.render(CssHeaderItem.forReference(DocsCssResourceReference.GOOGLE));
        }

        if (!getRequest().getRequestParameters().getParameterValue("bootlint").isNull()) {
            response.render(BootlintHeaderItem.INSTANCE);
        }
    }

    protected boolean hasNavigation() {
        return false;
    }

    /**
     * creates a new navigation component.
     *
     * @param markupId The component's markup id
     * @return a new navigation component.
     */
    private Component newNavigation(String markupId) {
        WebMarkupContainer navigation = new WebMarkupContainer(markupId);
        navigation.add(new AffixBehavior("200"));
        navigation.setVisible(hasNavigation());

        return navigation;
    }
    protected void makeAction(String button,PageParameters pars){
		 WicketApplication app = (WicketApplication)Application.get();
		 app.makeAction(this, button, pars);
	}
    protected void SwitchComponentOnOff(PageParameters pars,Configuration configuration){
 	   WicketApplication app = (WicketApplication)Application.get();
 	  app.SwitchComponentOnOff(pars,this,configuration); 
   }
    protected void ReturnClass(String button,PageParameters pars,Configuration configuration){
 	   WicketApplication app = (WicketApplication)Application.get();
 	  app.ReturnClass(button,pars,this,configuration);
   } 
    public void Action(String button,PageParameters pars){
 	   if (!(pars.getPosition("context")>=0)) pars.set("context", "default");
       ((WicketApplication) Application.get())
       .makeAction(this,button,pars);
  
 	}
   
    
   
    
}

