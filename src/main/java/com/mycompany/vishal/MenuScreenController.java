/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vishal;

/**
 *
 * @author Vishal Kumar Maurya
 */
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MenuScreenController {

    @FXML
    private Pane rootPane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Button playButton;

    @FXML
    private Button resumeButton;

    @FXML
    private Button highestScoreButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Label titleLabel;

    private String Name;

    public void setName(String name) {
        Name = name;
    }

    @FXML
    public void initialize() {
        playButton.setOnAction(event -> {
            System.out.println("Play button clicked!");
            MainGamePlayEngine gameEngine = new MainGamePlayEngine();
            gameEngine.setEnteredName(Name);
            try {

                App.demo("MainPlayScreen");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        resumeButton.setOnAction(event -> {
            System.out.println("Resume button clicked!");
        });

        highestScoreButton.setOnAction(event -> {
            System.out.println("Highest score button clicked!");
        });

        settingsButton.setOnAction(event -> {
            System.out.println("Settings button clicked!");
            try {
                App.demo("SettingsPage");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
