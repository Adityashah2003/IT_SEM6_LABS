package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, commentsEditText;
    private RadioGroup radioGroup;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        commentsEditText = findViewById(R.id.comments);
        radioGroup = findViewById(R.id.radio);
        ratingBar = findViewById(R.id.rating);

        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String comments = commentsEditText.getText().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                String gender = radioButton.getText().toString();
                float rating = ratingBar.getRating();

                String message = "Name: " + name + "\nEmail: " + email + "\nGender: " + gender + "\nComments: " + comments + "\nRating: " + rating;
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
