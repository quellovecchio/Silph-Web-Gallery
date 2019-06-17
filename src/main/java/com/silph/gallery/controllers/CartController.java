package com.silph.gallery.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.silph.gallery.model.Cart;
import com.silph.gallery.model.Photo;
import com.silph.gallery.model.UsageRequest;
import com.silph.gallery.services.PhotoService;
import com.silph.gallery.services.UsageRequestService;

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
 * CartController
 */
@Controller
@SessionAttributes({"cart"})
public class CartController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private UsageRequestService usageRequestService;

    @ModelAttribute("cart")
	public Cart cart() {
			return new Cart();
    }
    
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCartPage(@ModelAttribute ("cart") Cart cart, Model model, RedirectAttributes rAttributes) {
		model.addAttribute("usageRequest", new UsageRequest());
		model.addAttribute("cart", cart);
		rAttributes.addFlashAttribute("cart", cart);
		return "pages/cart.html";
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
    
    @RequestMapping(value = "/confirmCart", method = RequestMethod.POST) 
	public String confirmCart(@Valid @ModelAttribute("usageRequest") UsageRequest usageRequest, @ModelAttribute ("cart") Cart cart, Model model) {
		UsageRequest uRequest = new UsageRequest();
		uRequest.setClientEmail(usageRequest.getClientEmail());
		uRequest.setClientName(usageRequest.getClientName());
		uRequest.setClientSurname(usageRequest.getClientSurname());
		uRequest.setChosenPhotos(cart.getPhotosAsList());
        usageRequestService.putUsageRequest(uRequest);
        model.addAttribute("cart", new Cart());
		return "pages/confirmation.html";
	} 
    
}