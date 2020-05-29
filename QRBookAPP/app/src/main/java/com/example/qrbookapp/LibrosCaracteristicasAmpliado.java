package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Class.Libro;
import com.example.qrbookapp.Database.ConnectionClass;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibrosCaracteristicasAmpliado extends AppCompatActivity {

    TextView tvTituloAmpliado,tvAutorAmpliado,tvEditorialAmpliado,tvAnioAmpliado,tvSinopsisAmpliado,tvIdiomaAmpliado,tvGeneroAmpliado,tvIsbnAmpliado;
    ImageView imgLibroAmpliado;
    com.getbase.floatingactionbutton.FloatingActionsMenu fab;
    FloatingActionButton btnVer,btnAnadirQr,btnLeer;
    Button btnAnadir;
    ArrayList<String> contenidoFicheroRecordado= new ArrayList<>();
    AccesoFichero accesoFichero = new AccesoFichero();
    String correo;
    Descarga d=new Descarga();

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
        btnLeer=findViewById(R.id.btnLeer);
        btnAnadirQr=findViewById(R.id.btnAnadirQr);
        fab = findViewById(R.id.fab);
        btnAnadir=findViewById(R.id.btnAnadir);



        //Introducir los datos
        final Libro libroSeleccionadoAnteriormente=(Libro) getIntent().getSerializableExtra("libros");

        tvTituloAmpliado.setText(libroSeleccionadoAnteriormente.getTitulo());
        tvAutorAmpliado.setText(libroSeleccionadoAnteriormente.getAutor());
        tvEditorialAmpliado.setText(libroSeleccionadoAnteriormente.getEditorial());
        tvAnioAmpliado.setText(String.valueOf(libroSeleccionadoAnteriormente.getYear()));

        tvIdiomaAmpliado.setText(libroSeleccionadoAnteriormente.getIdioma());
        tvGeneroAmpliado.setText(libroSeleccionadoAnteriormente.getGenero());
        tvIsbnAmpliado.setText(libroSeleccionadoAnteriormente.getIsbn());
        tvSinopsisAmpliado.setText(libroSeleccionadoAnteriormente.getSinopsis());
        final String PDFDescarga=libroSeleccionadoAnteriormente.getPDF();

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

            //Conexión
            Connection connection = ConnectionClass.con;

            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM LIBRO where ISBN in (SELECT ISBN from  USUARIOLIBRO where CORREO like '"+correo+"' AND ISBN like '"+tvIsbnAmpliado.getText().toString()+"')");
            //Recorremos todos lo libros que tenemos en la ase de datos y los introducimos en el array

            //Hacemos visibles o invisibles los botones.
            if(rs.next()){
                fab.setVisibility(View.VISIBLE);
                btnAnadir.setVisibility(View.INVISIBLE);
            }else{
                btnAnadir.setVisibility(View.VISIBLE);
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

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Conexión a la BBDD
                Connection connection = ConnectionClass.con;

                if (libroSeleccionadoAnteriormente.getPDF()==null){

                }else{
                    //Descarga del libro
                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(PDFDescarga));
                    request.setDescription("Downloading file " + tvIsbnAmpliado.getText().toString()+".pdf");
                    request.setTitle("Downloading");
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/"+tvIsbnAmpliado.getText().toString()+".pdf");
                    manager.enqueue(request);
                }


                //Añadimos el libro al usuario.
                try{
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO USUARIOLIBRO(correo,ISBN) values(?,?)");
                    ps.setString(1,correo);
                    ps.setString(2,tvIsbnAmpliado.getText().toString());
                    ps.executeUpdate();
                    Intent i=new Intent(LibrosCaracteristicasAmpliado.this,InicioActivity.class);
                    startActivity(i);
                    finish();
                }catch (Exception e){
                    e.getMessage();
                }
            }
        });

        btnAnadirQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(getApplicationContext(), Escaner.class);
                nuevaActividad.putExtra("escanerisbn",tvIsbnAmpliado.getText().toString());
                startActivity(nuevaActividad);
            }
        });

        //Dependiendo si tiene PDF para leer o no, mostraremos el botón.
        if (libroSeleccionadoAnteriormente.getPDF()==null){
            btnLeer.setVisibility(View.INVISIBLE);
        }else{
            btnLeer.setVisibility(View.VISIBLE);
            btnLeer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent abrirPDF=new Intent(LibrosCaracteristicasAmpliado.this,PDF.class);
                    abrirPDF.putExtra("isbn",tvIsbnAmpliado.getText().toString());
                    startActivity(abrirPDF);
                }
            });
        }
        }

    //Al presionar el botón de retroceso vamos a inicio activity de nuevo y finalizamos esta actividad.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           Intent i=new Intent(LibrosCaracteristicasAmpliado.this,InicioActivity.class);
           startActivity(i);
           finish();
        }
        return super.onKeyDown(keyCode, event);
    }




}
