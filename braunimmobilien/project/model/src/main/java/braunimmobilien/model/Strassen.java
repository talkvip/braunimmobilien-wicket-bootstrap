package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Strassen")
@Indexed
@XmlRootElement
public class Strassen extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;

  private String strname;

  private String strhausbereich;
  
  private String strplz;
  
  private Orte ort;
  
private Boolean merkmal;
  
  private String planquadrat;
  
  private List<Objekte> objekte = new ArrayList<Objekte>();
  private List<Personen> personen = new ArrayList<Personen>();
 




public void setPersonen(List<Personen> personen) {
	this.personen = personen;
}




/**
   * The default constructor
   */
  public Strassen() {
    super();
    setId(new Long(0));
  }

 
  

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @DocumentId
  @Column(name="StrID",nullable = false, unique = true)
  public Long getId() {
      return id;
  }









  @Column(name="StrName",nullable = false, length = 50, unique = false)
  @Field
public String getStrname() {
	return strname;
}








public void setStrname(String strname) {
	this.strname = strname;
}







@Column(name="StrHausBereich",nullable = true, length = 50, unique = false)
@Field
public String getStrhausbereich() {
	return strhausbereich;
}








public void setStrhausbereich(String strhausbereich) {
	this.strhausbereich = strhausbereich;
}







@Column(name="StrPLZ",nullable = true, length = 20, unique = false)
@Field
public String getStrplz() {
	return strplz;
}
@XmlTransient 
@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
@JoinColumn(name="Objstrasseid",nullable=false )
public List<Objekte> getObjekte() {
	return objekte;
}
@XmlTransient 
@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
@JoinColumn(name="EigtStrID",nullable=false )
public List<Personen> getPersonen() {
	return personen;
}


public void setStrplz(String strplz) {
	this.strplz = strplz;
}







@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="OrtID", insertable=false, updatable=false, nullable=false)
public Orte getOrt() {
	return ort;
}








public void setOrt(Orte ort) {
	this.ort=ort;
}







@Column(name="Merkmal",nullable = true,columnDefinition = "int(11)", unique = false)
@Field
public Boolean getMerkmal() {
	return merkmal;
}








public void setMerkmal(Boolean merkmal) {
	this.merkmal = merkmal;
}







@Column(name="Planquadrat",nullable = true, length = 254, unique = false)
@Field
public String getPlanquadrat() {
	return planquadrat;
}




public void setId( Long param ){
	   this.id= param;
	}



public void setPlanquadrat(String planquadrat) {
	this.planquadrat = planquadrat;
}

public void setObjekte(List<Objekte> objekte) {
	this.objekte = objekte;
}
public void addObjekt(Objekte objekt) {
    this.objekte.add(objekt);
}
public void addPerson(Personen person) {
    this.personen.add(person);
}
public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Strassen id = ")
		.append(this.getId())
		.append(", strname = ")
		.append(strname)
		.append(", strplz = ")
		.append(strplz)
		.append(", planquadrat = ")
		.append(planquadrat)
		.append(", merkmal, = ")
		.append(merkmal)
		.append(", objekte, = [");
/*	Iterator it = objekte.iterator();
	while(it.hasNext()){
	Objekte objekt=(Objekte)it.next();	
		b.append(objekt.toStrassenReducedString());
	}
		b.append("]]");*/
	return b.toString();
}





public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Strassen)) {
        return false;
    }

    final Strassen strassen= (Strassen) o;

    return !(strassen != null ? !(strassen.getId().longValue()!=id.longValue()) : true);

}
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}
}
