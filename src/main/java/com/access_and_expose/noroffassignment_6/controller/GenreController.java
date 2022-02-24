package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.GenreService;
import com.access_and_expose.noroffassignment_6.model.Genre;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Tag(name = "Genre")
@RequestMapping(value = "api/v1/genre", method = RequestMethod.GET)
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/")
    public Collection<Genre> getAllGenre() {
        return this.genreService.getAll();
    }

    @GetMapping(value = "/id={genreId}")
    public Genre getGenreById(@PathVariable String genreId) {
        return this.genreService.getById(genreId);
    }

    @GetMapping(value = "/name={genreName}")
    public Collection<Genre> getGenreByName(@PathVariable String genreName) {
        return this.genreService.getByName(genreName);
    }
}
