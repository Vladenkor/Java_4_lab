package org.example;

import lombok.SneakyThrows;
import org.example.serializer.JSONSerializer;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.testng.AssertJUnit.assertEquals;

public class JSONSerializerTest {
    @Test
    public void toFile() {
        Song song = new Song.Builder().setName("Enter Sandman").build();
        JSONSerializer<Song> serializer = new JSONSerializer<>();
        serializer.toFile(song, "song.json");
        assertEquals(fileContent("songData.json"), fileContent("song.json"));
    }

    private String fileContent(String fileName){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            StringBuilder sb = new StringBuilder();
            while(scanner.hasNextLine()){
                sb.append(scanner.nextLine());
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Test
    public void fromFile(){
        JSONSerializer<Song> serializer = new JSONSerializer<>();
        serializer.fromFile("song.json");
        assertEquals(fileContent("song.json"), fileContent("songData.json"));
    }

    @SneakyThrows
    @Test
    public void jsonCarListToFile(){
        List<Song> songs = new ArrayList<Song>();
        Song song1 = new Song.Builder().setName("Enter Sandman").build();
        Song song2 = new Song.Builder().setName("Nothing Else Matters").build();
        songs.add(song2);
        songs.add(song1);
        JSONSerializer<Song> serializer = new JSONSerializer<>();
        serializer.listToFile(songs,"songList.json");
        assertEquals(fileContent("songList.json"), fileContent("songListData.json"));
    }

    @SneakyThrows
    @Test
    public void jsonCarListFromFile(){
        JSONSerializer<Song> serializer = new JSONSerializer<>();
        serializer.listFromFile("songList.json");
        assertEquals(fileContent("songList.json"), fileContent("songListData.json"));
    }


}
