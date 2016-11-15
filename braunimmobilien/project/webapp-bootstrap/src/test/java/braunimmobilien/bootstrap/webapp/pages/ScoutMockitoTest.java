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
import braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch;
import braunimmobilien.bootstrap.webapp.pages.scout.ScoutTree;
import braunimmobilien.model.*;
import braunimmobilien.service.*;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@RunWith(MockitoJUnitRunner.class)
public class ScoutMockitoTest extends AbstractWicketMockitoTest{
	
	static Logger logger = LoggerFactory.getLogger(ScoutMockitoTest.class);
	
	private static Angobjzuord ANGOBJZUORD = new Angobjzuord();	
	
	 private static Objarttyp  OBJARTTYP= new Objarttyp();
	 
	 private static List<Objarttyp> OBJARTTYPES = new ArrayList<Objarttyp>();
	 
	 private static Type  TYPE= new Type();
	 
	 private static List<Type> TYPES = new ArrayList<Type>();
	
    private static Orte  ORT= new Orte();
  
    private static Objekte OBJEKTNEUVOR = new Objekte();

    private static Objekte OBJEKTNEUNACH  = new Objekte();
    
    private static Objekte OBJEKT  = new Objekte();
	 
   
    
    private static Objektsuch OBJEKTSUCH  = new Objektsuch();
	 
    private static Eigtstatus EIGTSTATUS  = new Eigtstatus();
    
    private static Eigentuemermuster EIGENTUEMERMUSTER  = new Eigentuemermuster();
    
    private static List<Eigentuemermuster> EIGENTUEMERMUSTERS = new ArrayList<Eigentuemermuster>();
	
	 
    private static List<Eigtstatus> EIGTSTATUSE = new ArrayList<Eigtstatus>();
    
    
    private static Objektart OBJEKTART  = new Objektart();
	 
   
    private static Personen PERSON  = new Personen();
	 
    private static List<Personen> PERSONEN = new ArrayList<Personen>();
   
	
    private static Eigentuemertyp EIGENTUEMERTYP  = new Eigentuemertyp();
	 
    private static List<Eigentuemertyp> EIGENTUEMERTYPEN = new ArrayList<Eigentuemertyp>();
	
    
    private static Kunde KUNDE  = new Kunde();
	 
    
    
    private static Strassen STRASSE = new Strassen();
	 
   
	
	private static Scout SCOUT = new Scout();
	 
	private static Scout SCOUT1 = new Scout();
	
    private static List<Scout> SCOUTS = new ArrayList<Scout>();
    
    private static List<Scout> SCOUTS1 = new ArrayList<Scout>();

	
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
		OBJARTTYP.setId(1L);
		OBJARTTYP.setTypentext("abgehakt");
		OBJARTTYPES.add(OBJARTTYP);
		TYPE.setId(1L);
		TYPE.setType("Wohnung Vermietung");
		TYPES.add(TYPE);
		EIGENTUEMERMUSTER.setId(1L);
		EIGENTUEMERMUSTER.setEigentuemermuster("eigentuemermuster");
		EIGENTUEMERMUSTER.setPerson(PERSON);
		EIGENTUEMERMUSTER.setType(TYPE);
		EIGENTUEMERMUSTERS.add(EIGENTUEMERMUSTER);
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
		SCOUT.setId(66538384L);
		SCOUT.setObjarttyp(OBJARTTYP);
		SCOUT.setObjekt(OBJEKT);
		SCOUT.setPerson(PERSON);
		SCOUT.setOrt(ORT);
		SCOUT.setPhone("Telefon: +49(89) 38 66 52 3Telefon: +49(89) 38 66 52 3 Fax: +49(89) 38 66 52 44 Herzogstraße 23 80803 München Impressum des Anbieters");
		SCOUT.setType(TYPE);
		SCOUT.setWhat("Vermietbare Fläche: 5.000,00 m² Kaufpreis: 10.000.000,00 EUR x-fache Miete: 10,90   Objektart: Supermarkt Mieteinnahmen p.a. (Ist): 920.000,00 EUR Provision für Käufer: 3,57 % inkl. MwStPaket mit 5 EDEKA-Märkten, ca. 10-15 Jahre alt mit Ø-MV-Rest-Lfz. von 10 Jahren. Weitere EDEKA-Märkte auf Anfrage.Norddeutschland.");
		SCOUT.setWhere("30159 Hannover Die vollständige Adresse der Immobilie erhalten Sie vom Anbieter. Karte ansehenStreet View");
		SCOUT.setWho("  Langer Vermögensanlagen Langer Vermögensanlagen Anbieter kontaktieren www.langer-vermoegensanlagen.de Firmenprofil/Homepage des Anbieters Telefon: +49(89) 38 66 52 3 Fax: +49(89) 38 66 52 44 Herzogstraße 23 80803 München Impressum des Anbieters Weitere Angebote dieses Anbieters GOLFCLUB IM RHEIN/MAIN-BALLUNGSRAUM -&gt; FMZ NÄHE PASSAU -&gt; MFH-NEUBAU IN PFAFFENHOFEN/ILM -&gt; GESCHÄFTSHAUS BEI OBERSTDORF -&gt; GRUNDSTÜCK IN MÜNCHEN-BOGENHAUSEN -&gt; BUDGET-HOTEL NEUBAU IN MÜNCHEN -&gt; GRUNDSTÜCK MIT BESTAND IN INGOLSTADT -&gt; CITY-HOTEL IN BERLIN-MITTE -&gt; SB-MARKT-NEUBAU IN BERLIN -&gt; REWE DIREKT BEI BERLIN -&gt; DOPPELHAUS IN MÜNCHEN-TRUDERING -&gt; FMZ BEI STUTTGART -&gt; SB-MARKT NÄHE STUTTGART -&gt; SB-MARKT NÄHE CHIEMSEE -&gt; TOP: VERWALTUNGSGEBÄUDE MIT STAATLICHEM MIETER -&gt; ERSTANGEBOT: WGH IN ZORNEDING BEI MÜNCHEN -&gt; BÜROGEBÄUDE IN HAMBURG -&gt; RARITÄT: GRUNDSTÜCK IN MÜNCHEN -&gt; MEDIAMARKT IM RAUM BERLIN -&gt; GESCHÄFTSHAUS IN STUTTGART -&gt; FMZ BEI NÜRNBERG -&gt; Mehr Angebote anzeigen:no_collapse");
		SCOUT.setYesScout(true);
		SCOUTS.add(SCOUT);
		SCOUTS1.add(SCOUT1);
		SCOUT1.setId(66538385L);
		SCOUT1.setObjarttyp(OBJARTTYP);
		SCOUT1.setOrt(ORT);
		SCOUT1.setPhone("Telefon: +49(89) 38 66 52 3Telefon: +49(89) 38 66 52 3 Fax: +49(89) 38 66 52 44 Herzogstraße 23 80803 München Impressum des Anbieters");
		SCOUT1.setType(TYPE);
		SCOUT1.setWhat("Vermietbare Fläche: 5.000,00 m² Kaufpreis: 10.000.000,00 EUR x-fache Miete: 10,90   Objektart: Supermarkt Mieteinnahmen p.a. (Ist): 920.000,00 EUR Provision für Käufer: 3,57 % inkl. MwStPaket mit 5 EDEKA-Märkten, ca. 10-15 Jahre alt mit Ø-MV-Rest-Lfz. von 10 Jahren. Weitere EDEKA-Märkte auf Anfrage.Norddeutschland.");
		SCOUT1.setWhere("30159 Hannover Die vollständige Adresse der Immobilie erhalten Sie vom Anbieter. Karte ansehenStreet View");
		SCOUT1.setWho("  Langer Vermögensanlagen Langer Vermögensanlagen Anbieter kontaktieren www.langer-vermoegensanlagen.de Firmenprofil/Homepage des Anbieters Telefon: +49(89) 38 66 52 3 Fax: +49(89) 38 66 52 44 Herzogstraße 23 80803 München Impressum des Anbieters Weitere Angebote dieses Anbieters GOLFCLUB IM RHEIN/MAIN-BALLUNGSRAUM -&gt; FMZ NÄHE PASSAU -&gt; MFH-NEUBAU IN PFAFFENHOFEN/ILM -&gt; GESCHÄFTSHAUS BEI OBERSTDORF -&gt; GRUNDSTÜCK IN MÜNCHEN-BOGENHAUSEN -&gt; BUDGET-HOTEL NEUBAU IN MÜNCHEN -&gt; GRUNDSTÜCK MIT BESTAND IN INGOLSTADT -&gt; CITY-HOTEL IN BERLIN-MITTE -&gt; SB-MARKT-NEUBAU IN BERLIN -&gt; REWE DIREKT BEI BERLIN -&gt; DOPPELHAUS IN MÜNCHEN-TRUDERING -&gt; FMZ BEI STUTTGART -&gt; SB-MARKT NÄHE STUTTGART -&gt; SB-MARKT NÄHE CHIEMSEE -&gt; TOP: VERWALTUNGSGEBÄUDE MIT STAATLICHEM MIETER -&gt; ERSTANGEBOT: WGH IN ZORNEDING BEI MÜNCHEN -&gt; BÜROGEBÄUDE IN HAMBURG -&gt; RARITÄT: GRUNDSTÜCK IN MÜNCHEN -&gt; MEDIAMARKT IM RAUM BERLIN -&gt; GESCHÄFTSHAUS IN STUTTGART -&gt; FMZ BEI NÜRNBERG -&gt; Mehr Angebote anzeigen:no_collapse");
		SCOUT1.setYesScout(true);
		when(eigentuemermusterManager.getEigentuemermusters()).thenReturn(EIGENTUEMERMUSTERS);
		when(scoutManager.search("66538384")).thenReturn(SCOUTS);
		when(scoutManager.search("66538385")).thenReturn(SCOUTS1);
		when(scoutManager.get(66538384L)).thenReturn(SCOUT);
		when(strassenManager.get(1L)).thenReturn(STRASSE);
		when(scoutManager.save(SCOUT)).thenReturn(SCOUT);
		when(scoutManager.get(66538385L)).thenReturn(SCOUT1);
		when(scoutManager.save(SCOUT1)).thenReturn(SCOUT1);
		when(objarttypManager.getObjarttypes()).thenReturn(OBJARTTYPES);
		when(typeManager.getTypees()).thenReturn(TYPES);
		when(eigtstatusManager.getEigtstatuses()).thenReturn(EIGTSTATUSE);
		when(eigtstatusManager.get(new Long(1))).thenReturn(EIGTSTATUS);
		when(nutzerManager.get(new Long(1))).thenReturn(NUTZER);
		when(nutzerManager.getNutzer()).thenReturn(NUTZERE);
		when(landManager.getLandes()).thenReturn(LANDES);
		when(eigentuemertypManager.getEigentuemertypes()).thenReturn(EIGENTUEMERTYPEN);
		when(objektsuchManager.getObjektsuchen()).thenReturn(OBJEKTSUCHE);
		when(objektartManager.getObjektartes()).thenReturn(OBJEKTARTEN);
		when(objektManager.get(new Long(1))).thenReturn(OBJEKT);
		when(objektManager.save(OBJEKT)).thenReturn(OBJEKT);
		when(entityLoader.load(Scout.class, 66538384L)).thenReturn(SCOUT);
		when(entityLoader.load(Scout.class, 66538385L)).thenReturn(SCOUT1);
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
	 	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch");	
    	tester.assertRenderedPage(ScoutSuch.class);
    	 FormTester 	 formTester = tester.newFormTester("form");  	 
    		formTester.setValue("searchField", "66538384");	
    		 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("form:searchField").getBehaviors().get(0));
    		 formTester.select("scout", 0);
    		 formTester.submit("editScoutButton");
    		 tester.assertRenderedPage(ScoutTree.class);
    		String responseTxt = tester.getLastResponse().getDocument();
    		TagTester  	tagTester=null;	
    		int i=0;
    			for (i=1;i<100;i++){
    		    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../scoutbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&scoutid=66538384");
    		    if (tagTester!=null) break;}
    			 Assert.assertNotNull(tagTester);
    			tester.executeUrl("../../scoutbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&scoutid=66538384");	
    			tester.assertRenderedPage(IndexBootstrap.class);
    			formTester = tester.newFormTester("panel:form"); 
    			Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ScoutInput");
    				formTester.submit("addObjektToScoutButton");
    				 	tester.assertRenderedPage(IndexBootstrap.class);
   			 	formTester = tester.newFormTester("panel:form"); 
   				/*		Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ObjektInput");
    			tester.assertLabel("panel:form:id", "1");
    				formTester.submit("backButton");
    	  			tester.assertRenderedPage(IndexBootstrap.class);
    				formTester = tester.newFormTester("panel:form"); 
   			Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ScoutInput");	
    			formTester.submit("addPersonToScoutButton");
				 tester.assertRenderedPage(IndexBootstrap.class);
formTester = tester.newFormTester("panel:form"); 
	Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"PersonInput");
tester.assertLabel("panel:form:id", "1");
formTester.submit("backButton");
tester.assertRenderedPage(IndexBootstrap.class);*/
	}

	 @Test
	    @Transactional
	    @Rollback(true)
	    public void searchScoutByIdandChooseObjektByAdressandOpenandBackandChoosePersonByAdressandOpenandBack(){
	       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch");	
	    	tester.assertRenderedPage(ScoutSuch.class);
	    	 FormTester 	 formTester = tester.newFormTester("form");  	 
	    		formTester.setValue("searchField", "66538385");	
	    		 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("form:searchField").getBehaviors().get(0));
	  		 formTester.select("scout", 0);
	  			 formTester.submit("editScoutButton");
	  				 tester.assertRenderedPage(ScoutTree.class);
	    		String responseTxt = tester.getLastResponse().getDocument();
	    		TagTester  	tagTester=null;	
	    		int i=0;
	    			for (i=1;i<100;i++){
	    		    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../scoutbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&scoutid=66538385");
	    		    if (tagTester!=null) break;}
	    			 Assert.assertNotNull(tagTester);
	    			tester.executeUrl("../../scoutbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&scoutid=66538385");	
	    			tester.assertRenderedPage(IndexBootstrap.class);
	    			formTester = tester.newFormTester("panel:form"); 
	    			Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ScoutInput");
	    			formTester.submit("addObjektToScoutButton");
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
	     	       formTester.submit("backButton");
	     	         tester.assertRenderedPage(IndexBootstrap.class);
	     	         formTester = tester.newFormTester("panel:form");
	     	         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ScoutInput");
	    					formTester.submit("addPersonToScoutButton");
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
					         tester.assertVisible("panel:form:personenmarkup:person");
					         formTester.select("personenmarkup:person", 0);
					         formTester.submit("backButton");
			     	         tester.assertRenderedPage(IndexBootstrap.class);
			     	         formTester = tester.newFormTester("panel:form");
			     	         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ScoutInput");
	    }
	 
	 @Test
	    @Transactional
	    @Rollback(true)
	    public void searchScoutByIdandInsertObjektByAdressandInsertNewStrasseandOpenandBackandChoosePersonByAdressandOpenandBack(){
	       	tester.executeUrl("../../wicket/bookmarkable/braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch");	
	    	tester.assertRenderedPage(ScoutSuch.class);
	    	 FormTester 	 formTester = tester.newFormTester("form");  	 
	    		formTester.setValue("searchField", "66538385");	
	    		 tester.executeBehavior((AbstractAjaxBehavior)tester.getComponentFromLastRenderedPage("form:searchField").getBehaviors().get(0));
	  		 formTester.select("scout", 0);
	  			 formTester.submit("editScoutButton");
	  				 tester.assertRenderedPage(ScoutTree.class);
	    		String responseTxt = tester.getLastResponse().getDocument();
	    		TagTester  	tagTester=null;	
	    		int i=0;
	    			for (i=1;i<100;i++){
	    		    tagTester = TagTester.createTagByAttribute(responseTxt, "href","../../scoutbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&scoutid=66538385");
	    		    if (tagTester!=null) break;}
	    			 Assert.assertNotNull(tagTester);
	    			tester.executeUrl("../../scoutbreadcrumbtree?"+i+"-1.ILinkListener-tree-subtree-branches-1-node-content-link&scoutid=66538385");	
	    			tester.assertRenderedPage(IndexBootstrap.class);
	    			formTester = tester.newFormTester("panel:form"); 
	    			Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ScoutInput");
	    			formTester.submit("addObjektToScoutButton");
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
	     	    	/*	       formTester.submit("nextButton");
	     	         tester.assertRenderedPage(IndexBootstrap.class);
	     	         formTester = tester.newFormTester("panel:form");
	     	         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"StrasseInput");
	    			formTester.submit("addPersonToScoutButton");
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
					         tester.assertVisible("panel:form:personenmarkup:person");
					         formTester.select("personenmarkup:person", 0);
					         formTester.submit("backButton");
			     	         tester.assertRenderedPage(IndexBootstrap.class);
			     	         formTester = tester.newFormTester("panel:form");
			     	         Assert.assertEquals("",formTester.getForm().getClass().getSimpleName(),"ScoutInput");*/
	    }
	 
	
	
	
}

