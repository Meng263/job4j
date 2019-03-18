package ru.job4j.array;

/**
 * Проверка диагоналей матрицы на одинаковые значения
 */
public class MatrixCheck {
    /**
     *
     * @param data матрица, которую проверяем
     * @return True, если по диагоналям одинаковые значения
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean firstDiago = data[0][0]; // Первый элемент первой диагонали
        boolean secondDiago = data[data.length - 1][0]; // Первый элемент второй диагонали
        int d1 = 0; //счетчик 1 диагонали
        int d2 = data.length - 1; //счетчик 2 диагонали
        for (int i = 0; i < data.length; i++) {
            if ((firstDiago != data[i][d1]) || (secondDiago != data[i][d2])) {
                result = false;
                break;
            }
            d1++;
            d2--;
            }
        return result;
    }
}