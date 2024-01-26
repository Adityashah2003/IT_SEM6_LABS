package com.example.l2q2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText intxt;
    private Button button;
    public String name;
    public String newname;

    public String encrypt(String newStr){
        newStr = newStr.charAt(newStr.length() - 1) + newStr.substring(0, newStr.length() - 1);
        return newStr;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intxt = findViewById(R.id.input);
        button = findViewById(R.id.sub_butt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = intxt.getText().toString();
                newname = encrypt(name);
                //Toast.makeText(MainActivity.this,"input: "+ name,Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,"output: "+newname ,Toast.LENGTH_LONG).show();
            }
        });
    }
}