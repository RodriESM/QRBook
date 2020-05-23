package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Class.Libro;
import com.example.qrbookapp.Database.ConnectionClass;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibrosCaracteristicasAmpliado extends AppCompatActivity {

    TextView tvTituloAmpliado,tvAutorAmpliado,tvEditorialAmpliado,tvAnioAmpliado,tvSinopsisAmpliado,tvIdiomaAmpliado,tvGeneroAmpliado,tvIsbnAmpliado;
    ImageView imgLibroAmpliado;
    com.getbase.floatingactionbutton.FloatingActionsMenu fab;
    FloatingActionButton btnVer;
    Button btnAñadir;
    ArrayList<String> contenidoFicheroRecordado= new ArrayList<>();
    AccesoFichero accesoFichero = new AccesoFichero();
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros_caracteristicas_ampliado);

        tvTituloAmpliado=findViewById(R.id.tvTituloAmpliado);
        tvAutorAmpliado=findViewById(R.id.tvAutorAmpliado);
        tvEditorialAmpliado=findViewById(R.id.tvEditorialAmpliado);
        tvAnioAmpliado=findViewById(R.id.tvAnioAmpliado);
        tvSinopsisAmpliado=findViewById(R.id.tvSinopsisAmpliado);

        tvIdiomaAmpliado=findViewById(R.id.tvIdiomaAmpliado);
        tvGeneroAmpliado=findViewById(R.id.tvGeneroAmpliado);
        tvIsbnAmpliado=findViewById(R.id.tvIsbnAmpliado);

        imgLibroAmpliado=findViewById(R.id.imgLibroAmpliado);
        btnVer=findViewById(R.id.btnVer);
        fab = findViewById(R.id.fab);
        btnAñadir=findViewById(R.id.btnAñadir);



        //Introducir los datos
        Libro libroSeleccionadoAnteriormente=(Libro) getIntent().getSerializableExtra("libros");

        tvTituloAmpliado.setText(libroSeleccionadoAnteriormente.getTitulo());
        tvAutorAmpliado.setText(libroSeleccionadoAnteriormente.getAutor());
        tvEditorialAmpliado.setText(libroSeleccionadoAnteriormente.getEditorial());
        tvAnioAmpliado.setText(String.valueOf(libroSeleccionadoAnteriormente.getYear()));

        tvIdiomaAmpliado.setText(libroSeleccionadoAnteriormente.getIdioma());
        tvGeneroAmpliado.setText(libroSeleccionadoAnteriormente.getGenero());
        tvIsbnAmpliado.setText(libroSeleccionadoAnteriormente.getIsbn());
        tvSinopsisAmpliado.setText(libroSeleccionadoAnteriormente.getSinopsis());

        Picasso.get()//Context
                .load(libroSeleccionadoAnteriormente.getPortada()) //URL/FILE
                .into(imgLibroAmpliado);

//Comprobar si tiene el libro.
        final String datos []=fileList();
        final String nombreFicheroRecordatorio="user.txt";


        if (accesoFichero.archivoExisteEntreFicheros(datos,nombreFicheroRecordatorio)){

            try {
                InputStreamReader isr= new InputStreamReader(openFileInput(nombreFicheroRecordatorio));
                BufferedReader br= new BufferedReader(isr);
                String linea=br.readLine();

                //Introducimos los datos en un array recorriendo cada linea del fichero, en la primera linea tendrá el usuario y en la segunda la contraseña
                while(linea!=null){
                    contenidoFicheroRecordado.add(linea);
                    linea = br.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                correo = contenidoFicheroRecordado.get(0);
            }
        }
        try {
            Connection connection = ConnectionClass.con;

            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM LIBRO where ISBN in (SELECT ISBN from  USUARIOLIBRO where CORREO like '"+correo+"' AND ISBN like '"+tvIsbnAmpliado.getText().toString()+"')");
            //Recorremos todos lo libros que tenemos en la ase de datos y los introducimos en el array
            if(rs.next()){
                fab.setVisibility(View.VISIBLE);
                btnAñadir.setVisibility(View.INVISIBLE);
            }else{
                btnAñadir.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(getApplicationContext(), ListaQr.class);
                nuevaActividad.putExtra("info",tvIsbnAmpliado.getText().toString());
                startActivity(nuevaActividad);
            }
        });

    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
