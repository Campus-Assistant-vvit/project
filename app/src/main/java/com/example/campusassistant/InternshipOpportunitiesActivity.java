package com.example.campusassistant;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class InternshipOpportunitiesActivity extends AppCompatActivity {

    private RecyclerView internshipRecyclerView;
    private InternshipAdapter internshipAdapter;
    private List<Internship> internshipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_opportunities);

        // Initialize views
        internshipRecyclerView = findViewById(R.id.internshipRecyclerView);

        // Initialize data
        internshipList = new ArrayList<>();
        initializeInternshipData();

        // Set up RecyclerView
        internshipAdapter = new InternshipAdapter(internshipList, this);
        internshipRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        internshipRecyclerView.setAdapter(internshipAdapter);
    }

    private void initializeInternshipData() {
        // Add some static data for testing
        internshipList.add(new Internship("Google", "Software Engineer Intern", "Mountain View, CA", "3 months", "$5000/month", "Work on real projects with experienced engineers."));
        internshipList.add(new Internship("Microsoft", "Data Science Intern", "Redmond, WA", "3 months", "$4500/month", "Analyze data to provide insights."));
        internshipList.add(new Internship("Amazon", "Product Manager Intern", "Seattle, WA", "3 months", "$4800/month", "Help manage product development."));
        internshipList.add(new Internship("Facebook", "UX Design Intern", "Menlo Park, CA", "3 months", "$4700/month", "Design user interfaces and improve user experience."));
    }
}
