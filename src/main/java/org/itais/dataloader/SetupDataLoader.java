package org.itais.dataloader;

import org.itais.domain.AssetStatus;
import org.itais.domain.AssetType;
import org.itais.domain.Office;
import org.itais.domain.Role;
import org.itais.domain.User;
import org.itais.repository.AssetStatusRepository;
import org.itais.repository.AssetTypeRepository;
import org.itais.repository.InventoryRepository;
import org.itais.repository.OfficeRepository;
import org.itais.repository.RoleRepository;
import org.itais.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>
{

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AssetTypeRepository assetTypeRepository;

	@Autowired
	private AssetStatusRepository assetStatusRepository;


	// API

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event)
	{
		if (alreadySetup)
		{
			return;
		}

		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");

		createAdminIfNotFound("admin@company.org");
		
		createAssetTypeIfNotFound("Server");
		createAssetTypeIfNotFound("Virtual Machine Host");
		createAssetTypeIfNotFound("Virtual Machine");
		createAssetTypeIfNotFound("Desktop");

		createAssetStatusIfNotFound("Operational");
		createAssetStatusIfNotFound("Non-operational");
		createAssetStatusIfNotFound("Decommissioned");

		// Upload mock data
		InitMockData initMockData = new InitMockData(roleRepository, userRepository, officeRepository, inventoryRepository, assetTypeRepository, assetStatusRepository);
		initMockData.initData();

		alreadySetup = true;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name)
	{
		Role role = roleRepository.findByName(name);
		if (role == null)
		{
			role = new Role(name);
			roleRepository.save(role);
		}
		return role;
	}


	@Transactional
	private final User createAdminIfNotFound(final String email)
	{
		User uEmail = userRepository.findByEmail(email);
		if (uEmail == null)
		{
			final User admin = new User("admin@company.org","admin","Admin","Systems",officeRepository.findByName("Head Office"),roleRepository.findByName("ROLE_ADMIN"));
			userRepository.save(admin);
		}
		return uEmail;
	}

	@Transactional
	private final AssetType createAssetTypeIfNotFound(final String type)
	{
		AssetType aType = assetTypeRepository.findByType(type);
		if (aType == null)
		{
			aType = new AssetType(type);
			assetTypeRepository.save(aType);
		}
		return aType;
	}    

	@Transactional
	private final AssetStatus createAssetStatusIfNotFound(final String status)
	{
		AssetStatus aStatus = assetStatusRepository.findByStatus(status);
		if (aStatus == null)
		{
			aStatus = new AssetStatus(status);
			assetStatusRepository.save(aStatus);
		}
		return aStatus;
	}    

}