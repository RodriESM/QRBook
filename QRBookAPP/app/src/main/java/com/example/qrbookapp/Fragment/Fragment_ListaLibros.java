package com.example.qrbookapp.Fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.qrbookapp.Adapter.AdaptadorLibros;
import com.example.qrbookapp.Class.Libro;
import com.example.qrbookapp.R;

import java.util.ArrayList;
//Tiene que ser un fragment, no una actividad...
public class Fragment_ListaLibros extends Fragment {

    GridView gvListaLibros;
    AdaptadorLibros adaptadorLibros;
//Método para crear el fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_gridview_libros, container, false);
        ArrayList<Libro> arrayLibros= new ArrayList<>();
        //La vista donde pondremos los libros
        gvListaLibros=rootView.findViewById(R.id.gvListaLibros);

        //Datos a meter
        Libro libro= new Libro("23626262","Arthur Conan Doyle","Sherlock Holmes","anaya","El más famoso detective de todos los tiempos en una nueva aventura",1855);
        Libro libro2= new Libro("23626262","Miguel de Cervantes Saavedar","Don Quijote de La Mancha","anaya","El más audaz de los caballeros",1755);
        Libro libro3= new Libro("23626262","Dan Brown","El Codigo Da Vinci","anaya","Una aventura trepidante y llena de suspense",2002);

        arrayLibros.add(libro);
        arrayLibros.add(libro2);
        arrayLibros.add(libro3);

        //El adaptador...
        adaptadorLibros =new AdaptadorLibros(getActivity().getApplicationContext(),arrayLibros);

        //Añadir al gridview los libros
        gvListaLibros.setAdapter(adaptadorLibros);
        return rootView;
    }

 }

