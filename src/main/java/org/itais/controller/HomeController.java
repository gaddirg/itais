package org.itais.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
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
/**
 *Class defining the controller of home page of the complete system.
 *
 */
@Controller
public class HomeController
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
     * @param officeService details the reposervice layer and its functions
     * @param userService details the authenticated user service defined by service layer
     * @param inventoryService details the calls for proposal service declared in CFP service layer class
     */
    @Autowired
    public HomeController(OfficeService officeService, UserService userService,
	    InventoryService inventoryService)
    {
	super();
	this.officeService = officeService;
	this.userService = userService;
	this.inventoryService = inventoryService;
    }

    @RequestMapping("/")
    public String home()
    {
	return "index";
    }
    
    /**
     * 
     * @param model detaiks the sys admin model for authority
     * @return redirects system to authenticated admin to the "user/sysadmin" url
     */
    @PreAuthorize("hasAuthority('SYSADMIN_CREATE_PRIVILEGE')")
    @RequestMapping("/user/sysadmin")
    public String sysAdminCreate(Model model)
    {
	model.addAttribute("sysAdmin",new User());
	return "user/sysadmin";
	
    }
   
}