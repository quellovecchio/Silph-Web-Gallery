package com.silph.gallery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.gallery.model.Photo;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.repositories.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;
	
	@Transactional
	public Photo putPhoto(Photo p) {
		return photoRepository.save(p);
	}
	
	@Transactional
	public List<Photo> getAllPhotos() {
		return (List<Photo>)photoRepository.findAll();
	}

	// To be rewritten
	@Transactional
	public List<Photo> last30Photos() {
		List<Photo> photos = new ArrayList<Photo>();
		Photo p = new Photo();
		Photographer ph = new Photographer();
		ph.setName("Giuseppe");
		ph.setSurname("Rossi");
		ph.setId(457);
		p.setPhotographer(ph);
		p.setImageUrl("https://source.unsplash.com/pWkk7iiCoDM/400x300");
		for (int i=0; i<30; i++)
			photos.add(p);
		System.out.println("In photos there are stored " + photos.size() + " elements");
		return photos;
				//(List<Photo>)photoRepository.findLast30();
	}

	@Transactional
	public Optional<Photo> getPhotoById(Long id) {
		return photoRepository.findById(id);
	}
}
