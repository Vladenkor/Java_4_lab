package org.example.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLSerializer<T> implements Serializer<T> {
    @Override
    public void toFile(T entity, String filename) {
        XmlMapper mapper = new XmlMapper();
        try {
            mapper.writeValue(new File(filename), entity);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public T fromFile(String filename) {
        XmlMapper mapper = new XmlMapper();
        try {
            return mapper.readValue(new File(filename), new TypeReference<T>() {
            });
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void listToFile(List<T> entities, String filename) {
        XmlMapper mapper = new XmlMapper();
        try {
            mapper.writeValue(new File(filename), entities);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public List<T> listFromFile(String filename) {
        XmlMapper mapper = new XmlMapper();
        try {
            return mapper.readValue(new File(filename), new TypeReference<List<T>>() {
            });
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

}
