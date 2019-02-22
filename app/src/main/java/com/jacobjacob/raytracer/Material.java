package com.jacobjacob.raytracer;

import android.graphics.Color;

public class Material {
    Object3D reference;

    Vector ambient = new Vector (1,1,1); // percent / how strong this kind of light is
    Vector diffus = new Vector (0.7,0.7,0.7);
    Vector specular = new Vector(0.3,0.3,0.3); //(0.3,0.3,0.3);

    double phongExponent = 5;

    double reflectionIdx = 0.5;

    public Material(Vector color){
        //this.diffus = color;
        this.ambient = color;
        //this.specular = color;
    }
    public Material(Vector color, Object3D ref){
        //this.diffus = color;
        this.ambient = color;
        //this.specular = color;
        this.reference = ref;
    }

    public int getRGB(Vector position, int depth){
        if (reference == null){
            return 0;
        }

        Vector sum = new Vector();
        for (Light l: Scene.getScene().lights){

            Vector positionToLight = l.getPositin().subrtact(position).normalize();
            Ray shadow = new Ray(position.move(Util.EPSILON,positionToLight),positionToLight);
            boolean shadowed = shadow.castShadow();

            Vector ret = new Vector();
            ret.add(ambient.multiply(l.getIntensity(position)));

            if (!shadowed){
                Vector normal = this.reference.getNormal(position);
                double NL = Math.max(normal.prod(positionToLight),0);
                ret.add(diffus.multiply(l.getIntensity(position)).skalarmultiplikation(NL)); //diffus
                //specular:
                Vector refl = normal.skalarmultiplikation(NL*2).subrtact(positionToLight).normalize();
                Vector V = RayTracer.camera.getEye().subrtact(position).normalize();
                double RV = Math.max(refl.prod(V),0);
                ret.add(specular.multiply(l.getIntensity(position)).skalarmultiplikation(Math.pow(RV,phongExponent)));
            }
            double dist = l.getPositin().subrtact(position).length();
            sum.add(ret.skalarmultiplikation(1/(dist * dist)).skalarmultiplikation(255));
        }
        if(this.reflectionIdx > 0){
            Vector normal = this.reference.getNormal(position);
            Vector V = RayTracer.camera.getEye().subrtact(position).normalize();
            double NV = Math.max(normal.prod(V),0);//angle
            Vector refl = normal.skalarmultiplikation(NV*2).subrtact(V).normalize(); // reflected ray / V reflected on NV
            Ray reflection = new Ray(position.move(Util.EPSILON,refl),refl); // move the ray to prevent intersection with the same sphere
            int res = reflection.castPrimary(depth + 1);
            Vector v = new Vector(Color.red(res),Color.green(res),Color.blue(res));
            v.skalarmultiplikation(reflectionIdx); //intensity is skaled
            sum.add(v);
        }
        sum.setX((float) Math.min(255,sum.getX()));
        sum.setY((float) Math.min(255,sum.getY()));
        sum.setZ((float) Math.min(255,sum.getZ()));

        sum.setX((float) Math.max(0,sum.getX()));
        sum.setY((float) Math.max(0,sum.getY()));
        sum.setZ((float) Math.max(0,sum.getZ()));
        int c = Color.rgb((int)sum.getX(),(int)sum.getY(),(int)sum.getZ());
        return c;
    }

    public void setReference(Object3D ref) {
        this.reference = ref;
    }
}
