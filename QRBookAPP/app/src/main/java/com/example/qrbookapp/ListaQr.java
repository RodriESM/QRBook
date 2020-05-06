package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class ListaQr extends AppCompatActivity {

    GridView gvListaQr;
    AdaptadorQr adaptadorQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_qr);

        ArrayList<Libros> arrayQr= new ArrayList<>();
        gvListaQr=findViewById(R.id.gvListaQr);



        adaptadorQr=new AdaptadorQr(this,arrayQr);

        gvListaQr.setAdapter(adaptadorQr);

        gvListaQr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QR qrSeleccionado=(QR)adaptadorQr.getItem(position);
                Intent i = new Intent(getApplicationContext(),QrCaracteristicasAmpliado.class);
                i.putExtra("qr",qrSeleccionado);
                startActivity(i);

            }
        });


    }
}
