package org.itais.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.itais.json.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "office")
public class Office
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
	}

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

	public Office(String name, String location, String description, User creator, Boolean status)
	{
		super();
		this.name = name;
		this.location = location;
		this.description = description;
		this.creator = creator;
		this.status = status;
	}


	public User getCreator()
	{
		return creator;
	}

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

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Set<Inventory> getInventories()
	{
		return inventories;
	}

	public void setInventories(Set<Inventory> inventory)
	{
		this.inventories = inventory;
	}

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
