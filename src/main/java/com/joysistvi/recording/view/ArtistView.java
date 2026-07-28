package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.ArtistController;
import com.joysistvi.recording.model.Artist;

import java.util.List;
import java.util.Scanner;

public class ArtistView {

    private final ArtistController artistController;
    private final Scanner scanner = new Scanner(System.in);

    // Constructor injection
    public ArtistView(ArtistController artistController) {
        this.artistController = artistController;
    }

    public void showMenu() {
        int choice = -1;
        do {
            System.out.println("\n--- Artist Menu ---");
            System.out.println("1. Add Artist");
            System.out.println("2. View All Artist");
            System.out.println("3. Update Artist");
            System.out.println("4. Delete Artist");
            System.out.println("5. Archive Artist");
            System.out.println("6. Restore Artist");
            System.out.println("7. Search Artist");
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
                    System.out.println("=== Add Artist ===");

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    if (artistController.addArtist(name)) {
                        System.out.println("Artist added successfully.");
                    } else {
                        System.out.println("Failed to add artist.");
                    }

                    break;
                }
                case 2: {
                    System.out.println("View All Artists");

                    for (Artist artist : artistController.listArtists()) {
                        System.out.println(artist);
                    }
                    break;
                }

                case 3: {

                    System.out.println("=== Update Artist ===");
                    System.out.println("\nAvailable Artists:");

                    for (Artist artist : artistController.listArtists()) {
                        System.out.println(artist);
                    }

                    System.out.println();
                    System.out.print("Enter Artist ID: ");

                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Artist ID. Please enter a number.");
                        break;
                    }

                    System.out.print("Enter New Artist Name: ");
                    String name = scanner.nextLine();

                    if (artistController.updateArtist(name, id)) {
                        System.out.println("Artist updated successfully.");
                    } else {
                        System.out.println("Failed to update artist.");
                    }

                    break;
                }
                case 4: {

                    System.out.println("\nAvailable Artists:");

                    for (Artist artist : artistController.listArtists()) {
                        System.out.println(artist);
                    }

                    System.out.print("\nEnter Artist ID to delete: ");
                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Artist ID. Please enter a number.");
                        break;
                    }

                    System.out.print("Are you sure you want to delete this artist? (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {

                        if (artistController.deleteArtist(id)) {
                            System.out.println("Artist deleted successfully.");
                        } else {
                            System.out.println("Failed to delete artist.");
                        }

                    } else {

                        System.out.println("Delete cancelled.");

                    }

                    break;
                }
                case 5: {

                    System.out.println("=== Archive Artist ===");
                    System.out.println("\nAvailable Artists:");

                    for (Artist artist : artistController.listArtists()) {
                        System.out.println(artist);
                    }

                    System.out.println();
                    System.out.print("Enter Artist ID: ");

                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Artist ID. Please enter a number.");
                        break;
                    }

                    if (artistController.archiveArtist(id)) {
                        System.out.println("Artist archived successfully.");
                    } else {
                        System.out.println("Failed to archive artist.");
                    }

                    break;
                }
                case 6: {

                    System.out.println("=== Restore Artist ===");
                    System.out.println("\nArchived Artists:");

                    for (Artist artist : artistController.listArchivedArtists()) {
                        System.out.println(artist);
                    }

                    System.out.print("Enter Artist ID: ");
                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Artist ID. Please enter a number.");
                        break;
                    }

                    if (artistController.restoreArtist(id)) {
                        System.out.println("Artist restored successfully.");
                    } else {
                        System.out.println("Failed to restore artist.");
                    }

                    break;
                }
                case 7: {

                    System.out.println("=== Search Artist ===");

                    System.out.print("Enter artist name: ");
                    String keyword = scanner.nextLine();

                    List<Artist> artists = artistController.searchArtists(keyword);

                    if (artists.isEmpty()) {
                        System.out.println("No artists found.");
                    } else {

                        System.out.println("\nSearch Result:");

                        for (Artist artist : artists) {
                            System.out.println(artist);
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
