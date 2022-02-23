package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.track.ITrackRepository;
import com.access_and_expose.noroffassignment_6.model.track.Track;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Track")
@RequestMapping(value = "api/v1", method = RequestMethod.GET)
public class TrackController {

    private final ITrackRepository trackRepository;

    public TrackController(ITrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @GetMapping(value = "/track/id={trackId}")
    public Track getTrackById(String trackId) {
        return this.trackRepository.getById(trackId);
    }

    @GetMapping(value = "/track/name={trackName}")
    public Track getTrackByName(String trackName) {
        return this.trackRepository.getByName(trackName);
    }
}
