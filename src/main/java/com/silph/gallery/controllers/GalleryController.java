package com.silph.gallery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.silph.gallery.services.AlbumService;
import com.silph.gallery.services.PhotoService;
import com.silph.gallery.services.PhotographerService;

@Controller
public class GalleryController {
	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private PhotographerService photographerService;
	
    @GetMapping("/")
    public String gallery (Model model) {
    	model.addAttribute("last30Photos", photoService.last30Photos());
        return "pages/gallery.html";
    }
    
    @GetMapping("/login")
    public String login (Model model) {
    	return "login.html";
    }
}