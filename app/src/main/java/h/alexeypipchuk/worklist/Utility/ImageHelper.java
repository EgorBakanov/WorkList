package h.alexeypipchuk.worklist.Utility;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Singleton;

@Singleton
public class ImageHelper {

    public Uri saveBitmap(Bitmap bitmap) {

        File file;
        String path = Environment.getExternalStorageDirectory().toString();
        file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

        try {
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        return Uri.fromFile(file);
    }
}
