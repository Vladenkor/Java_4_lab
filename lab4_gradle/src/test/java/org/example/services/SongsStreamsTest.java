package org.example.services;

import org.example.Singer;
import org.example.Song;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SongsStreamsTest {
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
    public void aboveSpecifiedPrice(){
        SongsServiceStreams sortService = new SongsServiceStreams(singer);
        List<Song> expected = List.of(song4, song3, song2);
        List<Song> actual = sortService.sortAboveSetPrice(3.30);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void belowSpecifiedPrice(){
        List<Song> expected = List.of(song1, song4, song3);
        SongsServiceStreams sortService = new SongsServiceStreams(singer);
        List<Song> actual = sortService.sortBelowSetPrice(3.60);

        Assert.assertEquals(expected,actual);
    }


    @Test
    public void betweenSpecifiedPrices(){
        List<Song> expected = List.of(song3, song2);
        SongsServiceStreams sortService = new SongsServiceStreams(singer);
        List<Song> actual = sortService.sortBetweenSetPrice(3.55,4.00);
        Assert.assertEquals(expected,actual);
    }

}
