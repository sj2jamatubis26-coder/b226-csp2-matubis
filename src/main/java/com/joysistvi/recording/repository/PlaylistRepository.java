package com.joysistvi.recording.repository;

import com.joysistvi.recording.model.Playlist;

import java.util.List;

public interface PlaylistRepository {

    boolean createPlaylist(Playlist playlist);

    List<Playlist> getAllPlaylists();

    List<Playlist> getPlaylistsByUserId(int userId);

    boolean updatePlaylist(Playlist playlist);

    boolean deletePlaylist(int id);

    Playlist searchPlaylistById(int id);

    List<Playlist> searchPlaylistByName(String name);

    List<Playlist> getArchivedPlaylists();

    boolean archivePlaylist(int id);

    boolean restorePlaylist(int id);
}