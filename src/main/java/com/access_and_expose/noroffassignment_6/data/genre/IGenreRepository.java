package com.access_and_expose.noroffassignment_6.data.genre;

import com.access_and_expose.noroffassignment_6.model.genre.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository {

    Genre getById(String genreId);
    Genre getByName(String genreName);
}
