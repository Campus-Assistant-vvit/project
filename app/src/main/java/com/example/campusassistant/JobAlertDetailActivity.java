package com.example.campusassistant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JobAlertDetailActivity extends AppCompatActivity {

    private ImageView logoImageView;
    private TextView jobTitleTextView;
    private TextView companyTextView;
    private TextView locationTextView;
    private TextView descriptionTextView;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_alert_detail);

        // Initialize views
        logoImageView = findViewById(R.id.logoImageView);
        jobTitleTextView = findViewById(R.id.jobTitleTextView);
        companyTextView = findViewById(R.id.companyTextView);
        locationTextView = findViewById(R.id.locationTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        applyButton = findViewById(R.id.applyButton);

        // Retrieve job alert details from intent
        Intent intent = getIntent();
        String jobTitle = intent.getStringExtra("jobTitle");
        String companyName = intent.getStringExtra("companyName");
        String location = intent.getStringExtra("location");
        String description = intent.getStringExtra("description");

        // Set job alert details to views
        jobTitleTextView.setText(jobTitle);
        companyTextView.setText(companyName);
        locationTextView.setText(location);
        descriptionTextView.setText(description);

        // Set onClickListener for apply button if needed
        applyButton.setOnClickListener(v -> {
            // Handle apply button click event
            // Implement your logic here (e.g., open application form)
        });
    }
}
