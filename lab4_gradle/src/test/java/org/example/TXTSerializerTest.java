package org.example;

import org.example.serializer.TXTSerializer;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.testng.AssertJUnit.assertEquals;

public class TXTSerializerTest {
    @Test
    public void toFile() {
        Song song = new Song.Builder().setName("Enter Sandman").build();
        TXTSerializer<Song> serializer = new TXTSerializer<>();
        serializer.toFile(song, "song.txt");
        assertEquals(fileContent("songData.txt"), fileContent("song.txt"));
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

    @Test
    public void fromFile(){
        TXTSerializer<Song> serializer = new TXTSerializer<>();
        try {
            serializer.fromFile("song.txt");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        assertEquals(fileContent("song.txt"), fileContent("songData.txt"));
    }
}
