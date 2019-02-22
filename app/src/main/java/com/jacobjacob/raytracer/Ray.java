package com.jacobjacob.raytracer;

import android.graphics.Color;

import static com.jacobjacob.raytracer.Util.BACKGROUNDCOLOR;

public class Ray {
    public Vector position;
    public Vector direction;

    public Ray(Vector pos, Vector dir){
        this.direction = dir.normalize();
        this.position = pos;
    }
    public Ray(Vector from_point, Vector to_point, boolean dummy){ //boolean to be able to make a second constructor
        this.position = from_point;
        this.direction = to_point.subrtact(from_point).normalize();
    }

    public int castPrimary(int depth){
        if (depth > Util.maxRecursionDepth){
            return Color.BLACK;
        }
        Object3D intersect = null;
        double t = Double.MAX_VALUE - 1;
        for (Object3D o : Scene.getScene().objects){
            double t2 = o.intersect(this);
            if (t2 > 0 && t2 < t){
                intersect = o;
                t = t2;
            }
        }

        if (intersect != null){
            return intersect.getColor(this.getPosition(t),depth);
        }
        else {
            return BACKGROUNDCOLOR;
        }
    }
    public boolean castShadow(){

        double t = Double.MAX_VALUE - 1;
        for (Object3D o : Scene.getScene().objects){
            double t2 = o.intersect(this);
            if (0 < t2 && t2 < t){
                return true;
            }
        }
        return false;

    }

    private Vector getPosition(double t){
        return Util.add(this.position,this.direction.skalarmultiplikation(t));
    }

    public Vector getPositionpublic(double t){
        return Util.add(this.position,this.direction.skalarmultiplikation(t));
    }
}
