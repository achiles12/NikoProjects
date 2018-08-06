package com.example.nikoapps.memorableplaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Intent thisActivity = getIntent();

        //Log.i("Place    ", thisActivity.getStringExtra("name"));
        //Log.i("Latitude ", thisActivity.getDoubleExtra("lat"));
        //Log.i("Longitude", thisActivity.getDoubleExtra("lng"));

        Bundle b = getIntent().getExtras();
        String nameOfPlace = b.getString("place");
        double lat = b.getDouble("lat");
        double lng = b.getDouble("lng");

        Log.i("Place    ", nameOfPlace);
        Log.i("Latitude ", Double.toString(lat));
        Log.i("Longitude", Double.toString(lng));

        LatLng favePlace = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(favePlace).title(nameOfPlace));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(favePlace, 18));

    }
}
