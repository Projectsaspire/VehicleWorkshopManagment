package com.rt.springboot.app.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rt.springboot.app.models.entity.Client;
import com.rt.springboot.app.models.service.ClientServiceImpl;

@Controller
public class LoginController {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,  
			@RequestParam(value = "logout", required = false) String logout, 
			Model model, Principal principal, RedirectAttributes flash, Locale locale) {
		
		if(principal != null) {
			flash.addAttribute("info", messageSource.getMessage("text.login.already", null, locale));
			return "redirect:/";
		}
		
		if(error != null) {
			model.addAttribute("error", messageSource.getMessage("text.login.error", null, locale));
		}
		
		if(logout != null) {
			model.addAttribute("success", messageSource.getMessage("text.login.logout", null, locale));
		}
		
		return "login";
	}
	
	@GetMapping(path = { " ","/search" })
	public String home(Client client, Model model, String keyword) {
		if (keyword != null) {
			List<Client> clients = clientServiceImpl.getByKeyword(keyword);
			model.addAttribute("clients", clients);
		} else {
			List<Client> clients = clientServiceImpl.getAllDetails();
			model.addAttribute("clients", clients);
		}
		return "list";
	}
	
}
