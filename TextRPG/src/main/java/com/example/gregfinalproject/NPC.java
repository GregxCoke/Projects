package com.example.gregfinalproject;

public class NPC {

   private int hitPoints;
   private int strenght;
   private int dexterity;
   private int intelligence;
   private int attack;

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public NPC() {
        this.hitPoints = 10;
        this.strenght = diceRoll();
        this.dexterity = diceRoll();
        this.intelligence = diceRoll();
        this.attack = 1;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrenght() {
        return strenght;
    }


    public int getDexterity() {
        return dexterity;
    }


    public int getIntelligence() {
        return intelligence;
    }


    public int diceRoll (){
        int die1 = (int)(Math.random()*6 +1);
        int die2 = (int)(Math.random()*6 +1);
        int total = die1+die2;

        return total;
    }
}
