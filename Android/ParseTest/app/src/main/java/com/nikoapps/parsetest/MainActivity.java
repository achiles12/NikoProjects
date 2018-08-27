package com.nikoapps.parsetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Parse.initialize(new Parse.Configuration.Builder(this)
                //.applicationId("YOUR_APP_ID")
                .applicationId("xCcSj2v0iyLfJH7ZuE1sTKGaTTh95lZrDkzc5hse")
                // if defined
                //.clientKey("YOUR_CLIENT_KEY")
                .clientKey("Ak5urzYWfC7EvgGBIky1qJqWT7ZvDZ1TzwtMD8zU")
                .server("https://parseapi.back4app.com/")
                .build());

        ParseObject object = new ParseObject("ExampleObject");
        object.put("myNumber", "0005");
        object.put("myString", "joaqin");

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException ex) {
                if (ex == null) {
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + ex.toString());
                }
            }
        });



        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);*/

        /*ParseObject score = new ParseObject("Score");
        score.put("username", "sean");
        score.put("score",65);

        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Parse Result", "Successful!");
                } else {
                    Log.i("Parse Result", "Failed" + e.toString());
                }

            }
        });
*/

/*        // query parse object
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.getInBackground("1TlTFEP3HC", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    Log.i("USERNAME", object.getString("username"));
                    Log.i("SCORE",Integer.toString(object.getInt("score")));
                }
            }
        });


        // update parse object
        ParseQuery<ParseObject> update = ParseQuery.getQuery("Score");

        query.getInBackground("jWfTWco4i1", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {

                    object.put("score",85);
                    object.saveInBackground();

                    Log.i("USERNAME", object.getString("username"));
                    Log.i("SCORE",Integer.toString(object.getInt("score")));
                }
            }
        });*/

        // get all rows
        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Niko","exeption");
                    if (objects.size() > 0) {
                        Log.i("Niko","size");
                        for (ParseObject object : objects) {
                            Log.i("USERNAME", object.getString("username"));
                            Log.i("SCORE",Integer.toString(object.getInt("score")));
                        }
                    }
                }
            }
        });*/


        // query particular row without rowID
        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.whereEqualTo("username", "sean");
        query.setLimit(3);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("Niko","exeption");
                    if (objects.size() > 0) {
                        Log.i("Niko","size");
                        for (ParseObject object : objects) {
                            Log.i("USERNAME", object.getString("username"));
                            Log.i("SCORE",Integer.toString(object.getInt("score")));
                        }
                    }
                }
            }
        });*/

        // query particular rows greater than then update
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.whereGreaterThan("score", 50);
        query.setLimit(3);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null) {
                    Log.i("Niko","exeption");
                    if (objects.size() > 0) {
                        Log.i("Niko","size");
                        for (ParseObject object : objects) {

                            object.put("score", object.getInt("score") + 20);
                            object.saveInBackground();

                            Log.i("USERNAME", object.getString("username"));
                            Log.i("SCORE",Integer.toString(object.getInt("score")));
                        }
                    }
                }
            }
        });

        Log.i("Niko","trace");

        Log.i("Niko","trace");

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
}
