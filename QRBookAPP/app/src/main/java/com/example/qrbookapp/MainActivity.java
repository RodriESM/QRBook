package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Database.ConnectionClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnSignin;
    EditText etEmail, etPassword;
    ArrayList<String> contenidoFicheroRecordado= new ArrayList<>();
    AccesoFichero accesoFichero = new AccesoFichero();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ConnectionClass().setConnection();
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignin = findViewById(R.id.btnSignin);

        //Mira en todos los fichero que contiene la aplicacion
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
            }

        }

        if(contenidoFicheroRecordado.size()>0){
            etEmail.setText(contenidoFicheroRecordado.get(0));
            etPassword.setText(contenidoFicheroRecordado.get(1));
        }

            btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (ConnectionClass.con == null) {
                        new ConnectionClass().setConnection();
                        Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
                    } else {
                        Connection connection = ConnectionClass.con;
                        PreparedStatement pstUserPass = connection.prepareStatement("SELECT USUARIO,CORREO from USUARIO  WHERE (CORREO like'" + etEmail.getText().toString() + "' OR USUARIO like '" + etEmail.getText().toString() + "') AND PASSWORD like '" + etPassword.getText().toString() + "'");
                        ResultSet rs = pstUserPass.executeQuery();
                            if (rs.next()) {
                                Toast.makeText(MainActivity.this, "Bienvenido " + rs.getString(1), Toast.LENGTH_SHORT).show();
                                EscribirEnFichero(rs.getString(2), etPassword.getText().toString());
                                Intent i = new Intent(MainActivity.this, InicioActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                            }

                    }

                } catch (Exception e) {

                }
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });


    }

    public void EscribirEnFichero(String correo, String contrasena) throws IOException {
        File fichero = new File("user.txt");
        OutputStreamWriter osw= new OutputStreamWriter(openFileOutput("user.txt", Activity.MODE_PRIVATE));
        osw.write(correo +"\n" + contrasena);
        osw.flush();
        osw.close();
    }
}
