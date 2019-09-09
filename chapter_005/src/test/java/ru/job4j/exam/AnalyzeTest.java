package ru.job4j.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    List<Analyze.User> previous = new ArrayList<>();
    List<Analyze.User> current = new ArrayList<>();
    Analyze analyze = new Analyze();

    @Before
    public void setUp() {
       previous.add(new Analyze.User(11, "Jora"));
       previous.add(new Analyze.User(1, "Gelo"));
       previous.add(new Analyze.User(13, "Ivan"));
       previous.add(new Analyze.User(4, "Tramp"));
    }

    @Test
    public void whenDiffThanInfo() {
        current.addAll(previous);
        current.add(new Analyze.User(2, "Fenix1"));
        current.add(new Analyze.User(23, "Fenix2"));
        current.add(new Analyze.User(24, "Fenix3"));
        current.add(new Analyze.User(25, "Fenix4"));
        current.add(new Analyze.User(26, "Fenix5"));
        current.remove(1);
        current.remove(1);
        current.set(0, new Analyze.User(11, "Tata"));
        current.set(1, new Analyze.User(4, "Pavel"));
        Analyze.Info expected = new Analyze.Info(5, 2, 2);
        Analyze.Info result = analyze.diff(previous, current);
       assertThat(expected, is(result));
    }
}