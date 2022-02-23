package com.access_and_expose.noroffassignment_6.model;

public class Album {

    private Long id;
    private Long artistId;
    private String title;

    public Album() {}
    public Album(Long id, Long artistId, String title) {
        this.id = id;
        this.artistId = artistId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getArtistId() {
        return artistId;
    }
    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", artistId=" + artistId +
                ", title='" + title + '\'' +
                '}';
    }
}