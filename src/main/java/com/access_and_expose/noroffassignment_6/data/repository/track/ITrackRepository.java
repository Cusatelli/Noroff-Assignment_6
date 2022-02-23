package com.access_and_expose.noroffassignment_6.data.repository.track;

import com.access_and_expose.noroffassignment_6.data.repository.RestRepository;
import com.access_and_expose.noroffassignment_6.model.Track;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ITrackRepository extends RestRepository<Track> {
    ArrayList<Track> getByKeyword(String keyword);
}
