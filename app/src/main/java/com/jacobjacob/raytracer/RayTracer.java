package com.jacobjacob.raytracer;

public class RayTracer {

    static Camera camera = new Camera(new Vector(0,0,13),new Vector(0,0,0));
    //public Bitmap bitmap;
    public void trace(int startX, int startY, int endX, int endY) {
        //bitmap = Bitmap.createBitmap((int) WIDTH, (int) HEIGHT, Bitmap.Config.ARGB_8888);
        //Canvas canvas = new Canvas(bitmap);

        Buttons buttons = new Buttons();

        for (int i = startX; i < endX;i++){
            for (int j = startY; j < endY; j++){

                double u = camera.getL() + (camera.getR() - camera.getL()) * (i + 0.5) / Image.getWIDTH();
                double v = camera.getT() + (camera.getB() - camera.getT()) * (j + 0.5) / Image.getHEIGHT();

                Vector s = Util.add(camera.getU().skalarmultiplikation(u), camera.getV().skalarmultiplikation(v), camera.getW_d_negated());
                Vector dir = s.normalize();

                Ray r = new Ray(camera.getEye(),dir);

                int res_color = r.castPrimary(0);
                Image.setPixel(i, j, res_color);
            }
        }
    }
}
