package ru.job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Метод проверяет выграли ли X или O по одной последовательности(строке, столбце, диагонали)
     *
     * @param predicate Ссылка на метод, имеется ли в ячейке X или O
     * @param startX    начальное значение X
     * @param startY    начальное значение Y
     * @param deltaX    приращение X
     * @param deltaY    приращение Y
     * @return true, если на одной последовательности X или O(в зависимости от переданного predicate)
     */
    private boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Метод проверяет выиграл ли X или O на всем поле
     *
     * @param predicate Ссылка на метод, имеется ли в ячейке X или O
     * @return true, если выиграл X или O(в зависимости от переданного predicate)
     */
    private boolean isWinner(Predicate<Figure3T> predicate) {
        boolean isWinnerHorizontal = false,
                isWinnerVertical = false,
                isWinnerDiagonal = false;
        for (int i = 0; i < table.length; i++) {
            if (this.fillBy(predicate, 0, i, 1, 0)) {
                isWinnerHorizontal = true;
                break;
            }
        }
        if (!isWinnerHorizontal) {
            for (int i = 0; i < table.length; i++) {
                if (this.fillBy(predicate, i, 0, 0, 1)) {
                    isWinnerVertical = true;
                    break;
                }
            }
        }
        if (!(isWinnerHorizontal || isWinnerVertical)) {
            isWinnerDiagonal = this.fillBy(predicate, 0, 0, 1, 1)
                    || this.fillBy(predicate, this.table.length - 1, 0, -1, 1);
        }
        return isWinnerHorizontal
                || isWinnerVertical
                || isWinnerDiagonal;
    }

    /**
     * Метод проверяет выиграл ли X на всем поле
     *
     * @return true, если выиграл
     */
    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    /**
     * Метод проверяет выиграл ли O на всем поле
     *
     * @return true, если выиграл
     */
    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    /**
     * Метод проверяет доступность ячеек для ходов
     *
     * @return true, если есть свободные ячейки для ходов
     */
    public boolean hasGap() {
        int cells = table.length * table[0].length;
        int counter = 0;
        boolean result = true;
        for (Figure3T[] str : table) {
            for (Figure3T cell : str) {
                if (cell.hasMarkO() || cell.hasMarkX()) {
                    counter++;
                }
            }
            if (counter == cells) {
                result = false;
            }
        }
        return result;
    }
}
