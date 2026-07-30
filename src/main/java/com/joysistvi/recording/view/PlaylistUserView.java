package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.PlaylistController;
import com.joysistvi.recording.model.Playlist;
import com.joysistvi.recording.controller.SongController;
import java.util.Scanner;

public class PlaylistUserView {


    private final PlaylistController playlistController;
    private final SongController songController;
    private final Scanner scanner = new Scanner(System.in);

    public PlaylistUserView(PlaylistController playlistController,
                            SongController songController) {

        this.playlistController = playlistController;
        this.songController = songController;
    }
    public void showMenu(int userId) {

        int choice;

        do {

            System.out.println("\n===== MY PLAYLIST =====");
            System.out.println("1. View Playlists");
            System.out.println("2. Create Playlist");
            System.out.println("3. Delete Playlist");
            System.out.println("4. View Songs in Playlist");
            System.out.println("5. Add Songs to Playlist");
            System.out.println("6. Remove Song from Playlist");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                choice = -1;
            }

            switch (choice) {

                case 1: {

                    var playlists = playlistController.getPlaylistsByUserId(userId);

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                    } else {
                        playlists.forEach(System.out::println);
                    }

                    break;
                }
                case 2: {

                    System.out.println("\n===== CREATE PLAYLIST =====");
                    System.out.print("Playlist Name: ");
                    String name = scanner.nextLine();

                    Playlist playlist = new Playlist(userId, name);

                    if (playlistController.addPlaylist(playlist)) {
                        System.out.println("Playlist created successfully!");
                    } else {
                        System.out.println("Failed to create playlist.");
                    }
                    break;
            }


                case 3: {

                    System.out.println("\n===== DELETE PLAYLIST =====");

                    var userPlaylists = playlistController.getPlaylistsByUserId(userId);

                    if (userPlaylists.isEmpty()) {
                        System.out.println("No playlists found.");
                        break;
                    }

                    System.out.println("\nYour Playlists:");
                    userPlaylists.forEach(System.out::println);

                    int playlistId;

                    try {
                        System.out.print("\nEnter Playlist ID to delete: ");
                        playlistId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Playlist ID.");
                        break;
                    }

                    if (playlistController.archivePlaylist(playlistId)) {
                        System.out.println("Playlist deleted successfully.");
                    } else {
                        System.out.println("Playlist not found.");
                    }

                    break;
            }
                case 4: {
                    System.out.println("\n===== VIEW SONGS IN PLAYLIST =====");

                    var playlists = playlistController.getPlaylistsByUserId(userId);

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                        break;
                    }

                    playlists.forEach(System.out::println);

                    System.out.print("Enter Playlist ID: ");

                    int playlistId;

                    try {
                        playlistId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Playlist ID.");
                        break;
                    }

                    var songs = playlistController.getSongsInPlaylist(playlistId);

                    if (songs.isEmpty()) {
                        System.out.println("No songs in this playlist.");
                    } else {
                        System.out.println("\nSongs:");
                        songs.forEach(System.out::println);
                    }
                    break;
                }

                case 5:

                    System.out.println("\n===== ADD SONG TO PLAYLIST =====");

                    var myPlaylists = playlistController.getPlaylistsByUserId(userId);

                    if (myPlaylists.isEmpty()) {
                        System.out.println("No playlists found.");
                        break;
                    }

                    System.out.println("\nYour Playlists:");
                    myPlaylists.forEach(System.out::println);

                    int playlistIdToAdd;

                    try {
                        System.out.print("Enter Playlist ID: ");
                        playlistIdToAdd = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Playlist ID.");
                        break;
                    }

                    System.out.println("\nAvailable Songs:");

                    songController.listSongs().forEach(System.out::println);

                    int songId;

                    try {
                        System.out.print("Enter Song ID: ");
                        songId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Song ID.");
                        break;
                    }

                    if (playlistController.addSongToPlaylist(playlistIdToAdd, songId)) {
                        System.out.println("Song added successfully.");
                    } else {
                        System.out.println("Failed to add song.");
                    }

                    break;
                case 6: {

                    System.out.println("\n===== REMOVE SONG FROM PLAYLIST =====");

                    var playlists = playlistController.getPlaylistsByUserId(userId);

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                        break;
                    }

                    System.out.println("\nYour Playlists:");
                    playlists.forEach(System.out::println);

                    int playlistId;

                    try {
                        System.out.print("Enter Playlist ID: ");
                        playlistId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Playlist ID.");
                        break;
                    }

                    var songs = playlistController.getSongsInPlaylist(playlistId);

                    if (songs.isEmpty()) {
                        System.out.println("No songs in this playlist.");
                        break;
                    }

                    System.out.println("\nSongs in Playlist:");
                    songs.forEach(System.out::println);

                    int removeSongId;

                    try {
                        System.out.print("Enter Song ID to remove: ");
                        removeSongId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Song ID.");
                        break;
                    }

                    if (playlistController.removeSongFromPlaylist(playlistId, removeSongId)) {
                        System.out.println("Song removed successfully.");
                    } else {
                        System.out.println("Failed to remove song.");
                    }

                    break;
                }


                case 0: {
                    break;
                }

                default:
                    if (choice != -1) {
                        System.out.println("Invalid choice.");
                    }
            }

        } while (choice != 0);
    }
}