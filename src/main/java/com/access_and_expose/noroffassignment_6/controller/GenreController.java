package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.repository.genre.IGenreRepository;
import com.access_and_expose.noroffassignment_6.model.Genre;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Tag(name = "Genre")
@RequestMapping(value = "api/v1/genre", method = RequestMethod.GET)
public class GenreController {

    private final IGenreRepository genreRepository;

    public GenreController(IGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping(value = "/")
    public Collection<Genre> getAllGenre() {
        return this.genreRepository.getAll();
    }

    @GetMapping(value = "/id={genreId}")
    public Genre getGenreById(@PathVariable String genreId) {
        return this.genreRepository.getById(genreId);
    }

    @GetMapping(value = "/name={genreName}")
    public Collection<Genre> getGenreByName(@PathVariable String genreName) {
        return this.genreRepository.getByName(genreName);
    }
}
