package ru.job4j.studentgroup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Группировка учеников
 */
public class StudentGroup {
    /**
     * Возвращает спискок студендов с баллом больше bound
     *
     * @param students список студентов
     * @param bound    проходной бал
     * @return список студентов
     */
    List<Student> levelOf(List<Student> students, int bound) {
        return students
                .stream()
                .sorted(new Student())
                .flatMap(Stream::ofNullable)
                .takeWhile(student -> student.getScope() > bound)
                .collect(Collectors.toList());
    }
}
