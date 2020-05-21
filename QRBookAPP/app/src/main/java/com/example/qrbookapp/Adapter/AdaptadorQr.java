package com.example.qrbookapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qrbookapp.Class.QR;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(miContexto);
        convertView=layoutInflater.inflate(R.layout.item_qr, null);

        TextView tvTituloQr= convertView.findViewById(R.id.tvTituloQr);
        WebView wvqrseleccionador =convertView.findViewById(R.id.wvqrseleccionador);

        final WebSettings ajustesVisorWeb = wvqrseleccionador.getSettings();

        ajustesVisorWeb.setJavaScriptEnabled(true);
        ajustesVisorWeb.setUseWideViewPort(true);
        ajustesVisorWeb.setLoadWithOverviewMode(true);
        tvTituloQr.setText(miArrayList.get(position).getNombre());
        wvqrseleccionador.loadUrl(miArrayList.get(position).getUrl());


        return convertView;
    }
}
