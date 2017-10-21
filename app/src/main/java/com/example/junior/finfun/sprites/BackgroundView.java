package com.example.junior.finfun.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Junior on 10/20/2017.
 */

public class BackgroundView extends SurfaceView implements Runnable {

    ArrayList<Background> backgrounds;
    private volatile boolean running;
    private Thread gameThread = null;

    //Drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    //Reference to Activity
    Context context;

    long fps = 60;

    //screen resolution
    int screenWidth;
    int screenHeight;

    private PlayerShark playerShark;

    //UP to 20 enemies
    Enemy[] enemies = new Enemy[20];
    int numEnemies = 0;

    int score = 0;

    private int lives = 3;

    public BackgroundView(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        //initialize drawing objects
        ourHolder = getHolder();
        paint = new Paint();

        backgrounds = new ArrayList<>();

        backgrounds.add(new Background(
                this.context,
                screenWidth,
                screenHeight,
                "game_background", 0, 110, 200
        ));

        prepareLevel();


    }

    private void prepareLevel() {
        Log.d("screenWidth", Integer.toString(screenWidth));
        Log.d("screenHeight", Integer.toString(screenHeight));

        playerShark = new PlayerShark(context, screenHeight, screenWidth);


        for(int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy(context, screenWidth, screenHeight);
        }

    }

    private void drawBackground(int position) {

        // Make a copy of the relevant background
        Background bg = backgrounds.get(position);

        // define what portion of images to capture and
        // what coordinates of screen to draw them at

        // For the regular bitmap
        Rect fromRect1 = new Rect(0, 0, bg.width - bg.xClip, bg.height);
        Rect toRect1 = new Rect(bg.xClip, bg.startY, bg.width, bg.endY);

        // For the reversed background
        Rect fromRect2 = new Rect(bg.width - bg.xClip, 0, bg.width, bg.height);
        Rect toRect2 = new Rect(0, bg.startY, bg.xClip, bg.endY);

        //draw the two background bitmaps
        if (!bg.reversedFirst) {
            canvas.drawBitmap(bg.bitmap, fromRect1, toRect1, paint);
            canvas.drawBitmap(bg.bitmapReversed, fromRect2, toRect2, paint);
        } else {
            canvas.drawBitmap(bg.bitmap, fromRect2, toRect2, paint);
            canvas.drawBitmap(bg.bitmapReversed, fromRect1, toRect1, paint);
        }

    }

    public void pause() {
        running = false;
        try {
            gameThread.join();
        } catch(InterruptedException e) {
            Log.e("Error", "joining thread");
        }

    }

    private void draw() {
        if(ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255, 0, 3, 70));

            drawBackground(0);

            paint.setColor(Color.argb(255, 249,129,0));
            paint.setTextSize(40);
            canvas.drawText("Score: " + score + "    " +
                            "Lives: " + lives, 10, 50, paint);


            canvas.drawBitmap(playerShark.getBitmap(), playerShark.getX() - 700, playerShark.getY(), paint);

            for(int i =0; i < enemies.length; i++) {
                    canvas.drawBitmap(enemies[i].getBitmap(), enemies[i].getX(), enemies[i].getY(), paint);
            }

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {

        boolean lost = false;

        if(lost) {
            prepareLevel();
        }

        for(Background bg: backgrounds) {
            bg.update(fps);
        }

        playerShark.update(fps);


        // Update the players bullet
        for(int i = 0; i < enemies.length; i++) {
                enemies[i].update(fps);
        }

        // Has the player's bullet hit the top of the screen

        // Has an invaders bullet hit the bottom of the screen

        // Has the player's bullet hit an invader
        for(int i = 0; i < enemies.length; i++) {
            int enemyxCenter = (int) enemies[i].getX() + (enemies[i].getBitmap().getWidth() / 2);
            int enemyyCenter = (int) enemies[i].getY() + (enemies[i].getBitmap().getHeight() / 2);
            int sharkX = (int)playerShark.getX();
            int sharkY = (int)playerShark.getY();

            int sharkWidth = playerShark.getBitmap().getWidth();
            int sharkHeight = playerShark.getBitmap().getHeight();


            if(enemyxCenter < sharkX + sharkWidth && enemyxCenter > sharkX)
                    if(enemyyCenter < sharkY + sharkHeight  && enemyyCenter > sharkY)
                    {
                        Log.d("collision", "yes!");
                    }
        }

        // Has an alien bullet hit a shelter brick

        // Has a player bullet hit a shelter brick

        // Has an invader bullet hit the player ship


    }

    public void resume() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(running) {
            long startFrameTime = System.currentTimeMillis();

            update();

            draw();

            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame >= 1) {
                fps = 1000/timeThisFrame;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            //Player has touched the screen
            case MotionEvent.ACTION_DOWN:
                playerShark.setMovementState(PlayerShark.TOP);
                break;

            //Player has removed from screen
            case MotionEvent.ACTION_UP:
                playerShark.setMovementState(PlayerShark.DOWN);
                break;
        }
        return true;
    }
}
