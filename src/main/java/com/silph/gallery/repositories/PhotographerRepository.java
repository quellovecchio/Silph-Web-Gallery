package com.silph.gallery.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Photographer;

@Repository
public interface PhotographerRepository extends CrudRepository<Photographer, Long> {

	public List<Photographer> findByName(String name);
}
