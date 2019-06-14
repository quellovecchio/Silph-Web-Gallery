package com.silph.gallery.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

	private Map<Long, Photo> photos;

	public Cart() {
		photos = new HashMap<Long, Photo>();
	}
	
	public Map<Long, Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Map<Long, Photo> photos) {
		this.photos = photos;
	}
	
	public void addPhoto(Photo photo) {
		this.photos.put(photo.getId(), photo);
	}
	
	public void removePhoto(Photo photo) {
		this.photos.remove(photo.getId());
	}
	
	public boolean isNotEmpty() {
		return !photos.isEmpty();
	}

	public boolean contains(Photo photo) {
		return photos.containsKey(photo.getId());
	}
}
