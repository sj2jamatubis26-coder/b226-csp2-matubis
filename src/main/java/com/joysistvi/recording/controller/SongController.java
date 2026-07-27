package com.joysistvi.recording.controller;

import com.joysistvi.recording.model.Song;
import com.joysistvi.recording.service.SongService;

import java.util.List;

public class SongController {

    private final SongService songService;

    // Constructor injection
    public SongController(SongService songService) {
        this.songService = songService;
    }


    public boolean addSong(String title, String length, String genre, int albumId) {
        Song song = new Song(title, length, genre, albumId);
        return songService.addSong(song);
    }

    public List<Song> listSongs() {
        return songService.listSongs();
    }

    public boolean updateSong(String title, String length, String genre, int albumId) {
        return songService.updateSong(title, length, genre, albumId);
    }

    public boolean deleteSong(int id) {
        return songService.deleteSong(id);
    }

    public boolean archiveSong(int id) {
        return songService.archiveSong(id);
    }

    public boolean restoreSong(int id) {
        return songService.restoreSong(id);
    }

    public List<Song> listArchivedSongs() {
        return songService.listArchivedSongs();
    }

    public List<Song> searchSongs(String keyword) {
        return songService.searchSongs(keyword);
    }

    // public boolean updateSong(){}
    // public boolean deleteSong(){}
}
