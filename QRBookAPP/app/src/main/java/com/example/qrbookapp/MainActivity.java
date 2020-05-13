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

public class MainActivity extends AppCompatActivity {
    Button btnLogin,btnSignin;
    EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ConnectionClass().setConnection();

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
            }
        });

        try {
            if (ConnectionClass.con==null){
                new ConnectionClass().setConnection();
                Toast.makeText(getApplicationContext(),"No conectado",Toast.LENGTH_LONG).show();

            }else{

                Toast.makeText(getApplicationContext(),"Conectado",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){

        }
    }



}
