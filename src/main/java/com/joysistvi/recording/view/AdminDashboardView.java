package com.joysistvi.recording.view;

import java.util.Scanner;

public class AdminDashboardView {

    private final UserView userView;
    private final ArtistView artistView;
    private final AlbumView albumView;
    private final SongView songView;

    private final Scanner scanner = new Scanner(System.in);

    public AdminDashboardView(UserView userView,
                              ArtistView artistView,
                              AlbumView albumView,
                              SongView songView) {

        this.userView = userView;
        this.artistView = artistView;
        this.albumView = albumView;
        this.songView = songView;
    }

    public void showMenu() {

        int choice;

        do {

            System.out.println("\n===== ADMIN DASHBOARD =====");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Artists");
            System.out.println("3. Manage Albums");
            System.out.println("4. Manage Songs");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }

            switch (choice) {

                case 0:
                    System.out.println("Logging out...");
                    break;

                case 1:
                    userView.showMenu();
                    break;

                case 2:
                    artistView.showMenu();
                    break;

                case 3:
                    albumView.showMenu();
                    break;

                case 4:
                    songView.showMenu();
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

}