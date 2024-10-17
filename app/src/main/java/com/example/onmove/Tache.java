package com.example.onmove;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.onmove.R;
import com.example.onmove.TacheViewModel;

public class Tache extends Fragment {
    private TextView textViewHeure;
    private TextView textViewMinute;
    private TextView textViewDescription;

    private TacheViewModel tacheViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tache, container, false);

        // Initialiser les TextView en fonction de votre layout
        textViewHeure = view.findViewById(R.id.textViewHeure);
        textViewMinute = view.findViewById(R.id.textViewMinute);
        textViewDescription = view.findViewById(R.id.textViewDescription);

        // Initialise le ViewModel
        tacheViewModel = new ViewModelProvider(requireActivity()).get(TacheViewModel.class);

        // Mettre à jour les TextView avec les données du ViewModel
        updateTextViews();

        return view; // Retourner la vue créée
    }

    private void updateTextViews() {
        // Mettre à jour les TextView avec les données du ViewModel
        textViewHeure.setText(tacheViewModel.heure);
        textViewMinute.setText(tacheViewModel.minute);
        textViewDescription.setText(tacheViewModel.description);
    }
}
