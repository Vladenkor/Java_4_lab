package org.example.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONSerializer<T> implements Serializer<T> {
    @Override
    public void toFile(T entity, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filename), entity);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public T fromFile(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filename), new TypeReference<T>() {});
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void listToFile(List<T> entities, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filename),entities);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public List<T> listFromFile(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filename), new TypeReference<List<T>>(){});
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }


}
