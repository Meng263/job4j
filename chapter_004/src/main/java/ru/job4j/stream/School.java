package ru.job4j.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Фильтрация учеников
 */
public class School {
    /**
     * Метод фильтрует список учеников по предикату
     * @param students список учеников
     * @param predicate предикат
     * @return отфильтрованынй список
     */
    List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        List<Student> result = students.stream().filter(predicate).collect(Collectors.toList());
        return result;
    }
}
