package ru.job4j.solid.srp.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.Store;

import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements IReport {
    private final Store store;

    public XmlReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        try {
            xml = xmlMapper.writeValueAsString(employees);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
