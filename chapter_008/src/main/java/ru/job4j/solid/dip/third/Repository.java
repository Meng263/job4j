package ru.job4j.solid.dip.third;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private Map<String, List<String>> storage = new LinkedHashMap<>();

    public void saveAttributes(String userId, List<String> attributes) {
        storage.put(userId, attributes);
    }

    public List<String> getAttributes(String userId) {
        return storage.get(userId);
    }
}
