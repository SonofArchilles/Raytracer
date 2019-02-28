package com.jacobjacob.raytracer;

public class Vector {
    private double x, y, z;

    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector(double x) {
        this.x = x;
        this.y = 0;
        this.z = 0;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector b) {
        this.x += b.x;
        this.y += b.y;
        this.z += b.z;
    }

    public Vector addVector(Vector b) {
        return new Vector(this.x + b.x, this.y + b.y, this.z + b.z);
    }


    public double skalarprodukt(Vector b) { //vectorlength /dot
        return Math.sqrt(this.x * b.x + this.y * b.y + this.z * b.z);
    }


    public Vector skalarmultiplikation(double d) { // d = distance/multiplicator

        return new Vector(d * this.x, d * this.y, d * this.z);
    }

    public Vector subrtact(Vector b) {
        return new Vector(this.x - b.x, this.y - b.y, this.z - b.z);
    }

    public Vector normalize() {
        double length = skalarprodukt(this);
        if (Math.abs(length) == 0) {
            return new Vector();
        } else {
            return new Vector(this.x / length, this.y / length, this.z / length);
        }
    }

    public Vector cross(Vector b) {
        return new Vector(this.y * b.z - this.z * b.y, this.z * b.x - this.x * b.z, this.x * b.y - this.y * b.x);
    }

    public double prod(Vector b) {
        return this.x * b.x + this.y * b.y + this.z * b.z;
    }

    public Vector move(double epsilon, Vector positionToLight) {
        return Util.add(this, positionToLight.skalarmultiplikation(epsilon));
    }

    public Vector multiply(Vector b) {
        return new Vector(this.x * b.x, this.y * b.y, this.z * b.z);
    }

    public Vector multiplydouble(double b) {
        return new Vector(this.x * b, this.y * b, this.z * b);
    }

    public Vector negate() {
        return new Vector(-this.x, -this.y, -this.z);
    }

    public double length() {
        return this.skalarprodukt(this);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
