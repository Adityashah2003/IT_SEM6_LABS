package com.example.l9q1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Survey.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.SurveyEntry.TABLE_NAME + " (" +
                    DatabaseContract.SurveyEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.SurveyEntry.COLUMN_QUESTION + " TEXT," +
                    DatabaseContract.SurveyEntry.COLUMN_RESPONSE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.SurveyEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

