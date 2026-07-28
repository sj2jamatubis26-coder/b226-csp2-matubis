package com.joysistvi.recording.service;

import com.joysistvi.recording.model.Album;

import java.util.List;

import com.joysistvi.recording.repository.AlbumRepository;

public class AlbumService {

    private final AlbumRepository albumRepo;

    // Constructor injection
    public AlbumService(AlbumRepository albumRepo) {
        this.albumRepo = albumRepo;
    }

    public boolean addAlbum(Album album) {
        if (album.getName() == null || album.getName().trim().isEmpty() ) {
            System.out.println("Album name cannot be empty");
            return false;
        }

        return albumRepo.createAlbum(album);
    }

    public List<Album> listAlbums() {
        return albumRepo.getAllAlbums();
    }

    public boolean updateAlbum(String name, int year, int artistId, int id) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Album name cannot be empty.");
            return false;
        }

        return albumRepo.updateAlbum(name, year, artistId, id);
    }

    public boolean deleteAlbum(int id) {

        if (id <= 0) {
            System.out.println("Invalid Album ID.");
            return false;
        }

        return albumRepo.deleteAlbum(id);
    }

    public boolean archiveAlbum(int id) {

        if (id <= 0) {
            System.out.println("Invalid Album ID.");
            return false;
        }

        return albumRepo.archiveAlbum(id);
    }

    public boolean restoreAlbum(int id) {

        if (id <= 0) {
            System.out.println("Invalid Album ID.");
            return false;
        }

        return albumRepo.restoreAlbum(id);
    }

    public List<Album> listArchivedAlbums() {
        return albumRepo.getArchivedAlbums();
    }

    public List<Album> searchAlbums(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return albumRepo.searchAlbums(keyword);
    }

}
