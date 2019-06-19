package com.silph.gallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.silph.gallery.model.Cart;
import com.silph.gallery.model.SearchWrapper;
import com.silph.gallery.services.PhotoService;

@Controller
@RequestMapping(value={"/", "/gallery"})
@SessionAttributes({"cart"})
public class GalleryController {
	
	@Autowired
	private PhotoService photoService;

	@ModelAttribute("cart")
	public Cart cart() {
			return new Cart();
	}
	
 	@RequestMapping(method = RequestMethod.GET)
  	public String gallery (@ModelAttribute("cart") Cart cart, Model model) {
		System.out.println("Photo in cart in gallery page: " + cart.size());
		model.addAttribute("last30Photos", photoService.last30Photos());
		model.addAttribute("searchWrapper", new SearchWrapper());
    	return "pages/gallery.html";
  	}
}