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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchAll (Model model, @ModelAttribute(name = "search") SearchWrapper search) {
        System.out.println("--------------------------------------------"+search.getSearch()+"----------------------------------------------------");
        System.out.println("--------------------------------------------"+search.getType()+"----------------------------------------------------");
        // search all
        if(search.getType() == 0) {
            model.addAttribute("photos", photoService.searchByString(search.getSearch()));
            model.addAttribute("albums", albumService.searchByString(search.getSearch()));
            model.addAttribute("photographers", photographerService.searchByString(search.getSearch()));
        }
        // search photos
        if(search.getType() == 1) {
            model.addAttribute("photos", photoService.searchByString(search.getSearch()));
            model.addAttribute("albums", new ArrayList<Album>());
            model.addAttribute("photographers", new ArrayList<Photographer>());
        }
        // search albums
        if(search.getType() == 2) {
            model.addAttribute("photos", new ArrayList<Photo>());
            model.addAttribute("albums", albumService.searchByString(search.getSearch()));
            model.addAttribute("photographers", new ArrayList<Photographer>());
        }
        // search photographers
        if(search.getType() == 3) {
            model.addAttribute("photos", new ArrayList<Photo>());
            model.addAttribute("albums", new ArrayList<Album>());
            model.addAttribute("photographers", photographerService.searchByString(search.getSearch()));
        }
        return "pages/searchresults.html";
    }
    
}