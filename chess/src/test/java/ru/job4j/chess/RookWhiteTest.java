package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.RookWhite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class RookWhiteTest {
    @Test
    public void WhenRookTurnZeroXPlusY() {
        RookWhite rookWhite = new RookWhite(Cell.A1);
        Cell[] result = rookWhite.way(Cell.A1, Cell.A4);
        assertThat(result, is(new Cell[]{Cell.A2, Cell.A3, Cell.A4}));
    }

    @Test
    public void WhenRookTurnPlusXZeroY() {
        RookWhite rookWhite = new RookWhite(Cell.A4);
        Cell[] result = rookWhite.way(Cell.A4, Cell.D4);
        assertThat(result, is(new Cell[]{Cell.B4, Cell.C4, Cell.D4}));
    }

    @Test
    public void WhenRookTurnMinusXZeroY() {
        RookWhite rookWhite = new RookWhite(Cell.C6);
        Cell[] result = rookWhite.way(Cell.C6, Cell.C2);
        assertThat(result, is(new Cell[]{Cell.C5, Cell.C4, Cell.C3, Cell.C2}));
    }


}
