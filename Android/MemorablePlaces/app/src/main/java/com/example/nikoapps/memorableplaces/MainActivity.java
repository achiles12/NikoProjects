package com.example.nikoapps.memorableplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // recycler view definition
    RecyclerView recyclerView;
    // define ArrayList as static to be able to access in other activity
    // this is list of myPlaces objects
    static ArrayList<MyPlaces> myPlaces = new ArrayList<MyPlaces>();
    // define custom adapter
    static Adapter adapter;


    //define location manager and provider variables
    LocationManager locationManager;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // retrieve location service for location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // define criteria
        Criteria criteria = new Criteria();
        // retrieve best provider
        provider = locationManager.getBestProvider(criteria, false);

        // check for location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("Location Info", "Requesting Permission");

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }

        // retrieve last known location
        Location location = locationManager.getLastKnownLocation(provider);

        // add 1 place as initial
        myPlaces.add(new MyPlaces("Add New Place...",location.getLatitude(),location.getLongitude()));

        // retrieve recyler view ID
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // set the layout of the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // bind item function via adapter and include onClick listener !! onItemClickListner is an interface create an interface class to be accessed between adapter and MainActivity!!
        adapter = new Adapter(this, myPlaces, new OnItemClickListner() {
            // on item click listner
            @Override
            public void OnItemClicked(int pos) {

                // alert on name and location of place
                Toast.makeText(getApplicationContext(), myPlaces.get(pos).nameOfPlace+": " + Integer.toString(pos), Toast.LENGTH_SHORT ).show();

                // define intent to next activity
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                // create a bundle to pass variables to next activity
                Bundle b = new Bundle();
                b.putDouble("lat", myPlaces.get(pos).lat);
                b.putDouble("lng", myPlaces.get(pos).lng);
                b.putString("place", myPlaces.get(pos).nameOfPlace);

                // pass bundle to next activity
                intent.putExtras(b);

                // start next activity
                startActivity(intent);

            }
        });

        // bind adapter to recycler view
        recyclerView.setAdapter(adapter);

    }
}
