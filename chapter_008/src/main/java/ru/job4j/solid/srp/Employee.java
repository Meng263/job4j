package ru.job4j.solid.srp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Employee {
    private String name;
    private LocalDateTime hired;
    private LocalDateTime fired;
    private double salary;

    public Employee(String name, LocalDateTime hired, LocalDateTime fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getHired() {
        return hired;
    }

    public void setHired(LocalDateTime hired) {
        this.hired = hired;
    }

    public String getHiredString() {
        return localDateToString(getHired());
    }

    public LocalDateTime getFired() {
        return fired;
    }

    public String getFiredString() {
        return localDateToString(getFired());
    }

    public void setFired(LocalDateTime fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private String localDateToString(LocalDateTime localDateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
        return format.format(localDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employer = (Employee) o;
        return Objects.equals(name, employer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}