package com.example.junior.finfun.sprites;

import android.graphics.RectF;

public class Enemy {

    private float x;
    private float y;

    private RectF rect;

    //Which way is trash flowing
    public final int LEFT = 0;

    //going nowhere
    int heading = -1;
    float speed = 350;

    private int width;
    private int height;

    private boolean isActive;

    public Enemy(int screenX, int screenY) {
        width = screenX / 20;
        height = screenX / 20;
        isActive = false;

        rect = new RectF();
    }

    public RectF getRect() {
        return rect;
    }

    public boolean getStatus() {
        return isActive;
    }

    public void setInactive() {
        isActive = false;
    }

    public float getImpactPointX() {
        if(heading == LEFT) {
            return x - width;
        }
        else return x;
    }

    public boolean attack(float startX, float startY, int direction) {
        if(!isActive) {
            x = startX;
            y = startY;
            heading = direction;
            isActive = true;
            return true;
        }

        //bullet already active
        return false;
    }

    public void update(long fps) {
        //Just move from right to left

        if(heading == LEFT) {
            x = x - speed / fps;
        }

        rect.left = x;
        rect.right = x + width;
        rect.top = y;
        rect.bottom = y + height;
    }

}