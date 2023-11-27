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

import com.rt.springboot.app.models.entity.Product;
import com.rt.springboot.app.models.service.ProductServiceImpl;

@Controller
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@RequestMapping("/productdetails")
	public String viewHomePage(Model model) {
		List<Product> listProduct = productServiceImpl.listAll();
		model.addAttribute("listProduct", listProduct);
		return "productdetails";
	}

	@RequestMapping("/new")
	public String newProductPage(Model model) {
		Product product = new Product();
		model.addAttribute(product);
		return "new_product";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		productServiceImpl.save(product);
		return "redirect:/productdetails";
	}

	@RequestMapping("/edittt/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = productServiceImpl.get(id);
		mav.addObject("product", product);
		return mav;
	}

	@RequestMapping("/deleteee/{id}")
	public String deleteProductPage(@PathVariable(name = "id") Long id) {
		productServiceImpl.delete(id);
		return "redirect:/productdetails";
	}

}
