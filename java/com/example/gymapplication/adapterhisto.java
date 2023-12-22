package com.example.gymapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterhisto extends RecyclerView.Adapter<adapterhisto.ReservationViewHolder> {
    private List<reserver> historiqueList;

    public adapterhisto(List<reserver> historiqueList) {
        this.historiqueList = historiqueList;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historique, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        reserver reservation = historiqueList.get(position);
        holder.textViewNom.setText("Nom: " + reservation.getNomComplet());
        holder.textViewTypeCours.setText("Type de cours: " + reservation.getTypeCours());
        holder.textViewDateHeure.setText("Date et Heure: " + reservation.getDate() + " " + reservation.getDate());
    }

    @Override
    public int getItemCount() {
        return historiqueList.size();
    }

    public class ReservationViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNom;
        private TextView textViewTypeCours;
        private TextView textViewDateHeure;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewTypeCours = itemView.findViewById(R.id.textViewTypeCours);
            textViewDateHeure = itemView.findViewById(R.id.textViewDateHeure);
        }

        public void bind(reserver reservation) {
            textViewNom.setText("Nom: " + reservation.getNomComplet());
            textViewTypeCours.setText("Type de cours: " + reservation.getTypeCours());
            textViewDateHeure.setText("Date et Heure: " + reservation.getDate());
        }
    }
}