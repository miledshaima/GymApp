package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Details1 extends AppCompatActivity {
    Button reserv;
    ImageView second_back_arrow;
    TextView second_title, second_subtitle, second_rating_number, second_rating_number2, more_details;
    RatingBar second_ratingbar;

    Animation from_left, from_right, from_bottom;
    private static int placesDisponibles = 20; // Initial value

    private TextView textViewPlacesDisponibles;

    private boolean reservationEffectuee = false;
    public static void updatePlaces1(String typeCours) {
        if ("zumba".equals(typeCours)) {
            placesDisponibles--; // Decrease places for musculaion
        }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);
        textViewPlacesDisponibles = findViewById(R.id.textViewPlacesDisponibles);
        updatePlacesTextView1();
        reserv = findViewById(R.id.reserv);
        reserv.setEnabled(!reservationEffectuee);

        second_back_arrow = findViewById(R.id.second_back_arrow);
        second_title = findViewById(R.id.second_title);
        second_subtitle = findViewById(R.id.second_subtitle);
        second_rating_number = findViewById(R.id.second_rating_number);
        second_rating_number2 = findViewById(R.id.second_rating_number2);
        more_details = findViewById(R.id.more_details);
        second_ratingbar = findViewById(R.id.second_ratingbar);
        Button buttonReserver = findViewById(R.id.reserv);
        Button buttonAnnuler =findViewById(R.id.annuler);

        // Vérifier si la réservation a été effectuée
        if (reservationEffectuee) {
            // Si réservé, désactivez et personnalisez le bouton
            buttonReserver.setEnabled(false);
            buttonReserver.setBackgroundColor(ContextCompat.getColor(this, R.color.grey)); // Utilisez votre propre couleur grise
            buttonReserver.setText("Déjà réservé");
            // Affichez le bouton "Annuler"
            buttonAnnuler.setVisibility(View.VISIBLE);
        }

        abonnement_fragment abonnementFragment = new abonnement_fragment();

        reserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mettre à jour l'état de la réservation
                reservationEffectuee = true;
                // Désactiver et personnaliser le bouton
                buttonReserver.setEnabled(false);
                buttonReserver.setBackgroundColor(ContextCompat.getColor(Details1.this, R.color.grey)); // Utilisez votre propre couleur grise
                buttonReserver.setText("Déjà réservé");


            }
        });


        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationEffectuee = false;

                // Activer le bouton "Réserver"
                buttonReserver.setEnabled(true);
                buttonReserver.setBackgroundColor(ContextCompat.getColor(Details1.this, R.color.teal_200)); // Utilisez votre propre couleur
                buttonReserver.setText("Réserver");
                placesDisponibles++; // Decrease places for musculaion
                textViewPlacesDisponibles.setText("Places disponibles : " + placesDisponibles);
                // Masquer le bouton "Annuler"
                buttonAnnuler.setVisibility(View.GONE);
                // Afficher un message ou faire d'autres actions nécessaires
                Toast.makeText(Details1.this, "Réservation annulée", Toast.LENGTH_SHORT).show();
                buttonReserver.setEnabled(true);
                reservationEffectuee = false;
            }
        });
        second_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courses_fragment CourseFragment = new courses_fragment();

                // Use FragmentManager to handle fragments
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framCOURS, CourseFragment) // Replace 'fragmentContainer' with the actual container ID
                        .addToBackStack(null)  // This allows you to press back to return to the previous fragment
                        .commit();

            }
        });

        //Hide status bar and navigation bar at the bottom
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        this.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );


        //Load Animations
        from_left = AnimationUtils.loadAnimation(this, R.anim.anim_from_left);
        from_right = AnimationUtils.loadAnimation(this, R.anim.anim_from_right);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom);


        //Set Animations
        second_back_arrow.setAnimation(from_left);
        second_title.setAnimation(from_right);
        second_subtitle.setAnimation(from_right);
        second_ratingbar.setAnimation(from_left);
        second_rating_number.setAnimation(from_right);
        second_rating_number2.setAnimation(from_right);
        reserv.setAnimation(from_bottom);
        more_details.setAnimation(from_bottom);




    }

    private void updatePlacesTextView1() {
        textViewPlacesDisponibles.setText("Places disponibles : " + placesDisponibles);
    }
}