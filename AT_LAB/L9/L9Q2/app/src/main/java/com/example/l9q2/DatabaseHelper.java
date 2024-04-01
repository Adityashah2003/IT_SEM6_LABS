package com.example.l9q2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "airline_reservation.db";
    public static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_FLIGHTS = "flights";
    public static final String TABLE_CUSTOMERS = "customers";
    public static final String TABLE_RESERVATIONS = "reservations";

    // Common column names
    public static final String KEY_ID = "id";

    // Flights table - column names
    public static final String KEY_FLIGHT_NUMBER = "flight_number";
    public static final String KEY_DEPARTURE = "departure";
    public static final String KEY_DESTINATION = "destination";
    public static final String KEY_DEPARTURE_TIME = "departure_time";
    public static final String KEY_AVAILABLE_TIME = "available_time";
    public static final String KEY_AVAILABLE_DAY = "available_day";
    public static final String KEY_PRICE = "price";

    // Customers table - column names
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";

    // Reservations table - column names
    public static final String KEY_FLIGHT_ID = "flight_id";
    public static final String KEY_CUSTOMER_ID = "customer_id";

    // Table create statements
    private static final String CREATE_TABLE_FLIGHTS = "CREATE TABLE " + TABLE_FLIGHTS + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_FLIGHT_NUMBER + " TEXT," +
            KEY_DEPARTURE + " TEXT," +
            KEY_DESTINATION + " TEXT," +
            KEY_DEPARTURE_TIME + " TEXT," +
            KEY_AVAILABLE_TIME + " TEXT," +
            KEY_AVAILABLE_DAY + " TEXT," +
            KEY_PRICE + " REAL" +
            ")";

    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE " + TABLE_CUSTOMERS + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_NAME + " TEXT," +
            KEY_EMAIL + " TEXT," +
            KEY_PHONE + " TEXT" +
            ")";

    private static final String CREATE_TABLE_RESERVATIONS = "CREATE TABLE " + TABLE_RESERVATIONS + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_FLIGHT_ID + " INTEGER," +
            KEY_CUSTOMER_ID + " INTEGER," +
            "FOREIGN KEY(" + KEY_FLIGHT_ID + ") REFERENCES " + TABLE_FLIGHTS + "(" + KEY_ID + ")," +
            "FOREIGN KEY(" + KEY_CUSTOMER_ID + ") REFERENCES " + TABLE_CUSTOMERS + "(" + KEY_ID + ")" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FLIGHTS);
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_RESERVATIONS);
        insertDummyFlights(db);
        insertDummyCustomers(db);
        insertDummyReservations(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);

        onCreate(db);
    }

    // Insert dummy flights
    public void insertDummyFlights(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_FLIGHT_NUMBER, "FL123");
        values.put(KEY_DEPARTURE, "New York");
        values.put(KEY_DESTINATION, "Los Angeles");
        values.put(KEY_DEPARTURE_TIME, "2024-04-01 09:00:00");
        values.put(KEY_AVAILABLE_TIME, "2024-04-01 09:00:00");
        values.put(KEY_AVAILABLE_DAY, "Monday");
        values.put(KEY_PRICE, 200.00);
        long result = db.insert(TABLE_FLIGHTS, null, values);
        Log.d("DatabaseHelper", "Insert dummy flights result: " + result);
    }

    public void insertDummyCustomers(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "John Doe");
        values.put(KEY_EMAIL, "john.doe@example.com");
        values.put(KEY_PHONE, "1234567890");
        db.insert(TABLE_CUSTOMERS, null, values);
    }

    public void insertDummyReservations(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_FLIGHT_ID, 1); // Assuming flight with ID 1 is booked
        values.put(KEY_CUSTOMER_ID, 1); // Assuming customer with ID 1 is booking the flight
        db.insert(TABLE_RESERVATIONS, null, values);
    }
}
