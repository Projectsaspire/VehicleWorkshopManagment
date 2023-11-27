package com.rt.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rt.springboot.app.models.entity.Labour;
import com.rt.springboot.app.models.service.LabourServiceImpl;

@Controller
public class LabourController {

	@Autowired
	private  LabourServiceImpl labourServiceImpl;

	@RequestMapping("/labourdetails")
	public String viewHomePage(Model model) {
		List<Labour> listLabour = labourServiceImpl.listAll();
		model.addAttribute("listLabour",listLabour);
		return "labourdetails";
	}
	
	@RequestMapping("/neww")
	public String newLabourPage(Model model) {
		Labour labour=new Labour();
		model.addAttribute(labour);
		return "new_labour";
	}
	
	@RequestMapping(value = "/savee", method =RequestMethod.POST)
	public String saveLabour(@ModelAttribute("labour") Labour labour ) {
		labourServiceImpl.save(labour);
		return "redirect:/labourdetails";
	}
	
	
	@RequestMapping("/editt/{id}")
	public ModelAndView showEditLabourPage(@PathVariable (name="id") Long id) {
		ModelAndView mav=new ModelAndView("edit_labour");
		Labour labour=labourServiceImpl.get(id);
		mav.addObject("labour",labour);
		return mav;
	}
	
	@RequestMapping("/deletee/{id}")
	public String deleteLabourPage(@PathVariable (name="id") Long id) {
		labourServiceImpl.delete(id);
		return "redirect:/labourdetails";
	}
}
