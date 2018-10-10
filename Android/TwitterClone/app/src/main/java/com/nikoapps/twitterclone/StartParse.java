package com.nikoapps.twitterclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class StartParse extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)//.applicationId("YOUR_APP_ID")
                .applicationId("xCcSj2v0iyLfJH7ZuE1sTKGaTTh95lZrDkzc5hse")
                // if defined
                //.clientKey("YOUR_CLIENT_KEY")
                .clientKey("Ak5urzYWfC7EvgGBIky1qJqWT7ZvDZ1TzwtMD8zU")
                // Parse Server URL
                .server("https://parseapi.back4app.com/")
                .build());

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
