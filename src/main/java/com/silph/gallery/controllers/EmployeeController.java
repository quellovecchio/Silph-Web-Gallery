package com.silph.gallery.controllers;

import com.silph.gallery.model.Album;
import com.silph.gallery.model.Cart;
import com.silph.gallery.model.Photo;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.services.UsageRequestService;
import com.silph.gallery.validators.EmployeeValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * EmployeeController
 */
@Controller
@SessionAttributes({"cart"})
public class EmployeeController {

  @Autowired
  private UsageRequestService usageRequestService;
    
  @ModelAttribute("cart")
	public Cart cart() {
		return new Cart();
	}

  @RequestMapping(value = "/employeeDashboard", method = RequestMethod.GET)
  public String employeeDashboard (Model model) {
    return "pages/employeeDashboard.html";
  }

  @RequestMapping(value = "/employeeDashboard/checkUsageRequests", method = RequestMethod.GET)
  public String showUsageRequestList (Model model) {
    model.addAttribute("usageRequestList", usageRequestService.getAllUsageRequests());
    return "pages/usagerequests.html";
  }

  @RequestMapping(value = "/employeeDashboard/addPhotographer", method = RequestMethod.GET)
  public String addPhotographer (Model model) {
    model.addAttribute("photographer", new Photographer());
    return "pages/addphotographer.html";
  }

  @RequestMapping(value = "/employeeDashboard/addAlbum", method = RequestMethod.GET)
  public String addAlbum (Model model) {
    model.addAttribute("album", new Album());
    return "pages/addalbum.html";
  }

  @RequestMapping(value = "/employeeDashboard/addPhoto", method = RequestMethod.GET)
  public String addPhoto (Model model) {
    model.addAttribute("photo", new Photo());
    return "pages/addphoto.html";
  }
    
}