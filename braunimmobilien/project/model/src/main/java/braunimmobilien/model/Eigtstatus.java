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
@Table(name = "Eigtstatus")
@Indexed
@XmlRootElement
public class Eigtstatus extends BaseObject implements Serializable,Identifiable<Long>,Comparable<Eigtstatus>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	
   
   
    private String eigt_status_text;
 

public Eigtstatus(){
	super();
}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="Eigt_Status_ID",nullable = false, unique = true)
@Field()
public Long getId() {
    return id;
}




	


@Override
public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Eigtstatus id = ")
		.append(this.getId())
		.append(", eigt_status_text = ")
		.append(eigt_status_text)
		.append("]");
	return b.toString();
}







	







@Column(name="Eigt_Status_Text",nullable = false, length = 50, unique = true)
@Field
	public String getEigt_status_text() {
		return eigt_status_text;
	}

public void setId( Long param ){
	   this.id= param;
	}

@Override
public boolean equals(Object o) {
    if (this == o) {
        return false;
    }
    if (!(o instanceof Eigtstatus)) {
        return false;
    }

    final Eigtstatus eigtstatus= (Eigtstatus) o;

    if (this.compareTo(eigtstatus)==0) return true;
  
    return false;
}
@Override
public int hashCode() {
    return (eigt_status_text != null ? eigt_status_text.hashCode() : 0);
}

@Override
public int compareTo(Eigtstatus b) {
  if (b == null)
    return -1;
  
 return this.toString().compareTo(b.toString());
}





	public void setEigt_status_text(String eigt_status_text) {
		this.eigt_status_text = eigt_status_text;
	}	
	}


