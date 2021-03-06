package ru.job4j.loop;

/**
 * Шахматная доска
 */
public class Board {
    /**
     * @param width  ширина строки
     * @param height кол-во строк
     * @return строку с символьной графикой(шахматная доска)
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                if ((i + k) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}