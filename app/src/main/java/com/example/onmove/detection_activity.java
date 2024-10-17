package com.example.onmove;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

public class
detection_activity extends AppCompatActivity {

    private static final String TAG = "DetectionActivity";
    private static final String PREF_KEY_LAST_SENSOR_MESSAGE = "lastSensorMessage";

    private TextView updatedField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);

        // Initialiser la vue ici
        updatedField = findViewById(R.id.updated_field);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.buttonRappel) {
            // Gérer le clic sur l'élément de menu
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }


    public void updateView(String sensorMessage) {
        try {
            SharedPreferences sharedPref = getSharedPreferences("com.example.onmove.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE);

            if (sensorMessage == null || sensorMessage.equals("")) {
                sensorMessage = sharedPref.getString(PREF_KEY_LAST_SENSOR_MESSAGE, "Detection inactivité");
            }
            final String tempSensorMessage = sensorMessage;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Utiliser la vue initialisée
                    updatedField.setText(tempSensorMessage);
                }
            });
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(PREF_KEY_LAST_SENSOR_MESSAGE, sensorMessage);
            editor.apply(); // Utiliser apply() au lieu de commit()
        } catch (Exception ex) {
            Log.e(TAG, "Erreur lors de la mise à jour de la vue", ex);
        }
    }

    public void createNotification(String notificationTitle, String notificationMessage) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_template_icon_bg)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage);

        Intent resultIntent = new Intent(this, detection_activity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(detection_activity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(100, mBuilder.build());
    }
}
