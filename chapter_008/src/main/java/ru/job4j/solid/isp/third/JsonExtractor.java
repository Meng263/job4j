package ru.job4j.solid.isp.third;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Collection;

public class JsonExtractor implements IDataExtractor {
    @Override
    public String extractText() {
        return null;
    }

    @Override
    public Collection<?> extractCollection() {
        return null;
    }

    @Override
    public JSONPObject extractJson() {
        JSONPObject jsonObject = null;
        try {
            jsonObject = new ObjectMapper().readValue("{key: value }", JSONPObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
