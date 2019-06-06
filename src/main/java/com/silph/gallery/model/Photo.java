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
	private byte[] image;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private LocalDate publicationDate;
	@Column
	private String imageUrl;
	@ManyToOne
	private Photographer photographer;
	@ManyToOne
	private Album album;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
