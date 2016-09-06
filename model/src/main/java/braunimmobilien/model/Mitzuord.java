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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
@Table(name = "Mitzuord")
@Indexed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Mitzuord   extends BaseObject implements Serializable{
	
    protected Long id;
  
    protected Long kundennr;
    
    
    protected Long mitarbeiternr;
  
    @XmlJavaTypeAdapter(SQLDateAdapter.class)
    protected java.sql.Date vondatum;
   
    @XmlJavaTypeAdapter(SQLDateAdapter.class)
    protected java.sql.Date bisdatum;
   
    protected Boolean aktuell;		
	
 
public Mitzuord(){
	java.util.Date today = new java.util.Date();
	this.vondatum= new java.sql.Date(today.getTime());
	this.aktuell=new Boolean(true);
	this.mitarbeiternr= new Long(1);
}


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="ZuordnungsID",nullable = false, unique = true)
 @Field()
	public Long getId(){
	   return this.id;
	}

	public void setId( Long param ){
	   this.id= param;
	}
	

	@Column(name="MitarbeiterNr",nullable = true,  unique = false)
	@Field
	public Long getMitarbeiternr(){
	   return this.mitarbeiternr;
	}
	@Column(name="KundenNr",nullable = true,  unique = false)
	@Field
	public Long getKundennr(){
	   return this.kundennr;
	}
	public void setMitarbeiternr( Long param ){
	   this.mitarbeiternr= param;
	}
	public void setKundennr( Long param ){
		   this.kundennr= param;
		}
	@Column(name="VonDatum",nullable = true, unique = false)
	@Field
		  @DateBridge(resolution = Resolution.DAY)
	public java.sql.Date getVondatum(){
	   return this.vondatum;
	}

	public void setVondatum( java.sql.Date param ){
	   this.vondatum= param;
	}



@Column(name="BisDatum",nullable = true, unique = false)
@Field
	  @DateBridge(resolution = Resolution.DAY)
	public java.sql.Date getBisdatum(){
	   return this.bisdatum;
	}

	public void setBisdatum( java.sql.Date param ){
	   this.bisdatum= param;
	}
	@Column(name="Aktuell",nullable = true,columnDefinition = "int(11)", unique = false)
	@Field
	public Boolean getAktuell(){
	   return this.aktuell;
	}

public boolean isAktuell(){
	   return this.aktuell.booleanValue();
	}


public void setAktuell( Boolean param ){
	if (param!=null){
	if (!param.booleanValue() && this.aktuell.booleanValue()){
	java.util.Date today = new java.util.Date();
	this.bisdatum = new java.sql.Date(today.getTime());
	}}
		   this.aktuell= param;
		}
public String toString(){
	return "[ mitzuord : id : "+id+" mitarbeiternr :"+mitarbeiternr+" von Datum :"+vondatum+" bis Datum :"+bisdatum+" aktuell :"+aktuell;
	
}
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Mitzuord)) {
        return false;
    }

    final Mitzuord mitzuord= (Mitzuord) o;

    return !(mitzuord != null ? !(mitzuord.getId().longValue()!=id.longValue()) : true);

}
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}	
}
