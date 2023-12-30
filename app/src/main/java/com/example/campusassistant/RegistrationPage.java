package com.example.campusassistant;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RegistrationPage extends AppCompatActivity {

    private EditText nameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText rollNumberEditText;
    private EditText emailEditText;
    private EditText mobileNumberEditText;
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Initialize UI elements
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        rollNumberEditText = findViewById(R.id.Roll_number);
        emailEditText = findViewById(R.id.email);
        mobileNumberEditText = findViewById(R.id.mobileno);
        Button register = findViewById(R.id.register);

        // Initialize Firebase
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name= nameEditText.getText().toString();
                final String email= emailEditText.getText().toString();
                final String password=passwordEditText.getText().toString();
                final String confirmPassword = confirmPasswordEditText.getText().toString();
                final String rollNumber = rollNumberEditText.getText().toString();
                final String mobile = mobileNumberEditText.getText().toString();
                if (name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()||rollNumber.isEmpty()||mobile.isEmpty()){
                    Toast.makeText(RegistrationPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                if (name.isEmpty()) {
                        Toast.makeText(RegistrationPage.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
                if(!rollNumber.isEmpty()) {
                    // Check for length (10 characters)
                    if (rollNumber.length() != 10) {
                        Toast.makeText(RegistrationPage.this, "Invalid roll number length (must be 10 characters)", Toast.LENGTH_SHORT).show();
                    }

                    // Check for year pattern (first two characters)
                    if (!rollNumber.substring(0, 2).matches("[0-9]{2}")) {
                        Toast.makeText(RegistrationPage.this, "Invalid year format in roll number", Toast.LENGTH_SHORT).show();

                    }

                    // Check for fixed characters (bq)
                    if (!rollNumber.substring(2, 4).equals("bq") && !rollNumber.substring(2, 4).equals("BQ")) {
                        Toast.makeText(RegistrationPage.this, "Invalid character combination in roll number", Toast.LENGTH_SHORT).show();

                    }

                    // Check for section pattern (next two characters)
                    if (!rollNumber.substring(4, 6).matches("[0-9a-z]{2}")) {
                        Toast.makeText(RegistrationPage.this, "Invalid section format in roll number", Toast.LENGTH_SHORT).show();

                    }

                    // Check for branch pattern (next two characters)
                    if (!rollNumber.substring(6, 8).matches("[0-9a-z]{2}")) {
                        Toast.makeText(RegistrationPage.this, "Invalid branch format in roll number", Toast.LENGTH_SHORT).show();
                    }

                    // Check for roll number pattern (last two characters)
                    if (!rollNumber.substring(8).matches("[0-9]{2}")) {
                        Toast.makeText(RegistrationPage.this, "Invalid roll number format in roll number", Toast.LENGTH_SHORT).show();
                    }
                }
               if(!email.matches("[a-zA-Z0-9\\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*")){
                        Toast.makeText(RegistrationPage.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }
               if (!mobile.isEmpty()) {
                    if (mobile.length() != 10){
                        Toast.makeText(RegistrationPage.this, "Invalid mobile number length", Toast.LENGTH_SHORT).show();
                    }
                    if (!mobile.matches("[0-9]+")){
                        Toast.makeText(RegistrationPage.this, "Mobile number can only contain digits", Toast.LENGTH_SHORT).show();

                    }
                    if(!password.isEmpty()){
                        if (password.length() < 8) {
                            Toast.makeText(RegistrationPage.this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();

                        }

                        // Check for uppercase and lowercase letters
                        if (!password.matches("^(.*?[A-Z])(.*?[a-z]).*$")) {
                            Toast.makeText(RegistrationPage.this, "Password must contain both uppercase and lowercase letters", Toast.LENGTH_SHORT).show();

                        }

                        // Check for at least one number
                        if (!password.matches(".*\\d.*")) {
                            Toast.makeText(RegistrationPage.this, "Password must contain at least one number", Toast.LENGTH_SHORT).show();

                        }

                        // Check for at least one special character (optional, replace with your desired complexity)
                        if (!password.matches(".*[~!@#$%^&*()-_+={}:\"\\|;,.<>/?].*")) {
                            Toast.makeText(RegistrationPage.this, "Password must contain at least one special character", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegistrationPage.this, "password is not matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(rollNumber)) {
                                Toast.makeText(RegistrationPage.this, "Person is already registered", Toast.LENGTH_SHORT).show();
                            } else {

                                databaseReference.child("users").child(rollNumber).child("fullname").setValue(name);
                                databaseReference.child("users").child(rollNumber).child("rollnumber").setValue(rollNumber);
                                databaseReference.child("users").child(rollNumber).child("email").setValue(email);
                                databaseReference.child("users").child(rollNumber).child("mobilenumber").setValue(mobile);
                                databaseReference.child("users").child(rollNumber).child("passsword").setValue(password);
                                mAuth.createUserWithEmailAndPassword(email,password);
                                mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString()).addOnCompleteListener(RegistrationPage.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegistrationPage.this, "Register user successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegistrationPage.this, MainActivity.class));

                                        } else {
                                            Toast.makeText(RegistrationPage.this, "Registration failed", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(RegistrationPage.this, "DataBase error try again", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
}
