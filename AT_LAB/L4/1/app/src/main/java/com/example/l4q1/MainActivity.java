package com.example.l4q1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private quesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<question> ques = new ArrayList<>();
        ques.add(new question("Question 1", "Option A", "Option B", "Option C", "Option A"));
        ques.add(new question("Question 2", "Option A", "Option B", "Option C", "Option B"));
        ques.add(new question("Question 3", "Option A", "Option B", "Option C", "Option C"));
        ques.add(new question("Question 4", "Option A", "Option B", "Option C", "Option A"));

        adapter = new quesAdapter(ques);
        rv.setAdapter(adapter);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Submit Quiz")
                        .setMessage("Are you sure you want to submit the quiz?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> showScoreDialog())
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
    }
    private void showScoreDialog() {
        int score = adapter.calculateScore();
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", adapter.getItemCount());
        startActivity(intent);
    }
}
