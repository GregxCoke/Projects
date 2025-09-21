package com.example.gregfinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;


public class BadGameController {
    Room[][] rooms;
    @FXML
    private Label goldLabel;
    @FXML
    private Label totalGoldLabel;
    private int row;
    private int column;
    public BadGameController(){
        rooms = new Room[10][10];

        for (int rowIndex = 0; rowIndex < 10; rowIndex++ ){
            for ( int columnIndex = 0; columnIndex < 10; columnIndex ++){
                rooms[rowIndex][columnIndex] = new Room();

            }

        }
        row = 0;
        column = 0;
        room = rooms[row][column];


    }


    Room room;

    Player player = new Player();

    NPC monster = new NPC();
    @FXML
    private Button searchButton;
    @FXML
    private Button attackButton;
    @FXML
    private Button runButton;
    @FXML
    private Button sleepButton;
    @FXML
    private TextArea textField;
    @FXML
    private Button northButton;
    @FXML
    private Button southButton;
    @FXML
    private Button eastButton;
    @FXML
    private Button westButton;
    @FXML
    private Label healthLabel;
    @FXML
    private Label strengthLabel;
    @FXML
    private Label dexterityLabel;
    @FXML
    private Label intellectLabel;
    @FXML
    private Label healthPointsLabel;
    @FXML
    private Label strenghtPointsLabel;
    @FXML
    private Label dexterityPointsLabel;
    @FXML
    private Label intellectPointsLabel;
    @FXML
    private Button statRollButton;


    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
        int bigDiceRoll = (int)(Math.random()*20 +1);

        if ( bigDiceRoll < player.getIntellect()){
            player.setTotalGold(player.getTotalGold() + room.getGold());
            room.setGold(0);
            textField.appendText("You found gold!\n");
            statRollButtonClicked(null);
        } else {
            textField.appendText("A monster appeared\n");
            // Spawn NPC or Monster
            monster = new NPC();
        }
    }


    @FXML
    public void attackButtonClicked(ActionEvent actionEvent) {

        int bigDiceRoll = (int) (Math.random() * 20 + 1);

        if (bigDiceRoll >= monster.getDexterity()) {
            monster.setHitPoints(monster.getHitPoints() - player.getStrength() / 3);
            textField.appendText("You hit the monster for " + player.getStrength() / 3 + " damage!\n");

            if (monster.getHitPoints() <= 0) {
                textField.appendText("You defeated the monster!\n");
            } else if (monster.getHitPoints() > 0 ) {
                player.setHealth(player.getHealth() - monster.getStrenght() / 2);
                textField.appendText("Monster hit you for " + monster.getStrenght() / 2 + " damage! \n");
                healthPointsLabel.setText("" + player.getHealth());
            }
        } else {
            textField.appendText("You missed & suck at fighting\n");
        }
        if ( player.getHealth() <=0) {
            gameOver();
        }


    }


    @FXML
    public void runButtonClicked(ActionEvent actionEvent) {
        textField.appendText("You ran like a sissy!\n");
        Random run = new Random();
        int runNumber = run.nextInt(1,21);
        if (runNumber < monster.getIntelligence()) {
            player.setHealth(player.getHealth() - monster.getStrenght() / 2);
            textField.appendText("You have been hit for " + monster.getStrenght() / 2 + " damage");
            statRollButtonClicked(null);
        }
        if ( player.getHealth() <=0) {
            gameOver();
        }

    }

    @FXML
    public void sleepButtonClicked(ActionEvent actionEvent) {

        if ( player.getHealth() < 20 ) {
            player.setHealth(20);

        }else {
            monster = new NPC();
            textField.appendText("Monster hit you for " + monster.getStrenght() / 2 + " damage! \n");
            healthPointsLabel.setText("" + player.getHealth());
            player.setHealth(player.getHealth() - monster.getStrenght() / 2);
        }
        if ( player.getHealth() <=0) {
            gameOver();
        }

    }



    @FXML
    public void northButtonClicked(ActionEvent actionEvent) {
        if (!(row == 0)) {
            if(rooms[row-1][column].isBlocked()){
                textField.appendText("The way is blocked\n");
            } else {
                row -= 1;
                room = rooms[row][column];
                textField.appendText("You went North!\n");
            }
        } else {
            textField.appendText("You hit a wall bro, choose another direction\n");
        }


    }


    @FXML
    public void southButtonClicked(ActionEvent actionEvent) {
        if (!(row == 9)) {
            if(rooms[row+1][column].isBlocked()) {
                textField.appendText("The way is blocked\n");
            } else {
                row += 1;
                room = rooms[row][column];
                textField.appendText("You went South!\n");
            }
        } else {
            textField.appendText("You hit a wall bro, choose another direction\n");
        }

    }

    @FXML
    public void eastButtonClicked(ActionEvent actionEvent) {
        if (!(column == 9)) {
            if(rooms[row][column+1].isBlocked()) {
                textField.appendText("The way is blocked\n");
            } else {
                column += 1;
                room = rooms[row][column];
                textField.appendText("You went East!\n");
            }
        } else {
            textField.appendText("You hit a wall bro, choose another direction\n");
        }
    }

    @FXML
    public void westButtonClicked(ActionEvent actionEvent) {
        if (!(column == 0)) {
            if(rooms[row][column-1].isBlocked()) {
                textField.appendText("The way is blocked\n");
            } else {
                column -= 1;
                room = rooms[row][column];
                textField.appendText("You went West!\n");
            }
        } else {
            textField.appendText("You hit a wall bro, choose another direction\n");
        }

    }


    @FXML
    public void statRollButtonClicked(ActionEvent actionEvent) {

        strenghtPointsLabel.setText(""+ player.getStrength());
        dexterityPointsLabel.setText("" + player.getDexterity());
        intellectPointsLabel.setText("" + player.getIntellect());
        healthPointsLabel.setText("" + player.getHealth());
        totalGoldLabel.setText("" + player.getTotalGold());
    }

    // I specifically asked ChatGPT to help me end and restart my game here.
    // this was a cool alert pop up window so i kept it
    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("You lost all your health points!");
        alert.setContentText("Do you want to play again?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesButton) {
            restartGame();



        } else {
            System.exit(0);
        }
    }
    public void restartGame() {
        // Reset player stats
        player.setHealth(0);
        player.setTotalGold(0);

        // Reset current room to the first room in the array
        row = 0;
        column = 0;
        room = rooms[row][column];


        // Print message to inform the player that the game has been restarted
        System.out.println("The game has been restarted. You are back at the beginning.");
    }



}