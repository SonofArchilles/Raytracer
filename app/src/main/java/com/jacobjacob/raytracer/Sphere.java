package com.jacobjacob.raytracer;

import android.graphics.Color;

public class Sphere implements Object3D {
    double radius;
    private boolean move;
    Vector center;
    Material m = new Material(new Vector(0,0,0));
    {
        m.setReference(this);
    }
    public Sphere(double radius, Vector center) {
        this.radius = radius;
        this.center = center;
    }

    public Sphere(double radius, Vector center,int c){
        this.radius = radius;
        this.center = center;
        this.m = new Material(new Vector(Color.red(c) / 255,Color.green(c) / 255,Color.blue(c) / 255),this);
        m.setReference(this);
    }

    @Override
    public double intersect(Ray ray) {
        Vector dir = ray.direction;
        Vector origin = ray.position;
        double a = dir.prod(dir);
        Vector ec = origin.subrtact(center); // eye center
        double b = 2 * dir.prod(ec);
        double c = ec.prod(ec) - radius * radius;
        Vector result = Util.mitternachtsformel(a, b, c);
        switch ((int) Math.round(result.getZ())) {
            case 0:
                return Double.MAX_VALUE;
            case 1:
                return result.getX();
            case 2:
                if (result.getX() < 0) {
                    if (result.getY() < 0) {
                        return Double.MAX_VALUE;
                    } else {
                        return result.getY();
                    }
                } else {
                    if (result.getY() < 0) {
                        return result.getX();
                    } else {
                        return Math.min(result.getX(),result.getY());
                    }
                }
            default:
                return Double.MAX_VALUE;
        }
    }

    @Override
    public int getColor(Vector position,int depth) {
        return m.getRGB(position,depth);
    }

    @Override
    public Vector getNormal(Vector position) {
        return position.subrtact(center).normalize();
    }
}
