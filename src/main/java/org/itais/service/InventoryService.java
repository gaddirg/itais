package org.itais.service;

import java.sql.Date;
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
			inventoryDetails.setCreatedOn(tempInventory.getCreatedOn());
			inventoryDetails.setName(inventory.getName());
			inventoryDetails.setSerialNumber(inventory.getSerialNumber());
			inventoryDetails.setManufacturer(inventory.getManufacturer());
			inventoryDetails.setModel(inventory.getModel());
			inventoryDetails.setOsName(inventory.getOsName());
			inventoryDetails.setOsVersion(inventory.getOsVersion());
			inventoryDetails.setOsServicePack(inventory.getOsServicePack());
			inventoryDetails.setMemory(inventory.getMemory());
			inventoryDetails.setHdd(inventory.getHdd());
			inventoryDetails.setProcessorName(inventory.getProcessorName());
			inventoryDetails.setProcessorCount(inventory.getProcessorCount());
			inventoryDetails.setAcquisitionDate(inventory.getAcquisitionDate());
			inventoryDetails.setAcquisitionCost(inventory.getAcquisitionCost());
			inventoryDetails.setWarrantyProvider(inventory.getWarrantyProvider());
			inventoryDetails.setWarrantyExpirationDate(inventory.getWarrantyExpirationDate());
			inventoryDetails.setAssetType(inventory.getAssetType());
			inventoryDetails.setAssetStatus(inventory.getAssetStatus());
			inventoryDetails.setOffice(inventory.getOffice());
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
	
	public List<Inventory> findByWarrantyExpirationDateBetween(Date date1, Date date2)
	{
		return inventoryRepository.findByWarrantyExpirationDateBetween(date1, date2);
	}
	
	public List<Inventory> findByWarrantyExpirationDateBefore(Date date)
	{
		return inventoryRepository.findByWarrantyExpirationDateBefore(date);
	}
	
	public List<Inventory> findByWarrantyExpirationDateBeforeAndOffice(Date date, Office office)
	{
		return inventoryRepository.findByWarrantyExpirationDateBeforeAndOffice(date, office);
	}

	public List<Inventory> findByWarrantyExpirationDateBetweenAndOffice(Date date1, Date date2, Office office)
	{
		return inventoryRepository.findByWarrantyExpirationDateBetweenAndOffice(date1, date2, office);
	}	
	
	public List<Inventory> findByOffice(Office office)
	{
		return inventoryRepository.findByOffice(office);
	}
}
