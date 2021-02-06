package ru.job4j.solid.srp.report;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.solid.srp.MemStore;
import ru.job4j.solid.srp.MemStoreGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class XmlReportTest {
    @Test
    public void whenGenerateJsonShouldBeCorrectJson() {
        MemStoreGenerator generator = new MemStoreGenerator();
        MemStore store = generator.getStore();
        XmlReport engine = new XmlReport(store);
        String generated = engine.generate(employee -> employee.getName().contains("Ivan"));
        String expected = String.format("<ArrayList><item>" +
                "<fired>%s</fired>" +
                "<hired>%s</hired>" +
                "<name>Ivan</name>" +
                "<salary>100.0</salary>" +
                "</item></ArrayList>", localDateToString(LocalDateTime.now()), localDateToString(LocalDateTime.now()));
        Assert.assertEquals(generated, expected);
    }

    private String localDateToString(LocalDateTime localDateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
        return format.format(localDateTime);
    }
}