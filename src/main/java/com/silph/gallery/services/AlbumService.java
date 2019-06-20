package com.silph.gallery.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.silph.gallery.model.Album;
import com.silph.gallery.model.Photographer;
import com.silph.gallery.repositories.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumService {

    @Autowired 
    private AlbumRepository albumRepository;

    @Transactional
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Transactional
	public void addAlbum(Album album) {
        albumRepository.save(album);
	}

    @Transactional
	public List<Album> getAllAlbums() {
        List<Album> list = new ArrayList<>();
		Iterator<Album> i = albumRepository.findAll().iterator();
		i.forEachRemaining(list::add);
		return list;
    }
    
    @Transactional
    public Album getAlbumById(long id) {
        return albumRepository.findById(id).get();
	}

	public Object searchByString(String search) {
        List<Album> r = albumRepository.findByString(search);
        return r;
	}

}
