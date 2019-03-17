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
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if ((i == j) && (data[i][j] != firstDiago)) { // Проверяем 1 диагональ
                        result = false;
                        break;
                    }
                if (((i + j) == data.length - 1) && (data[i][j] != secondDiago)){ // Проверяем 2 диагональ
                    result = false;
                    break;
                }

            }
        }
        return result;
    }
}