package com.jacobjacob.raytracer;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Scene {

    private static Scene scene;
    public ArrayList<Object3D> objects = new ArrayList<Object3D>();
    public ArrayList<Light> lights = new ArrayList<Light>();

    public Scene(){
        //*/
        Random r = new Random();
        for (int i = 0; i < 0; i++) {
            int x1 = r.nextInt(30) - 15;
            int y1 = r.nextInt(30) - 15;
            int z1 = r.nextInt(30) - 15;
            int r1 = r.nextInt(255);
            int g1 = r.nextInt(255);
            int b1 = r.nextInt(255);
            int radius1 = r.nextInt(7)+1;
            objects.add(new Sphere(radius1, new Vector(x1, y1, z1), Color.rgb(r1, g1, b1)));
        }
        for (int i = 0; i < 0; i++) {
            int x1 = r.nextInt(30) - 15;
            int y1 = r.nextInt(30) - 15;
            int z1 = r.nextInt(30) - 15;
            int r1 = r.nextInt(205) + 50;
            int g1 = r.nextInt(205) + 50;
            int b1 = r.nextInt(205) + 50;
            lights.add(new PointLight(new Vector(x1, y1, z1), new Vector(r1, g1, b1)));
        }
        for (int i = 0; i < 0; i++) {
            int x1 = r.nextInt(20) - 30;
            int y1 = r.nextInt(20) - 30;
            int z1 = r.nextInt(20) - 30;
            lights.add(new PointLight(new Vector(x1, 0, z1), new Vector(255, 255, 255)));
        }
        //objects.add(new Sphere(10, new Vector(0, 5, 0), Color.rgb(0, 255, 0)));
        for (int i = 0; i < 1; i++) {
            int x1 = r.nextInt(60) - 30;
            int y1 = r.nextInt(10) - 40;
            int z1 = r.nextInt(60) - 30;
            int x2 = r.nextInt(2) - 1;
            int y2 = r.nextInt(2) - 1;
            int z2 = r.nextInt(2) - 1;
            int r1 = r.nextInt(105) + 50;
            int g1 = r.nextInt(105) + 50;
            int b1 = r.nextInt(105) + 50;
            objects.add(new Plane(new Vector(0, 0, 0), new Vector(0, -1, 0),Color.rgb(r1,g1,b1)));
        }
        //objects.add(new Triangle(new Vector(-10, 13, 1), new Vector(12, 12, 1),new Vector(10,13,1),Color.rgb(255,0,0)));

        /**/
        lights.add(new PointLight(new Vector(5, 2, -5), new Vector(255,255,255)));
        lights.add(new PointLight(new Vector(5, 2, 5), new Vector(255,255,255)));
        lights.add(new PointLight(new Vector(-5, 2, -5), new Vector(255,255,255)));
        lights.add(new PointLight(new Vector(-5, 2, 5), new Vector(255,255,255)));
        //lights.add(new PointLight(new Vector(0, 0, 0), new Vector(255, 255, 255)));
        //*/
        //*/
        objects.add(new Sphere(5, new Vector(0, 0, 0), Color.rgb(25, 255, 25)));
        objects.add(new Sphere(5, new Vector(7, 0, -3), Color.rgb(50, 40, 205)));
        objects.add(new Sphere(5, new Vector(-3, 8, 2), Color.rgb(205, 25, 25)));

        //objects.add(new Triangle(new Vector(7, 0, 2), new Vector(5, 8, 5),new Vector(-5,0,1),Color.rgb(255,0,0)));



        lights.add(new PointLight(new Vector(-5, 10, -5), new Vector(25, 5, 155)));

        lights.add(new PointLight(new Vector(2, -5, -6), new Vector(255, 255, 255)));

        lights.add(new PointLight(new Vector(-3, -10, 7), new Vector(255, 255, 255)));

        lights.add(new PointLight(new Vector(1, -5, 4), new Vector(255, 255, 255)));//*/

    }

    public static Scene getScene(){
        if (scene == null){
            Scene.scene = new Scene();
        }
        return scene;
    }
    public static Scene getnewScene(){
        Scene.scene = new Scene();
        return scene;
    }
}
