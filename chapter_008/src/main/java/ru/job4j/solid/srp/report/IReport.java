package ru.job4j.solid.srp.report;

import ru.job4j.solid.srp.Employee;

import java.util.function.Predicate;

public interface IReport {
    String generate(Predicate<Employee> filter);
}
