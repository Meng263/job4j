package ru.job4j.solid.srp.report;

import org.junit.Test;
import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.MemStore;
import ru.job4j.solid.srp.MemStoreGenerator;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStoreGenerator generator = new MemStoreGenerator();
        MemStore store = generator.getStore();
        List<Employee> workers = generator.getWorkers();
        ReportEngine engine = new ReportEngine(store);

        StringBuilder expect = new StringBuilder();
        expect.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        workers.forEach(employee ->
                expect.append(employee.getName()).append(";")
                        .append(employee.getHiredString()).append(";")
                        .append(employee.getFiredString()).append(";")
                        .append(employee.getSalary()).append(";")
                        .append(System.lineSeparator())
        );

        System.out.println(engine.generate(em -> true));
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}