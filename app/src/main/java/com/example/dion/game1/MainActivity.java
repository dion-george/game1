package com.example.dion.game1;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    //private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;


    // Size
    private int frameHeight;
    private int frameWidth;
    //private int boxSize;
    private int screenWidth;
    private int screenHeight;

    // Position
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;
    private float x;
    private float y;
    //speed
    private int orangeSpeed;

    private int score=0;

    // Initialize Class

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    //statuscheck
    private boolean start_flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        //box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);

        // Get screen size.
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;


        orangeSpeed = Math.round(screenWidth / 60); // 768 / 60 = 12.8 => 13


        // Move to out of screen.
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

        scoreLabel.setText("Score:0");
        //boxY = 500;





    }



    public void changePos() {
        // Orange
        //orangeX -= orangeSpeed;

        //if (orangeX < 0) {
        orangeX =(int) Math.floor(Math.random() * (frameWidth - orange.getHeight()));
        orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getWidth()));
        //}
        orange.setX(orangeX);
        orange.setY(orangeY);

    }

    public boolean onTouchEvent(MotionEvent me) {
        if(start_flag==false)
        {
            start_flag=true;
            // Why get frame height and box height here?
            // Because the UI has not been set on the screen in OnCreate()!!

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();
            frameWidth  = frame.getWidth();
            //boxY = (int)box.getY();

            // The box is a square.(height and width are the same.)
            //boxSize = box.getHeight();





            startLabel.setVisibility(View.GONE);
            changePos();

        }
//
        if (me.getAction() == MotionEvent.ACTION_DOWN) {

            x=(int)me.getX();
            y=(int)me.getY();
//                    if (x >= orangeX && x < (orangeX + orange.getWidth())
//                            && y >= orangeY && y < (orangeY + orange.getHeight())) {

            score += 10;
            scoreLabel.setText("Score:" + score);
            changePos();
            //}
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
            // changePos();
//                                }
//                            });
//                        }
//                    }, 0, 1000);
        }
        //        }
        return true;
    }
}