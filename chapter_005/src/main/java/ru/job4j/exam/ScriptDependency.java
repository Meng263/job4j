package ru.job4j.exam;

import java.util.*;

/**
 * Задача список скриптов с указанием их зависимосей.
 *
 * 1 - [2, 3], 2 - [4], 3 - [4, 5], 4 - [], 5 - []
 *
 * Необходим написать метод, который возвращает список всех скриптов, которые нужно для загрузки входящего скрипта.
 *
 * Например, чтобы выполнить скрипт 1. нужно выполнить скрипт (2, 3), которые в свою очередь зависят от 4 и 5 скрипта.
 *
 * List load(Map<Integer, List ds, Integer scriptId)
 */
public class ScriptDependency {
    /**
     * Метод, возвращает список всех скриптов, от которых зависит входящий скрипт
     * @param ds Карта зависимости скриптов
     * @param scriptId входящий скрипт
     * @return список всех скриптов, от которых зависит входящий скрипт
     */
    List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        Set<Integer> result = new HashSet<>();
        List<Integer> temp;
        result.add(scriptId);
        Queue<Integer> data = (Queue<Integer>) ds.get(scriptId);
        result.addAll(data);
        while (!data.isEmpty()) {
            temp = ds.get(data.poll());
            data.addAll(temp);
            result.addAll(temp);
        }
        return new ArrayList<>(result);
    }
}
