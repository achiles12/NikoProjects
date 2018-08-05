package com.example.nikoapps.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HikersWatch extends AppCompatActivity implements LocationListener {

    LocationManager locationManager; // GPS
    String provider;                 // network provider LAN or wifi

    TextView latitudeTextView;
    TextView longitudeTextView;
    TextView accuracyTextView;
    TextView speedTextView;
    TextView bearingTextView;
    TextView altitudeTextView;
    TextView addressTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.hikers_watch);



        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        latitudeTextView = (TextView) findViewById(R.id.latitudeTextView);
        longitudeTextView = (TextView) findViewById(R.id.longitudeTextView);
        accuracyTextView = (TextView) findViewById(R.id.accuracyTextView);
        speedTextView = (TextView) findViewById(R.id.speedTextView);
        bearingTextView = (TextView) findViewById(R.id.bearingTextView);
        altitudeTextView = (TextView) findViewById(R.id.altitudeTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.i("Location Info", "Requesting Permission");

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            Log.i("Location Info", "Location Achieved");
        } else {
            Log.i("Location Info", "No Location :(");
        }

    }

    @Override
    public void onLocationChanged(Location location) {

        Double lat = location.getLatitude();
        Double lng = location.getLongitude();
        Float acc = location.getAccuracy();
        Float speed = location.getSpeed();
        Float bearing = location.getBearing();
        Double alt = location.getAltitude();

        Log.i("Location Lng",lng.toString());
        Log.i("Location Lat",lat.toString());
        Log.i("Location Acc",acc.toString());
        Log.i("Location Speed",speed.toString());
        Log.i("Location Bearing",bearing.toString());
        Log.i("Location Altitude",alt.toString());Log.i("Location Lng",lng.toString());

        latitudeTextView.setText(lat.toString());
        longitudeTextView.setText(lng.toString());
        accuracyTextView.setText(acc.toString()+"m");
        speedTextView.setText(speed.toString()+"m/s");
        bearingTextView.setText(bearing.toString());
        altitudeTextView.setText(alt.toString());

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);

            Address  address = addressList.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();
            String addressFormat = "";

            if (addressList != null && addressList.size() > 0) {
                Log.i("Place Info", addressList.get(0).toString());

                for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {

                    addressFragments.add(address.getAddressLine(i));
                    addressFormat += addressFragments.get(i);
                    Log.i("Address Frags", addressFragments.get(i));
                    Log.i("Address Format", addressFormat);
                }

                addressTextView.setText(addressFormat);

            } else {
                addressTextView.setText("Unlisted Address");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        super.onPause();

        // stop updating location info
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.i("Location Info", "Requesting Permission");

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }

        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }
}
