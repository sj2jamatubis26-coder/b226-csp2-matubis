package com.joysistvi.recording.service;

import com.joysistvi.recording.model.Artist;
import com.joysistvi.recording.repository.ArtistRepository;


import java.util.List;


// Service contains business logic -- rules validation and computation
public class ArtistService {

    private final ArtistRepository artistRepo;

    // Constructor injection
    public ArtistService(ArtistRepository artistRepo) {
        this.artistRepo = artistRepo;
    }

    public boolean addArtist(Artist artist) {

        if (artist.getName() == null || artist.getName().trim().isEmpty()) {
            System.out.println("Artist name cannot be empty.");
            return false;
        }

        if (artistRepo.artistExists(artist.getName())) {
            System.out.println("Artist already exists.");
            return false;
        }

        return artistRepo.createArtist(artist);
    }

    public List<Artist> listArtists() {
        return artistRepo.getAllArtists();
    }
    public boolean updateArtist(String name, int artistId) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Artist name cannot be empty. ");
            return false;
        }
        if (artistRepo.artistExists(name)) {
            System.out.println("Artist already exists.");
            return false;
        }
        return artistRepo.updateArtist(name, artistId);
    }

    public boolean deleteArtist(int id) {

        if (id <= 0) {
            System.out.println("Invalid Artist ID.");
            return false;
        }

        return artistRepo.deleteArtist(id);
    }

    public boolean archiveArtist(int id) {

        if (id <= 0) {
            System.out.println("Invalid Artist ID.");
            return false;
        }

        return artistRepo.archiveArtist(id);
    }

    public boolean restoreArtist(int id) {

        if (id <= 0) {
            System.out.println("Invalid artist ID.");
            return false;
        }

        return artistRepo.restoreArtist(id);
    }

    public List<Artist> listArchivedArtists() {
        return artistRepo.getArchivedArtists();
    }

    public List<Artist> searchArtists(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return artistRepo.searchArtists(keyword);
    }
    public List<Artist> getAllArtists() {
        return artistRepo.getAllArtists();
    }

}
