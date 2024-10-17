package com.example.onmove;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class Medication extends AppCompatActivity {

    private EditText medicamentoEditText;
    private EditText dosagemEditText;
    private Spinner intervaloSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        medicamentoEditText = findViewById(R.id.medicamentoEditText);
        dosagemEditText = findViewById(R.id.dosagemEditText);
        intervaloSpinner = findViewById(R.id.intervaloSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.intervalos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intervaloSpinner.setAdapter(adapter);

        Button definirLembreteButton = findViewById(R.id.definirLembreteButton);
        definirLembreteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                definirLembrete();
            }
        });
    }

    private void definirLembrete() {
        String medicamento = medicamentoEditText.getText().toString();
        String dosagem = dosagemEditText.getText().toString();
        String intervalo = intervaloSpinner.getSelectedItem().toString();

        Intent intent = new Intent(Medication.this, AlarmReceiver.class);
        intent.putExtra("medicamento", medicamento);
        intent.putExtra("dosagem", dosagem);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                Medication.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();
        long intervaloMillis = 0;

        if ("6 horas".equals(intervalo)) {
            intervaloMillis = 1000 * 60 * 60 * 6;
        } else if ("8 horas".equals(intervalo)) {
            intervaloMillis = 1000 * 60 * 60 * 8;
        }

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonClick + intervaloMillis,
                pendingIntent);
    }
}
