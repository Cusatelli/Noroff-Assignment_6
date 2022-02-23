package com.access_and_expose.noroffassignment_6.model;

public class Track {

    private Long id;
    private String name;
    private Long albumId;
    private Long genreId;
    private Long unitPrice;

    public Track() {}
    public Track(Long id, String name, Long albumId, Long genreId, Long unitPrice) {
        this.id = id;
        this.name = name;
        this.albumId = albumId;
        this.genreId = genreId;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getGenreId() {
        return genreId;
    }
    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
    public Long getAlbumId() {
        return albumId;
    }
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
    public Long getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genreId=" + genreId +
                ", albumId=" + albumId +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
