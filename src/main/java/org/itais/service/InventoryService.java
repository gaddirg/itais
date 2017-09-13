package org.itais.service;

import java.util.List;

import org.itais.domain.Inventory;
import org.itais.domain.Office;
import org.itais.repository.InventoryRepository;
import org.itais.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * 
 * 
 */
@Service
public class InventoryService
{

	private InventoryRepository inventoryRepository;

	@Autowired
	public InventoryService(InventoryRepository inventoryRepository)
	{
		this.inventoryRepository = inventoryRepository;
	}

	public Inventory get(Long id)
	{
		return inventoryRepository.findOne(id);
	}

	public Inventory save(Inventory inventory)
	{
		Inventory tempInventory = inventoryRepository.findById(inventory.getId());
		if(tempInventory == null)
			return inventoryRepository.save(inventory);
		else
		{
			Inventory inventoryDetails = new Inventory();
			inventoryDetails.setId(inventory.getId());
			inventoryDetails.setName(inventory.getName());
			inventoryDetails.setSerialNumber(inventory.getSerialNumber());
			inventoryDetails.setCreatedOn(tempInventory.getCreatedOn());
			inventoryDetails.setAssetType(inventory.getAssetType());
			inventoryDetails.setOffice(tempInventory.getOffice());
			return inventoryRepository.save(inventoryDetails);
		}
	}

	public List<Inventory> list()
	{
		return inventoryRepository.findAllByOrderByCreatedOnDesc();
	}

	public Inventory findBySerialNumber(String serialNumber)
	{
		return inventoryRepository.findBySerialNumber(serialNumber);
	}
	public Inventory findById(Long id)
	{
		// TODO Auto-generated method stub
		return inventoryRepository.findById(id);
	}

	public void delete(Long id)
	{
		// TODO Auto-generated method stub
		inventoryRepository.delete(id);

	}
}
