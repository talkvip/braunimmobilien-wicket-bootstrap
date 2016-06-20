package braunimmobilien.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.request.mapper.parameter.PageParameters;

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
@Table(name = "Scout")
@Indexed
@XmlRootElement
public class Scout extends BaseObject implements Serializable,Identifiable<Long>{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
	private Boolean yesScout;  
	private String where;
	private String what;
   private String who;
 private String phone;
    private Personen person;
   private Objekte objekt;
 private Type type;
 private Objarttyp objarttyp;
 private Orte ort;

 

 
 
 @Id
// @GeneratedValue(strategy = GenerationType.AUTO)
 @DocumentId
 @Column(name="ScoutID",nullable = false, unique = true)
 public Long getId() {
     return id;
 }
 @Column(name="yesScout",nullable = true,columnDefinition = "int(11)", unique = false)
 @Field
 public Boolean getYesScout() {
		return yesScout;
	}


 
 
 @XmlTransient
 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name="OrtID", insertable=true, updatable=true, nullable=true)
public Orte getOrt() {
	return ort;
}

 @XmlTransient
 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name="Objarttyp", insertable=true, updatable=true, nullable=true)
public Objarttyp getObjarttyp() {
	return objarttyp;
}

 @XmlTransient
 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name="Bearbeitet", insertable=true, updatable=true, nullable=true)
public Type getType() {
	return type;
}



public Scout(){
	super();	   
		this.yesScout=new Boolean(true);
}

@Column(name="wogelegen",nullable = true,columnDefinition = "text", unique = false)
@Field
public String getWhere() {
	return where;
}


@Column(name="what",nullable = true,columnDefinition = "text", unique = false)
@Field
public String getWhat() {
	return what;
}


@Column(name="who",nullable = true,columnDefinition = "text", unique = false)
@Field
public String getWho() {
	return who;
}


@Column(name="phone",nullable = true,columnDefinition = "text", unique = false)
@Field
public String getPhone() {
	return phone;
}
@XmlTransient
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="EigtID", insertable=true, updatable=true, nullable=true)
public Personen getPerson() {
	return person;
}
@XmlTransient
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="ObjID", insertable=true, updatable=true, nullable=true)
public Objekte getObjekt() {
	return objekt;
}
public void setId(Long id) {
	this.id = id;
}
public boolean isYesScout(){
	   return yesScout.booleanValue();
	}
public void setYesScout(Boolean yesScout) {
	this.yesScout = yesScout;
}
public void setObjekt(Objekte objekt) {
	this.objekt=objekt;
}
public void setOrt(Orte ort) {
	this.ort = ort;
}
public void setObjarttyp(Objarttyp objarttyp) {
	this.objarttyp = objarttyp;
}
public void setType(Type type) {
	this.type = type;
}
public void setWhere(String where) {
	this.where = where;
}
public void setWhat(String what) {
	this.what = what;
}
public void setWho(String who) {
	this.who = who;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public void setPerson(Personen person) {
	this.person=person;
}
public int hashCode() {
    return (id != null ? id.hashCode() : 0);
}


public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof Scout)) {
        return false;
    }

    final Scout scout= (Scout) o;

    return !(scout != null ? !(scout.getId().longValue()!=id.longValue()) : true);

}

public String toString()
{

 StringBuilder b = new StringBuilder();
	b.append("[Scout id = ")
		.append(this.getId())
		.append(", where = ")
	//	.append(where)
		.append(", what = ")
		.append(what)
		.append(", who = ")
		.append(who)
		.append(", phone = ")
		.append(phone)
		.append(", type = ")
		.append(type)
			.append(", objarttyp = ")
		.append(objarttyp)
		.append(", ort = ")
		.append(ort.getOrtname())
		.append("]");
	return b.toString();
}
public void makePageParameters(PageParameters pars) {
	if(getId()!=null&&getId().shortValue()>0)	{
		pars.set("scoutid",getId().toString());
		
	}
	if(getObjekt()!=null&&getObjekt().getId().shortValue()>0)	{
		pars.set("objid",getObjekt().getId().toString());
		
	}
	if(getPerson()!=null&&getPerson().getId().shortValue()>0)	{
		pars.set("eigtid",getPerson().getId().toString());
		
	}
	if(getWhere()!=null&&getWhere().length()>0)	{
		pars.set("where",getWhere().replaceAll("&", " amp "));
		
	}
	if(getWho()!=null&&getWho().length()>0)	{
		pars.set("who",getWho().replaceAll("&", " amp "));
		
	}
}

	
	
	
	}


