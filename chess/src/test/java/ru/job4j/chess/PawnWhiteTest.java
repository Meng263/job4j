package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PawnWhiteTest {

    @Test
    public void whenPawnTurnTwoCell() {
        PawnWhite pawn = new PawnWhite(Cell.A2);
        Cell[] result = pawn.way(Cell.A2, Cell.A4);
        assertThat(result, is(new Cell[]{Cell.A3, Cell.A4}));
    }
    @Test
    public void whenPawnTurnOneCell() {
        PawnWhite pawn = new PawnWhite(Cell.A3);
        Cell[] result = pawn.way(Cell.A3, Cell.A4);
        assertThat(result, is(new Cell[]{Cell.A4}));
    }
}
