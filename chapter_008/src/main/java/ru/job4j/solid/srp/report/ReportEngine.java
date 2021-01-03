package ru.job4j.solid.srp.report;

import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportEngine {
    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("<html>\n<body>\n<table style=\"border: 1px solid black\">\n")
                .append("<tr><th>Name<th><th>Salary<th></tr>\n");
        List<Employee> workers = store.findBy(filter)
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        workers.forEach(employee ->
                reportBuilder.append(
                        String.format("<tr> <th> %s </th> <th> $%s </th> </tr>", employee.getName(), employee.getSalary()))
                        .append("\n")
        );
        reportBuilder.append("</table>\n</body>\n</html>");
        return reportBuilder.toString();
    }
}