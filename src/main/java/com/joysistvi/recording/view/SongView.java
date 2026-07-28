package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.SongController;
import com.joysistvi.recording.model.Song;

import java.util.List;
import java.util.Scanner;

public class SongView {

    private final SongController songController;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor injection
    public SongView(SongController songController) {
        this.songController = songController;
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
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

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

                    System.out.print("Album ID: ");
                    int albumId = scanner.nextInt();
                    scanner.nextLine();

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
                default:
                    System.out.println("Invalid choice");
                case 3: {
                    System.out.println("=== Update Song ===");

                    System.out.print("Enter Song ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New Title: ");
                    String title = scanner.nextLine();

                    System.out.print("New Length: ");
                    String length = scanner.nextLine();

                    System.out.print("New Genre: ");
                    String genre = scanner.nextLine();

                    if (songController.updateSong(title, length, genre, id)) {
                        System.out.println("Song updated successfully.");
                    } else {
                        System.out.println("Failed to update song.");
                    }

                    break;
                }
                case 4: {
                    System.out.println("=== Delete Song ===");

                    System.out.print("Enter Song ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (songController.deleteSong(id)) {
                        System.out.println("Song deleted successfully.");
                    } else {
                        System.out.println("Failed to delete song.");
                    }

                    break;
                }
                case 5: {

                    System.out.println("=== Archive Song ===");

                    System.out.print("Enter Song ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

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
                    int id = scanner.nextInt();
                    scanner.nextLine();

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
            }
        } while (choice != 0);
    }

}
