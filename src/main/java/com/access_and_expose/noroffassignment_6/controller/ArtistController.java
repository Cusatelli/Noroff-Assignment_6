package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.artist.IArtistRepository;
import com.access_and_expose.noroffassignment_6.model.artist.Artist;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Artist")
@RequestMapping("api/v1")
public class ArtistController {

    private final IArtistRepository artistRepository;

    public ArtistController(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/artist/id={artistId}")
    public Artist getArtistById(@PathVariable String artistId) {
        return this.artistRepository.getById(artistId);
    }

    @GetMapping("/artist/name={artistName}")
    public Artist getArtistByName(@PathVariable String artistName) {
        return this.artistRepository.getByName(artistName);
    }
}