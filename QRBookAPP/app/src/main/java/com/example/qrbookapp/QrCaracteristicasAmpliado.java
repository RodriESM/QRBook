package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.qrbookapp.Class.QR;

public class QrCaracteristicasAmpliado extends AppCompatActivity {

    TextView tvNombreQrAmpliado,tvDescripcionQrAmpliado,tvTipoQrAmpliado;
    WebView wvQrContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_caracteristicas_ampliado);

        tvNombreQrAmpliado = findViewById(R.id.tvNombreQrAmpliado);
        tvDescripcionQrAmpliado = findViewById(R.id.tvDescripcionQrAmpliado);
        tvTipoQrAmpliado = findViewById(R.id.tvTipoQrAmpliado);
        wvQrContenido = findViewById(R.id.wvQrContenido);

        QR qrSeleccionado=(QR)getIntent().getSerializableExtra("qr");

        tvNombreQrAmpliado.setText(qrSeleccionado.getNombre());
        tvDescripcionQrAmpliado.setText(qrSeleccionado.getDescripcion());
        tvTipoQrAmpliado.setText(qrSeleccionado.getTipo());
        wvQrContenido.loadUrl(qrSeleccionado.getUrl());

    }
}
