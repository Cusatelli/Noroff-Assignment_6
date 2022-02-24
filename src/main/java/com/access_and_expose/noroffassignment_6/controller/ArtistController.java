package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.ArtistService;
import com.access_and_expose.noroffassignment_6.model.Artist;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(name = "Artist")
@RequestMapping("api/v1")
public class ArtistController {

    private final ArtistService artistService;

    /**
     * Constructor injected with services.
     * @param artistService Artist service.
     */
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    /**
     * Get Artist by id using SQL Queries from SQL Database Chinook.
     * @param artistId id to search for in Database.
     * @return Artist model object.
     */
    @GetMapping("/artist/id={artistId}")
    public Artist getArtistById(@PathVariable String artistId) {
        return this.artistService.getById(artistId);
    }

    /**
     * Get Artist by name using SQL Queries from SQL Database Chinook.
     * @param artistName name to search for in Database.
     * @return Collection of Artist model objects.
     */
    @GetMapping("/artist/name={artistName}")
    public Collection<Artist> getArtistByName(@PathVariable String artistName) {
        return this.artistService.getByName(artistName);
    }
}