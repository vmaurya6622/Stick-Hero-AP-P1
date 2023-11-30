package com.mycompany.vishal;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MainGamePlayEngine {

    @FXML
    private Button Settings;

    @FXML
    private Circle circle1; // Leftmost

    @FXML
    private Circle circle2; // Center

    @FXML
    private Circle circle3; // Rightmost

    @FXML
    private Rectangle scoreBox;

    @FXML
    private Text myName;
    
    @FXML
    private Text scoreText;

    @FXML
    private Pane GamePane;

    @FXML
    private Button MouseClickSense;

    public String Name = "";
    public int currentScore = 0;
    public int LivesRemaining = 3;

    public int getLives() {
        return LivesRemaining;
    }

    public void decreaseLives() {
        LivesRemaining--;
    }

    public void setScore(int score) {
        currentScore = score;
        scoreText.setText("" + currentScore);
    }

    public int getScore() {
        return currentScore;
    }

    public void setEnteredName(String name) {
        Name = name;
        System.out.println("My name is " + Name);
        if (name == null) {
            System.out.println("Checking if null");

//            myName.setText("125642");
        } else {
//            myName.setText("1115522");
        }
    }

    @FXML
    private void initialize() {
        System.out.println("Now I'm In MainGamePlayEngine.java, Welcome: " + Name);
        StickHeroInAction stickHeroGame = new StickHeroInAction(this);
        GamePane.getChildren().add(stickHeroGame);
        stickHeroGame.startGame();
        Settings.setOnAction(event -> {
            System.out.println("Going to settings");
            switchToSettings();
        });
    }

    private void DecreaseLives(int remainingAttempts) {
        switch (remainingAttempts) {
            case 2:
                circle3.setFill(Color.RED);
                break;
            case 1:
                circle2.setFill(Color.RED);
                break;
            case 0:
                circle1.setFill(Color.RED);
                break;
            default:
                break;
        }
    }

    private void switchToSettings() {
        try {
            System.out.println("Loading SettingsPage.fxml...");
            App.demo("SettingsPage");
            System.out.println("SettingsPage.fxml loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error in Opening Settings!");
            e.printStackTrace();
        }
    }

    public class StickHeroInAction extends Pane {

        private TranslateTransition characterMovement;
        private Line stick;
        private boolean isExtending = false;
        private MainGamePlayEngine mainGamePlayEngine;

        public StickHeroInAction(MainGamePlayEngine mainGamePlayEngine) {
            this.mainGamePlayEngine = mainGamePlayEngine;
            createGameContent();
        }

        private void createGameContent() {
            Line stickman = createStickman();
            getChildren().add(stickman);
//            createClickableArea(0, 150, 850, 400);
        }

        // Other methods...
        private Line createStickman() {
            Line stickman = new Line(50, 300, 50, 270);
            stickman.setStrokeWidth(3);
            return stickman;
        }

        public void startGame() {
            System.out.println("Now Initialized StickHeroInAction.java, and the game is running");
            setScore(5);
            MouseClickSense.setOnMousePressed(event -> {
                System.out.println("LeftButton of mouse pressed");
                // Add your logic for mouse pressed event
            });

            MouseClickSense.setOnMouseReleased(event -> {
                System.out.println("LeftButton of mouse released");
                // Add your logic for mouse released event
            });

        }
    }
}
