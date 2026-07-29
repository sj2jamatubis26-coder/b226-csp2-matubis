package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.SongController;
import com.joysistvi.recording.model.Song;

import java.util.List;
import java.util.Scanner;

public class SongUserSearchView {

    private final SongController songController;
    private final Scanner scanner = new Scanner(System.in);


    public SongUserSearchView(SongController songController) {
        this.songController = songController;
    }


    public void searchSongs() {

        System.out.println("\n===== SEARCH SONG =====");

        System.out.print("Enter song title: ");
        String keyword = scanner.nextLine();


        List<Song> songs = songController.searchSongs(keyword);


        if (songs.isEmpty()) {

            System.out.println("No songs found.");

        } else {

            System.out.println("\n===== SEARCH RESULT =====");

            for (Song song : songs) {
                System.out.println(song);
            }
        }
    }
}