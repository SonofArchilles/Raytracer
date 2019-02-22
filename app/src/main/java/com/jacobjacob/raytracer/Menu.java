package com.jacobjacob.raytracer;

import static com.jacobjacob.raytracer.Image.HEIGHT;

public class Menu {

    public void changeCamera(Vector position,Vector direction,Camera camera){
        //
        camera.setEye(position);
        direction.subrtact(RayTracer.camera.getEye());

        camera.setZ(direction);

        //Vector W = direction.normalize();

        Vector W = RayTracer.camera.getEye().subrtact(direction).normalize();
        //W = camera.getEye().subrtact(direction).normalize();;//.addVector(direction.normalize());

        W = (direction.normalize());
        Vector U = camera.getUP().cross(W).normalize();
        Vector V = W.cross(U).normalize();
        camera.setW(W);
        camera.setU(U);
        camera.setV(V);

        double t = HEIGHT/2;
        double d = t / Math.tan(Math.PI/4) / 2; //Math.PI/4 == FOV
        Vector W_d_negated = W.skalarmultiplikation(d * -1);
        camera.setW_d_negated(W_d_negated);
        //*/
        Start start = new Start();
        start.rendernextImage();
    }

    //2d View
    //edit scene / render scene
}
