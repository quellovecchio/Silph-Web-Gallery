package com.silph.gallery.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silph.gallery.model.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, UUID> {

	public List<Album> findByName(String name);
	
}
