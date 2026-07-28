package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.SongController;
import com.joysistvi.recording.model.Song;
import com.joysistvi.recording.controller.AlbumController;
import com.joysistvi.recording.model.Album;

import java.util.List;
import java.util.Scanner;

public class SongView {

    private final SongController songController;
    private final AlbumController albumController;
    private final Scanner scanner = new Scanner(System.in);


    // Constructor injection
    public SongView(SongController songController,
                    AlbumController albumController) {

        this.songController = songController;
        this.albumController = albumController;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Song Menu ---");
            System.out.println("1. Add Song");
            System.out.println("2. View All Songs");
            System.out.println("3. Update Song");
            System.out.println("4. Delete Song");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.println("7. Search Song");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }

            switch (choice) {
                case 0: {
                    System.out.println("Returning to Main Menu...");
                    break;
                }

                case 1: {

                    System.out.println("=== Add Song ===");

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Length: ");
                    String length = scanner.nextLine();

                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    System.out.println("\n=== Available Albums ===");
                    System.out.printf("%-5s %-30s%n", "ID", "Album Name");
                    System.out.println("-------------------------------------------");

                    for (Album album : albumController.listAlbums()) {
                        System.out.printf("%-5d %-30s%n",
                                album.getAlbumId(),
                                album.getName());
                    }

                    System.out.println();
                    System.out.print("Enter Album ID: ");

                    System.out.print("Album ID: ");
                    int albumId;

                    try {
                        albumId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Album ID. Please enter a number.");
                        break;
                    }

                    if (songController.addSong(title, length, genre, albumId)) {
                        System.out.println("Song added successfully.");
                    } else {
                        System.out.println("Failed to add song.");
                    }

                    break;
                }
                case 2: {
                    System.out.println("View All Songs");

                    for (Song song : songController.listSongs()) {
                        System.out.println(song);
                    }
                    break;
                }

                case 3: {

                    System.out.println("=== Update Song ===");
                    System.out.println("\nAvailable Songs:");

                    for (Song song : songController.listSongs()) {
                        System.out.println(song);
                    }

                    System.out.println();
                    System.out.print("Enter Song ID: ");

                    int songId;

                    try {
                        songId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Song ID. Please enter a number.");
                        break;
                    }

                    System.out.print("New Title: ");
                    String title = scanner.nextLine();

                    System.out.print("New Length: ");
                    String length = scanner.nextLine();

                    System.out.print("New Genre: ");
                    String genre = scanner.nextLine();

                    if (songController.updateSong(title, length, genre, songId)) {
                        System.out.println("Song updated successfully.");
                    } else {
                        System.out.println("Failed to update song.");
                    }

                    break;
                }
                case 4: {

                    System.out.println("\n=== Available Songs ===");

                    List<Song> songs = songController.listSongs();
                    if (songs.isEmpty()) {
                        System.out.println("No songs found.");
                        break;
                    }

                    songs.forEach(System.out::println);

                    try {

                        System.out.print("\nEnter Song ID to delete: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Are you sure you want to delete this song? (Y/N): ");
                        String confirm = scanner.nextLine();

                        if (confirm.equalsIgnoreCase("Y")) {

                            boolean success = songController.deleteSong(id);

                            System.out.println(success ?
                                    "Song deleted successfully." :
                                    "Delete failed.");

                        } else {

                            System.out.println("Delete cancelled.");

                        }

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid ID. Please enter a number.");

                    }

                    break;
                }
                case 5: {

                    System.out.println("=== Archive Song ===");

                    System.out.println("\nAvailable Songs:");

                    for (Song song : songController.listSongs()) {
                        System.out.println(song);
                    }

                    System.out.print("\nEnter Song ID: ");

                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Song ID.");
                        break;
                    }

                    if (songController.archiveSong(id)) {
                        System.out.println("Song archived successfully.");
                    } else {
                        System.out.println("Failed to archive song.");
                    }

                    break;
                }
                case 6: {

                    System.out.println("=== Restore Song ===");
                    System.out.println("\nArchived Songs:");

                    for (Song song : songController.listArchivedSongs()) {
                        System.out.println(song);
                    }

                    System.out.print("Enter Song ID: ");
                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Song ID.");
                        break;
                    }

                    if (songController.restoreSong(id)) {
                        System.out.println("Song restored successfully.");
                    } else {
                        System.out.println("Failed to restore song.");
                    }

                    break;
                }
                case 7: {

                    System.out.println("=== Search Song ===");

                    System.out.print("Enter song title: ");
                    String keyword = scanner.nextLine();

                    List<Song> songs = songController.searchSongs(keyword);

                    if (songs.isEmpty()) {
                        System.out.println("No songs found.");
                    } else {

                        System.out.println("\nSearch Result:");

                        for (Song song : songs) {
                            System.out.println(song);
                        }
                    }

                    break;
                }
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

}
