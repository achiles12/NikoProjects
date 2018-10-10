package com.nikoapps.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class LaunchActivity extends CustomAppCompatActivity {

    protected void completedLogIn() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserInfo");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        Intent userActivityIntent = new Intent(getApplicationContext(), Home.class);
                        startActivity(userActivityIntent);
                        finish();
                    } else {
                        Intent userActivityIntent = new Intent(getApplicationContext(), SignUpForm.class);
                        startActivity(userActivityIntent);
                        finish();
                    }
                } else {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        getSupportActionBar().hide();

        //Check if user is logged in
        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser != null) {
            // do stuff with the user
            Log.i("Current User", ParseUser.getCurrentUser().getUsername());
            completedLogIn();
        } else {
            // show the signup or login screen
            Log.i("Current User", "No Current User");
            Intent loginUser = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(loginUser);
            finish();
        }

    }
}
