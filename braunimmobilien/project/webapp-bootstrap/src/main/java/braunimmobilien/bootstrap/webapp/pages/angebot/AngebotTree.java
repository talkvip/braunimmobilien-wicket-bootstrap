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
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Nachweise;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Angobjzuord;
import braunimmobilien.model.Objperszuord;
import braunimmobilien.model.Scout;
import braunimmobilien.bootstrap.webapp.pages.IndexPage;
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
@MountPath(value = "/angebotbreadcrumbtree")
public class AngebotTree extends BasePage {
	private static final long serialVersionUID = 1L;
	 @SpringBean
	    private AngebotManager angebotManager;
	 @SpringBean
	    private EigentuemertypManager eigentuemertypManager;
	 @SpringBean
	    private ObjektManager objektManager;
	 @SpringBean
	    private PersonManager personManager;
	 Angebot angebot=null;

	
	
	
	public AngebotTree(PageParameters pageparameters1)
	
	{ super(pageparameters1);
	FeedbackPanel feedback=new FeedbackPanel("feedback");
	add(feedback);
	if(pageparameters1.getPosition("error")>-1){
		error(pageparameters1.get("error").toString());
		pageparameters1.remove("error");
	}
	 WicketApplication.foos.clear();
	 MyFoo fooA = null;
 if(pageparameters1.getPosition("angnr")>-1){
	 try{angebot=angebotManager.get(pageparameters1.get("angnr").toString());}
	 catch(Exception ex){
			fooA	= new MyFoo(pageparameters1.get("angnr").toString()+ " "+ex,null);   
			angebot = new Angebot();	
		}
	 
	 	if(angebot.getId()!=null){	
	 		PageParameters pageparameters = new PageParameters()
	 				.add("angnr", pageparameters1.get("angnr").toString());
	fooA	= new MyFoo(angebot.getId(),new IndexBootstrap(AngebotTree.class,pageparameters));   
  
     if(angebot.getNachweise().size()>0||angebot.getNachweise1().size()>0||angebot.getNachweise2().size()>0){
 		MyFoo fooAA=new MyFoo(fooA, "nachweise",null);
  Iterator iterator2=angebot.getNachweise().iterator();
				while(iterator2.hasNext()){
			Nachweise nachweis=(Nachweise)iterator2.next();	
			showNachweis(nachweis,pageparameters,fooAA);
				}	
				  iterator2=angebot.getNachweise1().iterator();
			
			 				while(iterator2.hasNext()){
			 			Nachweise nachweis=(Nachweise)iterator2.next();	
			 			showNachweis(nachweis,pageparameters,fooAA);
			 				}		
			 				 iterator2=angebot.getNachweise2().iterator();
			 			 
			 			 				while(iterator2.hasNext()){
			 			 			Nachweise nachweis=(Nachweise)iterator2.next();	
			 			 			showNachweis(nachweis,pageparameters,fooAA);
			 			 				}			
  }		
     
Iterator iterator=angebot.getAngobjzuords().iterator();
				while(iterator.hasNext()){
		Angobjzuord angobjzuord=(Angobjzuord)iterator.next();
		PageParameters parameters = new PageParameters();
		parameters.mergeWith(pageparameters);
		 parameters.add("objid", angobjzuord.getObjekte().getId()); 
		
		 MyFoo fooAB=new MyFoo(fooA, angobjzuord.getObjekte().getObjektart().getObjartname(),new IndexBootstrap(AngebotTree.class,parameters));
		 if(angobjzuord.getObjekte().getScouts().size()>0){
				MyFoo fooABC=new MyFoo(fooAB, "scouts",null);
				
			Iterator iterator2=angobjzuord.getObjekte().getScouts().iterator();
						while(iterator2.hasNext()){
						
							Scout scout=(Scout)iterator2.next();
						showScout(scout,parameters,fooABC);
						}
			}
		 
		 
		 
		 PageParameters parameters1 = new PageParameters();
		 parameters1.mergeWith(parameters); 
			 parameters1.add("eigtid", "null"); 
		 new MyFoo(fooAB, "weitere Person",new IndexBootstrap(AngebotTree.class,parameters1));
		Iterator iterator1=angobjzuord.getObjekte().getObjperszuords().iterator();
		while(iterator1.hasNext()){
			Objperszuord objperszuord1=(Objperszuord)iterator1.next();
			PageParameters parameters2 = new PageParameters();
			 parameters2.mergeWith(parameters);  
			 parameters2.add("eigtid", objperszuord1.getPersonen().getId()); 
			 showPerson(objperszuord1,parameters2,fooAB);
				
			
		}
	}
			
			PageParameters parameters = new PageParameters();
			 parameters.add("angnr", pageparameters.get("angnr").toString());   
			 parameters.add("objid", "null"); 
			new MyFoo(fooA, "weiteres Objekt",new IndexBootstrap(AngebotTree.class,parameters));
	 	}
 
			}
 				WicketApplication.foos.add(fooA);
				add(new MyNestedTree("tree", new MyFooProvider()));
	}	


private void showPerson(Objperszuord objperszuord,PageParameters pageparameters,MyFoo myfoo){
	
	MyFoo fooAAB=null;
	if(objperszuord.getPersonen().getEigtName()!=null)
		
	fooAAB =new MyFoo(myfoo, objperszuord.getEigentuemertyp().getTypenbeschreibung()+" "+objperszuord.getPersonen().getEigtName(),new IndexBootstrap(AngebotTree.class,pageparameters));
	else fooAAB =new MyFoo(myfoo,objperszuord.getPersonen().getEigtFirma()+","+ objperszuord.getPersonen().getStrasse().getStrplz()+" "+objperszuord.getPersonen().getStrasse().getOrt().getOrtname()+" "+objperszuord.getPersonen().getEigtHausnummer(),new WizardBootstrapPage(NewLandWizard.class,AngebotWizardTree.class,pageparameters));
	PageParameters parameters=new PageParameters();
	parameters.mergeWith(pageparameters);
	parameters.add("kundennr","null");
	MyFoo fooAAC =new MyFoo(fooAAB,"weiterer Kunde",new IndexBootstrap(AngebotTree.class,parameters));

	
	if(objperszuord.getPersonen().getKunden().size()>0){
	
		Iterator iterator3=objperszuord.getPersonen().getKunden().iterator();
		while(iterator3.hasNext()){
			Kunde kunde=(Kunde)iterator3.next();
			PageParameters parameters1=new PageParameters();
			parameters1.mergeWith(pageparameters);
			parameters1.add("kundennr",kunde.getId().toString());
			MyFoo fooAAD =new MyFoo(fooAAB,kunde.getId()+" "+kunde.getKundenart().getKundenart()+" "+kunde.getDatum(),new IndexBootstrap(AngebotTree.class,parameters1));
		
		}
	}
		
}
private void showNachweis(Nachweise nachweis,PageParameters pageparameters,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters);
	parameters.add("nachweisid", nachweis.getId().toString());
	
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(AngebotTree.class,parameters));
			PageParameters parameters1 = new PageParameters();
			parameters1.mergeWith(parameters);   
			parameters1.add("kundennr", nachweis.getKunde().getId().toString());
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtName()+","+nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(AngebotTree.class,parameters1));
				}
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()==null&&nachweis.getKunde().getPerson().getEigtFirma()!=null){
		MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(AngebotTree.class,parameters));
		PageParameters parameters1 = new PageParameters();
		parameters1.mergeWith(parameters);   
		parameters1.add("kundennr", nachweis.getKunde().getId().toString());
		MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtFirma()+","+ nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(AngebotTree.class,parameters1));
			}
}


private void showScout(Scout scout,PageParameters pageparameters,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters);  
	parameters.add("scoutid", scout.getId().toString());
	
	String scouttext="";
	MyFoo fooAB=new MyFoo(myfoo,scout.getId().toString(),new IndexBootstrap(AngebotTree.class,parameters));
	
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
	MyFoo fooAC=new MyFoo(fooAB,scouttext,new IndexBootstrap(AngebotTree.class,parameters));}

}

}
