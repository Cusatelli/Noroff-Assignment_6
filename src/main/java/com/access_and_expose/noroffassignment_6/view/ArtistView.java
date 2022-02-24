package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.artist.IArtistRepository;
import com.access_and_expose.noroffassignment_6.model.Artist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/artist")
public class ArtistView {

    private final IArtistRepository artistRepository;

    public ArtistView(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("artists", artistRepository.getAll());
        return "listArtist";
    }
}
