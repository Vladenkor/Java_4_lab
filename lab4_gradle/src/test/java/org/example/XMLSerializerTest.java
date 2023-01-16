package org.example;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLSerializerTest {
    @Test
    public void xmlCarToFile() {
        XmlMapper mapper = new XmlMapper();
        Song song = new Song.Builder().setName("Enter Sandman").build();
        try {
            mapper.writeValue(new File("song.xml"), song);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCarListToFile() {
        List<Song> entities = new ArrayList<Song>();
        Song song1 = new Song.Builder().setName("Enter Sandman").build();
        Song song2 = new Song.Builder().setName("Nothing Else Matters").build();
        entities.add(song2);
        entities.add(song1);
        XmlMapper mapper = new XmlMapper();
        try {
            mapper.writeValue(new File("song1.xml"), entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
