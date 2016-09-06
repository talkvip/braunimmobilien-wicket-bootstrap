package braunimmobilien.bootstrap.webapp.pages;


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
import braunimmobilien.bootstrap.webapp.pages.objekt.ObjektTree;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-dao.xml","classpath:applicationContext-resources.xml","classpath:applicationContext-service.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

public class HomePageTest{
    private WicketTester tester;
   
   
    @Autowired
    private ApplicationContext applicationContext;
 
  //  @Autowired
    private WicketApplication myWebApp;
 
   
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    
   
   
    @Before
    public void setUp() {  
    //	 MockitoAnnotations.initMocks(this);
    	myWebApp = new WicketApplication(); 
    	
    	myWebApp.setApplicationContext(applicationContext); 
    	
    	    tester = new WicketTester(myWebApp);
    //	    Serializer serializer = new Persister();
   // 	    ListOfStuff list = serializer.read(ListOfStuff.class, new ClassPathResource("listofstuff.xml").
   // 	                getInputStream());
    	 
  //  	    when(aService.getList()).thenReturn(list);
 //   File curDirectory = new File(System.getProperty("user.dir"));
//    File webContextDir = new File(curDirectory, "src/main/webapp");

  //  tester = new WicketTester(new WicketApplication(), webContextDir.getAbsolutePath());
    }
    @Test
    @Transactional
    @Rollback(true)
	public void homepageRendersSuccessfully()
	{
		tester.startPage(BraunHomePage.class);
		  tester.assertRenderedPage(SignInPage.class);
	        FormTester formTester = tester.newFormTester("intform");
	    	//set credentials
	        formTester.setValue("maklerUsername", "root");
	    	formTester.setValue("maklerPassword", "Braun");			
	    	//submit form
	    	formTester.submit();
	    	tester.assertRenderedPage(BraunHomePage.class);
	    	String responseTxt = tester.getLastResponse().getDocument();
	    	TagTester tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");
	    	Assert.assertNotNull(tagTester);
	      	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
	    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null", false);
	    	Assert.assertEquals(1, tagTesterList.size());
	   	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");

	    	Assert.assertNotNull(tagTester);
	      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
	    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null", false);
	    	Assert.assertEquals(1, tagTesterList.size());
	    	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage");

		    	Assert.assertNotNull(tagTester);
		      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
		    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage", false);
		    	Assert.assertEquals(1, tagTesterList.size());
		    	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.kunde.KundeSuch");

			    	Assert.assertNotNull(tagTester);
			      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
			    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.kunde.KundeSuch", false);
			    	Assert.assertEquals(1, tagTesterList.size());
			    	tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.repair.Repair");
			    	Assert.assertNotNull(tagTester);
			      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
			    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.repair.Repair", false);
			    	Assert.assertEquals(1, tagTesterList.size());
			   	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.subjects.SubjectsPage");

			    	Assert.assertNotNull(tagTester);
			      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
			    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.subjects.SubjectsPage", false);
			    	Assert.assertEquals(1, tagTesterList.size());
			    	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.parameters.Parameters");

				    	Assert.assertNotNull(tagTester);
				      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.parameters.Parameters", false);
				    	Assert.assertEquals(1, tagTesterList.size());
				    	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotWizardListPage");

					    	Assert.assertNotNull(tagTester);
					      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
					    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotWizardListPage", false);
					    	Assert.assertEquals(1, tagTesterList.size());
					    	tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");

					    	Assert.assertNotNull(tagTester);
					      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
					    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage", false);
					    	Assert.assertEquals(1, tagTesterList.size());
				
					    	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
			    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.subjects.SubjectsPage", false);
			    	Assert.assertEquals(1, tagTesterList.size());
			    	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.CalendarPage");

				    	Assert.assertNotNull(tagTester);
				      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.CalendarPage", false);
				    	Assert.assertEquals(1, tagTesterList.size());
				    	 tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.PortalDownloadPage");

					    	Assert.assertNotNull(tagTester);
					      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
					    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.PortalDownloadPage", false);
					    	Assert.assertEquals(1, tagTesterList.size());
					    	tagTester = TagTester.createTagByAttribute(responseTxt, "href","./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.einstellungen.EinstellungenPage");

					    	Assert.assertNotNull(tagTester);
					      	tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
					    					"href", "./wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.einstellungen.EinstellungenPage", false);
					    	Assert.assertEquals(1, tagTesterList.size());
				
	  
	}
    
    
  
    @After
    public void tearDown(){
    	//clear any side effect occurred during test.
    	tester.destroy();
    }
    
}
