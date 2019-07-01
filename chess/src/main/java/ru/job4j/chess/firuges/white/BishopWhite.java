package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite implements Figure {
    private final Cell position;

    public BishopWhite(final Cell position) {
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
        if (!isDiagonal(source, dest)) throw new ImpossibleMoveException("Wrong way!");
        Cell[] steps = new Cell[0];
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        steps = new Cell[Math.abs(dest.x - source.x)];
        for (int i = 0, kX = 0, kY = 0; i < steps.length; i++) {
            kX = deltaX > 0 ? ++kX : --kX;
            kY = deltaY > 0 ? ++kY : --kY;
            steps[i] = Cell.values()[(source.x + kX) * 8 + source.y + kY];
        }
        return steps;
    }

    /**
     * Метод проверяет коррктность движения фигуры "Слон"
     * Слон движется по диагоналями
     * @param source начальная ячейка
     * @param dest ячейка назначения
     * @return true, если путь это диагональ
     */
    private boolean isDiagonal(Cell source, Cell dest){
        return (Math.abs(dest.x - source.x) == Math.abs(dest.y - source.y));
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
