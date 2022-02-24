package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/artist")
public class ArtistView {

    private final ArtistService artistService;

    public ArtistView(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("artists", artistService.getAll());
        return "listArtist";
    }
}
