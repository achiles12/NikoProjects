package com.nikoapps.newsreaderapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PageDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_display);

        Bundle b = getIntent().getExtras();

        String URL = b.getString("url");

        WebView myWindow = (WebView) findViewById(R.id.webView);

        myWindow.getSettings().setJavaScriptEnabled(true);
        myWindow.setWebViewClient(new WebViewClient());
        myWindow.loadUrl(URL);

    }
}
