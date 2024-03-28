package com.example.l8q1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextProductName, editTextProductPrice;
    Button buttonAddProduct, buttonShowProductStats;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonShowProductStats = findViewById(R.id.buttonShowProductStats);

        dbHelper = new DBHelper(this);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editTextProductName.getText().toString().trim();
                String productPriceStr = editTextProductPrice.getText().toString().trim();

                if (productName.isEmpty() || productPriceStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter product details", Toast.LENGTH_SHORT).show();
                    return;
                }

                double productPrice = Double.parseDouble(productPriceStr);

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DBHelper.COLUMN_PRODUCT_NAME, productName);
                values.put(DBHelper.COLUMN_PRODUCT_PRICE, productPrice);

                long newRowId = db.insert(DBHelper.TABLE_PRODUCTS, null, values);

                if (newRowId == -1) {
                    Toast.makeText(MainActivity.this, "Error adding product", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                }

                db.close();
            }
        });

        buttonShowProductStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayProductsActivity.class);
                startActivity(intent);
            }
        });
    }
}
