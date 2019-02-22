package com.jacobjacob.raytracer;

import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.provider.Telephony;

import static com.jacobjacob.raytracer.Image.HEIGHT;
import static com.jacobjacob.raytracer.Image.WIDTH;
import static com.jacobjacob.raytracer.MainActivity.bmpnew;
import static com.jacobjacob.raytracer.MainActivity.xscreen;
import static com.jacobjacob.raytracer.MainActivity.yscreen;
import static com.jacobjacob.raytracer.MainActivity.Screennew;

public class Start {
    private long Starttime, Endtime;
    private int counter;
    static Bitmap bitmap = Bitmap.createBitmap((int) Image.WIDTH, (int) Image.HEIGHT, Bitmap.Config.ARGB_8888);
    public Bitmap bitmap0, bitmap1, bitmap2, bitmap3,combinedbitmap0,combinedbitmap1,combinedbitmap2;

    public void Start(){


        bmpnew = Bitmap.createBitmap((int) xscreen, (int) yscreen, Bitmap.Config.ARGB_8888);
        Image.Image();
        Screennew.setImageBitmap(Image.getBitmap());


        for (int i = 0; i < 1; i++) {
            //Starttime = System.currentTimeMillis();
            Scene.getnewScene();
            Image.Image();
            //new RayTracer().trace(0, 0, Image.WIDTH, Image.getHEIGHT());

            //DoParallelCalculations();
            parallel();
            bmpnew = Image.getBitmap();

            //bmpnew = combinedbitmap2;

            SaveImage.saveImage("OutputRay" + System.currentTimeMillis() + i, bmpnew); //Image.getBitmap()

            Screennew.setImageBitmap(bmpnew);
            //Endtime = System.currentTimeMillis();
            //System.out.print("Paralleltime: " + (Endtime - Starttime) + "milli");
        }
    }
    void rendernextImage(){
        //counter++;

        Image.Image();
        parallel();
        bmpnew = Image.getBitmap();

        //SaveImage.saveImage("OutputRay" + System.currentTimeMillis() + counter, bmpnew); //Image.getBitmap()

        Screennew.setImageBitmap(bmpnew);
    }

    void parallel() {
//
        MyThread[] Threads = new MyThread[2];
        final MyThread[] Threads2 = Threads;
        for (int i = 0; i < Threads.length; i++) {
            for (int j = 0; j < Threads.length; j++) {
                Threads[i] = new MyThread((i) * (Image.getWIDTH() / Threads.length), (j) * (Image.getHEIGHT() / Threads.length), ((i) + 1) * (Image.getWIDTH() / Threads.length), ((j) + 1) * (Image.getHEIGHT() / Threads.length));
            }
        }
/*
                final int a = i;
                final int b = j;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Threads2[a] = new MyThread((a) * (Image.getWIDTH() / Threads2.length), (b) * (Image.getHEIGHT() / Threads2.length), ((a) + 1) * (Image.getWIDTH() / Threads2.length), ((b) + 1) * (Image.getHEIGHT() / Threads2.length));
                    }
                }).start();
                */
        //*/
/*/
            }
        }//*/
        //*/
                /*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyThread thread0 = new MyThread(0, 0, Image.getWIDTH() / 2, Image.getHEIGHT() / 2);
                }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyThread thread1 = new MyThread(Image.getWIDTH() / 2, 0, Image.getWIDTH(), Image.getHEIGHT() / 2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyThread thread2 = new MyThread(0, Image.getHEIGHT() / 2, Image.getWIDTH() / 2, Image.getHEIGHT());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyThread thread3 = new MyThread(Image.getWIDTH() / 2, Image.getHEIGHT() / 2, Image.getWIDTH(), Image.getHEIGHT());
            }
        }).start();//*/


        //*/
        //*/
        //}catch(Exception e){
        //    System.out.print(e);
        //}//*/
        //}

        /*
    void DoParallelCalculations() {
        try {
            MyThread[] Threads = new MyThread[4];

            for (int i = 0; i < Threads.length; i++) {
                for (int j = 0; j < Threads.length; j++) {
                    Threads[i] = new MyThread((i) * (Image.getWIDTH() / Threads.length), (j) * (Image.getHEIGHT() / Threads.length), ((i) + 1) * (Image.getWIDTH() / Threads.length), ((j) + 1) * (Image.getHEIGHT() / Threads.length));
                }
            }
            for (int i = 0; i < Threads.length; i++) {
                Threads[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
            */
    }
}


class MyThread extends Thread {
    int startX, startY, endX, endY;
    RayTracer r = new RayTracer();

    public MyThread(int startX, int startY, int endX, int endY) {
        //new RayTracer().trace(startX, startY, endX, endY);
        //RayTracer r = new RayTracer();
        r.trace(startX, startY, endX, endY);
    }
}
