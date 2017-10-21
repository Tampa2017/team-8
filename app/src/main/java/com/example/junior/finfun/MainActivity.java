package com.example.junior.finfun;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //offset so that items dont go off screen
    private final int OFFSET = 50;
    private ImageButton b_launch;
    private ImageButton shop_launch;
    private ConstraintLayout main_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_launch = (ImageButton) findViewById(R.id.start_btn); //button starts game
        shop_launch = (ImageButton) findViewById(R.id.shop_btn);
        main_screen = (ConstraintLayout) findViewById(R.id.main_screen);

        b_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayGameActivity.class);
                startActivity(intent);
            }
        });

        shop_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        Random rand = new Random(); //generates trash randomly on screen
        for(int i = 0; i < rand.nextInt(10)+1; ++i)
        {
            ImageView trash_img = new ImageView(MainActivity.this);
            trash_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ViewManager)v.getParent()).removeView(v);
                }
            });
            trash_img.setBackgroundResource(R.drawable.trash_sprite);
            trash_img.setX(rand.nextInt(Resources.getSystem().getDisplayMetrics().widthPixels)-(trash_img.getWidth()+OFFSET));
            trash_img.setY((rand.nextInt(Resources.getSystem().getDisplayMetrics().heightPixels/2) +
                    Resources.getSystem().getDisplayMetrics().heightPixels/2) - (trash_img.getHeight()+OFFSET)); //makes trash appear only on bottom half of screen
            main_screen.addView(trash_img);
        }
    }
}