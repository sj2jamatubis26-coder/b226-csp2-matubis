package com.joysistvi.recording;

import com.joysistvi.recording.config.DbConnection;
import java.util.Scanner;

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

import com.joysistvi.recording.view.AlbumView;
import com.joysistvi.recording.view.ArtistView;
import com.joysistvi.recording.view.SongView;
import com.joysistvi.recording.view.UserView;

import com.joysistvi.recording.controller.PlaylistController;
import com.joysistvi.recording.repository.PlaylistRepository;
import com.joysistvi.recording.repository.PlaylistRepositoryImpl;
import com.joysistvi.recording.service.PlaylistService;
import com.joysistvi.recording.view.PlaylistView;

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

        AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
        AlbumService albumService = new AlbumService(albumRepository);
        AlbumController albumController = new AlbumController(albumService);
        AlbumView albumView = new AlbumView(albumController, artistController);

        SongView songView = new SongView(songController, albumController);

        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        UserView userView = new UserView(userController);

        PlaylistRepository playlistRepository = new PlaylistRepositoryImpl(dbConnection);
        PlaylistService playlistService = new PlaylistService(playlistRepository);
        PlaylistController playlistController = new PlaylistController(playlistService);
        PlaylistView playlistView = new PlaylistView(playlistController, userController);

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {

            System.out.println("==================================");
            System.out.println(" Recording Studio Management ");
            System.out.println("==================================");
            System.out.println("1. Song");
            System.out.println("2. Artist");
            System.out.println("3. Album");
            System.out.println("4. User");
            System.out.println("5. Playlist");
            System.out.printf("6. Exit\n");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }

            switch (choice) {
                case 1:
                    songView.showMenu();
                    break;

                case 2:
                    artistView.showMenu();
                    break;

                case 3:
                    albumView.showMenu();
                    break;

                case 4:
                    userView.showMenu();
                    break;

                case 5:
                    playlistView.showMenu();
                    break;

                case 6:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

    }
}