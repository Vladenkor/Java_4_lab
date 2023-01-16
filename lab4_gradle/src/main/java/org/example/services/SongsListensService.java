package org.example.services;

import org.example.Singer;
import org.example.Song;

import java.util.LinkedHashSet;
import java.util.Set;

public class SongsListensService {
    private Singer singer;

    public SongsListensService(Singer singer) {
        this.singer = singer;
    }

    public Set<Song> aboveListens(int listens) {
        Set<Song> songs = new LinkedHashSet<>();
        for (Song song : singer.getSongs()) {
            if (song.getListens() >= listens) {
                songs.add(song);
            }
        }
        return songs;
    }

    public Set<Song> belowListens(int listens) {
        Set<Song> songs = new LinkedHashSet<>();
        for (Song song : singer.getSongs()) {
            if (song.getListens() <= listens) {
                songs.add(song);
            }
        }
        return songs;
    }

    public Set<Song> betweenListens(int listensFrom, int listensTo) {
        Set<Song> songs = new LinkedHashSet<>();
        for (Song song : singer.getSongs()) {
            if (song.getListens() >= listensFrom && song.getListens() <= listensTo) {
                songs.add(song);
            }
        }
        return songs;
    }
}
