package com.joysistvi.recording;

import com.joysistvi.recording.config.DbConnection;
import java.util.Scanner;
import com.joysistvi.recording.controller.LoginController;
import com.joysistvi.recording.view.AlbumUserView;


import com.joysistvi.recording.repository.LoginRepository;
import com.joysistvi.recording.repository.LoginRepositoryImpl;

import com.joysistvi.recording.service.LoginService;

import com.joysistvi.recording.view.*;

import com.joysistvi.recording.controller.AlbumController;
import com.joysistvi.recording.controller.ArtistController;
import com.joysistvi.recording.controller.SongController;
import com.joysistvi.recording.controller.UserController;

import com.joysistvi.recording.repository.AlbumRepository;
import com.joysistvi.recording.repository.ArtistRepository;
import com.joysistvi.recording.repository.SongRepository;
import com.joysistvi.recording.repository.UserRepository;

import com.joysistvi.recording.repository.AlbumRepositoryImpl;
import com.joysistvi.recording.repository.ArtistRepositoryImpl;
import com.joysistvi.recording.repository.SongRepositoryImpl;
import com.joysistvi.recording.repository.UserRepositoryImpl;

import com.joysistvi.recording.service.AlbumService;
import com.joysistvi.recording.service.ArtistService;
import com.joysistvi.recording.service.SongService;
import com.joysistvi.recording.service.UserService;

import com.joysistvi.recording.controller.PlaylistController;
import com.joysistvi.recording.repository.PlaylistRepository;
import com.joysistvi.recording.repository.PlaylistRepositoryImpl;
import com.joysistvi.recording.service.PlaylistService;

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