package braunimmobilien.bootstrap.webapp.pages.breadcrumb;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import braunimmobilien.model.Eigentuemertyp;
import braunimmobilien.model.Land;
import braunimmobilien.model.Orte;
import braunimmobilien.model.Strassen;
import braunimmobilien.model.Personen;
import braunimmobilien.model.Objekte;
import braunimmobilien.model.Kunde;
import braunimmobilien.model.Angebot;;

public class Search implements Serializable{
	private static final long serialVersionUID = -1926446968284768121L;
	Eigentuemertyp eigentuemertyp=null;

	Land land=null;
	
	Orte orte=null;

	Strassen strasse=null;
	
	public Strassen getStrasse() {
		return strasse;
	}
	public void setStrasse(Strassen strasse) {
		this.strasse = strasse;
	}
	public Personen getPerson() {
		return person;
	}
	public void setPerson(Personen person) {
		this.person = person;
	}
	public Objekte getObjekt() {
		return objekt;
	}
	public void setObjekt(Objekte objekt) {
		this.objekt = objekt;
	}
	public String getTextsearch() {
		return textsearch;
	}
	public void setTextsearch(String textsearch) {
		this.textsearch = textsearch;
	}
	Personen person=null;
	
	Objekte objekt=null;
	
	String textsearch=null;
	
Kunde kunden=null;
	
	public Kunde getKunden() {
	return kunden;
}
public void setKunden(Kunde kunden) {
	this.kunden = kunden;
}
public Angebot getAngebote() {
	return angebote;
}
public void setAngebote(Angebot angebote) {
	this.angebote = angebote;
}
	Angebot angebote=null;
	
	public Eigentuemertyp getEigentuemertyp() {
		return eigentuemertyp;
	}
	public void setEigentuemertyp(Eigentuemertyp eigentuemertyp) {
		this.eigentuemertyp = eigentuemertyp;
	}
	public Land getLand() {
		return land;
	}
	public void setLand(Land land) {
		this.land = land;
	}
	public Orte getOrte() {
		return orte;
	}
	public void setOrte(Orte orte) {
		this.orte = orte;
	}
	public String toString()
	{

	 StringBuilder b = new StringBuilder();
		b.append("[Search ")
			.append("eigentuemertyp = ")
			.append(eigentuemertyp)
			.append(", land = ")
			.append(land)
			.append(", orte = ")
			.append(orte)
			.append(", strasse = ")
			.append(strasse)
			.append(", person = ")
			.append(person)
			.append(", objekt = ")
			.append(objekt)
			.append(", kunde = ")
			.append(kunden)
			.append(", angebot = ")
			.append(angebote)
			.append("]");
		
		return b.toString();
	}
}
