package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.AlbumRepository;
import com.access_and_expose.noroffassignment_6.data.repository.ArtistRepository;
import com.access_and_expose.noroffassignment_6.data.repository.GenreRepository;
import com.access_and_expose.noroffassignment_6.data.repository.TrackRepository;
import com.access_and_expose.noroffassignment_6.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class HomeView {
    private static final String INDEX_HTML = "index";

    private static ArrayList<Artist> artists = new ArrayList<>();
    private static ArrayList<Track> tracks = new ArrayList<>();
    private static ArrayList<Genre> genres = new ArrayList<>();
    private static ArrayList<Album> albums = new ArrayList<>();

    private final ArtistRepository artistRepository;
    private final TrackRepository trackRepository;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;

    public HomeView(ArtistRepository artistRepository, TrackRepository trackRepository, GenreRepository genreRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
    }

    //
    /**
     * Get 5 random artists, 5 random songs, and 5 random genres
     * from Database and send them to Thymeleaf HTML.
     * @param model model
     * @return string-name of html file.
     */
    @GetMapping("/")
    public String view(Model model) {
        resetAll(); // Reset lists if you want to refresh content.
        initializeRandom(0, 5);

        model.addAttribute("artists", artists);
        model.addAttribute("albums", albums);
        model.addAttribute("tracks", tracks);
        model.addAttribute("genres", genres);

        return INDEX_HTML;
    }

    /**
     * Search for a Track in database using SQL Queries on the input keyword value.
     * @param track track found.
     * @param model model.
     * @param keyword input value.
     * @return string-name of html file.
     */
    @GetMapping("/search")
    public String searchView(Track track, Model model, @RequestParam(required = true) String keyword) {
        if(keyword == null || keyword.length() <= 1) { // Guard Clause
            model.addAttribute("tracks", tracks);
            model.addAttribute("albums", albums);
            model.addAttribute("artists", artists);
            model.addAttribute("genres", genres); // Return existing
            return INDEX_HTML;
        }

        resetAll(); // Reset lists if you want to refresh content.
        getSearch(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("tracks", tracks);
        model.addAttribute("albums", albums);
        model.addAttribute("artists", artists);
        model.addAttribute("genres", genres);

        return INDEX_HTML;
    }

    /**
     * Helper Method
     * Get the search result from the different lists populated by the query.
     * @param keyword keyword to search for.
     */
    private void getSearch(String keyword) {
        tracks = trackRepository.getByKeyword(keyword);

        for (int i = 0; i < tracks.size(); i++) {
            albums.add(albumRepository.getById(String.valueOf(tracks.get(0).getAlbumId())));
            Album album = albumRepository.getById(String.valueOf(tracks.get(0).getAlbumId()));
            artists.add(artistRepository.getById(String.valueOf(album.getArtistId())));
            genres.add(genreRepository.getById(String.valueOf(tracks.get(0).getGenreId())));
        }
    }

    /**
     * Initialize random int between min and max range.
     * @param min minimum value to start at.
     * @param max maximum value to end at.
     */
    private void initializeRandom(int min, int max) {
        int artistSize = artistRepository.getAll().size();
        int trackSize = trackRepository.getAll().size();
        int genreSize = genreRepository.getAll().size();

        for (int i = min; i < max; i++) {
            artists.add(artistRepository.getById(String.valueOf(Math.round(Math.random() * artistSize))));
            albums.clear();
            tracks.add(trackRepository.getById(String.valueOf(Math.round(Math.random() * trackSize))));
            genres.add(genreRepository.getById(String.valueOf(Math.round(Math.random() * genreSize))));
        }
    }

    /**
     * Clear all lists.
     */
    private void resetAll() {
        tracks.clear();
        albums.clear();
        artists.clear();
        genres.clear();
    }
}
