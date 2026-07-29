package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.ArtistController;
import com.joysistvi.recording.model.Artist;

import java.util.List;
import java.util.Scanner;

public class ArtistUserView {

    private final ArtistController artistController;
    private final Scanner scanner = new Scanner(System.in);


    public ArtistUserView(ArtistController artistController) {
        this.artistController = artistController;
    }


    public void showMenu() {

        int choice;

        do {

            System.out.println("\n===== ARTISTS =====");
            System.out.println("1. View Artists");
            System.out.println("2. Search Artists");
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

                    List<Artist> artists = artistController.listArtists();


                    if (artists.isEmpty()) {

                        System.out.println("No artists found.");

                    } else {

                        for (Artist artist : artists) {
                            System.out.println(artist);
                        }

                    }

                    break;



                case 2:

                    System.out.println("\n===== SEARCH ARTIST =====");

                    System.out.print("Enter artist name: ");
                    String keyword = scanner.nextLine();


                    List<Artist> searchResults =
                            artistController.searchArtists(keyword);


                    if (searchResults.isEmpty()) {

                        System.out.println("No artists found.");

                    } else {

                        System.out.println("\n===== SEARCH RESULT =====");

                        for (Artist artist : searchResults) {
                            System.out.println(artist);
                        }
                    }

                    break;



                case 0:
                    break;


                default:

                    if (choice != -1) {
                        System.out.println("Invalid choice.");
                    }

            }


        } while(choice != 0);

    }
}