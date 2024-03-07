package com.example.l6q2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Song song = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_song, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView artistTextView = convertView.findViewById(R.id.artistTextView);

        if (song != null) {
            titleTextView.setText(song.getTitle());
            artistTextView.setText(song.getArtist());
        }

        return convertView;
    }
}

