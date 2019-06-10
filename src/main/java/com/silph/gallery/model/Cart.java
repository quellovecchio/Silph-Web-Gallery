package com.silph.gallery.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Photo> photos;

	public Cart() {
		photos = new ArrayList<Photo>();
	}
	
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}
	
	public void removePhoto(Photo photo) {
		this.photos.remove(photo);
	}
	
}
