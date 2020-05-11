package com.example.qrbookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorBiblioteca extends BaseAdapter {
    private Context miContexto;
    private ArrayList<Libros> miArrayList;

    public AdaptadorBiblioteca(Context miContexto, ArrayList<Libros> miArrayList) {
        this.miContexto = miContexto;
        this.miArrayList = miArrayList;
    }

    @Override
    public int getCount() {
        return miArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return miArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //TO_DO No se que id Meterle, en este caso le metere el ISBN
        return Long.parseLong(miArrayList.get(position).getIsbn());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(miContexto);
        convertView=layoutInflater.inflate(R.layout.gridview_libros, null);

        TextView tvTitulo= convertView.findViewById(R.id.tvTitulo);
        TextView  tvAutor=convertView.findViewById(R.id.tvAutor);
        ImageView imgLibro=convertView.findViewById(R.id.imgLibro);

        tvTitulo.setText(miArrayList.get(position).getTitulo());
        tvAutor.setText(miArrayList.get(position).getAutor());

        //TO_DO Aquí habria que poner la imagenn corrrespondiente al libro pero no se donde las vamos a guardar
        //imgLibro.setImageResource(miArrayList.get(position).);


        return convertView;
    }
}
