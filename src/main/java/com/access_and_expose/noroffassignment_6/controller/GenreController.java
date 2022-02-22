package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.genre.IGenreRepository;
import com.access_and_expose.noroffassignment_6.model.genre.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/", method = RequestMethod.GET)
public class GenreController {
    private final IGenreRepository genreRepository;

    @Autowired
    public GenreController(IGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping(value = "genre/id={genreId}")
    public Genre getGenreById(String genreId) {
        return this.genreRepository.getGenreById(genreId);
    }

    @GetMapping(value = "genre/name={genreName}")
    public Genre getGenreByName(String genreName) {
        return this.genreRepository.getGenreByName(genreName);
    }
}
