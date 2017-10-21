package com.example.junior.finfun;

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
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int OFFSET = 50;
    private ImageButton b_launch;
    private ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_launch = (ImageButton) findViewById(R.id.start_btn);
        bg = (ConstraintLayout) findViewById(R.id.background);

        Random rand = new Random();
        for(int i = 0; i < rand.nextInt(10)+1; ++i)
        {
            ImageView trash_img = new ImageView(MainActivity.this);
            trash_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ViewManager)v.getParent()).removeView(v);
                }
            });
            trash_img.setBackgroundResource(R.mipmap.ic_launcher_round);
            trash_img.setX(rand.nextInt(Resources.getSystem().getDisplayMetrics().widthPixels)-trash_img.getWidth());
            trash_img.setY(rand.nextInt(Resources.getSystem().getDisplayMetrics().heightPixels)-(trash_img.getHeight()+OFFSET));
            bg.addView(trash_img);
        }
    }
}