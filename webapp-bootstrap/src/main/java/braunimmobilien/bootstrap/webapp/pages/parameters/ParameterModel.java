package braunimmobilien.bootstrap.webapp.pages.parameters;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import braunimmobilien.model.*;
import braunimmobilien.service.AngstatusManager;
import braunimmobilien.service.EigentuemermusterManager;
import braunimmobilien.service.EigentuemertypManager;
import braunimmobilien.service.EigtstatusManager;
import braunimmobilien.service.KonditionManager;
import braunimmobilien.service.KundenartManager;
import braunimmobilien.service.MitarbeiterManager;
import braunimmobilien.service.ObjarttypManager;
import braunimmobilien.service.ObjektartManager;
import braunimmobilien.service.ObjektsuchManager;
import braunimmobilien.service.StatusManager;
import braunimmobilien.service.TelefonartManager;
import braunimmobilien.service.TypeManager;
import braunimmobilien.service.XtypManager;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
@SuppressWarnings("serial")
public class ParameterModel implements Serializable {
Angstatus angstatus;
Eigentuemertyp eigentuemertyp;
Eigtstatus eigtstatus;
Kundenart kundenart;
Objektart objektart;
Objektsuch objektsuch;
Status status;
Telefonart telefonart;
User user;
Xtyp xtyp;
Eigentuemermuster eigentuemermuster;
Kondition kondition;
Mitarbeiter mitarbeiter;
Objarttyp objarttyp;
Type type;  










public void makePageParameters(PageParameters pars) {
	if(getEigentuemermuster()!=null&&getEigentuemermuster().getId().longValue()>0)	
		pars.set("musterid",getEigentuemermuster().getId().toString());
	if(getXtyp()!=null&&getXtyp().getId().longValue()>0)	
		pars.set("xtypid",getXtyp().getId().toString());
			
	}
	

public ParameterModel() {
    
  }

public Eigentuemermuster getEigentuemermuster() {
	return eigentuemermuster;
}


public void setEigentuemermuster(Eigentuemermuster eigentuemermuster) {
	this.eigentuemermuster = eigentuemermuster;
}
public Xtyp getXtyp() {
	return xtyp;
}


public void setXtyp(Xtyp xtyp) {
	this.xtyp = xtyp;
}


public Angstatus getAngstatus() {
	return angstatus;
}


public void setAngstatus(Angstatus angstatus) {
	this.angstatus = angstatus;
}


public Eigentuemertyp getEigentuemertyp() {
	return eigentuemertyp;
}


public void setEigentuemertyp(Eigentuemertyp eigentuemertyp) {
	this.eigentuemertyp = eigentuemertyp;
}


public Eigtstatus getEigtstatus() {
	return eigtstatus;
}


public void setEigtstatus(Eigtstatus eigtstatus) {
	this.eigtstatus = eigtstatus;
}


public Kundenart getKundenart() {
	return kundenart;
}


public void setKundenart(Kundenart kundenart) {
	this.kundenart = kundenart;
}


public Objektart getObjektart() {
	return objektart;
}


public void setObjektart(Objektart objektart) {
	this.objektart = objektart;
}


public Objektsuch getObjektsuch() {
	return objektsuch;
}


public void setObjektsuch(Objektsuch objektsuch) {
	this.objektsuch = objektsuch;
}


public Status getStatus() {
	return status;
}


public void setStatus(Status status) {
	this.status = status;
}


public Telefonart getTelefonart() {
	return telefonart;
}


public void setTelefonart(Telefonart telefonart) {
	this.telefonart = telefonart;
}


public Kondition getKondition() {
	return kondition;
}


public void setKondition(Kondition kondition) {
	this.kondition = kondition;
}


public Mitarbeiter getMitarbeiter() {
	return mitarbeiter;
}


public void setMitarbeiter(Mitarbeiter mitarbeiter) {
	this.mitarbeiter = mitarbeiter;
}


public Objarttyp getObjarttyp() {
	return objarttyp;
}


public void setObjarttyp(Objarttyp objarttyp) {
	this.objarttyp = objarttyp;
}


public Type getType() {
	return type;
}


public void setType(Type type) {
	this.type = type;
}


public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Parameter musterid = ")
		
		.append("]");
	return b.toString();


}
}
