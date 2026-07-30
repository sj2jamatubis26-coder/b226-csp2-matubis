package com.joysistvi.recording.model;

public class Album {

    private int albumId;
    private String name;
    private int year;
    private int artistId;
    private String artistName;

    public Album() {
    }

    public Album(int albumId, String name, int year, String artistName) {
        this.albumId = albumId;
        this.name = name;
        this.year = year;
        this.artistName = artistName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Album ID: " + albumId +
                ", Name: " + name +
                ", Year: " + year +
                ", Artist: " + artistName;
    }
    public Album(String name, int year, int artistId) {
        this.name = name;
        this.year = year;
        this.artistId = artistId;
    }
}

