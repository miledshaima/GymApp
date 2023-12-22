package com.example.gymapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class historique_fragment extends Fragment {
    private List<reserver> historiqueList;
    private RecyclerView recyclerView;
    private adapterhisto historiqueAdapter;

    public historique_fragment() {
        // Constructeur par défaut requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_historique, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String nom = bundle.getString("nom");
            String typeCours = bundle.getString("typeCours");
            String dateHeure = bundle.getString("dateHeure");

            // Trouver les TextViews dans le layout de ce fragment
            TextView textViewNom = view.findViewById(R.id.textViewNom);

            TextView textViewTypeCours = view.findViewById(R.id.textViewTypeCours);
            TextView textViewDateHeure = view.findViewById(R.id.textViewDateHeure);

            // Mettre à jour les TextViews avec les données
            textViewNom.setText("Nom complet: " + nom);
            textViewTypeCours.setText("Type de cours: " + typeCours);
            textViewDateHeure.setText("Date et Heure: " + dateHeure);


            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            historiqueAdapter = new adapterhisto(historiqueList);
            recyclerView.setAdapter(historiqueAdapter);
        }

        return view;
    }
}


















 /*       Bundle bundle = getArguments();
        if (bundle != null) {
            String nom = bundle.getString("nom");
            String telephone = bundle.getString("telephone");
            String adresse = bundle.getString("adresse");
            String typeCours = bundle.getString("typeCours");
            String dateHeure = bundle.getString("dateHeure");
            String type = bundle.getString("type");

            List<reserver> historiqueList = new ArrayList<>();
            historiqueList.add(new reserver(nom, telephone, adresse, typeCours, dateHeure, ));

            // Configurez le RecyclerView avec l'adaptateur personnalisé
            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHistorique);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            adapterhisto historiqueAdapter = new adapterhisto(historiqueList);
            recyclerView.setAdapter(historiqueAdapter);
        }

        return view;
    }
}


            // Initialisez votre liste d'historique avec des données fictives (à remplacer par vos données réelles)
            historiqueList = new ArrayList<>();
            historiqueList.add(new reserver("John Doe", "123456789", "123 Main St", "Danse", "2023-12-01", "10:00", "par seance"));
            historiqueList.add(new reserver("Jane Doe", "987654321", "456 Oak St", "Gym", "2023-12-05", "14:00", "Par mois"));
            historiqueList.add(new reserver("Alice Wonderland", "555666777", "789 Pine St", "Fitness", "2023-12-10", "09:30", "Par an"));

            // Configurez le RecyclerView
            recyclerView = view.findViewById(R.id.recyclerViewHistorique);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            historiqueAdapter = new adapterhisto(historiqueList);
            recyclerView.setAdapter(historiqueAdapter);
            return view;
        }
    }*/