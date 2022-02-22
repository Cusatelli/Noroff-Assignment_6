package com.access_and_expose.noroffassignment_6.data.track;

import com.access_and_expose.noroffassignment_6.model.track.Track;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrackRepository {

    Track getTrackById(Track track);
    Track getTrackName(String name);
}
