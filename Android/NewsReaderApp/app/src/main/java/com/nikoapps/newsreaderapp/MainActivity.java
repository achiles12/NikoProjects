package com.nikoapps.newsreaderapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    RecyclerView newsView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<NewsEntry> stories = new ArrayList<>();

    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpsURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }

                return  result;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                return "Failure";
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //Log.i("Website Content", result);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DownloadTask dTask = new DownloadTask();

        String getTopStoriesURL = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
        String getStoryURL = "https://hacker-news.firebaseio.com/v0/item/";
        String getStoryEndURL = ".json?print=pretty";



        try {
            // get top stories
            String topStories = new DownloadTask().execute(getTopStoriesURL).get();
            Log.i("Top Stories", topStories);

           // String story = new DownloadTask().execute(getStoryURL).get();
            //Log.i("Story", topStories);

            JSONArray JSONtopStories = new JSONArray(topStories);

            String newsID;

            for (int i = 0; i<JSONtopStories.length(); i++){
                Log.i("Story ID",JSONtopStories.getString(i));

                newsID = JSONtopStories.getString(i);

                String story = new DownloadTask().execute(getStoryURL+newsID+getStoryEndURL).get();
                Log.i("Story", story);

                JSONObject JSONstory = new JSONObject(story);

                Log.i("JSON title",JSONstory.getString("title"));
                Log.i("JSON time",JSONstory.getString("time"));
                Log.i("JSON score",JSONstory.getString("score"));
                Log.i("JSON url",JSONstory.getString("url"));

                long  unixDateTime = Long.parseLong(JSONstory.getString("time"));
                Date date = new java.util.Date(unixDateTime*1000L);
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-8"));
                String formattedDate = sdf.format(date);

                stories.add(new NewsEntry(newsID,JSONstory.getString("title"), formattedDate, JSONstory.getString("score"), JSONstory.getString("url")));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        newsView = (RecyclerView) findViewById(R.id.newsView);
        newsView.setLayoutManager(new LinearLayoutManager (this));

        recyclerAdapter = new RecyclerAdapter(this, stories, new OnItemClickListener() {
            @Override
            public void OnItemClicked(int pos) {

                Toast.makeText(getApplicationContext(), stories.get(pos).newsURL, Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent(getApplicationContext(),PageDisplay.class);

                Bundle b = new Bundle();
                b.putString("url", stories.get(pos).newsURL);

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        newsView.setAdapter(recyclerAdapter);


    }

}
