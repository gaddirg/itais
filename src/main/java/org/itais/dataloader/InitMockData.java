package org.itais.dataloader;

import org.itais.domain.Inventory;
import org.itais.domain.Office;
import org.itais.domain.User;
import org.itais.repository.AssetStatusRepository;
import org.itais.repository.AssetTypeRepository;
import org.itais.repository.InventoryRepository;
import org.itais.repository.OfficeRepository;
import org.itais.repository.RoleRepository;
import org.itais.repository.UserRepository;

public class InitMockData
{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private OfficeRepository officeRepository;
	private InventoryRepository inventoryRepository;
	private AssetTypeRepository assetTypeRepository;
	private AssetStatusRepository assetStatusRepository;

	public InitMockData(RoleRepository rRepository, UserRepository uRepository, 
			OfficeRepository oRepository, InventoryRepository iRepository, AssetTypeRepository aTRepository, AssetStatusRepository aSRepository)
	{
		this.roleRepository = rRepository;
		this.userRepository = uRepository;
		this.officeRepository = oRepository;
		this.inventoryRepository = iRepository;
		this.assetTypeRepository = aTRepository;
		this.assetStatusRepository = aSRepository;		
	}


	public void initData()
	{
		
		final Office office1 = new Office("Head Office","Bourke Street, Melbourne, VIC 3032","Head Office Description",userRepository.findByEmail("admin@company.org"),true);
		officeRepository.save(office1);
		final Office office2 = new Office("Sydney Branch","Balfour St, Chippendale NSW 2008","Sydney Branch Office description",userRepository.findByEmail("admin@company.org"),true);
		officeRepository.save(office2);
		final Office office3 = new Office("Canberra Branch","Bunda St, Canberra ACT 2601","Canberra Branch Office description",userRepository.findByEmail("admin@company.org"),true);
		officeRepository.save(office3);
		final Office office4 = new Office("Perth Branch","Victoria Square, Perth WA 6000","Perth Branch Office description",userRepository.findByEmail("admin@company.org"),true);
		officeRepository.save(office4);		
		
		
		final User user2 = new User("user1@company.org","user1","Mark","Gomez",officeRepository.findByName("Canberra Branch"),roleRepository.findByName("ROLE_USER"));
		userRepository.save(user2);
		final User user3 = new User("user2@company.org","user2","Katy","Jones",officeRepository.findByName("Sydney Branch"),roleRepository.findByName("ROLE_USER"));
		userRepository.save(user3);
		
		java.sql.Date acquisitionDate, warrantyDate1, warrantyDate2; 
		acquisitionDate = java.sql.Date.valueOf("2017-12-25");
		warrantyDate1 = java.sql.Date.valueOf("2017-10-01");
		warrantyDate2 = java.sql.Date.valueOf("2017-11-01");
		
			final Inventory inventory1 = new Inventory("DomainController01", "SN03929382", "HP", "model123456",
					"Windows Server 2012 R2", "6.2", "Service Pack 1", 10240, "C: 50000", "Intel(R) Core(TM) i5-4200U CPU @ 1.60GHz",
					1, acquisitionDate, 5000.00, "HP", warrantyDate1, officeRepository.findByName("Canberra Branch"), assetTypeRepository.findByType("Server"), 
					assetStatusRepository.findByStatus("Operational"));
			inventoryRepository.save(inventory1);
			final Inventory inventory2 = new Inventory("Database01", "SN083827373", "Dell", "mode985857848",
					"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i7-4200U CPU @ 2.00GHz",
					2, acquisitionDate, 80230.00, "Dell", warrantyDate2, officeRepository.findByName("Sydney Branch"), assetTypeRepository.findByType("Virtual Machine"),
					assetStatusRepository.findByStatus("Operational"));
			inventoryRepository.save(inventory2);
			
 
		
	}

}
