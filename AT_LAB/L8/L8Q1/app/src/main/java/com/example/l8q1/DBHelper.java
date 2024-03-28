package com.example.l8q1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Products.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_PRICE = "price";

    private static final String SQL_CREATE_PRODUCTS_TABLE =
            "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                    COLUMN_PRODUCT_NAME + " TEXT," +
                    COLUMN_PRODUCT_PRICE + " REAL)";

    private static final String SQL_DELETE_PRODUCTS_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_PRODUCTS;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUCTS_TABLE);
        onCreate(db);
    }
}
