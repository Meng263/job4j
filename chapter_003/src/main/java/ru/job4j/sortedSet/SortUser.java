package ru.job4j.sortedSet;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Конвертация из списка в отсортированное множество
 */
public class SortUser {
    /**
     * Метод возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания
     * @param list список пользователей
     * @return TreeSet
     */
    public Set<User> sort (List<User> list) {
        Set<User> result;
        result = new TreeSet<User>();
        for (User item : list) {
            result.add(item);
        }
        return result;
    }
}
