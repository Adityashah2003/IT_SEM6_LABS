package com.example.l3q1;

import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

class ImageAdapter extends BaseAdapter {
    private Context context;
    private int[] items;

    public ImageAdapter(Context context, int[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(items[position]);
        return imageView;
    }
}

public class MainActivity extends AppCompatActivity {
    int[] items = {R.drawable.th,R.drawable.th,R.drawable.th,R.drawable.th,R.drawable.th};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gv = (GridView) findViewById(R.id.gv);

        ImageAdapter adapter = new ImageAdapter(this, items);
        gv.setAdapter(adapter);
    }
}
