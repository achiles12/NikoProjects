package com.nikoapps.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class SignUpForm extends CustomAppCompatActivity {

    ParseUser currentUser;

    EditText firstName;
    EditText lastName;
    EditText address1;
    EditText address2;
    EditText phoneNumber;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater optionsMenu = getMenuInflater();
        optionsMenu.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

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

    public void OnSubmit(View view) {

        ParseObject userInfo = new ParseObject("UserInfo");
        userInfo.put("firstName", firstName.getText().toString());
        userInfo.put("lastName", lastName.getText().toString());
        userInfo.put("address1", address1.getText().toString());
        userInfo.put("address2", address2.getText().toString());
        userInfo.put("phonenumber", phoneNumber.getText().toString());
        userInfo.put("username",currentUser.getUsername().toString());

        userInfo.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Parse Result", "Successful!");
                    Intent userActivityIntent = new Intent(getApplicationContext(), Home.class);
                    startActivity(userActivityIntent);
                    finish();
                } else {
                    Log.i("Parse Result", "Failed" + e.toString());
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_form);

        Log.i("SignUp Form", "Begin");

        currentUser = ParseUser.getCurrentUser();

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        phoneNumber = findViewById(R.id.phoneNumber);

        if (currentUser != null) {
            setTitle("Welcome " + currentUser.getUsername().toString());
        }



    }
}
