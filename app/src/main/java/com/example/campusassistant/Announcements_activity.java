package com.example.campusassistant;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Announcements_activity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ArrayList<Announcement> announcements;
    private AnnouncementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        // Initialize Firebase and Database Reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("announcements");

        announcements = new ArrayList<>();
        adapter = new AnnouncementAdapter(this, announcements);

        // Fetch announcements from Firebase
        fetchAnnouncementsFromFirebase();

        // Connect ListView with adapter
        ListView listView = findViewById(R.id.activity_announcement);
        listView.setAdapter(adapter);
    }

    private void fetchAnnouncementsFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                announcements.clear(); // Clear existing announcements to avoid duplicates
                for (DataSnapshot announcementSnapshot : snapshot.getChildren()) {
                    Announcement announcement = announcementSnapshot.getValue(Announcement.class);
                    if (announcement != null) {
                        announcements.add(announcement);
                    }
                }
                adapter.notifyDataSetChanged(); // Notify adapter about data change
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle any errors during data retrieval
                System.out.println("Error fetching announcements: " + error.getMessage());
            }
        });
    }
}
