package com.silph.gallery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

	public List<Album> findByName(String name);

	@Query(value = "SELECT * FROM album WHERE LOWER(name) LIKE CONCAT('%',:search,'%')", nativeQuery = true)
	public List<Album> findByString(String search);
	
}
