package com.mycompany.vishal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.nio.file.Paths;

public class PrimaryController {

    @FXML
    private TextField InputName;

    @FXML
    private Button SubmitButton;

    @FXML
    private Button ResetButton;

    @FXML
    private Button QuitButton;
    MediaPlayer mediaPlayer;
    @FXML
    private void initialize() {
        music();
        SubmitButton.setOnAction(event -> {
            String enteredName = InputName.getText();
            if ("".equalsIgnoreCase(enteredName)) {
                System.out.println("Name cannot be Empty!\nRETRY\n");
            } else {
                System.out.println("Entered Name: " + enteredName);
                switchToMainGamePlayEngine(enteredName);
            }
        });

        ResetButton.setOnAction(event->{
            InputName.setText("");
            System.out.println("The text is Reset");
        });

        QuitButton.setOnAction(event -> {
            System.out.println("Exited Successfully!");
            System.exit(0);
        });
    }

    private void switchToMainGamePlayEngine(String name) {
        MainGamePlayEngine.setEnteredName(name);
        try {
            System.out.println("Loading MainPlayScreen.fxml...");
            App.switchScene("MenuScreen");
            System.out.println("MainPlayScreen.fxml loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error in starting the game!");
            e.printStackTrace();
        }
    }
    public void music() {
        String currentDirectory = System.getProperty("user.dir");
        String s = currentDirectory + System.getProperty("file.separator") + "fullGame.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
