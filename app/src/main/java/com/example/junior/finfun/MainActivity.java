package com.example.junior.finfun;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //offset so that items dont go off screen
    private final int OFFSET = 50;
    private ImageButton b_launch;
    private ImageButton shop_launch;
    private ConstraintLayout main_screen;
    private FirebaseUser user;
    private DatabaseReference ref;
    private int current_points;
    private int current_lives;
    private boolean shark_hat_unlocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

        b_launch = (ImageButton) findViewById(R.id.start_btn); //button starts game
        shop_launch = (ImageButton) findViewById(R.id.shop_btn);
        main_screen = (ConstraintLayout) findViewById(R.id.main_screen);

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData data = dataSnapshot.getValue(UserData.class);
                if(data != null) {
                    current_points = data.getPoints();
                    current_lives = data.getLives();
                    shark_hat_unlocked = data.isSharkHat();
                }
                else {
                    ref.setValue(new UserData());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Error: ", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ref.addValueEventListener(userListener);

        if(shark_hat_unlocked)
            b_launch.setBackgroundResource(R.drawable.shark_hat_sprite);

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
                    if(shark_hat_unlocked)
                        ref.setValue(new UserData(current_points+1, current_lives, true));
                    else
                        ref.setValue(new UserData(current_points+1, current_lives, false));
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
    @Override
    protected void onResume() {
        super.onResume();
        if(shark_hat_unlocked)
            b_launch.setBackgroundResource(R.drawable.shark_hat_sprite);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(shark_hat_unlocked)
            b_launch.setBackgroundResource(R.drawable.shark_hat_sprite);
    }
}