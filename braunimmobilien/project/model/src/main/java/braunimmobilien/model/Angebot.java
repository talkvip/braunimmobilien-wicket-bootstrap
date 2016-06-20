/**
 *
 * @author <a href="mailto:h.h.braun@kbr-immobilien.de">Heiner Braun</a>
 * @version CVS $Id: AngebotDAO.java 155366 2005-10-26 19:54:12Z braun $
*/
package braunimmobilien.model;

import java.sql.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.apache.lucene.document.DateTools;
import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.search.SearchException;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.bridge.ParameterizedBridge;
import org.hibernate.search.bridge.TwoWayStringBridge;
import org.hibernate.search.bridge.builtin.DateResolutionUtil;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Entity
@Table(name = "Angebote")
@Indexed
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Angebot extends BaseObject implements Serializable,Identifiable<String>,Comparable<Angebot>{
	static final long serialVersionUID=3832626162173359411L;
	
	private String id;
	
	private Angstatus angstatus;
	
	private String angkurzbeschreibung;

	private String anglagebeschreibung;
	
	 @XmlJavaTypeAdapter(SQLDateAdapter.class)
	private java.sql.Date  angaufdatum;

	private Integer anganz;

	private Kondition kondition;
	@XmlTransient
	private List<Angobjzuord> angobjzuords = new ArrayList<Angobjzuord>();
	@XmlTransient  private List<Nachweise> nachweise = new ArrayList<Nachweise>();
	@XmlTransient  private List<Nachweise> nachweise1 = new ArrayList<Nachweise>();
	@XmlTransient  private List<Nachweise> nachweise2 = new ArrayList<Nachweise>();


	

public Angebot(){
	java.util.Date today = new java.util.Date();
	this.angaufdatum = new java.sql.Date(today.getTime());
	this.anganz = new Integer(1);
}

@Id
@Column(name="AngNr",nullable = false,length=50, unique = true)
@DocumentId
public String getId() {
    return id;
}

@ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
@JoinColumn(name="AngStatus",nullable=false)
    public Angstatus getAngstatus() {
        return this.angstatus;
    }

    @Column(name="AngKurzBeschreibung",nullable = true,length = 100, unique = false)
    @Field
    public String getAngkurzbeschreibung() {
        return this.angkurzbeschreibung;
    }
    
	  @Column(name="AngAufDatum",nullable = false, unique = false)
	  @DateBridge(resolution = Resolution.DAY)
public java.sql.Date getAngaufdatum() {
     return this.angaufdatum;
 }

	  @Column(name="AnzANG",nullable = false,columnDefinition = "int(11)", unique = false)   
public Integer getAnganz() {
     return this.anganz;
 }
	  
@Column(name="AngLageBeschreibung",nullable = true,length = 100, unique = false)	  
@Field	  
public String getAnglagebeschreibung() {
     return this.anglagebeschreibung;
 }
  
@ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
@JoinColumn(name="Konditionen",nullable=false)
public Kondition getKondition() {
     return this.kondition;
 }
@XmlTransient 
@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
@JoinColumn(name="AngNr",nullable=false )
public List<Angobjzuord> getAngobjzuords() {
	return angobjzuords;
}
@XmlTransient 
@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
@JoinColumn(name="NachAngNr",nullable=false )
public List<Nachweise> getNachweise() {
	return nachweise;
}
@XmlTransient 
@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
@JoinColumn(name="NachAngNr1",nullable=false )
public List<Nachweise> getNachweise1() {
	return nachweise1;
}
@XmlTransient 
@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
@JoinColumn(name="NachAngNr2",nullable=false )
public List<Nachweise> getNachweise2() {
	return nachweise2;
}
public void setAngobjzuords(List<Angobjzuord> angobjzuords) {
	this.angobjzuords = angobjzuords;
}





public void setId(String id) {
    this.id = id;
}



public void setAngaufdatum(java.sql.Date newAngaufdatum) {
     this.angaufdatum = newAngaufdatum;
 }

public void setAnglagebeschreibung(String newAnglagebeschreibung) {
     this.anglagebeschreibung = newAnglagebeschreibung;
}

public void setAngkurzbeschreibung(String newAngkurzbeschreibung) {
    this.angkurzbeschreibung = newAngkurzbeschreibung;
}

public void setKondition(Kondition newKondition) {
     this.kondition = newKondition;
 }

public void setAnganz(Integer newAnganz) {
     this.anganz = newAnganz;
 }

public void setAngstatus(Angstatus newAngstatus) {
    this.angstatus = newAngstatus;
}
public void setNachweise(List<Nachweise> nachweise) {
	this.nachweise = nachweise;
}
public void setNachweise1(List<Nachweise> nachweise) {
	this.nachweise1 = nachweise;
}
public void setNachweise2(List<Nachweise> nachweise) {
	this.nachweise2 = nachweise;
}
  /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Angebot)) {
            return false;
        }

        final Angebot angebot= (Angebot) o;

        return !(angebot != null ? !(angebot.equals(this.getId())) : angebot.getId() != null);

    }
   
    public void addAngobjzuord(Angobjzuord angobjzuord) {
        getAngobjzuords().add(angobjzuord);
    }
    public void addNachweis(Nachweise param) {
        getNachweise().add(param);
    }
    public void addNachweis1(Nachweise param) {
        getNachweise1().add(param);
    }
    public void addNachweis2(Nachweise param) {
        getNachweise2().add(param);
    }
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    public String toString()
    {

     StringBuilder b = new StringBuilder();
  	b.append("[Angebot id = ")
    		.append(this.getId())
    		.append(", angstatus = ")
    	.append(this.angstatus.toString())
    	.append(", angkurzbeschreibung = ")
    	.append(this.angkurzbeschreibung)
    		.append(", anglagebeschreibung = ")
    	.append(this.anglagebeschreibung)
    	.append(", angaufdatum = ")
    	.append(this.angaufdatum)
    	.append(", anganz = ")
    	.append(this.anganz)
    	.append(", konditionen = ")
    	.append(this.kondition.toString())
     /* 	.append(", Angobjzuords [ = ");
    	Iterator it=angobjzuords.iterator();
    	while(it.hasNext()){
    	Angobjzuord angobjzuord=(Angobjzuord)	it.next();
    		
    		b.append(angobjzuord.toReducedString());
    	}*/
    		.append("]]");
    	return b.toString();
    }
    @Override
    public int compareTo(Angebot b) {
      if (b.getId() == null && this.getId() == null)
        return 0;
      
      if (this.getId() == null) 
        return 1;
      
      if (b.getId() == null) 
        return -1;
      
      if (!this.getId().substring(0, 1).equals(b.getId().substring(0, 1)))
      return this.getId().substring(0, 1).compareTo(b.getId().substring(0, 1));
      if (this.getId().length()>b.getId().length())
      return 1;
      if (this.getId().length()<b.getId().length())
          return -1;
      return this.getId().substring(2).compareTo(b.getId().substring(2));
    }
	}


   
