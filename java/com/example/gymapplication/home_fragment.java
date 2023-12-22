package com.example.gymapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class home_fragment extends Fragment {
    VideoView videoView;
    TextView addressTextView;
    ImageView inst,fcb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        addressTextView = view.findViewById(R.id.addressTextView);
        addressTextView=view.findViewById(R.id.addressTextView);

        addressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        // fcb = view.findViewById(R.id.fcb);
        addressTextView.setSelected(true);
        videoView = view.findViewById(R.id.videoView);

        // Spécifiez l'emplacement de votre vidéo
        String videoPath = "android.resource://" + requireActivity().getPackageName() + "/" + R.raw.video;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.start();
        ImageView inst = view.findViewById(R.id.imageView2);
        ImageView fcb = view.findViewById(R.id.imageView3);

        // Set click listeners for Instagram and Facebook icons
        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Instagram page
                openInstagram();
            }
        });

        fcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Facebook page
                openFacebook();
            }
        });

        return view;

    }
    // Open Instagram page
    private void openInstagram() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/airfitness_montargis/?hl=fr"));
        startActivity(intent);
    }

    // Open Facebook page
    private void openFacebook() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AirfitnessMontargis/"));
        startActivity(intent);
    }


}