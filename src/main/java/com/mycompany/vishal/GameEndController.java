/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vishal;

/**
 *
 * @author visha
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import static java.lang.System.exit;

public class GameEndController {

    @FXML
    private Button saveQuitButton;

    @FXML
    private Button restartButton;

    @FXML
    private Button homeMenuButton;

    @FXML
    private Text scoreText;

    @FXML
    private Text cherryCollectedText;

    private static int finalScore;
    private static int finalCherry;
    private static String playerName;

    public static void setfinalScore(int score){
        finalScore = score;
    }
    public static void setfinalCherry(int cherry){
        finalCherry = cherry;
    }
    public static void setPlayerName(String name){
        playerName = name;
    }

    @FXML
    private void initialize() {

        String scoreString = Integer.toString(finalScore);
        scoreText.setText(scoreString);
        String cherryString = Integer.toString(finalCherry);
        cherryCollectedText.setText(cherryString);


        ArrayList<String> list = getList();
        if (Integer.valueOf(list.get(0)) < finalScore){
            list.set(0,scoreString);
            list.set(1,cherryString);
            list.set(2,playerName);
        }
        else if(Integer.valueOf(list.get(0)) == finalScore && Integer.valueOf(list.get(1)) < finalCherry){
            list.set(1,cherryString);
            list.set(2,playerName);
        }

        setList(list);

        saveQuitButton.setOnAction(event -> {
            ArrayList<String> newlist = getList();
            newlist.set(3,scoreString);
            newlist.set(4,cherryString);
            newlist.set(5,playerName);
            setList(newlist);
            System.out.println("GameSaved Successfully...");
        });

        restartButton.setOnAction(event -> {
            System.out.println("Restart Game clicked");
            try {
                App.switchScene("MainPlayScreen");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        homeMenuButton.setOnAction(event -> {
            System.out.println("Go To Menu Screen Clicked");
            try {
                App.switchScene("MenuScreen");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    public static ArrayList<String> getList(){
        File myFile;
        Scanner sc;
        ArrayList<String> list = new ArrayList<>();

        try {
            myFile = new File("HighScore.txt");
            sc = new Scanner(myFile);

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                list.add(line);
            }
//            System.out.println(list);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

    public static void setList(ArrayList<String> list){
        // Code to write to a file
         try{
             FileWriter myFileWriter = new FileWriter("HighScore.txt");
             for(int i = 0 ; i< list.size() ; i++){
                 myFileWriter.write(list.get(i) + "\n");
             }
             myFileWriter.close();
         }
         catch(Exception e){
             System.out.println("Error: " + e);;
         }
    }
}
