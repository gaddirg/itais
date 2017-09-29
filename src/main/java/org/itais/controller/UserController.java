package org.itais.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.itais.domain.User;
import org.itais.service.OfficeService;
import org.itais.service.RoleService;
import org.itais.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class UserController
{

	private OfficeService officeService;
	private UserService userService;
	private RoleService roleService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService);
	}

	@Autowired
	public UserController(OfficeService officeService, UserService userService,
			RoleService roleService)
	{
		super();
		this.officeService = officeService;
		this.userService = userService;
		this.roleService = roleService;
	}


	@RequestMapping("/user/create")
	public String UserCreate(HttpServletRequest request, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("offices", officeService.listForSA());
		model.addAttribute("roles", roleService.list());
		model.addAttribute("users", new User());
		return "user/create";
	}

	@RequestMapping("/user/create/{id}")
	public String UserCreateWithPreselectedOffice(Model model,@PathVariable Long id)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("offices", Arrays.asList(officeService.findById(id)));
		model.addAttribute("roles", Arrays.asList(roleService.findById(id)));
		model.addAttribute("users", new User());
		return "user/create";
	}


	/**
	 * @param 
	 * @param 
	 * @return 
	 */    
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String UserSave(@ModelAttribute User user)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User saveData = userService.save(user);

		return "redirect:read/" + saveData.getId();
	}


	/**
	 * @param 
	 * @param 
	 * @return 
	 */
	@RequestMapping("/user/edit/{id}")
	public String UserEdit(@PathVariable Long id, Model model, HttpServletRequest request)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		model.addAttribute("roles", roleService.list());
		model.addAttribute("users", userService.findById(id));
		model.addAttribute("offices", officeService.listForSA());	
		return "user/create";
	}


	/**
	 * @param 
	 * @param 
	 * @return 
	 */
	@RequestMapping("/user/read/{id}")
	public String UserRead(@PathVariable Long id, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("users", userService.findById(id));
		return "user/read";
	}


	/**
	 * @param 
	 * @param 
	 * @return 
	 */
	@RequestMapping("/user/main")
	public String UserMain(HttpServletRequest request, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		model.addAttribute("users", userService.list());
		return "user/main";
	}


	/**
	 * @param 
	 * @param 
	 * @return 
	 */
	@RequestMapping("/user/delete/{id}")
	public String UserDelete(@PathVariable Long id, Model model)
	{
		model.addAttribute("users", userService.findById(id));
		return "user/delete";
	}


	/**
	 * @param 
	 * @param 
	 * @return 
	 */    
	@RequestMapping("/user/deleteconfirmed/{id}")
	public RedirectView userDeleteConfirmed(@PathVariable Long id, Model model)
	{
		userService.delete(id);
		return new RedirectView("/user/main");
	}

}