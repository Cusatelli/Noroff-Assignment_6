package com.access_and_expose.noroffassignment_6.data.repository.artist;

import com.access_and_expose.noroffassignment_6.data.repository.RestRepository;
import com.access_and_expose.noroffassignment_6.model.Artist;
import org.springframework.stereotype.Repository;

@Repository
public interface IArtistRepository extends RestRepository<Artist> { }
