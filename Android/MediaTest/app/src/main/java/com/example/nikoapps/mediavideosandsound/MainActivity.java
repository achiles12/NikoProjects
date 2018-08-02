package com.example.nikoapps.mediavideosandsound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fadeBart(View view) {

        ImageView bart = (ImageView) findViewById(R.id.bartView);
        ImageView homer = (ImageView) findViewById(R.id.homerView);
/*
        bart.animate().alpha(0f).setDuration(1000);
        homer.animate().alpha(1f).setDuration(3000);
*/
        bart.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);

    }
/*
    public void fadeHomer(View view) {

        ImageView bart = (ImageView) findViewById(R.id.bartView);
        ImageView homer = (ImageView) findViewById(R.id.homerView);

        int whoBart = 	bart.getImageAlpha();
        int whoHomer = 	homer.getImageAlpha();

        Log.i("Info",whoBart + "whoBart");
        Log.i("Info",whoHomer + "whoHomer");

        homer.animate().alpha(0f).setDuration(1000);
        bart.animate().alpha(1f).setDuration(3000);

    }
*/
}
