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

    private static double speed ;

    private void setSpeed(){
        speed = levelSlider.getValue();
    }

    public static double getSpeed(){
        return speed;
    }

    @FXML
    private void initialize() {
        System.out.println("Now i am in SettingsController.java");
        restartButton.setOnAction(event -> {
            try {
                App.switchScene("MainPlayScreen");
            } catch (IOException e) {
                System.out.println("Error : " + e);
                e.printStackTrace();
            }
        });

        goBackButton.setOnAction(event -> {
            try {
                App.switchScene("MenuScreen");
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
                App.switchScene("MenuScreen");
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
            setSpeed();
            MainGamePlayEngine.setDifficulty();
        });
    }
}
