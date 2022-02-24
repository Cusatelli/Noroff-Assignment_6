package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/album")
public class AlbumView {

    private final AlbumService albumService;

    public AlbumView(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Get All Albums from Database and send them to Thymeleaf HTML.
     * @param model model
     * @return string-name of html file.
     */
    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("albums", albumService.getAll());
        return "listAlbum";
    }
}
