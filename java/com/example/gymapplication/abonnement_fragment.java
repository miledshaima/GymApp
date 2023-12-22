package com.example.gymapplication;

import static android.widget.Toast.makeText;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.*;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class abonnement_fragment extends Fragment {

    private EditText editTextNomComplet, editTextNumeroTelephone, editTextAdresse;
    private Spinner spinnerTypeCours;
    private List<reserver> historiqueReservations = new ArrayList<>();

    private ProgressDialog progressDialog;


    private static int nbrDisponibles = 10; // Remplacez par le nombre initial de places disponibles
    private static int placesDisponibles = 20; // Initial value
    private static int nplacesDisponibles = 12; // Initial value
    private static int nbplacesDisponibles = 5; // Initial value

    private void updateDetailsFragment(String typeCours) {
        Details.updatePlaces(typeCours);
    }
    private void updateDetailsFragment1(String typeCours) {
        Details1.updatePlaces1(typeCours);
    }
    private void updateDetailsFragment2(String typeCours) {
        Details2.updatePlaces2(typeCours);
    }
    private void updateDetailsFragment3(String typeCours) {
        Details3.updatePlaces3(typeCours);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_abonnement, container, false);
        TextView editTextNomComplet=view.findViewById(R.id.editTextNom);
        TextView editTextNumeroTelephone=view.findViewById(R.id.editTextTelephone);
        TextView editTextAdresse=view.findViewById(R.id.editTextAdresse);
        ProgressBar bar =view.findViewById(R.id.progressBar);


        // Récupérer les vues du formulaire
        Spinner spinnerTypeCours = view.findViewById(R.id.spinnerTypeCours);
        Spinner spinnerDateHeure = view.findViewById(R.id.spinnerDateHeure);
        RadioGroup radioGroupAbonnement = view.findViewById(R.id.radioGroupAbonnement);
        RadioButton radioButtonParSeance = view.findViewById(R.id.radioButtonParSeance);
        RadioButton radioButtonParMois = view.findViewById(R.id.radioButtonParMois);
        RadioButton radioButtonParAn = view.findViewById(R.id.radioButtonParAn);
        Button buttonReserver = view.findViewById(R.id.buttonReserver);

        // Remplir le Spinner avec les types de cours
        ArrayAdapter<CharSequence> typeCoursAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.types_cours,
                android.R.layout.simple_spinner_item
        );
        typeCoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeCours.setAdapter(typeCoursAdapter);

        // Remplir le Spinner avec les dates spécifiques au type de danse (à ajuster selon vos besoins)
        ArrayAdapter<CharSequence> datesDanseAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.dates_danse,
                android.R.layout.simple_spinner_item
        );
        datesDanseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDateHeure.setAdapter(datesDanseAdapter);

        // Définir le listener pour le bouton de réservation
        buttonReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Récupérer les valeurs saisies par l'utilisateur
                String nom = editTextNomComplet.getText().toString();
                String telephone = editTextNumeroTelephone.getText().toString();
                String adresse = editTextAdresse.getText().toString();
                String typeCours = spinnerTypeCours.getSelectedItem().toString();
                updateDetailsFragment(typeCours);
                updateDetailsFragment1(typeCours);
                updateDetailsFragment2(typeCours);
                updateDetailsFragment3(typeCours);
                String dateHeure = spinnerDateHeure.getSelectedItem().toString();

                String typeAbonnement = "";
                int selectedRadioButtonId = radioGroupAbonnement.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = view.findViewById(selectedRadioButtonId);
                    typeAbonnement = selectedRadioButton.getText().toString();
                }

                if (nom.isEmpty() || telephone.isEmpty() || adresse.isEmpty() || typeCours.isEmpty() || dateHeure.isEmpty()) {
                    // Afficher un message d'erreur (par exemple, utiliser Toast)
                    Toast.makeText(requireContext(), "Veuillez remplir tous les champs du formulaire", Toast.LENGTH_SHORT).show();
                }else {
                    // Show a progress dialog
                    progressDialog = new ProgressDialog(requireContext());
                    progressDialog.setMessage("En cours de réservation...");
                    progressDialog.setCancelable(false); // Prevent users from canceling the dialog
                    progressDialog.show();

                    // Simulate a delay (you can replace this with your actual reservation logic)
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Hide the progress dialog
                            progressDialog.dismiss();

                            // Affichez la toast après le délai spécifié
                            LongToast.showLongToast(requireContext(), "Votre réservation est effectuée avec succès");

                            // Utilisez Handler pour lancer l'activité Details après un délai supplémentaire
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i;
                                    if (typeCours.equals("musculaion")) {
                                        i = new Intent(getContext(), Details.class);
                                    } else if (typeCours.equals("zumba")) {
                                        i = new Intent(getContext(), Details1.class);
                                    } else if (typeCours.equals("cardio")) {
                                        i = new Intent(getContext(), Details2.class);
                                    }
                                    else if (typeCours.equals("cross training")) {
                                        i = new Intent(getContext(), Details3.class);
                                    } else {
                                        // Par défaut, utilisez l'activité Details
                                        i = new Intent(getContext(), Home.class);
                                    }
                                    startActivity(i);
                                }
                            }, 2000); // Délai supplémentaire pour la toast en millisecondes (20000 ms = 20 secondes)
                        }
                    }, 3000);

                    // Les champs sont valides, procéder à la réservation
                    reserver reservation = new reserver(nom, telephone, adresse, typeCours, dateHeure, typeAbonnement);
                    historique_fragment historiqueFragment = new historique_fragment();

                    // Passer les données au fragment historique
                    Bundle bundle = new Bundle();
                    bundle.putString("nom", reservation.getNomComplet());
                    bundle.putString("typeCours", reservation.getTypeCours());
                    bundle.putString("dateHeure", reservation.getDate() + " " + reservation.getDate());


                 /*   historiqueFragment.setArguments(bundle);
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fram, historiqueFragment)
                            .addToBackStack(null)
                            .commit(); */
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Endspoint.save_url, response -> {
                        // Handle the response from the server
                        progressDialog.dismiss();
                        LongToast.showLongToast(requireContext(), "Votre réservation est effectuée avec succès");
                        // Handle navigation or other actions after successful reservation
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle errors
                                    progressDialog.dismiss();
                                    Toast.makeText(requireContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                                    // Print the error response
                                    if (error.networkResponse != null && error.networkResponse.data != null) {
                                        String errorResponse = new String(error.networkResponse.data);
                                        Log.e("VolleyError", "Error Response: " + errorResponse);
                                    }
                                }

                }) {
                        @Override
                        protected Map<String, String> getParams() {
                            // Set POST parameters
                            Map<String, String> params = new HashMap<>();
                            params.put("nom", nom);
                            params.put("telephone", telephone);
                            params.put("adresse", adresse);
                            params.put("typeCours", typeCours);
                            params.put("dateHeure", dateHeure);
                            return params;
                        }
                    };
                    // Add the request to the RequestQueue
                    VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


                }
            }
        });

        return view;
    }

  }