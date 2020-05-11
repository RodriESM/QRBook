package com.example.qrbookapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class OneFragment extends Fragment {
    View view;
    Class clase;
    int referencia = 0;

    public OneFragment(Class clase) {
        this.clase=clase;
    }

    public OneFragment(int referencia) {
        this.referencia=referencia;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(clase!=null) {
            view = inflater.inflate(referencia, container, false);
           /* Intent intent = new Intent(getActivity(), clase);
            getActivity().startActivity(intent);*/
        }else{
            view = inflater.inflate(referencia, container, false);
        }

        return view;
    }
}
