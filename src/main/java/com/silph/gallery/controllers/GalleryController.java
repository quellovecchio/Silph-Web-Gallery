package com.silph.gallery.controllers;

import java.util.Optional;

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
	
	@ModelAttribute
	public void addAttributes(Model model) {
		if (model.containsAttribute("cart"))
			model.addAttribute("cart", new Cart());
	}
	
    @GetMapping("/")
    public String gallery (Model model) {
    	model.addAttribute("last30Photos", photoService.last30Photos());
        return "pages/gallery.html";
    }
    
    @GetMapping("/login")
    public String login (Model model) {
    	model.addAttribute("employee", new Employee());
    	return "pages/login.html";
    }
    
    @RequestMapping(value = "/loginEmployee", method = RequestMethod.POST)
    public String loginEmployee(@Valid @ModelAttribute("employee") Employee employee, Model model, BindingResult br) {
    	this.employeeValidator.validate(employee, br);
    	if (!br.hasErrors()) {
    		model.addAttribute("currentEmployee", employee);
    		return "pages/employeeDashboard.html";
    	}
    	else
    		return "pages/login.html";
    }

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	public String showPhotoPage(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			Optional<Photo> o = this.photoService.getPhotoById(id);
			Photo photo = o.get();
			model.addAttribute("photo", photo);
			return "pages/photo.html";			
		}
		else
			return "pages/error.html";
	}
	
	@RequestMapping(value = "/photographer/{id}", method = RequestMethod.GET)
	public String showPhotographerPage(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			Optional<Photographer> o = this.photographerService.getPhotographerById(id);
			Photographer photographer = o.get();
			model.addAttribute("photographer", photographer);
			return "pages/photographer.html";			
		}
		else
			return "pages/error.html";
	}
    
}