package braunimmobilien.bootstrap.webapp.pages.angebot;

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
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Scout;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
import braunimmobilien.bootstrap.webapp.pages.BasePage;
import braunimmobilien.service.AngebotManager;
import braunimmobilien.service.PersonManager;
import braunimmobilien.service.ObjektManager;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */
@MountPath(value = "/angebotewizardtree")
public class AngebotWizardTree extends BasePage {
	private static final long serialVersionUID = 1L;
	 @SpringBean
	    private AngebotManager angebotManager;
	 @SpringBean
	    private ObjektManager objektManager;
	 @SpringBean
	    private PersonManager personManager;
	 Angebot angebot=null;

	
	
	
	public AngebotWizardTree(PageParameters pageparameters)
	
	{ super(pageparameters);
		
 if(pageparameters.getPosition("angnr")>-1){
	 
	/*MaklerFlow maklerflow = new MaklerFlow();
	HashMap<String,String> map=new HashMap<String,String>();
	map.put("state","angebot");
	map.put("angnr",pageparameters.get("angnr").toString());	
	maklerflow.add(map);*/
		WicketApplication.foos.clear();
		try{angebot=angebotManager.get(pageparameters.get("angnr").toString());	
	
		 
		 }
		catch(Exception ex){System.err.println("YYYYYYYYYYYYYYYYYYYYYYYYYYY"+ex);
		System.exit(1);}
		PageParameters parameters4 = new PageParameters();
		 parameters4.add("angnr", angebot.getId().toString());   
		MyFoo fooA = new MyFoo(angebot.getId(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters4)); 
     WicketApplication.foos.add(fooA);
     if(angebot.getNachweise().size()>0){
    		MyFoo fooAA=new MyFoo(fooA, "nachweise",null);
    		
     Iterator iterator2=angebot.getNachweise().iterator();
 	int j=0;
 				while(iterator2.hasNext()){
 					++j;
 			Nachweise nachweis=(Nachweise)iterator2.next();	
 			showNachweis(nachweis,angebot.getId().toString(),fooAA);
 			
 				}	
 				
 				  iterator2=angebot.getNachweise1().iterator();
 			
 			 				while(iterator2.hasNext()){
 			 					++j;
 			 			Nachweise nachweis=(Nachweise)iterator2.next();	
 			 			showNachweis(nachweis,angebot.getId().toString(),fooAA);
 			 				}		
 			 				 iterator2=angebot.getNachweise2().iterator();
 			 			 
 			 			 				while(iterator2.hasNext()){
 			 			 					++j;
 			 			 			Nachweise nachweis=(Nachweise)iterator2.next();	
 			 			 			showNachweis(nachweis,angebot.getId().toString(),fooAA);
 			 			 				}		
 				
     }		
 					
Iterator iterator=angebot.getAngobjzuords().iterator();
	      int i=0;
				while(iterator.hasNext()){
					++i;
		Angobjzuord angobjzuord=(Angobjzuord)iterator.next();
		PageParameters parameters = new PageParameters();
		 parameters.add("angnr", angebot.getId());   
		parameters.add("objid", angobjzuord.getObjekte().getId());
		MyFoo fooAB=new MyFoo(fooA, angobjzuord.getObjekte().getObjektart().getObjartname()+","+angobjzuord.getObjekte().getStrasse().getStrplz()+" "+angobjzuord.getObjekte().getStrasse().getOrt().getOrtname()+" "+angobjzuord.getObjekte().getObjhausnummer(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
		if(angobjzuord.getObjekte().getScouts().size()>0){
			MyFoo fooABC=new MyFoo(fooAB, "scouts",null);
			
		Iterator iterator2=angobjzuord.getObjekte().getScouts().iterator();
					while(iterator2.hasNext()){
						Scout scout=(Scout)iterator2.next();
					showScout(scout,scout.getId().toString(),fooABC);
					}
		}
		PageParameters parameters1 = new PageParameters();
		 parameters1.add("angnr", angebot.getId());   
		parameters1.add("objid", angobjzuord.getObjekte().getId());
		parameters1.add("eigtid","0");
		new MyFoo(fooAB, "weitere Person",new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters1));
		Iterator iterator1=angobjzuord.getObjekte().getObjperszuords().iterator();
		while(iterator1.hasNext()){
			Objperszuord objperszuord=(Objperszuord)iterator1.next();
			PageParameters parameters2 = new PageParameters();
			 parameters2.add("angnr", angebot.getId());   
			parameters2.add("objid", angobjzuord.getObjekte().getId());
			parameters2.add("eigtid",objperszuord.getPersonen().getId());
			showPerson(objperszuord.getPersonen(),parameters2,fooAB);
			
	//		MyFoo fooAAB=null;	
	//		if(objperszuord.getPersonen().getEigtName()!=null)
	//		fooAAB =new MyFoo(fooAB, objperszuord.getPersonen().getEigtName()+","+ objperszuord.getPersonen().getStrasse().getStrplz()+" "+objperszuord.getPersonen().getStrasse().getOrt().getOrtname()+" "+objperszuord.getPersonen().getEigtHausnummer(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters2));
	//		else fooAAB =new MyFoo(fooAB, objperszuord.getPersonen().getEigtFirma()+","+ objperszuord.getPersonen().getStrasse().getStrplz()+" "+objperszuord.getPersonen().getStrasse().getOrt().getOrtname()+" "+objperszuord.getPersonen().getEigtHausnummer(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters2));
		}
	}
		
			PageParameters parameters = new PageParameters();
			 parameters.add("angnr", angebot.getId());   
			 parameters.add("objid", "0");  
			new MyFoo(fooA, "weiteres Objekt",new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
				add(new MyNestedTree("tree", new MyFooProvider()));}
	angebot=null;
	}	
public AngebotWizardTree()
	
	{ super();
	PageParameters pageparameters = new PageParameters();
	pageparameters.add("angnr", "VB110");
		System.err.println("ÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖ"+pageparameters);
 if(pageparameters.getPosition("angnr")>-1){
	 Angebot angebot=null;
	
		WicketApplication.foos.clear();
		try{angebot=angebotManager.get(pageparameters.get("angnr").toString());	
		Iterator iterator=angebot.getAngobjzuords().iterator();
		if(pageparameters.getPosition("objid")>-1){ 
			 Objekte objekt=objektManager.get( pageparameters.get("objid").toLong());
			 if(pageparameters.getPosition("eigtid")>-1){
				Personen person=personManager.get(pageparameters.get("eigtid").toLong()); 
				Objperszuord objperszuord=new Objperszuord();
				objperszuord.setObjekt(objekt);
				objperszuord.setPersonen(person);
				objekt.addObjperszuord(objperszuord);
			 } 
			 else{
				 Angobjzuord angobjzuord=new Angobjzuord();
			
			angobjzuord.setAngebot(angebot);
			angobjzuord.setObjekte(objekt);
			angobjzuord.setAktuell(true);
			angebot.addAngobjzuord(angobjzuord);}
			angebotManager.saveAngebot(angebot);
			 
		 }}
		catch(Exception ex){System.err.println("YYYYYYYYYYYYYYYYYYYYYYYYYYY"+ex);
		System.exit(1);}
		
		MyFoo fooA = new MyFoo(angebot.getId(),new AngebotPage(new AngebotList(),angebot));   
     WicketApplication.foos.add(fooA);

Iterator iterator=angebot.getAngobjzuords().iterator();
	int i=0;
				while(iterator.hasNext()){
					++i;
		Angobjzuord angobjzuord=(Angobjzuord)iterator.next();
		PageParameters parameters = new PageParameters();
		 parameters.add("angnr", angebot.getId());   
		parameters.add("objid", angobjzuord.getObjekte().getId());
		MyFoo fooAB=new MyFoo(fooA, angebot.getId()+"Objekt"+i+" "+angobjzuord.getObjekte().getObjektart().getObjartname(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
		
		
		new MyFoo(fooAB, "weitere Person",new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
		Iterator iterator1=angobjzuord.getObjekte().getObjperszuords().iterator();
		while(iterator1.hasNext()){
			Objperszuord objperszuord=(Objperszuord)iterator1.next();
			MyFoo fooAAB =new MyFoo(fooAB, objperszuord.getPersonen().getEigtName(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
		}
	}
				Angobjzuord angobjzuord1=new Angobjzuord();
				angobjzuord1.setAngebot(angebot);
			angebot.addAngobjzuord(angobjzuord1);
			PageParameters parameters = new PageParameters();
			 parameters.add("angnr", angebot.getId());   
		
			new MyFoo(fooA, "weiteres Objekt",new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
			add(new MyNestedTree("tree", new MyFooProvider()));}
	}	
private void showNachweis(Nachweise nachweis,String angnr,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	 parameters.add("angnr", angnr);   
	parameters.add("nachweisid", nachweis.getId());
	
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtName()+","+nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),null);
				}
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()==null&&nachweis.getKunde().getPerson().getEigtFirma()!=null){
		MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),null);
		MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtFirma()+","+ nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),null);
			}
}
	private void showScout(Scout scout,String angnr,MyFoo myfoo){
		
		
		PageParameters parameters = new PageParameters();
		 parameters.add("angnr", angnr);   
		parameters.add("scoutid", scout.getId());
		
		String scouttext="";
		MyFoo fooAB=new MyFoo(myfoo,scout.getId().toString(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
		
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
		MyFoo fooAC=new MyFoo(fooAB,scouttext,new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));}
	
}
private void showPerson(Personen person,PageParameters pageparameters,MyFoo myfoo){
		
	MyFoo fooAAB=null;
	if(person.getEigtName()!=null)
	fooAAB =new MyFoo(myfoo,person.getEigtName()+","+ person.getStrasse().getStrplz()+" "+person.getStrasse().getOrt().getOrtname()+" "+person.getEigtHausnummer(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,pageparameters));
	else fooAAB =new MyFoo(myfoo, person.getEigtFirma()+","+ person.getStrasse().getStrplz()+" "+person.getStrasse().getOrt().getOrtname()+" "+person.getEigtHausnummer(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,pageparameters));
	if(person.getKunden().size()>0){
	
		Iterator iterator3=person.getKunden().iterator();
		while(iterator3.hasNext()){
			Kunde kunde=(Kunde)iterator3.next();
			PageParameters parameters=new PageParameters();
			parameters.mergeWith(pageparameters);
			parameters.add("kundennr",kunde.getId());
			MyFoo fooAAC =new MyFoo(fooAAB,kunde.getId()+" "+kunde.getKundenart().getKundenart()+" "+kunde.getDatum(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,parameters));
		
		}
	}
		
}	
}
