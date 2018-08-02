package com.example.nikoapps.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class WhatsTheWeather extends AppCompatActivity {

    EditText cityET;
    TextView currentWeather;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        // this is a background thread and cannot interact with UI on the main thread
        @Override
        protected String doInBackground(String... strings) {

            Log.i("URL",strings[0]);

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
                return "Failure";
            } catch (Exception e) {
                e.printStackTrace();
                return "Failure";
            }
        }

        // this method can interact and update the UI
        // this will receive the retrun value of the on background task
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.i("Website Content", result);

            String weatherDetails = "";
            String main = "";
            String description = "";

            // convert to JSON elements

            try {
                JSONObject jsonObject = new JSONObject(result);

                String weatherInfo = jsonObject.getString("weather");
                Log.i("Weather Content",weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                for (int i = 0; i < arr.length(); i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    Log.i("main",jsonPart.getString("main"));
                    Log.i("description",jsonPart.getString("description"));
                    Log.i("icon",jsonPart.getString("icon"));

                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if (main != "" && description != "") {
                        weatherDetails += main + ": " + description + "\r\n";
                    }
                }

                if (weatherDetails != "") {

                    currentWeather.setText(weatherDetails);

                } else {

                    Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);

                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG).show();
                Log.i("JSONException","Niko Here");
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG).show();
                Log.i("Exception","Niko Here");
                e.printStackTrace();
            }
        }
    }

    public void goWeather(View view) {

        String appID = "&APPID=e82304eaa0642091dc8111b115c36a5a";
        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=";

        DownloadTask dTask = new DownloadTask();

        String cityName = cityET.getText().toString();

        InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMgr.hideSoftInputFromWindow(cityET.getWindowToken(), 0);

        try {
            String encodedCityName = URLEncoder.encode(cityET.getText().toString(), "UTF-8");
            dTask.execute(weatherURL + encodedCityName + appID);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whats_the_weather);

        cityET = (EditText) findViewById(R.id.cityET);
        currentWeather = (TextView) findViewById((R.id.currentWeather));

        Log.i("Weather Begin", "Start");
/*
        try {
            result = dTask.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
*/

        //dTask.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
        //dTask.execute("https://api.openweathermap.org/data/2.5/weather?q=Pasig&APPID=e82304eaa0642091dc8111b115c36a5a");


    }
}
