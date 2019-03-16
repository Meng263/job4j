package ru.job4j.loop;

/**
 * @author Oleg Sivakov
 * @version $Id$
 * @since 16.03.19
 */
public class Paint {
    /**
     *
     * @param height высота
     * @return правая часть пирамиды
     */
    public String rightTrl(int height) {
        // Буфер для результата.
        StringBuilder screen = new StringBuilder();
        // ширина будет равна высоте.
        int width = height;
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != width; column++) {
                // если строка равна ячейке, то рисуем галку.
                // в данном случае строка определяет, сколько галок будет в строке
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }

    /**
     *
     * @param height высота
     * @return левая часть пирамиды
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= width- column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     *
     * @param height высота
     * @return пирамида
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int width= 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}