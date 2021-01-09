package ru.job4j.solid.srp.report;

import org.junit.Test;
import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.MemStore;
import ru.job4j.solid.srp.MemStoreGenerator;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DevReportTest {
    @Test
    public void devReportShouldBeHTMLReport() {
        MemStoreGenerator generator = new MemStoreGenerator();
        MemStore store = generator.getStore();
        List<Employee> workers = generator.getWorkers();
        DevReport engine = new DevReport(store);

        StringBuilder expected = new StringBuilder();
        expected.append("<html>\n<body>\n<table style=\"border: 1px solid black\">\n")
                .append("<tr><th>Name<th><th>Hired<th><th>Fired<th><th>Salary<th></tr>\n");
        workers.forEach(employee ->
                expected.append(String.format("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>",
                        employee.getName(), employee.getHiredString(), employee.getFiredString(), employee.getSalary()))
                        .append("\n")
        );
        expected.append("</table>\n</body>\n</html>");
        assertThat(engine.generate(em -> true), is(expected.toString()));
    }
}