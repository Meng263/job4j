package ru.job4j.solid.srp.report;

import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.Store;

import java.util.List;
import java.util.function.Predicate;

public class DevReport implements Report {
    private final Store store;

    public DevReport(Store store) {
        this.store = store;
    }


    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("<html>\n<body>\n<table style=\"border: 1px solid black\">\n")
                .append("<tr><th>Name<th><th>Hired<th><th>Fired<th><th>Salary<th></tr>\n");
        List<Employee> workers = store.findBy(filter);

        workers.forEach(employee ->
                reportBuilder.append(
                        String.format("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>",
                                employee.getName(), employee.getHiredString(), employee.getFiredString(), employee.getSalary()))
                        .append("\n")
        );
        reportBuilder.append("</table>\n</body>\n</html>");
        return reportBuilder.toString();
    }
}
