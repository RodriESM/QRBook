package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaLibros extends AppCompatActivity {

    GridView gvListaLibros;
    AdaptadorBiblioteca adaptadorBiblioteca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_libros);

        ArrayList<Libros> arrayLibros= new ArrayList<>();
        gvListaLibros=findViewById(R.id.gvListaLibros);

        Libros libro= new Libros(23626262,"Arthur Conan Doyle","Sherlock Holmes","anaya","El más famoso detective de todos los tiempos en una nueva aventura",1855);
        Libros libro2= new Libros(23626262,"Miguel de Cervantes Saavedar","Don Quijote de La Mancha","anaya","El más audaz de los caballeros",1755);
        Libros libro3= new Libros(23626262,"Dan Brown","El Codigo Da Vinci","anaya","Una aventura trepidante y llena de suspense",2002);

        arrayLibros.add(libro);
        arrayLibros.add(libro2);
        arrayLibros.add(libro3);

        adaptadorBiblioteca=new AdaptadorBiblioteca(this,arrayLibros);

        gvListaLibros.setAdapter(adaptadorBiblioteca);

        gvListaLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libros libroSeleccionado=(Libros)adaptadorBiblioteca.getItem(position);
                Intent i = new Intent(getApplicationContext(),LibrosCaracteristicasAmpliado.class);
                i.putExtra("libros",libroSeleccionado);
                startActivity(i);

            }
        });





    }
}
