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

    /**
     * Constructor injected with services.
     * @param genreService Genre service.
     */
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Get all Genre objects from SQL Database Chinook using SQL Queries.
     * @return Collection of Genre model objects.
     */
    @GetMapping(value = "/")
    public Collection<Genre> getAllGenre() {
        return this.genreService.getAll();
    }

    /**
     * Get Genre by id using SQL Queries from SQL Database Chinook.
     * @param genreId id to search for in Database.
     * @return Genre model object.
     */
    @GetMapping(value = "/id={genreId}")
    public Genre getGenreById(@PathVariable String genreId) {
        return this.genreService.getById(genreId);
    }

    /**
     * Get Genre by name using SQL Queries from SQL Database Chinook.
     * @param genreName name to search for in Database.
     * @return Collection of Genre model objects.
     */
    @GetMapping(value = "/name={genreName}")
    public Collection<Genre> getGenreByName(@PathVariable String genreName) {
        return this.genreService.getByName(genreName);
    }
}
