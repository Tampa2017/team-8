package com.example.junior.finfun;

//import android.content.intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//this activity allows the user to select different rewards for their animal depending on the number of points he or she has
public class ShopActivity extends AppCompatActivity
{
    private Button mBtGoBack;
    //private Button mBtTShirt;
    //private Button mBtWristBand;
    //private Button mBtWaterBottle;
    private Button mBttopHat;
    private Button mBtExtraLife;
    private FirebaseUser user;
    private DatabaseReference ref;
    private int current_points;
    private int current_lives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());

        mBtGoBack = (Button) findViewById(R.id.bt_go_back);
        //mBtTShirt = (Button) findViewById(R.id.bt_t_shirt);
        //mBtWristBand = (Button) findViewById(R.id.bt_wristband);
        //mBtWaterBottle = (Button) findViewById(R.id.bt_water_bottle);
        mBttopHat = (Button) findViewById(R.id.bt_top_hat);
        mBtExtraLife = (Button) findViewById(R.id.bt_extra_life);


        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserData data = dataSnapshot.getValue(UserData.class);
                current_points = data.getPoints();
                current_lives = data.getLives();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("Error: ", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        ref.addValueEventListener(userListener);

        mBtGoBack.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             finish();
                                         }
                                     }
        );

        /*mBtTShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text; //this text is what will be outputted through the toast
                if(points < 100)
                    text = "So sorry! You don't have enough points. Please try again later.";
                else {
                    text = "Great job! Here is your T-Shirt!";
                    points -= 100; //deduct the points that the user is paying for the item
                }
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show(); //output the message through the toast
            }
        }
        );

        mBtWristBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                if(points < 25) {
                    text = "So sorry! You don't have enough points. Please try again later.";
                }
                else {
                    text = "Great job! Here is your wristband!";
                    points -= 25;
                }
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        }
        );

        mBtWaterBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                if(points < 50)
                    text = "So sorry! You don't have enough points. Please try again later.";
                else {
                    text = "Great job! Here is your water bottle!";
                    points -= 50;
                }
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        }
        );*/

        mBttopHat.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             String text;
                                             if(current_points < 50)
                                                 text = "So sorry! You don't have enough points. Please try again later.";
                                             else {
                                                 text = "Great job! Here is your top hat!";
                                                 ref.setValue(new UserData(current_points-50, current_lives));
                                             }
                                             Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                                             toast.show();
                                         }
                                     }
        );

        mBtExtraLife.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                String text;
                                                if(current_points < 100)
                                                    text = "So sorry! You don't have enough points. Please try again later.";
                                                else {
                                                    text = "Great job! Here is your extra life!";
                                                    ref.setValue(new UserData(current_points-100, current_lives+1));
                                                }
                                                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                                                toast.show();
                                            }
                                        }
        );
    }
}