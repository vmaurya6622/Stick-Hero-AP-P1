/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.vishal;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;

/**
 *
 * @author visha
 */
public class HighestScoreController {
    @FXML
    private Text PreviousGameCherries;
    @FXML
    private Text PreviousGameScore;
    @FXML
    private Text PreviousGamePlayerName;
    @FXML
    private Text HighestCherries;
    @FXML
    private Text HighestScore;
    @FXML
    private Text HighestScorePlayerName;
    @FXML
    private Button GoBackButton;
    @FXML
    private Button QuitButton;


//    public static void setPreviousGameCherries(String name) {
//        PreviousGameCherries = name;
//    }
//    public static void setPreviousGameScore(String name) {
//        PreviousGameScore = name;
//    }
//    public static void setHighestScore(String name) {
//        HighestScore = name;
//    }
//    public static void setHighestCherries(String name) {
//        HighestCherries = name;
//    }

    @FXML
    private void initialize() {
        ArrayList<String> list = GameEndController.getList();
        HighestScore.setText(list.get(0));
        HighestCherries.setText(list.get(1));
        HighestScorePlayerName.setText(list.get(2));
        PreviousGameScore.setText(list.get(3));
        PreviousGameCherries.setText(list.get(4));
        PreviousGamePlayerName.setText(list.get(5));

        GoBackButton.setOnAction(event -> {
            System.out.println("Go Back button clicked!");
            try {
                App.switchScene("MenuScreen");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        QuitButton.setOnAction(event -> {
            System.out.println("Quit button clicked!");
            exit(0);
        });

    }


}
