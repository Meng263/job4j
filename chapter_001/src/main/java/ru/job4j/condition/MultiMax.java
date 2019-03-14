package ru.job4j.condition;

/**
 * Нахождение максимума из 3 чисел
 */
public class MultiMax {
    /**
     *
     * @param first первое число
     * @param second второе число
     * @param third третье число
     * @return максимум
     */
    public int max(int first, int second, int third) {
        int result = first;
        result = second > result ? second : result;
        result = third > result ? third : result;
       // result = first > result ? first : result; // лишняя проверка

        return result;
    }
}
