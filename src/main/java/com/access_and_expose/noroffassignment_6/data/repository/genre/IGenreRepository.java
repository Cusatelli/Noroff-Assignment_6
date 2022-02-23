package com.access_and_expose.noroffassignment_6.data.repository.genre;

import com.access_and_expose.noroffassignment_6.data.repository.RestRepository;
import com.access_and_expose.noroffassignment_6.model.Genre;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends RestRepository<Genre> { }
