package org.itais.dataloader;

import java.util.Arrays;
import java.util.Collection;
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
	final Office office1 = new Office("Head Office","Manila","Head Office Description",userRepository.findByEmail("admin@company.org"),true);
	officeRepository.save(office1);
	final Office office2 = new Office("Region 1","Pangasinan","Office description",userRepository.findByEmail("admin@company.org"),true);
	officeRepository.save(office2);
	
	final User user2 = new User("r1_user@company.org","r1","Mark","Gomez",officeRepository.findByName("Head Office"),Arrays.asList(roleRepository.findByName("ROLE_USER")));
	userRepository.save(user2);
	final User user3 = new User("r2_user@company.org","r2","Katy","Jones",officeRepository.findByName("Region 1"),Arrays.asList(roleRepository.findByName("ROLE_USER")));
	userRepository.save(user3);

	final Inventory inventory1 = new Inventory("sn0001","Server 1", assetTypeRepository.findByType("Server"), officeRepository.findById((long) 1));
	inventoryRepository.save(inventory1);
	final Inventory inventory2 = new Inventory("sn0002","Server 2",assetTypeRepository.findByType("Virtual Machine"),officeRepository.findById((long) 2));;
	inventoryRepository.save(inventory2);
    }
    
}
