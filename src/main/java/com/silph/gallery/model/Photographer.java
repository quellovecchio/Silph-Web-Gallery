package com.silph.gallery.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Photographer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToMany(mappedBy = "photographer", fetch = FetchType.LAZY)
	private List<Album> albums;
	@Column
	private String name;
	@Column
	private String surname;

	public Photographer(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.albums = new ArrayList<Album>();
	}
	
	public Photographer() {
		this.albums = new ArrayList<Album>();
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void addAlbum(Album a) {
		this.albums.add(a);
	}
}
