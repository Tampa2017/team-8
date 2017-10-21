package com.example.junior.finfun.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import com.example.junior.finfun.R;

/**
 * Created by Junior on 10/21/2017.
 */

class PlayerShark {

    RectF rect;

    //Player shark will be represented by a Bitmap
    private Bitmap bitmap;

    //How long and high the shark will be
    private float length;
    private float height;

    //x is the far left of the rectangle which forms the shark icon
    private float x;

    //Y is the top coordinate
    private float y;

    //speed of shark swimming
    private int sharkSpeed;

    //shark direction
    private int sharkDirection;

    //which ways can the shark move
    public final int TOP = 1;
    public final int DOWN = 2;

    public PlayerShark(Context context, int screenX, int screenY) {

        //Initialize a blank RectF
        rect = new RectF();

        length = screenX/10;
        height = screenY/10;

        //Start shark in roughly the screen centre
        x = screenX / 2;
        y = screenY - 20;

        //Initialize the bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.shark);

        bitmap = Bitmap.createScaledBitmap(bitmap, (int)length, (int) height, false);

        //how fast the shark is in pixels per second
        sharkSpeed = 200;
    }

    //This update method will be called from update in MainPlayView
    // It determines if the player shark needs to move and changes the coordinates
    // contained in y if necessary
    public void update(long fps) {
        if(sharkDirection == TOP) {
            y = y - sharkSpeed / fps;
        }
        if(sharkDirection == DOWN) {
            y = y + sharkSpeed / fps;
        }

        //update rect which is used to detect hits.
        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;
    }

    public RectF getRect() {
        return rect;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getLength() {
        return length;
    }

    public void setMovementState(int state) {
        sharkDirection = state;
    }


}
