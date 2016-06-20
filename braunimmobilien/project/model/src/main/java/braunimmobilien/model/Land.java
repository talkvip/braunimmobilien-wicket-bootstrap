package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.CascadeType;

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
@Table(name = "Land")
@Indexed
@XmlRootElement
public class Land extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	




private String landname;

  private String kennzeichen;

  private List<Orte> orte = new ArrayList<Orte>();


  /**
   * The default constructor
   */
  public Land() {
    super();
    this.setId(new Long(0));
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @DocumentId
  @Column(name="LandID",nullable = false, unique = true)
  public Long getId() {
      return id;
  }

 
  
  @Column(name="LandName",nullable = false, length = 254, unique = true)
  @Field
  public String getLandname() {
	return landname;
}






  @Column(name="Kennzeichen",nullable = true, length = 254, unique = true)
  @Field
public String getKennzeichen() {
	return kennzeichen;
}
  @XmlTransient 
  @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
@JoinColumn(name="LandID",nullable=false )
  public List<Orte> getOrte() {
  	return orte;
  }

public void setId( Long param ){
	   this.id= param;
	}

public void setLandname(String landname) {
	this.landname = landname;
}



public void setKennzeichen(String kennzeichen) {
	this.kennzeichen = kennzeichen;
}
public void setOrte(List<Orte> orte) {
	this.orte = orte;
}

public void addOrt(Orte orte) {
    getOrte().add(orte);
}

public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Land id = ")
		.append(this.getId())
		.append(", landname = ")
		.append(landname)
		.append(", kennzeichen = ")
		.append(kennzeichen)
	.append(", orte = [");
	Iterator it= orte.iterator();
	while(it.hasNext()){
		Orte ort=(Orte)it.next();
		b.append(ort);
	}
		b.append("]]");
	return b.toString();
}


public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Land)) {
        return false;
    }

    final Land land= (Land) o;

    return !(land != null ? !(land.getId().longValue()!=id.longValue()) : true);

}
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}
}
