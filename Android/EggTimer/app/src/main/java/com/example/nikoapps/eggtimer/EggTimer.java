package com.example.nikoapps.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class EggTimer extends AppCompatActivity {

    SeekBar timerBar;
    TextView timeTextView;
    Boolean activeCounter = false;

    Button switchBtn;

    CountDownTimer countDownTimer;

    public void updateTimerText(int secsLeft){

        int mins = (int) (secsLeft/60);
        int secs = secsLeft - (mins*60);

        String secondString = Integer.toString(secs);
        if (secondString.equals("0")){
            secondString = "00";
        }

        String displayText = Integer.toString(mins) + ":" + secondString;

        timeTextView.setText(displayText);

    }

    public void timeControl(View view){

        Log.i("Button Pressed",Integer.toString(timerBar.getProgress()));

        if (activeCounter == false){

            activeCounter = true;
            timerBar.setEnabled(false);
            switchBtn.setText("Stop");

            // timerBar.getProgress() * 1000 + 100
            // add 100 to synchronize due to delay
            countDownTimer = new CountDownTimer(timerBar.getProgress() * 1000 + 100,1000){
                @Override
                public void onTick(long l) {
                    updateTimerText((int) l/1000);
                    timerBar.setProgress((int) l/1000);
                }

                @Override
                public void onFinish() {
                    updateTimerText(0);
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();

                    timerBar.setEnabled(true);
                    switchBtn.setText("Go");
                    activeCounter = false;
                }
            }.start();
        } else {
            updateTimerText(timerBar.getProgress());
            timerBar.setEnabled(true);
            switchBtn.setText("Go");
            activeCounter = false;
            countDownTimer.cancel();
            timerBar.setProgress(timerBar.getProgress());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egg_timer);

        timerBar = (SeekBar) findViewById(R.id.timerBar);
        timeTextView = (TextView) findViewById(R.id.timeTextView);

        switchBtn = (Button) findViewById(R.id.switchBtn);

        int hour = 600;
        int defaultTime = 30;

        // set max and current values of the seek bar
        timerBar.setMax(hour);
        timerBar.setProgress(defaultTime);

        timerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimerText(i);
             }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
