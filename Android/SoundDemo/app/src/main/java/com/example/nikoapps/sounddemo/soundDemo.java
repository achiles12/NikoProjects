package com.example.nikoapps.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class soundDemo extends AppCompatActivity {

    // define media player
    MediaPlayer mPlayer;

    // define audio manager
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_demo);

        // associate sound file to the media player
        mPlayer = MediaPlayer.create(this, R.raw.song);

        // get system audio services and assign to audio manager
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // get max volume of device
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // get current volume of device
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // get seekbar (volume control) id
        SeekBar discreetSeekBar =  (SeekBar) findViewById(R.id.discreetSeekBar);

        Log.i("maxVolume Value: ", Integer.toString(maxVolume));
        Log.i("currVolume Value: ", Integer.toString(currVolume));

        // set max and current values of the seek bar
        discreetSeekBar.setMax(maxVolume);
        discreetSeekBar.setProgress(currVolume);

        // call seek bar on change action listner
        discreetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Discreet Value: ", Integer.toString(i));

                // set media music volume as seekbar changes progress
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // need to be declared as final due to Timer() class
        // define scrubber
        final SeekBar seekBar =  (SeekBar) findViewById(R.id.seekBar);
        // find max duration of current audio file
        seekBar.setMax(mPlayer.getDuration());

        // set seekbar position based on current position of player
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mPlayer.getCurrentPosition());
            }
        },0 /*update immediately*/,100 /*1000 every second; 100 10th of a second*/);

        // call seek bar on change action listner
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Seekbar Value: ", Integer.toString(i));
                // set current position of media player based on seek bar position changes
                mPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // pause audio when seekbar activated
                mPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // pause audio when seekbar deactivated
                mPlayer.start();
            }
        });
    }
    // play the sound
    public void playSound(View view) {
        mPlayer.start();
    }

    // stop the sound
    public void stopSound(View view) {
        mPlayer.stop();
    }

    // pause the sound
    public void pauseSound(View view) {
        mPlayer.pause();
    }

}
