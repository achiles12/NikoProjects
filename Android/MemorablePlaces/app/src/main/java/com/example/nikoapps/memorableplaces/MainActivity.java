package com.example.nikoapps.memorableplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MyPlaces> myPlaces = new ArrayList<MyPlaces>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int x=0; x<10; x++) {
            myPlaces.add(new MyPlaces("Pasig " + Integer.toString(x),14.58691,121.0614+(x*0.6)));
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, myPlaces, new OnItemClickListner() {
            @Override
            public void OnItemClicked(int pos) {
                //Toast.makeText(this,  myNames.get(pos) +": " + String.valueOf(pos), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), myPlaces.get(pos).nameOfPlace+": " + Integer.toString(pos), Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                Bundle b = new Bundle();
                b.putDouble("lat", myPlaces.get(pos).lat);
                b.putDouble("lng", myPlaces.get(pos).lng);
                b.putString("place", myPlaces.get(pos).nameOfPlace);
                //intent.putExtra("place", myPlaces.get(pos).nameOfPlace);
                //intent.putExtra("lat", myPlaces.get(pos).lat);
                //intent.putExtra("lat", myPlaces.get(pos).lng);

                intent.putExtras(b);

                startActivity(intent);
/*
                Intent yourInent = new Intent(thisActivity.this, nextActivity.class);
                Bundle b = new Bundle();
                b.putDouble("key", doubleVal);
                yourIntent.putExtras(b);
                startActivity(yourIntent);*/
            }
        });
        recyclerView.setAdapter(adapter);

    }
}
