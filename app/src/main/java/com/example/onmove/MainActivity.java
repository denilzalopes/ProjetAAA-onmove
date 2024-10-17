package com.example.onmove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rederiger vers la page principale aprés 3secondes
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
// Utilisation du contexte de l'application
                Intent intent = new Intent(MainActivity.this, Login_activity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }

}

