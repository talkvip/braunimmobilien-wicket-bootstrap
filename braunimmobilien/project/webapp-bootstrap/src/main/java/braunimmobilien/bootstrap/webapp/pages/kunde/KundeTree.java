package braunimmobilien.bootstrap.webapp.pages.kunde;

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
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */
@MountPath(value = "/kundebreadcrumbtree")
public class KundeTree extends BasePage {
	private static final long serialVersionUID = 1L;
	 @SpringBean
	    private KundeManager kundeManager;
	 @SpringBean
	    private EigentuemertypManager eigentuemertypManager;
	 @SpringBean
	    private ObjektManager objektManager;
	 @SpringBean
	    private PersonManager personManager;
	 Kunde kunde=null;

	
	
	
	public KundeTree(PageParameters pageparameters)
	
	{ super(pageparameters);
	FeedbackPanel feedback=new FeedbackPanel("feedback");
	add(feedback);
	if(pageparameters.getPosition("error")>-1){
		error(pageparameters.get("error").toString());
		pageparameters.remove("error");
	}
	 WicketApplication.foos.clear();
	 MyFoo fooA = null;
		try{kunde=kundeManager.get(new Long(pageparameters.get("kundennr").toString()));	 
		 }
		catch(Exception ex){
			fooA	= new MyFoo(pageparameters.get("kundennr").toString()+ " "+ex,null);   
		kunde = new Kunde();}
		if(kunde.getId()!=null){
		PageParameters parametersa = new PageParameters();
		 parametersa.add("kundennr", kunde.getId().toString());   
	fooA = new MyFoo(kunde.getId().toString(),new IndexBootstrap(KundeTree.class,parametersa));   
     WicketApplication.foos.add(fooA);
     add(new MyNestedTree("tree", new MyFooProvider()));
 /*    if(kunde.getPerson()!=null){
    showPerson(kunde.getPerson(),pageparameters,fooA);	 	 
     }
     
     
     
     
     if(kunde.getNachweise().size()>0){
 		MyFoo fooAA=new MyFoo(fooA, "nachweise",null);
 		
  Iterator iterator2=kunde.getNachweise().iterator();
	int j=0;
				while(iterator2.hasNext()){
					++j;
					PageParameters parameterskn = new PageParameters();
					 parameterskn.mergeWith(parametersa);
			Nachweise nachweis=(Nachweise)iterator2.next();	
			parameterskn.add("nachweisid", nachweis.getId().toString());
			showNachweis(nachweis,parameterskn,fooAA);
			
				}
     }
		}*/
 }
	}
	
	
public KundeTree()
	
	{ super();
		}	

private void showPerson(Personen person,PageParameters pageparameters,MyFoo myfoo){
	PageParameters parameters=new PageParameters();
	parameters.mergeWith(pageparameters);
	parameters.add("eigtid", person.getId().toString());
	MyFoo fooAAB=null;
	if(person.getEigtName()!=null) fooAAB =new MyFoo(myfoo,person.getEigtName(),new IndexBootstrap(KundeTree.class,parameters));
	else fooAAB =new MyFoo(myfoo,person.getEigtFirma()+","+ person.getStrasse().getStrplz()+" "+person.getStrasse().getOrt().getOrtname()+" "+person.getEigtHausnummer(),new IndexBootstrap(KundeTree.class,parameters));
	if(person.getObjperszuords().size()>0){
		Iterator iterator3=person.getObjperszuords().iterator();
		while(iterator3.hasNext()){
			Objperszuord objperszuord=(Objperszuord)iterator3.next();
			showObjekt(objperszuord,parameters,fooAAB);
		}
	}
	if(person.getNachweise().size()>0){
		Iterator iterator4=person.getNachweise().iterator();
		while(iterator4.hasNext()){
			Nachweise nachweis=(Nachweise)iterator4.next();
			showNachweisPerson(nachweis,parameters,fooAAB);
		}
	}
	if(person.getScouts().size()>0){
		Iterator iterator5=person.getScouts().iterator();
		while(iterator5.hasNext()){
			Scout scout=(Scout)iterator5.next();
			showScout(scout,parameters,fooAAB);
		}
	}
	
	if(person.getMitarbeiter().size()>0){
		Iterator iterator6=person.getMitarbeiter().iterator();
		while(iterator6.hasNext()){
			Mitarbeiter mitarbeiter=(Mitarbeiter)iterator6.next();
			PageParameters parametersm=new PageParameters();
			parametersm.mergeWith(pageparameters);
			parameters.add("mitarbeiternr", mitarbeiter.getId().toString());
			
		//	MyFoo fooAB=new MyFoo(myfoo, mitarbeiter.getPerson().getEigtName(),new IndexBootstrap(KundeTree.class,parameters));
			
		}
	}
	
	if(person.getEigentuemermuster().size()>0){
		Iterator iterator7=person.getEigentuemermuster().iterator();
		while(iterator7.hasNext()){
			Eigentuemermuster muster=(Eigentuemermuster)iterator7.next();
			PageParameters parametersmu=new PageParameters();
			parametersmu.mergeWith(pageparameters);
			parameters.add("musternr", muster.getId().toString());
			
		//	MyFoo fooAB=new MyFoo(myfoo, mitarbeiter.getPerson().getEigtName(),new IndexBootstrap(KundeTree.class,parameters));
			
		}
	}	
	
}	



private void showObjekt(Objperszuord objperszuord,PageParameters pageparameters,MyFoo myfoo){
	
	MyFoo fooAAB=null;
	
		
		PageParameters parameters=new PageParameters();
		parameters.mergeWith(pageparameters);
		parameters.add("objid", objperszuord.getObjekt().getId().toString());
		
		MyFoo fooAB=new MyFoo(myfoo, objperszuord.getEigentuemertyp().getTypenbeschreibung()+objperszuord.getObjekt().getObjektart().getObjartname(),new IndexBootstrap(KundeTree.class,parameters));
		
		
	}
		
private void showObjekt(Objekte objekt,PageParameters pageparameters,MyFoo myfoo){
	
	MyFoo fooAAB=null;
	
		
		PageParameters parameters=new PageParameters();
		parameters.mergeWith(pageparameters);
		parameters.add("objid", objekt.getId().toString());
		
		MyFoo fooAB=new MyFoo(myfoo, objekt.getObjektart().getObjartname(),new IndexBootstrap(KundeTree.class,parameters));
		
		
	}

private void showNachweis(Nachweise nachweis,PageParameters parameters,MyFoo myfoo){
	
	
	
	
	if(nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(KundeTree.class,parameters));
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtName()+","+nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(KundeTree.class,parameters));
				}
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()==null&&nachweis.getKunde().getPerson().getEigtFirma()!=null){
		MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(KundeTree.class,parameters));
		MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtFirma()+","+ nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(KundeTree.class,parameters));
			}
}

private void showNachweisPerson(Nachweise nachweis,PageParameters pageparameters,MyFoo myfoo){
	
	PageParameters pageparametersnp = new PageParameters();
	pageparametersnp.mergeWith(pageparameters);
	pageparametersnp.add("nachweisid", nachweis.getId().toString());
	if(nachweis.getObjekt()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(KundeTree.class,pageparametersnp));
			PageParameters parametersnpo = new PageParameters();
			parametersnpo.mergeWith(pageparametersnp);
			parametersnpo.add("objid", nachweis.getObjekt().getId().toString());
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getObjekt().getId().toString()+" "+nachweis.getObjekt().getObjektart().getObjartkurz()+","+nachweis.getObjekt().getStrasse().getStrplz()+" "+nachweis.getObjekt().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getObjekt().getObjhausnummer(),new IndexBootstrap(KundeTree.class,parametersnpo));
				}		
	}


private void showScout(Scout scout,PageParameters pageparameters,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters); 
	parameters.add("scoutid", scout.getId().toString());
	
	
	MyFoo fooAB=new MyFoo(myfoo,scout.getId().toString(),new IndexBootstrap(KundeTree.class,parameters));
	
	if(scout.getObjekt()!=null){
	showObjekt(scout.getObjekt(),parameters,fooAB);
	
	}

}

}
