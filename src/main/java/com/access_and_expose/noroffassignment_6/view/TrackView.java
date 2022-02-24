package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.track.ITrackRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/track")
public class TrackView {

    private final ITrackRepository trackRepository;

    public TrackView(ITrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("tracks", trackRepository.getAll());
        return "listTrack";
    }
}
