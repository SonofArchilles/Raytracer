package com.jacobjacob.raytracer;

import android.support.v7.app.AppCompatActivity;
import static com.jacobjacob.raytracer.Util.mousemovespeed;

public class Buttons extends AppCompatActivity {

    public double RotationX, RotationY;
    public static double RotationXstatic, RotationYstatic;
    public Vector position;
    public static Vector direction;
    public static Vector position1;
    public boolean moved;

    final public void moveCamera(Vector position) {
        this.position = position;

        Menu menu = new Menu();
        //menu.changeCamera(position, new Vector(Math.sin(RotationX), Math.sin(RotationY), Math.cos(RotationX)),RayTracer.camera);

        menu.changeCamera(position, getdirection(),RayTracer.camera);
        //menu.changeCamera(position, new Vector(getRotationX(), getRotationY(), getRotationX()),RayTracer.camera);
    }

    final public void touchCamera(double xmove, double ymove) {

        RotationY = (float) (ymove * mousemovespeed);


        if (RotationY > 180) {
            RotationY = 180;
        }
        if (RotationY < -180) {
            RotationY = -180;
        }//*/
        RotationX = (float) Math.toRadians((xmove * mousemovespeed));//-ymove * 0.04
        RotationY = (float) Math.toRadians((-ymove * mousemovespeed));//Math.toRadians(RotationY);

        RotationXstatic = Math.toRadians(RotationX);
        RotationYstatic = Math.toRadians(RotationY);

        Menu menu = new Menu();
        menu.changeCamera(position, getdirection(),RayTracer.camera);
    }

    static double getRotationX() {
        return RotationXstatic;
    }
    static double getRotationY() {
        return RotationYstatic;
    }
    static Vector getdirection(){
        return direction = new Vector(Math.sin(getRotationX()), Math.sin(getRotationY()), Math.cos(getRotationX()));
    }
}
