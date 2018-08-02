package com.example.nikoapps.timerdemo;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TimerDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_demo);

        // import android.os.Handler not java
        final Handler handler = new Handler();

        // create time
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");

        // create runnables
        // continuous runs
        Runnable run = new Runnable() {
            @Override
            public void run() {
                // insert code to be executed every second (1000ms)

                String datetime = dateFormat.format(cal.getTime());

                Log.i("Niko Time:",datetime);

                handler.postDelayed(this,1000);

                datetime = "";
            }
        };
        // execute run method for the 1st time
        handler.post(run);

        // set period of runs
        new CountDownTimer(10000, 1000){

            @Override
            public void onFinish() {
                // on counter end
                String datetime = dateFormat.format(cal.getTime());

                Log.i("Glorious End:",datetime);

            }

            @Override
            public void onTick(long millisecondsUntilDone) {
                // every counter drop
                String datetime = dateFormat.format(cal.getTime());

                Log.i("Countdown Time:",datetime);

            }
        }.start(); // start counter


    }
}
