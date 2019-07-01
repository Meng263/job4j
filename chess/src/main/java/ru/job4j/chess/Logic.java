package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Метод реализует движение фигур
     *
     * @param source исходная клетка
     * @param dest   клетка, куда фигуру перемещаем
     * @return true, если перемещение состоялось
     */
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        boolean busy = false;
        int index = this.findBy(source);
        Cell[] steps = new Cell[0];
        if (index != -1) {
            try {
                steps = this.figures[index].way(source, dest);
            } catch (ImpossibleMoveException ime) {
                System.out.println(ime.getMessage());
            }
            for (Cell i : steps) {
                if (this.isBusy(i)) {
                    busy = true;
                    break;
                }
            }
            if (!busy && steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    /**
     * Метод очищает массив фигур
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Поиск, проверяем какая фигура находится в данной ячейке
     *
     * @param cell ячейка
     * @return индекс фигуры, -1 если фигура не найдена
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public boolean isBusy(Cell cell) {
        boolean result = true;
        if (this.findBy(cell) == -1) result = false;
        return result;
    }
}
