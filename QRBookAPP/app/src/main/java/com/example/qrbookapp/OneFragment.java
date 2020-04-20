package com.example.qrbookapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class OneFragment extends Fragment {
    View view;
    TextView txtTitle;
    String title;

    public OneFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_one, container, false);
        txtTitle = (TextView)view.findViewById(R.id.txtTitle);

        if (getArguments() != null){
            title = getArguments().getString("title");
        }
        txtTitle.setText(title);

        return view;
    }
}
