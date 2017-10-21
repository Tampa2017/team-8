package com.example.junior.finfun.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

/**
 * Created by Junior on 10/20/2017.
 */

public class Enemy {
    Bitmap bitmap;

    int width;
    int height;

    float speed;

    int xClip;
    int startY;
    int endY;

    Enemy(Context context,
          int enemyWidth,
          int enemyHeight,
          String bitmapName,
          int sY,
          int eY,
          float s) {

        int resID = context.getResources().getIdentifier(
                bitmapName, "drawable", context.getPackageName());

        bitmap = BitmapFactory.decodeResource(context.getResources(), resID);

        xClip = 0;

        startY = sY * (enemyHeight / 100);
        endY = eY * (enemyHeight / 100);
        speed = s;

        bitmap = Bitmap.createScaledBitmap(bitmap, enemyWidth , (endY - startY), true);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    public void update(long fps) {
        xClip -= speed/fps;
        //if enemy goes off screen to the left, reset position
        // so that it enters back from the right
        if(xClip >= width) {
            xClip = 0;
        }
    }
}
