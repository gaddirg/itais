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
public class AssetType
{

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String type;

	@OneToMany(mappedBy="assetType", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	private Set<Inventory> inventories = new HashSet<Inventory>();

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}


	public AssetType()
	{
		super();
	}

	public AssetType(final String type)
	{
		super();
		this.type = type;
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}


}
