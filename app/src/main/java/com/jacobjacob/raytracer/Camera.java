package com.jacobjacob.raytracer;

public class Camera {

    private int l = -Image.WIDTH/2; // left
    private int r = -1 * l;         //right
    private int t = Image.HEIGHT/2; //top
    private int b = -1 * t;         //bottom
    private Vector UP = new Vector(0,1,0);   // up-Vector
    //*/
    private Vector eye = new Vector(0,0,10); // Cameraposition
    private Vector Z = new Vector(0,0,0);    // Position the Camera looks at

    private Vector W = eye.subrtact(Z).normalize();
    private Vector U = UP.cross(W).normalize();
    private Vector V = W.cross(U).normalize();

    private double d = t / Math.tan(Math.PI/4) / 2; //Math.PI/4 == FOV
    private Vector W_d_negated = W.skalarmultiplikation(d * -1);
    //*/

    public Camera(Vector eye, Vector Z){
        this.eye = eye;
        this.Z = Z;

        this.W = eye.subrtact(Z).normalize();
        this.U = UP.cross(W).normalize();
        this.V = W.cross(U).normalize();

        d = t / Math.tan(Math.PI/4) / 2; //Math.PI/4 == FOV
        W_d_negated = W.skalarmultiplikation(d * -1);
    }


    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Vector getUP() {
        return UP;
    }

    public void setUP(Vector UP) {
        this.UP = UP;
    }

    public Vector getEye() {
        return this.eye;
    }

    public void setEye(Vector eye) {
        if (eye != null) {
            this.eye = this.eye.addVector(eye);
        }
    }

    public Vector getZ() {
        return Z;
    }

    public void setZ(Vector z) {
        //this.Z = z.addVector(eye);
        this.Z = z;
        //this.Z.add(z);
    }

    public Vector getW() {
        return W;
    }

    public void setW(Vector w) {
        //this.W = w.addVector(eye) ;
        this.W = w ;
        //W.add(w);
    }

    public Vector getU() {
        return U;
    }

    public void setU(Vector u) {
        //this.U = u.addVector(eye);
        this.U = u;
        //U.add(u);
    }

    public Vector getV() {
        return V;
    }

    public void setV(Vector v) {
        //this.V = v.addVector(eye);
        this.V = v;
        //V.add(v);
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public Vector getW_d_negated() {
        return W_d_negated;
    }

    public void setW_d_negated(Vector w_d_negated) {
        W_d_negated = w_d_negated;
    }
}
