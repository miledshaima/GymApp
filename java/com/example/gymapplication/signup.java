package com.example.gymapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.gymapplication.databinding.ActivitySignupBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    private EditText fullNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signUpButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullNameEditText = findViewById(R.id.fullname);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confpass);
        signUpButton = findViewById(R.id.sign_button);



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("clients");
                // Récupérer les valeurs des champs
                String name = fullNameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                if (isValidEmail(email)){
                    registerUser(name,email,password);
                }

                Helper helperClass = new Helper(name, email, password,confirmPassword);
                reference.child(email).setValue(helperClass);
                // Vérifier les conditions avant l'inscription
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(signup.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(signup.this, "Adresse e-mail non valide", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(signup.this, "Le mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(signup.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                } else {
                    // Conditions remplies, vous pouvez maintenant procéder à l'inscription
                    // (par exemple, enregistrer les informations dans une base de données ou effectuer une autre action)


                    Toast.makeText(signup.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                    // Exemple : redirection vers une autre activité après l'inscription réussie
                    Intent intent = new Intent(signup.this, login.class);
                    startActivity(intent);
                    finish();  // Facultatif, cela fermera l'activité d'inscription pour éviter un retour en arrière direct vers cette activité
                }
            }
        });
    }

    // Vérifie si une adresse e-mail est valide
    private boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void showMessage(String nom) {
        Toast.makeText(this,nom,Toast.LENGTH_SHORT).show();
    }


    private void registerUser(final String name,final String email,final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,Endspoint.register_url, response -> {
            if(response.equals("success")){
                showMessage("registration success");
                Intent intent=new Intent(signup.this, login.class);
                startActivity(intent);
            }
            else {
                showMessage("registration failed");
                Intent intent=new Intent(signup.this, Home.class);
                startActivity(intent);
            }
        },error -> {
            showMessage("check internet");
            Log.d("volly",error.getMessage());
            Intent intent=new Intent(signup.this, signup.class);
            startActivity(intent);
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String , String> params=new HashMap<>();
                params.put("fullname",name);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}






