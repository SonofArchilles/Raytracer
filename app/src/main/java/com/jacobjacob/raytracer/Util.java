package com.jacobjacob.raytracer;

import android.graphics.Color;

public class Util {

    public static final double EPSILON = 0.00004;
    public static final int maxRecursionDepth = 1; //3
    public static final double movespeed = 1;
    public static final double mousemovespeed = 1;
    public static final int BACKGROUNDCOLOR = Color.rgb(80,80,255);


    public static Vector add(Vector a,Vector b){
        return new Vector(a.getX() + b.getX(),a.getY() + b.getY(),a.getZ() + b.getZ());
    }
    public static Vector add(Vector a,Vector b, Vector c){
        return Util.add(Util.add(a,b),c);
    }
    public static Vector mitternachtsformel(double a,double b, double c){
        double  diskriminante = b * b - 4 * a * c;
        if (a == 0){
            return new Vector();
        }
        if (diskriminante < 0){
            return new Vector();
        }
        else if (diskriminante == 0){
            return new Vector(-b/(2*a), 0, 1); // x,y,1 = numbers solutions = 1
        }
        else {
            double rightPart = Math.sqrt(diskriminante);
            return new Vector((-b + rightPart)/(2 * a),(-b - rightPart)/(2 * a), 2); // x,y,1 = numbers solutions = 2
        }
    }
}
