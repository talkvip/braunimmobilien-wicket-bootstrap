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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
@Entity
@Table(name = "USER")
@Indexed
@XmlRootElement
public class Nutzer extends BaseObject implements Serializable
{
	static final long serialVersionUID = 3832626162173359411L;
	private Long id;

	
	
	private String username;

	  private String password;

	  private String description;
	  
	  private Boolean admin;

	

	  public Nutzer() {
	    super();
	  }
	  
	  
	  @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@DocumentId
	@Column(name="ID",nullable = false, unique = true)
	@Field(name="ID")
	  public Long getId() {
			return id;
		}
	  @Column(nullable = false, length = 50, unique = true)
	    @Field(name="USERNAME")
	  public String getUsername() {
	    return username;
	  }

	 
	 
	  @Column(nullable = false, length = 50, unique = false)
	    @Field(name="PASSWORD")
	  public String getPassword() {
	    return password;
	  }

	  @Column(nullable = false, length = 50, unique = false)
	    @Field(name="DESCRIPTION")
	  public String getDescription() {
	    return description;
	  }
	  @Column(name="ADMIN",nullable = false, columnDefinition = "int(11)", unique = false)
		@Field()
	  public Boolean getAdmin() {
			return admin;
		}
		  public boolean isAdmin() {
				return admin.booleanValue();
				}
		  
		public void setAdmin(Boolean admin) {
			this.admin = admin;
		}

	  public void setId(Long id) {
			this.id = id;
		}
	  public void setUsername(String username) {
		    this.username = username;
		  }

	  
	  public void setDescription(String description) {
	    this.description = description;
	  }

	  public void setPassword(String password) {
		    this.password = password;
		  }
	  public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (!(o instanceof Nutzer)) {
	            return false;
	        }

	        final Nutzer nutzer= (Nutzer) o;

	        return !(username != null ? !username.equals(nutzer.getUsername()) : nutzer.getUsername() != null);

	    }

	    /**
	     * {@inheritDoc}
	     */
	    public int hashCode() {
	        return (username != null ? username.hashCode() : 0);
	    }

	public String toString()
	{
		        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
		    .append("[Nutzer id = ")
			.append(this.getId())
			.append(", username = ")
			.append(username)
				.append(", password = ")
			.append(password)
				.append(", admin = ")
			.append(admin)
			.append("]");
		return sb.toString();
	}	
	  
	  
	}