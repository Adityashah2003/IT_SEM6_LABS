package com.example.l8q1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayProductsActivity extends AppCompatActivity {

    TextView textViewTotalProducts, textViewMaxPricedProduct, textViewMinPricedProduct;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);

        textViewTotalProducts = findViewById(R.id.textViewTotalProducts);
        textViewMaxPricedProduct = findViewById(R.id.textViewMaxPricedProduct);
        textViewMinPricedProduct = findViewById(R.id.textViewMinPricedProduct);

        dbHelper = new DBHelper(this);

        displayProductStats();
    }

    private void displayProductStats() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(*), MAX(" + DBHelper.COLUMN_PRODUCT_PRICE + "), MIN(" + DBHelper.COLUMN_PRODUCT_PRICE + ") FROM " + DBHelper.TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {
            int totalProducts = cursor.getInt(0);
            double maxPrice = cursor.getDouble(1);
            double minPrice = cursor.getDouble(2);

            textViewTotalProducts.setText("Total Products: " + totalProducts);
            textViewMaxPricedProduct.setText("Max Priced Product: " + maxPrice);
            textViewMinPricedProduct.setText("Min Priced Product: " + minPrice);
        }

        cursor.close();
        db.close();
    }
}
