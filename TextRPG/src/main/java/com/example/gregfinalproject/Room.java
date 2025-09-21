package com.example.gregfinalproject;


public class Room {

    private int gold;
    private boolean blocked;

    public Room() {
        this.gold = (int) (Math.random() * 91) + 10; // generate random gold between 10 and 100
        this.blocked = Math.random() < 0.25;
    }

    public Room(int gold, boolean blocked) {
        this.gold = gold;
        this.blocked = blocked;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}

