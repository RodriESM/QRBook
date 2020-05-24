package com.example.qrbookapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.qrbookapp.Class.QR;
import com.example.qrbookapp.InicioActivity;
import com.example.qrbookapp.QrCaracteristicasAmpliado;
import com.example.qrbookapp.R;

import java.util.ArrayList;

public class AdaptadorQr extends BaseAdapter {
    private Context miContexto;
    private ArrayList<QR> miArrayList;

    public AdaptadorQr(Context miContexto, ArrayList<QR> miArrayList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(miContexto);
        convertView=layoutInflater.inflate(R.layout.item_qr, null);

        Button tvTituloQr= convertView.findViewById(R.id.tvTituloQr);
        WebView wvqrseleccionador =convertView.findViewById(R.id.wvqrseleccionador);

        final WebSettings ajustesVisorWeb = wvqrseleccionador.getSettings();

        ajustesVisorWeb.setJavaScriptEnabled(true);
        ajustesVisorWeb.setUseWideViewPort(true);
        ajustesVisorWeb.setLoadWithOverviewMode(true);
        tvTituloQr.setText(miArrayList.get(position).getNombre());
        wvqrseleccionador.loadUrl(miArrayList.get(position).getUrl());

        tvTituloQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(miContexto.getApplicationContext(), QrCaracteristicasAmpliado.class);
                i.putExtra("descripcion",miArrayList.get(position).getDescripcion());
                i.putExtra("isbn",miArrayList.get(position).getIsbn());
                i.putExtra("nombre",miArrayList.get(position).getNombre());
                i.putExtra("tipo",miArrayList.get(position).getTipo());
                i.putExtra("url",miArrayList.get(position).getUrl());
                i.putExtra("pag",miArrayList.get(position).getPagina());
                miContexto.startActivity(i);
            }
        });

        return convertView;
    }
}
