package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroActivity extends AppCompatActivity {

    Connection conexionMySQL;
    Button btnSignin;
    private String usuario, password, ip, bdDatos;
    EditText etEmail,etPassword,etPassword2;
    private int puerto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);



        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    if(etPassword.getText().equals(etPassword2.getText())) {
                        conectarBDMySQL(usuario, password, ip, puerto, bdDatos);
                        Statement st = conexionMySQL.createStatement();
                        st.executeQuery("insert into usuarios (correo,password) values '" + etEmail.getText() + "','" + etPassword.getText() + "'");
                        Toast.makeText(getApplicationContext(),"Enhorabuena, se ha registrado en nuestra aplicación.Sea bienvenido",Toast.LENGTH_LONG);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Las contraseñas introducidas no son iguales.Intentelo de nuevo",Toast.LENGTH_LONG);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

    }


    public void conectarBDMySQL (String usuario, String contrasena,
                                 String ip, int puerto, String bdDatos)
    {
        if (conexionMySQL == null) {
            String urlConexionMySQL = "";
            if (bdDatos != "")
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + puerto + "/" + bdDatos;
            else
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + puerto;
            if (usuario != "" & contrasena != "" & ip != "" & puerto != 0) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexionMySQL = DriverManager.getConnection(urlConexionMySQL,
                            usuario, contrasena);
                } catch (ClassNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
