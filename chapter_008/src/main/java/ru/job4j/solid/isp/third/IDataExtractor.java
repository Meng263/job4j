package ru.job4j.solid.isp.third;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Collection;

public interface IDataExtractor {
    String extractText();

    Collection<?> extractCollection();

    JSONPObject extractJson();
}
