package com.silph.gallery.model;

import java.util.List;
import java.util.UUID;

public class UsageRequest {

	private UUID id;
	private String clientName;
	private String clientSurname;
	private String clientEmail;
	private List<Photo> chosenPhotos;
	private String note;
	
}
