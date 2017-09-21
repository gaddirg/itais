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

    @Basic(optional = false)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
 
    @Column (nullable= false, unique = true)
    private String name;   
    
    @Column (nullable= false, unique = true)
    private String serialNumber;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String manufacturer;

    @Column (nullable= false,columnDefinition = "TEXT")
    private String model;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String osName;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String osVersion;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String osServicePack;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private long memory;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String hdd;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private String processorName;
    
    @Column (nullable= false,columnDefinition = "TEXT")
    private long processorCount;
    
    @Column (nullable= true,columnDefinition = "TEXT")
    private java.sql.Date acquisitionDate;
    
    @Column (nullable= true,columnDefinition = "TEXT")
    private double acquisitionCost;
    
    @Column (nullable= true,columnDefinition = "TEXT")
    private String warrantyProvider;
    
    @Column (nullable= true,columnDefinition = "TEXT")
    private java.sql.Date warrantyExpirationDate;
     
    @ManyToOne()
    @JoinColumn(name="office_id", nullable = false)
    private Office office;

    @ManyToOne()
    @JoinColumn(name="assetType_id", nullable = true)
    private AssetType assetType;

  
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsServicePack() {
		return osServicePack;
	}

	public void setOsServicePack(String osServicePack) {
		this.osServicePack = osServicePack;
	}

	public long getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public String getProcessorName() {
		return processorName;
	}

	public void setProcessorName(String processorName) {
		this.processorName = processorName;
	}

	public long getProcessorCount() {
		return processorCount;
	}

	public void setProcessorCount(long processorCount) {
		this.processorCount = processorCount;
	}

	public java.sql.Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(java.sql.Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public double getAcquisitionCost() {
		return acquisitionCost;
	}

	public void setAcquisitionCost(double acquisitionCost) {
		this.acquisitionCost = acquisitionCost;
	}

	public String getWarrantyProvider() {
		return warrantyProvider;
	}

	public void setWarrantyProvider(String warrantyProvider) {
		this.warrantyProvider = warrantyProvider;
	}

	public java.sql.Date getWarrantyExpirationDate() {
		return warrantyExpirationDate;
	}

	public void setWarrantyExpirationDate(java.sql.Date warrantyExpirationDate) {
		this.warrantyExpirationDate = warrantyExpirationDate;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public Inventory()
    {
    }

	public Inventory(String name, String serialNumber, String manufacturer, String model,
			String osName, String osVersion, String osServicePack, long memory, String hdd, String processorName,
			long processorCount, java.sql.Date acquisitionDate, double acquisitionCost, String warrantyProvider, 
			java.sql.Date warrantyExpirationDate, Office office, AssetType assetType) {
		super();
		this.name = name;
		this.serialNumber = serialNumber;
		this.manufacturer = manufacturer;
		this.model = model;
		this.osName = osName;
		this.osVersion = osVersion;
		this.osServicePack = osServicePack;
		this.memory = memory;
		this.hdd = hdd;
		this.processorName = processorName;
		this.processorCount = processorCount;
		this.acquisitionDate = acquisitionDate;
		this.acquisitionCost = acquisitionCost;
		this.warrantyProvider = warrantyProvider;
		this.warrantyExpirationDate = warrantyExpirationDate;
		this.office = office;
		this.assetType = assetType;
	}

	public Inventory(String name, String serialNumber, String manufacturer, String model,
			String osName, String osVersion, String osServicePack, String processorName,
			long processorCount, long memory, String hdd,  Office office, AssetType assetType) {
		super();
		this.name = name;
		this.serialNumber = serialNumber;
		this.manufacturer = manufacturer;
		this.model = model;
		this.osName = osName;
		this.osVersion = osVersion;
		this.osServicePack = osServicePack;
		this.memory = memory;
		this.hdd = hdd;
		this.processorName = processorName;
		this.processorCount = processorCount;
		this.office = office;
		this.assetType = assetType;
	}
	
	
}
