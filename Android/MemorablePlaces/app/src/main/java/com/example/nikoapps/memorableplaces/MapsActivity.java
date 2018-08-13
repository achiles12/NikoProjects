package com.example.nikoapps.memorableplaces;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// change extends from Fragement to AppCompatActivity to enable call to App Bar
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    // define variables
    private GoogleMap mMap;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Defining and calling the App bar to place a back button on the map
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        // this line will suppress the onCreate method when going back to the previous activity or next activity
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // end of call to the App Bar

    }

    // when button clicked on the app bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        // get intent
        Intent thisActivity = getIntent();

        // get variables passed (bundle) from the previous activity
        Bundle b = getIntent().getExtras();
        String nameOfPlace = b.getString("place");
        double lat = b.getDouble("lat");
        double lng = b.getDouble("lng");

        Log.i("Place    ", nameOfPlace);
        Log.i("Latitude ", Double.toString(lat));
        Log.i("Longitude", Double.toString(lng));

        // set initial MAP
        LatLng favePlace = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(favePlace).title(nameOfPlace));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(favePlace, 18));

        // on long click listner
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                //define geocoder
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                // list of addressess from geocoder
                List<Address> addressList = null;

                try {
                    // get details of address from geocoder
                    addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Address address = addressList.get(0);
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

                        // add marker based on geocoder details
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title(addressFormat)
                                .snippet(Double.toString(latLng.latitude) + " " + Double.toString(latLng.longitude)));

                        // add new place on ArrayList in MainActivity
                        MainActivity.myPlaces.add(new MyPlaces(addressFormat,latLng.latitude,latLng.longitude));
                        // refresh the recycler view on the adapter on the MainActivity
                        MainActivity.adapter.notifyDataSetChanged();

                    } else {
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("Unlisted Address")
                                .snippet(Double.toString(latLng.latitude) + " " + Double.toString(latLng.longitude)));
                        // refresh the recycler view on the adapter on the MainActivity
                        MainActivity.adapter.notifyDataSetChanged();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });

    }
}
