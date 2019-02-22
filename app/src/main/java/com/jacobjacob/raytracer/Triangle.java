package com.jacobjacob.raytracer;

import android.graphics.Color;
import android.os.Build;

import static com.jacobjacob.raytracer.Util.EPSILON;

public class Triangle implements Object3D {

    private Vector p1, p2, p3;
    private Vector u, v;
    private Vector normal;
    private int Colortri;
    Material m = new Material(new Vector(0, 0, 0));

    {
        m.setReference(this);
    }

    public Triangle(Vector p1, Vector p2, Vector p3, int Colortri) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.u = this.p2.subrtact(this.p1);
        this.v = this.p3.subrtact(this.p1);

        this.normal = u.cross(v).normalize();

        this.Colortri = Colortri;
        this.m = new Material(new Vector(Color.red(Colortri) / 255, Color.green(Colortri) / 255, Color.blue(Colortri) / 255), this);
        m.setReference(this);
    }

    public double intersect1(Ray ray) {

        double Planehit = new Plane(this.p1,this.normal,this.Colortri).intersect(ray);

        if (Planehit > EPSILON) {

            double uu, uv, vv, wu, wv, D;
            uu = u.skalarprodukt(u);
            uv = u.skalarprodukt(v);
            vv = v.skalarprodukt(v);

            //Vector w = new Vector(planeHit.point.plus(new Vector(p1).negate()));
            //Vector w = new Vector((ray.position.multiplydouble(Planehit).add(new Vector(this.p1.getX(),this.p1.getY(),this.p1.getZ()).negate())));

            Vector w = ray.position.multiplydouble(Planehit);
            w = ((ray.direction.normalize()).multiplydouble(Planehit).addVector((p1).negate())).subrtact(ray.position);
            //w = p1.negate();

            //Vector pvec = this.p1;//new Vector(this.p1.getX(), this.p1.getY(), this.p1.getZ());
            //w.add(pvec.negate());

            wu = w.skalarprodukt(u);
            wv = w.skalarprodukt(v);
            D = uv * uv - uu * vv;

            double s, t;
            s = (uv * wv - vv * wu) / D;

            if (s < 0 || s > 1) {
                return 0;
            }
            t = (uv * wu - uu * wv) / D;
            if (t < 0 || (s + t) > 1) {
                return 0;
            }

            //return new RayHit(planeHit.ray, this, planeHit.normal, planeHit.point, true);
            if (t > 0 && (s + t) < 1 && s > 0 & s < 1) {
                return Planehit;
            }
            return Planehit;//Math.min(s, t);
        }
        else{return 0;}
    }

    public double intersect(Ray ray)
    {
        double unew, vnew;
        Vector raydirection = ray.direction;
        Vector rayposition = ray.position;

        // compute plane's normal
        Vector v0v1 = this.p2.subrtact(this.p1);
        Vector v0v2 = this.p3.subrtact(this.p1);
        // no need to normalize
        Vector N = v0v1.cross(v0v2); // N
        double denom = N.skalarprodukt(N);

        // Step 1: finding P

        // check if ray and plane are parallel ?
        double NdotRayDirection = N.skalarprodukt(raydirection);


        if (NdotRayDirection < EPSILON){ // almost 0
            return 0;} // they are parallel so they don't intersect !

        // compute d parameter using equation 2
        double d = N.skalarprodukt(this.p1);

        // compute t (equation 3)
        double t = (N.skalarprodukt(rayposition) + d) / NdotRayDirection;
        // check if the triangle is in behind the ray

        if (t < 0){
            return 0;
        } // the triangle is behind

        // compute the intersection point using equation 1

        //Vector P = rayposition.add(raydirection.multiplydouble(t));

        Vector P = ray.getPositionpublic(t);
        // Step 2: inside-outside test
        Vector C; // vector perpendicular to triangle's plane

        // edge 0
        Vector edge0 = this.p2.subrtact(this.p1);
        Vector vp0 = P.subrtact(this.p1);
        C = edge0.cross(vp0);
        if (N.skalarprodukt(C) < 0){ return 0;} // P is on the right side

        // edge 1
        Vector edge1 = this.p3.subrtact(this.p2);
        Vector vp1 = P.subrtact(this.p2);
        C = edge1.cross(vp1);


        if ((unew = N.skalarprodukt(C)) < 0){
            return 0;
        } // P is on the right side

        // edge 2
        Vector edge2 = this.p1.subrtact(this.p3);
        Vector vp2 = P.subrtact(this.p3);
        C = edge2.cross(vp2);

        if ((vnew = N.skalarprodukt(C)) < 0){
            return 0;
        }// P is on the right side;

        unew /= denom;
        vnew /= denom;

        return Math.min(unew,vnew); // this ray hits the triangle
    }

    public Vector getA() {
        return p1;
    }

    public void setA(Vector p1) {
        this.p1 = p1;
    }

    public Vector getB() {
        return p2;
    }

    public void setB(Vector p2) {
        this.p2 = p2;
    }

    public Vector getC() {
        return p3;
    }

    public void setC(Vector p3) {
        this.p3 = p3;
    }

    @Override
    public int getColor(Vector position, int depth) {
        return m.getRGB(position, depth);
    }

    @Override
    public Vector getNormal(Vector position) {
        return normal.normalize();
    }
}
