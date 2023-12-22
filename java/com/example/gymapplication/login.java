package com.example.gymapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymapplication.R;
import com.example.gymapplication.databinding.ActivityLoginBinding;
import com.example.gymapplication.Profil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class login extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    TextView sign;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        sign =findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(login.this, signup.class);

                startActivity(intent1);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else if (!validateemail() | !validatePassword()) {
                }
                else {
                    checkUser();
                    // Générer le code QR
                    Bitmap qrBitmap = generateQRCodeBitmap("Votre texte à encoder en QR", 500, 500);

                    // Passer le code QR à la nouvelle activité
                    Intent intent = new Intent(login.this, Profil.class);
                    intent.putExtra("QR_BITMAP", qrBitmap);
                    startActivity(intent);
                }
            }
        });
    }

    private Bitmap generateQRCodeBitmap(String s, int i, int i1) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(s, BarcodeFormat.QR_CODE, i, i1);
            Bitmap bitmap = Bitmap.createBitmap(i, i1, Bitmap.Config.RGB_565);
            for (int x = 0; x < i; x++) {
                for (int y = 0; y < i1; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.white));
                }
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void checkUser() {

        String useremail = emailEditText.getText().toString().trim();
        String userPassword = passwordEditText.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("clients");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(useremail);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    emailEditText.setError(null);
                    String passwordFromDB = snapshot.child(useremail).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        emailEditText.setError(null);
                        String nameFromDB = snapshot.child(useremail).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(useremail).child("email").getValue(String.class);
                        Intent intent = new Intent(login.this, Profil.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        startActivity(intent);
                    } else {
                        passwordEditText.setError("Invalid Credentials");
                        passwordEditText.requestFocus();
                    }
                } else {
                    emailEditText.setError("User does not exist");
                    emailEditText.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

            private boolean validatePassword() {
                String val = passwordEditText.getText().toString();
                if (val.isEmpty()) {
                    passwordEditText.setError("Password cannot be empty");
                    return false;
                } else {
                    passwordEditText.setError(null);
                    return true;
                }
            }

            private boolean validateemail() {
                String val = emailEditText.getText().toString();
                if (val.isEmpty()) {
                    emailEditText.setError("Username cannot be empty");
                    return false;
                } else {
                    emailEditText.setError(null);
                    return true;
                }
            }

        }