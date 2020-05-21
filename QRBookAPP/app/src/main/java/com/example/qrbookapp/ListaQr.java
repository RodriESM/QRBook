package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.qrbookapp.Adapter.AdaptadorQr;
import com.example.qrbookapp.Class.QR;
import com.example.qrbookapp.Database.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        Bundle bundle=getIntent().getExtras();
        String isbn=bundle.getString("info");

        try{
            Connection con= ConnectionClass.con;

            //Obtenemos los datos de cada objeto para introducirlos en el adaptador
            ResultSet rs = con.createStatement().executeQuery("select * from QR where ISBN like '"+isbn+"' order by PAGINA");

            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            while(rs.next()){
                QR qr=new QR(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                arrayQr.add(qr);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            adaptadorQr=new AdaptadorQr(this,arrayQr);
        }

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
