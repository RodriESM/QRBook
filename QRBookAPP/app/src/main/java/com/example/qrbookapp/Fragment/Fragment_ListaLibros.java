package com.example.qrbookapp.Fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.qrbookapp.Adapter.AdaptadorLibros;
import com.example.qrbookapp.Adapter.AdaptadorQr;
import com.example.qrbookapp.Class.Libro;
import com.example.qrbookapp.Class.QR;
import com.example.qrbookapp.Database.ConnectionClass;
import com.example.qrbookapp.InicioActivity;
import com.example.qrbookapp.LibrosCaracteristicasAmpliado;
import com.example.qrbookapp.QrCaracteristicasAmpliado;
import com.example.qrbookapp.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        final View rootView = inflater.inflate(R.layout.activity_gridview_libros, container, false);
        ArrayList<Libro> arrayLibros= new ArrayList<>();
        //La vista donde pondremos los libros
        gvListaLibros=rootView.findViewById(R.id.gvListaLibros);

        try {
            Connection connection = ConnectionClass.con;


            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            ResultSet rs = connection.createStatement().executeQuery("select * from LIBRO order by ID desc");

            //Recorremos todos lo libros que tenemos en la ase de datos y los introducimos en el array
            while(rs.next()){

                Libro libro = new Libro(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
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
                Intent i = new Intent(getContext(), LibrosCaracteristicasAmpliado.class);
                i.putExtra("libros",LibroSeleccionado);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });

        return rootView;
    }

}

