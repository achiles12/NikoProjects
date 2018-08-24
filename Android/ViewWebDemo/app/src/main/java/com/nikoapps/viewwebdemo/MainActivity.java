package com.nikoapps.viewwebdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView myWindow = (WebView) findViewById(R.id.myWindow);


        myWindow.getSettings().setJavaScriptEnabled(true);
        myWindow.setWebViewClient(new WebViewClient());
        myWindow.loadUrl("https://www.google.com");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadData("<HTML><BODY><H1>Ako si Niko</H1><p>Ako ay saksakan ng guwapo!</p></BODY></HTML>", "text/html", "UTF-8");
    }
}
