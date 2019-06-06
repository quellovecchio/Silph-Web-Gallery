package com.silph.gallery.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silph.gallery.model.Photo;
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

	@Transactional
	public List<Photo> last30Photos() {
		return new ArrayList<Photo>();
				//(List<Photo>)photoRepository.findLast30();
	}
}
