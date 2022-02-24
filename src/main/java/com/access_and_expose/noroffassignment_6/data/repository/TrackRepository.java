package com.access_and_expose.noroffassignment_6.data.repository;

import com.access_and_expose.noroffassignment_6.model.Track;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TrackRepository extends RestRepository<Track> {
    ArrayList<Track> getByKeyword(String keyword);
}
