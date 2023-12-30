package com.example.campusassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;
    private FirebaseAuth mAuth;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         email= findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.log_btn);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(view -> {
            String em = email.getText().toString();
            String pass = password.getText().toString();
            if (pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter your password.", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(MainActivity.this, task -> {
                if (task.isSuccessful()) {
                    // User is logged in successfully
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                    // Navigate to the homepage
                    Intent intent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent);
                } else {
                    // Login failed
                    Toast.makeText(MainActivity.this, "LOGIN UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
            });
        });

        Button registerButton = findViewById(R.id.Register_text);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationPage.class);
                startActivity(intent);
            }
        });
    }
}
