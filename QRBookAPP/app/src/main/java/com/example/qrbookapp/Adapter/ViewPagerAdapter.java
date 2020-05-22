package com.example.qrbookapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qrbookapp.Fragment.Fragment_ListaLibros;
import com.example.qrbookapp.Fragment.Fragment_ListaLibros_Usuario;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    CharSequence Titles[];

    //Pasamos el array de los títulos.
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[]) {
        super(fm);
        this.Titles=mTitles;
    }

    //Modificar para que dependiendo de la posición del tab muestre un fragment u otro
    @Override
    public Fragment getItem(int position) {
        //Dependiendo del de la tabulación abrirá uno u otro y por defecto la lista de libros.
        switch (position){
            case 0:
                return new Fragment_ListaLibros();
            case 1:
                return new Fragment_ListaLibros_Usuario();
            default:
                return new Fragment_ListaLibros();
        }
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    //Ponemos el título que le pertenece a cada tab.
    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

}
