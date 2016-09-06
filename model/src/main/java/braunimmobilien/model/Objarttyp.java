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
@Table(name = "Objarttyp")
@Indexed
@XmlRootElement
public class Objarttyp extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;


private	String typentext;

 
  public Objarttyp() {
    super();
    setId(new Long(0));
  }

 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @DocumentId
  @Column(name="id",nullable = false, unique = true)
  public Long getId() {
      return id;
  }

  
  @Column(name="typentext",nullable = false, length = 100, unique = true)
  @Field
public String getTypentext() {
	return typentext;
}




public void setTypentext(String typentext) {
	this.typentext=typentext;
}



public void setId( Long param ){
	   this.id= param;
	}



public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Objarttyp id = ")
		.append(this.getId())
		.append(", typentext = ")
		.append(this.getTypentext())
		.append("]");
	return b.toString();
}


public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Objarttyp)) {
        return false;
    }

    final Objarttyp objarttyp=(Objarttyp) o;

    return !(typentext != null ? !typentext.equals(objarttyp.getTypentext()) : objarttyp.getTypentext()!=null);

}

/**
 * {@inheritDoc}
 */
public int hashCode() {
    return (typentext != null ? typentext.hashCode() : 0);
}
}

