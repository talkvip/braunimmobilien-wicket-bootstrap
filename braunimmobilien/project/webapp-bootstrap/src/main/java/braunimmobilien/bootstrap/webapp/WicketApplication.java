package braunimmobilien.bootstrap.webapp;

import com.google.gdata.client.contacts.ContactsService;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.gdata.client.contacts.ContactsService;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import java.util.Locale;
import org.apache.wicket.MarkupContainer;
import com.google.api.services.calendar.Calendar;
import java.util.LinkedHashMap;
import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.RenderJavaScriptToFooterHeaderResponseDecorator;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyCssResourceReference;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.prettyprint.PrettifyJavaScriptReference;
import de.agilecoders.wicket.core.markup.html.references.ModernizrJavaScriptReference;
import de.agilecoders.wicket.core.request.resource.caching.version.Adler32ResourceVersion;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.javascript.GoogleClosureJavaScriptCompressor;
import de.agilecoders.wicket.extensions.javascript.YuiCssCompressor;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5PlayerJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUICoreJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIDraggableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIMouseJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIResizableJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.jqueryui.JQueryUIWidgetJavaScriptReference;
import de.agilecoders.wicket.extensions.request.StaticResourceRewriteMapper;
import de.agilecoders.wicket.less.BootstrapLess;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.bootstrap.webapp.assets.base.ApplicationJavaScript;
import braunimmobilien.bootstrap.webapp.assets.base.FixBootstrapStylesCssResourceReference;
import braunimmobilien.bootstrap.webapp.pages.BraunHomePage;
import braunimmobilien.bootstrap.webapp.pages.auth.SignInPage;
import braunimmobilien.bootstrap.webapp.pages.tree.MyFoo;
import braunimmobilien.bootstrap.webapp.MaklerAuthorizationStrategy;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import net.ftlines.wicketsource.WicketSource;
import java.util.Set;
import com.google.api.services.gmail.Gmail;



import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.ResourceBundles;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.IPackageResourceGuard;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.CachingResourceVersion;
import org.apache.wicket.serialize.java.DeflatedJavaSerializer;
import org.apache.wicket.settings.IRequestCycleSettings;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import org.apache.wicket.Component;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.BeansException;
/**
 * Demo Application instance.
 */
@org.springframework.stereotype.Component
public class WicketApplication extends WebApplication implements ApplicationContextAware {
	ContactsService contactservice=null;
    public ContactsService getContactsService() {
		return contactservice;
	}
	public void setContactsService(ContactsService contactservice) {
		this.contactservice = contactservice;
	}
	public Calendar getCalendarService() {
		return calendarservice;
	}
	public void setCalendarService(Calendar calendarservice) {
		this.calendarservice = calendarservice;
	}
	public Gmail getGmailService() {
		return gmailservice;
	}
	public void setGmailService(Gmail gmailservice) {
		this.gmailservice = gmailservice;
	}
	Calendar calendarservice=null;
    Gmail gmailservice=null;
	
	
	
	private Properties properties;
    public static List<MyFoo> foos = new ArrayList<MyFoo>();
    /**
     * Get Application for current thread.
     *
     * @return The current thread's Application
     */
    public static final List<Locale> LOCALES = Arrays.asList(
			new Locale("nl", "NL"),Locale.ENGLISH, Locale.GERMAN, Locale.SIMPLIFIED_CHINESE, Locale.JAPANESE,
			new Locale("pt", "BR"), new Locale("fa", "IR"), new Locale("da", "DK"),
			new Locale("th", "TH"), new Locale("ru"), new Locale("ko", "KR"));
	
    private ApplicationContext ctx;
    
 /*   public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }*/

    /**
     * Constructor.
     */
    public WicketApplication() {
        super();

        properties = loadProperties();
        setConfigurationType(RuntimeConfigurationType.valueOf(properties.getProperty("configuration.type")));
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return BraunHomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    } 
    
    protected SpringComponentInjector getSpringInjector() {
    	return new SpringComponentInjector(this);
    } 
    
    @Override
    public void init() {
    	if(ctx==null)
    		getComponentInstantiationListeners().add(getSpringInjector());
    	else getComponentInstantiationListeners().add(new SpringComponentInjector(this,ctx,true));
        super.init();

        getApplicationSettings().setUploadProgressUpdatesEnabled(true);
 
        // deactivate ajax debug mode
        getDebugSettings().setAjaxDebugModeEnabled(false);

        configureBootstrap();
//        configureResourceBundles();

        optimizeForWebPerformance();

        new AnnotatedMountScanner().scanPackage("braunimmobilien.bootstrap.webapp.pages").mount(this);

        if (Strings.isTrue(properties.getProperty("cdn.useCdn"))) {
            final String cdn = properties.getProperty("cdn.baseUrl");

            StaticResourceRewriteMapper.withBaseUrl(cdn).install(this);
        }

        IPackageResourceGuard packageResourceGuard = getResourceSettings().getPackageResourceGuard();
        if (packageResourceGuard instanceof SecurePackageResourceGuard) {
            SecurePackageResourceGuard securePackageResourceGuard = (SecurePackageResourceGuard) packageResourceGuard;
            securePackageResourceGuard.addPattern("+*.woff2");
            securePackageResourceGuard.addPattern("+*.xslt");
            securePackageResourceGuard.addPattern("+*.xsl");
            securePackageResourceGuard.addPattern("+*.xsd");
            securePackageResourceGuard.addPattern("+*.gif");
        }
        MaklerAuthorizationStrategy maklerStrat = new MaklerAuthorizationStrategy(BasePage.class,SignInPage.class);
        getSecuritySettings().setAuthorizationStrategy(maklerStrat);
        WicketSource.configure(this);
    }

    /**
     * optimize wicket for a better web performance
     */
    private void optimizeForWebPerformance() {
        if (usesDeploymentConfig()) {
            getResourceSettings().setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(
                    "-v-",
                    new CachingResourceVersion(new Adler32ResourceVersion())
            ));

            getResourceSettings().setJavaScriptCompressor(new GoogleClosureJavaScriptCompressor(CompilationLevel.SIMPLE_OPTIMIZATIONS));
            getResourceSettings().setCssCompressor(new YuiCssCompressor());

            getFrameworkSettings().setSerializer(new DeflatedJavaSerializer(getApplicationKey()));
        } else {
            getResourceSettings().setCachingStrategy(new NoOpResourceCachingStrategy());
        }

        setHeaderResponseDecorator(new RenderJavaScriptToFooterHeaderResponseDecorator());
        getRequestCycleSettings().setRenderStrategy(IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
    }

    /**
     * configure all resource bundles (css and js)
     */
    private void configureResourceBundles() {
        ResourceBundles bundles = getResourceBundles();
        bundles.addJavaScriptBundle(WicketApplication.class, "core.js",
                                    (JavaScriptResourceReference) getJavaScriptLibrarySettings().getJQueryReference(),
                                    (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketEventReference(),
                                    (JavaScriptResourceReference) getJavaScriptLibrarySettings().getWicketAjaxReference(),
                                    ModernizrJavaScriptReference.instance()
        );

        bundles.addJavaScriptBundle(WicketApplication.class, "bootstrap.js",
                                    (JavaScriptResourceReference) Bootstrap.getSettings().getJsResourceReference(),
                                    (JavaScriptResourceReference) PrettifyJavaScriptReference.INSTANCE,
                                    ApplicationJavaScript.INSTANCE
        );
     
        getResourceBundles().addJavaScriptBundle(WicketApplication.class, "bootstrap-extensions.js",
                                                 JQueryUICoreJavaScriptReference.instance(),
                                                 JQueryUIWidgetJavaScriptReference.instance(),
                                                 JQueryUIMouseJavaScriptReference.instance(),
                                                 JQueryUIDraggableJavaScriptReference.instance(),
                                                 JQueryUIResizableJavaScriptReference.instance(),
                                                 Html5PlayerJavaScriptReference.instance()
        );

        bundles.addCssBundle(WicketApplication.class, "bootstrap-extensions.css",
                             Html5PlayerCssReference.instance(),
                             OpenWebIconsCssReference.instance()
        );

        bundles.addCssBundle(WicketApplication.class, "application.css",
                             (CssResourceReference) PrettifyCssResourceReference.INSTANCE,
                             FixBootstrapStylesCssResourceReference.INSTANCE
        );
    }

    /**
     * configures wicket-bootstrap and installs the settings.
     */
    private void configureBootstrap() {
        final IBootstrapSettings settings = new BootstrapSettings();
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Flatly);
        
        settings.setJsResourceFilterName("footer-container")
                .setThemeProvider(themeProvider);

        Bootstrap.install(this, settings);
        BootstrapLess.install(this);
    }

    /**
     * @return used configuration properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * loads all configuration properties from disk
     *
     * @return configuration properties
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream stream = getClass().getResourceAsStream("/config.properties");
            try {
                properties.load(stream);
            } finally {
                IOUtils.closeQuietly(stream);
            }
        } catch (IOException e) {
            throw new WicketRuntimeException(e);
        }
        return properties;
    }
    public static MyFoo getFoo(String id)
	{
		return findFoo(foos, id);
	}

	private static MyFoo findFoo(List<MyFoo> foos, String id)
	{
		for (MyFoo foo : foos)
		{
			if (foo.getTreeid().equals(id))
			{
				return foo;
			}

			MyFoo temp = findFoo(foo.getFoos(), id);
			if (temp != null)
			{
				return temp;
			}
		}

		return null;
	} 
	public Session newSession(Request request, Response response)
	{
		MaklerSession session = new MaklerSession(request,response);
		Locale locale = session.getLocale();
		if (!LOCALES.contains(locale))
		{
			session.setLocale(Locale.ENGLISH);
		}
		return session;
	} 
	
	  public static WicketApplication get()
		{
		  WebApplication webApplication = WebApplication.get();

			if (webApplication instanceof WicketApplication == false)
			{
				throw new WicketRuntimeException(
					"The application attached to the current thread is not a " +
					WicketApplication.class.getSimpleName());
			}

			return (WicketApplication) webApplication;
		}
	/*   Flow Steuerung */
	  
	  
	  public void ReturnClass(String button,PageParameters pars,MarkupContainer orig,Configuration configuration){
		  System.err.println("ReturnClassCentral button "+button+" PageParameters "+pars+" this "+orig.getId()+" Path "+orig.getPath()+" rel Path "+orig.getPageRelativePath()+" String "+this.toString()+" Classpath "+orig.getClassRelativePath()+" Class "+orig.getClass().getName()+" Classsimple "+orig.getClass().getSimpleName()+" Classcanonical "+orig.getClass().getCanonicalName());
		  PageParameters parsdanach=NewPageParameters(button,pars,orig,configuration);
		  if (!(pars.getPosition("context")>=0)) pars.set("context", "default");
		   
		  if(((LinkedHashMap) configuration.getSearchReturnClass()).containsKey(orig.getClass().getSimpleName())){
			  System.err.println("getSearchReturnClass Class SimpleName"+((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName()).toString());
			  System.err.println("getSearchReturnClass newParameters "+parsdanach);
			  if(((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).containsKey("all")){ 
				
				
				  if(((LinkedHashMap)((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).get("all")).containsKey(button)){
					
				String clazz=	  ((LinkedHashMap)((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).get("all")).get(button).toString();
					try{	
				Class c = Class.forName(clazz);
				 orig.setResponsePage(c,parsdanach);
					
					}
					catch(ClassNotFoundException ex){
						System.err.println("not found "+ex);
						ex.printStackTrace();
							}	
				  }  
				  
				  
			  
			  }
			  else{  if(((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).containsKey(pars.get("context").toString())){  
				  System.err.println("context "+((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).get(pars.get("context").toString()));
					  if(((LinkedHashMap)((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).get(pars.get("context").toString())).containsKey(button)){
						  System.err.println("button "+((LinkedHashMap)((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).get(pars.get("context").toString())).get(button).toString());
					String clazz=	  ((LinkedHashMap)((LinkedHashMap)((LinkedHashMap) configuration.getSearchReturnClass()).get(orig.getClass().getSimpleName())).get(pars.get("context").toString())).get(button).toString();
						try{	
					Class c = Class.forName(clazz);
					 orig.setResponsePage(c,parsdanach);
						
						}
						catch(ClassNotFoundException ex){
							System.err.println("not found "+ex);
							ex.printStackTrace();
								}	
					  }
			}
				
			}
				  }
			 

	 }    
	  
	
		public void SwitchComponentOnOff(PageParameters pars,MarkupContainer orig,Configuration configuration) {
		 	 
				
			
			  Iterator it=orig.visitChildren().iterator();
				while(it.hasNext()){
					Component comp= (Component)it.next();
				
					if(((LinkedHashMap)configuration.getSwitchoffTable()).containsKey(orig.getClass().getSimpleName())){
				
					if(((LinkedHashMap)((LinkedHashMap)configuration.getSwitchoffTable()).get(orig.getClass().getSimpleName())).containsKey(pars.get("context").toString())){
				
					if(((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getSwitchoffTable()).get(orig.getClass().getSimpleName())).get(pars.get("context").toString())).containsKey(comp.getId().toString())){
				
						if (((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getSwitchoffTable()).get(orig.getClass().getSimpleName())).get(pars.get("context").toString())).get(comp.getId().toString()).toString().equals("on")) comp.setVisible(true);
						if (((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getSwitchoffTable()).get(orig.getClass().getSimpleName())).get(pars.get("context").toString())).get(comp.getId().toString()).toString().equals("off")) comp.setVisible(false);
					}}
					}
				}
		}  
		public void makeAction(MarkupContainer orig,String button,PageParameters pars){
			System.err.println("yyyyyyyyyyyyyyyyyyyyyyyy "+button+" "+orig.getClass().getSimpleName());
			if (!(pars.getPosition("context")>=0)) pars.set("context", "default");
			  if(orig.getClass().getSimpleName().equals("StrassenSucheForm")){
				//	makeActionStrassenSucheForm(button,pars);
				 }	 
			  if(orig.getClass().getSimpleName().equals("TextSucheForm")){
				//  makeActionSucheTextForm(button,pars);
				 }	
			  if(orig.getClass().getSimpleName().equals("ObjekteMainPanel")){
				//  makeActionObjekteMainPanel(button,pars);
				 }	
			  if(orig.getClass().getSimpleName().equals("MainPanel")){
				 System.err.println("xxxxxxxxxxxxxxxxxxxxx makeActionPersonenMainPanel");
				//  makeActionPersonenMainPanel(button,pars);
				 }	
			  
		}	  
		private       PageParameters NewPageParameters(String buttonname,PageParameters pars,MarkupContainer orig,Configuration configuration) {
			   if (!(pars.getPosition("context")>=0)) pars.set("context", "default");
			   System.err.println("NewPageParametersCentral Class "+orig.getClass().getSimpleName()+" PageParameters "+pars+" configuration "+configuration);
			   if ( ((LinkedHashMap)configuration.getNewPageParameters()).containsKey(orig.getClass().getSimpleName())){
				   System.err.println("Configuration  "+orig.getClass().getSimpleName()+" "+((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName()));
				   if ( ((LinkedHashMap)((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName())).containsKey(buttonname)){
		  		 System.err.println("Configuration "+orig.getClass().getSimpleName()+" "+buttonname+" "+((LinkedHashMap)((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName())).get(buttonname));
		 		  if ((pars.getPosition("context")>=0)&&  ((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName())).get(buttonname)).containsKey("all")){
		 			  if ( ((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName())).get(buttonname)).get("all")).containsKey("all")){
		 			  return pars;
		 			  }  
		 			  return null;
		 		  }
		 		  else{  if ((pars.getPosition("context")>=0)&&  ((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName())).get(buttonname)).containsKey(pars.get("context").toString())){
		  			 System.err.println("Configuration "+orig.getClass().getSimpleName()+" "+buttonname+" "+pars.get("context").toString()+" "+((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName())).get(buttonname)).get(pars.get("context").toString())); 
		  			 PageParameters pars2=new PageParameters();
		  			 LinkedHashMap linkedmap=(LinkedHashMap)((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)configuration.getNewPageParameters()).get(orig.getClass().getSimpleName())).get(buttonname)).get(pars.get("context").toString()); 
		  		  	  Set keyset=linkedmap.keySet();
		  		  	  Iterator it=keyset.iterator();
		  		  	  while(it.hasNext()){
		  		  		  String key = (String)it.next();
		  		  		  System.err.println("Configuration key "+key+" get(key) "+pars.get(key).toString()+"linkedmap.get(key) "+linkedmap.get(key));
		  		  		  if(linkedmap.get(key).toString().length()==0) pars2.set(key,pars.get(key).toString());
		  		  		  else pars2.set(key,linkedmap.get(key).toString());
		  		  	  }
		 		 
		  		  	System.err.println("NewPageParametersCentral parsdanach "+pars2);
		  			 return pars2;
		  			 
		   }
		  		  }
			   }
			   }
		  return pars;
		}  


}
