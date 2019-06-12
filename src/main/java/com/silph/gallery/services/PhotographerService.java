package com.silph.gallery.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.gallery.model.Photographer;
import com.silph.gallery.repositories.PhotographerRepository;

@Service
public class PhotographerService {


	@Autowired
	private PhotographerRepository photographerRepository;
	
	
	public Optional<Photographer> getPhotographerById(Long id) {
		return photographerRepository.findById(id);
	}

}
