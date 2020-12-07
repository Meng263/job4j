package ru.job4j.solid.srp;

import java.util.function.Predicate;

public class ReportEngine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHiredString()).append(";")
                    .append(employee.getFiredString()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}