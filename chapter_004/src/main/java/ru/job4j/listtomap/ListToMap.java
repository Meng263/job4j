package ru.job4j.listtomap;
import ru.job4j.stream.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Преобразование списка в отображение
 */
public class ListToMap {
    /**
     * Метод преобразует список в map, с ключем score
     * @param list список студентов
     * @return map, ключ поле score, занение студент
     */
    Map<Integer, Student> convert(List<Student> list) {
        Map<Integer, Student> map = list.stream().distinct().collect(Collectors.toMap(Student::getScore, student -> student));
        return map;
    }
}
