package com.example.l8q3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "clinical_management.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE doctors_schedule (id INTEGER PRIMARY KEY, time TEXT, is_available INTEGER)");
        db.execSQL("INSERT INTO doctors_schedule (time, is_available) VALUES ('09:00', 1)");
        db.execSQL("INSERT INTO doctors_schedule (time, is_available) VALUES ('10:00', 1)");
        db.execSQL("INSERT INTO doctors_schedule (time, is_available) VALUES ('11:00', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS doctors_schedule");
        onCreate(db);
    }
}

