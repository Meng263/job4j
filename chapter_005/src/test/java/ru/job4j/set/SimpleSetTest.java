package ru.job4j.set;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    @Test
    public void whenAddElementWithDuplicateThanGetElementsWithoutDuplicate() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("1");
        set.add("2");
        set.add("2");
        set.add("3");
        set.add("3");
        set.add("3");
        set.add("4");
        List<String> expected = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        List<String> result = new ArrayList<>();
        for (String elem : set) {
            result.add(elem);
        }
        assertThat(expected, is(result));
    }

    @Test
    public void whenAddNullElementsThanSetContainsNull() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add(null);
        set.add("2");
        set.add("1");
        set.add(null);
        List<String> expected = new ArrayList<String>(Arrays.asList("1", null, "2"));
        List<String> result = new ArrayList<>();
        for (String elem : set) {
            result.add(elem);
        }
        assertThat(expected, is(result));
    }
}