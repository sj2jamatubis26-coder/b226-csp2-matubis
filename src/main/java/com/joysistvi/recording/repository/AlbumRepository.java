package com.joysistvi.recording.repository;

import com.joysistvi.recording.model.Album;

import java.util.List;

public interface AlbumRepository {

        List<Album> getAllAlbums();

        boolean createAlbum(Album album);

        boolean updateAlbum(String name, int year, int artistId, int id);

        boolean deleteAlbum(int id);

        boolean archiveAlbum(int id);

        boolean restoreAlbum(int id);

        List<Album> getArchivedAlbums();

        List<Album> searchAlbums(String keyword);
    }

