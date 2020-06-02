package com.example.qrbookapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrbookapp.Database.ConnectionClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroActivity extends AppCompatActivity {

    private ConnectionClass conexionMySQL = new ConnectionClass();
    private Button btnRegistrarse;
    private EditText etEmail, etUsuario, etPassword, etPassword2;
    private ImageButton ivFotoPerfil;
    private final int imagen_request = 0;
    private int puerto;
    private Bitmap bitmap;
    private byte[] imagenByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etEmail = findViewById(R.id.etEmail);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);

        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        ivFotoPerfil = findViewById(R.id.ivFotoPerfil);

        ivFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, imagen_request);
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String ps1 = etPassword.getText().toString();
                String ps2 = etPassword2.getText().toString();
                String usuario = etUsuario.getText().toString();

                if (!email.matches("^\\w+@[a-zA-Z_]+?.[a-zA-Z]{2,3}$")) {
                    etEmail.setBackgroundColor(getColor(R.color.alerta));
                    etEmail.setError("Correo inválido o ya en uso.");
                } else if (ps1.length() < 8) {
                    etEmail.setBackgroundColor(getColor(R.color.transparente));
                    etPassword.setBackgroundColor(getColor(R.color.alerta));
                    etPassword2.setBackgroundColor(getColor(R.color.alerta));
                    etPassword.setError("La contraseña debe tener mínimo 8 carácteres.");
                } else if (!ps1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                    etEmail.setBackgroundColor(getColor(R.color.transparente));
                    etPassword.setBackgroundColor(getColor(R.color.alerta));
                    etPassword2.setBackgroundColor(getColor(R.color.alerta));
                    etPassword.setError("La contraseña debe contener una letra mayúscula, minúscula y un número.");
                } else if (!ps1.equals(ps2)) { //Pass: ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$
                    etEmail.setBackgroundColor(getColor(R.color.transparente));
                    etPassword2.setBackgroundColor(getColor(R.color.alerta));
                    etPassword2.setError("Las contraseñas introducidas no son iguales. Intentelo de nuevo.");
                } else {
                    try {

                        bitmap = ((BitmapDrawable) ivFotoPerfil.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        imagenByte = baos.toByteArray();

                        Connection connection = ConnectionClass.con;
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO USUARIO(correo,password,usuario,foto) values(?,MD5(?),?,?)");
                        ps.setString(1, email);
                        ps.setString(2, ps1);
                        ps.setString(3, usuario);
                        ps.setBytes(4, imagenByte);
                        ps.executeUpdate();
                        Intent i = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(i);

                    } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
                        etUsuario.setError("Nombre de usuario ya registrado.");
                        etUsuario.requestFocus(R.id.etUsuario);
                        etUsuario.setBackgroundColor(getColor(R.color.alerta));
                        etEmail.setBackgroundColor(getColor(R.color.transparente));
                        etPassword.setBackgroundColor(getColor(R.color.transparente));
                        etPassword2.setBackgroundColor(getColor(R.color.transparente));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == imagen_request && resultCode == RESULT_OK && data != null) {
            Uri ruta = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ruta);
                ivFotoPerfil.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                imagenByte = baos.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

