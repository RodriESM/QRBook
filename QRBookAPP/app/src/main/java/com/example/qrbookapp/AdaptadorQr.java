package com.example.qrbookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorQr extends BaseAdapter {
    private Context miContexto;
    private ArrayList<Libros> miArrayList;

    public AdaptadorQr(Context miContexto, ArrayList<Libros> miArrayList) {
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
        return miArrayList.get(position).getIsbn();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(miContexto);
        convertView=layoutInflater.inflate(R.layout.gridview_qr, null);

        TextView tvTituloQr= convertView.findViewById(R.id.tvTituloQr);
        WebView wvqrseleccionador =convertView.findViewById(R.id.wvqrseleccionador);

        tvTituloQr.setText(miArrayList.get(position).getTitulo());
        wvqrseleccionador.loadUrl(miArrayList.get(position).getUrl());
        //TO_DO Hay que enlazar las imagenes con las que tenga la base de datos
        //imgQr.setText(miArrayList.get(position).getAutor());

        return convertView;
    }
}
