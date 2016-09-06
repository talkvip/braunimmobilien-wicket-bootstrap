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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "Emailbriefe")
@Indexed
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@XmlRootElement
public class Emailbrief extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private String id;
	
   
   
	protected String subject;
 

public Emailbrief(){}

@Id
@Column(name="EmailBrief",nullable = false,length=50, unique = true)
@DocumentId
public String getId() {
    return id;
}




	



public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Emailbriefe id = ")
		.append(this.getId())
		.append(", subject = ")
		.append(subject)
		.append("]");
	return b.toString();
}







	







@Column(name="Subject",nullable = false, length = 200, unique = true)
@Field
	public String getSubject() {
		return subject;
	}

public void setId( String param ){
	   this.id= param;
	}


public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Emailbrief)) {
        return false;
    }

    final Emailbrief emailbrief= (Emailbrief) o;

    return !(emailbrief != null ? !emailbrief.getId().equals(id) : true);

}

public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}







	public void setSubject(String subject) {
		this.subject=subject;
	}	
	}



