package org.itais.domain;

import java.util.Collection;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private Long id;

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

    // Setting relations of th edifferent users
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	    @JoinColumn(name = "role_id") })
    private Collection<Role> roles;

    @OneToMany(mappedBy = "creator", cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Office> createdOffices = new HashSet<Office>();

    @OneToOne()
    @JoinColumn(name="office_id", nullable = true)
    private Office office;    
   
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Proposal> createdProposals = new HashSet<Proposal>();
/**
 * returning user object to the immidiate super class
 */
    public User()
    {
	super();
	
    }

    /**storing user details in the database
     * 
     * @param email returns user email to the immediate super class
     * @param password returns user password to the immediate super class
     * @param firstName Returns user first name to the immediate super class
     * @param lastName returns user last name to the immediate super class
     * @param institution returns the user institution to immediate super class
     * @param roles returns user role to immediate super class
     */
    public User(String email, String password, String firstName, String lastName, Office office,
	    Collection<Role> roles)
    {
	super();
	this.email = email;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.office = office;
	this.roles = roles;
    }

/**
 * 
 * @return the user id to the id variable(Long Type)
 */
    public Long getId()
    {
	return id;
    }

    public void setId(Long id)
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
     * @return the set of Repos to the OwnedRepo Object
     */
    public Office getOffice()
    {
	return office;
    }

    public void setOffice(Office office)
    {
	this.office = office;
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
    public Collection<Role> getRoles()
    {
	return roles;
    }

    public void setRoles(Collection<Role> roles)
    {
	this.roles = roles;
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
    
    public Set<Proposal> getCreatedProposals()
    {
        return createdProposals;
    }

    public void setCreatedProposals(Set<Proposal> createdProposals)
    {
        this.createdProposals = createdProposals;
    }

    @Override
    public String toString()
    {
	return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
    }

}
