package org.itais.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.itais.domain.Office;
import org.itais.repository.OfficeRepository;
import org.itais.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class OfficeService
{

    private UserRepository userRepository;
    private OfficeRepository officeRepository;

    @Autowired
    public OfficeService(OfficeRepository officeRepository, UserRepository userRepository)
    {
	this.officeRepository = officeRepository;
	this.userRepository = userRepository;
    }

    public Office get(Long id)
    {
	return officeRepository.findOne(id);
    }

    public Office save(Office office)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Office tempRepo = officeRepository.findById(office.getId());
	if (tempRepo == null)
	{
	    office.setCreator(userRepository.findByEmail(auth.getName()));
	    return officeRepository.save(office);
	} else
	{
	    Office officeDetails = new Office();
	    officeDetails.setId(office.getId());
	    officeDetails.setCreatedOn(tempRepo.getCreatedOn());
	    officeDetails.setCreator(tempRepo.getCreator());
	    officeDetails.setDescription(office.getDescription());
	    officeDetails.setLocation(office.getLocation());
	    officeDetails.setStatus(office.getStatus());
	    officeDetails.setName(office.getName());
	    officeDetails.setInventories(office.getInventories());
	    return officeRepository.save(officeDetails);
	}
    }
    
    public void delete(Office office)
    {
	officeRepository.delete(office.getId());
    }
    
    public void delete(Long id)
    {
	System.out.println(id);
	officeRepository.delete(id);
    }

    @Transactional()
    public List<Office> listForSA()
    {
	return officeRepository.findAllByOrderByCreatedOnDesc();
    }
    
    @Transactional()
    public List<Office> listForOtherRoles()
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	List<Office> offices = new ArrayList<Office>();
	offices.addAll(officeRepository.findAllByStatusOrderByCreatedOnDesc(true));
//	offices.addAll(officeRepository.findAllByOwnerAndStatusOrderByCreatedOnDesc(userRepository.findByEmail(auth.getName()), false));
	return offices;
    }
    
    public Office findByName(String name)
    {
	return officeRepository.findByName(name);
    }
    
    public Office findById(Long Id)
    {
	return officeRepository.findById(Id);
    }

}
