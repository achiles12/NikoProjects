package com.example.nikoapps.gridlayoutdemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GridLayoutDemo extends AppCompatActivity {

    MediaPlayer mPlayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout_demo);
    }

    public void initiateMdeia(int mediaID) {
        mPlayer = MediaPlayer.create(this, mediaID);
    }

    public void onClick(View view) {

        /* if statement method by niko
        Button btnPress = (Button) view;
        Log.i("Pressed Button: ", Integer.toString(btnPress.getId()));

        if (view == (Button) findViewById(R.id.doyouspeakenglish)){
            initiateMdeia(R.raw.doyouspeakenglish);
        } else if (view == (Button) findViewById(R.id.goodevening)) {
            initiateMdeia(R.raw.goodevening);
        }else if (view == (Button) findViewById(R.id.hello)) {
            initiateMdeia(R.raw.hello);
        }else if (view == (Button) findViewById(R.id.howareyou)) {
            initiateMdeia(R.raw.howareyou);
        }else if (view == (Button) findViewById(R.id.ilivein)) {
            initiateMdeia(R.raw.ilivein);
        }else if (view == (Button) findViewById(R.id.mynameis)) {
            initiateMdeia(R.raw.mynameis);
        }else if (view == (Button) findViewById(R.id.please)) {
            initiateMdeia(R.raw.please);
        }else if (view == (Button) findViewById(R.id.welcome)) {
            initiateMdeia(R.raw.welcome);
        }
        mPlayer.start();

        */

        // get view ID numerical
        int btnID = view.getId();
        // get id used in development
        String devID = "";
        devID = view.getResources().getResourceEntryName(btnID);

        //get raw file id based on id used in development
        int mediaID = getResources().getIdentifier(devID,"raw","com.example.nikoapps.gridlayoutdemo");

        // assign the file onto the media player and play
        mPlayer = MediaPlayer.create(this, mediaID);
        mPlayer.start();

    }


}
