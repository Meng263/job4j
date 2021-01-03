package ru.job4j.solid.srp;

import org.junit.Test;
import ru.job4j.solid.srp.report.ReportEngine;

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

        expect.append("<html>\n<body>\n<table style=\"border: 1px solid black\">\n")
                .append("<tr><th>Name<th><th>Salary<th></tr>\n");
        workers.forEach(employee ->
                expect.append(
                        String.format("<tr> <th> %s </th> <th> $%s </th> </tr>", employee.getName(), employee.getSalary()))
                .append("\n")
        );
        expect.append("</table>\n</body>\n</html>");

        System.out.println(expect.toString());
        System.out.println(engine.generate(em -> true));
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}