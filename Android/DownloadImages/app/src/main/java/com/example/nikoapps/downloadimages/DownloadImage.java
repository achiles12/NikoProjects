package com.example.nikoapps.downloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class DownloadImage extends AppCompatActivity {

    ImageView downloadedImage;

    public void downloadImage(View view) {

        Log.i("Button Pressed", "Yes");

        //https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png

        ImageDownloader task = new ImageDownloader();

        Bitmap myImage;

        try {
            myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();

            downloadedImage.setImageBitmap(myImage);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
          catch (Exception e) {
                e.printStackTrace();
        }

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

                // open connection to URL
                conn.connect();
                // retrieve data and place it onto the inputStream variable. one blast
                InputStream inputStream = conn.getInputStream();
                // decode the image file from inputstream onto a bitmap variable
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                // return the image
                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_image);

        downloadedImage = (ImageView) findViewById(R.id.imageView);
    }
}
