package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.qrbookapp.Class.QR;

public class QrCaracteristicasAmpliado extends AppCompatActivity {

    TextView tvNombreQrAmpliado,tvDescripcionQrAmpliado,tvTipoQrAmpliado,tvPagina;
    WebView wvQrContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_caracteristicas_ampliado);

        tvNombreQrAmpliado = findViewById(R.id.tvNombreQrAmpliado);
        tvDescripcionQrAmpliado = findViewById(R.id.tvDescripcionQrAmpliado);
        tvTipoQrAmpliado = findViewById(R.id.tvTipoQrAmpliado);
        wvQrContenido = findViewById(R.id.wvQrContenido);
        tvPagina=findViewById(R.id.tvPagina);

        final WebSettings ajustesVisorWeb = wvQrContenido.getSettings();

        ajustesVisorWeb.setJavaScriptEnabled(true);
        ajustesVisorWeb.setUseWideViewPort(true);
        ajustesVisorWeb.setLoadWithOverviewMode(true);

        QR qrSeleccionado=(QR)getIntent().getSerializableExtra("qr");
        Bundle info=getIntent().getExtras();

        String nombre=info.getString("nombre");
        String isbn=info.getString("isbn");
        String descripcion=info.getString("descripcion");
        String tipo=info.getString("tipo");
        String url=info.getString("url");
        String pag=info.getString("pag");

        tvNombreQrAmpliado.setText(nombre);
        tvDescripcionQrAmpliado.setText(descripcion);
        tvTipoQrAmpliado.setText(tipo);
        tvPagina.setText(pag);

        wvQrContenido.loadUrl(url);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
