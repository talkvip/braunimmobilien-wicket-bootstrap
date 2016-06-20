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
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Konditionen")
@Indexed
@XmlRootElement
public class Kondition extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private String id;
    private String kontext;
   
  
public Kondition(){}

public Kondition(final String kontext){
this.kontext=kontext;	
}

@Id
@Column(name="KonID",nullable = false, length = 10, unique = true)
@DocumentId
public String getId() {
    return id;
}
    @Column(name="Kontext",nullable = false, length = 50, unique = true)
    @Field
    public String getKontext() {
        return kontext;
    }


  
    public void setId(String id) {
        this.id = id;
    }

    public void setKontext(String kontext) {
        this.kontext = kontext;
    }

  
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Kondition)) {
            return false;
        }

        final Kondition kondition= (Kondition) o;

        return !(kontext != null ? !kontext.equals(kondition.getKontext()) : kondition.getKontext() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (kontext != null ? kontext.hashCode() : 0);
    }

public String toString()
{
	        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
	    .append("[Kondition id = ")
		.append(this.getId())
		.append(", kontext = ")
		.append(kontext)
		.append("]");
	return sb.toString();
}	
	}








