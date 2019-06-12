package com.silph.gallery.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private LocalDate publicationDate;
	@Column
	private String imageUrl;
	@ManyToOne
	private Photographer photographer;
	@ManyToOne
	private Album album;
	
	public Photo () {
		description = "Fiera";
		publicationDate = LocalDate.now();
		imageUrl = "https://source.unsplash.com/pWkk7iiCoDM/400x300";
	}
	
	public Photo (String description, String url) {
		this.description = description;
		this.publicationDate = LocalDate.now();
		this.imageUrl = url;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String name) {
		this.description = name;
	}
	public LocalDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	public Photographer getPhotographer() {
		return photographer;
	}
	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public boolean equals(Object obj) {
		Photo that = (Photo)obj;
		return this.id == that.id;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}
}
