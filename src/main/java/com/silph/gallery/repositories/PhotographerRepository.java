package com.silph.gallery.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Photographer;

@Repository
public interface PhotographerRepository extends CrudRepository<Photographer, UUID> {

	public List<Photographer> findByName(String name);
}
