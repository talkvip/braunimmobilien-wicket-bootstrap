package braunimmobilien.bootstrap.webapp.pages.breadcrumb;
import java.io.Serializable;
import braunimmobilien.model.Eigentuemertyp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.model.IModel;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Angebot;
import braunimmobilien.model.Objekte;
import org.apache.wicket.Application;
import org.apache.wicket.spring.injection.annot.SpringBean;
import braunimmobilien.service.EntityLoader;
import org.apache.wicket.injection.Injector;
public class SearchModel implements IModel<Search>{
	@SpringBean
	public EntityLoader entityLoader;

	private static final long serialVersionUID = -1926446968284768121L;
	private Long eigentuemertypid;
	private Long landid;
	private Long orteid;
	private Long strasseid;
	private Long personid;
	private Long objektid;
	private Long kundennr;
	private String angnr;
	private String textsearch;
	protected final Log log = LogFactory.getLog(getClass());
private Search search;
	public SearchModel() {
		search=new Search();
		Injector.get().inject(this);
		log.debug("SearchModel() eigentuemerid "+eigentuemertypid+" landid "+landid +" orteid "+orteid+" strasseid "+strasseid+" personid "+personid+" objektid "+objektid+" kundennr "+kundennr+" angnr "+angnr+" textsearch "+textsearch);
		}

public Search getObject(){
	if(search!=null) {
		if(search.getLand()!=null) landid=search.getLand().getId();
		if(search.getEigentuemertyp()!=null) eigentuemertypid=search.getEigentuemertyp().getId();
		if(search.getOrte()!=null) orteid=search.getOrte().getId();
		if(search.getStrasse()!=null) strasseid=search.getStrasse().getId();
		if(search.getPerson()!=null) personid=search.getPerson().getId();
		if(search.getObjekt()!=null) objektid=search.getObjekt().getId();
		if(search.getKunden()!=null) kundennr=search.getKunden().getId();
		if(search.getAngebote()!=null) angnr=search.getAngebote().getId();
		if(search.getTextsearch()!=null) textsearch=search.getTextsearch();
		search=null;}
		search=new Search();
if(this.eigentuemertypid!=null) search.setEigentuemertyp(entityLoader.load(Eigentuemertyp.class,eigentuemertypid));
if(this.landid!=null) search.setLand(entityLoader.load(Land.class,landid));
if(this.orteid!=null) search.setOrte(entityLoader.load(Orte.class,orteid));
if(this.strasseid!=null) search.setStrasse(entityLoader.load(Strassen.class,strasseid));
if(this.personid!=null) search.setPerson(entityLoader.load(Personen.class,personid));
if(this.objektid!=null) search.setObjekt(entityLoader.load(Objekte.class,objektid));
if(this.kundennr!=null) search.setKunden(entityLoader.load(Kunde.class,kundennr));
if(this.angnr!=null) search.setAngebote(entityLoader.load(Angebot.class,angnr));
if(this.textsearch!=null) search.setTextsearch(textsearch);
log.debug("Search getObject() eigentuemerid "+eigentuemertypid+" landid "+landid +" orteid "+orteid+" strasseid "+strasseid+" personid "+personid+" objektid "+objektid+" kundennr "+kundennr+" angnr "+angnr+" textsearch "+textsearch);
return search;


}
public void setObject(Search search){
	if(search!=null){
		
		if(search.getLand()!=null) landid=search.getLand().getId();
		else landid=null;
		if(search.getEigentuemertyp()!=null) eigentuemertypid=search.getEigentuemertyp().getId();
		else eigentuemertypid=null;
		if(search.getOrte()!=null) orteid=search.getOrte().getId();
		else orteid=null;
		if(search.getStrasse()!=null) strasseid=search.getStrasse().getId();
		else strasseid=null;
		if(search.getPerson()!=null) personid=search.getPerson().getId();
		else personid=null;
		if(search.getObjekt()!=null) objektid=search.getObjekt().getId();
		else objektid=null;
		if(search.getKunden()!=null) kundennr=search.getKunden().getId();
		else kundennr=null;
		if(search.getAngebote()!=null) angnr=search.getAngebote().getId();
		else angnr=null;
		if(search.getTextsearch()!=null) textsearch=search.getTextsearch();
		else textsearch=null;
		this.search=search;
	}
	else{
		eigentuemertypid=null;
		landid=null;
		orteid=null;
		this.search= new Search();
	}
	
	log.debug("setObject(Search search) eigentuemerid "+eigentuemertypid+" landid "+landid +" orteid "+orteid+" strasseid "+strasseid+" personid "+personid+" objektid "+objektid+" kundennr "+kundennr+" angnr "+angnr+" textsearch "+textsearch);	
}
public void detach(){
	if (search!=null){
		if(search.getEigentuemertyp()!=null) eigentuemertypid=search.getEigentuemertyp().getId();
		else eigentuemertypid=null;
		if(search.getOrte()!=null) orteid=search.getOrte().getId();
		else orteid=null;
		if(search.getStrasse()!=null) strasseid=search.getStrasse().getId();
		else strasseid=null;
		if(search.getPerson()!=null) personid=search.getPerson().getId();
		else personid=null;
		if(search.getObjekt()!=null) objektid=search.getObjekt().getId();
		else objektid=null;
		if(search.getKunden()!=null) kundennr=search.getKunden().getId();
		else kundennr=null;
		if(search.getAngebote()!=null) angnr=search.getAngebote().getId();
		else angnr=null;
		if(search.getTextsearch()!=null) textsearch=search.getTextsearch();
		else textsearch=null;
	this.search=null;}
	log.debug("detach() eigentuemerid "+eigentuemertypid+" landid "+landid +" orteid "+orteid+" strasseid "+strasseid+" personid "+personid+" objektid "+objektid+" kundennr "+kundennr+" angnr "+angnr+" textsearch "+textsearch);}



}
