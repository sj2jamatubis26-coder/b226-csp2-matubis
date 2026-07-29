package com.joysistvi.recording.view;


import java.util.Scanner;

public class UserDashboardView {

    private final SongView songUserView;
    private final AlbumView albumUserView;
    private final ArtistUserView artistUserView;
    private final PlaylistView playlistView;

    private final Scanner scanner = new Scanner(System.in);

    public UserDashboardView(
            SongView songView,
            AlbumView albumView,
            ArtistUserView artistUserView,
            PlaylistView playlistView) {

        this.songView = songView;
        this.albumView = albumView;
        this.artistUserView = artistUserView;
        this.playlistView = playlistView;
    }
    public void showMenu() {

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
                    playlistView.showMenu();
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