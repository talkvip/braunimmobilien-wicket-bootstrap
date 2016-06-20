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
@Table(name = "Kundenart")
@Indexed
@XmlRootElement
public class Kundenart extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	
	 private String kundenart;
	   
	    private String kundenkuerzel;
 

public Kundenart(){}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="KundenArtNr",nullable = false, unique = true)
public Long getId() {
    return id;
}
@Column(name="KundenArt",nullable = false, length = 50, unique = true)
@Field
public String getKundenart(){
	   return this.kundenart;
	}
	
@Column(name="Kundenkuerzel",nullable = false, length = 50, unique = true)
@Field
	public String getKundenkuerzel(){
		   return this.kundenkuerzel;
		}

		public void setKundenkuerzel( String param ){
		   this.kundenkuerzel= param;
		}
		public void setKundenart( String param ){
			   this.kundenart= param;
			}
		public void setId( Long param ){
			   this.id= param;
			}

	



		public String toString()
		{
		 StringBuilder b = new StringBuilder();
			b.append("[Kundenart id = ")
				.append(this.getId())
				.append(", kundenart = ")
				.append(kundenart)
				.append(", kundenkuerzel = ")
				.append(kundenkuerzel)
				.append("]");
			return b.toString();
		}








	











public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Kundenart)) {
        return false;
    }

    final Kundenart kundart= (Kundenart) o;
   
    return !(this.kundenkuerzel != null ? !this.kundenkuerzel.equals(kundart.getKundenkuerzel()) : kundart.getKundenkuerzel()!=null);

}


public int hashCode() {
    return (kundenart != null ? kundenart.hashCode() : 0);
}







	
	}


