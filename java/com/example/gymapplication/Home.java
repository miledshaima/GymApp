package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gymapplication.databinding.HomeBinding;
import com.google.android.material.bottomappbar.BottomAppBar;

public class Home extends AppCompatActivity {
    HomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = HomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new home_fragment());

        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(new home_fragment());
            } else if (itemId == R.id.courses) {
                replaceFragment(new courses_fragment());
            } else if (itemId == R.id.abonnement) {
                replaceFragment(new abonnement_fragment());
            } else if (itemId == R.id.historique) {
                replaceFragment(new historique_fragment());
            }
            return true;
        });
        // Set a click listener for the FloatingActionButton
        binding.bottomAppBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment homeFragment = new home_fragment();
                FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, homeFragment).commit();
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment);
            fragmentTransaction.commit();
        }
    }
}
