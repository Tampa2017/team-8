package com.example.junior.finfun;


import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

import com.example.junior.finfun.sprites.BackgroundView;

public class PlayGameActivity extends AppCompatActivity {

    private BackgroundView backgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Display display = getWindowManager().getDefaultDisplay();
        Point resolution = new Point();
        display.getSize(resolution);

        backgroundView = new BackgroundView(this, resolution.x, resolution.y);

        setContentView(backgroundView);

    }
}
