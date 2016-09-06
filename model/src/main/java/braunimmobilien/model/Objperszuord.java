package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Zuordnungstabelle")
@Indexed
@XmlRootElement
public class Objperszuord extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	 private Objekte objekt;
	  private Personen personen;
    private Eigentuemertyp eigentuemertyp;		
	
public Objperszuord(){
}

public Objperszuord(Objperszuord param){
	this.setId(param.getId());
this.eigentuemertyp=param.getEigentuemertyp();
this.objekt=param.getObjekt();
this.personen=param.getPersonen();
}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="Zuordnungsnummer",nullable = false, unique = true)
public Long getId() {
    return id;
}
@XmlTransient 
@ManyToOne(fetch=FetchType.EAGER)//(fetch = FetchType.LAZY)
	@JoinColumn(name="ObjektID", insertable=false, updatable=false, nullable=false)
public Objekte getObjekt() {
	return objekt;
}
@ManyToOne(fetch=FetchType.EAGER)//(fetch = FetchType.LAZY)
	@JoinColumn(name = "KundEigtNr", insertable=false, updatable=false, nullable=false)
public Personen getPersonen() {
	return personen;
}
@ManyToOne(fetch=FetchType.EAGER)//(fetch = FetchType.LAZY)
	@JoinColumn(name = "ZuordnungsartID", insertable=true, updatable=true, nullable=false)
public Eigentuemertyp getEigentuemertyp() {
	return eigentuemertyp;
}
public void setId(Long id) {
    this.id = id;
}
public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[TestInputObject id = ")
		.append(this.getId())
		.append(", personen = ")
		.append(personen.toString())
		.append(", eigentuemertyp = ")
		.append(eigentuemertyp)
		.append("]");
	return b.toString();
}


public void setObjekt(Objekte objekt) {
	this.objekt = objekt;
}


public void setPersonen(Personen personen) {
	this.personen = personen;
}

public void setEigentuemertyp(Eigentuemertyp eigentuemertyp) {
	this.eigentuemertyp = eigentuemertyp;
}
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Objperszuord)) {
        return false;
    }

    final Objperszuord objperszuord=(Objperszuord) o;
    if (objekt.getId()==null && objperszuord.getObjekt().getId()!=null) return false;
    if (objekt.getId()!=null && objperszuord.getObjekt().getId()==null) return false;
    if (personen==null && objperszuord.getPersonen()!=null) return false;
    if (personen!=null && objperszuord.getPersonen()==null) return false;
    if (objekt.getId()==null && objperszuord.getObjekt().getId()!=null) return false;
    if (objekt.getId()==null && objperszuord.getObjekt().getId()==null) {
    	if (personen==null && objperszuord.getPersonen()==null) return true;
    	if (personen.getId().longValue()!=objperszuord.getPersonen().getId().longValue()) return false;
    	return true;}
    if (personen==null && objperszuord.getPersonen()==null){
    	  if (objekt.getId()==null && objperszuord.getObjekt().getId()==null) return true;
    	  if (!objekt.getId().equals(objperszuord.getObjekt().getId())) return false;
    	  return true;
    }
    if ((objekt.getId().longValue()!=objperszuord.getObjekt().getId().longValue())||(personen.getId().longValue()!=objperszuord.getPersonen().getId().longValue())) return false;
   
    return true;
   
}

/**
 * {@inheritDoc}
 */
public int hashCode() {
	if ((objekt.getId()!=null)&& (personen==null)) return (objekt.getId()+"/null").hashCode();
	if ((objekt.getId()==null)&& (personen!=null)) return ("null/"+personen).hashCode();
	if ((objekt.getId()==null)&& (personen==null)) return 0;
			return (objekt.getId()+"/"+personen).hashCode();
			
}

}
