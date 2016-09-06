package braunimmobilien.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "member")
public class Member extends AbstractEntity {

private static final long serialVersionUID = -6104400447575197988L;

@GenericGenerator(name = "MemberIdGenerator", strategy = "org.hibernate.id.MultipleHiLoPerTableGenerator", parameters = {
@Parameter(name = "table", value = "IdentityGenerator"),
@Parameter(name = "primary_key_column", value = "sequence_name"),
@Parameter(name = "primary_key_value", value = "Member"),
@Parameter(name = "value_column", value = "next_hi_value"),
@Parameter(name = "primary_key_length", value = "100"),
@Parameter(name = "max_lo", value = "1000") })

@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "MemberIdGenerator")
private Long id;

@Column(name = "firstname", length = 255, nullable = false)
private String firstName;

@Column(name = "lastname", length = 255, nullable = false)
private String lastName;

@Override
public Long getId() {
return id;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String toString(){
return "Member: "+firstName+" "+lastName;
}

}

