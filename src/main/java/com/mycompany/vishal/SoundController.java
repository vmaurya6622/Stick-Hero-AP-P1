//package com.mycompany.vishal;
//
//import javafx.application.Application;
//import javafx.stage.Stage;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.util.Duration;
//import java.io.File;
//
//
//public class SoundController extends Application{
//    @Override
//    public void start(Stage primaryStage) {
//        playMusic("fullGame.mp3");
//    }
//    private void playMusic(String musicFile) {
//        if(musicFile.equals("fullGame.mp3")){
//            Media sound = new Media(new File(musicFile).toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(sound);
//
//            mediaPlayer.play();
//        }
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        mediaPlayer.play();
//    }
//    public static void main(String[] args) {
//        launch(args);
//    }
//}