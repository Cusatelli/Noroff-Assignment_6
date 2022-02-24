package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/track")
public class TrackView {

    private final TrackService trackService;

    public TrackView(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("tracks", trackService.getAll());
        return "listTrack";
    }
}
