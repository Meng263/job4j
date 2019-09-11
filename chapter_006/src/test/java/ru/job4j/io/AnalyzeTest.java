package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalyzeTest {
    @Test
    public void whenLogContainsTwoIntervalsUnavailableThanTargetGetTwo() {
        Analyze analyze = new Analyze();
        String source = Analyze.class.getClassLoader().getResource("source.log").getFile();
        String target = Analyze.class.getClassLoader().getResource("target.log").getFile();
        analyze.unavailable(source, target);
        List<String> listStrings = null;
        try (BufferedReader bf = new BufferedReader(new FileReader(target))) {
            listStrings = bf.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> result = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");
        assertThat(listStrings, is(result));
    }
}