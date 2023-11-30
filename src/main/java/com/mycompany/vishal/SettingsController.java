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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SettingsController {

    @FXML
    private Button restartButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label settingsLabel;

    @FXML
    private Button GoToHomeScreen;

    @FXML
    private CheckBox dontSaveCheckBox;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private CheckBox muteMusicCheckBox;

    @FXML
    private Slider levelSlider;

    @FXML
    private Text levelText;

    @FXML
    private void initialize() {
        System.out.println("Now i am in SettingsController.java");
        restartButton.setOnAction(event -> {
        });

        goBackButton.setOnAction(event -> {
            try {
                App.demo("MainPlayScreen");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        quitButton.setOnAction(event -> {
            System.out.println("Exited the Game");
            System.exit(0);
        });

        GoToHomeScreen.setOnAction(event -> {
            System.out.println("Moving To Home Screen");
            try {
                App.demo("MenuScreen");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        dontSaveCheckBox.setOnAction(event -> {
        });

        muteMusicCheckBox.setOnAction(event -> {
        });

        levelSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.doubleValue();
            levelText.setText("Level: " + value);
        });
    }
}
