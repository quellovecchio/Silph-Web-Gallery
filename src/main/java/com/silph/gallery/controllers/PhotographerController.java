package com.silph.gallery.controllers;

import java.util.Optional;

import com.silph.gallery.model.Cart;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.services.PhotographerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * PhotographerController
 */
@Controller
@SessionAttributes({"cart"})
public class PhotographerController {

    @Autowired
    private PhotographerService photographerService;
    
    @ModelAttribute("cart")
	public Cart cart() {
			return new Cart();
	}

    @RequestMapping(value = "/photographer/{id}", method = RequestMethod.GET)
	public String showPhotographerPage(@ModelAttribute ("cart") Cart cart, @PathVariable ("id") Long id, Model model) {
		Optional<Photographer> p = photographerService.getPhotographerById(id);
		if(id!=null && p.isPresent()) {
			model.addAttribute("photographer", p.get());
			return "pages/photographer.html";			
		}
		else
			return "pages/error.html";
	}
}