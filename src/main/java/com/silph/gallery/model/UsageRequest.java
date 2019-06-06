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
