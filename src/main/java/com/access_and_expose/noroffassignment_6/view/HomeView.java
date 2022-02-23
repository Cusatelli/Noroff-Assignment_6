package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.album.IAlbumRepository;
import com.access_and_expose.noroffassignment_6.data.repository.artist.IArtistRepository;
import com.access_and_expose.noroffassignment_6.data.repository.genre.IGenreRepository;
import com.access_and_expose.noroffassignment_6.data.repository.track.ITrackRepository;
import com.access_and_expose.noroffassignment_6.model.Album;
import com.access_and_expose.noroffassignment_6.model.Artist;
import com.access_and_expose.noroffassignment_6.model.Genre;
import com.access_and_expose.noroffassignment_6.model.Track;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping(value = "/")
public class HomeView {

    private final IArtistRepository artistRepository;
    private final ITrackRepository trackRepository;
    private final IGenreRepository genreRepository;
    private final IAlbumRepository albumRepository;

    public HomeView(IArtistRepository artistRepository, ITrackRepository trackRepository, IGenreRepository genreRepository, IAlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
    }

    // 5 random artists, 5 random songs, and 5 random genres
    @GetMapping("/")
    public String view(Model model) {
        Collection<Artist> artists = new ArrayList<>();
        Collection<Track> tracks = new ArrayList<>();
        Collection<Genre> genres = new ArrayList<>();

        int artistSize = artistRepository.getAll().size();
        int trackSize = trackRepository.getAll().size();
        int genreSize = genreRepository.getAll().size();

        for (int i = 0; i < 5; i++) {
            artists.add(artistRepository.getById(String.valueOf(Math.round(Math.random() * artistSize))));
            tracks.add(trackRepository.getById(String.valueOf(Math.round(Math.random() * trackSize))));
            genres.add(genreRepository.getById(String.valueOf(Math.round(Math.random() * genreSize))));
        }

        model.addAttribute("artists", artists);
        model.addAttribute("tracks", tracks);
        model.addAttribute("genres", genres);
        return "index";
    }

    @GetMapping("/search")
    public String searchView(Track track, Model model, String keyword) {
        if(keyword != null && keyword != " ") {
            ArrayList<Track> tracks = trackRepository.getByKeyword(keyword);
            ArrayList<Album> albums = new ArrayList<>();
            ArrayList<Artist> artists = new ArrayList<>();
            ArrayList<Genre> genres = new ArrayList<>();
            for (int i = 0; i < tracks.size(); i++) {
                albums.add(albumRepository.getById(String.valueOf(tracks.get(0).getAlbumId())));
                Album album = albumRepository.getById(String.valueOf(tracks.get(0).getAlbumId()));
                artists.add(artistRepository.getById(String.valueOf(album.getArtistId())));
                genres.add(genreRepository.getById(String.valueOf(tracks.get(0).getGenreId())));
            }
            model.addAttribute("tracks", tracks);
            model.addAttribute("artists", artists);
            model.addAttribute("genres", genres);
        }

        return "index";
    }
}
