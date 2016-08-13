package braunimmobilien.bootstrap.webapp.pages;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.mockito.Mock;  
import org.mockito.Mockito.*; 
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.NutzerManager;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.EntityLoader;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.XtypManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.MitarbeiterManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.EigentuemertypManager;

import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Land;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Nutzer;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Strassen;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.servlet.ServletContext;

import org.apache.wicket.spring.injection.annot.SpringComponentInjector;


	public abstract class AbstractWicketMockitoTest {
	
		@Mock AngobjzuordManager angobjzuordManager;
		
	    private static Orte  ORT= new Orte();
	  
	    @Mock MitarbeiterManager mitarbeiterManager;
	    
	    @Mock XtypManager xtypManager;
	    
	    @Mock OrteManager orteManager;
	    
	    @Mock ObjektManager objektManager;
		 
	    @Mock ObjektsuchManager objektsuchManager;
		 
	    @Mock ObjektartManager objektartManager;
	    
	    @Mock EntityLoader entityLoader;
	   
	    @Mock PersonManager personManager;
	
	    @Mock EigentuemertypManager eigentuemertypManager;
		 
	    @Mock KundeManager kundeManager;
		 
	    @Mock StrassenManager strassenManager;
			 
		@Mock ScoutManager scoutManager;
		 
		@Mock LandManager landManager;
		 
	    @Mock NachweiseManager nachweiseManager;
	    	 
	    @Mock AngebotManager angebotManager;
	       
	    @Mock NutzerManager nutzerManager;

	       
	    protected WicketTester tester = null;
	    private ApplicationContextMock acm=null;
	    @Before
	    public void setUp() throws Exception {
	    	acm = new ApplicationContextMock();
			
			acm.putBean("landManager", landManager);
			acm.putBean("nutzerManager", nutzerManager);
			acm.putBean("angebotManager", angebotManager);
			acm.putBean("nachweiseManager", nachweiseManager);
			acm.putBean("scoutManager", scoutManager);
			acm.putBean("angobjzuordManager", angobjzuordManager);
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

