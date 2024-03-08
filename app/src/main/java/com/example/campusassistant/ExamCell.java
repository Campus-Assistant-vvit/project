package com.example.campusassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ExamCell extends AppCompatActivity {

    private com.google.android.material.button.MaterialButton resultsButton;
    private com.google.android.material.button.MaterialButton timetableButton;
    private com.google.android.material.button.MaterialButton academicCalendarButton;
    private com.google.android.material.button.MaterialButton previousPapersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_cell);

        // Find views by ID
        resultsButton = findViewById(R.id.resultsButton);
        timetableButton = findViewById(R.id.timetable);
        academicCalendarButton = findViewById(R.id.Academiccalendar);
        previousPapersButton = findViewById(R.id.previous_question_papers);

        // Set click listeners for buttons
        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AttendanceActivity
                Intent intent = new Intent(ExamCell.this, Results.class);
                startActivity(intent);
            }
        });

        timetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExamCell.this, "Timetable clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        academicCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can display the Academic Calendar here (e.g., open a webview or download a PDF)
                Toast.makeText(ExamCell.this, "Academic Calendar clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        previousPapersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // You can list or download previous question papers here
                Toast.makeText(ExamCell.this, "Previous Papers clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
