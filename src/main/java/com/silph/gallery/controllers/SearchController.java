package com.silph.gallery.controllers;

import java.util.ArrayList;
import java.util.List;

import com.silph.gallery.model.Album;
import com.silph.gallery.model.Photo;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.model.SearchWrapper;
import com.silph.gallery.services.AlbumService;
import com.silph.gallery.services.PhotoService;
import com.silph.gallery.services.PhotographerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * SearchController
 */
@Controller
public class SearchController {

    @Autowired
    private PhotographerService photographerService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "/searchAll", method = RequestMethod.GET)
    public String searchAll (Model model, @RequestParam(name = "search") String search) {
    model.addAttribute("photographer", new Photographer());
    return "pages/searchresults.html";
    }

    @RequestMapping(value = "/searchPhotos", method = RequestMethod.GET)
    public String searchPhoto (Model model, @RequestParam(name = "search") String search) {
    return "pages/searchresults.html";
    }

    @RequestMapping(value = "/searchPhotographers", method = RequestMethod.GET)
    public String searchPhotographer (Model model, @ModelAttribute(name = "searchWrapper") SearchWrapper search) {
    model.addAttribute("photographers", photographerService.searchByString(search.getSearch()));
    model.addAttribute("albums", new ArrayList<Album>());
    model.addAttribute("photos", new ArrayList<Photo>());
    return "pages/searchresults.html";
    }

    @RequestMapping(value = "/searchAlbums", method = RequestMethod.GET)
    public String searchAlbum (Model model, @RequestParam(name = "search") String search) {
    model.addAttribute("photographer", new Photographer());
    return "pages/searchresults.html";
    }
    
}