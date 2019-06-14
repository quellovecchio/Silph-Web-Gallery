package com.silph.gallery.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.silph.gallery.DbPopulator;
import com.silph.gallery.model.Album;
import com.silph.gallery.model.Cart;
import com.silph.gallery.model.Employee;
import com.silph.gallery.model.Photo;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.services.AlbumService;
import com.silph.gallery.services.PhotoService;
import com.silph.gallery.services.PhotographerService;
import com.silph.gallery.validators.EmployeeValidator;

@Controller
public class GalleryController {
	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private PhotographerService photographerService;
	@Autowired
	private EmployeeValidator employeeValidator;
	
    @RequestMapping(value={"/", "/gallery"})
    public String gallery (@ModelAttribute ("cart") Cart cart, Model model) {
		model = handleCart(cart, model);
		model.addAttribute("last30Photos", photoService.last30Photos());
        return "pages/gallery.html";
    }

	@GetMapping("/login")
    public String login (@ModelAttribute ("cart") Cart cart, Model model) {
		model = handleCart(cart, model);
    	model.addAttribute("employee", new Employee());
    	return "pages/login.html";
    }
    
    @RequestMapping(value = "/loginEmployee", method = RequestMethod.POST)
    public String loginEmployee(@ModelAttribute ("cart") Cart cart, @Valid @ModelAttribute("employee") Employee employee, Model model, BindingResult br) {
		model = handleCart(cart, model);
    	this.employeeValidator.validate(employee, br);
    	if (!br.hasErrors()) {
    		model.addAttribute("currentEmployee", employee);
    		return "pages/employeeDashboard.html";
    	}
    	else
    		return "pages/login.html";
    }

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	public String showPhotoPage(@ModelAttribute ("cart") Cart cart, @PathVariable ("id") Long id, Model model) {
		model = handleCart(cart, model);
		Optional<Photo> p = photoService.getPhotoById(id);
		if(id!=null && p.isPresent()) {
			model.addAttribute("photo", p.get());
			return "pages/photo.html";			
		}
		else
			return "pages/error.html";
	}
	
	@RequestMapping(value = "/photographer/{id}", method = RequestMethod.GET)
	public String showPhotographerPage(@ModelAttribute ("cart") Cart cart, @PathVariable ("id") Long id, Model model) {
		model = handleCart(cart, model);
		Optional<Photographer> p = photographerService.getPhotographerById(id);
		if(id!=null && p.isPresent()) {
			model.addAttribute("photographer", p.get());
			return "pages/photographer.html";			
		}
		else
			return "pages/error.html";
	}

	@RequestMapping(value = "/album/{id}", method = RequestMethod.GET) 
	public String showAlbumPage(@ModelAttribute ("cart") Cart cart, @PathVariable ("id") Long id, Model model) {
		model = handleCart(cart, model);
		Optional<Album> a = albumService.findById(id);
		if (id!=null && a.isPresent()) {
			model.addAttribute("album", a.get());
			return "pages/album.html";
		}
		return "pages/error.html";
	} 

	@RequestMapping(value = "/addToCart/{id}", method = RequestMethod.POST) 
	public String addToCartPhoto(@PathVariable ("id") Long id, @ModelAttribute ("cart") Cart cart, Model model, HttpServletRequest request) {
		model = handleCart(cart, model);
		Optional<Photo> p = photoService.getPhotoById(id);
		if (id!=null) {
			if (!cart.contains(p.get())) {
				return "redirect:" + request.getHeader("Referer");
			}
			cart.addPhoto(p.get());
			model.addAttribute("cart", cart);
			return "redirect:" + request.getHeader("Referer");
		}
		return "pages/error.html";
	} 
	    
    private Model handleCart(Cart cart, Model model) {
		if (cart == null) {
			model.addAttribute("cart", new Cart());
			return model;
		}
		return model;
	}
}