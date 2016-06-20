package braunimmobilien.bootstrap.webapp.pages.person;

import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

import braunimmobilien.bootstrap.webapp.EntityModel;

import org.apache.wicket.AttributeModifier;
import org.wicketstuff.annotation.mount.MountPath;

import braunimmobilien.bootstrap.webapp.MaklerFlow;
import braunimmobilien.bootstrap.webapp.pages.tree.MyFoo;
import braunimmobilien.bootstrap.webapp.pages.provider.MyFooProvider;
import braunimmobilien.bootstrap.webapp.pages.wizard.NewLandWizard;
import braunimmobilien.bootstrap.webapp.WicketApplication;
import braunimmobilien.bootstrap.webapp.pages.wizard.WizardBootstrapPage;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import braunimmobilien.model.Angebot;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Mitarbeiter;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Eigentuemermuster;

import braunimmobilien.model.Scout;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotList;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotTree;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngobjzuordForm;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.KundeManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;
import braunimmobilien.service.EigentuemertypManager;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */
@MountPath(value = "/persontree")
public class PersonTree extends BasePage {
	private static final long serialVersionUID = 1L;
	 @SpringBean
	    private KundeManager kundeManager;
	 @SpringBean
	    private EigentuemertypManager eigentuemertypManager;
	 @SpringBean
	    private ObjektManager objektManager;
	 @SpringBean
	    private PersonManager personManager;
	 Personen person=null;

	 protected final Log log = LogFactory.getLog(getClass());
	
	
	public PersonTree(PageParameters externalpageparameters)
	
	{ super(externalpageparameters);
	WicketApplication.foos.clear();
	log.debug("Parameters  "+externalpageparameters);
	PageParameters pageparameters=new PageParameters();
	 MyFoo fooAB=new MyFoo("error",null);
	 FeedbackPanel feedback=new FeedbackPanel("feedback");
		add(feedback);
		if(externalpageparameters.getPosition("error")>-1){
			error(externalpageparameters.get("error").toString());
			externalpageparameters.remove("error");
		}
 if(externalpageparameters.getPosition("eigtid")>-1){
	 pageparameters.add("eigtid", externalpageparameters.get("eigtid").toString());
	
		
		try{person=personManager.get(new Long(pageparameters.get("eigtid").toString()));	
		 }
		catch(Exception ex){
			log.error(ex);
			fooAB=new MyFoo("error "+pageparameters.get("eigtid").toString()+" "+ex,null);
			person= new Personen();
		}
		    
    log.debug("person "+person);
    		
    		if(person.getEigtName()!=null) fooAB =new MyFoo(person.getEigtName(),new IndexBootstrap(PersonTree.class,pageparameters));
    		else fooAB =new MyFoo(person.getEigtFirma()+","+ person.getStrasse().getStrplz()+" "+person.getStrasse().getOrt().getOrtname()+" "+person.getEigtHausnummer(),new IndexBootstrap(PersonTree.class,pageparameters));
    		
    		 log.debug("persobjzuords "+person.getObjperszuords().size());
     		
    		if(person.getObjperszuords().size()>0){
    			MyFoo	fooAAB=new MyFoo(fooAB,"objekte",null);
    			Iterator iterator3=person.getObjperszuords().iterator();
    			while(iterator3.hasNext()){
    				Objperszuord objperszuord=(Objperszuord)iterator3.next();
    				showObjekt(objperszuord,pageparameters,fooAAB);
    			}
    		}
    		 log.debug("nachweise "+person.getNachweise().size());
    	     	
    		  /*	if(person.getNachweise().size()>0){
    			MyFoo	fooAAB=new MyFoo(fooAB,"nachweise",null);
    			Iterator iterator4=person.getNachweise().iterator();
    			while(iterator4.hasNext()){
    				Nachweise nachweis=(Nachweise)iterator4.next();
    				showNachweisPerson(nachweis,pageparameters,fooAAB);
    			}
    		}
    		
    		 log.debug("scouts "+person.getScouts().size());
    	     	
    					if(person.getScouts().size()>0){
    			MyFoo	fooAAB=new MyFoo(fooAB,"scouts",null);
    			Iterator iterator5=person.getScouts().iterator();
    			while(iterator5.hasNext()){
    				Scout scout=(Scout)iterator5.next();
    				showScout(scout,pageparameters,fooAAB);
    			}
    		}*/
    		 log.debug("mitarbeiter "+person.getMitarbeiter().size());
    		if(person.getMitarbeiter().size()>0){
    			MyFoo	fooAAB=new MyFoo(fooAB,"mitarbeiter",null);
    			Iterator iterator6=person.getMitarbeiter().iterator();
    			while(iterator6.hasNext()){
    				Mitarbeiter mitarbeiter=(Mitarbeiter)iterator6.next();
    				PageParameters parametersm=new PageParameters();
    				parametersm.mergeWith(pageparameters);
    				parametersm.add("mitarbeiternr", mitarbeiter.getId().toString());
    				
    			//	MyFoo fooAB=new MyFoo(myfoo, mitarbeiter.getPerson().getEigtName(),new IndexBootstrap(KundeTree.class,parameters));
    				
    			}
    		}
    		log.debug("eigentümermuster "+person.getEigentuemermuster().size());
    			if(person.getEigentuemermuster().size()>0){
    			MyFoo	fooAAB=new MyFoo(fooAB,"eigentuemermuster",null);
    			Iterator iterator7=person.getEigentuemermuster().iterator();
    			while(iterator7.hasNext()){
    				Eigentuemermuster muster=(Eigentuemermuster)iterator7.next();
    				PageParameters parametersmu=new PageParameters();
    				parametersmu.mergeWith(pageparameters);
    				parametersmu.add("musternr", muster.getId().toString());
    				
    			//	MyFoo fooAB=new MyFoo(myfoo, mitarbeiter.getPerson().getEigtName(),new IndexBootstrap(KundeTree.class,parameters));
    				
    			}
    		}	
    		log.debug("kunden "+person.getKunden().size());
    			if(person.getKunden().size()>0){
    			MyFoo	fooAAB=new MyFoo(fooAB,"kunden",null);
    			Iterator iterator8=person.getKunden().iterator();
    			while(iterator8.hasNext()){
    				Kunde kunde=(Kunde)iterator8.next();
    				showKunde(kunde,pageparameters,fooAAB);			
    			}
    		}
    		PageParameters parametersnk=new PageParameters();
			parametersnk.mergeWith(pageparameters);
			parametersnk.add("kundennr", "null");
    		MyFoo	fooAABC=new MyFoo(fooAB,"new client",new IndexBootstrap(PersonTree.class,parametersnk));
 }  
 WicketApplication.foos.add(fooAB);
 add(new MyNestedTree("tree", new MyFooProvider()));
	
	}
	
	
	
public PersonTree()
	
	{ super();
		}	

	
private void showKunde(Kunde kunde,PageParameters pageparameters,MyFoo myfoo){
	
	MyFoo fooAAB=null;
	
		
		PageParameters parameters=new PageParameters();
		parameters.mergeWith(pageparameters);
		parameters.add("kundennr", kunde.getId().toString());
		
		MyFoo fooAB=new MyFoo(myfoo, kunde.getId().toString(),new IndexBootstrap(PersonTree.class,parameters));
		
		
	}


private void showObjekt(Objperszuord objperszuord,PageParameters pageparameters,MyFoo myfoo){
	
	MyFoo fooAAB=null;
	
		
		PageParameters parameters=new PageParameters();
		parameters.mergeWith(pageparameters);
		parameters.add("objid", objperszuord.getObjekt().getId().toString());
		
		MyFoo fooAB=new MyFoo(myfoo, objperszuord.getEigentuemertyp().getTypenbeschreibung()+objperszuord.getObjekt().getObjektart().getObjartname(),new IndexBootstrap(PersonTree.class,parameters));
		
		
	}
		
private void showObjekt(Objekte objekt,PageParameters pageparameters,MyFoo myfoo){
	
	MyFoo fooAAB=null;
	
		
		PageParameters parameters=new PageParameters();
		parameters.mergeWith(pageparameters);
		parameters.add("objid", objekt.getId().toString());
		
		MyFoo fooAB=new MyFoo(myfoo, objekt.getObjektart().getObjartname(),new IndexBootstrap(PersonTree.class,parameters));
		
		
	}

private void showNachweis(Nachweise nachweis,PageParameters parameters,MyFoo myfoo){
	
	
	
	
	if(nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(PersonTree.class,parameters));
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtName()+","+nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(PersonTree.class,parameters));
				}
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()==null&&nachweis.getKunde().getPerson().getEigtFirma()!=null){
		MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(PersonTree.class,parameters));
		MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtFirma()+","+ nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(PersonTree.class,parameters));
			}
}

private void showNachweisPerson(Nachweise nachweis,PageParameters pageparameters,MyFoo myfoo){
	
	PageParameters pageparametersnp = new PageParameters();
	pageparametersnp.mergeWith(pageparameters);
	pageparametersnp.add("nachweisid", nachweis.getId().toString());
	if(nachweis.getObjekt()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(PersonTree.class,pageparametersnp));
			PageParameters parametersnpo = new PageParameters();
			parametersnpo.mergeWith(pageparametersnp);
			parametersnpo.add("objid", nachweis.getObjekt().getId().toString());
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getObjekt().getId().toString()+" "+nachweis.getObjekt().getObjektart().getObjartkurz()+","+nachweis.getObjekt().getStrasse().getStrplz()+" "+nachweis.getObjekt().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getObjekt().getObjhausnummer(),new IndexBootstrap(PersonTree.class,parametersnpo));
				}		
	}


private void showScout(Scout scout,PageParameters pageparameters,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters); 
	parameters.add("scoutid", scout.getId().toString());
	
	
	MyFoo fooAB=new MyFoo(myfoo,scout.getId().toString(),new IndexBootstrap(PersonTree.class,parameters));
	
	if(scout.getObjekt()!=null){
	showObjekt(scout.getObjekt(),parameters,fooAB);
	
	}

}

}