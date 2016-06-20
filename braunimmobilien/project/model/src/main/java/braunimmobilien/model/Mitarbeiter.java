package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
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
@Table(name = "Mitrumpf")
@Indexed
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Mitarbeiter extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	
	  private Personen person;
	   
	    private Boolean mitstatus;
	    @XmlJavaTypeAdapter(SQLDateAdapter.class)
	    private java.sql.Date mitaufnahmedatum;
	    @XmlJavaTypeAdapter(SQLDateAdapter.class)
	    private java.sql.Date mitgeburtsdatum;
	   
	    private Double mitint;
	    @XmlJavaTypeAdapter(SQLDateAdapter.class)
	    private java.sql.Date mitverlassdatum;
	   
	    private String mitsparte;
	    @XmlJavaTypeAdapter(SQLDateAdapter.class)
	    private java.sql.Date mitrueckkehr;
	   
	    private String mitzeit;
	   
	    private String mitordner;

public Mitarbeiter(){}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="Mitnummer",nullable = false, unique = true)
public Long getId() {
    return id;
}
@ManyToOne(cascade = CascadeType.ALL)
@IndexedEmbedded
//@Cascade({CascadeType.SAVE_UPDATE})
@JoinColumn(name="MitEigtID", insertable=false, updatable=false, nullable=false)
	public Personen getPerson() {
		return person;
	}

@Column(name="MitStatus",nullable = false,  unique = false)
@Field
	public Boolean getMitstatus(){
	   return this.mitstatus;
	}

@Column(name="MitAufnahmedatum",nullable = false, unique = false)
@Field
	  @DateBridge(resolution = Resolution.DAY)
	public java.sql.Date getMitaufnahmedatum(){
	   return this.mitaufnahmedatum;
	}
@Column(name="MitGeburtsdatum",nullable = true, unique = false)
@Field
	  @DateBridge(resolution = Resolution.DAY)
	public java.sql.Date getMitgeburtsdatum(){
		return this.mitgeburtsdatum;
	}
	
@Column(name="Mitverlassdatum",nullable = true, unique = false)
@Field
	  @DateBridge(resolution = Resolution.DAY)
public java.sql.Date getMitverlassdatum(){
	return this.mitverlassdatum;
}
@Column(name="MitRueckkehr",nullable = false, unique = false)
@Field
	  @DateBridge(resolution = Resolution.DAY)
	public java.sql.Date getMitrueckkehr(){
	   return this.mitrueckkehr;
	}

	@Column(name="MitInt",nullable = false,columnDefinition = "double(31,30)",  unique = false)
	@Field
	public Double getMitint(){
	   return this.mitint;
	}

	@Column(name="MitSparte",nullable = true,length = 50,  unique = false)
	@Field
	public String getMitsparte(){
		   return this.mitsparte;
		}

	@Column(name="MitZeit",nullable = false,length = 2,  unique = false)
	@Field
		public String getMitzeit(){
			   return this.mitzeit;
			}

	@Column(name="MitOrdner",nullable = false,length = 50,  unique = false)
	@Field
			public String getMitordner(){
				   return this.mitordner;
				}
	
	public void setId( Long param ){
		   this.id= param;
		}
	
				public void setMitordner( String param ){
				   this.mitordner= param;
				}
				
				
				public void setMitzeit( String param ){
					   this.mitzeit= param;
					}
				
				public void setMitsparte( String param ){
					   this.mitsparte= param;
					}
				public void setMitint( Double param ){
					   this.mitint= param;
					}
				
				public void setMitrueckkehr( java.sql.Date param ){
					   this.mitrueckkehr= param;
					}
				public void setMitverlassdatum( java.sql.Date param ){
					if (param!=null){
					   this.mitverlassdatum= new java.sql.Date(param.getTime());
					}
				}
				public void setMitgeburtsdatum( java.sql.Date param ){
					if (param!=null){
					this.mitgeburtsdatum= new java.sql.Date(param.getTime());
					}
				}	
				public void setMitstatus( Boolean param ){
					   this.mitstatus= param;
					}
	
				public void setMitaufnahmedatum(java.sql.Date param ){
					   this.mitaufnahmedatum=param;
					}
				public void setPerson(Personen person) {
					this.person = person;
				}	
				
		

			


public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Mitarbeiter id = ")
		.append(this.getId())
		.append(", person = ")
		.append(person)
		.append(", mitstatus = ")
		.append(mitstatus)
		.append(", mitaufnahmedatum = ")
		.append(mitaufnahmedatum)
		.append(", mitgeburtsdatum = ")
		.append(mitgeburtsdatum)
		.append(", mitint = ")
		.append(mitint)
		.append(", mitverlassdatum = ")
		.append(mitverlassdatum)
		.append(", mitsparte = ")
		.append(mitsparte)
		.append(", mitrueckkehr = ")
		.append(mitrueckkehr)
		.append(", mitzeit = ")
		.append(mitzeit)
		.append(", mitordner = ")
		.append(mitordner)
		.append("]");
	return b.toString();
}







	












public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Mitarbeiter)) {
        return false;
    }

    final Mitarbeiter mitrumpf= (Mitarbeiter) o;

    return !(mitrumpf != null ? !(mitrumpf.getId().longValue()==id.longValue()) : mitrumpf.getId()!=null);

}
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}








	}


