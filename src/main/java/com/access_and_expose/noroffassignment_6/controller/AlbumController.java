package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.AlbumService;
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

    private final AlbumService albumService;

    /**
     * Constructor injected with services.
     * @param albumService Album service.
     */
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Get Album by id using SQL Queries from SQL Database Chinook.
     * @param albumId id to search for in Database.
     * @return Album model object.
     */
    @GetMapping(value = "/album/id={albumId}")
    public Album getAlbumById(@PathVariable String albumId) {
        return this.albumService.getById(albumId);
    }

    /**
     * Get Album by name using SQL Queries from SQL Database Chinook.
     * @param albumName name to search for in Database.
     * @return Collection of Album model objects.
     */
    @GetMapping(value="/album/albumName={albumName}")
    public Collection<Album> getAlbumByName(@PathVariable String albumName) {
        return this.albumService.getByName(albumName);
    }
}