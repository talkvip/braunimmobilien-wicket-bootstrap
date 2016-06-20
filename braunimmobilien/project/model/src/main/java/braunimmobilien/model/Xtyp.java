package braunimmobilien.model;
import braunimmobilien.model.Xtyp;
import java.io.Serializable;
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
@Table(name = "XTyp")
@Indexed
@XmlRootElement
public class Xtyp extends  BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	private String xtypkuerzel;
	private String betreff;
 

	
	private String xvor;	
    private Boolean xtypmitexposee;
    
    public Xtyp(){}

    public Xtyp(final String xtypkuerzel){
    this.xtypkuerzel=xtypkuerzel;	
    }  
    
    
    
    
    @Column(name="XTypKuerzel",nullable = false, length = 10, unique = false)
    @Field  
    public String getXtypkuerzel() {
	return xtypkuerzel;
}
    @Column(name="Betreff",nullable = true, length = 250, unique = false)
    @Field  
    public String getBetreff() {
 		return betreff;
 	}
    
    @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @DocumentId
	  @Column(name="XTypNr",nullable = false, unique = true)
	  public Long getId() {
	    return id;
	  }
    public void setId(Long id) {
    	this.id = id;
    }

public void setXtypkuerzel(String xtypkuerzel) {
	this.xtypkuerzel = xtypkuerzel;
}


@Column(name="XVor",nullable = false, length = 10, unique = false)
@Field
public String getXvor() {
	return xvor;
}



public void setXvor(String xvor) {
	this.xvor = xvor;
}


@Column(name="XTypmitExposee",nullable = false,columnDefinition = "int(11)", unique = false)
@Field
public Boolean getXtypmitexposee() {
	return xtypmitexposee;
}

/**
 * {@inheritDoc}
 */
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Xtyp)) {
        return false;
    }

    final Xtyp xtyp= (Xtyp) o;

    return !(xtypkuerzel != null ? !xtypkuerzel.equals(xtyp.getXtypkuerzel()) : xtyp.getXtypkuerzel() != null);

}

/**
 * {@inheritDoc}
 */
public int hashCode() {
    return (xtypkuerzel != null ? xtypkuerzel.hashCode() : 0);
}

public void setXtypmitexposee(Boolean xtypmitexposee) {
	this.xtypmitexposee = xtypmitexposee;
}
public void setBetreff(String betreff) {
	this.betreff = betreff;
}

	  public String toString(){
			 String    returnstring= "  Xtyp : [ ";
			 returnstring=returnstring+ this.id;
			 returnstring=returnstring+ " xvor: ";
			 if (xvor!=null){returnstring=returnstring+ xvor;}
			 else {returnstring=returnstring+ "null";}
			 returnstring=returnstring+ " xtypmitexposee: ";
			 if (xtypmitexposee!=null){returnstring=returnstring+ xtypmitexposee;}
			 else {returnstring=returnstring+ "null";}
			 returnstring=returnstring+ " xtypkuerzel: ";
			 if (xtypkuerzel!=null){returnstring=returnstring+ xtypkuerzel;}
			 else {returnstring=returnstring+ "null";}
			
			 returnstring=returnstring+ " ] ";
			 return returnstring;
			    }	
	
	  
			}
	


