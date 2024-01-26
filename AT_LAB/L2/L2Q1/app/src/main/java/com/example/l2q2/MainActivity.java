package com.example.l2q2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lists = findViewById(R.id.groc_lists);
        ArrayList<String> groc = new ArrayList<>();

        groc.add("Milk");
        groc.add("Fruit");
        groc.add("veg");
        groc.add("non-veg");
        groc.add("Drinks");

        ArrayAdapter<String> adap = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                groc
        );
        lists.setAdapter(adap);

        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, groc.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
