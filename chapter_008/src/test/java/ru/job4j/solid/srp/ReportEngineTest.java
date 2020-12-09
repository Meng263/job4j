package ru.job4j.solid.srp;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();

        List<Employee> workers = List.of(
                new Employee("Ivan", LocalDateTime.now(), LocalDateTime.now(), 100),
                new Employee("Goro", LocalDateTime.now(), LocalDateTime.now(), 200),
                new Employee("Gena", LocalDateTime.now(), LocalDateTime.now(), 150),
                new Employee("Yan", LocalDateTime.now(), LocalDateTime.now(), 120),
                new Employee("Joe", LocalDateTime.now(), LocalDateTime.now(), 90)
        );

        workers.forEach(store::add);
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

        System.out.println(expect.toString());
        System.out.println(engine.generate(em -> true));
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}