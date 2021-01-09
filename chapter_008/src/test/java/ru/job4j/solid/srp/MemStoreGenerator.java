package ru.job4j.solid.srp;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MemStoreGenerator {
    private final List<Employee> workers = List.of(
            new Employee("Ivan", LocalDateTime.now(), LocalDateTime.now(), 100),
            new Employee("Goro", LocalDateTime.now(), LocalDateTime.now(), 200),
            new Employee("Gena", LocalDateTime.now(), LocalDateTime.now(), 150),
            new Employee("Yan", LocalDateTime.now(), LocalDateTime.now(), 120),
            new Employee("Joe", LocalDateTime.now(), LocalDateTime.now(), 90)
    );

    public List<Employee> getWorkers() {
        return workers;
    }

    public List<Employee> getSortedWorkers() {
        return workers.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
    }

    public MemStore getStore() {
        MemStore store = new MemStore();
        workers.forEach(store::add);
        return store;
    }
}