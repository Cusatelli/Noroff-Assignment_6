package com.access_and_expose.noroffassignment_6.data.artist;

import com.access_and_expose.noroffassignment_6.model.artist.Artist;
import org.springframework.stereotype.Repository;

@Repository
public interface IArtistRepository {

    Artist getArtistById(String artistId);
    Artist getArtistByName(String artistName);

}
