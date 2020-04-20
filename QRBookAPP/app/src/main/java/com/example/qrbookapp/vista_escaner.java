package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class vista_escaner extends AppCompatActivity {
    WebView WVVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_escaner);

        WVVista=findViewById(R.id.WVvista);
        final WebSettings ajustesVisorWeb = WVVista.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        ajustesVisorWeb.setUseWideViewPort(true);
        ajustesVisorWeb.setLoadWithOverviewMode(true);

        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("url");
        WVVista.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
