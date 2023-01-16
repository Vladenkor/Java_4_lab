package org.example.services;

import org.example.Singer;
import org.example.Song;

import java.util.Set;
import java.util.TreeSet;

public class SongsPriceService {
    private Singer singer;
    private PriceComparator priceComparator;

    public SongsPriceService(Singer singer) {
        this.singer = singer;
        priceComparator = new PriceComparator();
    }

    public Set<Song> abovePrice(double price, SORT type) {
        TreeSet<Song> songs = new TreeSet<>(priceComparator);
        for (Song song : singer.getSongs()) {
            if (song.getPrice() >= price) {
                songs.add(song);
            }
        }
        if (type == SORT.ASC) {
            return songs;
        }
        return songs.descendingSet();
    }

    public Set<Song> belowPrice(double price, SORT type) {
        TreeSet<Song> songs = new TreeSet<>(priceComparator);
        for (Song song : singer.getSongs()) {
            if (song.getPrice() <= price) {
                songs.add(song);
            }
        }
        if (type == SORT.DSC) {
            return songs.descendingSet();
        }
        return songs;
    }

    public Set<Song> betweenSpecifiedPrices(double priceFrom, double priceTo, SORT type) {
        TreeSet<Song> songs = new TreeSet<>(priceComparator);
        for (Song song : singer.getSongs()) {
            if (song.getPrice() >= priceFrom && song.getPrice() <= priceTo) {
                songs.add(song);
            }
        }
        if (type == SORT.ASC) {
            return songs;
        }
        return songs.descendingSet();
    }
}
