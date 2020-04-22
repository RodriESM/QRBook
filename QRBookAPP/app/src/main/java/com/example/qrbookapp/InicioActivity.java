package com.example.qrbookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class InicioActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private int[] tabIcons = {R.drawable.facebook, R.drawable.google, R.drawable.flashlight_turn_off, R.drawable.flashlight_turn_on};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

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
        for (int i = 0; i < 4; i++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    private void loadViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment((newInstance(main_escaner.class)));
        adapter.addFragment((newInstance(R.layout.activity_main)));
        adapter.addFragment((newInstance(R.layout.activity_registro)));
        adapter.addFragment((newInstance(R.layout.activity_vista_escaner)));
        viewPager.setAdapter(adapter);
    }

    private OneFragment newInstance(Class clase){
        Bundle bundle = new Bundle();
        OneFragment fragment = new OneFragment(clase);
        fragment.setArguments(bundle);

        return fragment;
    }

    private OneFragment newInstance(int clase){
        Bundle bundle = new Bundle();
        OneFragment fragment = new OneFragment(clase);
        fragment.setArguments(bundle);

        return fragment;
    }
}