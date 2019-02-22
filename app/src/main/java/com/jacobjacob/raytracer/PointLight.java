package com.jacobjacob.raytracer;

public class PointLight implements Light{
    Vector position;
    Vector intensity;

    public PointLight(Vector position, Vector intensity){
        this.position = position;
        this.intensity = intensity;
    }

    @Override
    public Vector getPositin() {
        return position;
    }

    @Override
    public Vector getIntensity(Vector fromPosition) {
        return intensity;
    }
}
