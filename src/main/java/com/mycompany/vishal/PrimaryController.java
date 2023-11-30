package com.mycompany.vishal;

/**
 *
 * @author Vishal Kumar Maurya
 */
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
    private Pane GamePane;

    @FXML
    private Button ResetButton;

    @FXML
    private Button QuitButton;

    

    ;
    
    @FXML
    private void initialize() {
        SubmitButton.setOnAction(event -> {
            String enteredName = InputName.getText();
            switchToMainGamePlayEngine(enteredName);//comment it out

//            if ("".equalsIgnoreCase(enteredName)) {
//                System.out.println("Name cannot be Empty!\n\nRETRY\n");
//            } else {
//                System.out.println("Entered Name: " + enteredName);
//                switchToMainGamePlayEngine(enteredName);
//            }

        });

        ResetButton.setOnAction(event -> {
            InputName.clear();
        });

        QuitButton.setOnAction(event -> {
            System.out.println("Exited Successfully!");
            System.exit(0);
        });
    }

    private void switchToMainGamePlayEngine(String name) {
        MenuScreenController screenController = new MenuScreenController();
        screenController.setName(name);
        try {
            System.out.println("Loading MenuScreen.fxml...");
            App.demo("MenuScreen");
            System.out.println("MenuScreen.fxml loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error in starting the game!");
            e.printStackTrace();
        }
    }
}
