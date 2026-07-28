package com.joysistvi.recording.service;

import com.joysistvi.recording.model.Song;

import java.util.List;


// Service contains business logic -- rules validation and computation
public class SongService {

    private final com.joysistvi.recording.repository.SongRepository songRepo;

    // Constructor injection
    public SongService(com.joysistvi.recording.repository.SongRepository songRepo) {
        this.songRepo = songRepo;
    }

    public boolean addSong(Song song) {

        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }

        if (song.getLength() == null || song.getLength().trim().isEmpty()) {
            System.out.println("Song length cannot be empty.");
            return false;
        }

        if (song.getGenre() == null || song.getGenre().trim().isEmpty()) {
            System.out.println("Song genre cannot be empty.");
            return false;
        }

        if (song.getAlbumId() <= 0) {
            System.out.println("Invalid Album ID.");
            return false;
        }

        return songRepo.createSong(song);
    }

    public List<Song> listSongs() {
        return songRepo.getAllSongs();
    }

    public boolean updateSong(String title, String length, String genre, int id) {

        if (title == null || title.trim().isEmpty()) {
            System.out.println("Song title cannot be empty.");
            return false;
        }

        if (length == null || length.trim().isEmpty()) {
            System.out.println("Song length cannot be empty.");
            return false;
        }

        if (genre == null || genre.trim().isEmpty()) {
            System.out.println("Song genre cannot be empty.");
            return false;
        }

        return songRepo.updateSong(title, length, genre, id);
    }

    public boolean deleteSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid Song ID.");
            return false;
        }

        return songRepo.deleteSong(id);
    }

    public boolean archiveSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid Song ID.");
            return false;
        }

        return songRepo.archiveSong(id);
    }

    public boolean restoreSong(int id) {

        if (id <= 0) {
            System.out.println("Invalid Song ID.");
            return false;
        }

        return songRepo.restoreSong(id);
    }

    public List<Song> listArchivedSongs() {
        return songRepo.getArchivedSongs();
    }

    public List<Song> searchSongs(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return songRepo.searchSongs(keyword);
    }

}
