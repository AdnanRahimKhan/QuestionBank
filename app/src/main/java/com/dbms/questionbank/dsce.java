package com.dbms.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class dsce extends AppCompatActivity {
    WebView ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsce);
        getSupportActionBar().setTitle("About CSE@DSCE Department");

        ob = (WebView) findViewById(R.id.w1);
        ob.getSettings().setAppCacheEnabled(true);
        ob.getSettings().setJavaScriptEnabled(true);
        ob.loadUrl("http://www.dsce.edu.in/academics/ug/computer-science-engineering/");

    }

}
