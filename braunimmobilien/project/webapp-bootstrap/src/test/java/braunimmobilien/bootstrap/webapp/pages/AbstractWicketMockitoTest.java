package braunimmobilien.bootstrap.webapp.pages;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.mockito.Mock;  
import org.mockito.Mockito.*; 
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import braunimmobilien.service.*;
import braunimmobilien.webapp.person.SynchronizeContacts;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.model.*;
import org.junit.Before;
import braunimmobilien.bootstrap.webapp.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.servlet.ServletContext;
import braunimmobilien.webapp.person.*;
import org.apache.wicket.Application;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.wicket.spring.injection.annot.SpringBean;

	public abstract class AbstractWicketMockitoTest {
	
		@Mock Configuration configuration;;
		
		@Mock AngobjzuordManager angobjzuordManager;
		
		@Mock EigtstatusManager eigtstatusManager;
		
		@Mock SynchronizeContacts synchronizecontacts;
	   
		 @Mock MitarbeiterManager mitarbeiterManager;
			
		 @Mock OrteManager orteManager;
			
		 @Mock ObjektManager objektManager;
			
		 @Mock ObjektsuchManager objektsuchManager;
			
	    @Mock XtypManager xtypManager;
	    
	    @Mock TypeManager typeManager;
	    
	    @Mock ObjarttypManager objarttypManager;
	    
	    @Mock EigentuemermusterManager eigentuemermusterManager;
		 
	    @Mock ObjektartManager objektartManager;
	    
	    @Mock EntityLoader entityLoader;
	   
	    @Mock PersonManager personManager;
	
	    @Mock EigentuemertypManager eigentuemertypManager;
		 
	    @Mock KundeManager kundeManager;
		 
	    @Mock StrassenManager strassenManager;
			 
		@Mock ScoutManager scoutManager;
		 
		@Mock LandManager landManager;
		 
	    @Mock KonditionManager konditionManager;
	    
	    @Mock NachweiseManager nachweiseManager;
	    	 
	    @Mock AngebotManager angebotManager;
	       
	    @Mock AngstatusManager angstatusManager;
	       
	    
	    @Mock NutzerManager nutzerManager;
	    
	    @Mock TelefonartManager telefonartManager;
     
	    protected WicketTester tester = null;
	    private ApplicationContextMock acm=null;
	    @Before
	    public void setUp() throws Exception {
	    	acm = new ApplicationContextMock();
	    	acm.putBean("synchronizecontacts", synchronizecontacts);
	    	acm.putBean("eigtstatusManager", eigtstatusManager);
	    	acm.putBean("eigentuemermusterManager", eigentuemermusterManager);
			acm.putBean("landManager", landManager);
			acm.putBean("nutzerManager", nutzerManager);
			acm.putBean("angebotManager", angebotManager);
			acm.putBean("nachweiseManager", nachweiseManager);
			acm.putBean("scoutManager", scoutManager);
			acm.putBean("angobjzuordManager", angobjzuordManager);
			acm.putBean("objarttypManager", objarttypManager);
			acm.putBean("typeManager", typeManager);
			acm.putBean("kundeManager", kundeManager);
			acm.putBean("strassenManager", strassenManager);
			acm.putBean("personManager", personManager);
			acm.putBean("objektManager", objektManager);
			acm.putBean("eigentuemertypManager", eigentuemertypManager);
			acm.putBean("entityLoader", entityLoader);
			acm.putBean("objektsuchManager", objektsuchManager);
			acm.putBean("objektartManager", objektartManager);
			acm.putBean("orteManager", orteManager);
			acm.putBean("xtypManager", xtypManager);
			acm.putBean("mitarbeiterManager", mitarbeiterManager);
			acm.putBean("telefonartManager", telefonartManager);
			acm.putBean("Configuration", configuration);
			acm.putBean("angstatusManager", angstatusManager);
			acm.putBean("konditionManager", konditionManager);
			
			tester = new WicketTester(new WicketApplication(){
				/* (non-Javadoc)
				 * @see com.sampleapp.WicketApplication#getGuiceInjector()
				 */
				@Override
				protected SpringComponentInjector getSpringInjector() {
					return  new SpringComponentInjector(this, acm, true);
				}
			});   
			
			setupTest();
	    }
	 
	    /**
	     * Subclasses can use this method to provide the configuration needed by
	     * each test.
	     */
	    protected abstract void setupTest();
	 
	    /**
	     * Adds mock to the mock application context.
	     * @param beanName  The name of the mock bean.
	     * @param mock  The mock object.
	     */
	    protected void addMock(String beanName, Object mock) {
	        acm.putBean(beanName, mock);
	    }
	
	 
	    protected WicketTester getTester() {
	        return tester;
	    }
	}

