package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blikoon.qrcodescanner.QrCodeActivity;

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnSignin;
    EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin=findViewById(R.id.btnLogin);
        btnSignin=findViewById(R.id.btnSignin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,InicioActivity.class);
                startActivity(i);
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(i);
                finish();
            }
        });
    }



}
