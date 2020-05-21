package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qrbookapp.Class.Libro;
import com.squareup.picasso.Picasso;

public class LibrosCaracteristicasAmpliado extends AppCompatActivity {

    TextView tvTituloAmpliado,tvAutorAmpliado,tvEditorialAmpliado,tvAnioAmpliado,tvSinopsisAmpliado,tvIdiomaAmpliado,tvGeneroAmpliado,tvIsbnAmpliado;
    ImageView imgLibroAmpliado;
    Button btnQrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros_caracteristicas_ampliado);

        tvTituloAmpliado=findViewById(R.id.tvTituloAmpliado);
        tvAutorAmpliado=findViewById(R.id.tvAutorAmpliado);
        tvEditorialAmpliado=findViewById(R.id.tvEditorialAmpliado);
        tvAnioAmpliado=findViewById(R.id.tvAnioAmpliado);
        tvSinopsisAmpliado=findViewById(R.id.tvSinopsisAmpliado);

        tvIdiomaAmpliado=findViewById(R.id.tvIdiomaAmpliado);
        tvGeneroAmpliado=findViewById(R.id.tvGeneroAmpliado);
        tvIsbnAmpliado=findViewById(R.id.tvIsbnAmpliado);

        imgLibroAmpliado=findViewById(R.id.imgLibroAmpliado);
        btnQrs = findViewById(R.id.btnQrs);

        Libro libroSeleccionadoAnteriormente=(Libro) getIntent().getSerializableExtra("libros");

        tvTituloAmpliado.setText(libroSeleccionadoAnteriormente.getTitulo());
        tvAutorAmpliado.setText(libroSeleccionadoAnteriormente.getAutor());
        tvEditorialAmpliado.setText(libroSeleccionadoAnteriormente.getEditorial());
        tvAnioAmpliado.setText(String.valueOf(libroSeleccionadoAnteriormente.getYear()));

        tvIdiomaAmpliado.setText(libroSeleccionadoAnteriormente.getIdioma());
        tvGeneroAmpliado.setText(libroSeleccionadoAnteriormente.getGenero());
        tvIsbnAmpliado.setText(libroSeleccionadoAnteriormente.getIsbn());
        //TO_DO Aqui iría la imagen pero dado que aun no se como se introducirá la dejo sin introducir
        //imgLibroAmpliado.setImageResource(libroSeleccionadoAnteriormente.);
        tvSinopsisAmpliado.setText(libroSeleccionadoAnteriormente.getSinopsis());

        Picasso.get()//Context
                .load(libroSeleccionadoAnteriormente.getPortada()) //URL/FILE
                .into(imgLibroAmpliado);

        //imgLibroAmpliado.setImageResource(Integer.parseInt(libroSeleccionadoAnteriormente.getPortada()));

        btnQrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(getApplicationContext(), ListaQr.class);
                //Intent intentPri = new Intent().setClass(SPlashScreeeen.this, MainActivity.class);
                startActivity(nuevaActividad);
            }
        });

    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
