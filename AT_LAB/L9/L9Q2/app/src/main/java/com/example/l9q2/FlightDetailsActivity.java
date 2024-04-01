package com.example.l9q2;

import android.annotation.SuppressLint;
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

    TextView flightDetailsTextView1;
    TextView flightDetailsTextView2;
    Button bookButton;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String selectedFlight;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        flightDetailsTextView1 = findViewById(R.id.flightDetailsTextView1);
        flightDetailsTextView2 = findViewById(R.id.flightDetailsTextView2);
        bookButton = findViewById(R.id.bookButton);

        selectedFlight = getIntent().getStringExtra("flight");

        displayBookedFlightDetails(selectedFlight);

        // Handle booking button click
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookFlight(selectedFlight);
            }
        });
    }

//    private void displayFlightDetails(String selectedFlight) {
//        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FLIGHTS +
//                " WHERE " + DatabaseHelper.KEY_FLIGHT_NUMBER + "=?", new String[]{selectedFlight});
//
//        if (cursor.moveToFirst()) {
//            String flightNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FLIGHT_NUMBER));
//            String departure = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DEPARTURE));
//            String destination = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESTINATION));
//            String departureTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DEPARTURE_TIME));
//            String availableTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_AVAILABLE_TIME));
//            String availableDay = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_AVAILABLE_DAY));
//            double price = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.KEY_PRICE));
//
//            flightDetailsTextView1.setText("Flight Number: " + flightNumber + "\n" +
//                    "Departure: " + departure + "\n" +
//                    "Destination: " + destination + "\n" +
//                    "Departure Time: " + departureTime + "\n" +
//                    "Available Time: " + availableTime + "\n" +
//                    "Available Day: " + availableDay + "\n" +
//                    "Price: $" + price);
//        }
//        cursor.close();
//    }

    private void bookFlight(String selectedFlight) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_FLIGHT_ID, getFlightId(selectedFlight));
        values.put(DatabaseHelper.KEY_CUSTOMER_ID, 1); // Assume a customer with ID 1 is booking the flight

        long newRowId = db.insert(DatabaseHelper.TABLE_RESERVATIONS, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Flight booked successfully!", Toast.LENGTH_SHORT).show();
            displayBookedFlightDetails(selectedFlight); // Display booked flight details
        } else {
            Toast.makeText(this, "Failed to book flight!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint({"SetTextI18n", "Range"})
    private void displayBookedFlightDetails(String selectedFlight) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FLIGHTS +
                " WHERE " + DatabaseHelper.KEY_FLIGHT_NUMBER + "=?", new String[]{selectedFlight});

        if (cursor.moveToFirst()) {
            String flightNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FLIGHT_NUMBER));
            String departure = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DEPARTURE));
            String destination = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESTINATION));
            String departureTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DEPARTURE_TIME));

            flightDetailsTextView2.setVisibility(View.VISIBLE); // Make the booked flight details visible
            flightDetailsTextView2.setText("Booked Flight Details:\n" +
                    "Flight Number: " + flightNumber + "\n" +
                    "Departure: " + departure + "\n" +
                    "Destination: " + destination + "\n" +
                    "Departure Time: " + departureTime);
        }
        cursor.close();
    }

    @SuppressLint("Range")
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
