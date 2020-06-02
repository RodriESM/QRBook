package com.example.qrbookapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qrbookapp.Fragment.Fragment_ListaLibros;
import com.example.qrbookapp.Fragment.Fragment_ListaLibros_Usuario;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private CharSequence[] Titles;
    private Fragment_ListaLibros FL = new Fragment_ListaLibros();
    private Fragment_ListaLibros_Usuario FLU = new Fragment_ListaLibros_Usuario();

    //Pasamos el array de los títulos.
    public ViewPagerAdapter(FragmentManager fm, CharSequence[] mTitles) {
        super(fm);
        this.Titles = mTitles;
    }

    //Modificar para que dependiendo de la posición del tab muestre un fragment u otro
    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Dependiendo del de la tabulación abrirá uno u otro y por defecto la lista de libros.
        if (position == 1) {
            return FLU;
        }
        return FL;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    //Ponemos el título que le pertenece a cada tab.
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

}
