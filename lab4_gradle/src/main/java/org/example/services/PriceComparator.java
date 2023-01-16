package org.example.services;

import org.example.Song;

import java.util.Comparator;

public class PriceComparator implements Comparator<Song> {
    @Override
    public int compare(Song song1, Song song2) {
        return Double.compare(song1.getPrice(), song2.getPrice());
    }
}
