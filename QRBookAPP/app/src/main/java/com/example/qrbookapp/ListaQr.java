package com.example.qrbookapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrbookapp.Adapter.AdaptadorQr;
import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Class.QR;
import com.example.qrbookapp.Database.ConnectionClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaQr extends AppCompatActivity {

     GridView gvListaQr;
     AdaptadorQr adaptadorQr;
     ArrayList<String> contenidoFicheroRecordado = new ArrayList<>();
     AccesoFichero accesoFichero = new AccesoFichero();
     String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_qr);

        ArrayList<QR> arrayQr = new ArrayList<>();
        gvListaQr = findViewById(R.id.gvListaQr);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String isbn = bundle.getString("info");

        //Comprobar si tiene el libro.
        final String[] datos = fileList();
        final String nombreFicheroRecordatorio = "user.txt";

        if (accesoFichero.archivoExisteEntreFicheros(datos, nombreFicheroRecordatorio)) {

            try {
                InputStreamReader isr = new InputStreamReader(openFileInput(nombreFicheroRecordatorio));
                BufferedReader br = new BufferedReader(isr);
                String linea = br.readLine();

                //Introducimos los datos en un array recorriendo cada linea del fichero, en la primera linea tendrá el usuario y en la segunda la contraseña
                while (linea != null) {
                    contenidoFicheroRecordado.add(linea);
                    linea = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                correo = contenidoFicheroRecordado.get(0);
            }
        }

        try {
            Connection con = ConnectionClass.con;

            //Obtenemos los datos de cada objeto para introducirlos en el adaptador
            ResultSet rs = con.createStatement().executeQuery("select * from QR where ISBN like '" + isbn + "' order by PAGINA");

            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            while (rs.next()) {
                QR qr = new QR(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                arrayQr.add(qr);
            }
            rs.close();

            //Obtenemos los datos de cada objeto para introducirlos en el adaptador
            ResultSet rsUser = con.createStatement().executeQuery("select URL,ISBN,TIPO,NOMBRE,DESCRIPCION,PAGINA from USUARIOQR where ISBN like '" + isbn + "' and CORREO like '" + correo + "' order by PAGINA");

            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            while (rsUser.next()) {
                QR qr = new QR(rsUser.getString(1), rsUser.getString(2), rsUser.getString(3), rsUser.getString(4), rsUser.getString(5), rsUser.getString(6));
                arrayQr.add(qr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            adaptadorQr = new AdaptadorQr(this, arrayQr);
        }

        gvListaQr.setAdapter(adaptadorQr);

        gvListaQr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QR qrSeleccionado = (QR) adaptadorQr.getItem(position);
                Intent i = new Intent(getApplicationContext(), QrCaracteristicasAmpliado.class);
                i.putExtra("qr", qrSeleccionado);
                startActivity(i);

            }
        });

    }
}
