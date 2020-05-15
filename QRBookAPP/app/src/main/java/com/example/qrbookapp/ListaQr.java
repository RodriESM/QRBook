package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.qrbookapp.Adapter.AdaptadorQr;
import com.example.qrbookapp.Class.QR;

import java.util.ArrayList;

public class ListaQr extends AppCompatActivity {

    GridView gvListaQr;
    AdaptadorQr adaptadorQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_qr);

        ArrayList<QR> arrayQr= new ArrayList<>();
        gvListaQr=findViewById(R.id.gvListaQr);


        QR qr= new QR("https://www.google.com/","2515516351","Imagen","Primera Imagen","Se aprecia algo en ella");
        QR qr2= new QR("https://www.google.com/","2515516351","Web","Segunda Imagen","Se aprecia algo en ella");
        QR qr3= new QR("https://www.google.com/","2515516351","Video","Tercera Imagen","Se aprecia algo en ella");

        arrayQr.add(qr);
        arrayQr.add(qr2);
        arrayQr.add(qr3);



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
