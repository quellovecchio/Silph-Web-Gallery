package com.silph.gallery.controllers;

import com.silph.gallery.model.Album;
import com.silph.gallery.model.Cart;
import com.silph.gallery.model.Photo;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.model.UsageRequest;
import com.silph.gallery.services.AlbumService;
import com.silph.gallery.services.PhotoService;
import com.silph.gallery.services.PhotographerService;
import com.silph.gallery.services.UsageRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Autowired
  private PhotographerService photographerService;

  @Autowired
  private PhotoService photoService;

  @Autowired
  private AlbumService albumService;
    
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

  @RequestMapping(value = "/employeeDashboard/usageRequest/{id}", method = RequestMethod.GET)
  public String showUsageRequest (Model model, @PathVariable ("id") Long id) {
    UsageRequest ur = usageRequestService.getUsageRequestById(id);
    model.addAttribute("usageRequest", ur);
    System.out.println("---------------------------Number of pics in ur-----------------------" + ur.getChosenPhotos().size());
    return "pages/usagerequest.html";
  }

  @RequestMapping(value = "/employeeDashboard/deleteUsageRequest/{id}", method = RequestMethod.GET)
  public String deleteUsageRequest (Model model, @PathVariable ("id") Long id) {
    usageRequestService.deleteUsageRequestById(id);
    return "redirect:/employeeDashboard";
  }

  @RequestMapping(value = "/employeeDashboard/addPhotographer", method = RequestMethod.GET)
  public String addPhotographer (Model model) {
    model.addAttribute("photographer", new Photographer());
    return "pages/addphotographer.html";
  }

  @RequestMapping(value = "/employeeDashboard/addPhotographerAction", method = RequestMethod.POST)
  public String addPhotographerAction (Model model, @ModelAttribute("photograper") Photographer photographer) {
    photographerService.addPhotographer(photographer);
    return "redirect:/employeeDashboard";
  }

  @RequestMapping(value = "/employeeDashboard/addAlbum", method = RequestMethod.GET)
  public String addAlbum (Model model) {
    model.addAttribute("album", new Album());
    model.addAttribute("photographers", photographerService.getAllPhotographers());
    return "pages/addalbum.html";
  }

  @RequestMapping(value = "/employeeDashboard/addAlbumAction", method = RequestMethod.POST)
  public String addAlbumAction (Model model, @ModelAttribute("album") Album album) {
    albumService.addAlbum(album);
    return "redirect:/employeeDashboard";
  }

  @RequestMapping(value = "/employeeDashboard/addPhoto", method = RequestMethod.GET)
  public String choosePhotographer (Model model) {
    model.addAttribute("photo", new Photo());
    model.addAttribute("albums", albumService.getAllAlbums());
    model.addAttribute("photographers", photographerService.getAllPhotographers());
    model.addAttribute("stringToVisualize", "");
    return "pages/addphoto.html";
  }

  @RequestMapping(value = "/employeeDashboard/addPhotoAction", method = RequestMethod.POST)
  public String addPhotoAction (Model model, @ModelAttribute("photo") Photo photo) {
    if (photo.getPhotographer().equals(albumService.getAlbumById(photo.getAlbum().getId()).getPhotographer())) {
      photoService.addPhoto(photo);
      return "redirect:/employeeDashboard";
    }
    else {
      model.addAttribute("stringToVisualize", "The album is not by the photographer who owns it");
      model.addAttribute("photo", new Photo());
      model.addAttribute("albums", albumService.getAllAlbums());
      model.addAttribute("photographers", photographerService.getAllPhotographers());
      return "pages/addphoto.html";
    }
  }
    
}