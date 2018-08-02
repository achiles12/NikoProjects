package com.example.nikoapps.downloadwebcontent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class downloadWebContent extends AppCompatActivity {


    // AsyncTask background thread process
    // <String, Void, String>
    // <in parameter (URL?), Method to show the progress of download, output parameter>
    // do not forget to request permision via manifest file for internet access
    public class DownloadTask extends AsyncTask<String, Void, String> {


        // implement doInBackground critical parameter
        @Override
        protected String doInBackground(String... strings) {

            Log.i("URL",strings[0]);

            // where to store HTML content
            String result = "";
            // declare URL
            URL url;
            HttpsURLConnection urlConnection = null;

            try {

                // load the parameter URL to the URL variable
                url = new URL(strings[0]);

                // create a connection using the HTTPS connection
                urlConnection = (HttpsURLConnection) url.openConnection();

                // hold input of data
                InputStream in = urlConnection.getInputStream();

                // read the data
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return  result;

            } catch (Exception e){
                e.printStackTrace();
                return "Failure";
            }

        }
    }


    // onCreate is the Main thread
    // UI thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask dTask = new DownloadTask();
        String result = null;

        try {
            // always use try and catch
            // remember 2 default exceptions InterruptedException and ExecutionException
            result = dTask.execute("https://ecowebhosting.co.uk/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Contents of URL", result);

    }
}
