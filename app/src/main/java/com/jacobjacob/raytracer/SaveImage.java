package com.jacobjacob.raytracer;
import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;


public class SaveImage {

    public static void saveImage(String image_name, Bitmap bmp) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Pictures/Screenshots" );
        myDir.mkdirs();
        String fname = "New-" + image_name + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) {
            file.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
