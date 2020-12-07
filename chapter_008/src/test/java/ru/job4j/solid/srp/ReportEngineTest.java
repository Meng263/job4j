package ru.job4j.solid.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.time.LocalDateTime;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", LocalDateTime.now(), LocalDateTime.now(), 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHiredString()).append(";")
                .append(worker.getFiredString()).append(";")
                .append(worker.getSalary()).append(";0");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}