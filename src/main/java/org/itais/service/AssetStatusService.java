package org.itais.service;

import java.util.List;

import org.itais.domain.AssetStatus;
import org.itais.repository.AssetStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetStatusService
{
    
	private AssetStatusRepository assetStatusRepository;

	@Autowired
	public AssetStatusService(AssetStatusRepository assetStatusRepository)
	{
		this.assetStatusRepository = assetStatusRepository;
	}

	public AssetStatus save(String assetStatus)
	{
		return assetStatusRepository.save(new AssetStatus(assetStatus));
	}

	public boolean exists(String assetStatus)
	{
		if(assetStatusRepository.findByStatus(assetStatus) == null)
			return false;

		return true;
	}

	public AssetStatus findById(Long id)
	{
		return assetStatusRepository.findById(id);
	}    

	public List<AssetStatus> list()
	{
		return assetStatusRepository.findAllByOrderByStatus();
	}

	public AssetStatus findByStatus(String assetStatus)
	{
		return assetStatusRepository.findByStatus(assetStatus);
	}
    
}
