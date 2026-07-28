package com.joysistvi.recording.dao;



import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.controller.ArtistController;
import com.joysistvi.recording.repository.ArtistRepository;
import com.joysistvi.recording.repository.ArtistRepositoryImpl;
import com.joysistvi.recording.service.ArtistService;
import com.joysistvi.recording.view.ArtistView;

public class Main {
    public static void main(String[] args) {

        DbConnection dbConnection = new DbConnection();

        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistService(artistRepository);
        ArtistController artistController = new ArtistController(artistService);
        ArtistView artistView = new ArtistView(artistController);

        artistView.showMenu();
    }
}
//
//import com.joysistvi.recording.config.DbConnection;
//import com.joysistvi.recording.controller.SongController;
//import com.joysistvi.recording.repository.SongRepository;
//import com.joysistvi.recording.repository.SongRepositoryImpl;
//import com.joysistvi.recording.service.SongService;
//import com.joysistvi.recording.view.SongView;
//
//public class Main {
//    public static void main(String[] args) {
//
//        DbConnection dbConnection = new DbConnection();
//
//        SongRepository songRepository = new SongRepositoryImpl(dbConnection);
//        SongService songService = new SongService(songRepository);
//        SongController songController = new SongController(songService);
//        SongView songView = new SongView(songController);
//
//        songView.showMenu();
//    }
//}