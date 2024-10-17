package com.example.onmove;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Hours extends Fragment {
    private EditText heureEditText;
    private EditText minuteEditText;

    private EditText descriptionEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hours, container, false);
        heureEditText = view.findViewById(R.id.heure);
        minuteEditText = view.findViewById(R.id.minute);
        descriptionEditText = view.findViewById(R.id.ajouter_RDV);
        TacheViewModel tacheViewModel = new ViewModelProvider(requireActivity()).get(TacheViewModel.class);

        Button augmenterHeureButton = view.findViewById(R.id.AugmenterHeure);
        Button diminuerHeureButton = view.findViewById(R.id.DiminuerHeure);
        Button augmenterMinuteButton = view.findViewById(R.id.AugmenterMinute);
        Button diminuerMinuteButton = view.findViewById(R.id.DiminuerMinute);

        augmenterHeureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                augmenterHeure();
            }
        });

        diminuerHeureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diminuerHeure();
            }
        });

        augmenterMinuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                augmenterMinute();
            }
        });

        diminuerMinuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diminuerMinute();
            }
        });
        Button ajouterButton = view.findViewById(R.id.buttonRendezVous);
        ajouterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les valeurs des champs
                String heure = heureEditText.getText().toString();
                String minute = minuteEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // Vérifier si la description est vide
                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(getActivity(), "Veuillez saisir une description", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer une instance de TaskFragment et passer les données
                tacheViewModel.heure = heure;
                tacheViewModel.minute = minute;
                tacheViewModel.description = description;

                // Remplace le fragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new Tache());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();



            }
        });

        return view;

    }



    private void augmenterHeure() {
        int heures = Integer.parseInt(heureEditText.getText().toString());
        heures = (heures + 1) % 24;
        heureEditText.setText(String.format("%02d", heures));
    }

    private void diminuerHeure() {
        int heures = Integer.parseInt(heureEditText.getText().toString());
        heures = (heures - 1 + 24) % 24;
        heureEditText.setText(String.format("%02d", heures));
    }

    private void augmenterMinute() {
        int minutes = Integer.parseInt(minuteEditText.getText().toString());
        minutes = (minutes + 1) % 60;
        minuteEditText.setText(String.format("%02d", minutes));
    }

    private void diminuerMinute() {
        int minutes = Integer.parseInt(minuteEditText.getText().toString());
        minutes = (minutes - 1 + 60) % 60;
        minuteEditText.setText(String.format("%02d", minutes));
    }

}