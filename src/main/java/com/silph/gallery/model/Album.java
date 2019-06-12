package com.silph.gallery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(mappedBy = "album")
	private List<Photo> photos;
	@ManyToOne
	private Photographer photographer;
	@Column
	private String name;

	public Album(String name) {
		photos = new ArrayList<Photo>();
		this.name = name;
	}
	
	public Album() {
		photos = new ArrayList<Photo>();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public Photographer getPhotographer() {
		return photographer;
	}
	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void addPhoto(Photo p) {
		photos.add(p);
	}
}
