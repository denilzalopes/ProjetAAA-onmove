package com.example.onmove;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Set;

public class Accueil extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_acceuil, container, false);

        Button btn_RDV = rootView.findViewById(R.id.buttonRDV);
        Button btn_rappel = rootView.findViewById(R.id.buttonRappel);
        Button btn_medoc = rootView.findViewById(R.id.buttonMedoc);



            btn_medoc.setOnClickListener(new View.OnClickListener(){

                @Override
               public void onClick(View v) {
                    Intent intent2 = new Intent(getActivity(), Medication.class); // Remplacez "NextActivity" par votre prochaine activité
                   startActivity(intent2);
               }
            });


        btn_RDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacez CalendrierFragment par le nom correct de votre fragment calendrier
                Fragment calendrierFragment = new Calendrier();

                // Obtenez le gestionnaire de fragments et commencez la transaction
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Remplacez le fragment actuel par le fragment calendrier
                fragmentTransaction.replace(R.id.container, calendrierFragment);

                // Ajoutez la transaction à la pile pour permettre le retour en arrière
                fragmentTransaction.addToBackStack(null);

                // Appliquez les modifications
                fragmentTransaction.commit();
            }
        });

        btn_rappel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez un Intent pour démarrer la nouvelle activité
                Intent intent2 = new Intent(getActivity(), detection_activity.class); // Remplacez "NextActivity" par votre prochaine activité
                startActivity(intent2);
            }
        });
        return rootView;
    }


}

