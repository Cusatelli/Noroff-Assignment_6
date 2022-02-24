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

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artist/id={artistId}")
    public Artist getArtistById(@PathVariable String artistId) {
        return this.artistService.getById(artistId);
    }

    @GetMapping("/artist/name={artistName}")
    public Collection<Artist> getArtistByName(@PathVariable String artistName) {
        return this.artistService.getByName(artistName);
    }
}