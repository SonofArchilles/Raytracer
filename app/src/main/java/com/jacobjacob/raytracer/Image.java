package com.jacobjacob.raytracer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import static com.jacobjacob.raytracer.MainActivity.xscreen;
import static com.jacobjacob.raytracer.MainActivity.yscreen;

public class Image {
    public static final int WIDTH = 100; //640
    public static final int HEIGHT = 100;//480
    public static Bitmap bmp;
    public static Canvas canvas;


    public static void Image() {
        if (xscreen > yscreen) {
            bmp = Bitmap.createBitmap((int) WIDTH, (int) HEIGHT, Bitmap.Config.ARGB_8888);
        } else {
            //bmp = Bitmap.createBitmap((int) WIDTH, (int) HEIGHT, Bitmap.Config.ARGB_8888);
            bmp = Bitmap.createBitmap((int) HEIGHT, (int) WIDTH, Bitmap.Config.ARGB_8888);

        }
        canvas = new Canvas(bmp);

    }

    public static void setPixel(int x, int y, int rgb) {
        if (xscreen > yscreen) {
            if (x > WIDTH - 1 || y > HEIGHT - 1 || x < 0 || y < 0) {
                return;
            }
        } else {
            if (x > HEIGHT - 1 || y > WIDTH - 1 || x < 0 || y < 0) {
                return;
            }
        }
        Paint paint = new Paint();
        paint.setColor(rgb);
        canvas.drawRect(x, y, x + 1, y + 1, paint);
    }


    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) { //code from stackoverflow
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;// CREATE A MATRIX FOR THE MANIPULATION

        Matrix matrix = new Matrix();// RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);// "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    public static int getWIDTH() {
        if (xscreen > yscreen) {
            return WIDTH;
        } else {
            //return WIDTH;
            return HEIGHT;
        }
    }

    public static int getHEIGHT() {
        if (xscreen > yscreen) {
            return HEIGHT;
        } else {
            //return HEIGHT;
            return WIDTH;
        }
    }

    public static Bitmap getRescaledBitmap(Bitmap bitmap, int Width, int Height) {
        return getResizedBitmap(bitmap, Width, Height);
    }

    public static Bitmap getBitmap() {
        return getResizedBitmap(bmp, xscreen, yscreen);
        //return getResizedBitmap(bmp, getWIDTH(), getHEIGHT());
    }

    public static Bitmap CombineBitmap(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }
}