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

import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotTree;
import braunimmobilien.bootstrap.webapp.pages.auth.SignInPage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.StrassenSucheForm;
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
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
import braunimmobilien.model.*;
import org.apache.wicket.markup.html.form.DropDownChoice;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-dao.xml","classpath:applicationContext-resources.xml","classpath:applicationContext-service.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

public class PersonenTest{
    private WicketTester tester;
    static Logger logger = LoggerFactory.getLogger(PersonenTest.class);
   
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
    public void search_Cancel_Search(){
       	 tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");	
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
         tester.assertVisible("panel:form:personenmarkup:person");
         formTester.select("personenmarkup:person", 0);
         formTester.submit("cancelButton");
         tester.assertRenderedPage(BraunHomePage.class);
    } 
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_Person_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");	
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
         tester.assertVisible("panel:form:personenmarkup:person");
         formTester.select("personenmarkup:person", 0);
       
         formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
         formTester.submit("backButton");
         tester.assertRenderedPage(PersonTree.class);
         String responseTxt = tester.getLastResponse().getDocument();
   
 		TagTester  	tagTester=null;	
 		int j=0;
 		int i=0;
 		 outerloop:
 			for (i=0;i<100;i++){
 				for (j=0;j<100;j++){
 		    tagTester = TagTester.createTagByAttribute(responseTxt, "href","./persontree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&eigtid="+j+"&telefon=on");
 		    if (tagTester!=null) break outerloop;}}
 			 Assert.assertNotNull(tagTester);
 			tester.executeUrl("./persontree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&amp;eigtid="+j+"&telefon=on");	
 			tester.assertRenderedPage(IndexBootstrap.class);
 			formTester = tester.newFormTester("panel:form"); 
 			  Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
         
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void new_Person_With_Preset_location_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");	
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
         tester.assertVisible("panel:form:personenmarkup:person");
        formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
         formTester.setValue("eigtHausnummer",formTester.getTextComponentValue("eigtHausnummer")+" 12");
         formTester.setValue("eigtName","Frank Fizzlipuzzli");
         formTester.setValue("eigtAnschrift","Herrn Frank Fizzlipuzzli");
         formTester.setValue("eigtBriefanrede","Frank Fizzlipuzzli");
         formTester.select("eigtstatus", 0);
             formTester.submit("backButton");
         tester.assertRenderedPage(PersonTree.class);
         String responseTxt = tester.getLastResponse().getDocument();
         System.err.println(responseTxt);
 		TagTester  	tagTester=null;	
 		int j=0;
 		int i=0;
 		 outerloop:
 			for (i=0;i<100;i++){
 				for (j=0;j<100;j++){
 		    tagTester = TagTester.createTagByAttribute(responseTxt, "href","./persontree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&eigtid="+j+"&telefon=on");
 		    if (tagTester!=null) break outerloop;}}
 			 Assert.assertNotNull(tagTester);
 			tester.executeUrl("./persontree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&amp;eigtid="+j+"&telefon=on");	
 			tester.assertRenderedPage(IndexBootstrap.class);
 			formTester = tester.newFormTester("panel:form"); 
 			  Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
 			 tester.clickLink("panel:form:telefone:modeLink");
 			formTester = tester.newFormTester("panel:form:telefone:content:form"); 
 			formTester.submit("newButton");
  			formTester = tester.newFormTester("panel:form:telefone:content:form"); 
  			formTester.select("art",0);
  			 formTester.setValue("telefon", "0511314567789");
  			 
  			formTester.submit("saveButton");
  			tester.assertRenderedPage(IndexBootstrap.class);
  		
  			formTester = tester.newFormTester("panel:form");
  			Assert.assertEquals(formTester.getTextComponentValue("eigtName"),"Frank Fizzlipuzzli");
  			Assert.assertTrue(formTester.getTextComponentValue("eigtTelefone").length()==0);
  			Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
  		  DropDownChoice<Eigtstatus> eigtstatus = 
  				(DropDownChoice<Eigtstatus>)tester.getComponentFromLastRenderedPage("panel:form:eigtstatus");
  				    Assert.assertEquals(2, eigtstatus.getChoices().size());
  				    formTester.setValue("eigtAktuell", "on");
  			 formTester.select("eigtstatus", 0);
  			
  			
  			 formTester.submit("backButton");
  	
  		      tester.assertRenderedPage(PersonTree.class);
  		    		        responseTxt = tester.getLastResponse().getDocument();
   		 outerloop1:
   			for (i=0;i<100;i++){
   				for (j=0;j<100;j++){
   		    tagTester = TagTester.createTagByAttribute(responseTxt, "href","./persontree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&eigtid="+j+"&telefon=on");
   		    if (tagTester!=null) break outerloop1;}}
   			 Assert.assertNotNull(tagTester);
   			tester.executeUrl("./persontree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&amp;eigtid="+j+"&telefon=on");	
   			tester.assertRenderedPage(IndexBootstrap.class);
   			formTester = tester.newFormTester("panel:form"); 
   			Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
   			Assert.assertTrue(formTester.getTextComponentValue("eigtTelefone").length()>0);
   			
   		   	
    }
    
    
   @Test
    @Transactional
    @Rollback(true)
    public void search_By_Text_ShowPerson_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 tester.assertVisible("panel:form:textsearchmarkup:textsearch");
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
         tester.assertRenderedPage(PersonTree.class);
    }
   
    
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_By_Text_ShowPerson_Change_Location_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");	
    	 tester.assertRenderedPage(IndexBootstrap.class);
    	 FormTester formTester = tester.newFormTester("panel:form");  	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	 tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 tester.assertVisible("panel:form:textsearchmarkup:textsearch");
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
         tester.assertVisible("panel:form:locationButton");
      	 tester.assertLabel("panel:form:strasse.id", "1");
      	formTester.submit("locationButton");
        tester.assertRenderedPage(IndexBootstrap.class);
        formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
    	
    	 tester.assertInvisible("panel:form:ortemarkup:orte");
    	tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
    	 tester.assertVisible("panel:form:landmarkup:land");
    	 tester.assertInvisible("panel:form:textsearchmarkup:textsearch"); 
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
    	 formTester.setValue("strname","new strasse");
    	 formTester.setValue("strplz","new plz");
    	 formTester.setValue("strhausbereich","no exclusions");
    	 formTester.setValue("planquadrat","a5");
    	 formTester.setValue("merkmal",new Boolean(true));
    	 	 formTester.submit("nextButton");
    		 tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
    	
    	Assert.assertNotEquals(tester.getComponentFromLastRenderedPage("panel:form:strasse.id").getDefaultModelObjectAsString(),"1");
    	 formTester.submit("backButton");
        tester.assertRenderedPage(PersonTree.class);
    }
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_Person_Next_Show_Person_Next_New_Kunde_Next_New_Nachweis_Next_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");	
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
         tester.assertVisible("panel:form:personenmarkup:person");
         formTester.select("personenmarkup:person", 0);
         formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
         formTester.submit("nextButton");
         tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");  	 
    	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"KundeInput");
    	 formTester.select("status", 0);
    	 formTester.select("kundenart", 0);
    	 formTester.submit("nextButton");
    	  tester.assertRenderedPage(IndexBootstrap.class);
          formTester = tester.newFormTester("panel:form");
          Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"NachweisInput");
          formTester.select("angebot", 0);
          formTester.select("mitarbeiter", 0);
          formTester.select("xtyp", 0);
          formTester.submit("nextButton");
          tester.assertRenderedPage(IndexBootstrap.class);
          formTester = tester.newFormTester("panel:form"); 
          Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"NachweisInput");
          formTester.submit("backButton");
          tester.assertRenderedPage(PersonTree.class);
    }
  
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_Person_By_Adress_choose_Kunde_insert_new_Nachweis_store(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap?eigtid=null");	
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
         tester.assertVisible("panel:form:personenmarkup:person");
         formTester.select("personenmarkup:person", 0);
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:person").getBehaviors().get(0));
         formTester.select("personenmarkup:kunden", 0);  
         tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:kunden").getBehaviors().get(0));  
         formTester.submit("nextButton");
        tester.assertRenderedPage(IndexBootstrap.class);
         formTester = tester.newFormTester("panel:form");
         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"KundeInput");
         formTester.submit("nextButton");
    	  tester.assertRenderedPage(IndexBootstrap.class);
          formTester = tester.newFormTester("panel:form");
          Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"NachweisInput");
          formTester.select("angebot", 0);
          formTester.select("mitarbeiter", 0);
          formTester.select("xtyp", 0);
          formTester.submit("nextButton");
          tester.assertRenderedPage(IndexBootstrap.class);
          formTester = tester.newFormTester("panel:form"); 
          Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"NachweisInput");
          formTester.submit("backButton");
          tester.assertRenderedPage(PersonTree.class);
    }
    @After
    public void tearDown(){
    	//clear any side effect occurred during test.
    	tester.destroy();
    }
    
}
