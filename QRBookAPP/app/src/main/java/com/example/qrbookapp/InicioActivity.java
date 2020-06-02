package com.example.qrbookapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.qrbookapp.Adapter.ViewPagerAdapter;
import com.example.qrbookapp.Database.ConnectionClass;
import com.example.qrbookapp.Fragment.OneFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Objects;

public class InicioActivity extends AppCompatActivity {

     TabLayout tabLayout;
    //Array de los iconos
     int[] tabIcons = {R.drawable.library, R.drawable.user_library};
    //Array de los títulos
     CharSequence[] Titles = {"Novedades", "Mi biblioteca"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        com.getbase.floatingactionbutton.FloatingActionButton btnUser = findViewById(R.id.btnUser);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioActivity.this, PerfilUsuario.class);
                startActivity(i);
            }
        });

        com.getbase.floatingactionbutton.FloatingActionButton btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EscribirEnFichero("", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    ConnectionClass.con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(InicioActivity.this, MainActivity.class);
                startActivity(i);
                System.exit(0);
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        loadViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
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

    private void iconColor(@NonNull TabLayout.Tab tab, String color) {
        Objects.requireNonNull(tab.getIcon()).setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
    }

    private void tabIcons() {
        for (int i = 0; i < 2; i++) {
            Objects.requireNonNull(tabLayout.getTabAt(i)).setIcon(tabIcons[i]);
        }
    }

    private void loadViewPager(@NonNull ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles);
        //adapter.addFragment((newInstance(R.layout.activity_gridview_libros)));
        //Cargamos los fragment deseados al inicio, además los hemos separado en fragment distintos para dar más independencia a la app.
        adapter.addFragment((newInstance(R.layout.activity_gridview_libros)));
        adapter.addFragment((newInstance(R.layout.activity_gridview_libros_usuario)));
        //adapter.addFragment((newInstance(ListaLibros.class)));
        viewPager.setAdapter(adapter);
    }

    @NonNull
    private OneFragment newInstance(Class clase) {
        Bundle bundle = new Bundle();
        OneFragment fragment = new OneFragment(clase);
        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    private OneFragment newInstance(int referencia) {
        Bundle bundle = new Bundle();
        OneFragment fragment = new OneFragment(referencia);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void EscribirEnFichero(String correo, String contrasena) throws IOException {
        File fichero = new File("user.txt");
        OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("user.txt", Activity.MODE_PRIVATE));
        osw.write(correo + "\n" + contrasena);
        osw.flush();
        osw.close();
    }


}