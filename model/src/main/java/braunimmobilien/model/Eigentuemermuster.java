package braunimmobilien.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
@Entity
@Table(name = "Eigentuemermuster")
@Indexed
@XmlRootElement
public class Eigentuemermuster extends BaseObject implements Serializable,Identifiable<Long>{
	
	private Long id;
    private String eigentuemermuster;
	private Personen person;
	private Type type;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	@Column(name="Zuordnungsnummer",nullable = false, unique = true)
 @Field()
	public Long getId() {
	    return id;
	}	  
	    public void setId(Long id) {
	        this.id = id;
	    }
	    @ManyToOne(cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
	    @JoinColumn(name="Eingangsordner",nullable = true)
public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type=type;
	}

public Eigentuemermuster(){
	super();
	
	eigentuemermuster="%%";
}

public Eigentuemermuster(String eigentuemermuster){
	super();
	
	this.eigentuemermuster=eigentuemermuster;
}


@Column(name="Eigentuemermuster",nullable = false, length = 100, unique = false)
@Field()
public String getEigentuemermuster() {
	return eigentuemermuster;
}

public void setEigentuemermuster(String eigentuemermuster) {
	this.eigentuemermuster = eigentuemermuster;
}

@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="KundEigtNr",nullable=true,insertable=true, updatable=true)
public Personen  getPerson() {
	return person;
}

public void setPerson(Personen person) {
	this.person = person;
}
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Eigentuemermuster)) {
        return false;
    }

    final Eigentuemermuster eigentuemermuster= (Eigentuemermuster) o;

    return !(this.eigentuemermuster != null ? !this.eigentuemermuster.equals(eigentuemermuster.getEigentuemermuster()) : eigentuemermuster.getEigentuemermuster() != null);

}
public int hashCode() {
    return (this.eigentuemermuster != null ?this.eigentuemermuster.hashCode() : 0);
}
public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Eigentuemermuster id = ")
		.append(this.getId())
		.append(", person = ")
		.append(person)
			.append(", eigentuemermuster = ")
		.append(eigentuemermuster)
			.append(", type = ")
		.append(type)
		.append("]");
	return b.toString();
}


	
	
	
	}


