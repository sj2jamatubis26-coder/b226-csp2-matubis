package com.joysistvi.recording.controller;

import com.joysistvi.recording.model.Playlist;

import com.joysistvi.recording.service.PlaylistService;

import java.util.List;

public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public boolean addPlaylist(Playlist playlist) {
        return playlistService.addPlaylist(playlist);
    }

    public List<Playlist> getPlaylistsByUserId(int userId) {
        return playlistService.getPlaylistsByUserId(userId);
    }

    public boolean updatePlaylist(Playlist playlist) {
        return playlistService.updatePlaylist(playlist);
    }

    public boolean deletePlaylist(int id) {
        return playlistService.deletePlaylist(id);
    }

    public boolean archivePlaylist(int id) {
        return playlistService.archivePlaylist(id);
    }

    public boolean restorePlaylist(int id) {
        return playlistService.restorePlaylist(id);
    }

    public List<Playlist> listArchivedPlaylists() {
        return playlistService.listArchivedPlaylists();
    }

    public List<Playlist> searchPlaylists(String keyword) {
        return playlistService.searchPlaylists(keyword);
    }

    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        return playlistService.removeSongFromPlaylist(playlistId, songId);
    }
    public List<Playlist> listPlaylists() {
        return playlistService.listPlaylists();
    }
    public List<String> getSongsInPlaylist(int playlistId) {
        return playlistService.getSongsInPlaylist(playlistId);
    }
    public boolean addSongToPlaylist(int playlistId, int songId) {
        return playlistService.addSongToPlaylist(playlistId, songId);
    }
}
