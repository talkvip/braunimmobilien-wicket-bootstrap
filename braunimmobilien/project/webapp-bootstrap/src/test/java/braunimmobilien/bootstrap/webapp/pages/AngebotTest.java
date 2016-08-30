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
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.StrassenSucheForm;
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotTree;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Assert;
import java.util.List;
import java.util.Date;
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
import org.apache.wicket.util.tester.TagTester;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-dao.xml","classpath:applicationContext-resources.xml","classpath:applicationContext-service.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)

public class AngebotTest{
    private WicketTester tester;
    static Logger logger = LoggerFactory.getLogger(AngebotTest.class);
   
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
    public void Show_NewAngebot_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        FormTester formTester = tester.newFormTester("form");  
        formTester.setValue("angnr", "RH1000");
   	 formTester.submit("add-angebot");
     tester.assertRenderedPage(IndexBootstrap.class);
     formTester = tester.newFormTester("panel:form");  
     Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
    formTester.select("angstatus", 0);
    formTester.select("kondition", 0);
     formTester.setValue("angkurzbeschreibung", "RH1000");
     formTester.setValue("anglagebeschreibung", "RH1000");
     formTester.setValue("anganz", "1");
     formTester.submit("back");
      tester.assertRenderedPage(AngebotTree.class);
     
     
    }
    @Test
    @Transactional
    @Rollback(true)
    public void Show_NewAngebot_Next_NewCountry_Normal_NewLocation_Next_NewStreet_Next_NewObject_Next_NewCountry_Normal_NewLocation_Next_NewStreet_Next_NewPerson_Next_NewKunde_Next_NewNachweis_Next_Next_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        FormTester formTester = tester.newFormTester("form");  
        formTester.setValue("angnr", "RH1000");
   	 formTester.submit("add-angebot");
     tester.assertRenderedPage(IndexBootstrap.class);
     formTester = tester.newFormTester("panel:form");  
     Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
    formTester.select("angstatus", 0);
    formTester.select("kondition", 0);
     formTester.setValue("angkurzbeschreibung", "RH1000");
     formTester.setValue("anglagebeschreibung", "RH1000");
     formTester.setValue("anganz", "1");
     formTester.submit("next");
     tester.assertRenderedPage(IndexBootstrap.class);
     formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
	 tester.assertInvisible("panel:form:ortemarkup:orte");
		tester.assertInvisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
		 tester.assertInvisible("panel:form:textsearchmarkup:textsearch"); 
	 formTester.submit("nextButton");
	    tester.assertRenderedPage(IndexBootstrap.class);
	    formTester = tester.newFormTester("panel:form");  	 
		 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"LandInput");
		 formTester.setValue("landname","new land");
		 formTester.setValue("kennzeichen","new kennzeichen");
		 formTester.submit("normalButton");
		  tester.assertRenderedPage(IndexBootstrap.class);
		     formTester = tester.newFormTester("panel:form");  	 
			 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"OrtInput");
			 formTester.setValue("ortname","new ort");
			 formTester.setValue("ortplz","new plz");
			 formTester.submit("normalButton");
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
				 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
				 formTester.select("objektart", 0);
				 formTester.select("objektsuch", 0);
				 formTester.setValue("objhausnummer",formTester.getTextComponentValue("objhausnummer")+" 23");
				 formTester.submit("nextButton");
				 tester.assertRenderedPage(IndexBootstrap.class);
			     formTester = tester.newFormTester("panel:form");  	 
				 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
				 tester.assertInvisible("panel:form:ortemarkup:orte");
				 tester.assertVisible("panel:form:textsearchmarkup:textsearch"); 

				 tester.assertVisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
					formTester.select("eigentuemertypmarkup:eigentuemertyp", 0);
			   	 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:eigentuemertypmarkup:eigentuemertyp").getBehaviors().get(0));
				 formTester.submit("nextButton");
				    tester.assertRenderedPage(IndexBootstrap.class);
				    formTester = tester.newFormTester("panel:form");  	 
					 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"LandInput");
					 formTester.setValue("landname","new second land");
					 formTester.setValue("kennzeichen","new second kennzeichen");
					 formTester.submit("normalButton");
					  tester.assertRenderedPage(IndexBootstrap.class);
					     formTester = tester.newFormTester("panel:form");  	 
						 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"OrtInput");
						 formTester.setValue("ortname","new second ort");
						 formTester.setValue("ortplz","ond plz");
						 formTester.submit("normalButton");
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
							 formTester.setValue("eigtName","new name");
							 formTester.setValue("eigtHausnummer",formTester.getTextComponentValue("eigtHausnummer")+" 23");
							 formTester.setValue("eigtAnschrift","Herrn new name");
							 formTester.setValue("eigtBriefanrede","Sehr geehrter Herr new name");
							 formTester.setValue("eigtAktuell",new Boolean(true));
							 formTester.select("eigtstatus", 0);
							 formTester.submit("nextButton");
							 tester.assertRenderedPage(IndexBootstrap.class);
						     formTester = tester.newFormTester("panel:form");  	 
						     Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"KundeInput");
							 formTester.select("kundenart", 0);
							 formTester.select("status", 0);
							 formTester.submit("nextButton");
							 tester.assertRenderedPage(IndexBootstrap.class);
						     formTester = tester.newFormTester("panel:form");  	 
							 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"NachweisInput");
							 formTester.select("mitarbeiter", 0);
							 formTester.select("xtyp", 0);
							 formTester.select("angebot", 0);
							 formTester.submit("nextButton");
							 tester.assertRenderedPage(IndexBootstrap.class);
						     formTester = tester.newFormTester("panel:form");  	 
							 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"NachweisInput");
							 formTester.submit("nextButton");
							 tester.assertRenderedPage(IndexBootstrap.class);
						     formTester = tester.newFormTester("panel:form");  	 
							 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"NachweisInput");
							 formTester.submit("backButton");
							 tester.assertRenderedPage(AngebotTree.class);						 
    }
   
    @Test
    @Transactional
    @Rollback(true)
    public void search_Angebot_show_Angebot_Cancel(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        String responseTxt = tester.getLastResponse().getDocument();
        TagTester tagTester=null;
        int i=0;
    	for (i=1;i<10;i++){
        tagTester = TagTester.createTagByAttribute(responseTxt, "href","./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");
        if (tagTester!=null) break;}
       Assert.assertNotNull(tagTester);
       
    	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				"href", "./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link", false);
    	Assert.assertEquals(1, tagTesterList.size());
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");	
   	 tester.assertRenderedPage(AngebotTree.class);
    responseTxt = tester.getLastResponse().getDocument();
   	tagTester=null;	
	for (i=1;i<100;i++){
    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");
    if (tagTester!=null) break;}
	 Assert.assertNotNull(tagTester);
	 tester.executeUrl("../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");	
	 tester.assertRenderedPage(IndexBootstrap.class);
	 FormTester formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	 formTester.submit("cancel");
	
    tester.assertRenderedPage(AngebotBreadcrumbPage.class);
    
    }
   
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_Angebot_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        String responseTxt = tester.getLastResponse().getDocument();
        TagTester tagTester=null;
        int i=0;
    	for (i=1;i<10;i++){
        tagTester = TagTester.createTagByAttribute(responseTxt, "href","./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");
        if (tagTester!=null) break;}
       Assert.assertNotNull(tagTester);
       
    	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				"href", "./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link", false);
    	Assert.assertEquals(1, tagTesterList.size());
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");	
   	 tester.assertRenderedPage(AngebotTree.class);
    responseTxt = tester.getLastResponse().getDocument();
   	tagTester=null;	
	for (i=1;i<100;i++){
    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");
    if (tagTester!=null) break;}
	 Assert.assertNotNull(tagTester);
	 tester.executeUrl("../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");	
	 tester.assertRenderedPage(IndexBootstrap.class);
	 FormTester formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	 formTester.submit("back");
     tester.assertRenderedPage(AngebotTree.class);
    
    }
   
    @Test
    @Transactional
    @Rollback(true)
    public void search_Angebot_showAngebot_Next_search_Objekt_show_Objekt_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        String responseTxt = tester.getLastResponse().getDocument();
        TagTester tagTester=null;
        int i=0;
    	for (i=1;i<10;i++){
        tagTester = TagTester.createTagByAttribute(responseTxt, "href","./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");
        if (tagTester!=null) break;}
       Assert.assertNotNull(tagTester);
       
    	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				"href", "./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link", false);
    	Assert.assertEquals(1, tagTesterList.size());
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");	
   	 tester.assertRenderedPage(AngebotTree.class);
    responseTxt = tester.getLastResponse().getDocument();
   	tagTester=null;	
	for (i=1;i<100;i++){
    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");
    if (tagTester!=null) break;}
	 Assert.assertNotNull(tagTester);
	 tester.executeUrl("../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");	
	 tester.assertRenderedPage(IndexBootstrap.class);
	 FormTester formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	 formTester.submit("next");
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
     formTester.select("strassenmarkup:strasse", 0);
     tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:strassenmarkup:strasse").getBehaviors().get(0));
     tester.assertInvisible("panel:form:personenmarkup:person");
     tester.assertVisible("panel:form:objektemarkup:objekt");
    formTester.select("objektemarkup:objekt", 0);
     formTester.submit("backButton");
    tester.assertRenderedPage(AngebotTree.class);
    
    }
    
    
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_Angebot_Next_Search_Objekt_show_Objekt_Search_Kunde_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        String responseTxt = tester.getLastResponse().getDocument();
        TagTester tagTester=null;
        int i=0;
    	for (i=1;i<10;i++){
        tagTester = TagTester.createTagByAttribute(responseTxt, "href","./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");
        if (tagTester!=null) break;}
       Assert.assertNotNull(tagTester);
       
    	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				"href", "./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link", false);
    	Assert.assertEquals(1, tagTesterList.size());
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");	
   	 tester.assertRenderedPage(AngebotTree.class);
    responseTxt = tester.getLastResponse().getDocument();
   	tagTester=null;	
	for (i=1;i<100;i++){
    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");
    if (tagTester!=null) break;}
	 Assert.assertNotNull(tagTester);
	 tester.executeUrl("../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");	
	 tester.assertRenderedPage(IndexBootstrap.class);
	 FormTester formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	 formTester.submit("next");
     tester.assertRenderedPage(IndexBootstrap.class);
     formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
	 tester.assertInvisible("panel:form:ortemarkup:orte");
	 tester.assertInvisible("panel:form:textsearchmarkup:textsearch");
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
  	formTester.submit("nextButton");
    tester.assertRenderedPage(IndexBootstrap.class);
    formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
	 tester.assertInvisible("panel:form:ortemarkup:orte");
	 tester.assertVisible("panel:form:textsearchmarkup:textsearch");
		tester.assertVisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
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
       tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:person").getBehaviors().get(0));

       formTester.select("personenmarkup:kunden", 0);
       tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:kunden").getBehaviors().get(0));

       
       formTester.submit("backButton");
       tester.assertRenderedPage(AngebotTree.class);
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_Angebot_Next_Search_Objekt_show_Objekt_Search_Kunde_Show_Kunde_Back(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        String responseTxt = tester.getLastResponse().getDocument();
        TagTester tagTester=null;
        int i=0;
    	for (i=1;i<10;i++){
        tagTester = TagTester.createTagByAttribute(responseTxt, "href","./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");
        if (tagTester!=null) break;}
       Assert.assertNotNull(tagTester);
       
    	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				"href", "./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link", false);
    	Assert.assertEquals(1, tagTesterList.size());
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");	
   	 tester.assertRenderedPage(AngebotTree.class);
    responseTxt = tester.getLastResponse().getDocument();
   	tagTester=null;	
	for (i=1;i<100;i++){
    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");
    if (tagTester!=null) break;}
	 Assert.assertNotNull(tagTester);
	 tester.executeUrl("../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");	
	 tester.assertRenderedPage(IndexBootstrap.class);
	 FormTester formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	 formTester.submit("next");
     tester.assertRenderedPage(IndexBootstrap.class);
     formTester = tester.newFormTester("panel:form");  	 
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
  	formTester.submit("nextButton");
    tester.assertRenderedPage(IndexBootstrap.class);
    formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
	 tester.assertInvisible("panel:form:ortemarkup:orte");
		tester.assertVisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
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
       tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:person").getBehaviors().get(0));

       formTester.select("personenmarkup:kunden", 0);
       tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:kunden").getBehaviors().get(0));

       
       formTester.submit("nextButton");
       tester.assertRenderedPage(IndexBootstrap.class);
       formTester = tester.newFormTester("panel:form");  
       Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"KundeInput");
       formTester.submit("backButton");
       tester.assertRenderedPage(AngebotTree.class);
    }
      
    @Test
    @Transactional
    @Rollback(true)
    public void search_Angebot_Next_Search_Objekt_show_Objekt_Search_Kunde_Show_Kunde_Cancel(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        String responseTxt = tester.getLastResponse().getDocument();
        TagTester tagTester=null;
        int i=0;
    	for (i=1;i<10;i++){
        tagTester = TagTester.createTagByAttribute(responseTxt, "href","./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");
        if (tagTester!=null) break;}
       Assert.assertNotNull(tagTester);
       
    	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				"href", "./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link", false);
    	Assert.assertEquals(1, tagTesterList.size());
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");	
   	 tester.assertRenderedPage(AngebotTree.class);
    responseTxt = tester.getLastResponse().getDocument();
   	tagTester=null;	
	for (i=1;i<100;i++){
    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");
    if (tagTester!=null) break;}
	 Assert.assertNotNull(tagTester);
	 tester.executeUrl("../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");	
	 tester.assertRenderedPage(IndexBootstrap.class);
	 FormTester formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	 formTester.submit("next");
     tester.assertRenderedPage(IndexBootstrap.class);
     formTester = tester.newFormTester("panel:form");  	 
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
  	formTester.submit("nextButton");
    tester.assertRenderedPage(IndexBootstrap.class);
    formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
	 tester.assertInvisible("panel:form:ortemarkup:orte");
		tester.assertVisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
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
       tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:person").getBehaviors().get(0));

       formTester.select("personenmarkup:kunden", 0);
       tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("panel:form:personenmarkup:kunden").getBehaviors().get(0));

       
       formTester.submit("nextButton");
       tester.assertRenderedPage(IndexBootstrap.class);
       formTester = tester.newFormTester("panel:form");  
       Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"KundeInput");
       formTester.submit("cancelButton");
       tester.assertRenderedPage(AngebotTree.class);
    } 
    
    
    @Test
    @Transactional
    @Rollback(true)
    public void search_Angebot_Next_Search_Objekt_show_Objekt_Search_Kunde_Show_Kunde_Next_Show_Nachweis(){
       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage");	
        tester.assertRenderedPage(AngebotBreadcrumbPage.class);
        String responseTxt = tester.getLastResponse().getDocument();
        TagTester tagTester=null;
        int i=0;
    	for (i=1;i<10;i++){
        tagTester = TagTester.createTagByAttribute(responseTxt, "href","./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");
        if (tagTester!=null) break;}
       Assert.assertNotNull(tagTester);
       
    	List<TagTester> tagTesterList = TagTester.createTagsByAttribute(responseTxt, 
				"href", "./braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link", false);
    	Assert.assertEquals(1, tagTesterList.size());
    	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.angebot.AngebotBreadcrumbPage?"+i+"-1.ILinkListener-infinites-pageable-0-edit~link");	
   	 tester.assertRenderedPage(AngebotTree.class);
    responseTxt = tester.getLastResponse().getDocument();
   	tagTester=null;	
	for (i=1;i<100;i++){
    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");
    if (tagTester!=null) break;}
	 Assert.assertNotNull(tagTester);
	 tester.executeUrl("../../angebotbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&angnr=RH996");	
	 tester.assertRenderedPage(IndexBootstrap.class);
	 FormTester formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	 formTester.submit("next");
     tester.assertRenderedPage(IndexBootstrap.class);
     formTester = tester.newFormTester("panel:form");  	 
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
  	formTester.submit("nextButton");
    tester.assertRenderedPage(IndexBootstrap.class);
    formTester = tester.newFormTester("panel:form");  	 
	 Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrassenSucheForm");
	 tester.assertInvisible("panel:form:ortemarkup:orte");
		tester.assertVisible("panel:form:eigentuemertypmarkup:eigentuemertyp");
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
       tester.assertRenderedPage(AngebotTree.class);
   
    } 
    
    @After
    public void tearDown(){
    	//clear any side effect occurred during test.
    	tester.destroy();
    }
    
}
