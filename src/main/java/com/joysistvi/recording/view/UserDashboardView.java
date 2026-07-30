package com.joysistvi.recording.view;


import com.joysistvi.recording.model.User;

import java.util.Scanner;

public class UserDashboardView {

    private final SongUserView songUserView;
    private final AlbumUserView albumUserView;
    private final ArtistUserView artistUserView;
    private final PlaylistUserView playlistUserView;
    private final Scanner scanner = new Scanner(System.in);

    public UserDashboardView(
            SongUserView songUserView,
            AlbumUserView albumUserView,
            ArtistUserView artistUserView,
            PlaylistUserView playlistUserView) {

        this.songUserView = songUserView;
        this.albumUserView = albumUserView;
        this.artistUserView = artistUserView;
        this.playlistUserView = playlistUserView;
    }
    public void showMenu(User user) {

        int choice;

        do {

            System.out.println("\n===== USER DASHBOARD =====");
            System.out.println("1. Browse Songs");
            System.out.println("2. Browse Albums");
            System.out.println("3. Browse Artists");
            System.out.println("4. My Playlists");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }

            switch (choice) {

                case 1:
                    songUserView.showMenu();
                    break;

                case 2:
                    albumUserView.showMenu();
                    break;

                case 3:
                    artistUserView.showMenu();
                    break;

                case 4:
                    System.out.println("Logged in User ID: " + user.getId());
                    playlistUserView.showMenu(user.getId());
                    break;

                case 0:
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}