package braunimmobilien.bootstrap.webapp.pages.repair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

import org.apache.wicket.markup.html.link.PopupSettings;

import braunimmobilien.bootstrap.webapp.EntityModel;

import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.AttributeModifier;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;

import braunimmobilien.bootstrap.webapp.MaklerFlow;
import braunimmobilien.bootstrap.webapp.pages.tree.MyFoo;
import braunimmobilien.bootstrap.webapp.pages.provider.MyFooProvider;
import braunimmobilien.bootstrap.webapp.pages.wizard.NewLandWizard;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.WebMarkupContainer;

import braunimmobilien.model.Angebot;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Eigentuemermuster;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.service.LandManager;
import braunimmobilien.service.AngobjzuordManager;
import braunimmobilien.service.OrteManager;
import braunimmobilien.service.StrassenManager;
import braunimmobilien.service.NachweiseManager;
import braunimmobilien.service.ScoutManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.EigentuemermusterManager;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.select.BootstrapSelect;
import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */

public class Repair extends BasePage {
	private static final long serialVersionUID = 1L;
	@SpringBean
    private LandManager landManager;
	@SpringBean
    private AngobjzuordManager angobjuordeManager;
	@SpringBean
    private OrteManager orteManager;
	@SpringBean
    private StrassenManager strassenManager;
	@SpringBean
    private ObjektManager objektManager;
	@SpringBean
    private PersonManager personManager;
	@SpringBean
    private NachweiseManager nachweiseManager;
	@SpringBean
    private ScoutManager scoutManager;
	@SpringBean
    private KundeManager kundeManager;
	@SpringBean
    private EigentuemermusterManager eigentuemermusterManager;
	@SpringBean
	 private AngobjzuordManager angobjzuordManager;
	private String countryoriginal;		
	private String countrytoreplace;
				
	private String townoriginal;		
	private String  towntoreplace;

	private String 	streetoriginal;		
	private String streettoreplace;

	private String 	objectoriginal;		
	private String  objecttoreplace;

	private String 	personoriginal;		
	private String persontoreplace;
	private String countryoriginaltext;		
	private String countrytoreplacetext;
				
	private String townoriginaltext;		
	private String  towntoreplacetext;

	private String 	streetoriginaltext;		
	private String streettoreplacetext;

	private String 	objectoriginaltext;		
	private String  objecttoreplacetext;

	private String 	personoriginaltext;		
	private String persontoreplacetext;
	
	
	
	final TextField<String> countryOriginal = new TextField<String>("countryoriginal");		
  	final TextField<String> countryToReplace = new TextField<String>("countrytoreplace");
			
	final TextField<String> townOriginal = new TextField<String>("townoriginal");		
  	final TextField<String> townToReplace = new TextField<String>("towntoreplace");

	final TextField<String> streetOriginal = new TextField<String>("streetoriginal");		
  	final TextField<String> streetToReplace = new TextField<String>("streettoreplace");

	final TextField<String> objectOriginal = new TextField<String>("objectoriginal");		
  	final TextField<String> objectToReplace = new TextField<String>("objecttoreplace");

	final TextField<String> personOriginal = new TextField<String>("personoriginal");		
  	final TextField<String> personToReplace = new TextField<String>("persontoreplace");
  	
  	ReplaceModel model = new ReplaceModel();
  	BootstrapForm  bootstrapForm = new BootstrapForm("form",new CompoundPropertyModel(model));
public Repair()
	
	{ super();
	  
	  add(bootstrapForm);
	  final NotificationPanel feedback = new NotificationPanel("feedback");
		add(feedback);
		 bootstrapForm.add(new CounterLabel("label1","countryoriginaltext"));
		 bootstrapForm.add(new CounterLabel("label2","countrytoreplacetext"));
		 bootstrapForm.add(new CounterLabel("label3","townoriginaltext"));
         bootstrapForm.add(new CounterLabel("label4","towntoreplacetext"));
         bootstrapForm.add(new CounterLabel("label5","streetoriginaltext"));
         bootstrapForm.add(new CounterLabel("label6","streettoreplacetext"));
         bootstrapForm.add(new CounterLabel("label7","objectoriginaltext"));
         bootstrapForm.add(new CounterLabel("label8","objecttoreplacetext"));
         bootstrapForm.add(new CounterLabel("label9","personoriginaltext"));
         bootstrapForm.add(new CounterLabel("label10","persontoreplacetext"));
         bootstrapForm.add(countryOriginal);
         bootstrapForm.add(countryToReplace);
         bootstrapForm.add(townOriginal);
         bootstrapForm.add(townToReplace);
         bootstrapForm.add(streetOriginal);
         bootstrapForm.add(streetToReplace);
         bootstrapForm.add(objectOriginal);
         bootstrapForm.add(objectToReplace);
         bootstrapForm.add(personOriginal);
         bootstrapForm.add(personToReplace);
         bootstrapForm.add(new AjaxButton("submit")
         {

             @Override
             protected void onSubmit(AjaxRequestTarget target, Form<?> form)
            {if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal().equals("countryoriginal")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setCountryoriginaltext(landManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal())).getLandname());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace().equals("countrytoreplace")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setCountrytoreplacetext(landManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace())).getLandname());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal().equals("townoriginal")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setTownoriginaltext(orteManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal())).getOrtname());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace().equals("towntoreplace")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setTowntoreplacetext(orteManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace())).getOrtname());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal().equals("streetoriginal")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setStreetoriginaltext(strassenManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal())).getStrname());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace().equals("streettoreplace")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setStreettoreplacetext(strassenManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace())).getStrname());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal().equals("objectoriginal")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setObjectoriginaltext(objektManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal())).getObjhausnummer());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace().equals("objecttoreplace")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setObjecttoreplacetext(objektManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace())).getObjhausnummer());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal().equals("personoriginal")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setPersonoriginaltext(personManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal())).getEigtAnschrift());}
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace().equals("persontoreplace")) {((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).setPersontoreplacetext(personManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace())).getEigtAnschrift());}
               
             
             
             send(getPage(), Broadcast.BREADTH, new CounterUpdate(target));
             }

             @Override
             protected void onError(AjaxRequestTarget target, Form<?> form)
             {
             }

         });  
         
         bootstrapForm.add(new Button("execute")
         {

             @Override
             public void onSubmit()
             {
            	
            	 if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal().equals("countryoriginal")&&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace().equals("countrytoreplace")) {
            		 Land land=landManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountryoriginal()));
            		Land landreplace =landManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getCountrytoreplace()));
            		 
            		 Iterator it= landreplace.getOrte().iterator();
            	 while(it.hasNext()){
            		 Orte ort=(Orte)it.next();
            		 ort.setLand(land);
            		 land.addOrt(ort);
            		 orteManager.save(ort);
            	 }
            	 landreplace.setOrte(new ArrayList<Orte>());
            	 landManager.removeLand(landreplace);
             }
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal().equals("townoriginal")&&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace().equals("towntoreplace")) {
            	 Orte orte=orteManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTownoriginal()));
            	 Orte ortereplace= orteManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getTowntoreplace()));
            	 Iterator it= ortereplace.getStrassen().iterator();
             	 while(it.hasNext()){
             		 Strassen strasse=(Strassen)it.next();
             		 strasse.setOrt(orte);
             		 orte.addStrassen(strasse);
             		 strassenManager.save(strasse);
             	 }
             ortereplace.setStrassen(new ArrayList<Strassen>());
             	orteManager.removeOrte(ortereplace);
              }
                
             
             
             if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal().equals("streetoriginal")&&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace().equals("streettoreplace")) {
              Strassen strasse=strassenManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreetoriginal()));
              Strassen strassereplace=strassenManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getStreettoreplace()));
         	  
              Iterator it=strassereplace.getObjekte().iterator();
              	 while(it.hasNext()){
              		 Objekte objekt=(Objekte)it.next();
              		 objekt.setStrasse(strasse);
              		 strasse.addObjekt(objekt);
              
              	 }
             	it= strassereplace.getPersonen().iterator();
             	 while(it.hasNext()){
             		 Personen person=(Personen)it.next();
             		 person.setStrasse(strasse);
             		 strasse.addPerson(person);
            
             	 }
             	
             	strassereplace.setPersonen(new ArrayList<Personen>());
             	strassereplace.setObjekte(new ArrayList<Objekte>());
              	strassenManager.save(strasse);
             	strassenManager.removeStrassen(strassereplace);
               }         
             
            
             

            if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal().equals("objectoriginal")&&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace().equals("objecttoreplace")) {
               	Objekte objekt=objektManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjectoriginal()));
            	Objekte objektreplace=objektManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getObjecttoreplace()));
               	List<Nachweise> nachweise=objektreplace.getNachweise();
               	Iterator it=nachweise.iterator();
               	 while(it.hasNext()){
               		 Nachweise nachweis=(Nachweise)it.next();
               		 nachweis.setObjekt(objekt);
               		 objekt.addNachweise(nachweis);
            
               	 }
               	objektreplace.setNachweise(new ArrayList<Nachweise>());
               	List<Scout> scouts=objektreplace.getScouts();
              	it= scouts.iterator();
              	 while(it.hasNext()){
              		 Scout scout=(Scout)it.next();
              		 scout.setObjekt(objekt);
              		 objekt.addScout(scout);

              	 }
             	objektreplace.setScouts(new ArrayList<Scout>());
              	it= objektreplace.getAngobjzuords().iterator();
             	 while(it.hasNext()){
             		 Angobjzuord angobjzuord=(Angobjzuord)it.next();
             		angobjzuord.setObjekte(objekt);
             		objekt.addAngobjzuord(angobjzuord);
             	 }
              	objektreplace.setAngobjzuords(new ArrayList<Angobjzuord>());
             
            	 it= objektreplace.getObjperszuords().iterator();
            	 while(it.hasNext()){
            		 Objperszuord objperszuord=(Objperszuord)it.next();
            		 objperszuord.setObjekt(objekt);
            		 objekt.addObjperszuord(objperszuord);
            
            	 }
            	 objektreplace.setObjperszuords(new ArrayList<Objperszuord>());
                 objektManager.save(objekt);
                 objektManager.remove(objektreplace);
            
                }  
             
             
            if(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal().equals("personoriginal")&&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace()!=null &&((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace().length()>0&&!((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace().equals("persontoreplace")) {
                
             	Personen person=personManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersonoriginal()));
            	Personen personreplace=personManager.get(new Long(((ReplaceModel) Repair.this.bootstrapForm.getDefaultModelObject()).getPersontoreplace()));
            	
            	
            	Iterator it=personreplace.getNachweise().iterator();
                	 while(it.hasNext()){
                		 Nachweise nachweis=(Nachweise)it.next();
                		 nachweis.setPerson(person);
                		person.addNachweis(nachweis);
                	 }
                	 personreplace.setNachweise(new ArrayList<Nachweise>()); 
               	it= personreplace.getScouts().iterator();
               	 while(it.hasNext()){
               		 Scout scout=(Scout)it.next();
               		 scout.setPerson(person);
               		 person.addScout(scout);
               		
               	 }
               	 personreplace.setScouts(new ArrayList<Scout>()); 
               	it= personreplace.getKunden().iterator();
              	 while(it.hasNext()){
              		 Kunde kunde=(Kunde)it.next();
              		 kunde.setPerson(person);
              		 person.addKunde(kunde);
              	 }
              	 personreplace.setKunden(new ArrayList<Kunde>()); 
             	 it= personreplace.getObjperszuords().iterator();
             	 while(it.hasNext()){
             		 Objperszuord objperszuord=(Objperszuord)it.next();
             		 objperszuord.setPersonen(person);
             		 person.addObjperszuord(objperszuord);
             		
             	 }
             	 personreplace.setObjperszuords(new ArrayList<Objperszuord>());
             	 it= personreplace.getEigentuemermuster().iterator();
             	 Eigentuemermuster muster=null;
             	 while(it.hasNext()){
             		muster=(Eigentuemermuster)it.next();
             
             eigentuemermusterManager.save(muster);
            
            
             	 }   
           
             	 personreplace.setEigentuemermuster(new ArrayList<Eigentuemermuster>());
             	
           
                	 personManager.remove( personreplace);
                 }        
             
              }

         });  
         
	       
	  
	  
	  
	  
	  
	  
}
public class CounterUpdate
{
    private final AjaxRequestTarget target;

    /**
     * Constructor
     * 
     * @param target
     */
    public CounterUpdate(AjaxRequestTarget target)
    {
        this.target = target;
    }

    /** @return ajax request target */
    public AjaxRequestTarget getTarget()
    {
        return target;
    }
}	


 public class CounterLabel extends Label
    {

        /**
         * Construct.
         * 
         * @param id
         */
        public CounterLabel(String id,String field)
        {
            super(id, new PropertyModel<String>((ReplaceModel)Repair.this.bootstrapForm.getDefaultModelObject(), field));
            setOutputMarkupId(true);
        }

        /**
         * @see org.apache.wicket.Component#onEvent(org.apache.wicket.event.IEvent)
         */
        @Override
        public void onEvent(IEvent<?> event)
        {
            super.onEvent(event);

            // check if this is a counter update event and if so repaint self
            if (event.getPayload() instanceof CounterUpdate)
            {
                CounterUpdate update = (CounterUpdate)event.getPayload();
                update.getTarget().add(this);
            }
        }

    }


}
