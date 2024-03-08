package com.example.campusassistant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

public class Transportation extends AppCompatActivity {

    private Spinner busRouteSpinner;
    private CardView driverDetailsCard;
    private TextView busNumberText, driverNameText, mobileNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation);

        // Initialize views
        busRouteSpinner = findViewById(R.id.bus_route_spinner);
        driverDetailsCard = findViewById(R.id.driver_details_card);
        busNumberText = findViewById(R.id.bus_number_text);
        driverNameText = findViewById(R.id.driver_name_text);
        mobileNumberText = findViewById(R.id.mobile_number_text);

        // Set listener for spinner selection
        busRouteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Show or hide driver details based on spinner selection
                if (position != 0) { // Assuming position 0 is a default hint or empty value
                    driverDetailsCard.setVisibility(View.VISIBLE);
                } else {
                    driverDetailsCard.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case when nothing is selected
                driverDetailsCard.setVisibility(View.GONE);
            }
        });
    }
}
