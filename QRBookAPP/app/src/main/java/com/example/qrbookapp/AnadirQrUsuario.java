package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.qrbookapp.Database.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnadirQrUsuario extends AppCompatActivity {

    WebView wvQrContenido;
    Button btnGuardar;
    EditText etDescripcionQrAmpliado, etPagina, etTipoQrAmpliado, etNombreQrAmpliado;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_qr_usuario);
        wvQrContenido = findViewById(R.id.wvQrContenido);
        btnGuardar = findViewById(R.id.btnGuardar);
        etNombreQrAmpliado = findViewById(R.id.etNombreQrAmpliado);
        etTipoQrAmpliado = findViewById(R.id.etTipoQrAmpliado);
        etPagina = findViewById(R.id.etPagina);
        etDescripcionQrAmpliado = findViewById(R.id.etDescripcionQrAmpliado);

        final WebSettings ajustesVisorWeb = wvQrContenido.getSettings();

        ajustesVisorWeb.setJavaScriptEnabled(true);
        ajustesVisorWeb.setUseWideViewPort(true);
        ajustesVisorWeb.setLoadWithOverviewMode(true);

        final Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String url = bundle.getString("url");
        final String ISBN = bundle.getString("isbn");
        final String correo = bundle.getString("correo");

        wvQrContenido.loadUrl(url);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Si queremos abrirla en el navegador por defecto usariamos este código o Script
                Uri uri=Uri.parse(code.displayValue);
                Intent i=new Intent(Intent.ACTION_VIEW,uri);*/

                //Al querer abrirlo en otra actividad, pasamos los datos a la otra actividad de esta forma

                Connection connection = ConnectionClass.con;

                PreparedStatement ps;

                try {
                    ps = connection.prepareStatement("INSERT INTO USUARIOQR(CORREO,URL,ISBN,TIPO,NOMBRE,DESCRIPCION,PAGINA) values(?,?,?,?,?,?,?)");

                    ps.setString(1, correo);
                    ps.setString(2, url);
                    ps.setString(3, ISBN);
                    ps.setString(4, etTipoQrAmpliado.getText().toString());
                    ps.setString(5, etNombreQrAmpliado.getText().toString());
                    ps.setString(6, etDescripcionQrAmpliado.getText().toString());
                    ps.setString(7, etPagina.getText().toString());
                    ps.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
