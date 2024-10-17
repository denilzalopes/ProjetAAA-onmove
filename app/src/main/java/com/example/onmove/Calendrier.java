package com.example.onmove;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Calendrier extends Fragment {

    private CalendarView calendarView;
    private TextView textViewSelectedDate;  // Ajout de la référence au TextView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendrier, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        textViewSelectedDate = view.findViewById(R.id.textViewSelectedDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                String formattedDate = sdf.format(selectedDate.getTime());

                Toast.makeText(requireContext(), "Selected Date: " + formattedDate, Toast.LENGTH_SHORT).show();

                handleSelectedDate(selectedDate);

                // Ajouter la redirection vers une autre activité
                redirectToFragment();
            }
        });

        return view;
    }

    private void handleSelectedDate(Calendar selectedDate) {
        updateUIWithSelectedDate(selectedDate);
    }

    private void updateUIWithSelectedDate(Calendar selectedDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = sdf.format(selectedDate.getTime());
        textViewSelectedDate.setText("Selected Date: " + formattedDate);
    }

    private void redirectToFragment() {
        Log.d("Calendrier", "redirectToFragment() called");

        // Créez une instance du fragment HeureFragment.
        Hours heureFragment = new Hours();

        // Obtenez le gestionnaire de fragments de l'activité parente.
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Remplacez le fragment actuel par le fragment HeureFragment.
        fragmentManager.beginTransaction()
                .replace(R.id.container, heureFragment) // Remplacez R.id.fragment_container par l'ID réel de votre conteneur de fragments.
                .addToBackStack(null)
                .commit();
    }
}