package com.example.l9q2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FlightDetailsActivity extends AppCompatActivity {

    TextView flightDetailsTextView;
    Button bookButton;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String selectedFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        flightDetailsTextView = findViewById(R.id.flightDetailsTextView);
        bookButton = findViewById(R.id.bookButton);

        selectedFlight = getIntent().getStringExtra("flight");

        // Display flight details
        displayFlightDetails(selectedFlight);

        // Handle booking button click
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookFlight(selectedFlight);
            }
        });
    }

    private void displayFlightDetails(String selectedFlight) {
        // Retrieve flight details from the database
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FLIGHTS +
                " WHERE " + DatabaseHelper.KEY_FLIGHT_NUMBER + "=?", new String[]{selectedFlight});

        if (cursor.moveToFirst()) {
            String flightNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FLIGHT_NUMBER));
            String departure = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DEPARTURE));
            String destination = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESTINATION));
            String departureTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DEPARTURE_TIME));
            double price = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.KEY_PRICE));

            // Display flight details
            flightDetailsTextView.setText("Flight Number: " + flightNumber + "\n" +
                    "Departure: " + departure + "\n" +
                    "Destination: " + destination + "\n" +
                    "Departure Time: " + departureTime + "\n" +
                    "Price: $" + price);
        }
        cursor.close();
    }

    private void bookFlight(String selectedFlight) {
        // Check if the flight is available (You may need to implement this logic based on your requirements)
        // For simplicity, let's assume the flight is available

        // You would typically insert a new reservation into the reservations table
        // For this example, let's assume a customer with ID 1 is booking the flight

        // Insert reservation into the reservations table
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_FLIGHT_ID, getFlightId(selectedFlight)); // Get flight ID from flight number
        values.put(DatabaseHelper.KEY_CUSTOMER_ID, 1); // Assume a customer with ID 1 is booking the flight

        long newRowId = db.insert(DatabaseHelper.TABLE_RESERVATIONS, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Flight booked successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to book flight!", Toast.LENGTH_SHORT).show();
        }
    }

    private int getFlightId(String flightNumber) {
        int flightId = -1;
        Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.KEY_ID + " FROM " +
                        DatabaseHelper.TABLE_FLIGHTS + " WHERE " + DatabaseHelper.KEY_FLIGHT_NUMBER + "=?",
                new String[]{flightNumber});

        if (cursor.moveToFirst()) {
            flightId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID));
        }
        cursor.close();

        return flightId;
    }
}
