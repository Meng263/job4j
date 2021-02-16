package ru.job4j.solid.isp.third;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Collection;

public class textExtractor implements IDataExtractor {
    @Override
    public String extractText() {
        return "Some extracted text";
    }

    @Override
    public Collection<?> extractCollection() {
        return null;
    }

    @Override
    public JSONPObject extractJson() {
        return null;
    }
}
