package com.mycompany.vishal;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.io.IOException;

import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.transform.Rotate;

public class MainGamePlayEngine {
    private static String enteredName;

    @FXML
    private Pane GamePane;

    @FXML
    private Text cherryText;

    @FXML
    private Text playerNameText;

    @FXML
    private Text playerScoreText;

    @FXML
    private Button arrowButton;

    private final double screenWidth = 850.0;
    private final double screenHeight = 550.0;
    private Line stick;

    @FXML
    private Pane pane;
    private ScheduledExecutorService scheduler;
    private static int time = 100;  // for inceasing difficulty level / speed for increasing sticklength
    private double initialX = 55;
    private double initialY = screenHeight - 250 + 2; // - mai pillar Height karna hai baad mai
    private double stickLength;
    private int stickFellSpeed = 2000;   // in millisec
    private int stickWidth = 5;
    private final double pillarHeight = 250;
    private Rectangle pillar;
    private Rectangle redBox;
    private double pillarWidth;
    private double pillarX = 0;
    private final double pillarY = screenHeight-pillarHeight;
    private final double pillarMaxWidth = 100;
    private boolean eventHandlersEnabled = true;
    private ImageView imageView;
    private int score = 0;
    private boolean downKeyPressed = false;
    private boolean upDownAllowed = false;

    private ImageView imageViewCherry;

    private boolean eatsCherry = false;
    private boolean hitsPillar = false;
    private Timeline cherryTimeline;
    private Timeline pillarTimeline;

    public static void setEnteredName(String name) {
        enteredName = name;
        System.out.println("Reached here");
    }

    public  void increaseCherryText(){
        String s = String.valueOf(Integer.valueOf(cherryText.getText()) + 1);
        cherryText.setText(s);
    }

    @FXML
    private void initialize() {
        System.out.println("Now Here");

        pane.setOnMousePressed(this::extendStick);
        pane.setOnMouseReleased(this::releaseStick);

        StartGame();

    }

    private void StartGame(){

        playerNameText.setText(enteredName);
        stick = CreateStick();
        pane.getChildren().add(stick);

        System.out.println("Player Name : " + enteredName);

        try {
            String imagePath = "Stickhero.png"; // Adjust the path based on your package structure
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView = new ImageView(image);
            imageView.setFitWidth(50); // Set the desired width
            imageView.setFitHeight(60); // Set the desired height
            imageView.setLayoutX(0); // X coordinate
            imageView.setLayoutY(pillarY - 60); // Y coordinate

            imagePath = "cherry.png";
            Image cherry = new Image(getClass().getResourceAsStream(imagePath));
            imageViewCherry = new ImageView(cherry);
            imageViewCherry.setFitWidth(30);
            imageViewCherry.setFitHeight(30);
            imageViewCherry.setLayoutX(-100);
            imageViewCherry.setLayoutY(pillarY +10);

            pane.getChildren().add(imageViewCherry);
            pane.getChildren().add(imageView);

            pillarWidth = 60;
            pillar = createPillar(pillarX, pillarY, pillarWidth, pillarHeight);
            pillar.setFill(Color.BLACK); // Set pillar color (you can customize this)
            pane.getChildren().addAll(pillar);
            generateRandomRectangularPillar(pane);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    private Line CreateStick(){
        Line newstick = new Line(initialX, initialY, initialX, initialY); // Initial stickman position
        newstick.setStrokeWidth(stickWidth);    // Set the desired width
        return newstick;
    }

    public static void setDifficulty(){
        if ((int)SettingsController.getSpeed() >=1 && (int)SettingsController.getSpeed() <=10 ){
            time = (int)(100/(int) SettingsController.getSpeed() );
            System.out.println("Value of variable time = " + time);
        }
    }

    private void extendStick(MouseEvent event) {
        if (!eventHandlersEnabled){
            if (downKeyPressed && upDownAllowed){
                System.out.println("Up arrow key pressed");
                Rotate rotate = new Rotate(180, Rotate.X_AXIS);
                imageView.getTransforms().add(rotate);
                imageView.setLayoutY( screenHeight - pillarHeight -60);
                downKeyPressed = false;
            }
            else if (!downKeyPressed && upDownAllowed){
                System.out.println("Down arrow key pressed");
                Rotate rotate = new Rotate(180, Rotate.X_AXIS);
                imageView.getTransforms().add(rotate);
                imageView.setLayoutY( screenHeight - pillarHeight + 60);
                downKeyPressed = true;
            }
            else{
                System.out.println("No Actions are allowed...");
            }
            return; // Do nothing if event handlers are disabled
        }
        setDifficulty();
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {stick.setStartY(stick.getStartY() - 10);}, 0, time, TimeUnit.MILLISECONDS);
    }

    private void releaseStick(MouseEvent event) {
        if (!eventHandlersEnabled) {
            return;
        }
        else if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
        eventHandlersEnabled = false;
        rotateLine();
    }

    private void rotateLine() {

        Transition rotate = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            @Override
            protected void interpolate(double frac) {
                double angle = frac * 90.0;
                Rotate rotation = new Rotate(angle, stick.getEndX(), stick.getEndY());
                stick.getTransforms().setAll(rotation);
            }
        };

        rotate.play();
        rotate.setOnFinished(event -> {
            System.out.println("Animation finished. Continuing with the rest of the code.");
            moveImageWithAnimation(imageView , pane);
        });
    }

    private double getLength() {
        double length = stick.getEndY() - stick.getStartY();
        return length;
    }

    private void generateRandomRectangularPillar(Pane pane) {
        Random random = new Random();
//        pillarX += pillarWidth + 100 + 1;  // Center the pillar horizontally
        pillarX += random.nextInt((int)(screenWidth - (3*pillarMaxWidth))) + pillarWidth + 120 ;  // Center the pillar horizontally
        pillarWidth = random.nextInt(40)  + 60 + 1; // Random width between 60 and 100
        System.out.println("Pillar Created.Continuing with the rest of the code.");

        pillar = createPillar(pillarX, pillarY, pillarWidth, pillarHeight);
        pillar.setFill(Color.BLACK); // Set pillar color (you can customize this)

        redBox = createPillar(pillarX + pillarWidth/3.0,pillarY,pillarWidth/3.0,5.0);
        redBox.setFill(Color.RED);

        pane.getChildren().addAll(pillar,redBox);
        generateRandomCherry();
    }

    public void generateRandomCherry(){
        Random random = new Random();
        int probability = random.nextInt(2);
        if (probability>=1){
            int position = random.nextInt((int)pillar.getX() - (int)stick.getStartX() - 80) + (int) stick.getStartX() + 20;
            imageViewCherry.setLayoutX(position);
        }
        else{
            System.out.println("Cherry Not created this time...");
        }
    }

    private Rectangle createPillar(double x,double y,double width,double height){
        return new Rectangle(x,y,width,height);
    }

    private void movePaneBackwardWithAnimation(Pane pane) {

        double translationDistance = -1*pillarX; // Negative value to move backward (left)

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), pane);
        translateTransition.setToX(translationDistance);

        translateTransition.setOnFinished(event -> {
            System.out.println("Pane moved backward. Continuing with the rest of the code.");
            initialX = (pillarX + pillarWidth-2);
            stick = CreateStick();
            pane.getChildren().add(stick);
            generateRandomRectangularPillar(pane);
            eventHandlersEnabled = true;
        });

        translateTransition.play();
    }

    private void moveImageWithAnimation(ImageView imageView, Pane pane) {
        upDownAllowed = true;
        System.out.println("stick (x1,x2) : " + stick.getStartX() +", " + stick.getEndX());
        System.out.println("stick (y1,y2) : " + stick.getStartY() +", " + stick.getEndY());
        System.out.println("Stick length: " + getLength());
        System.out.println("Pillar (x,y) : " + pillar.getX() + ", " + pillar.getY());
        System.out.println("Required Sum: " + (stick.getStartX() + getLength()) + ", Range: (" + pillar.getX() + ", " + (pillar.getX() + pillarWidth) + ")" );

        double requiredSum = (stick.getStartX() + getLength());
        double translationDistanceX ;
        double translationDistanceY = screenHeight;

        if ( requiredSum >= pillar.getX() && requiredSum <= (pillar.getX() + pillarWidth)){
            score += computeScore();
            playerScoreText.setText(String.valueOf(score));

            // game will continue normally
            translationDistanceX = pillarX + pillarWidth - 60;
            System.out.println("Continuing the game....");

            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), imageView);
            translateTransition.setToX(translationDistanceX);
            translateTransition.setOnFinished(event -> {
                System.out.println("Image moved. Continuing with the rest of the code.");
                upDownAllowed = false;
                cherryTimeline.stop();
                pillarTimeline.stop();
                eatsCherry = false;
                movePaneBackwardWithAnimation(pane);
            });
            translateTransition.play();
            upDownAllowed = true;
            checkCollisionWithCherry();
            checkCollisionWithPillar(translateTransition,translationDistanceY);
            return;
        }
        else if(getLength() >= 850){
            // gameover and show animation of only 850 and then terminate the game
            translationDistanceX = stick.getStartX() + 850;
            System.out.println("GameOver1....");
        }
        else{
            translationDistanceX = requiredSum - 50;
            System.out.println("Translation in x : " + translationDistanceX);
            System.out.println("GameOver2....");
        }

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), imageView);
        translateTransition.setToX(translationDistanceX);
        translateTransition.setOnFinished(event -> {
            upDownAllowed = false;
            cherryTimeline.stop();
            pillarTimeline.stop();
            System.out.println("Image moved. Continuing with the rest of the code.");
            gameOver(translationDistanceY);
        });
        translateTransition.play();
        upDownAllowed = true;
        checkCollisionWithCherry();
        checkCollisionWithPillar(translateTransition, translationDistanceY);
    }

    private void gameOver(double translationDistanceY){
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(2), imageView);
        translateTransition2.setToY(translationDistanceY);
        translateTransition2.play();

        pane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this::extendStick);
        pane.removeEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseStick);

        translateTransition2.setOnFinished(event2->{
            try {
                GameEndController.setfinalScore(score);
                GameEndController.setfinalCherry(Integer.valueOf(cherryText.getText()));
                GameEndController.setPlayerName(enteredName);

                App.switchScene("GameEnd");
            } catch (IOException e) {
                System.out.println("Error in Loading the EndScreen...");;
            }
        });
    }

    private int computeScore(){
        double stickFellPosition = stick.getStartX() + getLength();
        double lowerBound = pillar.getX() + pillarWidth/3.0;
        double upperBound = pillar.getX() + pillarWidth/3.0 + pillarWidth/3.0;
        if (stickFellPosition >= lowerBound &&  stickFellPosition <= upperBound){
            return 2;
        }
        return 1;
    }

    private void checkCollisionWithCherry() {
        if(!eatsCherry) {
            cherryTimeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
                Bounds imageViewBounds = imageView.getBoundsInParent();
                Bounds cherryBounds = imageViewCherry.getBoundsInParent();
                if (imageViewBounds.intersects(cherryBounds) && downKeyPressed) {
                    eatsCherry = true;
                    cherryTimeline.stop();
                    increaseCherryText();
                    imageViewCherry.setLayoutX(-50);
                    System.out.println("Cherry Collected...");
                }
            }));

            cherryTimeline.setCycleCount(Timeline.INDEFINITE);
            cherryTimeline.play();
        }
    }

    private void checkCollisionWithPillar(TranslateTransition translateTransition, double translateTransitionY) {
        if(!hitsPillar) {
            pillarTimeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
                Bounds imageViewBounds = imageView.getBoundsInParent();
                Bounds pillarBounds = pillar.getBoundsInParent();

                if (imageViewBounds.intersects(pillarBounds) && downKeyPressed) {
                    hitsPillar = true;
                    pillarTimeline.stop();
                    translateTransition.stop();
//                    System.out.println(imageView.getX() + ", " + imageView.getY());
//                    System.out.println(imageViewBounds.getMaxX() + " " + imageViewBounds.getMinX());
//                    System.out.println(pillarBounds.getMaxX() + " " + pillarBounds.getMinX());
                    imageView.setLayoutY(imageViewBounds.getMinX());
                    imageView.setLayoutY(pillarY + 60);
                    gameOver(translateTransitionY);
                    System.out.println("StickHero collides with the pillar...");
                }
            }));

            pillarTimeline.setCycleCount(Timeline.INDEFINITE);
            pillarTimeline.play();
        }
    }

}