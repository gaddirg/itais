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
		
		java.sql.Date acquisitionDate, warrantyDate1, warrantyDate2, warrantyDate3, warrantyDate4, warrantyDate5, warrantyDate6, warrantyDate7, warrantyDate8, warrantyDate9, warrantyDate10 ; 
		acquisitionDate = java.sql.Date.valueOf("2017-12-25");
		warrantyDate1 = java.sql.Date.valueOf("2017-10-01");
		warrantyDate2 = java.sql.Date.valueOf("2016-01-01");
		warrantyDate3 = java.sql.Date.valueOf("2018-02-20");
		warrantyDate4 = java.sql.Date.valueOf("2017-12-02");
		warrantyDate5 = java.sql.Date.valueOf("2017-01-01");
		warrantyDate6 = java.sql.Date.valueOf("2018-11-01");
		warrantyDate7 = java.sql.Date.valueOf("2017-03-09");
		warrantyDate8 = java.sql.Date.valueOf("2017-11-05");
		warrantyDate9 = java.sql.Date.valueOf("2017-12-12");
		warrantyDate10 = java.sql.Date.valueOf("2016-03-01");
		
		
		final Inventory inventory1 = new Inventory("Server01", "03KDK293KD", "HP", "434-34",
				"Windows Server 2012 R2", "6.2", "Service Pack 1", 10240, "C: 50000", "Intel Xeon E5 1650 v6",
				1, acquisitionDate, 5000.00, "HP", warrantyDate1, officeRepository.findByName("Head Office"), assetTypeRepository.findByType("Server"), 
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory2 = new Inventory("Server02", "LDKE93K392", "Dell", "MD-339393",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel Xeon E3 1250 v3",
				2, acquisitionDate, 80230.00, "Dell", warrantyDate2, officeRepository.findByName("Sydney Branch"), assetTypeRepository.findByType("Server"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory3 = new Inventory("Server03", "39848FKVM", "IBM", "NZDK-0923293",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel Xeon E4 1456 v3",
				2, acquisitionDate, 80230.00, "IBM", warrantyDate3, officeRepository.findByName("Head Office"), assetTypeRepository.findByType("Virtual Machine Host"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory4 = new Inventory("Server04", "038KKCMDEK2", "HP", "NSLE-29384",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel Xeon E4 1650 v6",
				2, acquisitionDate, 80230.00, "HP", warrantyDate4, officeRepository.findByName("Sydney Branch"), assetTypeRepository.findByType("Virtual Machine"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory5 = new Inventory("Desktop01", "LDSE339483KF", "Acer", "9334-234324234",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i7-4200U CPU @ 2.00GHz",
				2, acquisitionDate, 80230.00, "Acer", warrantyDate5, officeRepository.findByName("Canberra Branch"), assetTypeRepository.findByType("Virtual Machine"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory6 = new Inventory("Desktop02", "737J8JEDUD", "Lenovo", "SMD-30349",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i3-2200U CPU @ 1.60GHz",
				2, acquisitionDate, 80230.00, "Lenovo", warrantyDate6, officeRepository.findByName("Perth Branch"), assetTypeRepository.findByType("Desktop"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory7 = new Inventory("Desktop03", "DKEU37FJ3DID", "Dell", "FKFKS-3943",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i5-3200U CPU @ 2.00GHz",
				2, acquisitionDate, 80230.00, "Dell", warrantyDate7, officeRepository.findByName("Sydney Branch"), assetTypeRepository.findByType("Desktop"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory8 = new Inventory("Desktop04", "DK33FM38FE", "HP", "KDLD-340393",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i3-2300U CPU @ 2.10GHz",
				2, acquisitionDate, 80230.00, "HP", warrantyDate8, officeRepository.findByName("Head Office"), assetTypeRepository.findByType("Desktop"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory9 = new Inventory("Laptop01", "KFK38KDM33", "Asus", "FSDJ-3049",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i5-1200U CPU @ 2.20GHz",
				2, acquisitionDate, 80230.00, "Asus", warrantyDate9, officeRepository.findByName("Canberra Branch"), assetTypeRepository.findByType("Desktop"),
				assetStatusRepository.findByStatus("Operational"));
		final Inventory inventory10 = new Inventory("Laptop02", "KDOE23KDM3JDMDS", "Acer", "MSMS-02993",
				"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i3-2200U CPU @ 1.60GHz",
				2, acquisitionDate, 80230.00, "Acer", warrantyDate10, officeRepository.findByName("Head Office"), assetTypeRepository.findByType("Desktop"),
				assetStatusRepository.findByStatus("Operational"));

		inventoryRepository.save(inventory1);
		inventoryRepository.save(inventory2);
		inventoryRepository.save(inventory3);
		inventoryRepository.save(inventory4);
		inventoryRepository.save(inventory5);
		inventoryRepository.save(inventory6);
		inventoryRepository.save(inventory7);
		inventoryRepository.save(inventory8);
		inventoryRepository.save(inventory9);
		inventoryRepository.save(inventory10);
				
	}

}
