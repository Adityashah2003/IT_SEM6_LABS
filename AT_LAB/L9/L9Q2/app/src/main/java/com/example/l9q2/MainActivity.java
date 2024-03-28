package com.example.l9q2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Populate list with flight options
        String[] flights = {"Flight 1", "Flight 2", "Flight 3"}; // You can replace this with actual flight data from your database
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flights);
        listView.setAdapter(adapter);

        // Handle item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFlight = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, FlightDetailsActivity.class);
                intent.putExtra("flight", selectedFlight);
                startActivity(intent);
            }
        });
    }
}
