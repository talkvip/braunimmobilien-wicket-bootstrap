package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "Eigentuemertyp")
@Indexed
@XmlRootElement
public class Eigentuemertyp extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
    private String typenbeschreibung;
  

public Eigentuemertyp(){
	super();
}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="TypNr",nullable = false, unique = true)
public Long getId() {
    return id;
}
@Column(name="Typenbeschreibung",nullable = false, length = 50, unique = true)
@Field
	public String getTypenbeschreibung() {
		return typenbeschreibung;
	}
public void setId( Long param ){
	   this.id= param;
	}
	public void setTypenbeschreibung(String typenbeschreibung) {
		this.typenbeschreibung = typenbeschreibung;
	}	
	public String toString()
	{

	 StringBuilder b = new StringBuilder();
		b.append("[Eigentuemertyp id = ")
			.append(this.getId())
			.append(", typenbeschreibung = ")
			.append(typenbeschreibung)
			.append("]");
		return b.toString();
	}


	 public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (!(o instanceof Eigentuemertyp)) {
	            return false;
	        }

	        final Eigentuemertyp eigentuemertyp= (Eigentuemertyp) o;
	        return !(eigentuemertyp != null ? !(eigentuemertyp.getId().longValue()!=id.longValue()) : true);

	    }
	  public int hashCode() {
		  return (id != null ? id.hashCode() : 0);
	    }
	}


