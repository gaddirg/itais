package org.itais.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.itais.domain.AssetType;
import org.itais.domain.Office;
import org.itais.service.AssetTypeService;
import org.itais.service.EmailServiceImpl;
import org.itais.service.InventoryService;
import org.itais.service.OfficeService;
import org.itais.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{

    private OfficeService officeService;
    private UserService userService;
    private InventoryService inventoryService;
    private AssetTypeService assetTypeService;

	@Autowired
	private EmailServiceImpl emailService;
    
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
	public String Home(HttpServletRequest request, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		java.sql.Date currDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +60);
		java.sql.Date dateAfter60Days = new java.sql.Date(cal.getTime().getTime());
		
		if (request.isUserInRole("ROLE_ADMIN"))	     
		{
			model.addAttribute("officeJson", getOfficeJsonAsString(officeService.listForSA()));
			model.addAttribute("assetTypeJson", getAssetTypeJsonAsString(assetTypeService.list()));
			model.addAttribute("invAboutToExpire", inventoryService.findByWarrantyExpirationDateBetween(currDate, dateAfter60Days));
			model.addAttribute("invExpired", inventoryService.findByWarrantyExpirationDateBefore(currDate));
		}
		else
		{	
			Office office = userService.findByEmail(auth.getName()).getOffice();
			model.addAttribute("invExpired", inventoryService.findByWarrantyExpirationDateBeforeAndOffice(currDate, office));
			model.addAttribute("invAboutToExpire", inventoryService.findByWarrantyExpirationDateBetweenAndOffice(currDate, dateAfter60Days, office));
			
		}
		return "index";
	}
    
	@RequestMapping("/help")
	public String Help()
	{
		return "help";
	}
	
	@RequestMapping("/about")
	public String About()
	{
		return "about";
	}	
	
	@RequestMapping("/options")
	public String Options()
	{
		return "options";
	}		
	
	public static String getOfficeJsonAsString(List<Office> offices)
	{
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(Office off : offices)
		{
			obj.put("name", off.getName());
			obj.put("invCount", off.getInventories().size());
			obj.put("color", getColorCode());			
			arr.put(obj);
			obj = new JSONObject();
		}
		return arr.toString();
	}

	public static String getAssetTypeJsonAsString(List<AssetType> assetTypes)
	{
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(AssetType at : assetTypes)
		{
			obj.put("name", at.getType());
			obj.put("invCount", at.getInventories().size());
			obj.put("color", getColorCode());			
			arr.put(obj);
			obj = new JSONObject();
		}
		return arr.toString();
	}	
	
	public static String getColorCode()
	{
	       // create random object - reuse this as often as possible
        Random random = new Random();

        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(256*256*256);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        // print it
        return colorCode;
	}
	   
}