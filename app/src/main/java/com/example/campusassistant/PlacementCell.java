package com.example.campusassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class PlacementCell extends AppCompatActivity {

    private com.google.android.material.button.MaterialButton placementRecordsButton;
    private com.google.android.material.button.MaterialButton internshipOpportunitiesButton;
    private com.google.android.material.button.MaterialButton jobAlertsButton;
    private com.google.android.material.button.MaterialButton careerResourcesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_cell);

        // Find views by ID
        placementRecordsButton = findViewById(R.id.button_placement_records);
        internshipOpportunitiesButton = findViewById(R.id.button_internship_opportunities);
        jobAlertsButton = findViewById(R.id.button_job_alerts);
        careerResourcesButton = findViewById(R.id.button_career_resources);

        // Set click listeners for buttons
        placementRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlacementCell.this, PlacementRecordsActivity.class);
                startActivity(intent);
            }
        });

        internshipOpportunitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlacementCell.this, InternshipOpportunitiesActivity.class);
                startActivity(intent);

            }
        });

        jobAlertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlacementCell.this, JobAlertsActivity.class);
                startActivity(intent);

            }
        });

        careerResourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PlacementCell.this, CareerResourcesActivity.class);
                startActivity(intent);

            }
        });
    }
}
