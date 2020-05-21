package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.example.qrbookapp.Adapter.ViewPagerAdapter;
import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Fragment.OneFragment;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class InicioActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private int[] tabIcons = {R.drawable.facebook, R.drawable.google, R.drawable.flashlight_turn_off, R.drawable.flashlight_turn_on};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        com.getbase.floatingactionbutton.FloatingActionButton btnCamera = findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(InicioActivity.this, Escaner.class);
                startActivity(i);
            }
        });

        com.getbase.floatingactionbutton.FloatingActionButton btnUser = findViewById(R.id.btnUser);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(InicioActivity.this, PerfilUsuario.class);
                startActivity(i);
            }
        });

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        loadViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabIcons();
        //iconColor(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()),"#3b5998");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //iconColor(tab, "#3b5998");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //iconColor(tab, "#E0E0E0");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void iconColor(TabLayout.Tab tab, String color){
        tab.getIcon().setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
    }

    private void tabIcons(){
        for (int i = 0; i < 2; i++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    private void loadViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment((newInstance(R.layout.activity_gridview_libros)));
        //Cargamos los fragment deseados al inicio, además los hemos separado en fragment distintos para dar más independencia a la app.
        adapter.addFragment((newInstance(R.layout.activity_gridview_libros)));
        adapter.addFragment((newInstance(R.layout.activity_gridview_libros_usuario)));
        //adapter.addFragment((newInstance(ListaLibros.class)));
        viewPager.setAdapter(adapter);
    }

    private OneFragment newInstance(Class clase){
        Bundle bundle = new Bundle();
        OneFragment fragment = new OneFragment(clase);
        fragment.setArguments(bundle);

        return fragment;
    }

    private OneFragment newInstance(int referencia){
        Bundle bundle = new Bundle();
        OneFragment fragment = new OneFragment(referencia);
        fragment.setArguments(bundle);

        return fragment;
    }



}