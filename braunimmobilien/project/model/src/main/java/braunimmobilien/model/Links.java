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
@Table(name = "Links")
@Indexed
@XmlRootElement
public class Links extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;

	private Subjects subject;


	private String internetadresse;

	private String beschreibung;
	 

 
  public Links() {
    super();
    setId(new Long(0));
  }

 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @DocumentId
  @Column(name="linksID",nullable = false, unique = true)
  public Long getId() {
      return id;
  }
  @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
  @JoinColumn(name="subjectID", nullable=false)
  public Subjects getSubject() {
  	return subject;
  }
 

  @Column(name="internetadresse",nullable = false, length = 250, unique = false)
  @Field
public String getInternetadresse() {
	return internetadresse;
}
  @Column(name="beschreibung",nullable = false, length = 250, unique = false)
  @Field
  public String getBeschreibung() {
		return beschreibung;
  }

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}

public void setId( Long param ){
	   this.id= param;
	}

public void setInternetadresse(String internetadresse) {
	this.internetadresse = internetadresse;
}


public void setBeschreibung(String beschreibung) {
	this.beschreibung = beschreibung;
}

public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Links id = ")
		.append(this.getId())
		.append(", subject = ")
			.append(this.getSubject())
			.append(", internetadresse = ")
			.append(this.getInternetadresse())
			.append(", beschreibung = ")
			.append(this.getBeschreibung())
		.append("]");
	return b.toString();
}


public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Links)) {
        return false;
    }

    final Links link=(Links) o;

    return !(beschreibung != null ? !beschreibung.equals(link.getBeschreibung()) : link.getBeschreibung()!=null);

}

/**
 * {@inheritDoc}
 */
public int hashCode() {
    return (beschreibung != null ? beschreibung.hashCode() : 0);
}
}









