package com.silph.gallery.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class UsageRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String clientName;
	@Column
	private String clientSurname;
	@Column
	private String clientEmail;
	@ManyToMany
	private List<Photo> chosenPhotos;

	public UsageRequest() {
		this.chosenPhotos = new ArrayList<Photo>();
	}

	public UsageRequest(String name, String surname, String email) {
		this.clientEmail = email;
		this.clientName = name;
		this.clientSurname = surname;
		this.chosenPhotos = new ArrayList<Photo>();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientSurname() {
		return clientSurname;
	}
	public void setClientSurname(String clientSurname) {
		this.clientSurname = clientSurname;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public List<Photo> getChosenPhotos() {
		return chosenPhotos;
	}
	public void setChosenPhotos(List<Photo> chosenPhotos) {
		this.chosenPhotos = chosenPhotos;
	}
	public void setChosenPhotos(Collection<Photo> chosenPhotos) {
		this.chosenPhotos.clear();
		this.chosenPhotos.addAll(chosenPhotos);
	}
}
