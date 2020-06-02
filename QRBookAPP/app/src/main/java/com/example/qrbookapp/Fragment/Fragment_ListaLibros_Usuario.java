package com.example.qrbookapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qrbookapp.Adapter.AdaptadorLibros;
import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Class.Libro;
import com.example.qrbookapp.Database.ConnectionClass;
import com.example.qrbookapp.LibrosCaracteristicasAmpliado;
import com.example.qrbookapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Tiene que ser un fragment, no una actividad...
public class Fragment_ListaLibros_Usuario extends Fragment {

     GridView gvListaLibros;
     AdaptadorLibros adaptadorLibros;
     SearchView svBuscarUsuarioLibro;
     ArrayList<String> contenidoFicheroRecordado= new ArrayList<>();
     AccesoFichero accesoFichero = new AccesoFichero();
     String correo;
     ArrayList<Libro> arrayLibros;

    //Método para crear el fragment

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.activity_gridview_libros_usuario, container, false);
         arrayLibros= new ArrayList<>();
        //La vista donde pondremos los libros
        gvListaLibros=rootView.findViewById(R.id.gvListaLibros);
        svBuscarUsuarioLibro=rootView.findViewById(R.id.svBuscarUsuarioLibro);

        final String[] datos = getActivity().getApplicationContext().fileList();
        final String nombreFicheroRecordatorio="user.txt";


        if (accesoFichero.archivoExisteEntreFicheros(datos,nombreFicheroRecordatorio)){

            try {
                InputStreamReader isr= new InputStreamReader(getActivity().getApplicationContext().openFileInput(nombreFicheroRecordatorio));
                BufferedReader br= new BufferedReader(isr);
                String linea=br.readLine();

                //Introducimos los datos en un array recorriendo cada linea del fichero, en la primera linea tendrá el usuario y en la segunda la contraseña
                while(linea!=null){
                    contenidoFicheroRecordado.add(linea);
                    linea = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                correo = contenidoFicheroRecordado.get(0);
            }
        }
        try {
            Connection connection = ConnectionClass.con;

            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM LIBRO where ISBN in (SELECT ISBN from  USUARIOLIBRO where CORREO like '"+correo+"')");
            //Recorremos todos lo libros que tenemos en la ase de datos y los introducimos en el array
            while(rs.next()){
                Libro libro = new Libro(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(11));
                arrayLibros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //El adaptador...
        adaptadorLibros =new AdaptadorLibros(getActivity().getApplicationContext(),arrayLibros);

        //Añadir al gridview los libros
        gvListaLibros.setAdapter(adaptadorLibros);

        gvListaLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro LibroSeleccionado=(Libro)adaptadorLibros.getItem(position);
                Intent i = new Intent(getContext().getApplicationContext(), LibrosCaracteristicasAmpliado.class);
                i.putExtra("libros",LibroSeleccionado);
                startActivity(i);
               getActivity().finish();
            }
        });

        svBuscarUsuarioLibro.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                try {
                    Connection connection = ConnectionClass.con;

                    //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
                    ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM LIBRO where (genero like '%"+newText+"%' or autor like '%"+newText+"%' or titulo like '%"+newText+"%') and ISBN in (SELECT ISBN from  USUARIOLIBRO where CORREO like '"+correo+"')");
                   arrayLibros.clear();
                    //Recorremos todos lo libros que tenemos en la ase de datos y los introducimos en el array
                    while(rs.next()){
                        Libro libro = new Libro(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(11));
                        arrayLibros.add(libro);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                //El adaptador...
                adaptadorLibros =new AdaptadorLibros(getActivity().getApplicationContext(),arrayLibros);

                //Añadir al gridview los libros
                gvListaLibros.setAdapter(adaptadorLibros);

                gvListaLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Libro LibroSeleccionado=(Libro)adaptadorLibros.getItem(position);
                        Intent i = new Intent(getContext().getApplicationContext(), LibrosCaracteristicasAmpliado.class);
                        i.putExtra("libros",LibroSeleccionado);
                        startActivity(i);
                        getActivity().finish();
                    }
                });

                return true;
            }
        });

        return rootView;
    }

 }

