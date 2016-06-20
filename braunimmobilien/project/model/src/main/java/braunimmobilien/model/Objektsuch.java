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
@Table(name = "Objektsuch")
@Indexed
@XmlRootElement
public class Objektsuch extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;

	

   
   
    private String suchtext;
   
public Objektsuch(){}

@Column(name="ObjektsuchID",nullable = false, unique = true)
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
public Long getId() {
    return id;
}






	public String toString(){
		 return "[" + getId() + "] \t\t\t suchtext: " + suchtext;
	}









	







	@Column(name="Suchtext",nullable = false, length = 50, unique = true)
	@Field
	public String getSuchtext() {
		return suchtext;
	}



	
	public void setId( Long param ){
		   this.id= param;
		}




	public void setSuchtext(String suchtext) {
		this.suchtext = suchtext;
	}	

	 public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (!(o instanceof Objektsuch)) {
	            return false;
	        }

	        final Objektsuch objektsuch= (Objektsuch) o;

	        return !(suchtext != null ? !suchtext.equals(objektsuch.getSuchtext()) : objektsuch.getSuchtext()!=null);

	    }

	    /**
	     * {@inheritDoc}
	     */
	    public int hashCode() {
	        return (suchtext != null ? suchtext.hashCode() : 0);
	    }
}
