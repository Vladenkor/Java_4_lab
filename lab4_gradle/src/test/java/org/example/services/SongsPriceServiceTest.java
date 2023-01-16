package org.example.services;

import org.example.Singer;
import org.example.Song;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SongsPriceServiceTest {
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
    public void abovePriceAsc() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song4);
        expectedSongs.add(song3);
        expectedSongs.add(song2);
        SongsPriceService songsPriceService = new SongsPriceService(singer);
        Set<Song> songs = songsPriceService.abovePrice(3.30, SORT.ASC);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }

    @Test
    public void abovePriceDsc() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song2);
        expectedSongs.add(song3);
        expectedSongs.add(song4);
        SongsPriceService songsPriceService = new SongsPriceService(singer);
        Set<Song> songs = songsPriceService.abovePrice(3.30, SORT.DSC);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }

    @Test
    public void belowPriceAsc() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song1);
        expectedSongs.add(song4);
        expectedSongs.add(song3);
        SongsPriceService songsPriceService = new SongsPriceService(singer);
        Set<Song> songs = songsPriceService.belowPrice(3.60, SORT.ASC);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }

    @Test
    public void belowPriceDsc() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song3);
        expectedSongs.add(song4);
        expectedSongs.add(song1);
        SongsPriceService songsPriceService = new SongsPriceService(singer);
        Set<Song> songs = songsPriceService.belowPrice(3.60, SORT.DSC);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }

    @Test
    public void betweenPriceAsc() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song3);
        expectedSongs.add(song2);
        SongsPriceService songsPriceService = new SongsPriceService(singer);
        Set<Song> songs = songsPriceService.betweenSpecifiedPrices(3.55, 4.00, SORT.ASC);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }

    @Test
    public void betweenPriceDsc() {
        Set<Song> expectedSongs = new LinkedHashSet<>();
        expectedSongs.add(song2);
        expectedSongs.add(song3);
        SongsPriceService songsPriceService = new SongsPriceService(singer);
        Set<Song> songs = songsPriceService.betweenSpecifiedPrices(3.55, 4.0, SORT.DSC);
        Assert.assertEquals(songs.toString(), expectedSongs.toString());
    }
}
