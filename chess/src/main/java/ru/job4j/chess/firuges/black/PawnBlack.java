package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack implements Figure {
    private final Cell position;


    public PawnBlack(final Cell position) {
        this.position = position;

    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (source.x == dest.x && source.y == 6 && source.y == dest.y + 2) {
            steps = new Cell[]{Cell.values()[(dest.x) * 8 + dest.y + 1], dest};
        } else if (source.x == dest.x && source.y == dest.y + 1) {
            steps = new Cell[]{dest};
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
