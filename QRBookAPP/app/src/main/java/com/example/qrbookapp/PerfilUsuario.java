package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.qrbookapp.Database.ConnectionClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PerfilUsuario extends AppCompatActivity {

    EditText etUsuarioCam,etNombre,etApellido1,etApellido2,etContrasena,etRepiteContrasena;
    ImageView imgUsuario;
    Button btnRealizarCambios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        etUsuarioCam = findViewById(R.id.etUsuarioCam);
        etNombre = findViewById(R.id.etNombre);
        etApellido1 = findViewById(R.id.etApellido1);
        etApellido2 = findViewById(R.id.etApellido2);
        etContrasena = findViewById(R.id.etContrasena);
        etRepiteContrasena = findViewById(R.id.etRepiteContrasena);
        imgUsuario = findViewById(R.id.imgUsuario);
        btnRealizarCambios = findViewById(R.id.btnRealizarCambios);

        String usuario = etUsuarioCam.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido1 = etApellido1.getText().toString();
        String apellido2 = etApellido2.getText().toString();
        String contrasena = etContrasena.getText().toString();
        String repContrasena = etRepiteContrasena.getText().toString();

        try {
            Connection connection = ConnectionClass.con;
            PreparedStatement ps = null;
            if (contrasena.equals(repContrasena)) {
                ps = connection.prepareStatement("update USUARIO set usuario=?,password=?,nombre=?,apellido1=?,apellido2=? where correo=?");


                ps.setString(1, usuario);
                ps.setString(2, contrasena);
                ps.setString(3, nombre);
                ps.setString(4, apellido1);
                ps.setString(5, apellido2);
                ps.setString(6, "1234");

                ps.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);

    }
}
