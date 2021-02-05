package ru.job4j.solid.srp.report;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.srp.MemStore;
import ru.job4j.solid.srp.MemStoreGenerator;

public class XmlReportTest {
    @Test
    public void whenGenerateJsonShouldBeCorrectJson() {
        MemStoreGenerator generator = new MemStoreGenerator();
        MemStore store = generator.getStore();
        XmlReport engine = new XmlReport(store);
        String generated = engine.generate(employee -> employee.getName().contains("Ivan"));
        String expected = "<ArrayList><item>" +
                "<fired>2021-02-03</fired>" +
                "<hired>2021-02-03</hired>" +
                "<name>Ivan</name>" +
                "<salary>100.0</salary>" +
                "</item></ArrayList>";
        Assert.assertEquals(generated, expected);
    }
}