package com.nikoapps.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class StartParse extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                //.applicationId("YOUR_APP_ID")
                .applicationId("xCcSj2v0iyLfJH7ZuE1sTKGaTTh95lZrDkzc5hse")
                // if defined
                //.clientKey("YOUR_CLIENT_KEY")
                .clientKey("Ak5urzYWfC7EvgGBIky1qJqWT7ZvDZ1TzwtMD8zU")
                // Parse Server URL
                .server("https://parseapi.back4app.com/")
                .build()
        );

        // automated user on the USER class of the parse server
        // even if you do not have any user log-in can still be used to gather data for data analytics
        // annonymous user
        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
