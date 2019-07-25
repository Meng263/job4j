package ru.job4j.depatrment;

import java.util.*;

/**
 * Сортировка департаментов
 */
public class SortDepartment {
    /**
     * Метод заполняет список департаментов недостающими элементами
     * Для проверики на уникальность элементов использыется HashSet
     * @param list список
     * @return полный список
     */
    private List<String> fillToFullDepartments(List<String> list) {
        Set<String> set = new HashSet<>(list);
        for (String elem : list) {
            if (elem.contains("\\")) {
                set.add(elem.substring(0, elem.lastIndexOf("\\")));
            }
            set.add(elem);
        }
        return new ArrayList<>(set);
    }

    /**
     * Метод сортирует по возрастанию
     * @param array массив
     * @return полный отсортированный массиив
     */
    public String[] sort(String[] array) {
        List<String> list = new ArrayList<String>(Arrays.asList(array));
        list = fillToFullDepartments(list);
        Collections.sort(list);
        return  list.toArray(new String[list.size()]);
    }

    /**
     * Метод сортирует по убыванию
     * Сначала сравниваем посимвольно, затем по длине
     * @param array массив
     * @return полный отсортированный массив по убыванию
     */
    public String[] sortReverse(String[] array) {
        List<String> list = new ArrayList<>(Arrays.asList(array));
        list = fillToFullDepartments(list);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;
                int length = o1.length() < o2.length() ? o1.length() : o2.length();
                for (int i = 0; i < length; i++) {
                    result = Character.compare(o2.charAt(i), o1.charAt(i));
                    if (result != 0) {
                        break;
                    }
                }
                if (result == 0) {
                    result = Integer.compare(o1.length(), o2.length());
                }
                return result;
            }
        });
        return list.toArray(new String[list.size()]);
    }
}
