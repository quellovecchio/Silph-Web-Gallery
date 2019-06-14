package com.silph.gallery.services;

import java.util.Optional;

import com.silph.gallery.model.Album;
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

}
