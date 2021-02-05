package ru.job4j.solid.srp.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.Store;

import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements IReport {
    private final Store store;

    public JsonReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = "";
        try {
            jsonResult = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(employees);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
