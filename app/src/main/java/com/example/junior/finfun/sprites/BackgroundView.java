package com.example.junior.finfun.sprites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Junior on 10/20/2017.
 */

public class BackgroundView extends SurfaceView implements Runnable {
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

    BackgroundView(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        //initialize drawing objects
        ourHolder = getHolder();
        paint = new Paint();

    }

    public void pause() {
        running = false;
        try {
            gameThread.join();
        } catch(InterruptedException e) {

        }

    }

    private void draw() {
        if(ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255, 0, 3, 70));

            paint.setTextSize(60);
            paint.setColor(Color.argb(255, 255, 255, 255));
            canvas.drawText("I am a plane", 350, screenHeight / 100 * 5, paint);
            paint.setTextSize(220);
            canvas.drawText("I'm a train", 50, screenHeight / 100*80, paint);
        }
    }

    private void update() {

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
}
