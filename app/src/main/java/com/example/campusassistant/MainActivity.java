package com.example.campusassistant;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button loginbtn;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "campusAssistantPref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final long SESSION_DURATION_MS = 180 * 24 * 60 * 60 * 1000; // 6 months in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.log_btn);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Check if user is already logged in on app launch
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        if (isLoggedIn && isSessionValid()) {
            navigateToHomePage();
            return;
        }

        // Login button click listener
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

                    // Save login state in shared preference
                    saveLoginState(true);

                    // Navigate to the homepage
                    navigateToHomePage();
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

    private void navigateToHomePage() {
        Intent intent = new Intent(MainActivity.this, HomePage.class);
        startActivity(intent);
        finish(); // Close MainActivity after successful login
    }

    private void saveLoginState(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply();
    }

    private boolean isSessionValid() {
        long loginTime = sharedPreferences.getLong("loginTime", 0);
        return (System.currentTimeMillis() - loginTime) < SESSION_DURATION_MS;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Update login time on app launch (optional, can be removed)
        sharedPreferences.edit().putLong("loginTime", System.currentTimeMillis()).apply();
    }
}
