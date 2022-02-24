package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.AlbumRepository;
import com.access_and_expose.noroffassignment_6.data.repository.ArtistRepository;
import com.access_and_expose.noroffassignment_6.data.repository.GenreRepository;
import com.access_and_expose.noroffassignment_6.data.repository.TrackRepository;
import com.access_and_expose.noroffassignment_6.model.Album;
import com.access_and_expose.noroffassignment_6.model.Artist;
import com.access_and_expose.noroffassignment_6.model.Genre;
import com.access_and_expose.noroffassignment_6.model.Track;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // 5 random artists, 5 random songs, and 5 random genres
    @GetMapping("/")
    public String view(Model model) {
        resetAll(); // Reset lists if you want to refresh content.
        initializeRandom(0, 5);

        model.addAttribute("artists", artists);
        model.addAttribute("tracks", tracks);
        model.addAttribute("genres", genres);

        return INDEX_HTML;
    }

    @GetMapping("/search")
    public String searchView(Track track, Model model, String keyword) {
        if(keyword == null || keyword.equals(" ") || keyword.length() <= 0) { // Guard Clause
            return INDEX_HTML;
        }

        resetAll(); // Reset lists if you want to refresh content.
        getSearch(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("tracks", tracks);
        model.addAttribute("artists", artists);
        model.addAttribute("genres", genres);

        return INDEX_HTML;
    }

    private void getSearch(String keyword) {
        tracks = trackRepository.getByKeyword(keyword);

        for (int i = 0; i < tracks.size(); i++) {
            albums.add(albumRepository.getById(String.valueOf(tracks.get(0).getAlbumId())));
            Album album = albumRepository.getById(String.valueOf(tracks.get(0).getAlbumId()));
            artists.add(artistRepository.getById(String.valueOf(album.getArtistId())));
            genres.add(genreRepository.getById(String.valueOf(tracks.get(0).getGenreId())));
        }
    }

    private void initializeRandom(int min, int max) {
        int artistSize = artistRepository.getAll().size();
        int trackSize = trackRepository.getAll().size();
        int genreSize = genreRepository.getAll().size();

        for (int i = min; i < max; i++) {
            artists.add(artistRepository.getById(String.valueOf(Math.round(Math.random() * artistSize))));
            tracks.add(trackRepository.getById(String.valueOf(Math.round(Math.random() * trackSize))));
            genres.add(genreRepository.getById(String.valueOf(Math.round(Math.random() * genreSize))));
        }
    }

    private void resetAll() {
        tracks = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
        genres = new ArrayList<>();
    }
}
