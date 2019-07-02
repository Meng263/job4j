package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite implements Figure {
    private final Cell position;

    public PawnWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод реализует перемещение пешки, если пешка стоит на начальной позиции, может ходить на 2 клетки вперед
     * в остальных случаях на 1 клетку вперед
     *
     * @param source начальная ячейка
     * @param dest   конечная ячейка
     * @return ячеку назначения если джижется на 1 клетку
     * ячейку назначения и предыдущую, если движется на 2 клетки, начиная с предыдущей
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[0];
        if (!this.checkWay(source, dest)) throw new ImpossibleMoveException("Wrong way!");
        else if (source.x == dest.x && source.y == 1 && source.y == dest.y - 2) {
            steps = new Cell[]{Cell.values()[(dest.x) * 8 + dest.y - 1], dest};
        } else if (source.x == dest.x && source.y == dest.y - 1) {
            steps = new Cell[]{dest};
        }
        return steps;
    }

    /**
     * Метод проверяет возможность перемещения пешки
     *
     * @param source начальное значение
     * @param dest   конечное значение
     * @return true, если путь свободен
     */
    private boolean checkWay(Cell source, Cell dest) {
        boolean result = false;
        if ((source.x == dest.x && source.y == 1 && source.y == dest.y - 2) || (source.x == dest.x && source.y == dest.y - 1)) {
            result = true;
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
