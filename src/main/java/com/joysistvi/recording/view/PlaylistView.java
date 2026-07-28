package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.PlaylistController;
import com.joysistvi.recording.model.Playlist;
import com.joysistvi.recording.controller.UserController;
import com.joysistvi.recording.model.User;

import java.util.List;
import java.util.Scanner;

public class PlaylistView {

    private final Scanner scanner = new Scanner(System.in);
    private final PlaylistController playlistController;
    private final UserController userController;

    // Constructor injection
    public PlaylistView(PlaylistController playlistController,
                        UserController userController) {

        this.playlistController = playlistController;
        this.userController = userController;
    }


    public void showMenu() {

        int choice;

        do {

            System.out.println("\n--- Playlist Menu ---");
            System.out.println("1. Add Playlist");
            System.out.println("2. View All Playlists");
            System.out.println("3. Update Playlist");
            System.out.println("4. Delete Playlist");
            System.out.println("5. Archive Playlist");
            System.out.println("6. Restore Playlist");
            System.out.println("7. Search Playlist");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Huwag kang tanga!!!. number ang ilagay mo.");
                choice = -1;
            }

            switch (choice) {

                case 0: {
                    System.out.println("Returning to Main Menu...");
                    break;
                }
                case 1: {

                    System.out.println("=== Add Playlist ===");

                    System.out.println("\n=== Available Users ===");

                    List<User> users = userController.listUsers();

                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                        break;
                    }

                    for (User user : users) {
                        System.out.println(user);
                    }

                    System.out.print("\nEnter User ID: ");

                    int userId;

                    try {
                        userId = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid User ID. Please enter a number.");
                        break;
                    }


                    System.out.print("Enter Playlist Name: ");
                    String name = scanner.nextLine();

                    Playlist playlist = new Playlist(userId, name);

                    if (playlistController.addPlaylist(playlist)) {
                        System.out.println("Playlist added successfully.");
                    } else {
                        System.out.println("Failed to add playlist.");
                    }

                    break;
                }

                case 2: {

                    System.out.println("=== View All Playlists ===");

                    List<Playlist> playlists = playlistController.listPlaylists();

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                    } else {
                        for (Playlist playlist : playlists) {
                            System.out.println(playlist);
                        }
                    }

                    break;
                }

                case 3: {

                    System.out.println("=== Update Playlist ===");

                    System.out.println("\nAvailable Playlists:");

                    List<Playlist> playlists = playlistController.listPlaylists();

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                        break;
                    }

                    for (Playlist playlist : playlists) {
                        System.out.println(playlist);
                    }


                    System.out.print("\nEnter Playlist ID to update: ");

                    int id;

                    try {
                        id = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Playlist ID. Please enter a number.");
                        break;
                    }


                    System.out.println("\n=== Available Users ===");

                    List<User> users = userController.listUsers();

                    for (User user : users) {
                        System.out.println(user);
                    }


                    System.out.print("\nEnter User ID: ");

                    int userId;

                    try {
                        userId = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid User ID. Please enter a number.");
                        break;
                    }


                    System.out.print("Enter New Playlist Name: ");
                    String name = scanner.nextLine();


                    Playlist playlist = new Playlist(userId, name);
                    playlist.setId(id);


                    if (playlistController.updatePlaylist(playlist)) {
                        System.out.println("Playlist updated successfully.");
                    } else {
                        System.out.println("Failed to update playlist.");
                    }

                    break;
                }

                case 4: {

                    System.out.println("\n=== Available Playlists ===");

                    List<Playlist> playlists = playlistController.listPlaylists();

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                        break;
                    }

                    for (Playlist playlist : playlists) {
                        System.out.println(playlist);
                    }


                    try {

                        System.out.print("\nEnter Playlist ID to delete: ");
                        int id = Integer.parseInt(scanner.nextLine());


                        System.out.print("Are you sure you want to delete this playlist? (Y/N): ");
                        String confirm = scanner.nextLine();


                        if (confirm.equalsIgnoreCase("Y")) {

                            boolean success = playlistController.deletePlaylist(id);

                            System.out.println(success ?
                                    "Playlist deleted successfully." :
                                    "Delete failed.");

                        } else {

                            System.out.println("Delete cancelled.");

                        }


                    } catch (NumberFormatException e) {

                        System.out.println("Invalid Playlist ID. Please enter a number.");

                    }

                    break;
                }

                case 5: {

                    System.out.println("=== Archive Playlist ===");

                    System.out.println("\nAvailable Playlists:");

                    List<Playlist> playlists = playlistController.listPlaylists();

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                        break;
                    }

                    for (Playlist playlist : playlists) {
                        System.out.println(playlist);
                    }


                    try {

                        System.out.print("\nEnter Playlist ID to archive: ");
                        int id = Integer.parseInt(scanner.nextLine());


                        boolean success = playlistController.archivePlaylist(id);

                        System.out.println(success ?
                                "Playlist archived successfully." :
                                "Archive failed.");


                    } catch (NumberFormatException e) {

                        System.out.println("Invalid Playlist ID. Please enter a number.");

                    }

                    break;
                }

                case 6: {

                    System.out.println("=== Restore Playlist ===");

                    System.out.println("\nArchived Playlists:");

                    List<Playlist> playlists = playlistController.listArchivedPlaylists();

                    if (playlists.isEmpty()) {
                        System.out.println("No archived playlists found.");
                        break;
                    }

                    for (Playlist playlist : playlists) {
                        System.out.println(playlist);
                    }


                    try {

                        System.out.print("\nEnter Playlist ID to restore: ");
                        int id = Integer.parseInt(scanner.nextLine());


                        boolean success = playlistController.restorePlaylist(id);

                        System.out.println(success ?
                                "Playlist restored successfully." :
                                "Restore failed.");


                    } catch (NumberFormatException e) {

                        System.out.println("Invalid Playlist ID. Please enter a number.");

                    }

                    break;
                }

                case 7: {

                    System.out.println("=== Search Playlist ===");

                    System.out.print("Enter Playlist Name: ");
                    String keyword = scanner.nextLine();

                    List<Playlist> playlists = playlistController.searchPlaylists(keyword);

                    if (playlists.isEmpty()) {
                        System.out.println("No playlists found.");
                    } else {

                        System.out.println("\nSearch Result:");

                        for (Playlist playlist : playlists) {
                            System.out.println(playlist);
                        }
                    }

                    break;
                }

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

}