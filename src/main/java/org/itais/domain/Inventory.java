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
public class Inventory
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    
    private long id;
    
    @Column (nullable= false, unique = true)
    private String serialNumber;
    
    @Basic(optional = false)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String name;
    
    @ManyToOne()
    @JoinColumn(name="office_id", nullable = false)
    private Office office;

    @ManyToOne()
    @JoinColumn(name="assetType_id", nullable = true)
    private AssetType assetType;
    
    public Inventory()
    {
    }

	public Inventory(String serialNumber, String name, AssetType assetType, Office office)
    {
	super();
	this.serialNumber = serialNumber;
	this.name = name;
	this.assetType = assetType;
	this.office = office;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
   }
    
    public Date getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
        this.createdOn = createdOn;
    }
    
    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Office getOffice()
    {
        return office;
    }

    public void setOffice(Office office)
    {
        this.office = office;
    }
    
    public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

}
