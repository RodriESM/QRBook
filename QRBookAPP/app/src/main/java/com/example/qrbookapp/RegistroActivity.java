package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qrbookapp.Database.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroActivity extends AppCompatActivity {

    ConnectionClass conexionMySQL = new ConnectionClass();
    Button btnRegistrarse;
    EditText etEmail,etUsuario,etPassword,etPassword2;
    private int puerto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etEmail=findViewById(R.id.etEmail);
        etUsuario=findViewById(R.id.etUsuario);
        etPassword=findViewById(R.id.etPassword);
        etPassword2=findViewById(R.id.etPassword2);


        btnRegistrarse=findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email= etEmail.getText().toString();
                String ps1= etPassword.getText().toString();
                String ps2= etPassword2.getText().toString();
                String usuario=etUsuario.getText().toString();

                if (ps1.equals(ps2)) {
                    try {

                        Connection f = ConnectionClass.con;
                        PreparedStatement ps = f.prepareStatement("insert into USUARIO(correo,password,usuario) values(?,?,?)");
                        ps.setString(1,email);
                        ps.setString(2,ps1);
                        ps.setString(3,usuario);
                        ps.executeUpdate();
                        Intent i = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(i);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{

                    Toast.makeText(getApplicationContext(),"Las contraseñas introducidas no son iguales.Intentelo de nuevo",Toast.LENGTH_LONG);

                }

            }
        });

    }
}
