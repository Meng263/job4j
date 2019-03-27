package ru.job4j.array;

/**
 * Обертка над строкой.
 */
public class ArrayChar {
    private char[] data;

    /**
     *
     * @param line Слово, которое будем проверять
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет, что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        for (int i = 0; i < prefix.length(); i++) {
            if (data[i] != prefix.charAt(i)) {
                result = false;
                break;
            }
        }
        return result;
    }
}