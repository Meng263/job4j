package ru.job4j.solid.srp.report;

import org.junit.Test;
import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.MemStore;
import ru.job4j.solid.srp.MemStoreGenerator;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HrReportTest {
    @Test
    public void hrReportShouldBeDescendingWithTwoFields() {
        MemStoreGenerator generator = new MemStoreGenerator();
        MemStore store = generator.getStore();
        List<Employee> workers = generator.getSortedWorkers();
        HrReport engine = new HrReport(store);

        StringBuilder expect = new StringBuilder();
        expect.append("Name; Salary;")
                .append(System.lineSeparator());
        workers.forEach(employee ->
                expect.append(employee.getName()).append(";")
                        .append(employee.getSalary()).append(";")
                        .append(System.lineSeparator())
        );
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}