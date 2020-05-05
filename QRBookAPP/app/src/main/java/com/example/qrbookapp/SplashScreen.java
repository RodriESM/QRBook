package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent nuevaActividad = new Intent(getApplicationContext(), modificacion.class);
                //Intent intentPri = new Intent().setClass(SPlashScreeeen.this, MainActivity.class);
                startActivity(nuevaActividad);
                finish();
            }
        };

        Timer timer= new Timer();
        timer.schedule(task,2000);
    }

}
