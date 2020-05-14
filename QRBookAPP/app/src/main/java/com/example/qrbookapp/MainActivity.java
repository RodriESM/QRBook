package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.qrbookapp.Database.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnSignin;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ConnectionClass().setConnection();
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignin = findViewById(R.id.btnSignin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (ConnectionClass.con == null) {
                        new ConnectionClass().setConnection();
                        Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
                    } else {
                        Connection connection = ConnectionClass.con;
                        PreparedStatement pstUserPass = connection.prepareStatement("SELECT USUARIO from USUARIO  WHERE CORREO like'" + etEmail.getText().toString() + "' OR USUARIO like '" + etEmail.getText().toString() + "' AND PASSWORD like '" + etPassword.getText().toString() + "'");
                        ResultSet rs = pstUserPass.executeQuery();
                            if (rs.next()) {
                                Toast.makeText(MainActivity.this, "Bienvenido " + rs.getString(1), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this, InicioActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(MainActivity.this, "Usuario no enctronado", Toast.LENGTH_SHORT).show();
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
}
