package org.example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertThrows;

public class SingerTest {
    @Test
    public void testEqualSong() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song.Builder().setName("Wrecked").build());

        Singer singer1 = new Singer.Builder().setName("Imagine Dragons").setSongs(songs).build();
        Singer singer2 = new Singer.Builder().setName("Imagine Dragons").setSongs(songs).build();
        Assert.assertEquals(singer1, singer2);
    }

    @Test
    public void negativeTestEqualSong() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song.Builder().setName("Wrecked").build());

        Singer singer1 = new Singer.Builder().setName("Imagine Dragons").setSongs(songs).build();
        Singer singer2 = new Singer.Builder().setName("AC DC").setSongs(songs).build();
        Assert.assertNotEquals(singer1, singer2);
    }

    @DataProvider(name = "hashcode-provider")
    public Object[][] testHashCodeProvider() {
        List<Song> songs1 = new ArrayList<>();
        songs1.add(new Song.Builder().setName("Enter Sandman").build());
        songs1.add(new Song.Builder().setName("Nothing Else Matters").build());

        return new Object[][]{
                {
                        new Singer.Builder()
                                .setName("Metallica")
                                .setSongs(songs1)
                                .build().hashCode(),

                        new Singer.Builder()
                                .setName("Metallica")
                                .setSongs(songs1)
                                .build().hashCode()
                },
                {
                        new Singer.Builder()
                                .setName("Apocalyptica")
                                .setSongs(songs1)
                                .build().hashCode(),

                        new Singer.Builder()
                                .setName("Apocalyptica")
                                .setSongs(songs1)
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
        List<Song> songs1 = new ArrayList<>();
        songs1.add(new Song.Builder().setName("Enter Sandman").build());
        songs1.add(new Song.Builder().setName("Nothing Else Matters").build());

        List<Song> songs2 = new ArrayList<>();
        songs1.add(new Song.Builder().setName("Utopia").build());

        return new Object[][]{
                {
                        new Singer.Builder()
                                .setName("Apocalyptica")
                                .setSongs(songs1)
                                .build().hashCode(),

                        new Singer.Builder()
                                .setName("Metallica")
                                .setSongs(songs1)
                                .build().hashCode()
                },
                {
                        new Singer.Builder()
                                .setName("Apocalyptica")
                                .setSongs(songs1)
                                .build().hashCode(),

                        new Singer.Builder()
                                .setName("Apocalyptica")
                                .setSongs(songs2)
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
        assertThrows(IllegalArgumentException.class, () -> new Singer.Builder().setEmail("name@gmail.com").build());
    }

    @Test
    public void wrongEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Singer.Builder().setName("Metallica").setEmail("wrongnamegmail.com").build());
    }

    @Test
    public void longEmail() {
        assertThrows(IllegalArgumentException.class, () -> new Singer.Builder().setName("Metallica").setEmail("thisisreallylongemailnamethatwillthrowanexception@gmail.com").build());
    }

}
