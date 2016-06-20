package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
@Table(name = "Kunden")
@Indexed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Kunde extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;

    protected Personen person;
   
    private Kundenart kundenart;
  
    
	protected Status status;
	 private List<Nachweise> nachweise = new ArrayList<Nachweise>();
	 
	
	
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
    protected java.sql.Date datum;		

    public Kunde(){
    	super();
    	setId(new Long(0));
    	java.util.Date today = new java.util.Date();
    	this.datum = new java.sql.Date(today.getTime());	
    }
    public Kunde(Kunde kunde){
    	super();
    	this.id=kunde.getId();
    	this.datum = kunde.getDatum();
    	this.setPerson(kunde.getPerson());
    	this.setKundenart(kunde.getKundenart());
    	this.setStatus(kunde.getStatus());
    }


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="KundenNr",nullable = false, unique = true)
public Long getId() {
    return id;
}

@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="KundEigtNr",nullable=true,insertable=true, updatable=true)
@IndexedEmbedded
public Personen getPerson(){
	   return this.person;
	}
@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
@JoinColumn(name="KundArt",nullable=false,insertable=true, updatable=true)
	public Kundenart getKundenart(){
	   return this.kundenart;
	}
@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
@JoinColumn(name="KundStatus",nullable=false,insertable=true, updatable=true)
	public Status getStatus(){
	   return this.status;
	}

@Column(name="KundAufDat",nullable = true, unique = false)
@Field
	  @DateBridge(resolution = Resolution.DAY)
	public java.sql.Date getDatum(){
	   return this.datum;
	}
@XmlTransient
@OneToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
@JoinColumn(name="NachKundNr",nullable=false)
public List<Nachweise> getNachweise() {
	return nachweise;
}
public void setId( Long param ){
	   this.id= param;
	}
	
public void setDatum( java.sql.Date param ){
	   this.datum=param;
	}


	public void setPerson( Personen param ){
	   this.person= param;
	}
	public void setKundenart(Kundenart kundenart) {
		this.kundenart = kundenart;
	}
	 public void setStatus(Status status) {
			this.status = status;
		}
	
	 public void setNachweise(List<Nachweise> nachweise) {
			this.nachweise = nachweise;
		}
	 public void addNachweis(Nachweise nachweis) {
			this.nachweise.add(nachweis);
		}


	

	


public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Kunde id = ")
		.append(this.getId())
		.append(", kundeigtnr = ")
//		.append(person.getId())
		.append(", kundenart = ")
		.append(kundenart)
		.append(", status = ")
		.append(status)
		.append(", datum = ")
		.append(datum)
		.append("]");
	return b.toString();
}







	












public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Kunde)) {
        return false;
    }

    final Kunde kunde= (Kunde) o;

    return !(kunde != null ? !(kunde.getId().longValue()!=id.longValue()) : true);

}
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}








	}




