package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.genre.IGenreRepository;
import com.access_and_expose.noroffassignment_6.model.genre.Genre;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Genre")
@RequestMapping(value = "api/v1", method = RequestMethod.GET)
public class GenreController {

    private final IGenreRepository genreRepository;

    public GenreController(IGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping(value = "/genre/id={genreId}")
    public Genre getGenreById(@PathVariable String genreId) {
        return this.genreRepository.getById(genreId);
    }

    @GetMapping(value = "/genre/name={genreName}")
    public Genre getGenreByName(@PathVariable String genreName) {
        return this.genreRepository.getByName(genreName);
    }
}
