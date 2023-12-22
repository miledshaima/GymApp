package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ImageView displayQrImageView = findViewById(R.id.displayQrImageView);

        // Récupérer le code QR à partir de l'intent
        Bitmap qrBitmap = getIntent().getParcelableExtra("QR_BITMAP");

        // Afficher le code QR dans l'ImageView
        displayQrImageView.setImageBitmap(qrBitmap);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intr=new Intent(getApplicationContext(),Home.class);
                startActivity(intr);
              /*  // Create an instance of the home_fragment
                Fragment homeFragment = new home_fragment();
                FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, homeFragment).commit(); */
            }
        });
    }
}