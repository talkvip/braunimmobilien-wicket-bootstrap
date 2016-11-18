package braunimmobilien.bootstrap.webapp.pages.scout;

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
import braunimmobilien.service.ScoutManager;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import braunimmobilien.bootstrap.webapp.pages.search.strasse.StrassenSuchePage;
import braunimmobilien.bootstrap.webapp.pages.breadcrumb.IndexBootstrap;
import braunimmobilien.bootstrap.webapp.pages.tree.MyNestedTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Page to manage and display users.
 * 
 * @author mraible
 */
@MountPath(value = "/scoutbreadcrumbtree")
public class ScoutTree extends BasePage {
	private static final long serialVersionUID = 1L;
	 @SpringBean
	    private AngebotManager angebotManager;
	 @SpringBean
	    private EigentuemertypManager eigentuemertypManager;
	 @SpringBean
	    private ObjektManager objektManager;
	 @SpringBean
	    private PersonManager personManager;
	 @SpringBean
	    private ScoutManager scoutManager;
	 
	 Scout scout=null;

	 static Logger logger = LoggerFactory.getLogger(ScoutTree.class);
	
	Label who = new Label("who","who");
	
	Label where = new Label("where","where");
	
	Label what = new Label("what","what");
	
	Label telefon = new Label("telefon","telefon");
	
	Label id = new Label("id","id");
	
	Label objarttyp = new Label("objarttyp","objarttyp");
	
	Label type = new Label("type","type");
	
	Label ort = new Label("ort","ort");
	 
	public ScoutTree(PageParameters pageparameters)
	
	{ super(pageparameters);
		logger.debug("ScouTree init PageParameters : "+pageparameters);
		 FeedbackPanel feedback=new FeedbackPanel("feedback");
			add(feedback);
			if(pageparameters.getPosition("error")>-1){
				error(pageparameters.get("error").toString());
				pageparameters.remove("error");
			}
		add(who);
		add(where);
		add(what);
		add(telefon);
		add(id);
		add(objarttyp);
		add(type);
		add(ort);
 if(pageparameters.getPosition("scoutid")>-1){
		WicketApplication.foos.clear();
		try{scout=scoutManager.get(new Long(pageparameters.get("scoutid").toString()));	
		 }
		catch(Exception ex){
			 logger.debug("ScoutTree scout record doesn't exist "+pageparameters.get("scoutid").toString());
		error("ScoutTree scout record doesn't exist "+pageparameters.get("scoutid").toString());
		}
		if (scout!=null){
			
			who.setDefaultModelObject(scout.getWho());
			where.setDefaultModelObject(scout.getWhere());
			what.setDefaultModelObject(scout.getWhat());
			telefon.setDefaultModelObject(scout.getPhone());
			id.setDefaultModelObject(scout.getId().toString());
			objarttyp.setDefaultModelObject(scout.getObjarttyp().getTypentext());
			type.setDefaultModelObject(scout.getType().getType().toString());
			ort.setDefaultModelObject(scout.getOrt().getOrtname());
		PageParameters parametersa = new PageParameters();
		 parametersa.add("scoutid", scout.getId().toString());   
		MyFoo fooA = new MyFoo(scout.getId().toString(),new IndexBootstrap(ScoutTree.class,parametersa)); 
		if (scout.getPerson()==null) {
			PageParameters parametersw = new PageParameters();
			parametersw.mergeWith(parametersa);
			parametersw.add("eigtid", "null");
	if(scout.getWho()!=null)		parametersw.add("who", scout.getWho());
	if(scout.getPhone()!=null)			parametersw.add("phone", scout.getPhone());
			MyFoo fooAX =new MyFoo(fooA,"Person zuordnen",new IndexBootstrap(ScoutTree.class,parametersw));}
		
		showPerson(scout.getPerson(),parametersa,fooA);
		if (scout.getObjekt()==null) {
			PageParameters parametersr = new PageParameters();
		parametersr.mergeWith(parametersa);
		parametersr.add("objid", "null");
		if(scout.getWhere()!=null)		parametersr.add("where", scout.getWhere());
		MyFoo fooAY =new MyFoo(fooA,"Objekt zuordnen",new IndexBootstrap(ScoutTree.class,parametersr));}
		showObjekt(scout.getObjekt(),parametersa,fooA);
		WicketApplication.foos.add(fooA);}
     
				add(new MyNestedTree("tree", new MyFooProvider()));}
	}	

private void showPerson(Personen person,PageParameters pageparameters,MyFoo myfoo){
	if (person!=null){
	MyFoo fooAAB=null;
	PageParameters parametersp=new PageParameters();
	parametersp.mergeWith(pageparameters);
	parametersp.add("eigtid",person.getId().toString());
	if(person.getEigtName()!=null)
		
	fooAAB =new MyFoo(myfoo, person.getEigtName(),new IndexBootstrap(ScoutTree.class,parametersp));
	else fooAAB =new MyFoo(myfoo,person.getEigtFirma()+","+ person.getStrasse().getStrplz()+" "+person.getStrasse().getOrt().getOrtname()+" "+person.getEigtHausnummer(),new IndexBootstrap(ScoutTree.class,parametersp));
	if(person.getKunden().size()>0){
	
		Iterator iterator3=person.getKunden().iterator();
		while(iterator3.hasNext()){
			Kunde kunde=(Kunde)iterator3.next();
			PageParameters parameters=new PageParameters();
			parameters.mergeWith(parametersp);
			parameters.add("kundennr",kunde.getId());
			MyFoo fooAAC =new MyFoo(fooAAB,kunde.getId()+" "+kunde.getKundenart().getKundenart()+" "+kunde.getDatum(),new IndexBootstrap(ScoutTree.class,parameters));
		
		}
	}
	}	
}

private void showObjekt(Objekte objekt,PageParameters pageparameters,MyFoo myfoo){
	if (objekt!=null){
	MyFoo fooAAB=null;
	PageParameters parameters = new PageParameters();
	parameters.mergeWith(pageparameters);  
	 parameters.add("objid", objekt.getId().toString()); 
	
	 MyFoo fooAB=new MyFoo(myfoo, "Objekt "+objekt.getObjektart().getObjartname(),new IndexBootstrap(ScoutTree.class,parameters));
	}	
}


private void showNachweis(Nachweise nachweis,String angnr,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	 parameters.add("angnr", angnr);   
	parameters.add("nachweisid", nachweis.getId().toString());
	
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()!=null){
			MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(ScoutTree.class,parameters));
			PageParameters parameters1 = new PageParameters();
			 parameters1.add("angnr", angnr);   
			parameters1.add("kundennr", nachweis.getKunde().getId().toString());
			parameters1.add("nachweisid", nachweis.getId().toString());
			MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtName()+","+nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(ScoutTree.class,parameters1));
				}
	if(nachweis.getKunde()!=null&&nachweis.getKunde().getPerson()!=null&&nachweis.getKunde().getPerson().getEigtName()==null&&nachweis.getKunde().getPerson().getEigtFirma()!=null){
		MyFoo fooAB=new MyFoo(myfoo, nachweis.getXtyp().getXtypkuerzel()+" "+nachweis.getNachdatum()+" "+nachweis.getMitarbeiter().getPerson().getEigtName(),new IndexBootstrap(ScoutTree.class,parameters));
		PageParameters parameters1 = new PageParameters();
		 parameters1.add("angnr", angnr);   
		parameters1.add("kundennr", nachweis.getKunde().getId().toString());
		parameters1.add("nachweisid", nachweis.getId().toString());
		MyFoo fooAC=new MyFoo(fooAB,nachweis.getKunde().getId()+" "+nachweis.getKunde().getPerson().getEigtFirma()+","+ nachweis.getKunde().getPerson().getStrasse().getStrplz()+" "+nachweis.getKunde().getPerson().getStrasse().getOrt().getOrtname()+" "+nachweis.getKunde().getPerson().getEigtHausnummer(),new IndexBootstrap(ScoutTree.class,parameters1));
			}
}


private void showScout(Scout scout,String angnr,MyFoo myfoo){
	
	
	PageParameters parameters = new PageParameters();
	 parameters.add("angnr", angnr);   
	parameters.add("scoutid", scout.getId().toString());
	
	String scouttext="";
	MyFoo fooAB=new MyFoo(myfoo,scout.getId().toString(),new IndexBootstrap(ScoutTree.class,parameters));
	
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
	MyFoo fooAC=new MyFoo(fooAB,scouttext,new IndexBootstrap(ScoutTree.class,parameters));}

}

}
