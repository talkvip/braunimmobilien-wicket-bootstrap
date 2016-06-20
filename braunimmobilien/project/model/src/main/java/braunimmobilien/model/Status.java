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
@Table(name = "Status")
@Indexed
@XmlRootElement
public class Status extends BaseObject implements Serializable{
	static final long serialVersionUID=3832626162173359411L;
	private Long id;
  private String statustext;

  private String statuskuerzel;

 

  /**
   * The default constructor
   */
  public Status() {
    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @DocumentId
  @Column(name="StatusNr",nullable = false, unique = true)
  public Long getId() {
      return id;
  }
  @Column(name="Statustext",nullable = false, length = 50, unique = true)
  @Field
  public String getStatustext() {
    return statustext;
  }

  /**
   * @param statustext
   *          the statustext to set
   */
  public void setStatustext(String statustext) {
    this.statustext = statustext;
  }

  @Column(name="StatusKuerzel",nullable = false, length = 50, unique = false)
  @Field
  public String getStatuskuerzel() {
    return statuskuerzel;
  }

  /**
   * @param statuskuerzel
   *          the statuskuerzel to set
   */
  public void setStatuskuerzel(String statuskuerzel) {
    this.statuskuerzel = statuskuerzel;
  }
  public void setId( Long param ){
	   this.id= param;
	}
  
  public String toString()
	{
	 StringBuilder b = new StringBuilder();
		b.append("[Objekte id = ")
			.append(this.getId())
			.append(", statustext = ")
			.append(statustext)
			.append(", statuskuerzel = ")
			.append(statuskuerzel);
			
			b.append("]");
		return b.toString();
	}
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (!(o instanceof Status)) {
          return false;
      }

      final Status status= (Status) o;
      return !(statustext != null ? !statustext.equals(status.getStatustext()) : status.getStatustext() != null);

     
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
      return (id != null ? id.hashCode() : 0);
  }
}
