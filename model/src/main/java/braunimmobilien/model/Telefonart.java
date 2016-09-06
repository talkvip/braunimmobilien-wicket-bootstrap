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
@Table(name = "Telefonart")
@Indexed
@XmlRootElement
public class Telefonart extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
    private String en;
   
  
public Telefonart(){}

public Telefonart(final String en){
this.en=en;	
}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="TelefonArtID",nullable = false, unique = true)
 @Field()
public Long getId() {
    return id;
}
    @Column(nullable = false, length = 50, unique = true)
    @Field(name="en")
    public String getEn() {
        return en;
    }


  
    public void setId(Long id) {
        this.id = id;
    }

    public void setEn(String en) {
        this.en=en;
    }

  
    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Telefonart)) {
            return false;
        }

        final Telefonart telefonart= (Telefonart) o;

        return !(en != null ? !en.equals(telefonart.getEn()) : telefonart.getEn() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (en != null ? en.hashCode() : 0);
    }

public String toString()
{
	        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
	    .append("[Telefonart id = ")
		.append(this.getId())
		.append(", en = ")
		.append(en)
		.append("]");
	return sb.toString();
}	
	}


