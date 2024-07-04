package com.example.campusassistant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InternshipDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_detail);

        // Get the data passed from the previous activity
        String company = getIntent().getStringExtra("company");
        String position = getIntent().getStringExtra("position");
        String location = getIntent().getStringExtra("location");
        String duration = getIntent().getStringExtra("duration");
        String stipend = getIntent().getStringExtra("stipend");
        String description = getIntent().getStringExtra("description");

        // Set the data to the views
        TextView companyTextView = findViewById(R.id.companyTextView);
        companyTextView.setText(company);

        TextView positionTextView = findViewById(R.id.positionTextView);
        positionTextView.setText(position);

        TextView locationTextView = findViewById(R.id.locationTextView);
        locationTextView.setText(location);

        TextView durationTextView = findViewById(R.id.durationTextView);
        durationTextView.setText(duration);

        TextView stipendTextView = findViewById(R.id.stipendTextView);
        stipendTextView.setText(stipend);

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(description);
    }
}
