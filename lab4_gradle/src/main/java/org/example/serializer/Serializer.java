package org.example.serializer;

import java.io.FileNotFoundException;
import java.util.List;

public interface Serializer<T> {

    void toFile(T entity, String filename);

    T fromFile(String filename) throws FileNotFoundException;

    void listToFile(List<T> entities, String filename);

    List<T> listFromFile(String filename);

}
