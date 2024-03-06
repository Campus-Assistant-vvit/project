package com.example.campusassistant;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Course_Information  extends AppCompatActivity {

    private Spinner branchSpinner, yearSpinner, regulationSpinner, semesterSpinner;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_information); // Set the layout

        // Initialize spinners
        branchSpinner = findViewById(R.id.branchSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        regulationSpinner = findViewById(R.id.regulationSpinner);
        semesterSpinner = findViewById(R.id.semesterSpinner);

        // Initialize submit button
        submitButton = findViewById(R.id.openButton);

        // Set up spinners with data from resources (modify these arrays according to your data)
        setupSpinner(branchSpinner, R.array.branch_options);
        setupSpinner(yearSpinner, R.array.year_options);
        setupSpinner(regulationSpinner, R.array.regulation_options); // Optional, based on your data structure
        setupSpinner(semesterSpinner, R.array.semester_options);

        // Handle submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void setupSpinner(Spinner spinner, int resourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                resourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void submitForm() {
        // Retrieve selected values from spinners
        String selectedBranch = branchSpinner.getSelectedItem().toString();
        String selectedYear = yearSpinner.getSelectedItem().toString();
        String selectedRegulation = regulationSpinner.getSelectedItem().toString();
        String selectedSemester = semesterSpinner.getSelectedItem().toString();

        // Perform actions based on selected values
        // This example shows a toast message, but you can implement functionality
        // to retrieve and display course information (syllabus, faculty, about course)
        String message = "Branch: " + selectedBranch + "\n"
                + "Year: " + selectedYear + "\n"
                + (regulationSpinner.getVisibility() == View.VISIBLE ? "Regulation: " + selectedRegulation + "\n" : "") // Only show regulation if visible
                + "Semester: " + selectedSemester;

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // **Optional:** Implement logic to fetch and display course information based on selections
        // - Use the selected values to query your data source (e.g., database, API)
        // - Retrieve syllabus (PDF file path), faculty, and about course information
        // - Display the information on the screen (e.g., TextView, WebView for PDF)
    }
}
