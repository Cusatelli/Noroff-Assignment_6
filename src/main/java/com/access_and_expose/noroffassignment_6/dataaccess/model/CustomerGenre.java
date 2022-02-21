package com.access_and_expose.noroffassignment_6.dataaccess.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashMap;

@Setter
@Getter
public class CustomerGenre {

    //hashmap
    private String musicGenre;

    public CustomerGenre(String musicGenre, HashMap<String, Integer> favoriteGenre) {
        this.musicGenre = musicGenre;
        this.favoriteGenre = favoriteGenre;
    }

    HashMap<String, Integer> favoriteGenre = new HashMap<String, Integer>();

}
