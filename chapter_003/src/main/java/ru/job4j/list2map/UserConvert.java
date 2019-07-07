package ru.job4j.list2map;

import java.util.HashMap;
import java.util.List;

/**
 * Преобразование List в Map
 */
public class UserConvert {
    /**
     * Метод конвертирует List в Map, в качестве ключа используется поле id
     * @param list список пользователей
     * @return Map с ключем id
     */
    public HashMap<Integer, User> process(List<User> list){
        HashMap<Integer, User> map = new HashMap<>();
        for (User elem : list) {
            map.put(elem.getId(), elem);
        }
        return map;
    }
}
