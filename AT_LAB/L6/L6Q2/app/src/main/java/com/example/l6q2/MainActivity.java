package com.example.l6q2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView songListView;
    private TextView nowPlayingTextView;
    private MediaPlayer mediaPlayer;
    private ArrayList<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songListView = findViewById(R.id.songList);
        nowPlayingTextView = findViewById(R.id.nowPlaying);
        mediaPlayer = new MediaPlayer();

        songList = new ArrayList<>();
        songList.add(new Song("Song 1", "Artist 1", R.raw.song));
        songList.add(new Song("Song 2", "Artist 2", R.raw.song));

        SongAdapter adapter = new SongAdapter(this, songList);
        songListView.setAdapter(adapter);

        songListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song selectedSong = songList.get(position);
                playSong(selectedSong);
            }
        });
    }

    private void playSong(Song song) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(getResources().openRawResourceFd(song.getResourceId()));
            mediaPlayer.prepare();
            mediaPlayer.start();

            nowPlayingTextView.setText("Now Playing: " + song.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
