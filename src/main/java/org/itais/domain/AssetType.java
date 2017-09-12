package org.itais.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class AssetType
{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    
    private String type;

    
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
