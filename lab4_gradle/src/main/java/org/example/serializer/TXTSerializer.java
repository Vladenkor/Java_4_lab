package org.example.serializer;

import lombok.SneakyThrows;
import org.example.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TXTSerializer<T> implements Serializer<T> {
    @Override
    public void toFile(T entity, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(entity.toString());
            fileWriter.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public T fromFile(String filename) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            line = br.readLine();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            try {
                line = br.readLine();
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
        String everything = sb.toString();
        return (T) new Song().fromStringSerialize(everything);

    }

    private String toString(List<T> entities) {
        String res = "";
        for (int i = 0; i < entities.size(); i++) {
            res += entities.get(i).toString();
            if (i + 1 < entities.size()) {
                res += "||";
            }
            System.out.println();
        }
        return res;
    }

    @Override
    public void listToFile(List<T> entities, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (T song : entities) {
                Song s = (Song) song;
                stringBuilder.append(s.toStringSerialize()).append(";");
            }
            fileWriter.write(stringBuilder.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public List<T> listFromFile(String filename) {
        List<T> songs = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        while (line != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator());
            try {
                line = bufferedReader.readLine();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

            String sb1 = new String(stringBuilder);
            String[] objString = sb1.split(";");
            for (String item : objString) {
                songs.add((T) new Song().fromStringSerialize(item));
            }
        }
        return songs;

    }
}
