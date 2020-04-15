package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.Toast;
import com.blikoon.qrcodescanner.QrCodeActivity;



public class main_escaner extends AppCompatActivity {

    private static final int REQUEST_CODE_QR_SCAN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_escaner);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT > 22) {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA))
                    Toast.makeText(getApplicationContext(), "Esta aplicación necesita acceder a la cámara para funcionar", Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_CODE_QR_SCAN);
            }
        }
    }

    public void onClick(View v) {
        Intent i = new Intent(main_escaner.this, QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR_SCAN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
            String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (resultado != null) {
                Toast.makeText(getApplicationContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data != null) {
                String lectura = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                Toast.makeText(getApplicationContext(), "Leído: " + lectura, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
