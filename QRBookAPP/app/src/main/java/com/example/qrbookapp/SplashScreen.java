package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Database.ConnectionClass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    ArrayList<String> contenidoFicheroRecordado= new ArrayList<>();
    AccesoFichero accesoFichero = new AccesoFichero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new ConnectionClass().setConnection();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                //Intent intentPri = new Intent().setClass(SPlashScreeeen.this, MainActivity.class);

                final String datos []=fileList();
                final String nombreFicheroRecordatorio="user.txt";

                if (accesoFichero.archivoExisteEntreFicheros(datos,nombreFicheroRecordatorio)){

                    try {
                        InputStreamReader isr= new InputStreamReader(openFileInput(nombreFicheroRecordatorio));
                        BufferedReader br= new BufferedReader(isr);
                        String linea=br.readLine();

                        //Introducimos los datos en un array recorriendo cada linea del fichero, en la primera linea tendrá el usuario y en la segunda la contraseña

                            if(linea.equals("") || linea==null){
                                Intent nuevaActividad = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(nuevaActividad);

                            }else{
                                Intent nuevaActividad = new Intent(getApplicationContext(), InicioActivity.class);
                                startActivity(nuevaActividad);

                            }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                    finish();
            }
        };

        Timer timer= new Timer();
        timer.schedule(task,2000);
    }

}
