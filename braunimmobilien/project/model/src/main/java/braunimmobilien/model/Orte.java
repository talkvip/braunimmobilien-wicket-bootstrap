package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
@Table(name = "Orte")
@Indexed
@XmlRootElement
public class Orte extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;


  private String ortname;

  private String ortplz;
  
  private Land land;

  private List<Strassen> strassen = new ArrayList<Strassen>();
  /**
   * The default constructor
   */
  public Orte() {
    super();
    setId(new Long(0));
  }

 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @DocumentId
  @Column(name="OrtID",nullable = false, unique = true)
  public Long getId() {
      return id;
  }

  
  @Column(name="OrtName",nullable = false, length = 50, unique = false)
  @Field
public String getOrtname() {
	return ortname;
}




public void setOrtname(String ortname) {
	this.ortname = ortname;
}



@Column(name="OrtPLZ",nullable = true, length = 10, unique = false)
@Field
public String getOrtplz() {
	return ortplz;
}
@XmlTransient 
@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
@JoinColumn(name="OrtID",nullable=false )
public List<Strassen> getStrassen() {
	return strassen;
}


public void setOrtplz(String ortplz) {
	this.ortplz = ortplz;
}



@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="LandID", insertable=false, updatable=false, nullable=false)
public Land getLand() {
	return land;
}

public void setId( Long param ){
	   this.id= param;
	}


public void setLand(Land land) {
	this.land = land;
}

public void setStrassen(List<Strassen> strassen) {
	this.strassen = strassen;
}
public void addStrassen(Strassen strassen) {
    getStrassen().add(strassen);
}
public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Orte id = ")
		.append(this.getId())
		.append(", ortname = ")
		.append(ortname)
		.append(", ortplz = ")
		.append(ortplz)
		.append(", strassen = [");
	Iterator it= strassen.iterator();
	while(it.hasNext()){
		Strassen strassen=(Strassen)it.next();
		b.append(strassen);
	}
		b.append("]]");
	return b.toString();
}


public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Orte)) {
        return false;
    }

    final Orte orte= (Orte) o;

    return !(orte != null ? !(orte.getId().longValue()!=id.longValue()) : true);

}

/**
 * {@inheritDoc}
 */
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}
}