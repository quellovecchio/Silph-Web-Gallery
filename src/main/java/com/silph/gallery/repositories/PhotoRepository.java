package com.silph.gallery.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, UUID> {

	/*@Query("SELECT TOP 30 * FROM photo ORDER BY publicationDate DESC")
	public List<Photo> findLast30();*/
	
	public List<Photo> findByName(String name);
	
	
	
}
