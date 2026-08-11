package com.joysistvi.recording;

import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.controller.*;
import com.joysistvi.recording.repository.*;
import com.joysistvi.recording.service.*;
import com.joysistvi.recording.view.*;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        DbConnection dbConnection = new DbConnection();

        SongRepository songRepository = new SongRepositoryImpl(dbConnection);
        SongService songService = new SongService(songRepository);
        SongController songController = new SongController(songService);

        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistService(artistRepository);
        ArtistController artistController = new ArtistController(artistService);
        ArtistView artistView = new ArtistView(artistController);
        ArtistUserView artistUserView =
                new ArtistUserView(artistController);

        AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
        AlbumService albumService = new AlbumService(albumRepository);
        AlbumController albumController = new AlbumController(albumService);
        AlbumView albumView = new AlbumView(albumController, artistController);
        AlbumUserView albumUserView = new AlbumUserView(albumController);

        SongView songView = new SongView(songController, albumController);
        SongUserSearchView songUserSearchView = new SongUserSearchView(songController);
        SongUserView songUserView = new SongUserView(songController, songUserSearchView);

        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        UserView userView = new UserView(userController);

        LoginRepository loginRepository = new LoginRepositoryImpl(dbConnection);
        LoginService loginService = new LoginService(loginRepository);
        LoginController loginController = new LoginController(loginService);

        PlaylistRepository playlistRepository = new PlaylistRepositoryImpl(dbConnection);
        PlaylistService playlistService = new PlaylistService(playlistRepository);
        PlaylistController playlistController = new PlaylistController(playlistService);
        PlaylistView playlistView = new PlaylistView(playlistController, userController);
        PlaylistUserView playlistUserView =
                new PlaylistUserView(playlistController, songController);


        AdminDashboardView adminDashboardView =
                new AdminDashboardView(
                        userView,
                        artistView,
                        albumView,
                        songView
                );

        UserDashboardView userDashboardView =
                new UserDashboardView(
                        songUserView,
                        albumUserView,
                        artistUserView,
                        playlistUserView
                );
        LoginView loginView =
                new LoginView(
                        loginController,
                        adminDashboardView,
                        userDashboardView
                );


        Scanner scanner = new Scanner(System.in);

        int choice;

        do {

            System.out.println("==================================");
            System.out.println(" Recording Studio Management ");
            System.out.println("==================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }

            switch (choice) {

                case 1:
                    loginView.showMenu();
                    break;

                case 2:
                    userView.addUser();
                    break;

                case 0:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

    }
}