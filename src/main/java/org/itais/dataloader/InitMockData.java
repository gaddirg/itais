package org.itais.dataloader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.itais.domain.AssetType;
import org.itais.domain.Inventory;
import org.itais.domain.Office;
import org.itais.domain.Proposal;
import org.itais.domain.Role;
import org.itais.domain.User;
import org.itais.repository.AssetTypeRepository;
import org.itais.repository.InventoryRepository;
import org.itais.repository.OfficeRepository;
import org.itais.repository.ProposalRepository;
import org.itais.repository.RoleRepository;
import org.itais.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 *This Class sets initial users and roles for diifferent repositories of this website
 */
public class InitMockData
{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private OfficeRepository officeRepository;
	private InventoryRepository inventoryRepository;
	private AssetTypeRepository assetTypeRepository;


	public InitMockData(RoleRepository rRepository, UserRepository uRepository, 
			OfficeRepository oRepository, InventoryRepository iRepository, AssetTypeRepository aRepository)
	{
		this.roleRepository = rRepository;
		this.userRepository = uRepository;
		this.officeRepository = oRepository;
		this.inventoryRepository = iRepository;
		this.assetTypeRepository = aRepository;
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
		
		
		final User user2 = new User("r1_user@company.org","r1","Mark","Gomez",officeRepository.findByName("Head Office"),Arrays.asList(roleRepository.findByName("ROLE_USER")));
		userRepository.save(user2);
		final User user3 = new User("r2_user@company.org","r2","Katy","Jones",officeRepository.findByName("Region 1"),Arrays.asList(roleRepository.findByName("ROLE_USER")));
		userRepository.save(user3);

		java.sql.Date date1; 
		date1 = java.sql.Date.valueOf("2017-12-25");
		
			final Inventory inventory1 = new Inventory("DomainController01", "SN03929382", "HP", "model123456",
					"Windows Server 2012 R2", "6.2", "Service Pack 1", 10240, "C: 50000", "Intel(R) Core(TM) i5-4200U CPU @ 1.60GHz",
					1, date1, 5000.00, "HP", date1, officeRepository.findById((long) 1), assetTypeRepository.findByType("Server"));
			inventoryRepository.save(inventory1);
			final Inventory inventory2 = new Inventory("Database01", "SN083827373", "Dell", "mode985857848",
					"Windows Server 2008 R2", "10", "Service Pack 3", 20240, "C: 100000; E: 80000", "Intel(R) Core(TM) i7-4200U CPU @ 2.00GHz",
					2, date1, 80230.00, "Dell", date1, officeRepository.findById((long) 2), assetTypeRepository.findByType("Virtual Machine"));
			inventoryRepository.save(inventory2);
			
 

	}

}
