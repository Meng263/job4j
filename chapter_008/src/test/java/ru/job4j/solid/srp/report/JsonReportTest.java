package ru.job4j.solid.srp.report;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.srp.MemStore;
import ru.job4j.solid.srp.MemStoreGenerator;

public class JsonReportTest {
    @Test
    public void whenGenerateJsonShouldBeCorrectJson() {
        MemStoreGenerator generator = new MemStoreGenerator();
        MemStore store = generator.getStore();
        JsonReport engine = new JsonReport(store);
        String generated = engine.generate(employee -> employee.getName().contains("Ivan"));
        String expected = "[ {\n" +
                "  \"fired\" : \"2021-02-03\",\n" +
                "  \"hired\" : \"2021-02-03\",\n" +
                "  \"name\" : \"Ivan\",\n" +
                "  \"salary\" : 100.0\n" +
                "} ]";
        Assert.assertEquals(generated, expected);
    }
}