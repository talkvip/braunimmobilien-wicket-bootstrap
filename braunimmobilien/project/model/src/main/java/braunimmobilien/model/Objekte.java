package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.OneToMany;

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
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Entity
@Table(name = "Objekte")
@Indexed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Objekte extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	private Strassen strasse;
	private Objektsuch objektsuch;
	private Objektart objektart;
	private String objhausnummer;
	private String objinfo;
	private Double merkmal;
	 @XmlJavaTypeAdapter(SQLDateAdapter.class)
	private java.sql.Date objaufnahme;
	 @XmlJavaTypeAdapter(SQLDateAdapter.class)
	private java.sql.Date objletztkontakt;
	 @XmlJavaTypeAdapter(DateAdapter.class)
	private java.util.Date objvorlage;
	 @XmlTransient private List<Objperszuord> objperszuords = new ArrayList<Objperszuord>();
	 @XmlTransient private List<Angobjzuord> angobjzuords = new ArrayList<Angobjzuord>();
	 @XmlTransient private List<Nachweise> nachweise = new ArrayList<Nachweise>();	  
	 @XmlTransient private List<Scout> scouts = new ArrayList<Scout>();






	



	



	public Objekte(){
		   this.id=new Long(0);
	java.util.Date today = new java.util.Date();
	this.objaufnahme = new java.sql.Date(today.getTime());
	}
	
	   public Objekte(Objekte param){
			this.setId(param.getId());
			this.objaufnahme = param.getObjaufnahme();
			this.objektsuch = param.getObjektsuch();
			this.objektart=param.getObjektart();
			this.strasse=param.getStrasse();
			 this.objhausnummer=param.getObjhausnummer();
			  this.objinfo=param.getObjinfo();
			  this.merkmal=param.getMerkmal();
			   this.objletztkontakt=param.getObjletztkontakt();
			   this.objvorlage=param.getObjvorlage();
			}
 @Column(name="ObjID",nullable = false,  unique = true)
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   @DocumentId
	   public Long getId() {
	       return id;
	   }
 @XmlTransient
	   @ManyToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name="ObjstrasseID", insertable=false, updatable=false, nullable=false)
		public Strassen getStrasse(){
		   return this.strasse;
		}

	   @ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.EAGER)
	   @JoinColumn(name="ObKeinKontakt", nullable=false)
		public Objektsuch getObjektsuch(){
		   return this.objektsuch;
		}
	  
	   @ManyToOne(cascade = CascadeType.REFRESH,fetch=FetchType.EAGER)
	   @JoinColumn(name="ObjArtID",nullable=false)
		public Objektart getObjektart(){
			   return this.objektart;
			}
	   @Column(name="ObjHausnummer",nullable = true, length = 255, unique = false)
	    @Field
		public String getObjhausnummer(){
			   return this.objhausnummer;
			}
	   @Column(name="Merkmal",nullable = true,columnDefinition = "double(31,30)", unique = false)
	    @Field
		public Double getMerkmal(){
			   return this.merkmal;
			}
	   @Column(name="Objinfo",nullable = true,columnDefinition = "text", unique = false)
	    @Field
		public String getObjinfo(){
			   return this.objinfo;
			}
   @Column(name="ObjLetztKontakt",nullable = true, unique = false)
	   @Field
		  @DateBridge(resolution = Resolution.DAY)
		public java.sql.Date getObjletztkontakt(){
			   return this.objletztkontakt;
		}
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name="ObjVorlage",nullable = true, unique = false)
	   @Field
	//   @DateBridge(resolution = Resolution.HOUR)
		public java.util.Date getObjvorlage(){
			   return this.objvorlage;
			}
   @Column(name="ObjAufnahme",nullable = true, unique = false)
	   @Field
		  @DateBridge(resolution = Resolution.DAY)
		public java.sql.Date getObjaufnahme(){
			   return this.objaufnahme;
			}
	 //  @LazyCollection(LazyCollectionOption.FALSE)
   @XmlTransient
@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
@JoinColumn(name="ObjektID",nullable=false )
	public List<Objperszuord> getObjperszuords() {
		return objperszuords;
	}
   @XmlTransient
@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
@JoinColumn(name="ObjNr",nullable=false )
public List<Angobjzuord> getAngobjzuords() {
	return angobjzuords;
}
   @XmlTransient
   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
   @JoinColumn(name="NachObjNr",nullable=false )
   public List<Nachweise> getNachweise() {
		return nachweise;
	}
   @XmlTransient
   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
   @JoinColumn(name="ObjID",insertable=true, updatable=true,nullable=true )
   public List<Scout> getScouts() {
		return scouts;
	}
public void setObjperszuords(List<Objperszuord> objperszuords) {
	this.objperszuords = objperszuords;
}
 public void addObjperszuord(Objperszuord objperszuord) {
        getObjperszuords().add(objperszuord);
    }
 public void setAngobjzuords(List<Angobjzuord> angobjzuords) {
		this.angobjzuords = angobjzuords;
	}
 public void addAngobjzuord(Angobjzuord angobjzuord) {
     getAngobjzuords().add(angobjzuord);
 }
		public void setObjektsuch( Objektsuch param ){
		this.objektsuch=param;
		}
			
		public void setObjektart( Objektart param ){
		   this.objektart= param;
		}
		public void setObjhausnummer( String param ){
		   this.objhausnummer= param;
		}
		public void setObjinfo( String param ){
		   this.objinfo= param;
		}
	
		public void setId( Long param ){
			   this.id= param;
			}	
		public void setMerkmal( Double param ){
		   this.merkmal= param;
		}
		public void setObjaufnahme( java.sql.Date param ){
		   this.objaufnahme= param;
		}
		public void setObjletztkontakt( java.sql.Date param ){
		   this.objletztkontakt= param;
		}
		public void setObjvorlage( java.util.Date param ){
		   this.objvorlage= param;
		}
	
		public boolean equals(Objekte param){
			return true;
		}
		public void setStrasse( Strassen param ){
			   this.strasse= param;
			}
		public void setNachweise(List<Nachweise> nachweise) {
			this.nachweise = nachweise;
		}
		 public void addNachweise(Nachweise nachweis) {
		     getNachweise().add(nachweis);
		 }
		 public void setScouts(List<Scout> scouts) {
				this.scouts = scouts;
			}
		 public void addScout(Scout scout) {
		     this.scouts.add(scout);
		 }
		public String toStrassenReducedString()
		{
		 StringBuilder b = new StringBuilder();
			b.append("[Objekte id = ")
				.append(this.getId())
				.append(", objektsuch = ")
				.append(objektsuch)
				.append(", objektart = ")
				.append(objektart)
				.append(", objhausnummer = '")
				.append(objhausnummer)
				.append("', objinfo = '")
				.append(objinfo)
				.append("', merkmal = ")
				.append(merkmal)
				.append(", objaufnahme = ")
				.append(objaufnahme)
				.append(", objletztkontakt ")
				.append(objletztkontakt)
				.append(", objvorlage= ")
				.append(objvorlage);
				b.append("]");
			return b.toString();
		}
		
		
		
		public String toString()
		{
		 StringBuilder b = new StringBuilder();
			b.append("[Objekte id = ")
				.append(this.getId())
				.append(", strasse = ")
				.append(strasse)
				.append(", objektsuch = ")
				.append(objektsuch)
				.append(", objektart = ")
				.append(objektart)
				.append(", objhausnummer = '")
				.append(objhausnummer)
				.append("', objinfo = '")
				.append(objinfo)
				.append("', merkmal = ")
				.append(merkmal)
				.append(", objaufnahme = ")
				.append(objaufnahme)
				.append(", objletztkontakt ")
				.append(objletztkontakt)
				.append(", objvorlage= ")
				.append(objvorlage);
				b.append("]");
			return b.toString();
		}

		   public boolean equals(Object o) {
		        if (this == o) {
		            return true;
		        }
		        if (!(o instanceof Objekte)) {
		            return false;
		        }

		        final Objekte objekte= (Objekte) o;

		        return !(objekte != null ? !(objekte.getId().longValue()!=id.longValue()) : true);

		    }

		    /**
		     * {@inheritDoc}
		     */
		    public int hashCode() {
		        return (id != null ? id.hashCode() : 0);
		    }
}
