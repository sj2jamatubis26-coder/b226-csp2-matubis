package com.joysistvi.recording.controller;

import com.joysistvi.recording.model.Artist;
import com.joysistvi.recording.service.ArtistService;

import java.util.List;

public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    public boolean addArtist(String name) {

        Artist artist = new Artist(name);
        artist.setName(name);

        return artistService.addArtist(artist);
    }

    public List<Artist> listArtists() {
        return artistService.listArtists();
    }

    public boolean updateArtist(String name, int id) {
        return artistService.updateArtist(name, id);
    }

    public boolean deleteArtist(int id) {
        return artistService.deleteArtist(id);
    }

    public boolean archiveArtist(int id) {
        return artistService.archiveArtist(id);
    }

    public boolean restoreArtist(int id) {
        return artistService.restoreArtist(id);
    }

    public List<Artist> listArchivedArtists() {
        return artistService.listArchivedArtists();
    }

    public List<Artist> searchArtists(String keyword) {
        return artistService.searchArtists(keyword);
    }

    public List<Artist> getAllArtists() { return artistService.getAllArtists(); }
}