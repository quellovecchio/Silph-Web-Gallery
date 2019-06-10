package com.silph.gallery.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

	/*@Query("SELECT TOP 30 * FROM photo ORDER BY publicationDate DESC")
	public List<Photo> findLast30();*/
	
	public List<Photo> findByName(String name);
	
}
