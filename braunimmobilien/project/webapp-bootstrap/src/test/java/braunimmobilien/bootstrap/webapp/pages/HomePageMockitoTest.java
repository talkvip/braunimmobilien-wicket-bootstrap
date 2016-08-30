package braunimmobilien.bootstrap.webapp.pages;
import braunimmobilien.bootstrap.webapp.pages.auth.SignInPage;
import braunimmobilien.model.Land;
import braunimmobilien.model.Nutzer;
import braunimmobilien.service.LandManager;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class HomePageMockitoTest extends AbstractWicketMockitoTest {
	final Nutzer NUTZER = new Nutzer();
	final List<Nutzer> NUTZERE = Arrays.asList(NUTZER);
 	
    @Override
    protected void setupTest() {
    	NUTZER.setId(new Long(1));
		NUTZER.setAdmin(true);
		NUTZER.setDescription("bla bla");
		NUTZER.setPassword("bla");
		NUTZER.setUsername("bla");
		when(nutzerManager.get(new Long(1))).thenReturn(NUTZER);
		when(nutzerManager.getNutzer()).thenReturn(NUTZERE);
		
          }
 
    @Test
    public void callAndCheckHomePage() {
    	tester.startPage(BraunHomePage.class);
		  tester.assertRenderedPage(SignInPage.class);
	        FormTester formTester = tester.newFormTester("intform");
	    	//set credentials
	    	formTester.setValue("maklerUsername", "bla");
	    	formTester.setValue("maklerPassword", "bla");		
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
 
}