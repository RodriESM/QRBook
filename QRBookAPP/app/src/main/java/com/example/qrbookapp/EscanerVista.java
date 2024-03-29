package com.example.qrbookapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class EscanerVista extends AppCompatActivity {
    WebView WVVista;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner_vista);

        WVVista = findViewById(R.id.WVvista);
        //De esta forma ajustamos el WebView a los marcos
        final WebSettings ajustesVisorWeb = WVVista.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        ajustesVisorWeb.setUseWideViewPort(true);
        ajustesVisorWeb.setLoadWithOverviewMode(true);
        ajustesVisorWeb.setBuiltInZoomControls(true);
        //Obtenemos los datos de la otra actividad mediante el ID de url
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String url = bundle.getString("url");
        //Mostramos en el WebView la página indicada.
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
