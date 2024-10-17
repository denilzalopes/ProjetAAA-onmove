package com.example.onmove;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {

    private TextView textView;
    private int second = 0;
    private int minute = 0;
    private int hour = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Récupérer la référence du TextView depuis le layout
        textView = findViewById(R.id.timerTextView);

        // Initialiser le Handler dans le thread principal
        handler = new Handler(Looper.getMainLooper());

        // Démarrer le thread lorsque l'activité est créée
        startTimerThread();
    }

    private void startTimerThread() {
        // Créer et démarrer le thread pour la mise à jour du timer
        Thread timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        second++;
                        if (second > 59) {
                            second = 0;
                            minute++;
                            if (minute > 59) {
                                minute = 0;
                                hour++;
                                if (hour > 23) {
                                    hour = 0;
                                }
                            }
                        }

                        // Utiliser le Handler pour mettre à jour l'interface utilisateur depuis le thread de fond
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("Timer: " + String.format("%02d:%02d:%02d", hour, minute, second));
                            }
                        });

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        timerThread.start();
    }
}
