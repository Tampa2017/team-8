package com.example.junior.finfun;

/**
 * Created by david on 10/21/17.
 */

public class UserData {
    private int points;
    private int lives;
    private boolean sharkHat;
    public UserData() {
        points = 0;
        lives = 3;
        sharkHat = false;
    }
    public UserData(int p, int l, boolean sh) {
        points = p;
        lives = l;
        sharkHat = sh;
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
    public boolean isSharkHat() {
        return sharkHat;
    }
    public void setSharkHat(boolean sharkHat) {
        this.sharkHat = sharkHat;
    }
}
