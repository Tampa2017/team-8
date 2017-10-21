package com.example.junior.finfun;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageButton b_launch;
    private ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_launch = (ImageButton) findViewById(R.id.start_btn);
        bg = (ConstraintLayout) findViewById(R.id.background);

        Random rand = new Random();
        List coords = new ArrayList<Point>();
        for(int i = 0; i < rand.nextInt(10)+1; ++i)
        {
            ImageView trash_img = new ImageView(MainActivity.this);
            trash_img.setBackgroundResource(R.mipmap.ic_launcher_round);
            trash_img.setX(rand.nextInt(Resources.getSystem().getDisplayMetrics().widthPixels)-trash_img.getWidth());
            trash_img.setY(rand.nextInt(Resources.getSystem().getDisplayMetrics().heightPixels)-trash_img.getHeight());
            coords.set(i, new Pair<Float, Float>(trash_img.getX(), trash_img.getY()));
            bg.addView(trash_img);
        }
    }
    private class Point {
        float x;
        float y;
        public Point() {
            x = 0;
            y = 0;
        }
        public Point(float _x, float _y)
        {
            x = _x;
            y = _y;
        }
        public float getFirst() {
            return x;
        }
        public float getSecond() {
            return y;
        }
        public void setFirst(float _x)
        {
            x = _x;
        }
        public void setSecond(float _y)
        {
            y = _y;
        }
    }
}