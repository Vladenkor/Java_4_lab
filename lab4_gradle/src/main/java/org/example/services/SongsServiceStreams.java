package org.example.services;

import org.example.Singer;
import org.example.Song;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SongsServiceStreams {
    private Singer singer;

    public SongsServiceStreams(Singer singer) {
        this.singer = singer;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public List<Song> sortAboveSetPrice(double price) {
        return singer.getSongs().stream()
                .filter(s -> s.getPrice() >= price)
                .sorted(Comparator.comparing(Song::getPrice))
                .collect(Collectors.toList());
    }


    public List<Song> sortBelowSetPrice(double price) {
        return singer.getSongs().stream()
                .filter(s -> s.getPrice() <= price)
                .sorted(Comparator.comparing(Song::getPrice))
                .collect(Collectors.toList());
    }


    public List<Song> sortBetweenSetPrice(double priceFrom, double priceTo) {
        return singer.getSongs().stream()
                .filter(s -> s.getPrice() >= priceFrom && s.getPrice() <= priceTo)
                .sorted(Comparator.comparing(Song::getPrice))
                .collect(Collectors.toList());
    }

}
