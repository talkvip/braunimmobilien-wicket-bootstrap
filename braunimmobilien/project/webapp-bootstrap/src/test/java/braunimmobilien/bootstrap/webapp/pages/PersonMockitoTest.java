package braunimmobilien.bootstrap.webapp.pages;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
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
import braunimmobilien.bootstrap.webapp.pages.person.PersonTree;
import braunimmobilien.bootstrap.webapp.pages.scout.ScoutSuch;
import braunimmobilien.bootstrap.webapp.pages.scout.ScoutTree;
import braunimmobilien.webapp.person.TelefoneModel;
import braunimmobilien.webapp.person.TelefonListModel;
import braunimmobilien.model.*;
import braunimmobilien.service.*;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@RunWith(MockitoJUnitRunner.class)
public class PersonMockitoTest extends AbstractWicketMockitoTest{
	
	static Logger logger = LoggerFactory.getLogger(PersonMockitoTest.class);
	
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
    
    private static Personen PERSONNULL  = new Personen();
    
    private static Personen PERSONAFTER  = new Personen();
    
    private static Personen PERSONAFTERTELEFON  = new Personen();
    
    
    private static Personen PERSONAFTERTELEFONSAVE  = new Personen();

    private static List<Personen> PERSONEN = new ArrayList<Personen>();
   
	
    private static Eigentuemertyp EIGENTUEMERTYP  = new Eigentuemertyp();
	 
    private static List<Eigentuemertyp> EIGENTUEMERTYPEN = new ArrayList<Eigentuemertyp>();
	
    
    private static Kunde KUNDE  = new Kunde();
	 
    
    
    private static Strassen STRASSE = new Strassen();
	 
   
	
	private static Scout SCOUT = new Scout();
	 
	 
    private static List<Scout> SCOUTS = new ArrayList<Scout>();

	
	private static Land LAND = new Land();
	 
	
	
    private static Nachweise NACHWEIs= new Nachweise();
	 
   
    
    private static Angebot ANGEBOT = new Angebot();
	 
    private static Telefonart TELEFONART = new Telefonart();
    
    private static Nutzer NUTZER = new Nutzer();
    
    private static List<Nutzer> NUTZERE = new ArrayList<Nutzer>();
    
    private static List<Land> LANDES = new ArrayList<Land>();
    
    private static List<Objektsuch> OBJEKTSUCHE = new ArrayList<Objektsuch>();
    
    private static List<Objektart> OBJEKTARTEN = new ArrayList<Objektart>();
    
    private static List<Telefonart> TELEFONARTEN = new ArrayList<Telefonart>();
	
	 @Override
	    protected void setupTest() {
		 TELEFONART.setId(1L);
		 TELEFONART.setEn("en");
		 TELEFONARTEN.add(TELEFONART);
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
		
		PERSONNULL.setId(null);
		PERSONNULL.setEigtName("Frank Fizzlipuzzli");
		PERSONNULL.setEigtAnschrift("Herrn Frank Fizzlipuzzli");
		PERSONNULL.setEigtBriefanrede("Frank Fizzlipuzzli");
		PERSONNULL.setEigtHausnummer(STRASSE.getStrname()+" 12");
		PERSONNULL.setEigtstatus(EIGTSTATUS);
		java.util.Date today = new java.util.Date();
		PERSONNULL.setEigtaufDatum(new java.sql.Date(today.getTime()));
		PERSONNULL.setEigtAktuell(true);
		PERSONNULL.setStrasse(STRASSE);
		
		PERSON.setId(new Long(1));
		PERSON.setEigtName("Niemand");
		PERSON.setEigtAnschrift("Herrn Niemand");
		PERSON.setEigtHausnummer(STRASSE.getStrname()+" 1");
		PERSON.setEigtstatus(EIGTSTATUS);
		
		PERSON.setEigtaufDatum(new java.sql.Date(today.getTime()));
		PERSON.setEigtAktuell(true);
		PERSON.setStrasse(STRASSE);
		
		PERSONAFTER.setId(new Long(3));
		PERSONAFTER.setEigtName("Frank Fizzlipuzzli");
		PERSONAFTER.setEigtAnschrift("Herrn Frank Fizzlipuzzli");
		PERSONAFTER.setEigtHausnummer(STRASSE.getStrname()+" 12");
		PERSONAFTER.setEigtstatus(EIGTSTATUS);
		PERSONAFTER.setEigtaufDatum(new java.sql.Date(today.getTime()));
		PERSONAFTER.setEigtAktuell(true);
		PERSONAFTER.setStrasse(STRASSE);
		PERSONAFTERTELEFON.setId(new Long(3));
		PERSONAFTERTELEFON.setEigtName("Frank Fizzlipuzzli");
		PERSONAFTERTELEFON.setEigtAnschrift("Herrn Frank Fizzlipuzzli");
		PERSONAFTERTELEFON.setEigtHausnummer(STRASSE.getStrname()+" 12");
		PERSONAFTERTELEFON.setEigtstatus(EIGTSTATUS);
		PERSONAFTERTELEFON.setEigtaufDatum(new java.sql.Date(today.getTime()));
		PERSONAFTERTELEFON.setEigtAktuell(true);
		PERSONAFTERTELEFON.setStrasse(STRASSE);
		PERSONAFTERTELEFONSAVE.setId(new Long(4));
		PERSONAFTERTELEFONSAVE.setEigtName("Frank Fizzlipuzzli");
		PERSONAFTERTELEFONSAVE.setEigtAnschrift("Herrn Frank Fizzlipuzzli");
		PERSONAFTERTELEFONSAVE.setEigtHausnummer(STRASSE.getStrname()+" 12");
		PERSONAFTERTELEFONSAVE.setEigtstatus(EIGTSTATUS);
		PERSONAFTERTELEFONSAVE.setEigtaufDatum(new java.sql.Date(today.getTime()));
		PERSONAFTERTELEFONSAVE.setEigtAktuell(true);
		PERSONAFTERTELEFONSAVE.setStrasse(STRASSE);
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
		TelefoneModel telefonModel=new TelefoneModel();
			TelefoneModel.Telefon telefon=new TelefoneModel.Telefon();
			telefon.setArt("en");
			telefon.setTelefon("0511314567789");
			telefonModel.getTelefonList().add(telefon);
			TelefonListModel telefonlistmodel=null;
			try{
				telefonlistmodel=new TelefonListModel(telefonModel);
				PERSONAFTERTELEFON.setEigtTelefone(telefonlistmodel.getTelXml());
				PERSONAFTERTELEFONSAVE.setEigtTelefone(telefonlistmodel.getTelXml());
				when(synchronizecontacts.createAndInsertNewContact(PERSONAFTERTELEFONSAVE,telefonlistmodel,"Testaufnahme","System Group: My Contacts")).thenReturn("http://www.google.com/m8/feeds/contacts/wichtigtuer.braun%40gmail.com/base/4b353");
			}
			catch(Exception ex){
				telefonlistmodel=new TelefonListModel();
				PERSONAFTERTELEFON.setEigtTelefone("");
				PERSONAFTERTELEFONSAVE.setEigtTelefone("");
					}
		doThrow(new ObjectRetrievalFailureException(Personen.class, null))
		   .when(personManager).get(null);
		when(telefonartManager.getTelefonartes()).thenReturn(TELEFONARTEN);
		when(eigentuemermusterManager.getEigentuemermusters()).thenReturn(EIGENTUEMERMUSTERS);
		when(synchronizecontacts.getUrl()).thenReturn("http:8088/bootstrap");

		when(scoutManager.search("66538384")).thenReturn(SCOUTS);
		when(strassenManager.get(1L)).thenReturn(STRASSE);
		when(scoutManager.get(66538384L)).thenReturn(SCOUT);
		when(scoutManager.save(SCOUT)).thenReturn(SCOUT);
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
		when(entityLoader.load(Scout.class, 66538384L)).thenReturn(SCOUT);
		when(entityLoader.load(Orte.class, new Long(1))).thenReturn(ORT);
		when(entityLoader.load(Land.class, new Long(1))).thenReturn(LAND);
		when(entityLoader.load(Strassen.class, new Long(1))).thenReturn(STRASSE);
		when(entityLoader.load(Objekte.class, new Long(1))).thenReturn(OBJEKT);
		when(entityLoader.load(Objektsuch.class, new Long(1))).thenReturn(OBJEKTSUCH);
		when(entityLoader.load(Objektart.class, new Long(1))).thenReturn(OBJEKTART);
		when(entityLoader.load(Eigentuemertyp.class, new Long(1))).thenReturn(EIGENTUEMERTYP);
		when(entityLoader.load(Eigtstatus.class, new Long(1))).thenReturn(EIGTSTATUS);
		when(entityLoader.load(Personen.class, new Long(1))).thenReturn(PERSON);
		when(entityLoader.load(Personen.class, new Long(3))).thenReturn(PERSONAFTER);
		when(entityLoader.load(Personen.class, new Long(4))).thenReturn(PERSONAFTERTELEFONSAVE);
		when(personManager.get(new Long(1))).thenReturn(PERSON);
		when(personManager.get(new Long(3))).thenReturn(PERSONAFTER);
		when(personManager.get(new Long(4))).thenReturn(PERSONAFTERTELEFONSAVE);
		when(personManager.save(PERSON)).thenReturn(PERSON);
		when(personManager.save(PERSONAFTERTELEFON)).thenReturn(PERSONAFTERTELEFONSAVE);
		when(personManager.save(PERSONNULL)).thenReturn(PERSONAFTER);

		when(personManager.search("Braun")).thenReturn(PERSONEN);
		 tester.startPage(BraunHomePage.class);
	        FormTester formTester = tester.newFormTester("intform");
	    	formTester.setValue("maklerUsername", "bla");
	    	formTester.setValue("maklerPassword", "bla");		
	    	//submit form
	    	formTester.submit();
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
	         formTester.setValue("eigtAktuell","true");
	         STRASSE.addPerson(PERSONNULL);
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
	    				    Assert.assertEquals(1, eigtstatus.getChoices().size());
	    				    formTester.setValue("eigtAktuell", "on");
	    			 formTester.select("eigtstatus", 0);

	  		//	 formTester.select("eigtstatus", 0);
	  				 formTester.submit("backButton");
	  			    responseTxt = tester.getLastResponse().getDocument();
		    		  	System.err.println(responseTxt);
	  		      tester.assertRenderedPage(PersonTree.class);
	  		    		        responseTxt = tester.getLastResponse().getDocument();
	  		    		  	System.err.println(responseTxt);
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
	   			formTester.setValue("accesskey","mytestkey");
	   			formTester.submit("finish");
	    }
	 
	 
	 
	 
	 
}

