package com.example.campusassistant;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class JobAlertsActivity extends AppCompatActivity {

    private RecyclerView jobAlertRecyclerView;
    private JobAlertAdapter jobAlertAdapter;
    private List<JobAlert> jobAlertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_alert);

        // Initialize views
        jobAlertRecyclerView = findViewById(R.id.jobAlertRecyclerView);

        // Initialize data
        jobAlertList = new ArrayList<>();
        initializeJobAlertData();

        // Set up RecyclerView
        jobAlertAdapter = new JobAlertAdapter(jobAlertList, this);
        jobAlertRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobAlertRecyclerView.setAdapter(jobAlertAdapter);
    }

    private void initializeJobAlertData() {
        // Add some static data for testing
        jobAlertList.add(new JobAlert("Google", "Software Engineer", "Mountain View, CA", "Work on cutting-edge technologies."));
        jobAlertList.add(new JobAlert("Microsoft", "Data Scientist", "Redmond, WA", "Analyze big data to derive insights."));
        jobAlertList.add(new JobAlert("Amazon", "Product Manager", "Seattle, WA", "Manage product lifecycle."));
        jobAlertList.add(new JobAlert("Facebook", "UX Designer", "Menlo Park, CA", "Design user interfaces and improve user experience."));
    }
}
