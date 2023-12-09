/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vishal;

/**
 *
 * @author visha
 */
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MenuScreenController {

    @FXML
    private Button playButton;
    
    @FXML
    private Button highestScoreButton;
    
    @FXML
    private Button settingsButton;
    
    @FXML
    private Label titleLabel;


    @FXML
    public void initialize() {
        // Add initialization logic here if needed
        playButton.setOnAction(event -> {
            System.out.println("Play button clicked!");
            try {
                App.switchScene("MainPlayScreen");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        

        highestScoreButton.setOnAction(event -> {
            System.out.println("Highest score button clicked!");
            try {
                App.switchScene("HighScore");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        settingsButton.setOnAction(event -> {
            System.out.println("Settings button clicked!");
            try {
                App.switchScene("SettingsPage");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
