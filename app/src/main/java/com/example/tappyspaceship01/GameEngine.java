package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="DINO-RAINBOWS";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;



    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------

    // represent the TOP LEFT CORNER OF THE GRAPHIC
   //add player image
    int playerXPosition;
    int playerYPosition;
    Bitmap dinoImage;
    Rect playerHitbox;

   //add candy image
    Bitmap candyImage;
    int candyXPosition;
    int candyYPosition;
    Rect candyHitbox;

   //add  rainbow image
    Bitmap rainbowImage;
    int rainbowXPosition;
    int rainbowYPosition;
    Rect rainbowHitbox;

    //add poop image
    Bitmap poopImage;
    int poopXPosition;
    int poopYPosition;
    Rect poopHitbox;

 //adding lane to the screen
    int laneXPosition;
    int laneYPosition;

    int lives = 3;
    public GameEngine(Context context, int w, int h) {
        super(context);

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;

        this.printScreenInfo();
        //@ TODO add your sprites
        //put initial starting position of player
        this.dinoImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                R.drawable.dino32);
        this.playerXPosition = 1300;
        this.playerYPosition = 120;
        // 1. create the hitbox
        this.playerHitbox = new Rect(1300,
                120,
                1300+dinoImage.getWidth(),
                120+dinoImage.getHeight()
        );
    // put initial position of candy

        this.candyImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                R.drawable.candy32);
        this.candyXPosition = 1000;
        this.candyYPosition = 10;

        this.candyHitbox = new Rect(1000,
                100,
                1000+candyImage.getWidth(),
                100+candyImage.getHeight()
        );
        //put initial image for rainbow

        this.rainbowImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                R.drawable.rainbow32);
        this.rainbowXPosition = 1100;
        this.rainbowYPosition = 110;
        this.rainbowHitbox = new Rect(1300,
                110,
                1100+rainbowImage.getWidth(),
                110+rainbowImage.getHeight()
        );

        // put initial image for poop
        this.poopImage = BitmapFactory.decodeResource(this.getContext().getResources(),
                R.drawable.poop32);
        this.poopXPosition = 900;
        this.poopYPosition = 120;
        this.poopHitbox = new Rect(900,
                120,
                900+poopImage.getWidth(),
                120+poopImage.getHeight()
        );

        this.laneXPosition = 500;
        this.laneYPosition =  1500;
    }




    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    public void updatePositions() {
    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.YELLOW);


            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(8);


            // draw candy graphic on screen
            canvas.drawBitmap(candyImage, candyXPosition, candyYPosition, paintbrush);
            // draw the player's hitbox
           canvas.drawRect(this.playerHitbox, paintbrush);

            // draw the dino graphic on the screen
            canvas.drawBitmap(dinoImage, playerXPosition,playerYPosition, paintbrush);


            // draw rainbow on the screen

            canvas.drawBitmap(rainbowImage, rainbowXPosition,rainbowYPosition, paintbrush);


            //draw the lane
            paintbrush.setColor(Color.YELLOW);
            this.canvas.drawLine(this.laneXPosition,
                    this.laneYPosition,
                    this.laneXPosition + 400,
                    this.laneYPosition + 50,
                    paintbrush);
            paintbrush.setColor(Color.BLUE);

            // DRAW GAME STATS
            // -----------------------------
            paintbrush.setTextSize(60);
            canvas.drawText("Lives remaining: " + lives,
                    1100,
                    800,
                    paintbrush
            );
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------


    String fingerAction = "";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {

        }
        else if (userAction == MotionEvent.ACTION_UP) {

        }

        return true;
    }
}
