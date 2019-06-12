package com.silph.gallery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

	@Query("FROM Photo")
	public List<Photo> findLast30();
	
	public List<Photo> findByDescription(String name);
	
}
