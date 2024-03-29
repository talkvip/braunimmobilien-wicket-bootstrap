package braunimmobilien.bootstrap.webapp.pages;


import braunimmobilien.bootstrap.webapp.EntityModel;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.hibernate.SessionFactory;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.File;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.TestExecutionListeners;
import javax.servlet.ServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import braunimmobilien.bootstrap.webapp.pages.auth.SignInPage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.StrassenSucheForm;
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch;

import org.apache.wicket.util.tester.TagTester;
import org.junit.Assert;
import java.util.List;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-dao.xml","classpath:applicationContext-resources.xml","classpath:applicationContext-service.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

public class StrassenSuchWizardTest{
    private WicketTester tester;
    static Logger logger = LoggerFactory.getLogger(StrassenSuchWizardTest.class);
   
    @Autowired
    private ApplicationContext applicationContext;
 
 
    private WicketApplication myWebApp;
 
    protected final Log log = LogFactory.getLog(getClass());
    
   
   
    @Before
    public void setUp() {  
    	myWebApp = new WicketApplication(); 
    	
    	myWebApp.setApplicationContext(applicationContext); 
    	
    	    tester = new WicketTester(myWebApp);
     	    tester.startPage(BraunHomePage.class);
            FormTester formTester = tester.newFormTester("intform");
        	formTester.setValue("maklerUsername", "root");
        	formTester.setValue("maklerPassword", "Braun");		
        	formTester.submit();
    }
   
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void searchObjektandStore(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage");	
       	tester.assertRenderedPage(WizardBootstrapPage.class);
    }
   
  
    @After
    public void tearDown(){
    	//clear any side effect occurred during test.
    	tester.destroy();
    }
    
}
