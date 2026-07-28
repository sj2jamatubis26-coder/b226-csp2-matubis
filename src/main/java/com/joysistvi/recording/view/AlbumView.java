package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.AlbumController;
import com.joysistvi.recording.model.Album;
import com.joysistvi.recording.controller.ArtistController;
import com.joysistvi.recording.model.Artist;

import java.util.List;
import java.util.Scanner;

public class AlbumView {

    private final AlbumController albumController;
    private final ArtistController artistController;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor injection
    public AlbumView(AlbumController albumController,
                     ArtistController artistController) {

        this.albumController = albumController;
        this.artistController = artistController;
    }

    public void showMenu() {
        int choice = -1;
        do {
            System.out.println("\n===== ALBUM MENU =====");
            System.out.println("1. Add Album");
            System.out.println("2. View Albums");
            System.out.println("3. Update Album");
            System.out.println("4. Delete Album");
            System.out.println("5. Archive Album");
            System.out.println("6. Restore Album");
            System.out.println("7. Search Album");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }


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
                    int artistId;

                    try {
                        artistId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Artist ID. Please enter a number.");
                        break;
                    }

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
                    System.out.println("\nAvailable Albums:");

                    for (Album album : albumController.listAlbums()) {
                        System.out.println(album);
                    }

                    System.out.println();

                    System.out.print("Enter Album ID: ");
                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Album ID. Please enter a number.");
                        break;
                    }

                    System.out.print("New Name: ");
                    String name = scanner.nextLine();

                    System.out.print("New Year: ");
                    int year;

                    try {
                        year = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Year. Please enter a number.");
                        break;
                    }

                    System.out.println("\n=== Available Artists ===");

                    for (Artist artist : artistController.listArtists()) {
                        System.out.println(artist);
                    }

                    System.out.println();

                    System.out.print("New Artist ID: ");
                    int artistId;

                    try {
                        artistId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Artist ID. Please enter a number.");
                        break;
                    }

                    if (albumController.updateAlbum(name, year, artistId, id)) {
                        System.out.println("Album updated successfully.");
                    } else {
                        System.out.println("Failed to update album.");
                    }

                    break;
                }
                case 4: {

                    System.out.println("\nAvailable Albums:");

                    for (Album album : albumController.listAlbums()) {
                        System.out.println(album);
                    }

                    System.out.print("\nEnter Album ID to delete: ");
                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Album ID. Please enter a number.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete this album? (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {

                        if (albumController.deleteAlbum(id)) {
                            System.out.println("Album deleted successfully.");
                        } else {
                            System.out.println("Failed to delete album.");
                        }

                    } else {

                        System.out.println("Delete cancelled.");

                    }

                    break;
                }
                case 5: {

                    System.out.println("=== Archive Album ===");
                    System.out.println("\nAvailable Albums:");

                    for (Album album : albumController.listAlbums()) {
                        System.out.println(album);
                    }

                    System.out.println();

                    System.out.print("Enter Album ID: ");

                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Album ID. Please enter a number.");
                        break;
                    }

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
                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Album ID. Please enter a number.");
                        break;
                    }

                    if (albumController.restoreAlbum(id)) {
                        System.out.println("Album restored successfully.");
                    } else {
                        System.out.println("Failed to restore album.");
                    }

                    break;
                }

                case 7: {

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

