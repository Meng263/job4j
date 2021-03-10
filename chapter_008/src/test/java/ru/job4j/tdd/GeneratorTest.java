package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GeneratorTest {
    @Ignore
    @Test
    public void singleKeyValueCase() {
        Map<String, String> args = Map.of("name", "Petr");
        String template = "I am a ${name}.";
        Generator generator = new GeneratorImp();
        String expected = "I am a Petr.";
        String result = generator.produce(template, args);

        assertEquals(expected, result);
    }

    @Ignore
    @Test
    public void twoKeyValueCase() {
        Map<String, String> args = Map.of(
                "name", "Petr",
                "subject", "you"
        );

        String template = "I am a ${name}, Who are ${subject}?";
        Generator generator = new GeneratorImp();
        String expected = "I am a Petr, Who are you?";
        String result = generator.produce(template, args);

        assertEquals(expected, result);
    }

    @Ignore
    @Test
    public void doubleKeyCase() {
        Map<String, String> args = Map.of(
                "name", "Petr",
                "subject", "you"
        );

        String template = "I am a ${name}, Are you ${name} too?";
        Generator generator = new GeneratorImp();
        String expected = "I am a Petr, Are you Petr too?";
        String result = generator.produce(template, args);

        assertEquals(expected, result);
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void mapAbsentKeyCase() {
        Map<String, String> args = Map.of("name", "Petr");

        String template = "I am a ${name}, Who are ${subject}?";
        Generator generator = new GeneratorImp();
        String result = generator.produce(template, args);
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void mapRedundantKeyCase() {
        Map<String, String> args = Map.of(
                "name", "Petr",
                "subject", "you",
                "object", "some object"
        );

        String template = "I am a ${name}, Who are ${subject}?";
        Generator generator = new GeneratorImp();
        String result = generator.produce(template, args);
    }
}