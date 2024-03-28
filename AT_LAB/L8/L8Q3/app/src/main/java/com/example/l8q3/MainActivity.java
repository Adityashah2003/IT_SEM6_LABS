package com.example.l8q3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPatientName, editTextAppointmentTime;
    private Button buttonScheduleAppointment;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPatientName = findViewById(R.id.editTextPatientName);
        editTextAppointmentTime = findViewById(R.id.editTextAppointmentTime);
        buttonScheduleAppointment = findViewById(R.id.buttonScheduleAppointment);
        dbHelper = new DBHelper(this);

        buttonScheduleAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = editTextPatientName.getText().toString().trim();
                String appointmentTime = editTextAppointmentTime.getText().toString().trim();

                if (patientName.isEmpty() || appointmentTime.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (scheduleAppointment(patientName, appointmentTime)) {
                        Toast.makeText(MainActivity.this, "Appointment Scheduled", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No available doctors at this time", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean scheduleAppointment(String patientName, String appointmentTime) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String query = "SELECT * FROM doctors_schedule WHERE time = ? AND is_available = 1";
            Cursor cursor = db.rawQuery(query, new String[]{appointmentTime});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                @SuppressLint("Range") int doctorId = cursor.getInt(cursor.getColumnIndex("id"));

                String doctorName = "Doctor " + doctorId;
                String appointmentDetails = "Appointment scheduled with " + doctorName + " at " + appointmentTime;

                ContentValues values = new ContentValues();
                values.put("is_available", 0);
                cursor.close();
                Toast.makeText(MainActivity.this, appointmentDetails, Toast.LENGTH_SHORT).show();
                return true;
            } else {
                cursor.close();
                return false;
            }
        }

}

