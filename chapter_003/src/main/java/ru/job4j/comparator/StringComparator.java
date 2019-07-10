package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Компаратор для строк
 */
public class StringComparator implements Comparator<String> {
    /**
     * Метод реализует лексикографическое сравнение строк
     *
     * @param left  первая строка
     * @param right вторая строка
     * @return 0, если равны; больше 0, если позиция второй строки больше ; меньше 0, если позиция первой строки больше
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int length = (left.length() > right.length()) ? right.length() : left.length();
        for (int i = 0; i < length; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                result = Character.compare(left.charAt(i), right.charAt(i));
                break;
            }
        }
        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}
