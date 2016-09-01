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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-dao.xml","classpath:applicationContext-resources.xml","classpath:applicationContext-service.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

public class ObjektTest{
    private WicketTester tester;
    static Logger logger = LoggerFactory.getLogger(ObjektTest.class);
   
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
    public void searchObjektandCancelSearch(){
       	 tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 formTester.select("landmarkup:land", 0);
    	 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
         formTester.select("strassenmarkup:strasse", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
         tester.assertVisible("panel:form:objektemarkup:objekt");
        formTester.select("objektemarkup:objekt", 0);
         formTester.submit("cancelButton");
         tester.assertRenderedPage(BraunHomePage.class);
    } 
    
  @Test
    @Transactional
    @Rollback(true)
    public void searchObjektandBackSearch(){
       	 tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 formTester.select("landmarkup:land", 0);
    	 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
         formTester.select("strassenmarkup:strasse", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
         tester.assertVisible("panel:form:objektemarkup:objekt");
         formTester.submit("backButton");
         tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    } 
    
    
   @Test
    @Transactional
    @Rollback(true)
    public void searchObjektandStore(){
       	 tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 formTester.select("landmarkup:land", 0);
    	 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
         formTester.select("strassenmarkup:strasse", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
         tester.assertVisible("panel:form:objektemarkup:objekt");
        formTester.select("objektemarkup:objekt", 0);
         formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
         formTester.submit("backButton");
         tester.assertRenderedPage(ObjektTree.class);
    }
   
   @Test
    @Transactional
    @Rollback(true)
    public void searchObjektAndStoreAndAddPerson(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
      tester.assertVisible("panel:form:landmarkup:land");
	    formTester.select("landmarkup:land", 0);
			        tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
			          tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
           formTester.select("strassenmarkup:strasse", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
         tester.assertVisible("panel:form:objektemarkup:objekt");
         tester.assertInvisible("panel:form:personenmarkup:person");
         formTester.select("objektemarkup:objekt", 0);
         formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
         formTester.submit("nextButton");
         logger.debug("next SucheStrasse");
         tester.assertRenderedPage(IndexBootstrap.class);
    	 formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	  	 tester.assertVisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	   tester.assertVisible("panel:form:landmarkup:land");
    	formTester.select("eigentuemertypmarkup:eigentuemertyp", 0);
    	 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:eigentuemertypmarkup:eigentuemertyp").getBehaviors().get(0));
    	    
    	formTester.select("landmarkup:land", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
         formTester.select("strassenmarkup:strasse", 0);  
        tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
        tester.assertInvisible("panel:form:objektemarkup:objekt");
        tester.assertVisible("panel:form:personenmarkup:person");
        formTester.select("personenmarkup:person", 0);
         formTester.submit("nextButton");
       tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
        formTester.submit("backButton");
        tester.assertRenderedPage(ObjektTree.class);
    }
    
     @Test
    @Transactional
    @Rollback(true)
    public void searchObjektAndStoreAndAddPersonByFreeTextSearch(){
    	
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
         formTester.select("landmarkup:land", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
         formTester.select("strassenmarkup:strasse", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
         tester.assertVisible("panel:form:objektemarkup:objekt");
         tester.assertInvisible("panel:form:personenmarkup:person");
         formTester.select("objektemarkup:objekt", 0);
         formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
         formTester.submit("nextButton");
         tester.assertRenderedPage(IndexBootstrap.class);
    	 formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertVisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 tester.assertVisible("panel:form:textsearchmarkup:textsearch");
    	 formTester.select("eigentuemertypmarkup:eigentuemertyp", 0);
    	    tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:eigentuemertypmarkup:eigentuemertyp").getBehaviors().get(0));
    	 formTester.setValue("textsearchmarkup:textsearch", "Braun");
    	 
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:textsearchmarkup:textsearch").getBehaviors().get(0));
         tester.assertInvisible("panel:form:landmarkup:land");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
        tester.assertVisible("panel:form:personenmarkup:person");
        formTester.select("personenmarkup:person", 0);
         formTester.submit("nextButton");
       tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
        formTester.submit("backButton");
        tester.assertRenderedPage(ObjektTree.class);
    }
 
    @Test
    @Transactional
    @Rollback(true)
    public void createObjektAndStore(){
      
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
         formTester.select("landmarkup:land", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
         formTester.select("strassenmarkup:strasse", 0);
        tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
        tester.assertVisible("panel:form:objektemarkup:objekt");
         formTester.submit("nextButton");
       tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
        formTester.setValue("objhausnummer", "Ich weiß nicht wo 13");
        formTester.select("objektsuch", 0);
        formTester.select("objektart", 0);
        formTester.submit("backButton");
        tester.assertRenderedPage(ObjektTree.class);
    }
    
   @Test
    @Transactional
    @Rollback(true)
    public void createStrasseAndObjektAndStore(){
      
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
         formTester.select("landmarkup:land", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.select("ortemarkup:orte", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:ortemarkup:orte").getBehaviors().get(0));
         tester.assertVisible("panel:form:strassenmarkup:strasse");
         tester.assertInvisible("panel:form:objektemarkup:objekt");
         formTester.submit("nextButton");
         tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrasseInput");
        formTester.setValue("strname", "Ich weiß nicht wohin");
        formTester.setValue("strplz", "1234567");
        formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
        formTester.setValue("objhausnummer", "Ich weiß nicht wohin 13");
        formTester.select("objektsuch", 0);
        formTester.select("objektart", 0);
        formTester.submit("backButton");
        tester.assertRenderedPage(ObjektTree.class);
    }
   
    
    @Test
    @Transactional
    @Rollback(true)
    public void createStrasseAndOrtAndObjektAndStore(){
      
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
         formTester.select("landmarkup:land", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:landmarkup:land").getBehaviors().get(0));
         tester.assertVisible("panel:form:ortemarkup:orte");
         tester.assertInvisible("panel:form:strassenmarkup:strasse");
         formTester.submit("nextButton");
         tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"OrtInput");
        formTester.setValue("ortname", "Irgedwohin");
        formTester.setValue("ortplz", "1234567");
        formTester.submit("normalButton");
        tester.assertRenderedPage(IndexBootstrap.class);
        formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrasseInput");
         formTester.setValue("strname", "Ich weiß nicht wohin");
        formTester.setValue("strplz", "1234567");
        formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
        formTester.setValue("objhausnummer", "Ich weiß nicht wohin 13");
        formTester.select("objektsuch", 0);
        formTester.select("objektart", 0);
        formTester.submit("backButton");
        tester.assertRenderedPage(ObjektTree.class);
    }
   
    @Test
    @Transactional
    @Rollback(true)
    public void createStrasseAndOrtAndLandAndObjektAndStore(){
      
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?objid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 formTester.submit("nextButton");
    	  tester.assertRenderedPage(IndexBootstrap.class);
          formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"LandInput");
         formTester.setValue("landname", "Nirgendwohin");
         formTester.setValue("kennzeichen", "Ni");
         formTester.submit("normalButton");
         tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"OrtInput");
        formTester.setValue("ortname", "Irgedwohin");
        formTester.setValue("ortplz", "1234567");
        formTester.submit("normalButton");
        tester.assertRenderedPage(IndexBootstrap.class);
        formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrasseInput");
         formTester.setValue("strname", "Ich weiß nicht wohin");
        formTester.setValue("strplz", "1234567");
        formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
        Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
        formTester.setValue("objhausnummer", "Ich weiß nicht wohin 13");
        formTester.select("objektsuch", 0);
        formTester.select("objektart", 0);
        formTester.submit("backButton");
        tester.assertRenderedPage(ObjektTree.class);
    } 
    @After
    public void tearDown(){
    	//clear any side effect occurred during test.
    	tester.destroy();
    }
    
}