package com.jacobjacob.raytracer;


import android.graphics.Color;

public class Plane implements Object3D{
    private Vector originplane;
    private Vector normal;
    private int color;
    Material m = new Material(new Vector(0,0,0));
    {
        m.setReference(this);
    }


    public Plane(Vector originplane, Vector normal, int c){
        this.originplane = originplane;
        this.normal = normal.normalize();
        this.color = c;
        this.m = new Material(new Vector(Color.red(c) / 255,Color.green(c) / 255,Color.blue(c) / 255),this);
        m.setReference(this);
    }

    //@Override
    public double intersect(Ray ray) {
        Vector dir = ray.direction;
        Vector positionray = ray.position;
        double t = this.originplane.subrtact(positionray).skalarprodukt(this.normal)/dir.skalarprodukt(this.normal);

        t = (this.originplane.subrtact(positionray)).skalarprodukt(this.normal)/(dir).skalarprodukt(this.normal);
        t = (this.originplane.subrtact(positionray)).skalarprodukt(this.normal)/(dir).skalarprodukt(this.normal);
        double d = normal.getX() * originplane.getX() + normal.getY() * originplane.getZ() + normal.getZ() * originplane.getZ();
        t = -(d + normal.getX() * positionray.getX() + normal.getY() * positionray.getY() + normal.getZ() * positionray.getZ())/(normal.getX() * dir.getX() + normal.getY() * dir.getY()+normal.getZ() * dir.getZ());

        //double t = ((originplane).skalarprodukt(this.normal))/dir.skalarprodukt(this.normal);
        //double t = this.originplane.skalarprodukt(this.normal)/dir.skalarprodukt(this.normal);

        if (t > Util.EPSILON){
            return t;
        }else {
            return 0;
        }
    }


    @Override
    public int getColor(Vector position,int depth) {
        return m.getRGB(position,depth);
    }

    public Vector getNormal(Vector position) {
        return normal.normalize();
    }
}
