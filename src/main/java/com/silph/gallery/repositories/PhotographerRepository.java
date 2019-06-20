package com.silph.gallery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Photographer;

@Repository
public interface PhotographerRepository extends CrudRepository<Photographer, Long> {

	@Query(value = "SELECT * FROM photographer WHERE LOWER(name) LIKE CONCAT('%',:search,'%') OR LOWER(surname) LIKE CONCAT('%',:search,'%')", nativeQuery = true)
	public List<Photographer> findByString(@Param("search") String search);

	public List<Photographer> findByName(String name);

}
