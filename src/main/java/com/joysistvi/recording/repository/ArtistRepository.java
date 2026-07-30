package com.joysistvi.recording.repository;

// Contract

import com.joysistvi.recording.model.Artist;

import java.util.List;

// Repository: handles data access
public interface ArtistRepository {

    public List<Artist> getAllArtists();
    public boolean createArtist(Artist artist);
    public boolean updateArtist(String name, int id);
    public boolean deleteArtist(int id);
    public boolean archiveArtist(int id);
    public boolean restoreArtist(int id);
    public  List<Artist> getArchivedArtists();
    public List<Artist> searchArtists(String keyword);
    boolean artistExists(String name);


}

