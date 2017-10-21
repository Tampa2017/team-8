package com.example.junior.finfun.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
