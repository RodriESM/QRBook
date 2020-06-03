package com.example.qrbookapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class OneFragment extends Fragment {
    private View view;
    private Class clase;
    private int referencia = 0;

    public OneFragment(Class clase) {
        this.clase=clase;
    }

    public OneFragment(int referencia) {
        this.referencia=referencia;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
