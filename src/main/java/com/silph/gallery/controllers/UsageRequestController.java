package com.silph.gallery.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.silph.gallery.model.Cart;
import com.silph.gallery.model.UsageRequest;
import com.silph.gallery.services.UsageRequestService;
import com.silph.gallery.validators.UsageRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UsageRequestController
 */
@Controller
public class UsageRequestController {

    @Autowired
	private UsageRequestService usageRequestService;
	@Autowired
    private UsageRequestValidator usageRequestValidator;

    @RequestMapping(value = "/confirmCart", method = RequestMethod.POST) 
	public String confirmCart(@Valid @ModelAttribute("usageRequest") UsageRequest usageRequest,
							  Model model,
							  HttpSession session,
							  BindingResult bindingResult) {
		System.out.println("Starting procedure of saving a usage request");
		Cart cart = (Cart)session.getAttribute("cart");
		System.out.println("---------------------------"+cart.size()+"--------------------------");
		this.usageRequestValidator.validate(usageRequest, bindingResult);
		if (bindingResult.hasErrors() || cart.isEmpty()) {
			System.out.println("Validation didn't pass");
            model.addAttribute("cart", cart);
            return "pages/cart.html";
		}

		else {
			System.out.println("Validation passed");
			UsageRequest uRequest = new UsageRequest();
			uRequest.setClientEmail(usageRequest.getClientEmail());
			uRequest.setClientName(usageRequest.getClientName());
			uRequest.setClientSurname(usageRequest.getClientSurname());
			uRequest.setChosenPhotos(cart.getPhotosAsList());
			usageRequestService.putUsageRequest(uRequest);
			model.addAttribute("cart", new Cart());
			System.out.println("Done");
			return "pages/confirmation.html";	
		}
	} 
}