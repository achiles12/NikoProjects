package com.nikoapps.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends CustomAppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    EditText username;
    EditText password;
    EditText email;

    TextView signUpTextView;
    Button loginButton;

    ConstraintLayout backgroundLayout;
    ImageView logoImageView;

    Boolean loginModeActive = true;

    protected void hideKeyboard(){
        // hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

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
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        username = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
        email = findViewById(R.id.emailEditText);

        signUpTextView = findViewById(R.id.signupTextView);
        loginButton = findViewById(R.id.loginButton);

        logoImageView = findViewById(R.id.logoImageView);
        backgroundLayout = findViewById(R.id.backgroundLayout);

        signUpTextView.setOnClickListener(this);
        backgroundLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);

        username.setImeActionLabel("Next", KeyEvent.KEYCODE_ENTER);
        password.setImeActionLabel("Login", KeyEvent.KEYCODE_ENTER);

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    Log.i("Done Pressed", "Password Enter");
                    logIn(textView);
                }

                // hide keyboard
                hideKeyboard();
                return false;
            }
        });

        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    Log.i("Done Pressed", "Email Enter");
                    logIn(textView);
                }

                // hide keyboard
                hideKeyboard();
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.signupTextView) {
            Log.i("Button Switch", "Ok");
            if (loginModeActive) {
                loginModeActive = false;
                signUpTextView.setText("or, Login");
                loginButton.setText("Sign-up");
                password.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                email.setVisibility(View.VISIBLE);
                email.setImeActionLabel("Signup", KeyEvent.KEYCODE_ENTER);
                password.setImeActionLabel("Next", KeyEvent.KEYCODE_ENTER);

                password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        return false;
                    }
                });

            } else {
                loginModeActive = true;
                signUpTextView.setText("or, Sign-up");
                loginButton.setText("Login");
                password.setImeOptions(EditorInfo.IME_ACTION_DONE);
                email.setVisibility(View.INVISIBLE);
                password.setImeActionLabel("Login", KeyEvent.KEYCODE_ENTER);

                password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == KeyEvent.KEYCODE_ENTER) {
                            Log.i("Done Pressed", "Password Enter");
                            logIn(textView);

                            // hide keyboard
                            hideKeyboard();
                        }
                        return false;
                    }
                });

            }
        } else if (view.getId() == R.id.logoImageView || view.getId() == R.id.backgroundLayout) {
            // hide keyboard
            hideKeyboard();
        }
    }

    public void logIn(View view) {
        Log.i("Button", "Pressed");
        if (loginModeActive) {
            Log.i("Login", "Pressed");
            if (username.getText().toString().matches("") || password.getText().toString().matches("") ) {
                Toast.makeText(this, "Username and Password Required", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("Login", "Username and Password Present");
                ParseUser logInUser = new ParseUser();
                logInUser.setUsername(username.getText().toString());
                logInUser.setPassword(password.getText().toString());

                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Log.i("Login", "Success");
                            completedLogIn();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }else {
            Log.i("Signup", "Pressed");
            if (username.getText().toString().matches("") || password.getText().toString().matches("")||email.getText().toString().matches("")) {
                Toast.makeText(this, "Please complete all required information", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("Signup", "Username, Password, and Email Present");
                ParseUser signUpUser = new ParseUser();
                signUpUser.setUsername(username.getText().toString());
                signUpUser.setPassword(password.getText().toString());
                signUpUser.setEmail(email.getText().toString());

                signUpUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Signup", "Success");
                            completedLogIn();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

/*
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            Log.i("Key Event Listner", "Key Board Enter");
            logIn(view);
        }
*/

        return false;
    }


}
