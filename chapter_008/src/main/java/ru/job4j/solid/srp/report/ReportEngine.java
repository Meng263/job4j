package ru.job4j.solid.srp.report;

import ru.job4j.solid.srp.Employee;
import ru.job4j.solid.srp.Store;

import java.util.function.Predicate;

public class ReportEngine implements IReport {
    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHiredString()).append(";")
                    .append(employee.getFiredString()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}