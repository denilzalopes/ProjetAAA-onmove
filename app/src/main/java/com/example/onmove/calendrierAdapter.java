package com.example.onmove;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class calendrierAdapter extends RecyclerView.Adapter<calendrierViewHolder> {
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public calendrierAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public calendrierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendrier_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new calendrierViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull calendrierViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));

        // Mettez en surbrillance la cellule si elle est sélectionnée
        holder.itemView.setSelected(position == selectedPosition);
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }

    // Méthode pour mettre à jour la position sélectionnée
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged(); // Met à jour l'ensemble du RecyclerView
    }
}
