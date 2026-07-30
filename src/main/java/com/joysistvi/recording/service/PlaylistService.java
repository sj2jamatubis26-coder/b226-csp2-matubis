package com.joysistvi.recording.service;

import com.joysistvi.recording.model.Playlist;


import java.util.List;


// Service contains business logic -- rules validation and computation
public class PlaylistService {

    private final com.joysistvi.recording.repository.PlaylistRepository playlistRepo;

    // Constructor injection
    public PlaylistService(com.joysistvi.recording.repository.PlaylistRepository playlistRepo) {
        this.playlistRepo = playlistRepo;
    }

    public boolean addPlaylist(Playlist playlist) {
        if (playlist.getName() == null || playlist.getName().isEmpty() ) {
            System.out.println("Playlist name cannot be empty");
            return false;
        }

        return playlistRepo.createPlaylist(playlist);
    }

    public List<Playlist> getPlaylistsByUserId(int userId) {

        if (userId <= 0) {
            System.out.println("Invalid User ID.");
            return List.of();
        }

        return playlistRepo.getPlaylistsByUserId(userId);
    }
    public boolean updatePlaylist(Playlist playlist) {

        if (playlist.getName() == null || playlist.getName().trim().isEmpty()) {
            System.out.println("Playlist name cannot be empty.");
            return false;
        }

        return playlistRepo.updatePlaylist(playlist);
    }

    public boolean deletePlaylist(int id) {

        if (id <= 0) {
            System.out.println("Invalid Playlist ID.");
            return false;
        }

        return playlistRepo.deletePlaylist(id);
    }

    public boolean archivePlaylist(int id) {

        if (id <= 0) {
            System.out.println("Invalid Playlist ID.");
            return false;
        }

        return playlistRepo.archivePlaylist(id);
    }

    public boolean restorePlaylist(int id) {

        if (id <= 0) {
            System.out.println("Invalid Playlist ID.");
            return false;
        }

        return playlistRepo.restorePlaylist(id);
    }

    public List<Playlist> listArchivedPlaylists() {
        return playlistRepo.getArchivedPlaylists();
    }


    public List<Playlist> searchPlaylists(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return playlistRepo.searchPlaylistByName(keyword);
    }

    public boolean removeSongFromPlaylist(int playlistId, int songId) {

        if (playlistId <= 0 || songId <= 0) {
            System.out.println("Invalid Playlist ID or Song ID.");
            return false;
        }

        return playlistRepo.removeSongFromPlaylist(playlistId, songId);
    }

    public List<Playlist> listPlaylists() {
        return playlistRepo.getAllPlaylists();
    }
    public List<String> getSongsInPlaylist(int playlistId) {

        if (playlistId <= 0) {
            System.out.println("Invalid Playlist ID.");
            return List.of();
        }

        return playlistRepo.getSongsInPlaylist(playlistId);
    }
    public boolean addSongToPlaylist(int playlistId, int songId) {

        if (playlistId <= 0 || songId <= 0) {
            System.out.println("Invalid Playlist ID or Song ID.");
            return false;
        }

        if (playlistRepo.songExistsInPlaylist(playlistId, songId)) {
            System.out.println("Song already exists in this playlist.");
            return false;
        }

        return playlistRepo.addSongToPlaylist(playlistId, songId);
    }
}
