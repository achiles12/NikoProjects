package com.example.nikoapps.guesstheceleb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CelebMatrix extends AppCompatActivity {

    ArrayList<String> celebURLs = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();

    Random rand = new Random();
    int randomCeleb;
    int locationOfCorrectAnswer;
    String[] answers = new String[4];

    ImageView celebImage;

    public void chooseName(View view){

        Toast toast;

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            toast = Toast.makeText(getApplicationContext(), "Correct! Freak!", Toast.LENGTH_SHORT);
            toast.show();

        } else {

            toast = Toast.makeText(getApplicationContext(), "Engk! It was !" + celebNames.get(randomCeleb), Toast.LENGTH_SHORT);
            toast.show();

        }

        generateCeleb();
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.connect();

                InputStream inputStream = conn.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

      @Override
        protected String doInBackground(String... strings) {

            Log.i("URL",strings[0]);

            String result = "";

            URL url;
            HttpURLConnection urlConn = null;

            try {

                url                       = new URL(strings[0]);
                urlConn                   = (HttpURLConnection) url.openConnection();
                InputStream in            = urlConn.getInputStream();
                InputStreamReader reader  = new InputStreamReader(in);

                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return  result;

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
                return "Failure";
            }

            catch (IOException e) {
                e.printStackTrace();
                return "Failure";
            }

            catch (Exception e) {
                e.printStackTrace();
                return "Failure";
            }
        }
    }

    public void generateCeleb() {

        randomCeleb = rand.nextInt(celebNames.size());

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        Button ans0 = (Button) findViewById(R.id.ans0);
        Button ans1 = (Button) findViewById(R.id.ans1);
        Button ans2 = (Button) findViewById(R.id.ans2);
        Button ans3 = (Button) findViewById(R.id.ans3);

        try {

            myImage = task.execute(celebURLs.get(randomCeleb)).get();
            //myImage = task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
            celebImage.setImageBitmap(myImage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers[i] = celebNames.get(randomCeleb);

            } else {

                incorrectAnswer = rand.nextInt(celebNames.size());

                while (incorrectAnswer == locationOfCorrectAnswer) {

                    incorrectAnswer = rand.nextInt(celebNames.size());

                }

                answers[i] = celebNames.get(incorrectAnswer);

            }

        }

        ans0.setText(answers[0]);
        ans1.setText(answers[1]);
        ans2.setText(answers[2]);
        ans3.setText(answers[3]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess_the_celeb);

        DownloadTask dTask = new DownloadTask();
        String result = null;
        Log.i("Contents of URL","Niko");

        celebImage = (ImageView) findViewById(R.id.celebImage);

        try {
            result = dTask.execute("http://192.168.1.196:8080/docs/celebrityList.html").get();

            String[] splitResult = result.split("<div class=\"sidebarContainer\">");

            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);

            while (m.find()){
               System.out.println(m.group(1));
               celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while (m.find()){

                System.out.println(m.group(1));
                celebNames.add(m.group(1));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        generateCeleb();
    }
}
