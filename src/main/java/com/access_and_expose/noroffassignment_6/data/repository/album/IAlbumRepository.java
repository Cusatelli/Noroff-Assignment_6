package com.access_and_expose.noroffassignment_6.data.repository.album;

import com.access_and_expose.noroffassignment_6.data.repository.RestRepository;
import com.access_and_expose.noroffassignment_6.model.Album;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends RestRepository<Album> { }
