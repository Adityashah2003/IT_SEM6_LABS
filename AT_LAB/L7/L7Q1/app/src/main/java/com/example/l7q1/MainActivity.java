package com.example.l7q1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editRollNumber, editName, editMarks;
    Button addButton, viewButton,updateButton,deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editRollNumber = (EditText)findViewById(R.id.rollNumber);
        editName = (EditText)findViewById(R.id.name);
        editMarks = (EditText)findViewById(R.id.marks);
        addButton = (Button)findViewById(R.id.addButton);
        viewButton = (Button)findViewById(R.id.viewButton);
        updateButton = (Button)findViewById(R.id.updateButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = editRollNumber.getText().toString();
                String name = editName.getText().toString();
                String marks = editMarks.getText().toString();
                myDb.insertData(rollNumber, name, marks);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Roll Number :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Marks :"+ res.getString(2)+"\n\n");
                }

                showMessage("Data",buffer.toString());
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = editRollNumber.getText().toString();
                String name = editName.getText().toString();
                String marks = editMarks.getText().toString();

                boolean isUpdated = myDb.updateData(rollNumber, name, marks);

                if (isUpdated) {
                    showMessage("Success", "Data updated successfully");
                } else {
                    showMessage("Error", "Failed to update data");
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = editRollNumber.getText().toString();

                Integer deletedRows = myDb.deleteData(rollNumber);

                if (deletedRows > 0) {
                    showMessage("Success", "Data deleted successfully");
                } else {
                    showMessage("Error", "Failed to delete data");
                }
            }
        });

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
