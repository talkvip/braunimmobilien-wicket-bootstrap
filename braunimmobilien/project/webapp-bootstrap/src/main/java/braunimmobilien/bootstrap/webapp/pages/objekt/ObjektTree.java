package braunimmobilien.bootstrap.webapp.pages.objekt;

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
import braunimmobilien.model.Personen;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Scout;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.angebot.AngebotTree;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.AngebotManager;
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
@MountPath(value = "/objekttree")
public class ObjektTree extends BasePage {
	private static final long serialVersionUID = 1L;
	 @SpringBean
	    private AngebotManager angebotManager;
	 @SpringBean
	    private EigentuemertypManager eigentuemertypManager;
	 @SpringBean
	    private ObjektManager objektManager;
	 @SpringBean
	    private PersonManager personManager;
	 Objekte objekt=null;

	
	
	
	public ObjektTree(PageParameters parametersexternal)
	
	{ super(parametersexternal);
	PageParameters pageparameters=new PageParameters();
	WicketApplication.foos.clear();
	 MyFoo fooAB=new MyFoo("error",null);
 if(parametersexternal.getPosition("objid")>-1){
	
pageparameters.add("objid", parametersexternal.get("objid").toString());
		
		try{objekt=objektManager.get(new Long(parametersexternal.get("objid").toString()));	
		
		 fooAB=new MyFoo(objekt.getObjektart().getObjartname(),new IndexBootstrap(ObjektTree.class,pageparameters));
			
		 
		
		
		
		
		 
   Iterator iterator2=objekt.getNachweise().iterator();

				while(iterator2.hasNext()){
				
			Nachweise nachweis=(Nachweise)iterator2.next();	
			
			showNachweis(nachweis,pageparameters,fooAB);
			
				}	
    
			
     PageParameters parameters3=new PageParameters();
	 parameters3.mergeWith(pageparameters);
	 parameters3.add("nachweisid", "null"); 
     new MyFoo(fooAB, "weiterer Nachweis",new IndexBootstrap(ObjektTree.class,parameters3));	
  	
     
     
     if(objekt.getAngobjzuords().size()>0){ 
    	
    	 MyFoo fooAA=new MyFoo(fooAB, "angebote",null);
Iterator iterator=objekt.getAngobjzuords().iterator();
				while(iterator.hasNext()){
		Angobjzuord angobjzuord=(Angobjzuord)iterator.next();
		showAngebote(angobjzuord,pageparameters,fooAA);
				}
     }
     
     PageParameters parameters2=new PageParameters();
	 parameters2.mergeWith(pageparameters);
	 parameters2.add("angnr", "null"); 
   new MyFoo(fooAB, "weiteres Angebot",new IndexBootstrap(ObjektTree.class,parameters2));
		 if(objekt.getScouts().size()>0){
				MyFoo fooABC=new MyFoo(fooAB, "scouts",null);
				
			Iterator iterator3=objekt.getScouts().iterator();
						while(iterator3.hasNext()){
							Scout scout=(Scout)iterator3.next();
						showScout(scout,pageparameters,fooABC);
						}
			}
		 if(objekt.getObjperszuords().size()>0){
			 MyFoo fooABC=new MyFoo(fooAB, "persons",null);
		 	Iterator iterator1=objekt.getObjperszuords().iterator();
		while(iterator1.hasNext()){
			Objperszuord objperszuord1=(Objperszuord)iterator1.next();
			showPerson(objperszuord1,pageparameters,fooABC);	
		} 
		}
			PageParameters parameters1=new PageParameters();
			 parameters1.mergeWith(pageparameters);
			 parameters1.add("eigtid", "null"); 
		 new MyFoo(fooAB, "weitere Person",new IndexBootstrap(ObjektTree.class,parameters1));
		
		}	catch(Exception ex){error(ex);
		System.err.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+ex);
	ex.printStackTrace();
		System.exit(5);
		}	
 }
		WicketApplication.foos.add(fooAB);
						add(new MyNestedTree("tree", new MyFooProvider()));
						}	
	
	
public ObjektTree()
	
	{ super();
	PageParameters pageparameters = new PageParameters();
		}	

private void showPerson(Objperszuord objperszuord,PageParameters pageparameters,MyFoo myfoo){
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters);
	parameters.add("eigtid", objperszuord.getPersonen().getId().toString());
	MyFoo fooAAB=null;
	if(objperszuord.getPersonen().getEigtName()!=null)
		
	fooAAB =new MyFoo(myfoo, objperszuord.getEigentuemertyp().getTypenbeschreibung()+" "+objperszuord.getPersonen().getEigtName(),new IndexBootstrap(ObjektTree.class,parameters));
	else fooAAB =new MyFoo(myfoo,objperszuord.getPersonen().getEigtFirma()+","+ objperszuord.getPersonen().getStrasse().getStrplz()+" "+objperszuord.getPersonen().getStrasse().getOrt().getOrtname()+" "+objperszuord.getPersonen().getEigtHausnummer(),new WizardBootstrapPage(NewLandWizard.class,ObjektTree.class,pageparameters));
	if(objperszuord.getPersonen().getKunden().size()>0){
	
		Iterator iterator3=objperszuord.getPersonen().getKunden().iterator();
		while(iterator3.hasNext()){
			Kunde kunde=(Kunde)iterator3.next();
			PageParameters parameters1=new PageParameters();
			parameters1.mergeWith(parameters);
			parameters1.add("kundennr",kunde.getId());
			MyFoo fooAAC =new MyFoo(fooAAB,kunde.getId()+" "+kunde.getKundenart().getKundenart()+" "+kunde.getDatum(),new IndexBootstrap(ObjektTree.class,parameters1));
		
		}
	}
		
}


private void showAngebote(Angobjzuord angobjzuord,PageParameters pageparameters,MyFoo myfoo){
	
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters);
	parameters.add("angnr", angobjzuord.getAngebot().getId().toString());
	
	
			MyFoo fooAB=new MyFoo(myfoo, angobjzuord.getAngebot().getId().toString(),new IndexBootstrap(ObjektTree.class,parameters));
			PageParameters parameters1 = new PageParameters();
			 	
	
	
	
}

private void showNachweis(Nachweise nachweis,PageParameters pageparameters,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters);
	parameters.add("nachweisid", nachweis.getId().toString());
	
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(ObjektTree.class,parameters));
			PageParameters parameters1 = new PageParameters();
			parameters1.mergeWith(parameters);
		//	parameters1.add("kundennr", nachweis.getKunde().getId().toString());
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtName()+","+nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(ObjektTree.class,parameters1));
				}
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()==null&&nachweis.getKunde().getPerson().getEigtFirma()!=null){
		MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(ObjektTree.class,parameters));
		PageParameters parameters1 = new PageParameters();
		parameters1.mergeWith(parameters);
	//	parameters1.add("kundennr", nachweis.getKunde().getId().toString());
		MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtFirma()+","+ nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(ObjektTree.class,parameters1));
			}
	if(nachweis.getKunde()==null&&nachweis.getPerson()!=null&&nachweis.getPerson().getEigtName()!=null){
		PageParameters parameters1 = new PageParameters();
		parameters1.mergeWith(parameters);
	//	parameters1.add("eigtid", nachweis.getPerson().getId().toString());
		MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName()+" "+nachweis.getPerson().getEigtName(),new IndexBootstrap(ObjektTree.class,parameters1));
				}
if(nachweis.getKunde()==null&&nachweis.getPerson()!=null&&nachweis.getPerson().getEigtName()==null&&nachweis.getPerson().getEigtFirma()!=null){
	PageParameters parameters1 = new PageParameters();
	parameters1.mergeWith(parameters);
	//parameters1.add("eigtid", nachweis.getPerson().getId().toString());
	MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName()+" "+nachweis.getPerson().getEigtFirma(),new IndexBootstrap(ObjektTree.class,parameters1));
			}
	
	
}


private void showScout(Scout scout,PageParameters pageparameters,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters);
	parameters.add("scoutid", scout.getId().toString());
	
	String scouttext="";
	MyFoo fooAB=new MyFoo(myfoo,scout.getId().toString(),new IndexBootstrap(ObjektTree.class,parameters));
	
	if(scout.getPerson()!=null&&scout.getPerson().getEigtName()!=null){
		scouttext=scouttext+" "+scout.getPerson().getEigtName()+","+scout.getPerson().getStrasse().getStrplz()+" "+scout.getPerson().getStrasse().getOrt().getOrtname()+" "+scout.getPerson().getEigtHausnummer();
		
				}
	if(scout.getPerson()!=null&&scout.getPerson().getEigtName()==null&&scout.getPerson().getEigtFirma()!=null){
		scouttext=scouttext+" "+scout.getPerson().getEigtFirma()+","+scout.getPerson().getStrasse().getStrplz()+" "+scout.getPerson().getStrasse().getOrt().getOrtname()+" "+scout.getPerson().getEigtHausnummer();
		
	}	
	if(scout.getPerson()!=null&&scout.getPerson().getEigtName()==null&&scout.getPerson().getEigtFirma()==null){
	scouttext="no name";
	}
	if (scouttext.length()>0){
		PageParameters pars = new PageParameters();
		pars.mergeWith(parameters);
		parameters.add("eigtid", scout.getPerson().getId().toString());
	MyFoo fooAC=new MyFoo(fooAB,scouttext,new IndexBootstrap(ObjektTree.class,pars));}

}

}
