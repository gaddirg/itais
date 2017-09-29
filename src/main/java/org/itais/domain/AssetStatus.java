package org.itais.domain;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class AssetStatus
{

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String status;

	@OneToMany(mappedBy="assetStatus", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Set<Inventory> inventories = new HashSet<Inventory>();

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}


	public AssetStatus()
	{
		super();
	}

	public AssetStatus(final String status)
	{
		super();
		this.status = status;
	}

	//
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}


}
