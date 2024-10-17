package com.example.onmove;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class calendrierViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayOfMonth;
    private final calendrierAdapter.OnItemListener onItemListener;

    public calendrierViewHolder(@NonNull View itemView, calendrierAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            String dayText = dayOfMonth.getText().toString();
            onItemListener.onItemClick(position, dayText);

            // Ajouter un effet visuel pour la sélection (par exemple, changer la couleur du texte)
            dayOfMonth.setTextColor(view.getContext().getResources().getColor(R.color.selectedTextColor));
        }
    }

    // Méthode pour réinitialiser l'effet visuel (par exemple, rétablir la couleur du texte d'origine)
    public void resetVisualEffect() {
        dayOfMonth.setTextColor(dayOfMonth.getContext().getResources().getColor(R.color.originalTextColor));
    }
}
