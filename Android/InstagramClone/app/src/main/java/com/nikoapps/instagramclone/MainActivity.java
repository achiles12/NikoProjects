package com.nikoapps.instagramclone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    EditText uname;
    EditText passwd;

    public void signUp(View view) {
        Log.i("Sign-up", "Sign-up Clicked");

        Log.i("Sign-up", "Uname" + uname.getText().toString() + "-:");
        Log.i("Sign-up", "Passwd" + passwd.getText().toString() + "-:");

        if (uname.getText().toString().matches("") || passwd.getText().toString().matches("")) {
            Toast.makeText(this, "Username and Password is required.", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("IF", "Username and ID Present");
            ParseUser user = new ParseUser();
            user.setUsername(uname.getText().toString());
            user.setPassword(passwd.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        //ok
                        Log.i("Sign-up", "Success!");
                    } else {
                        // failure
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void logIn(View view){

        Log.i("Log-in", "Log-in Clicked");

        Log.i("Log-in", "Uname" + uname.getText().toString() + "-:");
        Log.i("Log-in", "Passwd" + passwd.getText().toString() + "-:");

        if (uname.getText().toString().matches("") || passwd.getText().toString().matches("")) {
            Toast.makeText(this, "Username and Password is required.", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("IF", "Username and ID Present");
            ParseUser user = new ParseUser();
            user.setUsername(uname.getText().toString());
            user.setPassword(passwd.getText().toString());

            ParseUser.logInInBackground(uname.getText().toString(), passwd.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Log.i("Log-in", "Is Good!!");
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.userNameEditText);
        passwd = findViewById(R.id.passwordEditText);


/*
        // Create new user for application to be placed on the User class of the parse server
        ParseUser user = new ParseUser();

        user.setUsername("MyNewUser");
        user.setPassword("MyPassword");

        //sign-up with callback
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //ok
                    Log.i("Sign-up","GOOD!");
                } else {
                    // failure
                    e.printStackTrace();
                }
            }
        });
*/
        // used to log-in onto the Parse server
        // Parse will handle the monitoring of the log in
        // will not log-out unless instructed?
/*        ParseUser.logInInBackground("MyNewUser", "MyPassword", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.i("Log-in", "GOOD!");
                } else {
                    e.printStackTrace();
                }
            }
        });*/

        Log.i("Current User", "Before Log-out");
        // Log-out from parse
        ParseUser.logOut();
        Log.i("Current User", "After Log-out");

        // check for current user
        if (ParseUser.getCurrentUser().getUsername() != null) {
            Log.i("Current User", ParseUser.getCurrentUser().getUsername());
        } else {
            // returns an error if you use this
            // Log.i("Current User", "No Current User: "+ ParseUser.getCurrentUser().getUsername());
            Log.i("Current User", "No Current User");
            // redirect to log-in sign-up activity
        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
