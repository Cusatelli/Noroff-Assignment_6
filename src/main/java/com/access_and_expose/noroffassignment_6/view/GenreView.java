package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.service.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/genre")
public class GenreView {

    private final GenreService genreService;

    public GenreView(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("genres", genreService.getAll());
        return "listGenre";
    }
}
