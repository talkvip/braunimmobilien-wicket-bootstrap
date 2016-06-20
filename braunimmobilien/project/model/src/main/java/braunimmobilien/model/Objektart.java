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
@Table(name = "Objektarten")
@Indexed
@XmlRootElement
public class Objektart extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
    private String objartkurz;
    private String objartname;		
    private Boolean objarttyp;	
    private Boolean doppelbelegung;	
    
public Objektart(){}
@Id
@Column(name="ObjektartID",nullable = false, unique = true)
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
public Long getId() {
    return id;
}
@Column(name="OBJARTKurz",nullable = false, length = 50, unique = false)
@Field
	public String getObjartkurz(){
	   return this.objartkurz;
	}

	public void setObjartkurz( String param ){
	   this.objartkurz= param;
	}
	@Column(name="OBJARTName",nullable = false, length = 50, unique = false)
	@Field
	public String getObjartname(){
	   return this.objartname;
	}
	public void setObjartname( String param ){
		   this.objartname= param;
		}
	
	@Column(name="OBJARTTyp",nullable = true,columnDefinition = "int(11)", unique = false)
	@Field
	public Boolean getObjarttyp() {
		return objarttyp;
	}

	@Column(name="Doppelbelegung",nullable = true,columnDefinition = "int(11)", unique = false)
	@Field
	public Boolean getDoppelbelegung() {
		return doppelbelegung;
	}

	public String toString(){
	   return " [ Objektart : id: "+this.getId()+" objartkurz: "+this.getObjartkurz()+" objartname: "+this.getObjartname()+" objarttyp: "+this.getObjarttyp()+" doppelbelegung: "+this.getDoppelbelegung()+" ] ";
	}

	public void setId( Long param ){
		   this.id= param;
		}

	public void setObjarttyp(Boolean objarttyp) {
		this.objarttyp = objarttyp;
	}

	public void setDoppelbelegung(Boolean doppelbelegung) {
		this.doppelbelegung = doppelbelegung;
	}
	 public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (!(o instanceof Objektart)) {
	            return false;
	        }

	        final Objektart objektart= (Objektart) o;

	        return !(objartname != null ? !objartname.equals(objektart.getObjartname()) : objektart.getObjartname()!=null);

	    }

	    /**
	     * {@inheritDoc}
	     */
	    public int hashCode() {
	        return (objartname != null ? objartname.hashCode() : 0);
	    }
	
}
