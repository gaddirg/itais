package org.itais.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.itais.domain.Inventory;
import org.itais.domain.Office;
import org.itais.domain.User;
import org.itais.service.InventoryService;
import org.itais.service.OfficeService;
import org.itais.service.UserDetailsImpl;
import org.itais.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.security.authentication.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class InventoryController
{

    private OfficeService officeService;
    private UserService userService;
    private InventoryService inventoryService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception
    {
	auth.userDetailsService(userDetailsService);
    }
    
    @Autowired
    public InventoryController(OfficeService officeService, UserService userService,
	    InventoryService inventoryService)
    {
	super();
	this.officeService = officeService;
	this.userService = userService;
	this.inventoryService = inventoryService;
    }

    
    /**
     * @param 
     * @param 
     * @return 
     */    
    @RequestMapping("/inventory/create")
    public String InventoryCreate(HttpServletRequest request, Model model)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	
	if (request.isUserInRole("ROLE_ADMIN"))	     
	{
		model.addAttribute("offices", officeService.listForSA());
	}
	else
	{
		model.addAttribute("offices", userService.findByEmail(auth.getName()).getOffice());
	}
	model.addAttribute("inventories", new Inventory());
	return "inventory/create";
    }

    
    /**
     * @param 
     * @param 
     * @return 
     */    
    @RequestMapping("/inventory/create/{id}")
    public String InventoryCreateWithPreselectedOffice(Model model,@PathVariable Long id)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	model.addAttribute("offices", Arrays.asList(officeService.findById(id)));
	model.addAttribute("inventories", new Inventory());
	return "inventory/create";
    }
    
    
    /**
     * @param 
     * @param 
     * @return 
     */    
    @RequestMapping(value = "/inventory/create", method = RequestMethod.POST)
    public String InventorySave(@ModelAttribute Inventory inventory)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	Inventory savedCallForProposals = inventoryService.save(inventory);

	return "redirect:read/" + savedCallForProposals.getId();
    }

    
    /**
     * @param 
     * @param 
     * @return 
     */
    @RequestMapping("/inventory/edit/{id}")
    public String InventoryEdit(@PathVariable Long id, Model model)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	model.addAttribute("offices", Arrays.asList((inventoryService.findById(id)).getOffice()));
	model.addAttribute("inventories", inventoryService.findById(id));
	return "inventory/create";
    }

    
    /**
     * @param 
     * @param 
     * @return 
     */
    @RequestMapping("/inventory/read/{id}")
    public String InventoryRead(@PathVariable Long id, Model model)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	model.addAttribute("inventories", inventoryService.findById(id));
	return "inventory/read";
    }


    /**
     * @param 
     * @param 
     * @return 
     */
    @RequestMapping("/inventory/main")
    public String InventoryMain(HttpServletRequest request, Model model)
    {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
	if (request.isUserInRole("ROLE_ADMIN"))	     
	{
		model.addAttribute("inventories", inventoryService.list());
	}
	else
	{
		model.addAttribute("inventories", userService.findByEmail(auth.getName()).getOffice().getInventories());
	}
  
	return "inventory/main";
    }
    
    
    /**
     * @param 
     * @param 
     * @return 
     */
    @RequestMapping("/inventory/delete/{id}")
    public String InventoryDelete(@PathVariable Long id, Model model)
    {
	model.addAttribute("inventories", inventoryService.findById(id));
	return "inventory/delete";
    }
    

    /**
     * @param 
     * @param 
     * @return 
     */    
    @RequestMapping("/inventory/deleteconfirmed/{id}")
    public RedirectView InventoryDeleteConfirmed(@PathVariable Long id, Model model)
    {
	inventoryService.delete(id);
	 return new RedirectView("/inventory/main");
    }
   
}