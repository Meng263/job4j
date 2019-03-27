package ru.job4j.condition;

/**
 * Максимум 2 чисел
 */
public class Max {
    /**
     * @param left  1 число
     * @param right 2 число
     * @return максимум
     */
    public int max(int left, int right) {
        return left > right ? left : right;
    }
}