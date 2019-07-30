package ru.job4j.listtomap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Преобразование списка в отображение
 */
public class ListToMap {
    /**
     * Метод преобразует список в map, с ключем name
     * @param list список студентов
     * @return map, ключ поле score, занение студент
     */
    Map<String, Student> convert(List<Student> list) {
        Map<String, Student> map = list.stream().distinct().collect(Collectors.toMap(Student::getName, student -> student));
        return map;
    }
}
