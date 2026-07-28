package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.AlbumController;
import com.joysistvi.recording.model.Album;

import java.util.List;
import java.util.Scanner;

public class AlbumView {

    private final AlbumController albumController;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor injection
    public AlbumView(AlbumController albumController) {
        this.albumController = albumController;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== ALBUM MENU =====");
            System.out.println("1. Add Album");
            System.out.println("2. View Albums");
            System.out.println("3. Update Album");
            System.out.println("4. Delete Album");
            System.out.println("5. Archive Album");
            System.out.println("6. Restore Album");
            System.out.println("7. View Archived Albums");
            System.out.println("8. Search Album");
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
                    System.out.println("=== Add Album ===");

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Year: ");
                    int year = scanner.nextInt();

                    System.out.print("Artist ID: ");
                    int artistId = scanner.nextInt();
                    scanner.nextLine();

                    if (albumController.addAlbum(name, year, artistId)) {
                        System.out.println("Album added successfully.");
                    } else {
                        System.out.println("Failed to add album.");
                    }

                    break;
                }
                case 2: {
                    System.out.println("=== View All Albums ===");

                    for (Album album : albumController.listAlbums()) {
                        System.out.println(album);
                    }
                    break;
                }

                case 3: {
                    System.out.println("=== Update Album ===");

                    System.out.print("Enter Album ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New Name: ");
                    String name = scanner.nextLine();

                    System.out.print("New Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New Artist ID: ");
                    int artistId = scanner.nextInt();
                    scanner.nextLine();

                    if (albumController.updateAlbum(name, year, artistId, id)) {
                        System.out.println("Album updated successfully.");
                    } else {
                        System.out.println("Failed to update album.");
                    }

                    break;
                }
                case 4: {
                    System.out.println("=== Delete Album ===");

                    System.out.print("Enter Album ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (albumController.deleteAlbum(id)) {
                        System.out.println("Album deleted successfully.");
                    } else {
                        System.out.println("Failed to delete Album.");
                    }

                    break;
                }
                case 5: {

                    System.out.println("=== Archive Album ===");

                    System.out.print("Enter Album ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (albumController.archiveAlbum(id)) {
                        System.out.println("Album archived successfully.");
                    } else {
                        System.out.println("Failed to archive album.");
                    }

                    break;
                }
                case 6: {

                    System.out.println("=== Restore Album ===");
                    System.out.println("\nArchived Albums:");

                    for (Album album : albumController.listArchivedAlbums()) {
                        System.out.println(album);
                    }

                    System.out.print("Enter Album ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (albumController.restoreAlbum(id)) {
                        System.out.println("Album restored successfully.");
                    } else {
                        System.out.println("Failed to restore album.");
                    }

                    break;
                }
                case 7: {

                    System.out.println("=== Archived Albums ===");

                    for (Album album : albumController.listArchivedAlbums()) {
                        System.out.println(album);
                    }

                    break;
                }

                case 8: {

                    System.out.println("=== Search Album ===");

                    System.out.print("Enter album name: ");
                    String keyword = scanner.nextLine();

                    List<Album> albums = albumController.searchAlbums(keyword);

                    if (albums.isEmpty()) {
                        System.out.println("No albums found.");
                    } else {

                        System.out.println("\nSearch Result:");

                        for (Album album : albums) {
                            System.out.println(album);
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

