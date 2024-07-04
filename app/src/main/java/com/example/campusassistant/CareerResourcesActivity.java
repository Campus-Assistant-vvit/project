package com.example.campusassistant;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class CareerResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_resources);

        // Bind data to the UI elements
        TextView resource1TitleTextView = findViewById(R.id.resource1TitleTextView);
        TextView resource1DescriptionTextView = findViewById(R.id.resource1DescriptionTextView);
        TextView resource2TitleTextView = findViewById(R.id.resource2TitleTextView);
        TextView resource2DescriptionTextView = findViewById(R.id.resource2DescriptionTextView);
        TextView resource3TitleTextView = findViewById(R.id.resource3TitleTextView);
        TextView resource3DescriptionTextView = findViewById(R.id.resource3DescriptionTextView);

        // Resource 1 data
        resource1TitleTextView.setText("Resource 1: Resume Building");
        resource1DescriptionTextView.setText("Learn how to create a professional resume that stands out.");

        // Resource 2 data
        resource2TitleTextView.setText("Resource 2: Interview Tips");
        resource2DescriptionTextView.setText("Get tips on how to ace your job interviews.");

        // Resource 3 data
        resource3TitleTextView.setText("Resource 3: Networking Strategies");
        resource3DescriptionTextView.setText("Discover effective networking strategies to advance your career.");
    }
}
