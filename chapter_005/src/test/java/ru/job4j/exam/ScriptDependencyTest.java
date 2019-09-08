package ru.job4j.exam;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import java.util.*;

import static org.hamcrest.Matchers.is;

public class ScriptDependencyTest {
    ScriptDependency scriptDependency = new ScriptDependency();
    Map<Integer, List<Integer>> map = new HashMap<>();

    @Before
    public void setUp() {
        map.put(1, new LinkedList<>(Arrays.asList(2, 3)));
        map.put(2, new LinkedList<>((Arrays.asList(4))));
        map.put(3, new LinkedList<>(Arrays.asList(4, 5)));
        map.put(4, new LinkedList<>(Arrays.asList()));
        map.put(5, new LinkedList<>(Arrays.asList()));
    }

    @Test
    public void whenOneThanResultHasAllElements() {
        List<Integer> expected = List.of(1, 2, 3, 4, 5);
        assertThat(expected, is(scriptDependency.load(map, 1)));
    }

    @Test
    public void whenThreeThanResultHasThreeElements() {
        List<Integer> expected = List.of(3, 4, 5);
        assertThat(expected, is(scriptDependency.load(map, 3)));
    }

    @Test
    public void whenFourThanResultHasOneElements() {
        List<Integer> expected = List.of(4);
        assertThat(expected, is(scriptDependency.load(map, 4)));
    }
}