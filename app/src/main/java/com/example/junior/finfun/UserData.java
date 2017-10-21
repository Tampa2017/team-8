package com.example.junior.finfun;

/**
 * Created by david on 10/21/17.
 */

public class UserData {
    private int points;
    private int lives;
    public UserData() {
        points = 0;
        lives = 3;
    }
    public UserData(int p, int l) {
        points = p;
        lives = l;
    }
    public int getPoints() {
        return points;
    }
    public int getLives() {
        return lives;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
}
