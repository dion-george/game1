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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private TextView life;
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


    private int score=0;
    private int no_of_times=0;
    private int check_touch=0;
    private int count_life = 3;

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
        life = (TextView) findViewById(R.id.life);
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


        // Move to out of screen.
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);

        scoreLabel.setText("Score:0");

        life.setText("life: 3");




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

            playGame();

        }

        //        }
        return true;
    }



    public void playGame(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                no_of_times++;

               if(no_of_times<100) {
                   changePos();
                   orange.setVisibility(View.VISIBLE);
                    handler.postDelayed(this, 3000);
                   touched();

               }
            }
        };

        handler.postDelayed(runnable,1000);
    }

    public void touched(){
        orange.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction()==MotionEvent.ACTION_DOWN) {
                        orange.setVisibility(View.INVISIBLE);

                        score = score + 10;
                        scoreLabel.setText("Score : " + score);

                }
                else{
                    if(orange.getVisibility()== View.VISIBLE) {
                        count_life = count_life - 1;
                        life.setText("Life: " + count_life);
                        if (count_life < 0) {
                            scoreLabel.setText("Score : " + score + " over!");
                        }
                    }
                }


                return true;
            }
        });

    }


}