package org.example.services;

import org.example.Singer;
import org.example.Song;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SongsListensServiceTest {
    private static Singer singer;
    private static Song song1;
    private static Song song2;
    private static Song song3;
    private static Song song4;

    @BeforeClass
    public static void beforeClass() {
        song1 = new Song.Builder().setName("Bones").setAlbum("Mercury").setPrice(2.55).setListens(55003).build();
        song2 = new Song.Builder().setName("Enemy").setAlbum("Mercury").setPrice(3.99).setListens(266055).build();
        song3 = new Song.Builder().setName("Believer").setAlbum("Evolve").setPrice(3.59).setListens(343899).build();
        song4 = new Song.Builder().setName("Thunder").setAlbum("Evolve").setPrice(3.50).setListens(554066).build();

        singer = new Singer.Builder().setName("Imagine Dragons").setSongs(List.of(song1, song2, song3, song4)).build();
    }

    @Test
    public void abovePrice() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song3);
        expectedSongs.add(song4);
        SongsListensService songsListensService = new SongsListensService(singer);
        Set<Song> songs = songsListensService.aboveListens(300000);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }

    @Test
    public void belowPrice() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song1);
        SongsListensService songsListensService = new SongsListensService(singer);
        Set<Song> songs = songsListensService.belowListens(60000);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }

    @Test
    public void betweenPrice() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song2);
        expectedSongs.add(song3);
        SongsListensService songsListensService = new SongsListensService(singer);
        Set<Song> songs = songsListensService.betweenListens(200000, 400000);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }
}
