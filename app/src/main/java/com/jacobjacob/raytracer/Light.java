package com.jacobjacob.raytracer;

public interface Light {

    Vector getPositin();
    Vector getIntensity(Vector fromPosition);
}
