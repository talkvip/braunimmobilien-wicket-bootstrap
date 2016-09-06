package braunimmobilien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import static java.util.Locale.GERMANY;
import java.util.Locale;
import static java.text.DateFormat.SHORT;
import java.text.DateFormat;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlElement;

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
@Table(name = "Nachweise")
@Indexed
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Nachweise extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	
	  protected Angebot angebot;
	    protected Angebot angebot1;
	    protected Angebot angebot2;
	    protected Kunde kunde;
	    protected Mitarbeiter mitarbeiter; 
	
	    protected Xtyp xtyp;

	    @XmlJavaTypeAdapter(SQLDateAdapter.class)
	    protected java.sql.Date nachdatum;
	    protected Double nachgedruckt;
	    protected String anlage1;
	    protected String anlage2;
	    protected String anlage3;
	    protected String anlage4;
	    protected String unterlagen1;
	    protected String unterlagen2;
	    protected String unterlagen3;
	    protected String unterlagen4;
	    protected Double steuerfeld;
	    protected Personen person;
	    protected java.lang.Boolean nachversandt;
	    protected Long nachversandart;
	    protected Emailbrief emailbrief;
	    protected Objekte objekt;
	    protected String nachdoku;
	    protected Boolean email;

	    public Nachweise(){
		//	setId(new Long(0));
			java.util.Date today = new java.util.Date();
			this.nachdatum = new java.sql.Date(today.getTime());
			this.email=new java.lang.Boolean(false);
			this.nachversandt = new java.lang.Boolean(false);;
			this.nachgedruckt = 1.0;
			this.steuerfeld=1.0;
			this.nachversandart=new Long(1);
			}
	    
	    public Nachweise(Nachweise nachweis){
	 			this.nachdatum = nachweis.getNachdatum();
	 			this.email=nachweis.getEmail();
	 			this.nachversandt = nachweis.getNachversandt();
	 			this.nachgedruckt = nachweis.getNachgedruckt();
	 			this.steuerfeld=nachweis.getSteuerfeld();
	 			this.nachversandart=nachweis.getNachversandart();
	 			this.anlage1=nachweis.getAnlage1();
	 			this.anlage2=nachweis.getAnlage2();
	 			this.anlage3=nachweis.getAnlage3();
	 			this.anlage4=nachweis.getAnlage4();
	 			this.unterlagen1=nachweis.getUnterlagen1();
	 			this.unterlagen2=nachweis.getUnterlagen2();
	 			this.unterlagen3=nachweis.getUnterlagen3();
	 			this.unterlagen4=nachweis.getUnterlagen4();
	 			this.angebot=nachweis.getAngebot();
	 			this.angebot1=nachweis.getAngebot1();
	 			this.angebot2=nachweis.getAngebot2();
	 			this.kunde=nachweis.getKunde();
	 			this.mitarbeiter=nachweis.getMitarbeiter();
	 			this.xtyp=nachweis.getXtyp();
	 			this.objekt=nachweis.getObjekt();
	 			this.person=nachweis.getPerson();
	 			
	 			}
	    
	    
	    
	    public void copyNachweise(Nachweise nachweis){
			this.nachdatum = nachweis.getNachdatum();
			this.email=nachweis.getEmail();
			this.nachversandt = nachweis.getNachversandt();
			this.nachgedruckt = nachweis.getNachgedruckt();
			this.steuerfeld=nachweis.getSteuerfeld();
			this.nachversandart=nachweis.getNachversandart();
			this.anlage1=nachweis.getAnlage1();
			this.anlage2=nachweis.getAnlage2();
			this.anlage3=nachweis.getAnlage3();
			this.anlage4=nachweis.getAnlage4();
			this.unterlagen1=nachweis.getUnterlagen1();
			this.unterlagen2=nachweis.getUnterlagen2();
			this.unterlagen3=nachweis.getUnterlagen3();
			this.unterlagen4=nachweis.getUnterlagen4();
			
			}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@DocumentId
@Column(name="NachweisNr",nullable = false, unique = true)
public Long getId() {
    return id;
}


@Column(name="Nachversandt",nullable = true,  unique = false)
@Field
public java.lang.Boolean getNachversandt() {
	return nachversandt;
}
//@XmlTransient  
@IndexedEmbedded
@ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
@JoinColumn(name="NachAngNr",insertable=false, updatable=false)
//@ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
//@JoinColumn(name="NachAngNr")
public Angebot getAngebot(){
	   return this.angebot;
	}

//@XmlTransient  
@ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
@JoinColumn(name="NachAngNr1", insertable=false, updatable=false)
	public Angebot getAngebot1(){
	   return this.angebot1;
	}


@ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
@JoinColumn(name="NachEmailBrief", insertable=false, updatable=false)
public Emailbrief getEmailbrief() {
	return this.emailbrief;
}


@Column(name="Nachversandart",nullable = true,  unique = false)
@Field
public Long getNachversandart() {
	return nachversandart;
}



//@XmlTransient  
@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
@JoinColumn(name="NachAngNr2",nullable=true, insertable=false, updatable=false)
	public Angebot getAngebot2(){
	   return this.angebot2;
	}
//@XmlTransient  
@ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
@JoinColumn(name="NachKundNr",insertable=false, updatable=false)
	public Kunde getKunde(){
	   return this.kunde;
	}

//@XmlTransient  
@ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
@JoinColumn(name="NachMitarbNr")
	public Mitarbeiter getMitarbeiter(){
	   return this.mitarbeiter;
	}

	
	@ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name="NachXNr")
	public Xtyp getXtyp(){
	   return this.xtyp;
	}

	
	@Column(name="NachDatum",nullable = false, unique = false)
	@Field
		  @DateBridge(resolution = Resolution.DAY)
	public java.sql.Date getNachdatum(){
	   return this.nachdatum;
	}

	
	  @Transient
	  @XmlTransient
	public java.util.Date getDateutil(){
	   return new java.util.Date(this.nachdatum.getTime());
	}
	
	  @Transient
	  @XmlTransient
	public String getDatetext(){
		 
		    Locale locale = GERMANY;
		    int style = SHORT;
		   
		    DateFormat fmt = null;
		  
		        fmt = DateFormat.getDateInstance(style, locale);
		     return fmt.format(new java.util.Date(this.nachdatum.getTime()));
		    

	}
	  
	
	@Column(name="Nachgedruckt",nullable = false,columnDefinition = "double(31,30)",  unique = false)
	@Field
	public Double getNachgedruckt(){
	   return this.nachgedruckt;
	}

	
	@Column(name="Anlage1",nullable = true,length = 100,  unique = false)
	@Field
	public String getAnlage1(){
	   return this.anlage1;
	}

	
	@Column(name="Anlage2",nullable = true,length = 100,  unique = false)
	@Field
	public String getAnlage2(){
	   return this.anlage2;
	}

	
	@Column(name="Anlage3",nullable = true,length = 100,  unique = false)
	@Field
	public String getAnlage3(){
	   return this.anlage3;
	}

	
	@Column(name="Anlage4",nullable = true,length = 100,  unique = false)
	@Field
	public String getAnlage4(){
	   return this.anlage4;
	}

	
	@Column(name="Unterlagen1",nullable = true,length = 100,  unique = false)
	@Field
	public String getUnterlagen1(){
	   return this.unterlagen1;
	}

	
	@Column(name="Unterlagen2",nullable = true,length = 1000,  unique = false)
	@Field
	public String getUnterlagen2(){
	   return this.unterlagen2;
	}

	
	@Column(name="Unterlagen3",nullable = true,length = 100,  unique = false)
	@Field
	public String getUnterlagen3(){
	   return this.unterlagen3;
	}

	
	@Column(name="Unterlagen4",nullable = true,length = 100,  unique = false)
	@Field
	public String getUnterlagen4(){
	   return this.unterlagen4;
	}

	
	@Column(name="Steuerfeld",nullable = true,columnDefinition = "double(31,30)",  unique = false)
	@Field
	public Double getSteuerfeld(){
	   return this.steuerfeld;
	}
	
	@XmlTransient  
	 @ManyToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name="NachEigtNr", insertable=false, updatable=false, nullable=true)
	public Personen getPerson(){
	   return this.person;
	}

	@XmlTransient  
	 @ManyToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name="NachObjNr", insertable=false, updatable=false, nullable=true)
	public Objekte getObjekt(){
	   return this.objekt;
	}

	
	@Column(name="NachDoku",nullable = true,length = 50,  unique = false)
	@Field
	public String getNachdoku(){
	   return this.nachdoku;
	}

	
	@Column(name="email",nullable = true,columnDefinition = "int(4)",  unique = false)
	@Field
	public Boolean getEmail(){
	   return this.email;
	}

	public void setId(Long id) {
		this.id=id;
	}
	public void setEmailbrief(Emailbrief emailbrief) {
		this.emailbrief = emailbrief;
	}

	public void setAngebot( Angebot param ){
		   this.angebot= param;
		}

			public void setNachversandart(Long nachversandart) {
				this.nachversandart = nachversandart;
			}

			public void setAngebot1( Angebot param ){
				   this.angebot1= param;
				}
			public void setAngebot2( Angebot param ){
				   this.angebot2= param;}
				   
				   public void setKunde( Kunde param ){
					   this.kunde= param;
					}  
			public void setNachversandt(java.lang.Boolean nachversandt) {
				this.nachversandt = nachversandt;
			}
			public void setMitarbeiter( Mitarbeiter param ){
				   this.mitarbeiter= param;
				}
			
			public void setXtyp( Xtyp param ){
				   this.xtyp= param;
				}	
	/*		public void setNachxnr( Long param ){
				   this.nachxnr= param;
				}*/
			public void setNachdatum( java.sql.Date param ){
				   this.nachdatum= param;
				}
			public void setNachgedruckt( Double param ){
				   this.nachgedruckt= param;
				}
			public void setAnlage1( String param ){
				   this.anlage1= param;
				}
			
			public void setAnlage2( String param ){
				   this.anlage2= param;
				}
			public void setAnlage3( String param ){
				   this.anlage3= param;
				}
			
			public void setAnlage4( String param ){
				   this.anlage4= param;
				}
			
			public void setUnterlagen1( String param ){
				   this.unterlagen1= param;
				}
			
			public void setUnterlagen2( String param ){
				   this.unterlagen2= param;
				}
			public void setUnterlagen3( String param ){
				   this.unterlagen3= param;
				}
			public void setUnterlagen4( String param ){
				   this.unterlagen4= param;
				}
			
			public void setSteuerfeld( Double param ){
				   this.steuerfeld= param;
				}
			public void setPerson( Personen person){
				   this.person = person;
				}
			public void setObjekt( Objekte param ){
				   this.objekt= param;
				}	
			public void setNachdoku( String param ){
				   this.nachdoku= param;
				}
			public void setEmail( Boolean param ){
				   this.email= param;
				}
			  @Transient
			  @XmlTransient
			public String[] getNachweisXMLText() {
				String[] zeilen=this.getUnterlagen2().split("\n");
				
				return zeilen;
			}		
			
			
			
			
			
			
			public String toString()
	{
		
		 
	 StringBuilder b = new StringBuilder();
		b.append("[Nachweise id = ")
			.append(this.getId())
		/*	.append(", nachangnr = ")
			.append(angebot.getId())
				.append(", nachangnr1 = ")
			.append(angebot1.getId())
				.append(", nachangnr2 = ")
			.append(angebot2.getId())
			.append(", kunde = ")
			.append(kunde)
				.append(", mitarbeiter = ")
			
				.append(", xtyp = ")
			.append(xtyp)
		
			.append(", nachdatum = ")
			.append(nachdatum)
			.append(", nachgedruckt = ")
			.append(nachgedruckt)
				.append(", anlage1 = ")
			.append(anlage1)
				.append(", anlage2 = ")
			.append(anlage2)
			.append(", anlage3 = ")
			.append(anlage3)
			.append(", anlage4 = ")
			.append(anlage4)
				.append(", unterlagen1 = ")
			.append(unterlagen1)
				.append(", unterlagen2 = ")
			.append(unterlagen2)
			.append(", unterlagen3 = ")
			.append(unterlagen3)
			.append(", unterlagen4 = ")
			.append(unterlagen4)
			.append(", nachdoku = ")
			.append(nachdoku)
				.append(", steuerfeld = ")
			.append(steuerfeld)
				.append(", person = ")
			.append(person)
			.append(", nachobjnr = ")
			.append(objekt.getId())
			.append(", email = ")
			.append(email)
			.append(", nachversandt = ")
			.append(nachversandt)
			.append(", nachversandart = ")
			.append(nachversandart)
			.append(", nachemailbrief = ")
			.append(emailbrief)*/
			.append("]");
		return b.toString();
	}
	   

	

























	
				
				
		

			










	












public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Nachweise)) {
        return false;
    }

    final Nachweise nachweise= (Nachweise) o;

    return !(nachweise != null ? !(nachweise.getId().longValue()!=id.longValue()) : true);

}
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}








	}



