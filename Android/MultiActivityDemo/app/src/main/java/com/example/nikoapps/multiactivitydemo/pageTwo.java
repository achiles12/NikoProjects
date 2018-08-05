package com.example.nikoapps.multiactivitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class pageTwo extends AppCompatActivity {



    public void onClick(View view){
        Log.i("On Click", "Going Back");
        Intent intent = new Intent(getApplicationContext(),page_one.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_two);

        Intent thisActivity = getIntent();
        TextView displayTextView = (TextView) findViewById(R.id.displayTextView);
        displayTextView.setText("Hello "+ thisActivity.getStringExtra("name"));

    }
}
