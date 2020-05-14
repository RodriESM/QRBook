package com.example.qrbookapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
//Tiene que ser un fragment, no una actividad...
public class ListaLibros extends Fragment {

    GridView gvListaLibros;
    AdaptadorBiblioteca adaptadorBiblioteca;
//Método para crear el fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_lista_libros, container, false);
        ArrayList<Libros> arrayLibros= new ArrayList<>();
        //La vista donde pondremos los libros
        gvListaLibros=rootView.findViewById(R.id.gvListaLibros);

        //Datos a meter
        Libros libro= new Libros("23626262","Arthur Conan Doyle","Sherlock Holmes","anaya","El más famoso detective de todos los tiempos en una nueva aventura",1855);
        Libros libro2= new Libros("23626262","Miguel de Cervantes Saavedar","Don Quijote de La Mancha","anaya","El más audaz de los caballeros",1755);
        Libros libro3= new Libros("23626262","Dan Brown","El Codigo Da Vinci","anaya","Una aventura trepidante y llena de suspense",2002);

        arrayLibros.add(libro);
        arrayLibros.add(libro2);
        arrayLibros.add(libro3);

        //El adaptador...
        adaptadorBiblioteca=new AdaptadorBiblioteca(getActivity().getApplicationContext(),arrayLibros);

        //Añadir al gridview los libros
        gvListaLibros.setAdapter(adaptadorBiblioteca);
        return rootView;
    }

 }

