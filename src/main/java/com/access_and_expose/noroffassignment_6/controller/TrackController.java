package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.GenreService;
import com.access_and_expose.noroffassignment_6.data.service.TrackService;
import com.access_and_expose.noroffassignment_6.model.Genre;
import com.access_and_expose.noroffassignment_6.model.Track;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Tag(name = "Track")
@RequestMapping(value = "api/v1/track", method = RequestMethod.GET)
public class TrackController {

    private final TrackService trackService;

    /**
     * Constructor injected with services.
     * @param trackService Track service.
     */
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * Get all Track objects from SQL Database Chinook using SQL Queries.
     * @return Collection of Track model objects.
     */
    @GetMapping(value = "/")
    public Collection<Track> getAllTracks() {
        return this.trackService.getAll();
    }

    /**
     * Get Track by id using SQL Queries from SQL Database Chinook.
     * @param trackId id to search for in Database.
     * @return Track model object.
     */
    @GetMapping(value = "/id={trackId}")
    public Track getTrackById(String trackId) {
        return this.trackService.getById(trackId);
    }

    /**
     * Get Track by name using SQL Queries from SQL Database Chinook.
     * @param trackName name to search for in Database.
     * @return Collection of Track model objects.
     */
    @GetMapping(value = "/name={trackName}")
    public Collection<Track> getTrackByName(String trackName) {
        return this.trackService.getByName(trackName);
    }
}
