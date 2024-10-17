package com.example.onmove;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String medicamento = intent.getStringExtra("medicamento");
        String dosagem = intent.getStringExtra("dosagem");

        Toast.makeText(context, "Tomar " + medicamento + " - Dosagem: " + dosagem, Toast.LENGTH_LONG).show();
    }
}
