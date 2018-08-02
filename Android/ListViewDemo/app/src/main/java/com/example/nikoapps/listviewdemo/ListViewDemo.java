package com.example.nikoapps.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_demo);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        //create list based on array (table? sql?)
        final ArrayList<String> myNames = new ArrayList<String>();

        myNames.add("Amanda");
        myNames.add("Brandon");
        myNames.add("Charles");
        myNames.add("Dennis");
        myNames.add("Emma");
        myNames.add("Fritz");
        myNames.add("Ginger");
        myNames.add("Harley");
        myNames.add("Inca");
        myNames.add("Jenny");
        myNames.add("Kris");
        myNames.add("Liam");
        myNames.add("Mustafa");
        myNames.add("Niko");
        myNames.add("Oris");
        myNames.add("Pamela");

        // create adapter for array list to list view

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myNames );

        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //adapterView = myListView in onItemClick method
                //adapterView.setVisibility(View.GONE);

                // retrieve contents of array
                Log.i("Position Tapped:",Integer.toString(i));
                Log.i("ID Tapped:",Long.toString(l));
                Log.i("Name:",myNames.get(i));

                //Toast.makeText(getApplicationContext(),"Hello " + myNames.get(i), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Hello " + myNames.get(i), Toast.LENGTH_LONG).show();

            }
        });

    }
}
