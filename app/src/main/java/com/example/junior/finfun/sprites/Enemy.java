package com.example.junior.finfun.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.util.Log;

import com.example.junior.finfun.R;

import java.util.Random;

public class Enemy {

    private float x;
    private float y;

    private RectF rect;
    private Bitmap bitmap;

    //Which way is trash flowing
    public final int LEFT = 0;

    //going nowhere
    int heading = -1;
    float speed = 500;

    private int width;
    private int height;
    private int startX;
    private int startY;
    public Enemy(Context context, int screenX, int screenY) {

        Random rand = new Random();
        width = screenX / 5;
        height = screenY / 5;

        rect = new RectF();

        startX = screenX;
        startY = screenY;
        int xShift = rand.nextInt(1000);
        x = startX + xShift;
        int yShift = rand.nextInt(3000) - 100;
        y = startY - yShift;
        heading = LEFT;

        int bitmapSelector = rand.nextInt(2);

        if(bitmapSelector == 0)
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_sprite1);
        else
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.trash_sprite2);

        bitmap = Bitmap.createScaledBitmap(bitmap,
                width, height, false);

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        Log.d("enemy", Integer.toString((int)width));
        Log.d("enemy", Integer.toString((int)height));


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

    public float getImpactPointX() {
        if(heading == LEFT) {
            return x - width;
        }
        else return x;
    }


    public void update(long fps) {
        //Just move from right to left
        Random rand = new Random();

        if(heading == LEFT) {
            x -= speed/fps;
        }
        if(x <= 0) {
            int xShift = rand.nextInt(1000);
            x = startX + xShift;
            int yShift = rand.nextInt(3000) - 100;
            y = startY - yShift;
        }

        rect.left = x;
        rect.right = (x + width) / 2;
        rect.top = y;
        rect.bottom = (y + height) / 2;
    }

}