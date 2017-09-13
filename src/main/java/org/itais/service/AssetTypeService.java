package org.itais.service;

import java.util.List;

import org.itais.domain.AssetType;
import org.itais.domain.Inventory;
import org.itais.repository.AssetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetTypeService
{
    
    private AssetTypeRepository assetTypeRepository;
    
    @Autowired
    public AssetTypeService(AssetTypeRepository assetTypeRepository)
    {
	this.assetTypeRepository = assetTypeRepository;
    }
    
    public AssetType save(String assetType)
    {
	return assetTypeRepository.save(new AssetType(assetType));
    }
    
    public boolean exists(String assetType)
    {
	if(assetTypeRepository.findByType(assetType) == null)
	    return false;
	return true;
    }

	public AssetType findById(Long id)
	{
		return assetTypeRepository.findById(id);
	}    
    
	public List<AssetType> list()
	{
		return assetTypeRepository.findAllByOrderByType();
	}
    
}
