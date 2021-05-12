package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class SoftCacheTest {

    @Test
    public void whenGetAbsentValueItShouldBeLoaded() {
       Map<String, String> anotherSource = Map.of(
               "Russia", "Moscow",
               "USA", "Washington"
       );

        SoftCache<String, String> cache = new SoftCache<>(anotherSource::get);
        String capitalOfRussia = cache.getValue("Russia");
        Assert.assertEquals(capitalOfRussia, "Moscow");
    }
}