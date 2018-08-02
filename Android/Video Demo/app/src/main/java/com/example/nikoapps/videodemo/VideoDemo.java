package com.example.nikoapps.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_demo);

        VideoView demoVideo = (VideoView) findViewById(R.id.demoVideo);

        //set video source
        demoVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video001);

        // create media controller
        MediaController mediaController = new MediaController(this);

        //attach video view to controller
        mediaController.setAnchorView(demoVideo);

        //attach controller to video view
        demoVideo.setMediaController(mediaController);

        // start video
        demoVideo.start();
    }
}
