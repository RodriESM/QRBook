package com.example.qrbookapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qrbookapp.Fragment.Fragment_ListaLibros;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Modificar para que dependiendo de la posición del tab muestre un fragment u otro
    @Override
    public Fragment getItem(int position) {

        return new Fragment_ListaLibros();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }
}
