package com.majeed_ch.coinflip;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class CoinController implements Initializable {
    @FXML
    public Circle coin;
    @FXML
    private Label resultText;
    @FXML
    private Button coinButton;

    // initialize class Random that will be used to generate random integer.
    private final Random RANDOM = new Random();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // This will create a rotation transition animation for the coin Node (a Circle) and
        // set the duration of the animation to x milliseconds.
        int rotateDuration = 500;
        RotateTransition rotate = new RotateTransition(Duration.millis(rotateDuration),coin);
        // repeats the transition x times
        rotate.setCycleCount(3);
        // sets the angle of the rotation
        rotate.setByAngle(360);
        // sets the axis on which the Node rotates
        rotate.setAxis(Rotate.X_AXIS);

        // This creates a fading transition animation for text in the coin and
        // sets the duration of the animation to x milliseconds.
        int fadeDuration = rotateDuration;
        FadeTransition fadeIn = new FadeTransition(Duration.millis(fadeDuration),resultText);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        // this is an action event for the button onClick.
        // it changes the color of text in the coin to transparent, plays the two animations, and
        // decides head or tail based on a random integer, then makes the color white.
        // also, the button will be disabled while the animation is running.
        coinButton.setOnAction(event -> {
            resultText.setStyle("-fx-text-fill: TRANSPARENT");
            // starts the rotation animation
            rotate.playFromStart();
            // generate a random number between 0 (inclusive) and 2 ( exclusive)
            int flipResult = RANDOM.nextInt(2);
            // disables the button, so the user doesn't click while the animation is running.
            coinButton.setDisable(true);
            // if 0 the text will say "Head" if 1 it will say "tail"
            switch (flipResult){
                case 0:
                    resultText.setText("Heads");
                    break;
                case 1:
                    resultText.setText("Tails");
                    break;
                }
            // initilaizes a new Timer Object that will create a schedule
            // the scheduale takes two parameters, TimerTask Object and delay
            // the TimerTask contains run method that will run what's inside it after
            // the specified delay in milliseconds.
            int taskDelay = rotateDuration+fadeDuration+500;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // enables the button
                    coinButton.setDisable(false);
                    // plays the fade animation
                    fadeIn.playFromStart();
                    // sets the color of text so it becomes visible
                    resultText.setStyle("-fx-text-fill: #383838");
                    // terminates the timer
                    timer.cancel();
                }
            }, taskDelay);
            }); // end of event listener
    }
}