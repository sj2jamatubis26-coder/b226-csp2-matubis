package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.SongController;
import com.joysistvi.recording.model.Song;

import java.util.List;
import java.util.Scanner;

public class SongUserView {

    private final SongController songController;
    private final SongUserSearchView songUserSearchView;

    private final Scanner scanner = new Scanner(System.in);


    public SongUserView(SongController songController,
                        SongUserSearchView songUserSearchView) {

        this.songController = songController;
        this.songUserSearchView = songUserSearchView;
    }


    public void showMenu() {

        int choice;

        do {

            System.out.println("\n===== SONGS =====");
            System.out.println("1. View Songs");
            System.out.println("2. Search Songs");
            System.out.println("0. Back");

            System.out.print("Enter choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please enter a number.");
                choice = -1;

            }


            switch(choice) {

                case 1:

                    List<Song> songs = songController.listSongs();

                    for(Song song : songs) {
                        System.out.println(song);
                    }

                    break;


                case 2:

                    songUserSearchView.searchSongs();

                    break;


                case 0:
                    break;


                default:
                    System.out.println("Invalid choice.");

            }


        } while(choice != 0);

    }
}