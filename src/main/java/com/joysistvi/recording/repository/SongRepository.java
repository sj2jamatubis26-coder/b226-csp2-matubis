package com.joysistvi.recording.repository;

// Contract

import com.joysistvi.recording.model.Song;

import java.util.List;

// Repository: handles data access
public interface SongRepository {

    public List<Song> getAllSongs();
    public boolean createSong(Song song);
    public boolean updateSong(String title, String length, String genre, int id);
    public boolean deleteSong(int id);
    public boolean archiveSong(int id);
    public boolean restoreSong(int id);
    public  List<Song> getArchivedSongs();
    public List<Song> searchSongs(String keyword);
    boolean songExists(String title, int albumId);


}

