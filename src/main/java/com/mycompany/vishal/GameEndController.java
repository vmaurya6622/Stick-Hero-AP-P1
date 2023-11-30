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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameEndController {

    @FXML
    private Pane gameOverPane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Text gameOverText;

    @FXML
    private Button saveQuitButton;

    @FXML
    private Button restartButton;

    @FXML
    private Text scoreText;

    @FXML
    private Button homeMenuButton;

    @FXML
    private void initialize() {
        saveQuitButton.setOnAction(event -> {
            System.out.println("Save and Quit clicked");
        });

        restartButton.setOnAction(event -> {
            System.out.println("Restart Game clicked");
        });

        homeMenuButton.setOnAction(event -> {
            try {
                App.demo("MenuScreenController");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Go To Home Menu clicked");
        });
    }
}
