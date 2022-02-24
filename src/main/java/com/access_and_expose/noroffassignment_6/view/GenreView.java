package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.genre.IGenreRepository;
import com.access_and_expose.noroffassignment_6.model.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/genre")
public class GenreView {

    private final IGenreRepository genreRepository;

    public GenreView(IGenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/list")
    public String view(Model model) {
        Genre genre = new Genre();
        model.addAttribute("genres", genreRepository.getAll());
        return "listGenre";
    }
}
