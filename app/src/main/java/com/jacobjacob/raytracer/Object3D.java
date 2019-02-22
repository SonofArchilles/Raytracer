package com.jacobjacob.raytracer;

public interface Object3D {

    double intersect(Ray ray);

    int getColor(Vector position,int depth);

    Vector getNormal(Vector position);
}
