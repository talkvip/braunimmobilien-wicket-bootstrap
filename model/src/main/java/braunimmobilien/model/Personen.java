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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Resolution;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Eigentuemer")
@Indexed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Personen extends BaseObject implements Serializable,Comparable<Personen>,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	protected Strassen strasse;
    protected String eigtHausnummer;
    protected String eigtInfo;
    protected String eigtHomepage;
    protected String eigtTelefone; 
    protected String eigtName; 
    protected String eigtAnschrift; 
    protected String eigtFirma;
    protected String eigtTel;
    protected Boolean eigtAktuell;  
 //   protected Long eigtstatus;
    protected Eigtstatus eigtstatus;
    protected String eigtBriefanrede; 
    protected String eigtEmail;
    @XmlJavaTypeAdapter(SQLDateAdapter.class)
    protected java.sql.Date eigtaufDatum;
    @XmlJavaTypeAdapter(SQLDateAdapter.class)
    protected java.sql.Date eigtletztKontakt;		
    @XmlJavaTypeAdapter(DateAdapter.class)
    protected java.util.Date eigtVorlage;	
    private List<Objperszuord> objperszuords = new ArrayList<Objperszuord>();
    private List<Mitarbeiter> mitarbeiter = new ArrayList<Mitarbeiter>();
    private List<Kunde> kunden = new ArrayList<Kunde>();
    private List<Scout> scouts = new ArrayList<Scout>();
	private List<Nachweise> nachweise = new ArrayList<Nachweise>();
    private List<Eigentuemermuster> eigentuemermuster = new ArrayList<Eigentuemermuster>();
    


public Personen(){
	 super();
	    setId(new Long(0));
	java.util.Date today = new java.util.Date();
	this.eigtaufDatum = new java.sql.Date(today.getTime());
	this.eigtletztKontakt = new java.sql.Date(today.getTime());
	this.eigtAktuell=new Boolean(true);
	this.eigtBriefanrede="Sehr geehrte Damen und Herren";
	
}


public Personen(Personen person){
	 super();
	 this.id=person.getId();
	this.eigtaufDatum = person.getEigtaufDatum();
	this.eigtletztKontakt = person.getEigtletztKontakt();
	this.eigtAktuell=person.getEigtAktuell();
	this.eigtBriefanrede=person.getEigtBriefanrede();
	this.strasse=person.getStrasse();
	this.eigtHausnummer=person.getEigtHausnummer();
	this.eigtInfo=person.getEigtInfo();
	this.eigtHomepage=person.getEigtHomepage();
	this.eigtTelefone=person.getEigtTelefone();
	this.eigtTel=person.getEigtTel();
	this.eigtName=person.getEigtName();
    this.eigtAnschrift=person.getEigtAnschrift();
    this.eigtFirma=person.getEigtFirma();
    this.eigtstatus=person.getEigtstatus();
    this.eigtEmail=person.getEigtEmail();
  
}

public void copyPersonen(Personen person){
	 	this.id=person.getId();
		this.eigtaufDatum = person.getEigtaufDatum();
		this.eigtletztKontakt = person.getEigtletztKontakt();
		this.eigtAktuell=person.getEigtAktuell();
		this.eigtBriefanrede=person.getEigtBriefanrede();
		this.eigtHausnummer=person.getEigtHausnummer();
		this.eigtInfo=person.getEigtInfo();
		this.eigtHomepage=person.getEigtHomepage();
		this.eigtTelefone=person.getEigtTelefone();
		this.eigtTel=person.getEigtTel();
		this.eigtName=person.getEigtName();
	    this.eigtAnschrift=person.getEigtAnschrift();
	    this.eigtFirma=person.getEigtFirma();
	    this.eigtEmail=person.getEigtEmail();
	}
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="EigtID",nullable = false, unique = true)
public Long getId() {
    return id;
}

@Column(name="EigtHausnummer",nullable = true, length=100, unique = false)
@Field
	public String getEigtHausnummer(){
		   return this.eigtHausnummer;
		}
@Column(name="EigtInfo",nullable = true,columnDefinition = "text", unique = false)
@Field
	public String getEigtInfo(){
		   return this.eigtInfo;
		}
@Column(name="EigtName",nullable = true,length=100,unique=false)
@Field
public String getEigtName(){
		   return this.eigtName;
		}
@Column(name="EigtAktuell",nullable = true,columnDefinition = "int(11)", unique = false)
@Field
public Boolean getEigtAktuell(){
		   return this.eigtAktuell;
		}
@Column(name="EigtTel",nullable = true,length=254, unique = false)
@Field
public String getEigtTel(){
		   return this.eigtTel;
		}
/*@Column(name="EigtStatus",nullable = true, unique = false)
@Field
public Long getEigtstatus(){
		   return this.eigtstatus;
		}*/
@ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
@JoinColumn(name="EigtStatus")
public Eigtstatus getEigtstatus(){
		   return this.eigtstatus;
		}
@Column(name="EigtBriefanrede",nullable = true,length=100, unique = false)
@Field
public String getEigtBriefanrede(){
		   return this.eigtBriefanrede;
		}
@Column(name="EigtEmail",nullable = true,length=50, unique = false)
@Field
public String getEigtEmail(){
		   return this.eigtEmail;
		}
@Column(name="EigtaufDatum",nullable = false, unique = false)
@Field
@DateBridge(resolution = Resolution.DAY)
public java.sql.Date getEigtaufDatum(){
		   return this.eigtaufDatum;
		}
@Column(name="EigtletztKontakt",nullable = false, unique = false)
@Field
@DateBridge(resolution = Resolution.DAY)
public java.sql.Date getEigtletztKontakt(){
		   return this.eigtletztKontakt;
		}


	 @Column(name="EigtAnschrift",nullable = true,length=254, unique = false)
	    @Field
	public String getEigtAnschrift() {
		return eigtAnschrift;
	}
	 @Column(name="EigtHomepage",nullable = true,length=250, unique = false)
	    @Field
	 public String getEigtHomepage() {
			return eigtHomepage;
		}
	 @Column(name="EigtTelefone",nullable = true,columnDefinition = "text", unique = false)
	 @Field
	 public String getEigtTelefone() {
			return eigtTelefone;
		}
	 @Column(name="EigtFirma",nullable = true,length=254, unique = false)
	    @Field
	 public String getEigtFirma() {
			return eigtFirma;
		}
	 @ManyToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name="EigtStrID",insertable=false, updatable=false, nullable=true)
	  public Strassen getStrasse() {
			return strasse;
		}
	 @Temporal(TemporalType.TIMESTAMP)
	   @Column(name="EigtVorlage",nullable = true, unique = false)
		   @Field
		   public java.util.Date getEigtVorlage(){
		   return this.eigtVorlage;
		}
	 
	  @XmlTransient
	   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	   @JoinColumn(name="KundEigtNr",nullable=false )
	   public List<Objperszuord> getObjperszuords() {
			return objperszuords;
		}
	  @XmlTransient
	   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	   @JoinColumn(name="MitEigtID",insertable=false, updatable=false, nullable=false )
	  @ContainedIn
	  public List<Mitarbeiter> getMitarbeiter() {
			return mitarbeiter;
		}
	
	  @XmlTransient
	   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	   @JoinColumn(name="KundEigtNr",insertable=true, updatable=true, nullable=true )
	  @ContainedIn
	  public List<Kunde> getKunden() {
			return kunden;
		}
	  @XmlTransient
	   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	   @JoinColumn(name="EigtID",insertable=true, updatable=true, nullable=true  )
	  public List<Scout> getScouts() {
			return scouts;
		}
	  @XmlTransient
	   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	   @JoinColumn(name="NachEigtNr",insertable=false, updatable=false, nullable=false )
	  public List<Nachweise> getNachweise() {
			return nachweise;
		}
	 
	  @XmlTransient
	   @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	   @JoinColumn(name="KundEigtNr",insertable=true, updatable=true, nullable=true )
		public List<Eigentuemermuster> getEigentuemermuster() {
			return eigentuemermuster;
		}
	  @Transient
	  @XmlTransient
	  public String[] getEigtXMLAnschrift() {
			String[] zeilen=(this.eigtAnschrift.replaceAll("&","&amp;")).split("\n");
			
			return zeilen;
		}
	 

		public void setStrasse(Strassen strasse) {
			this.strasse = strasse;
		}
	 
	 public boolean isEigtAktuell(){
		   return eigtAktuell.booleanValue();
		}
	
	 public void setMitarbeiter(List<Mitarbeiter> mitarbeiter) {
			this.mitarbeiter = mitarbeiter;
		}
	
	 public void addMitarbeiter(Mitarbeiter mitarbeiter) {
	        getMitarbeiter().add(mitarbeiter);
	    } 
	 
	 public void setKunden(List<Kunde> kunden) {
			this.kunden = kunden;
		}
	
	 public void addKunde(Kunde kunde) {
	        getKunden().add(kunde);
	    }
	
	 public void setScouts(List<Scout> scouts) {
			this.scouts = scouts;
		}
	 public void addScout(Scout scout) {
	        getScouts().add(scout);
	    }
	
	
	
	
	
	
	
	
	 public void setId( Long param ){
		   this.id= param;
		}
		
	
	
	

	public void setEigtHausnummer( String param ){
	   this.eigtHausnummer= param;
	}
	
	
	public void setEigtInfo( String param ){
	   this.eigtInfo= param;
	}
	

	public void setEigtName( String param ){
	   this.eigtName= param;
	}
	

	public void setEigtTel( String param ){
	   this.eigtTel= param;
	}
	
	

	public void setEigtAktuell( Boolean param ){
	   this.eigtAktuell= param;
	}
	




	/*public void setEigtstatus( Long param ){
	   this.eigtstatus= param;
	}*/
	
	
	public void setEigtstatus( Eigtstatus param ){
		   this.eigtstatus= param;
		}	
	

	public void setEigtBriefanrede( String param ){
	   this.eigtBriefanrede= param;
	}
	
	
	public void setEigtEmail( String param ){
	   this.eigtEmail= param;
	}
	
	public void setEigtaufDatum( java.sql.Date param ){
	   this.eigtaufDatum= param;
	}
	

	

	public void setEigtletztKontakt( java.sql.Date param ){
	   this.eigtletztKontakt= param;
	}
	public void setEigtVorlage( java.util.Date param ){
	   this.eigtVorlage= param;
	}
	
	public void setObjperszuords(List<Objperszuord> objperszuords) {
		this.objperszuords = objperszuords;
	}
	
	 public void addObjperszuord(Objperszuord objperszuord) {
	        getObjperszuords().add(objperszuord);
	    }
	 
	 public void setNachweise(List<Nachweise> nachweise) {
			this.nachweise = nachweise;
		}
	 public void addNachweis(Nachweise nachweis) {
	        getNachweise().add(nachweis);
	    } 

		
		
		

		public void setEigentuemermuster(List<Eigentuemermuster> eigentuemermuster) {
			this.eigentuemermuster = eigentuemermuster;
		}
		 public void addEigentuemermuster(Eigentuemermuster eigentuemermuster) {
		        getEigentuemermuster().add(eigentuemermuster);
		    } 
	public String toString()
	{
	 StringBuilder b = new StringBuilder();
		b.append("[Personen id = ")
			.append(this.getId())
			.append(", strasse = ")
	//		.append(strasse)
			.append(", eigtHausnummer = ")
			.append(eigtHausnummer)
			.append(", eigtInfo = ")
			.append(eigtInfo)
			.append(", eigtName = '")
			.append(eigtName)
			.append("', eigtstatus = '")
			.append(eigtstatus)
			.append("', eigtAnschrift = ")
			.append(eigtAnschrift)
			.append(", eigtFirma = ")
			.append(eigtFirma)
			.append(", eigtTel ")
			.append(eigtTel)
			.append(", eigtAktuell= ")
			.append(eigtAktuell)
		.append("', eigtBriefanrede = ")
		.append(eigtBriefanrede)
		.append(", eigtEmail = ")
		.append(eigtEmail)
		.append(", eigtaufDatum")
		.append(eigtaufDatum)
		.append(", eigtletztKontakt= ")
		.append(eigtletztKontakt)
		.append(", eigtTelefone= ")
		.append(eigtTelefone)
	.append("', eigtHomepage = ")
	.append(eigtHomepage)
.append(", eigtVorlage= ")
		.append(eigtVorlage)
;
	
			b.append("]");
		return b.toString();
	}


	public void setEigtAnschrift(String eigtAnschrift) {
		this.eigtAnschrift = eigtAnschrift;
	}


	


	public void setEigtFirma(String eigtFirma) {
		this.eigtFirma = eigtFirma;
	}	
	 

		public void setEigtTelefone(String eigtTelefone) {
			this.eigtTelefone = eigtTelefone;
		}
		 
			public void setEigtHomepage(String eigtHomepage) {
				this.eigtHomepage = eigtHomepage;
			} 
			
	
			
			
			  public boolean equals(Object o) {
			        if (this == o) {
			            return true;
			        }
			        if (!(o instanceof Personen)) {
			            return false;
			        }

			        final Personen person= (Personen) o;

			        return !(person != null ? !(person.getId().longValue()!=id.longValue()) : true);

			    }
			  public int hashCode() {
			        return (id != null ? id.hashCode() : 0);
			    }
			  public int compareTo(Personen personen ) {
				  String thisstring="";
				  String personenstring="";
				  if (this.eigtFirma!=null) thisstring+=this.eigtFirma;
				  if (personen.getEigtFirma()!=null) personenstring+=personen.getEigtFirma();
				  if (this.eigtName!=null) thisstring+=this.eigtName;
				  if (personen.getEigtName()!=null) personenstring+=personen.getEigtName();
			      
			        	return thisstring.compareTo(personenstring);       
			        
			    }
}
