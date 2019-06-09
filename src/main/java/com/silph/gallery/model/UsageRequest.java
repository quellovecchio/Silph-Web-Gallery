package com.silph.gallery.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UsageRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Column
	private String clientName;
	@Column
	private String clientSurname;
	@Column
	private String clientEmail;
	@OneToMany
	private List<Photo> chosenPhotos;
	@Column
	private String note;
	
}
