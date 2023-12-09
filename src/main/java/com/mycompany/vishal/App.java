package com.mycompany.vishal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */

// 850 * 550 is the default screen size that was finalized by us
public class App extends Application {
    private static Scene scene2;
    private static Scene scene;
    private static Stage window;
    private static int totalCherry = 0;

    public static void setTotalCherry(int cherry){
        totalCherry += cherry;
    }

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene2 = new Scene(loadFXML("HomeScreen"), 850, 550);
        window.setScene(scene2);
        window.show();
        window.setResizable(false);
    }

    static void switchScene(String fxml) throws IOException {
        scene = new Scene(loadFXML(fxml), 850, 550);
        window.setScene(scene);
    }

    private static Parent loadFXML(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
