package com.silph.gallery.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.silph.gallery.model.Cart;
import com.silph.gallery.model.Photo;
import com.silph.gallery.services.PhotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * PhotoController
 */
@Controller
@SessionAttributes({"cart"})
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    
    @ModelAttribute("cart")
	public Cart cart() {
			return new Cart();
	}

    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	public String showPhotoPage(@ModelAttribute ("cart") Cart cart, @PathVariable ("id") Long id, Model model) {
		System.out.println("Photo in cart in photo page: " + cart.size());	
		Optional<Photo> p = photoService.getPhotoById(id);
		if(id!=null && p.isPresent()) {
			model.addAttribute("photo", p.get());
			return "pages/photo.html";			
		}
		else
			return "pages/error.html";
    }
    
    @RequestMapping(value = "/addToCart/{id}", method = RequestMethod.POST) 
	public String addToCartPhoto(@PathVariable ("id") Long id, @ModelAttribute ("cart") Cart cart, Model model, RedirectAttributes attributes, HttpServletRequest request) {
		System.out.println("Photo in cart before updating the cart: " + cart.size());
		Optional<Photo> p = photoService.getPhotoById(id);
		if (id!=null) {
			if (cart.contains(p.get())) {
				System.out.println("Photo in cart already present");
				return "redirect:" + request.getHeader("Referer");
			}
			cart.addPhoto(p.get());
			attributes.addFlashAttribute("cart", cart);
			System.out.println("Photo in cart after updating the cart: " + cart.size());
			return "redirect:" + request.getHeader("Referer");
		}
		return "redirect:pages/error.html";
	} 
}