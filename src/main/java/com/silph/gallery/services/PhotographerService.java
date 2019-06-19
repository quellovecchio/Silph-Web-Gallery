package com.silph.gallery.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silph.gallery.model.Photographer;
import com.silph.gallery.repositories.PhotographerRepository;

@Service
public class PhotographerService {

	@Autowired
	private PhotographerRepository photographerRepository;
	
	@Transactional
	public Optional<Photographer> getPhotographerById(Long id) {
		return photographerRepository.findById(id);
	}

	@Transactional
	public void addPhotographer(Photographer photographer) {
		photographerRepository.save(photographer);
	}

	@Transactional
	public List<Photographer> getAllPhotographers() {
		List<Photographer> list = new ArrayList<>();
		Iterator<Photographer> i = photographerRepository.findAll().iterator();
		i.forEachRemaining(list::add);
		return list;
	}

	@Transactional
	public List<Photographer> searchByString(String search) {
		List<Photographer> r = photographerRepository.findByString(search);
		return r;
	}

}
