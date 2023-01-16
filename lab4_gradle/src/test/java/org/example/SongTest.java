package org.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class SongTest {
    @Test
    public void testEqualSong() {
        Song song1 = new Song.Builder().setName("Bones").setGenre(GENRES.ROCK).setPrice(2.55).setListens(50000).build();
        Song song2 = new Song.Builder().setName("Bones").setGenre(GENRES.ROCK).setPrice(2.55).setListens(50000).build();
        Assert.assertEquals(song1, song2);
    }

    @Test
    public void negativeTestEqualSong() {
        Song song1 = new Song.Builder().setName("Bones").setGenre(GENRES.ROCK).setPrice(2.55).setListens(50000).build();
        Song song2 = new Song.Builder().setName("Bones").setGenre(GENRES.ROCK).setPrice(2.55).setListens(50000).setAlbum("Mercury").build();
        Assert.assertNotEquals(song1, song2);
    }

    @DataProvider(name = "hashcode-provider")
    public Object[][] testHashCodeProvider() {
        return new Object[][]{
                {
                        new Song.Builder()
                                .setName("Enter Sandman")
                                .setGenre(GENRES.ROCK)
                                .setPrice(2.55)
                                .setListens(25000)
                                .build().hashCode(),

                        new Song.Builder()
                                .setName("Enter Sandman")
                                .setGenre(GENRES.ROCK)
                                .setPrice(2.55)
                                .setListens(25000)
                                .build().hashCode()
                },
                {
                        new Song.Builder()
                                .setName("Nothing Else Matters")
                                .setGenre(GENRES.ROCK)
                                .setAlbum("Metallica")
                                .setPrice(2.55)
                                .setListens(25000)
                                .build().hashCode(),

                        new Song.Builder()
                                .setName("Nothing Else Matters")
                                .setGenre(GENRES.ROCK)
                                .setAlbum("Metallica")
                                .setPrice(2.55)
                                .setListens(25000)
                                .build().hashCode()
                }
        };
    }

    @Test(dataProvider = "hashcode-provider")
    public void testHashCode(int p1, int p2) {
        Assert.assertEquals(p1, p2);
    }


    @DataProvider(name = "hashcode-provider-neg")
    public Object[][] testNegativeHashCodeProvider() {
        return new Object[][]{
                {
                        new Song.Builder()
                                .setName("Enter Sandman")
                                .setGenre(GENRES.ROCK)
                                .setPrice(2.55)
                                .setListens(25000)
                                .build().hashCode(),

                        new Song.Builder()
                                .setName("Enter Sandman")
                                .setGenre(GENRES.POP)
                                .setPrice(2.55)
                                .setListens(25000)
                                .build().hashCode()
                },
                {
                        new Song.Builder()
                                .setName("Nothing Else Matters")
                                .setGenre(GENRES.ROCK)
                                .setAlbum("Metallica")
                                .setPrice(2.55)
                                .setListens(15000)
                                .build().hashCode(),

                        new Song.Builder()
                                .setName("Nothing Else Matters")
                                .setGenre(GENRES.ROCK)
                                .setAlbum("Inquisition Symphony")
                                .setPrice(2.55)
                                .setListens(15000)
                                .build().hashCode()
                }
        };
    }

    @Test(dataProvider = "hashcode-provider-neg")
    public void testNegativeHashCode(int p1, int p2) {
        Assert.assertNotEquals(p1, p2);
    }

    /*
     *    Testing updates for lab.4
     */

    @Test
    public void nullName() {
        assertThrows(IllegalArgumentException.class, () -> new Song.Builder().setPrice(2.55).setListens(12500).build());
    }

    @Test
    public void negativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Song.Builder().setName("Retro").setPrice(-3.5).setListens(12500).build());
    }

    @Test
    public void greaterPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Song.Builder().setName("Retro").setPrice(12.0).setListens(12500).build());
    }

    @Test
    public void negativeListens() {
        assertThrows(IllegalArgumentException.class, () -> new Song.Builder().setName("Retro").setPrice(5.0).setListens(-12500).build());
    }
}
