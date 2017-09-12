package org.itais.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.json.JSONArray;
import org.json.JSONObject;
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
/**
 * 
 *controller class  for repository management field and functions in MVC structure implementing request mapping.
 */
@Controller
public class OfficeController
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
    /**
     * 
     * @param officeService
     * 
     */
    @Autowired
    public OfficeController(OfficeService officeService, UserService userService,
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/office/create")
    public String repoCreate(Model model)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	List<User> users = userService.list();
	users.remove((userService.findByEmail(auth.getName())));
	users.add(0,userService.findByEmail(auth.getName()));
	model.addAttribute("users",users);
	model.addAttribute("offices", new Office());
	
	return "office/create";
    }


    /**
    * @param 
    * @param 
    * @return 
    */  
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/office/create", method = RequestMethod.POST)
    public String repoSave(@ModelAttribute Office offices)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	Office savedOffice = officeService.save(offices);
	
	Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(newAuth);
	
	return "redirect:read/" + savedOffice.getId();
    }


    /**
    * @param 
    * @param 
    * @return 
    */  
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/office/edit/{id}")
    public String repoEdit(@PathVariable Long id, Model model)
    {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	model.addAttribute("offices", new Office());
	List<User> users = userService.list();
	users.remove((userService.findByEmail(auth.getName())));
	users.add(0,userService.findByEmail(auth.getName()));
	model.addAttribute("users",users);
	model.addAttribute("offices", officeService.findById(id));
	return "office/create";
    }

    @RequestMapping("/office/read/{id}")
    public String repoRead(@PathVariable Long id, Model model)
    {
	model.addAttribute("offices", officeService.findById(id));
	return "office/read";
    }

    /**
    * @param 
    * @param 
    * @return 
    */  
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/office/delete/{id}")
    public String repoDelete(@PathVariable Long id, Model model)
    {
	model.addAttribute("offices", officeService.findById(id));
	return "office/delete";
    }


    /**
    * @param 
    * @param 
    * @return 
    */  
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/office/deleteconfirmed/{id}")
    public RedirectView repoDeleteConfirmed(@PathVariable Long id, Model model)
    {
	officeService.delete(id);
	return new RedirectView("/office/main");
    }
    
    @RequestMapping("/office/main")
    public String repoList(HttpServletRequest request, Model model)
    {
    	
    model.addAttribute("offices", officeService.listForSA());
	return "office/main";
    }
}