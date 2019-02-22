package com.jacobjacob.raytracer;

import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.icu.util.Output;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import static com.jacobjacob.raytracer.Util.mousemovespeed;
import static com.jacobjacob.raytracer.Util.movespeed;

public class MainActivity extends AppCompatActivity {

    public static int xscreen, yscreen;
    public static ImageView Screennew;
    public static Bitmap bmpnew;
    public double xmove, ymove, x1, y1, x2, y2, xmove1, ymove1;
    public boolean moved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        android.graphics.Point size = new Point();
        display.getSize(size);
        xscreen = size.x;
        yscreen = size.y;

        Screennew = findViewById(R.id.Screen1);

        Start start = new Start();
        start.Start();


        Button topleft = findViewById(R.id.topleft);
        Button topmid = findViewById(R.id.topmid);
        Button topright = findViewById(R.id.topright);
        Button bottomleft = findViewById(R.id.bottomleft);
        Button bottommid = findViewById(R.id.bottommid);
        Button bottomright = findViewById(R.id.bottomright);

        final Buttons button = new Buttons();

        topmid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button.moveCamera(new Vector(movespeed * Math.sin(Buttons.getRotationX()), 0, -movespeed * Math.cos(Buttons.getRotationX())));
            }
        });

        topmid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button.moveCamera(new Vector(movespeed * Math.sin(Buttons.getRotationX()), 0, -movespeed * Math.cos(Buttons.getRotationX())));
                return true;
            }
        });

        bottomleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.moveCamera(new Vector(movespeed * Math.cos(Buttons.getRotationX()), 0, movespeed * Math.sin(Buttons.getRotationX())));
            }
        });

        bottomleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button.moveCamera(new Vector(movespeed * Math.cos(Buttons.getRotationX()), 0, movespeed * Math.sin(Buttons.getRotationX())));
                return true;
            }
        });


        bottomright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.moveCamera(new Vector(-movespeed * Math.cos(Buttons.getRotationX()), 0, -movespeed * Math.sin(Buttons.getRotationX())));
            }
        });
        bottomright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button.moveCamera(new Vector(-movespeed * Math.cos(Buttons.getRotationX()), 0, -movespeed * Math.sin(Buttons.getRotationX())));
                return true;
            }
        });

        bottommid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.moveCamera(new Vector(-movespeed * Math.sin(Buttons.getRotationX()), 0, movespeed * Math.cos(Buttons.getRotationX())));
            }
        });
        bottommid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button.moveCamera(new Vector(-movespeed * Math.sin(Buttons.getRotationX()), 0, movespeed * Math.cos(Buttons.getRotationX())));
                return true;
            }
        });

        topright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.moveCamera(new Vector(0, -movespeed, 0));
            }
        });
        topright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button.moveCamera(new Vector(0, -movespeed, 0));
                return true;
            }
        });

        topleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.moveCamera(new Vector(0, movespeed, 0));
            }
        });
        topleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                button.moveCamera(new Vector(0, movespeed, 0));
                return true;
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final Buttons button = new Buttons();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //if (moved) {
                x2 = (int) event.getX() + xmove1 - xscreen / 2;
                y2 = (int) event.getY() + ymove1 - yscreen / 2;

                x2 = (int) event.getX() - xmove1;
                y2 = (int) event.getY() - ymove1;
                //moved = false;
                button.touchCamera(x2, y2);
                //}
                break;
            case MotionEvent.ACTION_MOVE:
                x1 = (int) (event.getX());
                y1 = (int) (event.getY());


                xmove = x1 - x2 + xmove1 - xscreen / 2;
                ymove = y1 - y2 + ymove1 - yscreen / 2;

                xmove = event.getX() - x2;//- xscreen / 2;
                ymove = event.getY() - y2;//- yscreen / 2;
                button.touchCamera(xmove, ymove);
                break;
            case MotionEvent.ACTION_UP:
                //*/
                //if (!moved) {
                xmove1 = (xmove);//+ xscreen / 2);
                ymove1 = (ymove);//+ yscreen / 2);
                //moved = true;
                button.touchCamera(xmove1, ymove1);
                //}//*/moved = true;
                break;
        }
        return true;
    }
}

