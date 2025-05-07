package fr.diginamic.geoff.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Geoff
 * Utility focused class that handles jackson implementation and creates an object mapper.
 */
public class JsonParser {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Method that reads through a json file using Jackson and returns a list of the specified DTO
     *
     * @param clazz   the target .class for the list
     * @param jsonUrl the String url of the file
     * @param <T>     the specified class for the list
     * @return a list of the specified class
     * @throws IOException;
     */
    public <T> List<T> tryReading(Class<T> clazz, String jsonUrl) throws IOException {
        return mapper.readValue(new File(jsonUrl), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}