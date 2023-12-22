package com.example.gymapplication;


import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LongToast {

    public static void showLongToast(Context context, String message) {
        // Create a custom Toast
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);

        // Inflate the custom layout
        View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
        TextView textViewMessage = view.findViewById(R.id.textViewMessage);
        textViewMessage.setText(message);

        // Set the custom layout on the Toast
        toast.setView(view);

        // Use a Handler to display the Toast for a longer duration
        new Handler().postDelayed(toast::show, 500);

        // Dismiss the Toast after 30 seconds
        new Handler().postDelayed(toast::cancel, 10000);
    }
}
