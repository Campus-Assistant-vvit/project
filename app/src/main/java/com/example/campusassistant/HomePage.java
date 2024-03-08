package com.example.campusassistant;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout menuLayout; // LinearLayout to hold menu options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Find views by their respective IDs
        TextInputEditText searchEditText = findViewById(R.id.searchEditText);
        MaterialButton attendanceButton = findViewById(R.id.attendanceButton);
        MaterialButton resultsButton = findViewById(R.id.resultsButton);
        MaterialButton announcementsButton = findViewById(R.id.announcementsButxton);
        MaterialButton digilockerButton = findViewById(R.id.digilockerButton);
        ImageView menuIconImageView = findViewById(R.id.menuIconImageView);
        MaterialButton examcellButton = findViewById(R.id.examcellButton);
        MaterialButton TransportationButton = findViewById(R.id.TranspoButton);

        menuLayout = findViewById(R.id.menuLayout); // ID of the LinearLayout in your XML

        // Set click listener for the menu icon
        menuIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu();
            }
        });

        // Set click listeners for other buttons
        attendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AttendanceActivity
                Intent intent = new Intent(HomePage.this, Attendance.class);
                startActivity(intent);
            }
        });

        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ResultsActivity
                Intent intent = new Intent(HomePage.this, Results.class);
                startActivity(intent);
            }
        });

        announcementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Announcements button click

                Intent intent = new Intent(HomePage.this, Announcements_activity.class);
                startActivity(intent);
            }
        });

        digilockerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Handle Digilocker button click
                Intent intent = new Intent(HomePage.this, digilockerMain.class);
                startActivity(intent);
            }
        });

        Button courseInformationButton = findViewById(R.id.courseinfoButton1);

        // Set OnClickListener for the button
        courseInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CourseInformationActivity
                Intent intent = new Intent(HomePage.this, Course_Information.class);
                startActivity(intent);
            }
        });


        examcellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Handle ExamCell button click
                Intent intent = new Intent(HomePage.this, ExamCell.class);
                startActivity(intent);
            }
        });


        TransportationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Handle Transportation button click
                Intent intent = new Intent(HomePage.this, Transportation.class);
                startActivity(intent);
            }
        });

        // Set up NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Define the openMenuActivity method
    public void showMenu() {
        // Clear existing menu items
        menuLayout.removeAllViews();

        // Create menu options and handle clicks directly
        addMenuItem("NIRF Ranking", Constants.NAV_NIRF_RANKING, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle NIRF Ranking click
                menuLayout.setVisibility(View.GONE); // Close the menu after click
            }
        });

        addMenuItem("Mandatory Disclosure", Constants.NAV_MANDATORY_DISCLOSURE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Mandatory Disclosure click
                menuLayout.setVisibility(View.GONE);
            }
        });

        addMenuItem("Institution Info", Constants.NAV_INSTITUTION_INFO, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Institution Info click
                menuLayout.setVisibility(View.GONE);
            }
        });

        addMenuItem("Gallery", Constants.NAV_GALLERY, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Gallery click
                menuLayout.setVisibility(View.GONE);
            }
        });

        addMenuItem("About", Constants.NAV_ABOUT, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle About click
                menuLayout.setVisibility(View.GONE);
            }
        });

        addMenuItem("Logout", Constants.NAV_LOGOUT, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Logout click
                menuLayout.setVisibility(View.GONE);
            }
        });

        addMenuItem("Exit", Constants.NAV_EXIT, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle exit click
                handleExit();
                menuLayout.setVisibility(View.GONE);
            }
        });

        // Show the menu layout
        menuLayout.setVisibility(View.VISIBLE);

        // Customize menu appearance
        menuLayout.setBackgroundColor(Color.parseColor("#E97F69"));
        menuLayout.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels / 2;
    }

    // Helper method to add menu item dynamically
    private void addMenuItem(String text, final int itemId, final View.OnClickListener clickListener) {
        // Calculate width as 60% of the screen width
        int menuWidth = (int) (getResources().getDisplayMetrics().widthPixels * 0.6);

        // Set layout parameters for half of the screen width and wrap content height
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                menuWidth, // Width is now set to 60% of the screen width
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Create the TextView
        TextView menuItem = new TextView(this);
        menuItem.setText(text);
        menuItem.setTextSize(18);

        // Set padding for a visually appealing appearance
        int paddingStartEnd = getResources().getDimensionPixelSize(R.dimen.menu_item_padding_start_end);
        int paddingTopBottom = getResources().getDimensionPixelSize(R.dimen.menu_item_padding_top_bottom);

        menuItem.setPadding(paddingStartEnd, paddingTopBottom, paddingStartEnd, paddingTopBottom);

        menuItem.setBackgroundColor(Color.parseColor("#E97F69"));
        menuItem.setTextColor(Color.WHITE);
        menuItem.setGravity(Gravity.CENTER_VERTICAL);

        // Set layout parameters
        menuItem.setLayoutParams(layoutParams);

        // Set click listener
        menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Menu", "Menu item clicked: " + text);
                if (clickListener != null) {
                    clickListener.onClick(v);
                }
            }
        });

        // Add the TextView to the menu layout
        menuLayout.addView(menuItem);
    }

    // Handle menu item click
    private void onMenuItemClick(int itemId) {
        // Handle different menu item clicks based on the item ID
        switch (itemId) {
            case Constants.NAV_NIRF_RANKING:
                // Handle NIRF Ranking click
                break;
            case Constants.NAV_MANDATORY_DISCLOSURE:
                // Handle Mandatory Disclosure click
                break;
            case Constants.NAV_INSTITUTION_INFO:
                // Handle Institution Info click
                break;
            case Constants.NAV_GALLERY:
                // Handle Gallery click
                break;
            case Constants.NAV_ABOUT:
                // Handle About click
                break;
            case Constants.NAV_LOGOUT:
                // Handle Logout click
                break;
            case Constants.NAV_EXIT:
                // Handle Exit click
                handleExit();
                break;
        }

        // Close the menu layout
        menuLayout.setVisibility(View.GONE);
    }

    // Handle NavigationView item selection
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        int itemId = item.getItemId();
        showMenu(); // Close the menu layout
        onMenuItemClick(itemId); // Handle the selected menu item
        return true;
    }

    // Handle exit action
    private void handleExit() {
        // Perform actions needed before exiting
        navigateToHomePage();
    }

    // Navigate to home page
    private void navigateToHomePage() {
        // Close the navigation drawer
        menuLayout.setVisibility(View.GONE);
    }

}
