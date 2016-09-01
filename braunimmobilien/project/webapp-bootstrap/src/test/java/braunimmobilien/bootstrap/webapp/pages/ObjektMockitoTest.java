package braunimmobilien.bootstrap.webapp.pages;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*; 
import org.junit.runner.RunWith;  
import org.mockito.Mock;  
import org.mockito.runners.MockitoJUnitRunner;  
import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.bootstrap.webapp.pages.auth.SignInPage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.objekt.ObjektTree;
import braunimmobilien.model.Land;
import braunimmobilien.service.LandManager;
import braunimmobilien.model.Nutzer;
import braunimmobilien.service.NutzerManager;
import java.util.List;
import java.util.ArrayList;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Objektsuch;
import braunimmobilien.model.Objektart;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Eigtstatus;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Strassen;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.EntityLoader;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.XtypManager;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.MitarbeiterManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.EigentuemertypManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class ObjektMockitoTest extends AbstractWicketMockitoTest{
	
	static Logger logger = LoggerFactory.getLogger(ObjektTest.class);
	
	private static Angobjzuord ANGOBJZUORD = new Angobjzuord();
	
	
    private static Orte  ORT= new Orte();
  
    private static Objekte OBJEKTNEUVOR = new Objekte();

    private static Objekte OBJEKTNEUNACH  = new Objekte();
    
    private static Objekte OBJEKT  = new Objekte();
	 
   
    
    private static Objektsuch OBJEKTSUCH  = new Objektsuch();
	 
    private static Eigtstatus EIGTSTATUS  = new Eigtstatus();
	 
    private static List<Eigtstatus> EIGTSTATUSE = new ArrayList<Eigtstatus>();
    
    
    private static Objektart OBJEKTART  = new Objektart();
	 
   
    private static Personen PERSON  = new Personen();
	 
    private static List<Personen> PERSONEN = new ArrayList<Personen>();
   
	
    private static Eigentuemertyp EIGENTUEMERTYP  = new Eigentuemertyp();
	 
    private static List<Eigentuemertyp> EIGENTUEMERTYPEN = new ArrayList<Eigentuemertyp>();
	
    
    private static Kunde KUNDE  = new Kunde();
	 
    
    
    private static Strassen STRASSE = new Strassen();
	 
   
	
	private static Scout SCOUT = new Scout();
	 
	
	
	private static Land LAND = new Land();
	 
	
	
    private static Nachweise NACHWEIs= new Nachweise();
	 
   
    
    private static Angebot ANGEBOT = new Angebot();
	 
    
    
    private static Nutzer NUTZER = new Nutzer();
    
    private static List<Nutzer> NUTZERE = new ArrayList<Nutzer>();
    
    private static List<Land> LANDES = new ArrayList<Land>();
    
    private static List<Objektsuch> OBJEKTSUCHE = new ArrayList<Objektsuch>();
    
    private static List<Objektart> OBJEKTARTEN = new ArrayList<Objektart>();
    
 
	
	 @Override
	    protected void setupTest() {
	NUTZER.setId(new Long(1));
		NUTZER.setAdmin(true);
		NUTZER.setDescription("bla bla");
		NUTZER.setPassword("bla");
		NUTZER.setUsername("bla");
		NUTZERE.add(NUTZER);
		LAND.setId(new Long(1));
		LAND.setKennzeichen("UNB");
		LAND.setLandname("Unbekannt");
		LANDES.add(LAND);
		ORT.setId(new Long(1));
		ORT.setLand(LAND);
		ORT.setOrtname("Weiss nicht wo");
		ORT.setOrtplz("12345");
		LAND.addOrt(ORT);
		STRASSE.setId(new Long(1));
		STRASSE.setOrt(ORT);
		STRASSE.setStrname("Nirgenwo");
		STRASSE.setStrplz("12345");
		ORT.addStrassen(STRASSE);
		OBJEKTNEUVOR.setId(null);
		OBJEKTNEUVOR.setObjektart(OBJEKTART);
		OBJEKTNEUVOR.setObjektsuch(OBJEKTSUCH);
		OBJEKTNEUVOR.setObjhausnummer("Nirgendwo 10");
		OBJEKTNEUVOR.setStrasse(STRASSE);
		OBJEKTNEUNACH.setId(new Long(2));
		OBJEKTNEUNACH.setObjektart(OBJEKTART);
		OBJEKTNEUNACH.setObjektsuch(OBJEKTSUCH);
		OBJEKTNEUNACH.setObjhausnummer("Nirgendwo 10");
		OBJEKTNEUNACH.setStrasse(STRASSE);
		OBJEKT.setId(new Long(1));
		OBJEKT.setObjektart(OBJEKTART);
		OBJEKT.setObjektsuch(OBJEKTSUCH);
		OBJEKT.setObjhausnummer("Nirgendwo 10");
		OBJEKT.setStrasse(STRASSE);
		OBJEKTSUCH.setId(new Long(1));
		OBJEKTSUCH.setSuchtext("keine Spezifität");
		OBJEKTART.setId(new Long(1));
		OBJEKTART.setObjartkurz("kG");
		OBJEKTART.setObjartname("komosches Gebilde");;
		OBJEKTART.setObjarttyp(true);
		OBJEKTSUCHE.add(OBJEKTSUCH);
		OBJEKTARTEN.add(OBJEKTART);
		STRASSE.addObjekt(OBJEKT);
		EIGENTUEMERTYP.setId(new Long(1));
		EIGENTUEMERTYP.setTypenbeschreibung("undefinierbar");
		EIGENTUEMERTYPEN.add(EIGENTUEMERTYP);
		EIGTSTATUS.setId(new Long(1));
		EIGTSTATUS.setEigt_status_text("aktuell");
		EIGTSTATUSE.add(EIGTSTATUS);
		PERSON.setId(new Long(1));
		PERSON.setEigtName("Niemand");
		PERSON.setEigtAnschrift("Herrn Niemand");
		PERSON.setEigtHausnummer("Nitgendwo 1");
		PERSON.setEigtstatus(EIGTSTATUS);
		java.util.Date today = new java.util.Date();
		PERSON.setEigtaufDatum(new java.sql.Date(today.getTime()));
		PERSON.setEigtAktuell(true);
		PERSON.setStrasse(STRASSE);
		PERSONEN.add(PERSON);
		STRASSE.addPerson(PERSON);
		when(eigtstatusManager.getEigtstatuses()).thenReturn(EIGTSTATUSE);
		when(eigtstatusManager.get(new Long(1))).thenReturn(EIGTSTATUS);
		when(nutzerManager.get(new Long(1))).thenReturn(NUTZER);
		when(nutzerManager.getNutzer()).thenReturn(NUTZERE);
		when(landManager.getLandes()).thenReturn(LANDES);
		when(eigentuemertypManager.getEigentuemertypes()).thenReturn(EIGENTUEMERTYPEN);
		when(objektsuchManager.getObjektsuchen()).thenReturn(OBJEKTSUCHE);
		when(objektartManager.getObjektartes()).thenReturn(OBJEKTARTEN);
		when(objektManager.get(new Long(1))).thenReturn(OBJEKT);
		when(entityLoader.load(Orte.class, new Long(1))).thenReturn(ORT);
		when(entityLoader.load(Land.class, new Long(1))).thenReturn(LAND);
		when(entityLoader.load(Strassen.class, new Long(1))).thenReturn(STRASSE);
		when(entityLoader.load(Objekte.class, new Long(1))).thenReturn(OBJEKT);
		when(entityLoader.load(Objektsuch.class, new Long(1))).thenReturn(OBJEKTSUCH);
		when(entityLoader.load(Objektart.class, new Long(1))).thenReturn(OBJEKTART);
		when(entityLoader.load(Eigentuemertyp.class, new Long(1))).thenReturn(EIGENTUEMERTYP);
		when(entityLoader.load(Personen.class, new Long(1))).thenReturn(PERSON);
		when(personManager.get(new Long(1))).thenReturn(PERSON);
		when(personManager.save(PERSON)).thenReturn(PERSON);
		when(personManager.search("Braun")).thenReturn(PERSONEN);
		 tester.startPage(BraunHomePage.class);
	        FormTester formTester = tester.newFormTester("intform");
	    	formTester.setValue("maklerUsername", "bla");
	    	formTester.setValue("maklerPassword", "bla");		
	    	//submit form
	    	formTester.submit();
			}

	@Test
	public void SearchObjektAndStore()
	{
		 
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
	         formTester.select("objektemarkup:objekt", 0);
	         formTester.submit("nextButton");
	         tester.assertRenderedPage(IndexBootstrap.class);
	         formTester = tester.newFormTester("panel:form");  	 
	         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
	         formTester.submit("backButton");
	         tester.assertRenderedPage(ObjektTree.class);
	    	    	 
	        
	}
 @Test
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
}
