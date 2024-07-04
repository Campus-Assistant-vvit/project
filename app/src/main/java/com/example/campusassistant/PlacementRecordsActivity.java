package com.example.campusassistant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlacementRecordsActivity extends AppCompatActivity {

    private Spinner branchSpinner, yearSpinner, companySpinner;
    private Button openButton;
    private TableLayout studentRecordsTable;

    // Static data for demonstration
    private List<Student> students;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_records);

        // Initialize views
        branchSpinner = findViewById(R.id.branchSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        companySpinner = findViewById(R.id.companySpinner);
        openButton = findViewById(R.id.openButton);
        studentRecordsTable = findViewById(R.id.studentRecordsTable); // Table layout

        // Initialize static student data
        generateMockData(); // Generate mock data here

        // Set up spinners
        setupSpinners();

        // Set click listener for Open button
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStudentRecords();
            }
        });
    }

    private void setupSpinners() {
        // Set up branch spinner
        ArrayAdapter<CharSequence> branchAdapter = ArrayAdapter.createFromResource(this,
                R.array.branch_options, android.R.layout.simple_spinner_item);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        // Set up year spinner with the years array resource
        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, generateYearRange());
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        // Set up company spinner
        ArrayAdapter<CharSequence> companyAdapter = ArrayAdapter.createFromResource(this,
                R.array.company_options, android.R.layout.simple_spinner_item);
        companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companySpinner.setAdapter(companyAdapter);
    }

    private List<Integer> generateYearRange() {
        // Define year range
        int startYear = 2018;
        int endYear = 2024;
        List<Integer> years = new ArrayList<>();
        for (int year = startYear; year <= endYear; year++) {
            years.add(year);
        }
        return years;
    }

    private void generateMockData() {
        students = new ArrayList<>();

        Random random = new Random();

        // Sample companies and branches
        String[] companies = {"Google", "Microsoft", "Amazon", "Facebook","Infosys", "Tech Mahindhra", "Accenture", "Wipro", };
        String[] branches = {"CSE", "ECE", "IT", "CIVIL", "MECH", "EEE", "AIM", "AID", "CSM", "CSO", "CIC"};

        // Generate mock data
        for (String company : companies) {
            for (int i = 0; i < 100; i++) { // Generate 100 students per company
                String name = "Student " + (i + 1);
                String rollNumber = "B" + (10000 + i);
                String packageOffered = (8 + random.nextInt(5)) + " LPA"; // Random package between 8 to 12 LPA
                String branch = branches[random.nextInt(branches.length)]; // Random branch
                int academicYear = generateYearRange().get(random.nextInt(generateYearRange().size())); // Random year from the list

                Student student = new Student(name, rollNumber, company, packageOffered, branch, String.valueOf(academicYear));
                students.add(student);
            }
        }
    }

    private void displayStudentRecords() {
        // Get selected options
        String selectedBranch = branchSpinner.getSelectedItem().toString();
        int selectedYear = (int) yearSpinner.getSelectedItem();
        String selectedCompany = companySpinner.getSelectedItem().toString();

        // Perform filtering based on selected options
        List<Student> filteredStudents = filterStudents(selectedBranch, selectedYear, selectedCompany);

        // Clear existing table rows
        studentRecordsTable.removeAllViews();

        // Add table headers
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        headerRow.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        headerRow.setPadding(16, 16, 16, 16);

        TextView nameHeader = new TextView(this);
        nameHeader.setText("Name");
        nameHeader.setTextColor(getResources().getColor(android.R.color.white));
        nameHeader.setGravity(Gravity.CENTER);
        nameHeader.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        headerRow.addView(nameHeader);

        TextView rollNumberHeader = new TextView(this);
        rollNumberHeader.setText("Roll Number");
        rollNumberHeader.setTextColor(getResources().getColor(android.R.color.white));
        rollNumberHeader.setGravity(Gravity.CENTER);
        rollNumberHeader.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        headerRow.addView(rollNumberHeader);

        TextView companyHeader = new TextView(this);
        companyHeader.setText("Company");
        companyHeader.setTextColor(getResources().getColor(android.R.color.white));
        companyHeader.setGravity(Gravity.CENTER);
        companyHeader.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        headerRow.addView(companyHeader);

        TextView packageHeader = new TextView(this);
        packageHeader.setText("Package");
        packageHeader.setTextColor(getResources().getColor(android.R.color.white));
        packageHeader.setGravity(Gravity.CENTER);
        packageHeader.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        headerRow.addView(packageHeader);

        TextView branchHeader = new TextView(this);
        branchHeader.setText("Branch");
        branchHeader.setTextColor(getResources().getColor(android.R.color.white));
        branchHeader.setGravity(Gravity.CENTER);
        branchHeader.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        headerRow.addView(branchHeader);

        TextView yearHeader = new TextView(this);
        yearHeader.setText("Year");
        yearHeader.setTextColor(getResources().getColor(android.R.color.white));
        yearHeader.setGravity(Gravity.CENTER);
        yearHeader.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        headerRow.addView(yearHeader);

        studentRecordsTable.addView(headerRow);

        // Add data rows
        for (Student student : filteredStudents) {
            TableRow dataRow = new TableRow(this);
            dataRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            dataRow.setPadding(16, 16, 16, 16);

            TextView nameText = new TextView(this);
            nameText.setText(student.getName());
            nameText.setGravity(Gravity.CENTER);
            nameText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            dataRow.addView(nameText);

            TextView rollNumberText = new TextView(this);
            rollNumberText.setText(student.getRollNumber());
            rollNumberText.setGravity(Gravity.CENTER);
            rollNumberText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            dataRow.addView(rollNumberText);

            TextView companyText = new TextView(this);
            companyText.setText(student.getCompany());
            companyText.setGravity(Gravity.CENTER);
            companyText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            dataRow.addView(companyText);

            TextView packageText = new TextView(this);
            packageText.setText(student.getPackage());
            packageText.setGravity(Gravity.CENTER);
            packageText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            dataRow.addView(packageText);

            TextView branchText = new TextView(this);
            branchText.setText(student.getBranch());
            branchText.setGravity(Gravity.CENTER);
            branchText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            dataRow.addView(branchText);

            TextView yearText = new TextView(this);
            yearText.setText(student.getYear());
            yearText.setGravity(Gravity.CENTER);
            yearText.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            dataRow.addView(yearText);

            studentRecordsTable.addView(dataRow);
        }

        // If no records found, display a message
        if (filteredStudents.isEmpty()) {
            TableRow emptyRow = new TableRow(this);
            emptyRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            emptyRow.setPadding(16, 16, 16, 16);

            TextView emptyText = new TextView(this);
            emptyText.setText("No records found for selected criteria.");
            emptyText.setTextColor(getResources().getColor(android.R.color.black));
            emptyText.setGravity(Gravity.CENTER);
            emptyText.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            emptyRow.addView(emptyText);

            studentRecordsTable.addView(emptyRow);
        }
    }

    private List<Student> filterStudents(String branch, int year, String company) {
        // Implement your filtering logic here based on selected criteria
        List<Student> filteredList = new ArrayList<>();
        for (Student student : students) {
            if (student.getBranch().equals(branch) &&
                    Integer.parseInt(student.getYear()) == year &&
                    student.getCompany().equals(company)) {
                filteredList.add(student);
            }
        }
        return filteredList;
    }
}
