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
        //De esta forma ajustamos el WebView a los marcos
        final WebSettings ajustesVisorWeb = WVVista.getSettings();
        ajustesVisorWeb.setJavaScriptEnabled(true);
        ajustesVisorWeb.setUseWideViewPort(true);
        ajustesVisorWeb.setLoadWithOverviewMode(true);

        //Obtenemos los datos de la otra actividad mediante el ID de url
        Bundle bundle=getIntent().getExtras();
        String url=bundle.getString("url");
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
