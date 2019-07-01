package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookWhite implements Figure {
    private final Cell position;

    public RookWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод возвращает массив ячеек, по которым движется фигура
     * @param source начальная ячейка
     * @param dest конечная ячейка
     * @return массив ячеек начиная со следующей от начальной
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (!this.isStraightLine(source, dest)) throw new ImpossibleMoveException("Wrong way!");
            int directX = dest.x - source.x;
            int directY = dest.y - source.y;
            steps = new Cell[Math.abs(directX == 0 ? directY : directX)];
            for (int i = 0, kX = 0, kY = 0; i < steps.length; i++) {
                kX = directX == 0 ? 0 : directX > 0 ? ++kX : --kX;
                kY = directY == 0 ? 0 : directY > 0 ? ++kY : --kY;
                steps[i] = Cell.values()[(source.x + kX) * 8 + source.y + kY];
            }
        return steps;
    }

    /**
     * Метод проверят корректность пути фигуры "Ладья", движется по вертикальным и горизонтальным линиям
     * для проверки используется логическая функция исключающее ИЛИ: или горизонталь или вертикаль
     * @param source начальная ячейка
     * @param dest конечная ячейка
     * @return true, если путь корректен
     */
    private boolean isStraightLine(Cell source, Cell dest) {
        return ((source.x == dest.x) ^ (source.y == dest.y));
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
