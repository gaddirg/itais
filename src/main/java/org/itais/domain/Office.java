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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.itais.json.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * 
 * this class defines the Repo table and relation of its various fields with other table and objects
 *
 */
@Entity
@Table(name = "office")
public class Office
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String location;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @ManyToOne()
    @JoinColumn(name="creator_id",nullable = false)
    private User creator;
    
    @Type(type="true_false")
    private Boolean status = true;
    // set of CFPs as new object(As a hashset)
    
    @OneToMany(mappedBy="office", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Inventory> inventories = new HashSet<Inventory>();

    
    public Office()
    {
    }
/**
 * 
 * @param name returns Repo name to immediate super class
 * @param institution returns institute name (String type) to the immediate super class
 * @param location returns definition of the Repository (String type) to the immediate super class
 * @param description returns description of the Repository (String type) to the immediate super class
 * @param creator returns creator of the Repository (String type) to the immediate super class
 * @param owner returns owner of the Repository (String type) to the immediate super class
 * @param status returns status of the Repository (String type) to the immediate super class
 */
    public Office(String name, String location, String description, User creator, Boolean status)
    {
	super();
	this.name = name;
	this.location = location;
	this.description = description;
	this.creator = creator;
	this.status = status;
	this.inventories = inventories;
    }

    /**
     * 
     * @return the name of Repo Owner to the owner object(User type)
     */
    
/**
 * 
 * @return the name of creator of the repository to the creator object(User type)
 */
    public User getCreator()
    {
	return creator;
    }
/**
 * 
 * @return the date of creation of repo to the createdOn variable(Date type)
 */
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreatedOn()
    {
	return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
	this.createdOn = createdOn;
    }

    public void setCreator(User creator)
    {
	this.creator = creator;
    }

    public Office(String name)
    {
	this.setName(name);
    }
/**
 * 
 * @return repo id to the id variable(long type)
 */
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
 * @return repo name to the variable name (String type)
 */
    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }
/**
 * 
 * @return the definiton of repo to the definition variable(String type)
 */
    public String getLocation()
    {
	return location;
    }

    public void setLocation(String location)
    {
	this.location = location;
    }
/**
 * 
 * @return the description of Repo to the description variable(String type)
 */
    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }
    /**
     * 
     * @return the value of cfp to the set of CFPs
     */
    public Set<Inventory> getInventories()
    {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventory)
    {
        this.inventories = inventory;
    }
/**
 * 
 * @return the status of Repo to the status variable(Bolean type)
 */
    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }
    

    @Override
    public String toString()
    {
	return "Office [id=" + id + ", name=" + name + ", location=" + location + ", description=" + description
		+ ", user=" + creator + "]";
    }
}
