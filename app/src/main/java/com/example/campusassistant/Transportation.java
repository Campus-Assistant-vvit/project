package com.example.campusassistant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        busRouteSpinner = findViewById(R.id.routeSpinner);
        driverDetailsCard = findViewById(R.id.driver_details_card);
        busNumberText = findViewById(R.id.bus_number_text);
        driverNameText = findViewById(R.id.driver_name_text);
        mobileNumberText = findViewById(R.id.mobile_number_text);

        // Set up the spinner with default ArrayAdapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.route_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busRouteSpinner.setAdapter(adapter);

        // Set listener for spinner selection
        busRouteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Show or hide driver details based on spinner selection
                if (position != 0) { // Assuming position 0 is a default hint or empty value
                    driverDetailsCard.setVisibility(View.VISIBLE);
                    // Update driver details here if needed
                    // For example:
                    // busNumberText.setText("Bus Number: XYZ");
                    // driverNameText.setText("Driver Name: John Doe");
                    // mobileNumberText.setText("Mobile Number: 1234567890");

                    switch (position) {
                        case 1:
                            busNumberText.setText("Bus Number: 1");
                            break;
                        case 2:
                            busNumberText.setText("Bus Number: 2");
                            break;
                        // Add more cases as needed
                        default:
                            // Handle default case
                            break;
                    }
                    driverNameText.setText("Driver Name: John Doe");
                    mobileNumberText.setText("Mobile Number: 1234567890");
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