package com.nikoapps.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Home extends CustomAppCompatActivity {

    ParseUser currentUser;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater optionsMenu = getMenuInflater();
        optionsMenu.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         if (item.getItemId() == R.id.logout) {

            Log.i("Current User", "Before Log-out");
            // Log-out from parse
            ParseUser.logOut();
            //Log.i("Current User", "After Log-out");

            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivityIntent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.i("Home", "Begin");

        final TextView firstName = findViewById(R.id.firstName);
        final TextView lastName = findViewById(R.id.lastName);
        final TextView address1 = findViewById(R.id.address1);
        final TextView phoneNumber = findViewById(R.id.phoneNumber);


        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            setTitle("Welcome Back " + currentUser.getUsername().toString());
        }

        ParseQuery<ParseObject> getUserInfo = ParseQuery.getQuery("UserInfo");
        getUserInfo.whereEqualTo("username", currentUser.getUsername().toString());

        getUserInfo.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Parse Query", "Objects Size" + objects.size());
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            firstName.setText(object.getString("firstName"));
                            lastName.setText(object.getString("lastName"));
                            address1.setText(object.getString("address1"));
                            phoneNumber.setText(object.getString("phonenumber"));
                        }
                    }

                }
            }
        });

    }
}
