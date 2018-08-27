package com.nikoapps.parsetest;

import android.app.Application;

import com.parse.Parse;

public class ParseTestOne extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                //.applicationId("YOUR_APP_ID")
                .applicationId("xCcSj2v0iyLfJH7ZuE1sTKGaTTh95lZrDkzc5hse")
                // if defined
                //.clientKey("YOUR_CLIENT_KEY")
                .clientKey("Ak5urzYWfC7EvgGBIky1qJqWT7ZvDZ1TzwtMD8zU")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
/*ParseObject object = new ParseObject("ExampleObject");
        object.put("myNumber", "0004");
        object.put("myString", "romulo");

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


}
