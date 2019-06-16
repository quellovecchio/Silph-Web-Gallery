package com.silph.gallery.controllers;

import java.util.Optional;

import com.silph.gallery.model.Album;
import com.silph.gallery.model.Cart;
import com.silph.gallery.services.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * AlbumController
 */
@Controller
@SessionAttributes({"cart"})
public class AlbumController {

	@Autowired
    private AlbumService albumService;
    
    @ModelAttribute("cart")
	public Cart cart() {
			return new Cart();
	}

    @RequestMapping(value = "/album/{id}", method = RequestMethod.GET) 
	public String showAlbumPage(@ModelAttribute ("cart") Cart cart, @PathVariable ("id") Long id, Model model) {
		Optional<Album> a = albumService.findById(id);
		if (id!=null && a.isPresent()) {
			model.addAttribute("album", a.get());
			return "pages/album.html";
		}
		return "pages/error.html";
    } 
    
}