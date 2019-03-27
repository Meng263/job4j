package ru.job4j.array;

/**
 * Проверка диагоналей матрицы на одинаковые значения
 */
public class MatrixCheck {
    /**
     * @param data матрица, которую проверяем
     * @return True, если по диагоналям одинаковые значения
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean first = data[0][0];
        boolean second = data[data.length - 1][0];
        int d1 = 0;
        int d2 = data.length - 1;
        for (int i = 0; i < data.length; i++) {
            if ((first != data[i][d1]) || (second != data[i][d2])) {
                result = false;
                break;
            }
            d1++;
            d2--;
        }
        return result;
    }
}