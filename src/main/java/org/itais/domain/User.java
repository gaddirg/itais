package org.itais.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 *this class defines the user domain of the system and its relation with other objects and classes
 */
@Entity
@Table(name = "users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @ManyToOne()
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @OneToOne()
    @JoinColumn(name="office_id", nullable = true)
    private Office office;   
   
 	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public void setCreatedOffices(Set<Office> createdOffices) {
		this.createdOffices = createdOffices;
	}

	@OneToMany(mappedBy = "creator", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Office> createdOffices = new HashSet<Office>();
   

    public long getId()
    {
	return id;
    }

    public void setId(long id)
    {
	this.id = id;
    }
/**
 * 
 * @return the user email to the email variable(string Type)
 */
    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

 /**
     * 
     * @return the user password to the password variable(String Type)
     */
    public String getPassword()
    {
	return password;
    }

    public void setPassword(String password)
    {
	this.password = password;
    }
/**
 * 
 * @return the user first name to the firstname variable(String Type)
 */
    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstName)
    {
	this.firstName = firstName;
    }
    /**
     * 
     * @return the user last name to the lastname variable(String Type)
     */
    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastName)
    {
	this.lastName = lastName;
    }

    /**
     * 
     * @return the Collection of ROle to roles object
     */
    public Role getRole()
    {
	return role;
    }

    public void setRole(Role role)
    {
	this.role = role;
    }
    /**
     * 
     * @return the set of Repo to the createdRepos object
     */
    public Set<Office> getCreatedOffices()
    {
	return createdOffices;
    }

    public void setCreatedRepos(Set<Office> createdOffices)
    {
	this.createdOffices = createdOffices;
    }
    
    /**
     * 
     * @return the user institution to the institution variable(String Type)
     */

    public Date getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }
   
    
    public User()
    {
	super();
	
    }

     public User(String email, String password, String firstName, String lastName, Office office,
	    Role role)
    {
	super();
	this.email = email;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.office = office;
	this.role = role;
    }
    
    
    @Override
    public String toString()
    {
	return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
    }

}
