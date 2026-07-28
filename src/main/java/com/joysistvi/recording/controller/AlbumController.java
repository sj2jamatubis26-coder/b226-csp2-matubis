package com.joysistvi.recording.controller;

import com.joysistvi.recording.model.Album;
import com.joysistvi.recording.service.AlbumService;

import java.util.List;

public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    public boolean addAlbum(String name, int year, int artistId) {

        Album album = new Album();
        album.setName(name);
        album.setYear(year);
        album.setArtistId(artistId);

        return albumService.addAlbum(album);
    }

    public List<Album> listAlbums() {
        return albumService.listAlbums();
    }

    public boolean updateAlbum(String name, int year, int artistId, int id) {
        return albumService.updateAlbum(name, year, artistId, id);
    }

    public boolean deleteAlbum(int id) {
        return albumService.deleteAlbum(id);
    }

    public boolean archiveAlbum(int id) {
        return albumService.archiveAlbum(id);
    }

    public boolean restoreAlbum(int id) {
        return albumService.restoreAlbum(id);
    }

    public List<Album> listArchivedAlbums() {
        return albumService.listArchivedAlbums();
    }

    public List<Album> searchAlbums(String keyword) {
        return albumService.searchAlbums(keyword);
    }
}