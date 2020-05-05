package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class modificacion extends AppCompatActivity {

    EditText etUsuario,etNombre,etApellido1,etApellido2,etContrasena,etRepiteContrasena;
    ImageView imgUsuario;
    Button btnRealizarCambios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion);

        etUsuario=findViewById(R.id.etUsuario);
        etNombre=findViewById(R.id.etNombre);
        etApellido1=findViewById(R.id.etApellido1);
        etApellido2=findViewById(R.id.etApellido2);
        etContrasena=findViewById(R.id.etContrasena);
        etRepiteContrasena=findViewById(R.id.etRepiteContrasena);
        imgUsuario=findViewById(R.id.imgUsuario);
        btnRealizarCambios=findViewById(R.id.btnRealizarCambios);


        
        
    }
}
