package ru.job4j.solid.srp;

import org.junit.Test;
import ru.job4j.solid.srp.report.ReportEngine;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGeneratedThanValidHTML() {
        MemStore store = new MemStore();

        List<Employee> workers = List.of(
                new Employee("Ivan", LocalDateTime.now(), LocalDateTime.now(), 100),
                new Employee("Goro", LocalDateTime.now(), LocalDateTime.now(), 200),
                new Employee("Gena", LocalDateTime.now(), LocalDateTime.now(), 150),
                new Employee("Yan", LocalDateTime.now(), LocalDateTime.now(), 120),
                new Employee("Joe", LocalDateTime.now(), LocalDateTime.now(), 90)
        ).stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());

        workers.forEach(store::add);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expected = new StringBuilder();
        expected.append("<html>\n<body>\n<table style=\"border: 1px solid black\">\n")
                .append("<tr><th>Name<th><th>Salary<th></tr>\n");
        workers.forEach(employee ->
                expected.append(String.format("<tr> <th> %s </th> <th> $%s </th> </tr>", employee.getName(), employee.getSalary()))
                        .append("\n")
        );
        expected.append("</table>\n</body>\n</html>");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }
}