package com.example.gregfinalproject;

import java.util.Scanner;

public class Player {

   private int health;
    private int totalGold;
    private int dexterity;
   private int strength;
   private int intellect;



    public Player() {
        health = 20;
        totalGold = 0;
        dexterity = diceRoll();
        strength = diceRoll();
        intellect = diceRoll();
    }

    public Player(int health, int totalGold, int dexterity, int strength, int intellect) {
        this.health = health;
        this.totalGold = totalGold;
        this.dexterity = dexterity;
        this.strength = strength;
        this.intellect = intellect;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {

        this.health = health;
    }

    public int getTotalGold() {

        return totalGold;
    }

    public void setTotalGold(int totalGold) {

        this.totalGold = totalGold;
    }

    public int getDexterity() {
        return dexterity;
    }



    public int getStrength() {
        return strength;
    }



    public int getIntellect() {
        return intellect;
    }




    public int diceRoll (){
        int die1 = (int)(Math.random()*6 +1);
        int die2 = (int)(Math.random()*6 +1);
        int die3 = (int)(Math.random()*6 +1);
        int total = die1+die2+die3;
        return total;
    }

}
