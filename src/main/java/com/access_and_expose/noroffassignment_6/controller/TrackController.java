package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.TrackService;
import com.access_and_expose.noroffassignment_6.model.Track;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Tag(name = "Track")
@RequestMapping(value = "api/v1/track", method = RequestMethod.GET)
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping(value = "/")
    public Collection<Track> getAllTracks() {
        return this.trackService.getAll();
    }

    @GetMapping(value = "/id={trackId}")
    public Track getTrackById(String trackId) {
        return this.trackService.getById(trackId);
    }

    @GetMapping(value = "/name={trackName}")
    public Collection<Track> getTrackByName(String trackName) {
        return this.trackService.getByName(trackName);
    }
}
