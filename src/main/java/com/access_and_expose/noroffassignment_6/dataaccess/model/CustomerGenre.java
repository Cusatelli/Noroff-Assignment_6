package com.access_and_expose.noroffassignment_6.dataaccess.model;

import org.springframework.data.relational.core.mapping.Table;

import java.util.HashMap;

@Table
public class CustomerGenre {

    //hashmap
    private String musicGenre;
    private HashMap<String, Integer> favoriteGenre = new HashMap<String, Integer>();

    public CustomerGenre(String musicGenre, HashMap<String, Integer> favoriteGenre) {
        this.musicGenre = musicGenre;
        this.favoriteGenre = favoriteGenre;
    }


}
