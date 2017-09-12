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

@Entity
@Table (name = "inventory")

/**
 * 
 *This is domain class for Call For Proposals defining 
 *objects used in project database and there relation with different objects
 */
public class Inventory
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    
    // defines CFP Id variable of long type generated automatically 
    private long id;
    
    // Defines CFP serialNumber variable of string type stored in unique table column with default type
    @Column (nullable= false, unique = true)
    private String serialNumber;
    
    @Basic(optional = false)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String name;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String type;
    
    // joining repo_id for table repo with many to one relationship
    @ManyToOne()
    @JoinColumn(name="office_id", nullable = false)
    private Office office;
    
    @OneToMany(mappedBy="inventory", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Proposal> proposals = new HashSet<Proposal>();

   
    public Inventory()
    {
	
    }

    public Inventory(String serialNumber, String name, String type, Office office)
    {
	super();
	this.serialNumber = serialNumber;
	this.name = name;
	this.type = type;
	this.office = office;
    }
/**
 * @return value of Id object immediately of long  data type
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
 * @return value of createdOn object immediately of Date type
 */
    public Date getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }
    
    /**
     * @return the serialNumber object immediately of String data type 
     */
    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    /**
     * 
     * @return the name object immediately of String data type
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
     * @return the type object immediately of String data type
     */
    
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return the Repo object immediately of Repo data type
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
 * @return the Proposals in the form of proposal set
 */
    public Set<Proposal> getProposals()
    {
        return proposals;
    }

/**
 * 
 * @param proposals store the set of proposals in proposal object
 */
    
    public void setProposals(Set<Proposal> proposals)
    {
        this.proposals = proposals;
    }

    
}
