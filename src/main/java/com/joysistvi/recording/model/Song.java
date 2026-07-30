package com.joysistvi.recording.model;


// Model / Encapsulated Class

// holds and presents data
// This is where data passes through before going to the database or before being set to the User
public class Song {
    private int id;
    private String title;
    private String length;
    private String genre;
    private String albumName;
    private String artistName;
    private int albumId;

    public Song() {
    }

    public Song(int id, String title, String length, String genre, String albumName, String artistName) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.albumName = albumName;
        this.artistName = artistName;
    }

    public Song(String title, String length, String genre, int albumId) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.albumId = albumId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() { return artistName; }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    @Override
    public String toString() {
        return String.format(
                "%-4d %-20s %-20s %-20s",
                id,
                title,
                artistName,
                albumName
        );
    }
}