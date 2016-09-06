package braunimmobilien.bootstrap.webapp;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.protocol.http.WebApplication;
import braunimmobilien.model.Nutzer;

public class MaklerSession extends WebSession{
private String context="";


private Long landid=new Long(0);
private Long ortid=new Long(0);
private Long strasseid=new Long(0);
private Long objid=new Long(0);
private Nutzer nutzer;
public String getContext() {
	return context;
}
public void setContext(String context) {
	this.context = context;
}


public Nutzer getNutzer() {
	return nutzer;
}
public void setNutzer(Nutzer nutzer) {
	this.nutzer =nutzer;
}
public Long getObjid() {
	return objid;
}
public void setObjid(Long objid) {
	this.objid = objid;
}
public Long getStrasseid() {
	return strasseid;
}
public void setStrasseid(Long strasseid) {
	this.strasseid = strasseid;
}
public Long getOrtid() {
	return this.ortid;
}
public void setOrtid(Long ortid) {
	this.ortid = ortid;
}

	protected MaklerSession(Request request,Response response)
	{
		super(request);
	}
public Long getLandid(){
	
	return this.landid;
}	
public void setLandid(Long landid){
	
	this.landid=landid;
}
public static MaklerSession get(){
	return (MaklerSession) Session.get();
} 

public boolean isAuthenticated(){
	return (nutzer!=null);
}
}
