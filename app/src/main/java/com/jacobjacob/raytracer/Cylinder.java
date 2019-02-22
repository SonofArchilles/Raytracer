package com.jacobjacob.raytracer;

import android.graphics.Color;

public class Cylinder  implements Object3D{
    Vector radius;
    Vector origin;
    Vector direction;
    Material m = new Material(new Vector(0,0,0));
    {
        m.setReference(this);
    }
    public Cylinder(Vector radius,Vector origin, Vector center) {
        this.radius = radius;
        this.origin = origin;
        this.direction = direction;
    }

    public Cylinder(Vector origin,Vector direction, Vector radius,int c){
        this.origin = origin;
        this.direction = direction;
        this.radius = radius;
        this.m = new Material(new Vector(Color.red(c) / 255,Color.green(c) / 255,Color.blue(c) / 255),this);
        m.setReference(this);
    }

    @Override
    public double intersect(Ray ray) {
        Vector dir = ray.direction;
        Vector origin = ray.position;

        Vector Sphereradius = dir.cross(this.direction).normalize().multiplydouble(this.radius.length());

        /**/
        double a = dir.prod(dir);
        Vector ec = origin.subrtact(origin); // eye center
        double b = 2 * dir.prod(ec);
        double c = 0;// = ec.prod(ec) - radius * radius;

        a = Math.pow(dir.getX(), 2) + Math.pow(dir.getY(), 2);
        b = 2 * (dir.getX() * ec.getX() + dir.getY() * ec.getY());
        c = Math.pow(ec.getX(), 2) + Math.pow(ec.getY(), 2) - Math.pow(radius.length(), 2);
        double discriminant = b * b - 4 * a * c;
        //*/
        if (Sphereradius.length() > this.radius.length()){
            c = 0;
        }
        if (Sphereradius.length() < this.radius.length()){
            c = 2;
        }

        if (Sphereradius.length() == this.radius.length()){
            c = 1;
        }/**/
        Vector result = Util.mitternachtsformel(a, b, discriminant);
        //*/

        double t1 = Math.min((-b + Math.sqrt(discriminant)) / (2 * a), (-b - Math.sqrt(discriminant)) / (2 * a));
        double t2 = (radius.length() / 2.0 - ec.getZ()) / direction.getZ();
        double t3 = (-radius.length() / 2.0 - ec.getZ()) / direction.getZ();

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
        return position.subrtact(origin).normalize();
    }
}
