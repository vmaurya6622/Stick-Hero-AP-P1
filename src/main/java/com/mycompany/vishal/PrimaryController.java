package com.mycompany.vishal;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TextField InputName;

    @FXML
    private Button SubmitButton;

    @FXML
    private Button ResetButton;

    @FXML
    private Button QuitButton;

    @FXML
    private void initialize() {
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
}
