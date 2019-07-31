package ru.job4j.studentgroup;

import java.util.Comparator;
import java.util.Objects;

public class Student implements Comparator<Student> {
    private String name;
    private int scope;

    public Student() {
    }

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    @Override
    public int compare(Student o1, Student o2) {
        int result;
        if ((o1 == null) || (o2 == null)) {
            return 0;
        }
        result = Integer.compare(o2.getScope(), o1.getScope());
        if (result == 0) {
            result = o1.getName().compareTo(o2.getName());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return scope == student.scope
                && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scope);
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", scope=" + scope + '}';
    }
}