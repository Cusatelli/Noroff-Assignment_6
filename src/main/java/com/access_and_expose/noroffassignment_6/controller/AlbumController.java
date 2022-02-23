package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.repository.album.IAlbumRepository;
import com.access_and_expose.noroffassignment_6.model.Album;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(name = "Album")
@RequestMapping(value="api/v1")
public class AlbumController {

    private final IAlbumRepository albumRepository;

    public AlbumController(IAlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping(value = "/album/id={albumId}")
    public Album getAlbumById(@PathVariable String albumId) {
        return this.albumRepository.getById(albumId);
    }

    @GetMapping(value="/album/albumName={albumName}")
    public Collection<Album> getAlbumByName(@PathVariable String albumName) {
        return this.albumRepository.getByName(albumName);
    }
}