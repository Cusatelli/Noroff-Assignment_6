package com.access_and_expose.noroffassignment_6.data.album;

import com.access_and_expose.noroffassignment_6.model.album.Album;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository {

    Album getById(String albumId);
    Album getByName(String albumName);
}
