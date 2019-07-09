package ru.job4j.sort;

import java.util.*;

/**
 * Конвертация из списка в отсортированное множество
 */
public class SortUser {
    /**
     * Метод возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания
     *
     * @param list список пользователей
     * @return TreeSet
     */
    public Set<User> sort(List<User> list) {
        Set<User> result;
        result = new TreeSet<>();
        for (User item : list) {
            result.add(item);
        }
        return result;
    }

    /**
     * Метод сортирует список по возрастанию длины имени
     *
     * @param list список
     * @return список
     */
    public List<User> sortNameLength(List<User> list) {
        List<User> result = new ArrayList<>(list);
        result.sort(new UserComparator());
        return result;
    }

    /**
     * Метод сортирует список по всем полям, сначала по имени, затем по возрасту
     * @param list список
     * @return список
     */
    public List<User> sortByAllFields(List<User> list) {
        List<User> result = new ArrayList<>(list);
        result.sort(new Comparator<User>() {
            @Override
            public int compare(User left, User right) {
                int result = left.getName().compareTo(right.getName());
                if (result == 0) {
                    result = Integer.compare(left.getAge(), right.getAge());
                }
                return result;
            }
        });
        return result;
    }
}
