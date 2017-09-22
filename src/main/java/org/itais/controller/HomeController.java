package org.itais.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.itais.domain.Inventory;
import org.itais.domain.Office;
import org.itais.domain.User;
import org.itais.service.AssetTypeService;
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
    private AssetTypeService assetTypeService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception
    {
	auth.userDetailsService(userDetailsService);
    }

    @Autowired
    public HomeController(OfficeService officeService, UserService userService,
	    InventoryService inventoryService, AssetTypeService assetTypeService)
    {
	super();
	this.officeService = officeService;
	this.userService = userService;
	this.inventoryService = inventoryService;
	this.assetTypeService = assetTypeService;
    }
    
	@RequestMapping("/")
	public String InventoryCreate(HttpServletRequest request, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		java.sql.Date currDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +60);
		java.sql.Date dateAfter60Days = new java.sql.Date(cal.getTime().getTime());
		
		if (request.isUserInRole("ROLE_ADMIN"))	     
		{
			model.addAttribute("offices", officeService.listForSA());
			model.addAttribute("assetTypes", assetTypeService.list());
			model.addAttribute("inventories", inventoryService.list());	
			model.addAttribute("invAboutToExpire", inventoryService.findByWarrantyExpirationDateBetween(currDate, dateAfter60Days));
			model.addAttribute("invExpired", inventoryService.findByWarrantyExpirationDateBefore(currDate));
		}
		else
		{
			model.addAttribute("offices", userService.findByEmail(auth.getName()).getOffice());
			model.addAttribute("inventories", userService.findByEmail(auth.getName()).getOffice().getInventories());
		}
		System.out.println(currDate);
		System.out.println(dateAfter60Days);
		return "index";
	}
    
    
    
   
}