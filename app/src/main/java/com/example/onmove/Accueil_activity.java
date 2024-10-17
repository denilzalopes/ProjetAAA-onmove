package com.example.onmove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Accueil_activity extends AppCompatActivity {

    private Accueil homeFragment;
    private Calendrier calenderFragment;
    private Tache taskFragment;
    private Profile profilFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        homeFragment = new Accueil();
        calenderFragment = new Calendrier();
        taskFragment = new Tache();
        profilFragment = new Profile();

        showFragment(homeFragment);



    bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
        if (item.getItemId() == R.id.home  ) {
            showFragment(homeFragment);
            return true;

        } else if (item.getItemId() == R.id.calender  ) {
            showFragment(calenderFragment);
            return true;


        } else if (item.getItemId() == R.id.task  ) {
            showFragment(taskFragment);
            return true;

        } else if (item.getItemId() == R.id.profil  ) {
            showFragment(profilFragment);
            return true;
        }
        return false;
    });    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}