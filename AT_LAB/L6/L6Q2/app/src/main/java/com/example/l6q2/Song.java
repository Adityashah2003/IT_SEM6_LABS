package com.example.l6q2;

public class Song {
    private String title;
    private String artist;
    private int resourceId;

    public Song(String title, String artist, int resourceId) {
        this.title = title;
        this.artist = artist;
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getResourceId() {
        return resourceId;
    }
}
