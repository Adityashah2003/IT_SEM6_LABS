package com.example.l9q1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        private TextView question1TextView, question2TextView;
        private EditText response1EditText, response2EditText;
        private Button submitButton; // Added submitButton variable
        private SQLiteDatabase database; // Added database variable

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Initialize views
            question1TextView = findViewById(R.id.question1TextView);
            response1EditText = findViewById(R.id.response1EditText);
            question2TextView = findViewById(R.id.question2TextView);
            response2EditText = findViewById(R.id.response2EditText);
            submitButton = findViewById(R.id.submitButton);

            // Initialize database
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            database = dbHelper.getWritableDatabase();

            // Initial questions
            showQuestions();

            // Button click listener
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertResponses();
                }
            });
        }

        private void showQuestions() {
            // Display questions
            question1TextView.setText("Question 1");
            question2TextView.setText("Question 2");
        }

        private void insertResponses() {
            String response1 = response1EditText.getText().toString().trim();
            String response2 = response2EditText.getText().toString().trim();

            // Ensure responses are not empty
            if (response1.isEmpty() || response2.isEmpty()) {
                Toast.makeText(this, "Please enter responses for all questions", Toast.LENGTH_SHORT).show();
                return;
            }

            // Prepare data for insertion
            ContentValues values1 = new ContentValues();
            values1.put(DatabaseContract.SurveyEntry.COLUMN_QUESTION, question1TextView.getText().toString());
            values1.put(DatabaseContract.SurveyEntry.COLUMN_RESPONSE, response1);

            ContentValues values2 = new ContentValues();
            values2.put(DatabaseContract.SurveyEntry.COLUMN_QUESTION, question2TextView.getText().toString());
            values2.put(DatabaseContract.SurveyEntry.COLUMN_RESPONSE, response2);

            // Insert data into database
            long newRowId1 = database.insert(DatabaseContract.SurveyEntry.TABLE_NAME, null, values1);
            long newRowId2 = database.insert(DatabaseContract.SurveyEntry.TABLE_NAME, null, values2);

            // Check if insertion was successful
            if (newRowId1 != -1 && newRowId2 != -1) {
                Toast.makeText(this, "Responses submitted successfully", Toast.LENGTH_SHORT).show();
                response1EditText.setText(""); // Clear EditText
                response2EditText.setText(""); // Clear EditText
            } else {
                Toast.makeText(this, "Failed to submit responses", Toast.LENGTH_SHORT).show();
            }
        }
}

