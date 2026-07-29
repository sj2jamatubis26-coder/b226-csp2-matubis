package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.AlbumController;
import com.joysistvi.recording.model.Album;

import java.util.List;
import java.util.Scanner;

public class AlbumUserView {

    private final AlbumController albumController;
    private final Scanner scanner = new Scanner(System.in);


    public AlbumUserView(AlbumController albumController) {
        this.albumController = albumController;
    }


    public void showMenu() {

        int choice;

        do {

            System.out.println("\n===== ALBUMS =====");
            System.out.println("1. View Albums");
            System.out.println("2. Search Albums");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");


            try {

                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input.");
                choice = -1;

            }


            switch(choice) {

                case 1:

                    List<Album> albums = albumController.listAlbums();

                    if (albums.isEmpty()) {

                        System.out.println("No albums found.");

                    } else {

                        for (Album album : albums) {
                            System.out.println(album);
                        }

                    }

                    break;
                case 2:

                    System.out.println("\n===== SEARCH ALBUM =====");

                    System.out.print("Enter album name: ");
                    String keyword = scanner.nextLine();


                    List<Album> searchResults = albumController.searchAlbums(keyword);


                    if (searchResults.isEmpty()) {

                        System.out.println("No albums found.");

                    } else {

                        System.out.println("\n===== SEARCH RESULT =====");

                        for (Album album : searchResults) {
                            System.out.println(album);
                        }
                    }

                case 0:
                    break;


                default:
                    System.out.println("Invalid choice.");

            }


        } while(choice != 0);

    }
}