package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.album.IAlbumRepository;
import com.access_and_expose.noroffassignment_6.model.Album;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/album")
public class AlbumView {

    private final IAlbumRepository albumRepository;

    public AlbumView(IAlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("albums", albumRepository.getAll());
        return "listAlbum";
    }
}
